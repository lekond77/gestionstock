

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";




CREATE DATABASE gstocks;

use gstocks;

--
-- Base de données : `gstocks`
--

-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- Structure de la table `ARTICLES`
--

CREATE TABLE `ARTICLES` (
  `idArticle` int(11) NOT NULL,
  `refArticle` varchar(30) NOT NULL,
  `nomArticle` varchar(30) NOT NULL,
  `dateCreation` date NOT NULL,
  `prixArticle` int(11) NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ARTICLES`
--

INSERT INTO `ARTICLES` (`idArticle`, `refArticle`, `nomArticle`, `dateCreation`, `prixArticle`, `description`) VALUES
(35, 'AR001', 'Ordinateur ', '2023-03-16', 280, 'Premier article'),
(36, 'AR002', 'Souris', '2023-03-16', 20, 'ajouter souris'),
(39, 'AR003', 'Clavier', '2023-03-16', 40, 'Clavier'),
(40, 'AR004', 'ecran', '2023-03-23', 60, 'Ecran'),
(41, 'AR30', 'toto', '2023-03-23', 20, 'toto'),
(42, 'ZE12344', 'ZOZU', '2023-03-23', 20, 'toutoutut');

-- --------------------------------------------------------

--
-- Structure de la table `ENTREPOT`
--

CREATE TABLE `ENTREPOT` (
  `idEntrepot` int(11) NOT NULL,
  `codeEntrepot` varchar(20) NOT NULL,
  `libelleEntrepot` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ENTREPOT`
--

INSERT INTO `ENTREPOT` (`idEntrepot`, `codeEntrepot`, `libelleEntrepot`) VALUES
(1, 'E12', 'E12'),
(2, 'E13', 'E13'),
(3, 'E14', 'E14');

-- --------------------------------------------------------

--
-- Structure de la table `estGerer`
--

CREATE TABLE `estGerer` (
  `idGerance` int(11) NOT NULL,
  `utilisateur` int(11) NOT NULL,
  `article` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `estProduit`
--

CREATE TABLE `estProduit` (
  `idProduction` int(11) NOT NULL,
  `producteur` varchar(30) NOT NULL,
  `article` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `ETAGERE`
--

CREATE TABLE `ETAGERE` (
  `idEtagere` int(11) NOT NULL,
  `codeEtagere` varchar(20) NOT NULL,
  `numeroEtage` int(11) NOT NULL,
  `zone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ETAGERE`
--

INSERT INTO `ETAGERE` (`idEtagere`, `codeEtagere`, `numeroEtage`, `zone`) VALUES
(51, 'ZA1', 1, 'ZA'),
(52, 'ZA2', 2, 'ZA'),
(53, 'ZA3', 3, 'ZA'),
(54, 'ZB1', 1, 'ZB'),
(55, 'ZB2', 2, 'ZB'),
(56, 'ZB3', 3, 'ZB'),
(57, 'ZC1', 1, 'ZC'),
(58, 'ZC2', 2, 'ZC'),
(59, 'ZC3', 3, 'ZC'),
(60, 'ZD1', 1, 'ZD'),
(61, 'ZD2', 2, 'ZD'),
(62, 'ZD3', 3, 'ZD'),
(63, 'ZE1', 1, 'ZE'),
(64, 'ZE2', 2, 'ZE'),
(65, 'ZE3', 3, 'ZE'),
(66, 'ZF1', 1, 'ZF'),
(67, 'ZF2', 2, 'ZF'),
(68, 'ZF3', 3, 'ZF'),
(69, 'ZG1', 1, 'ZG'),
(70, 'ZG2', 2, 'ZG'),
(71, 'ZG3', 3, 'ZG'),
(72, 'ZH1', 1, 'ZH'),
(73, 'ZH2', 2, 'ZH'),
(74, 'ZH3', 3, 'ZH'),
(75, 'ZI1', 1, 'ZI'),
(76, 'ZI2', 2, 'ZI'),
(77, 'ZI3', 3, 'ZI'),
(78, 'ZD4', 4, 'ZD'),
(79, 'ZD5', 5, 'ZD'),
(80, 'ZF4', 4, 'ZF'),
(81, 'ZF5', 5, 'ZF'),
(82, 'ZF6', 6, 'ZF'),
(83, 'ZA4', 4, 'ZA'),
(84, 'ZB4', 4, 'ZB'),
(85, 'ZB5', 5, 'ZB'),
(86, 'ZB6', 6, 'ZB'),
(87, 'ZC4', 4, 'ZC'),
(88, 'ZC5', 5, 'ZC'),
(89, 'ZC6', 6, 'ZC'),
(90, 'ZC7', 7, 'ZC');

-- --------------------------------------------------------

--
-- Structure de la table `GESTIONNAIRE`
--

CREATE TABLE `GESTIONNAIRE` (
  `idGestionnaire` int(11) NOT NULL,
  `numGestionnaire` int(11) NOT NULL,
  `numService` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `PRODUCTEUR`
--

CREATE TABLE `PRODUCTEUR` (
  `idProducteur` int(11) NOT NULL,
  `numProducteur` varchar(20) NOT NULL,
  `pseudoProducteur` varchar(20) NOT NULL,
  `emailProducteur` varchar(30) NOT NULL,
  `adressProducteur` varchar(100) NOT NULL,
  `telephone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `statut`
--

CREATE TABLE `statut` (
  `idStatut` int(11) NOT NULL,
  `nomStatut` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `statut`
--

INSERT INTO `statut` (`idStatut`, `nomStatut`) VALUES
(2, 'chef de rayon'),
(3, 'conseiller de vente'),
(1, 'gestionnaire magasin'),
(4, 'responsable logistique'),
(5, 'responsable maintenance');

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `idStock` int(11) NOT NULL,
  `article` varchar(30) NOT NULL,
  `entrepot` varchar(20) NOT NULL,
  `zone` varchar(20) NOT NULL,
  `etagere` varchar(20) NOT NULL,
  `quantiteStock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `stock`
--

INSERT INTO `stock` (`idStock`, `article`, `entrepot`, `zone`, `etagere`, `quantiteStock`) VALUES
(16, 'AR001', 'E13', 'ZE', 'ZE2', 29),
(17, 'AR002', 'E14', 'ZH', 'ZH2', 320),
(18, 'AR003', 'E12', 'ZB', 'ZB3', 40),
(19, 'AR004', 'E12', 'ZB', 'ZB5', 70),
(20, 'AR30', 'E12', 'ZB', 'ZB3', 12),
(21, 'ZE12344', 'E13', 'ZE', 'ZE2', 30);

-- --------------------------------------------------------

--
-- Structure de la table `UTILISATEUR`
--

CREATE TABLE `UTILISATEUR` (
  `idUtilisateur` int(11) NOT NULL,
  `matricule` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `dateNaissance` date NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `motDePasse` varchar(50) NOT NULL DEFAULT 'java',
  `telephone` int(11) NOT NULL,
  `statut` varchar(100) DEFAULT NULL,
   CONSTRAINT check_statut CHECK (statut IN ('Gestionnaire magasin', 'Chef de rayon', 'Conseiller de vente', 'Responsable logistique', 'Responsable maintenance')),
  `cle` int(11) NOT NULL DEFAULT '0',
  `privilege` tinyint(1) NOT NULL DEFAULT '0',
  `estGestionnaire` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Structure de la table `AGENT`
--

CREATE TABLE `AGENT` (
  `idAgent` int(11) NOT NULL,
  `numAgent` int(11) NOT NULL,
  `specialite` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




--
-- Index pour la table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  ADD PRIMARY KEY (`idUtilisateur`),
  ADD UNIQUE KEY `unique1_utilisateur` (`matricule`),
  ADD UNIQUE KEY `unique2_utilisateur` (`email`),
  ADD UNIQUE KEY `unique3_utilisateur` (`telephone`);


-- --------------------------------------------------------

--
-- Structure de la table `ZONE`
--

CREATE TABLE `ZONE` (
  `idZone` int(11) NOT NULL,
  `codeZone` varchar(20) NOT NULL,
  `entrepot` varchar(20) NOT NULL,
  `desciptionZone` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ZONE`
--

INSERT INTO `ZONE` (`idZone`, `codeZone`, `entrepot`, `desciptionZone`) VALUES
(1, 'ZA', 'E12', 'zone numéro A - E12'),
(2, 'ZB', 'E12', 'Zone numéro B _E12'),
(3, 'ZC', 'E12', 'Zone numéro B _E12'),
(4, 'ZD', 'E13', 'Zone numéro D _E123'),
(5, 'ZE', 'E13', 'Zone numéro E _E13'),
(6, 'ZF', 'E13', 'Zone numéro F _E13'),
(7, 'ZG', 'E14', 'Zone numéro G - E14'),
(8, 'ZH', 'E14', 'Zone numéro H - E14'),
(9, 'ZI', 'E14', 'Zone numéro H - E14');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `AGENT`
--
ALTER TABLE `AGENT`
  ADD PRIMARY KEY (`idAgent`),
  ADD UNIQUE KEY `unique1_agent` (`numAgent`);

--
-- Index pour la table `ARTICLES`
--
ALTER TABLE `ARTICLES`
  ADD PRIMARY KEY (`idArticle`),
  ADD UNIQUE KEY `unique_article` (`refArticle`);

--
-- Index pour la table `ENTREPOT`
--
ALTER TABLE `ENTREPOT`
  ADD PRIMARY KEY (`idEntrepot`),
  ADD UNIQUE KEY `unique_entrepot` (`codeEntrepot`);

--
-- Index pour la table `estGerer`
--
ALTER TABLE `estGerer`
  ADD PRIMARY KEY (`idGerance`),
  ADD KEY `fk_gerance_utilisateur` (`utilisateur`),
  ADD KEY `fk_gerance_article` (`article`);

--
-- Index pour la table `estProduit`
--
ALTER TABLE `estProduit`
  ADD PRIMARY KEY (`idProduction`),
  ADD KEY `fk_production_personnel` (`producteur`),
  ADD KEY `fk_production_article` (`article`);

--
-- Index pour la table `ETAGERE`
--
ALTER TABLE `ETAGERE`
  ADD PRIMARY KEY (`idEtagere`),
  ADD UNIQUE KEY `codeEtagere` (`codeEtagere`),
  ADD KEY `fk_etagere` (`zone`);

--
-- Index pour la table `GESTIONNAIRE`
--
ALTER TABLE `GESTIONNAIRE`
  ADD PRIMARY KEY (`idGestionnaire`),
  ADD UNIQUE KEY `unique1_gestionnaire` (`numGestionnaire`);

--
-- Index pour la table `PRODUCTEUR`
--
ALTER TABLE `PRODUCTEUR`
  ADD PRIMARY KEY (`idProducteur`),
  ADD UNIQUE KEY `unique1_producteur` (`numProducteur`),
  ADD UNIQUE KEY `unique2_producteur` (`emailProducteur`),
  ADD UNIQUE KEY `unique3_producteur` (`telephone`);

--
-- Index pour la table `statut`
--
ALTER TABLE `statut`
  ADD PRIMARY KEY (`idStatut`),
  ADD UNIQUE KEY `unique1_statut` (`nomStatut`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`idStock`),
  ADD UNIQUE KEY `unique_stock` (`article`,`entrepot`),
  ADD KEY `fk_stock_entrepot` (`entrepot`),
  ADD KEY `fk_stock_zone` (`zone`),
  ADD KEY `fk_stock_etagere` (`etagere`);



--
-- Index pour la table `ZONE`
--
ALTER TABLE `ZONE`
  ADD PRIMARY KEY (`idZone`),
  ADD UNIQUE KEY `unique_zone` (`codeZone`,`entrepot`),
  ADD KEY `fk_zone` (`entrepot`),
  ADD KEY `id_zone_code_entrepot` (`codeZone`,`entrepot`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `AGENT`
--
ALTER TABLE `AGENT`
  MODIFY `idAgent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `ARTICLES`
--
ALTER TABLE `ARTICLES`
  MODIFY `idArticle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `ENTREPOT`
--
ALTER TABLE `ENTREPOT`
  MODIFY `idEntrepot` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `estGerer`
--
ALTER TABLE `estGerer`
  MODIFY `idGerance` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `estProduit`
--
ALTER TABLE `estProduit`
  MODIFY `idProduction` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ETAGERE`
--
ALTER TABLE `ETAGERE`
  MODIFY `idEtagere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT pour la table `GESTIONNAIRE`
--
ALTER TABLE `GESTIONNAIRE`
  MODIFY `idGestionnaire` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `PRODUCTEUR`
--
ALTER TABLE `PRODUCTEUR`
  MODIFY `idProducteur` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `statut`
--
ALTER TABLE `statut`
  MODIFY `idStatut` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `idStock` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `UTILISATEUR`
--
ALTER TABLE `UTILISATEUR`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `ZONE`
--
ALTER TABLE `ZONE`
  MODIFY `idZone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `AGENT`
--
ALTER TABLE `AGENT`
  ADD CONSTRAINT `fk_agent` FOREIGN KEY (`numAgent`) REFERENCES `UTILISATEUR` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `estGerer`
--
ALTER TABLE `estGerer`
  ADD CONSTRAINT `fk_gerance_article` FOREIGN KEY (`article`) REFERENCES `ARTICLES` (`refArticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_gerance_utilisateur` FOREIGN KEY (`utilisateur`) REFERENCES `UTILISATEUR` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `estProduit`
--
ALTER TABLE `estProduit`
  ADD CONSTRAINT `fk_production_article` FOREIGN KEY (`article`) REFERENCES `ARTICLES` (`refArticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_production_personnel` FOREIGN KEY (`producteur`) REFERENCES `PRODUCTEUR` (`numProducteur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ETAGERE`
--
ALTER TABLE `ETAGERE`
  ADD CONSTRAINT `fk_etagere` FOREIGN KEY (`zone`) REFERENCES `ZONE` (`codeZone`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `GESTIONNAIRE`
--
ALTER TABLE `GESTIONNAIRE`
  ADD CONSTRAINT `fk_gestionnaire` FOREIGN KEY (`numGestionnaire`) REFERENCES `UTILISATEUR` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `fk_stock_article` FOREIGN KEY (`article`) REFERENCES `ARTICLES` (`refArticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_stock_entrepot` FOREIGN KEY (`entrepot`) REFERENCES `ENTREPOT` (`codeEntrepot`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_stock_etagere` FOREIGN KEY (`etagere`) REFERENCES `ETAGERE` (`codeEtagere`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_stock_zone` FOREIGN KEY (`zone`) REFERENCES `ZONE` (`codeZone`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `ZONE`
--
ALTER TABLE `ZONE`
  ADD CONSTRAINT `fk_zone` FOREIGN KEY (`entrepot`) REFERENCES `ENTREPOT` (`codeEntrepot`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;



INSERT INTO utilisateur (matricule, email, nom, prenom, dateNaissance, adresse, motDePasse, telephone, statut, cle, privilege) VALUES 
(122, 'agent19@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349871', 'CHEF DE RAYON', '0', '0'),
(103, 'agent2@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349872', 'CHEF DE RAYON', '0', '0'),
(104, 'agent3@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349873', 'CHEF DE RAYON', '0', '0'),
(105, 'agent4@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349874', 'CHEF DE RAYON', '0', '0'),
(106, 'agent5@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349875', 'CHEF DE RAYON', '0', '0'),
(107, 'agent6@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349876', 'CHEF DE RAYON', '0', '0'),
(108, 'agent@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349877', 'CHEF DE RAYON', '0', '0'),
(109, 'agent9@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349878', 'CHEF DE RAYON', '0', '0'),
(110, 'agent10@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '12349879', 'CHEF DE RAYON', '0', '0'),
(111, 'agent11@gmail.com', 'Agent1', 'Agent1', '2023-03-17', '100 Avenue de maison', 'java', '123498710', 'CHEF DE RAYON', '0', '0');

INSERT INTO utilisateur (matricule, email, nom, prenom, dateNaissance, adresse, motDePasse, telephone, statut, cle, privilege) VALUES 
(100, 'admin', 'Admin', 'Admin', '1997-11-09', '100 Avenue de maison', 'java', '98272652', 'CHEF DE RAYON', '1', '1');


INSERT INTO agent (numAgent, specialite) VALUES
 (122, 'RAYON'),
 (103, 'RAYON'),
 (104, 'RAYON'),
 (105, 'RAYON'),
 (106, 'RAYON'),
 (107, 'RAYON'),
 (108, 'RAYON'),
 (109, 'RAYON'),
 (110, 'RAYON'),
 (111, 'RAYON');
