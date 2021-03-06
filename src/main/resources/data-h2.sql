INSERT INTO ADDRESS VALUES ('a4c9bac6cdba431dae8df36a3f568a12', 'DN' , 'Sector V' , 'Kolkata', 'India', 'West Bengal ' , '700091');
INSERT INTO ADDRESS VALUES ('bcb046de77724a2b89a9c26a20f02ea5', '304' , 'New Saddle St.' , 'St. Louis County', 'Minnesota', 'Great Lakes ' , '52801');
INSERT INTO ADDRESS VALUES ('cfa4a9c0fa1c4be38556c67abe86c9e1', '404' , 'Dummy St.' , 'Dummy City', 'Dummy Country', 'Dummy State' , '55701');
INSERT INTO ADDRESS VALUES ('cfa4a9c0fa1c4be38556c67abe86c9e2', '504' , 'Saddle St.' , 'Test City', 'Test Country', 'Test State' , '55801');
INSERT INTO ADDRESS VALUES ('a4c9bac6cdba431dae8df36a3f568a13', '123 Domain St' , 'Suite D St.' , 'Hermitage', 'USA', 'TN' , '37076');
INSERT INTO ADDRESS VALUES ('a4c9bac6cdba431dae8df36a3f568a14', '123 McKee Ave St' , 'D St.' , 'Chicago', 'USA', 'IL' , '60076');
INSERT INTO ADDRESS VALUES ('a4c9bac6cdba431dae8df36a3f568a15', '123 Green St' , 'Suite F' , 'GreenBay', 'USA', 'WI' , '53434');

INSERT INTO CONTACT VALUES ('3e687aaf61ef412fa367f5e43e9550ab' , 'sayantan.mitra@wipro.com' , '9874561233' , '1234567890');
INSERT INTO CONTACT VALUES ('efca1911a7914eb596768202fd28dd94'  , 'john.muller@wipro.com' , '9474561233' , '1252567890');
INSERT INTO CONTACT VALUES ('efca1911a7914eb596768202fd28dd93'  , 'john@wipro.com' , '6150000000' , '6151112222');
INSERT INTO CONTACT VALUES ('efca1911a7914eb596768202fd28dd95'  , 'roger@wipro.com' , '8470000000' , '8471112222');
INSERT INTO CONTACT VALUES ('efca1911a7914eb596768202fd28dd96'  , 'aaron@wipro.com' , '6080000112' , '6081112222');

INSERT INTO CUSTOMER VALUES ('484c5aadbbbe484fac0bb778f7b11d82' , (CURTIME()) , 1001 , 'John' , 'Doe' , 'H' , 'ABCDS1734Y' , 'Active' , (CURTIME()) , 'efca1911a7914eb596768202fd28dd93' , 'a4c9bac6cdba431dae8df36a3f568a13');
INSERT INTO CUSTOMER VALUES ('caca476e2d4c4e09be32ee58b8464e85' , (CURTIME()) , 1002 , 'Roger' , 'Federer', 'D' , 'PBAB12345Y' ,'Active' , (CURTIME()) , 'efca1911a7914eb596768202fd28dd95' , 'a4c9bac6cdba431dae8df36a3f568a14');
INSERT INTO CUSTOMER VALUES ('484c5adbbbe484fac0bb6378f7b11d85' , (CURTIME()) , 1003 , 'Aaron' , 'Rodgers' , 'B' , 'AQCDS1234Y' , 'Active' , (CURTIME()) , 'efca1911a7914eb596768202fd28dd96' , 'a4c9bac6cdba431dae8df36a3f568a15');
INSERT INTO CUSTOMER VALUES ('484c5aadbbbe484fac0bb778f7b11d85' , (CURTIME()) , 1004 , 'Sayantan' , 'Mitra' , '' , 'ABCDS1234Y' , 'Active' , (CURTIME()) , '3e687aaf61ef412fa367f5e43e9550ab' , 'a4c9bac6cdba431dae8df36a3f568a12');
INSERT INTO CUSTOMER VALUES ('caca476e2d4c4e09be32ee58b8464e15' , (CURTIME()) , 1005 , 'John' , 'Muller', 'Jr' , 'ABAB12345Y' ,'Active' , (CURTIME()) , 'efca1911a7914eb596768202fd28dd94' , 'bcb046de77724a2b89a9c26a20f02ea5');


INSERT INTO BANK_INFO VALUES ('490b99e86e024a2eb6cb411e5ed1cec8' , 1,  'SBI', 'cfa4a9c0fa1c4be38556c67abe86c9e1');
INSERT INTO BANK_INFO VALUES ('490b99e86e024a2eb6cb411e5ed1cec9' , 2,  'HDFC', 'cfa4a9c0fa1c4be38556c67abe86c9e2');

INSERT INTO ACCOUNT VALUES ('b750a4ddcdb64880a5e2fab0a4991ee4' , 50000.0 , 564561631367 , 'ACTIVE', 'SAVINGS', '19:57:59' , '19:58:59', '490b99e86e024a2eb6cb411e5ed1cec8');
INSERT INTO ACCOUNT VALUES ('b750a4ddcdb64880a5e2fab0a4991ee5' , 150000.0 , 564561631368 , 'ACTIVE', 'SAVINGS', '19:57:59' , '19:58:59', '490b99e86e024a2eb6cb411e5ed1cec9');

INSERT INTO CUSTOMER_ACCOUNTXREF  VALUES ('abc880cb8f3345b2bdf6633df86567cc' , 564561631368,1002);
INSERT INTO CUSTOMER_ACCOUNTXREF  VALUES ('abc880cb8f3345b2bdf6633df86567cd' , 564561631367,1001);
INSERT INTO CUSTOMER_ACCOUNTXREF  VALUES ('abc880cb8f3345b2bdf6633df86567ce' , 564561631369,1003);
INSERT INTO CUSTOMER_ACCOUNTXREF  VALUES ('abc880cb8f3345b2bdf6633df86567cf' , 564561631362,1004);

