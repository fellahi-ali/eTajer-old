VALUES SYSCS_UTIL.SYSCS_CHECK_TABLE('ALILO', 'ACHAT');
VALUES SYSCS_UTIL.SYSCS_CHECK_TABLE('ALILO', 'LIGNE_ACH');

VALUES SYSCS_UTIL.SYSCS_CHECK_TABLE('ALILO', 'LIGNE_VNT');
VALUES SYSCS_UTIL.SYSCS_CHECK_TABLE('ALILO', 'VENTE');

VALUES SYSCS_UTIL.SYSCS_CHECK_TABLE('ALILO', 'EN_STOCK');

DROP TRIGGER T_AFT_VALID_LACH_UPD_ENSTK;

CREATE TRIGGER T_AFT_VALID_LACH_UPD_ENSTK
AFTER UPDATE OF VALIDEE ON LIGNE_ACH
REFERENCING NEW AS LACH
FOR EACH ROW 
    UPDATE EN_STOCK SET 
        QTE =  QTE + LACH.QTE_UNIT 
    WHERE LACH.VALIDEE = TRUE AND ID = LACH.ID_EN_STK; 

DROP TRIGGER T_AFT_INVALID_LACH_UPD_ENSTK;

CREATE TRIGGER T_AFT_INVALID_LACH_UPD_ENSTK
AFTER UPDATE OF VALIDEE ON LIGNE_ACH
REFERENCING NEW AS LACH
FOR EACH ROW 
    UPDATE EN_STOCK SET 
        QTE =  QTE - LACH.QTE_UNIT
    WHERE LACH.VALIDEE = FALSE AND ID = LACH.ID_EN_STK;

DROP TRIGGER T_AFT_VALID_LVNT_UPD_STK;

CREATE TRIGGER T_AFT_VALID_LVNT_UPD_STK
AFTER UPDATE OF VALIDEE ON LIGNE_VNT
REFERENCING NEW AS LVNT
FOR EACH ROW 
    UPDATE EN_STOCK SET QTE =  QTE - LVNT.QTE_UNIT
    WHERE LVNT.VALIDEE = TRUE AND ID = LVNT.ID_EN_STK;

DROP TRIGGER T_AFT_INVALID_LVNT_UPD_STK;

CREATE TRIGGER T_AFT_INVALID_LVNT_UPD_STK
AFTER UPDATE OF VALIDEE ON LIGNE_VNT
REFERENCING NEW AS LVNT
FOR EACH ROW 
    UPDATE EN_STOCK SET QTE =  QTE + LVNT.QTE_UNIT
    WHERE LVNT.VALIDEE = FALSE AND ID = LVNT.ID_EN_STK;
