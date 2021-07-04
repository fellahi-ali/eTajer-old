DROP VIEW V_LOT_EN_STOCK; 

 CREATE VIEW V_LOT_EN_STOCK AS 
SELECT S.ID, S.ID_PROD, S.ID_DEPOT, P.ID_FAM, P.ID_CATEG ,S.COD_BAR AS "Réf.Lot", P.COD_BAR AS "Réf.Produit", P.DES AS "Désignation", 
       S.QTE AS "Qte", D.ADR AS "Dépôt", S.DATE_ENTR AS "Date.Entrée", S.PU_ACH AS "PU.Achat", S.PU_VNT_DT AS "PU.Détail", 
       S.PU_VNT_GR AS "PU.Gros", S.PU_VNT_DGR AS "PU.Demi-Gr", S.PU_VNT_SGR AS "PU.Super-Gr", 
       S.DATE_EXP AS "Date.Exp", S.ACTIF AS "Actif", S.QTE * S.PU_ACH AS "Total/Ach" 
FROM (EN_STOCK S INNER JOIN DEPOT D ON S.ACTIF = TRUE AND S.ID_DEPOT = D.ID) INNER JOIN PRODUIT P ON S.ID_PROD = P.ID 
ORDER BY S.DATE_ENTR ASC; 

DROP VIEW V_LOT_A_VENDRE; 

 CREATE VIEW V_LOT_A_VENDRE AS 
SELECT S.ID, S.ID_PROD, S.ID_DEPOT, P.ID_CATEG, S.COD_BAR AS "Réf.Lot", P.COD_BAR AS "Réf.Produit", P.DES AS "Désignation", 
    S.QTE AS "Qte", D.ADR AS "Dépôt", S.DATE_ENTR AS "Date.Entrée", S.DATE_EXP AS "Date.Exp",   
    S.PU_VNT_DT AS "PU.Détail", S.PU_VNT_GR AS "PU.Gros", S.PU_VNT_DGR AS "PU.Demi-Gr", S.PU_VNT_SGR AS "PU.Super-Gr"  
FROM (EN_STOCK S INNER JOIN DEPOT D ON S.ACTIF = TRUE AND S.QTE > 0 AND D.DE_VENTE = TRUE AND S.ID_DEPOT = D.ID) 
    INNER JOIN PRODUIT P ON S.ID_PROD = P.ID 
ORDER BY S.DATE_ENTR ASC; 

DROP TRIGGER T_AFT_UPD_QTE_UPD_ACTIF; 
 CREATE TRIGGER T_AFT_UPD_QTE_UPD_ACTIF
AFTER UPDATE OF QTE ON EN_STOCK
REFERENCING NEW ROW AS NEW_STK
FOR EACH ROW
    UPDATE  EN_STOCK SET ACTIF = TRUE 
    WHERE QTE > 0 AND ID = NEW_STK.ID; 

UPDATE EN_STOCK SET ACTIF = TRUE WHERE ID NOT IN (SELECT ID_EN_STK FROM LIGNE_ACH WHERE VALIDEE = FALSE); 

DROP TRIGGER T_AFT_VALID_LACH_UPD_ENSTK;
    CREATE TRIGGER T_AFT_VALID_LACH_UPD_ENSTK
AFTER UPDATE OF VALIDEE ON LIGNE_ACH
REFERENCING NEW AS LACH
FOR EACH ROW 
    UPDATE EN_STOCK SET 
        QTE =  QTE + LACH.QTE_UNIT 
    WHERE LACH.VALIDEE = TRUE AND ID = LACH.ID_EN_STK; 

    CREATE TRIGGER T_AFT_INVALID_LACH_UPD_ENSTK
AFTER UPDATE OF VALIDEE ON LIGNE_ACH
REFERENCING NEW AS LACH
FOR EACH ROW 
    UPDATE EN_STOCK SET 
        QTE =  QTE - LACH.QTE_UNIT
    WHERE LACH.VALIDEE = FALSE AND ID = LACH.ID_EN_STK; 

-- you must recreate all ON UPDATE OF QTE ON EN_STOCK triggers 

DROP TRIGGER T_AFT_UPD_LACH_UPD_TOTALACH;
    CREATE TRIGGER T_AFT_UPD_LACH_UPD_TOTALACH
AFTER UPDATE OF TOTAL_LACH ON LIGNE_ACH
REFERENCING OLD ROW AS OLD_LACH NEW ROW AS NEW_LACH
FOR EACH ROW
    UPDATE ACHAT SET 
        TOTAL = TOTAL - OLD_LACH.TOTAL_LACH + NEW_LACH.TOTAL_LACH
    WHERE ID = NEW_LACH.ID_ACH;

DROP TRIGGER T_AFT_UPD_LACH_DEL_ENSTK;    
    CREATE TRIGGER T_AFT_UPD_LACH_DEL_ENSTK
AFTER UPDATE OF ID_EN_STK ON LIGNE_ACH
REFERENCING OLD ROW AS OLD_LACH NEW ROW AS NEW_LACH
FOR EACH ROW
    DELETE FROM EN_STOCK 
    WHERE OLD_LACH.ID_EN_STK != NEW_LACH.ID_EN_STK
    AND ID = OLD_LACH.ID_EN_STK AND ACTIF = FALSE 
    AND ID NOT IN (SELECT ID_EN_STK FROM LIGNE_VNT UNION SELECT ID_EN_STK FROM LIGNE_ACH);

-- 21/01/2016 11:25 Abdel-Ghani et Hachemi;
-- DONE;
---------------------------------------------------;
-- 09/03/2016;
ALTER TABLE PRODUIT DROP COLUMN MARGE_DGR;
ALTER TABLE PRODUIT DROP COLUMN MARGE_SGR;
ALTER TABLE PRODUIT ADD COLUMN PU_VNT_DT DECIMAL(10, 2) NOT NULL DEFAULT 0;
ALTER TABLE PRODUIT ADD COLUMN PU_VNT_GR DECIMAL(10, 2) NOT NULL DEFAULT 0;



