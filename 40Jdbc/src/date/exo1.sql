CREATE TABLE letemps ( 
			id NUMBER,
			ladate DATE,
			heure DATE,
			ladateetheure DATE,
			ladateetheure2 TIMESTAMP(3)
			);


SELECT 
	to_char(ladate,"DD-MON-YYYY HH24:MI:SS"),
	to_char(heure,"DD-MON-YYYY HH24:MI:SS"),
	to_char(ladateetheure,"DD-MON-YYYY HH24:MI:SS"),
	to_char(ladateetheure2,"DD-MON-YYYY HH24:MI:SS.FF")	
FROM letemps

