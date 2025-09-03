USE desfusion;
/* CREAZIONE NUOVA RIGA */
INSERT INTO DESCRIZIONI (Descrizione, INGLESE ,Gruppo ) VALUES
('NUOVO', 'NEWADD', 'NUOVO');

/*Show all occurence */
select *
from descrizioni
order by Gruppo;

/*Delete value */
delete FROM descrizioni
where Descrizione = 'NUOVO'
AND INGLESE = 'NEWADD'
AND Gruppo = 'NUOVO';

/*Modifica Descrizione */
update descrizioni
SET Descrizione = 'PIPPO'
, INGLESE = 'PLUTO'
where Descrizione = 'NUOVO'
AND INGLESE = 'NEWADD'
AND Gruppo = 'NUOVO';

UPDATE descrizionigruppi
SET DESCRIZIONE = replace(DESCRIZIONE,"TUBO","PIPPO")
WHERE DESCRIZIONE LIKE '%TUBO%';

SELECT *
FROM descrizionigruppi
WHERE DESCRIZIONE LIKE "%PIPPO%";
