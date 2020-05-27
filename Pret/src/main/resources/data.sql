/*CREATE TABLE LIVRE
(
    isbn VARCHAR(100) PRIMARY KEY NOT NULL,
    auteur VARCHAR(100),
    titre VARCHAR(100),
    editeur VARCHAR(100),
    edition INT
);

CREATE TABLE LECTEUR
(
    id INT PRIMARY KEY NOT NULL,
    genre VARCHAR(100),
    nom VARCHAR(100),
    prenom VARCHAR(100),
    adresse VARCHAR(200),
    date_naissance DATE
);

insert into Livre(isbn, auteur, titre, editeur, edition) values('1','Emile Zola','Germinal','Flammarion',2017);
insert into Livre(isbn, auteur, titre, editeur, edition) values('2','Emile Zola','Detective','Pocket',2017);
insert into Livre(isbn, auteur, titre, editeur, edition) values('3','Mouloud Feraoune','Village','Flammarion',2019);

insert into Lecteur(id, genre, nom, prenom, date_naissance, adresse) values('1','M','Arzur','Samuel','1997-04-28','9 rue jean moulin');
insert into Lecteur(id, genre, nom, prenom, date_naissance, adresse) values('2','M','Aït-Mouloud','Lisa','1997-03-30','dans colombe');
insert into Lecteur(id, genre, nom, prenom, date_naissance, adresse) values('3','M','Arzur','Michel'w,'1963-08-19','9 rue jean moulin');*/