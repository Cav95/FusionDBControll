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

UPDATE descrizionigruppi
SET GRUPPO = SUBSTRING(GRUPPO,1,3);

select des.DESCRIZIONE , des.INGLESE , des.Gruppo, count(*) as num 
from descrizionigruppi as des
group by 1,2,3 having count(*)>1;


SELECT *
FROM descrizionigruppi
WHERE DESCRIZIONE LIKE "%PIPPO%";

select distinct des1.DESCRIZIONE , des1.INGLESE
from descrizionigruppi as des1 , descrizionigruppi as des2
where des1.DESCRIZIONE = des2.DESCRIZIONE
and des1.INGLESE <> des2.INGLESE;
