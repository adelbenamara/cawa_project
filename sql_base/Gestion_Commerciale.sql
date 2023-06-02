 
CREATE DATABASE `gestion_commerciale`;
USE `gestion_commerciale`;

-- Table structure for table `articles`
CREATE TABLE `articles` (
  `ref_article` varchar(30) NOT NULL,
  `designation` varchar(100) NOT NULL,
  `prix` decimal(10,2) NOT NULL,
  `quantite_stock` int(11) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ref_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `articles`
INSERT INTO `articles` (`ref_article`, `designation`, `prix`, `quantite_stock`)
VALUES
('CT10032', 'Perceuse CROWN 1050W', 9800.00, 18),
('CT11020', 'Paumelleuse 6mm 430W CROWN', 6950.00, 18),
('HN13022', 'Paumelleuse 500W HONESTPRO', 7200.00, 13),
('PS13046', ' Poste à souder Mecafer ', 34230.00, 2);

-- Table structure for table `clients`
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `clients`
INSERT INTO `clients` (`id`, `nom`, `telephone`, `email`)
VALUES
(1, 'Benamara Adel', '0777556677', 'adelbenamara@univ-boumerdes.dz');

-- Table structure for table `factures`
CREATE TABLE `factures` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_facture` date NOT NULL,
  `mode_paiement` varchar(50) NOT NULL,
  `id_client` int(11) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  CONSTRAINT `factures_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `factures`
INSERT INTO `factures` (`id`, `date_facture`, `mode_paiement`, `id_client`)
VALUES
(2, '2023-05-27', 'paysira', 1);

-- Table structure for table `lignes_facture`
CREATE TABLE `lignes_facture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_facture` int(11) NOT NULL,
  `ref_article` varchar(30) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_facture` (`id_facture`),
  KEY `ref_article` (`ref_article`),
  CONSTRAINT `lignes_facture_ibfk_1` FOREIGN KEY (`id_facture`) REFERENCES `factures` (`id`),
  CONSTRAINT `lignes_facture_ibfk_2` FOREIGN KEY (`ref_article`) REFERENCES `articles` (`ref_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table `lignes_facture`
INSERT INTO `lignes_facture` (`id`, `id_facture`, `ref_article`, `quantite`)
VALUES
(1, 2, 'CT10032', 2),
(2, 2, 'CT11020', 2),
(3, 2, 'HN13022', 1);
