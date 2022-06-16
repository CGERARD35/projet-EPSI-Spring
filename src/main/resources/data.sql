create table client
(
    id       serial
        primary key,
    fixe     varchar(255) not null,
    mail     varchar(255) not null,
    nom      varchar(255) not null,
    notes    varchar(255) not null,
    portable varchar(255) not null,
    prenom   varchar(255) not null,
    societe  varchar(255) not null,
    statut   varchar(255) not null
);

alter table client
    owner to projet;

create table produit
(
    id     serial
        primary key,
    nom    varchar(100),
    prix   numeric,
    statut varchar(20),
    type   varchar(50)
);

alter table produit
    owner to projet;

create table commande
(
    id            serial
        primary key,
    date_commande timestamp    not null,
    duree         varchar(255) not null,
    notes         text,
    prix          numeric      not null,
    statut        varchar(255) not null,
    client_id     integer      not null
        constraint fk79q1nginx2k3m83vi3bt3rlon
            references client,
    produit_id    integer      not null
        constraint fk3q11l1e5acu450cqiomkrv5s3
            references produit
);

alter table commande
    owner to projet;

create table utilisateur
(
    id       serial
        primary key,
    mail     varchar(70)  not null,
    nom      varchar(30)  not null,
    password varchar(255) not null,
    prenom   varchar(30)  not null,
    role     varchar(30)  not null,
    statut   varchar(15)  not null
);

alter table utilisateur
    owner to projet;


INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (1, 'admin@admin.com', 'admin', '$2a$10$JCzNVUDkGlgLiGge0tNrnuz.rkfC1v3QJ/XehqouCgIgX2IY4wTr2', 'admin', 'ROLE_ADMIN', 'Actif');
INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (2, 'mansanarez.gurvan@oneday.com', 'Mansanarez', '$2a$10$j1U3EbHuRmZPzSaqSigHMORmqdDrza42Kqpz8QnyhjU4Z3jY5nNHu', 'Gurvan', 'ROLE_USER', 'Actif');
INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (3, 'theron.benjamin@oneday.com', 'Theron', '$2a$10$eTZcwQJXyBCyhnW/dh6vMu0gG54msZd65Dazu/JzCfR6j14Ze8q4S', 'Benjamin', 'ROLE_USER', 'Actif');
INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (4, 'gerard.carl@oneday.com', 'Gerard', '$2a$10$EZBiOsfvcmoVYS.P1ARh/u27NK1MRrN6NOpGH8DYxM5c/vAksbreG', 'Carl', 'ROLE_USER', 'Actif');

INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (1, 'pack vitrine', 300, 'Actif', 'site vitrine');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (2, 'pack crm', 700, 'Actif', 'crm');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (21, 'pack vente en ligne', 1000, 'Actif', 'vente en ligne');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (4, 'pack vente e-commerce', 2000, 'Actif', 'vente e-commerce');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (5, 'pack forum', 100, 'Actif', 'forum');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (6, 'pack blog', 200, 'Actif', 'blog');

INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (7, '0237654677', 'martin.paul@petits-papiers.com', 'Martin', 'Client en imprimerie', '0687654377', 'Paul', 'les petits papiers', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (9, '0289887657', 'angele.sonamar@auto-engine.com', 'Sonamar', 'Location de voitures', '0689765434', 'Angèle', 'Auto engine', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (8, '0276543457', 'antoine.martinez@phone-century.com', 'Martinez', 'Magasin de téléphone', '07789654', 'Antoine', 'phone century', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (1, '0237654677', 'jean.petit@voilier-serein.com', 'Petit', 'Client en bateau', '0687654377', 'Jean', 'les voiliers sereins', 'Inactif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (2, '0289887657', 'anne.marquis@moto-roots.com', 'Marquis', 'Location de motos', '0689765434', 'Anne', 'Moto roots', 'Inactif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (3, '0276543457', 'nine.gonzales@pokemon-cards.com', 'Gonzales', 'Magasin de téléphone', '07789654', 'Nine', 'pokemon cards', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (4, '0237654677', 'arnaud.moisson@moissons-bretonnes.com', 'Moisson', 'Client en bateau', '0687654377', 'Arnaud', 'Les moissons bretonnes', 'Inactif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (5, '0289887657', 'jeanne.latin@moto-pas-cheres.com', 'Latin', 'Location de motos', '0689765434', 'Jeanne', 'Moto pas chères', 'Inactif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (6, '0276543457', 'marie.bruno@pokemon-games.com', 'Bruno', 'Magasin de cartes pokemon', '07789654', 'Marie', 'pokemon games', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (10, '0289887657', 'claire.delune@photomoon.com', 'Delune', 'Photographie de lune', '0689765434', 'Claire', 'Photomoon', 'Inactif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (11, '0276543457', 'marc.dupont@informatique-city.com', 'Dupont', 'Vente de produit informatique', '07789654', 'Marc', 'Informatique City', 'Actif');

INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (1, '2022-06-16 02:00:00.000000', '20', 'La cliente à payée la commande à la réception', 20000, 'Payée', 9, 21);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (2, '2022-06-14 02:00:00.000000', '30', 'Crm pour les petits papiers', 21000, 'Impayée', 7, 2);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (3, '2022-06-07 02:00:00.000000', '5', '', 1500, 'Payée', 8, 1);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (4, '2022-06-15 02:00:00.000000', '2', '', 2000, 'Payée', 6, 6);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (5, '2022-06-02 02:00:00.000000', '30', '', 30000, 'Impayée', 1, 4);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (6, '2022-06-08 02:00:00.000000', '100', '', 70000, 'Payée', 2, 2);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (7, '2022-06-04 02:00:00.000000', '100', '', 100000, 'Impayée', 1, 4);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (9, '2022-06-03 02:00:00.000000', '3', '', 3000, 'Payée', 3, 5);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (10, '2022-06-10 02:00:00.000000', '7', '', 7000, 'Payée', 2, 5);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (11, '2022-06-02 02:00:00.000000', '3', '', 3000, 'Impayée', 1, 6);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (12, '2022-06-08 02:00:00.000000', '6', '', 4200, 'Payée', 8, 2);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (13, '2022-06-09 02:00:00.000000', '3', '', 900, 'Impayée', 7, 1);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (14, '2022-06-08 02:00:00.000000', '15', '', 15000, 'Payée', 3, 21);
