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


INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (2, 'admin@admin.com', 'admin', '$2a$10$JCzNVUDkGlgLiGge0tNrnuz.rkfC1v3QJ/XehqouCgIgX2IY4wTr2', 'admin', 'ROLE_ADMIN', 'Actif');
INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (9, 'mansanarez.gurvan@oneday.com', 'Mansanarez', '$2a$10$j1U3EbHuRmZPzSaqSigHMORmqdDrza42Kqpz8QnyhjU4Z3jY5nNHu', 'Gurvan', 'ROLE_USER', 'Actif');
INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (10, 'theron.benjamin@oneday.com', 'Theron', '$2a$10$eTZcwQJXyBCyhnW/dh6vMu0gG54msZd65Dazu/JzCfR6j14Ze8q4S', 'Benjamin', 'ROLE_USER', 'Actif');
INSERT INTO public.utilisateur (id, mail, nom, password, prenom, role, statut) VALUES (11, 'gerard.carl@oneday.com', 'Gerard', '$2a$10$EZBiOsfvcmoVYS.P1ARh/u27NK1MRrN6NOpGH8DYxM5c/vAksbreG', 'Carl', 'ROLE_USER', 'Actif');

INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (1, 'pack vitrine', 300, 'Actif', 'site vitrine');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (2, 'pack crm', 700, 'Actif', 'crm');
INSERT INTO public.produit (id, nom, prix, statut, type) VALUES (21, 'pack vente en ligne', 1000, 'Actif', 'vente en ligne');

INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (7, '0237654677', 'martin.paul@petits-papiers.com', 'Martin', 'Client en imprimerie', '0687654377', 'Paul', 'les petits papiers', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (9, '0289887657', 'angele.sonamar@auto-engine.com', 'Sonamar', 'Location de voitures', '0689765434', 'Angèle', 'Auto engine', 'Actif');
INSERT INTO public.client (id, fixe, mail, nom, notes, portable, prenom, societe, statut) VALUES (8, '0276543457', 'antoine.martinez@phone-century.com', 'Martinez', 'Magasin de téléphone', '07789654', 'Antoine', 'phone century', 'Actif');

INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (45, '2022-06-16 02:00:00.000000', '20', 'La cliente à payée la commande à la réception', 20000, 'Payée', 9, 21);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (46, '2022-06-14 02:00:00.000000', '30', 'Crm pour les petits papiers', 21000, 'Impayée', 7, 2);
INSERT INTO public.commande (id, date_commande, duree, notes, prix, statut, client_id, produit_id) VALUES (47, '2022-06-07 02:00:00.000000', '5', '', 1500, 'Payée', 8, 1);
