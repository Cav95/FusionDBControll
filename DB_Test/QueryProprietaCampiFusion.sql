/*Select field with print filter from fusion DB*/
SELECT cam.cpNome ,prop.propValore ,cod.codCodice
from CAMPI as cam,
PROPCOD as prop,
CODICI as cod
where cam.cpID = prop.cpID
and cod.codID = prop.codID
and cam.cpNome in ('Officina','Preassemblaggio','Sartoria','Prodotto Finito','Spedizioni','Montatori','Ufficio Acquisti');

/*Create first time and update with new value time history table*/
INSERT into PRINTHISTORY(cpNome,propValore,codCodice,STARTVALUE, ENDVALUE)
SELECT ps.cpNome ,ps.propValore ,ps.codCodice, now(),'2099-12-31'
from PrintPropValue as ps
where (ps.codCodice ,ps.cpNome , ps.propvalore) not in (select ph1.codCodice ,ph1.cpNome , ph1.propvalore
from printhistory ph1
where ph1.ENDVALUE <> '2099-12-31');

/*Select value with different value from PrintPropValue*/
select ph1.*
from printhistory ph1
where Id not in ( select ph.Id
					from PrintPropValue ps , printhistory ph
					where ps.codCodice = ph.codCodice
					and ps.cpNome = ph.cpNome
					and TRIM(ps.propValore) = TRIM(ph.propValore)
					and ph.ENDVALUE = '2099-12-31')
                    and ph1.ENDVALUE = '2099-12-31';
                    
/*Add to printhistory differt old value table end value*/
UPDATE printhistory ph
LEFT JOIN differentvalue dv ON ph.Id = dv.Id
SET ph.endvalue = NOW()
WHERE dv.Id IS NULL;

/*Select ultimate code from hystory*/
SELECT ph.*
from printhistory ph
where ph.ENDVALUE = '2099-12-31'
and ph.codCodice like '%';

/*Test Cambio valore*/
update PrintPropValue
set propValore ='SI'
where codCodice = 'AFF06520'
AND cpNome = 'Officina';

select *
FROM printhistory
WHERE codCodice = 'AFF06520'
AND cpNome = 'Officina';