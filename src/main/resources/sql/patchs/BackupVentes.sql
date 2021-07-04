-- Ventes Backup
DROP TABLE DEL_VENTE;
CREATE TABLE DEL_VENTE  (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
                        ID_VNT INT NOT NULL,
                        NUM INT  NOT NULL,
                        "DATE" DATE DEFAULT CURRENT_DATE,
                        HEURE TIME NOT NULL DEFAULT CURRENT_TIME,
                        TOTAL DECIMAL(12,2) NOT NULL DEFAULT 0,
                        ID_USER INT NOT NULL,
                        DELETE_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT DEL_VNT_PK PRIMARY KEY (ID),
                        CONSTRAINT DEL_VNT_USER_FK FOREIGN KEY (ID_USER) REFERENCES APP_USER ON DELETE RESTRICT);

DROP VIEW V_DEL_VNT;
CREATE VIEW V_DEL_VNT AS
SELECT ID_VNT,  NUM AS "N°", "DATE" AS "Date", HEURE AS "Heure", TOTAL AS "Total", U.LOGIN, DELETE_TIME AS "Suppr. à" 
FROM DEL_VENTE DV INNER JOIN APP_USER U ON DV.ID_USER = U.ID;

DROP TABLE DEL_LVNT;
CREATE TABLE DEL_LVNT  (   ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,
                            ID_VNT  INT NOT NULL,
                            ID_EN_STK INT NOT NULL,
                            PU_VNT DECIMAL(12,2) NOT NULL DEFAULT 0,
                            QTE_UNIT DOUBLE NOT NULL DEFAULT 0,
                            CONSTRAINT DEL_LVNT_PK PRIMARY KEY(ID),
                            --CONSTRAINT DEL_LVNT_VNT_FK FOREIGN KEY (ID_VNT) REFERENCES DEL_VENTE ON DELETE CASCADE,
                            CONSTRAINT DEL_LVNT_ENSTK_FK FOREIGN KEY (ID_EN_STK) REFERENCES EN_STOCK ON DELETE CASCADE);


DROP VIEW V_DEL_LVNT;

CREATE VIEW V_DEL_LVNT AS
SELECT DLV.ID_VNT, P.DES, DLV.PU_VNT AS "Prix.Vnt", DLV.QTE_UNIT AS "Qte"
FROM DEL_LVNT DLV INNER JOIN EN_STOCK S ON DLV.ID_EN_STK = S.ID
                    INNER JOIN PRODUIT P ON S.ID_PROD = P.ID;

DROP TRIGGER AFT_DEL_VNT_BACKUP;

DROP TRIGGER BEF_DEL_VNT_BACKUP;

CREATE TRIGGER AFT_DEL_VNT_BACKUP
AFTER DELETE ON VENTE
REFERENCING OLD ROW AS DEL_VNT 
FOR EACH ROW
    INSERT INTO DEL_VENTE (ID_VNT, NUM, "DATE", HEURE, TOTAL, ID_USER)
    VALUES (DEL_VNT.ID, DEL_VNT.NUM, DEL_VNT."DATE", DEL_VNT.HEURE, DEL_VNT.TOTAL, DEL_VNT.ID_USER);

DROP TRIGGER AFT_DEL_LVNT_BACKUP;

DROP TRIGGER BEF_DEL_LVNT_BACKUP;

CREATE TRIGGER AFT_DEL_LVNT_BACKUP
AFTER DELETE ON LIGNE_VNT
REFERENCING OLD ROW AS DEL_LVNT 
FOR EACH ROW
    INSERT INTO DEL_LVNT (ID_VNT, ID_EN_STK, PU_VNT, QTE_UNIT)
    VALUES (DEL_LVNT.ID_VNT, DEL_LVNT.ID_EN_STK, DEL_LVNT.PU_VNT, DEL_LVNT.QTE_UNIT);