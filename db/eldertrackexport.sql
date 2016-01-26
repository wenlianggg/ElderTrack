BEGIN TRANSACTION;
CREATE TABLE "et_staff" (
	`staffid`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`username`	TEXT NOT NULL UNIQUE,
	`password`	TEXT NOT NULL,
	`firstname`	TEXT NOT NULL,
	`lastname`	TEXT NOT NULL,
	`dob`	NUMERIC NOT NULL,
	`nric`	TEXT NOT NULL,
	`photo`	BLOB,
	`accesslevel`	INTEGER NOT NULL,
	`lastlogin`	NUMERIC NOT NULL,
	`staffnotes`	TEXT
);
INSERT INTO `et_staff` (staffid,username,password,firstname,lastname,dob,nric,photo,accesslevel,lastlogin,staffnotes) VALUES (1,'wlgoh','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413','Wen Liang','Goh',0,'S1234567A',NULL,1,0,NULL);
INSERT INTO `et_staff` (staffid,username,password,firstname,lastname,dob,nric,photo,accesslevel,lastlogin,staffnotes) VALUES (2,'jamese','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413','James','Escabas',0,'S1234567A',NULL,1,0,NULL);
INSERT INTO `et_staff` (staffid,username,password,firstname,lastname,dob,nric,photo,accesslevel,lastlogin,staffnotes) VALUES (3,'roytql','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413','Roy','Tang',0,'S1234567A',NULL,1,0,NULL);
INSERT INTO `et_staff` (staffid,username,password,firstname,lastname,dob,nric,photo,accesslevel,lastlogin,staffnotes) VALUES (4,'kkezia','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413','Kezia','Kew',0,'S1234567A',NULL,1,0,NULL);
CREATE TABLE "et_logs" (
	`logid`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`staffid`	INTEGER NOT NULL,
	`action`	TEXT NOT NULL,
	`target`	INTEGER
);
CREATE TABLE "et_elderly" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL,
	`dob`	NUMERIC NOT NULL,
	`nric`	TEXT NOT NULL UNIQUE,
	`gender`	TEXT NOT NULL,
	`age`	INTEGER NOT NULL,
	`room`	INTEGER NOT NULL,
	`address`	TEXT,
	`medication`	BLOB,
	`diet`	BLOB,
	`photo`	BLOB,
	`description`	TEXT,
	`elderlynotes`	TEXT
);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (1,'Ching Chong Lee',0,'S1234567G','M',69,1,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (2,'Ling Long Lim',0,'S7654321F','F',68,1,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (3,'Tan Ring Rong',0,'S8473954J','M',65,1,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (4,'Kang Ding Dong',0,'S4839572L','F',68,2,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (5,'Ho Hing Kong',0,'S3859123G','F',70,2,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (6,'Liu Ling Tong',0,'S7946132P','M',75,3,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (7,'Tan Loo Long',0,'S1231232A','F',78,3,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `et_elderly` (id,name,dob,nric,gender,age,room,address,medication,diet,photo,description,elderlynotes) VALUES (8,'Teo Jing Jong',0,'S3124512D','F',88,3,'180 Ang Mo Kio Avenue 5',NULL,NULL,NULL,NULL,NULL);
COMMIT;
