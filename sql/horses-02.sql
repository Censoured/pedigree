ALTER TABLE "HORSE" ADD CONSTRAINT "Sex_Ref" FOREIGN KEY (SEX) REFERENCES SEX(ID);
ALTER TABLE "HORSE" ADD CONSTRAINT "Color_Ref" FOREIGN KEY (COLOR) REFERENCES COLOR(ID);
ALTER TABLE "HORSE" ADD CONSTRAINT "Dam_Ref" FOREIGN KEY (DAM) REFERENCES HORSE(ID);
ALTER TABLE "HORSE" ADD CONSTRAINT "Sire_Ref" FOREIGN KEY (SIRE) REFERENCES HORSE(ID);
ALTER TABLE "HORSE" ADD CONSTRAINT "Breeder_Ref" FOREIGN KEY (BREEDER) REFERENCES BREEDER(ID);

ALTER TABLE "SEX" ADD CONSTRAINT "Sex_Enum" CHECK ( "NAME" IN ( 'MARE', 'STALLION', 'GELDING') );

INSERT INTO "SEX"(NAME) VALUES('mare');
INSERT INTO "SEX"(NAME) VALUES('stallion');
INSERT INTO "SEX"(NAME) VALUES('gelding');

INSERT INTO "COLOR"(LNAME, SNAME) VALUES('bay', 'bay');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('light bay', 'l.bay');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('dark bay', 'd.bay');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('grey', 'grey');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('chestnut', 'chest.');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('light chestnut', 'l.chest.');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('dark chestnut', 'd.chest.');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('black', 'black');
INSERT INTO "COLOR"(LNAME, SNAME) VALUES('brown', 'brown');