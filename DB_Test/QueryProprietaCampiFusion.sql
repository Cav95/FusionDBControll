/*Select field with print filter from fusion DB*/
SELECT cam.cpNome ,prop.propValore ,cod.codCodice
from [CAMPI] as cam,
[PROPCOD] as prop,
[CODICI] as cod
where cam.cpID = prop.cpID
and cod.codID = prop.codID
and cam.cpNome in ('Officina','Preassemblaggio','Sartoria','Prodotto Finito','Spedizioni','Montatori','Ufficio Acquisti');

/*Create first time history table*/
INSERT into PRINTHISTORY(cpNome,propValore,codCodice,STARTVALUE, ENDVALUE)
SELECT cam.cpNome ,prop.propValore ,cod.codCodice, today(),date(31/12/2099)
from [CAMPI] as cam,
[PROPCOD] as prop,
[CODICI] as cod
where cam.cpID = prop.cpID
and cod.codID = prop.codID
and cam.cpNome in ('Officina','Preassemblaggio','Sartoria','Prodotto Finito','Spedizioni','Montatori','Ufficio Acquisti');
