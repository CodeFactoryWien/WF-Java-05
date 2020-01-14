-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 14. Jan 2020 um 15:43
-- Server-Version: 10.4.10-MariaDB
-- PHP-Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `feeldgoodltd`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `client`
--

CREATE TABLE `client` (
  `clientID` int(11) NOT NULL,
  `clientname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientaddress` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientemail` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientphone` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `shippingarea` varchar(10) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `client`
--

INSERT INTO `client` (`clientID`, `clientname`, `clientaddress`, `clientemail`, `clientphone`, `shippingarea`) VALUES
(1, 'Mister', 'Hemp', 'hemp@green.com', '+4366412349330', 'NORD'),
(2, 'Super', 'Trooper', 'trooper@deathstar.com', '+43680596404', 'OST'),
(3, 'Daniela', 'Tunichtgut', 'unfug@bledsinn.com', '+436763049404', 'SÜD'),
(4, 'John', 'Wick', 'john@wick.com', '+436649787775', 'WEST');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dispatcher`
--

CREATE TABLE `dispatcher` (
  `dispatcherID` int(11) NOT NULL,
  `dispname` varchar(20) COLLATE utf8_german2_ci NOT NULL,
  `clientaddress` varchar(20) COLLATE utf8_german2_ci NOT NULL,
  `orderID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `manufacturer`
--

CREATE TABLE `manufacturer` (
  `manuID` int(11) NOT NULL,
  `manuname` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ordertab`
--

CREATE TABLE `ordertab` (
  `orderID` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `date` date NOT NULL,
  `clientID` int(11) NOT NULL,
  `shippingTeam` varchar(20) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `amount` int(11) NOT NULL,
  `bulkprice` int(11) NOT NULL,
  `category` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `availabilty` tinyint(1) NOT NULL,
  `singleprice` int(11) NOT NULL,
  `description` varchar(100) COLLATE utf8_german2_ci NOT NULL,
  `location` varchar(20) COLLATE utf8_german2_ci NOT NULL,
  `manuID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `productorder`
--

CREATE TABLE `productorder` (
  `productID_FK` int(11) NOT NULL,
  `orderID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shippingteam`
--

CREATE TABLE `shippingteam` (
  `shippingteamID` int(11) NOT NULL,
  `teamname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `shippingarea` varchar(10) COLLATE utf8_german2_ci NOT NULL,
  `staffID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shippinteamproduct`
--

CREATE TABLE `shippinteamproduct` (
  `shippingteamID_FK` int(11) NOT NULL,
  `productID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `staffmember`
--

CREATE TABLE `staffmember` (
  `staffID` int(11) NOT NULL,
  `staffname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `staffphone` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `staffemail` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `staffaddress` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `staffsvnumber` int(11) NOT NULL,
  `role` varchar(30) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientID`);

--
-- Indizes für die Tabelle `dispatcher`
--
ALTER TABLE `dispatcher`
  ADD PRIMARY KEY (`dispatcherID`),
  ADD KEY `orderID` (`orderID`) USING BTREE;

--
-- Indizes für die Tabelle `manufacturer`
--
ALTER TABLE `manufacturer`
  ADD PRIMARY KEY (`manuID`);

--
-- Indizes für die Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `clientID` (`clientID`);

--
-- Indizes für die Tabelle `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`),
  ADD KEY `manuID` (`manuID`);

--
-- Indizes für die Tabelle `productorder`
--
ALTER TABLE `productorder`
  ADD UNIQUE KEY `productID_FK` (`productID_FK`,`orderID_FK`),
  ADD KEY `orderID_FK` (`orderID_FK`);

--
-- Indizes für die Tabelle `shippingteam`
--
ALTER TABLE `shippingteam`
  ADD PRIMARY KEY (`shippingteamID`),
  ADD KEY `staffID` (`staffID`);

--
-- Indizes für die Tabelle `shippinteamproduct`
--
ALTER TABLE `shippinteamproduct`
  ADD PRIMARY KEY (`shippingteamID_FK`,`productID_FK`),
  ADD KEY `productID_FK` (`productID_FK`);

--
-- Indizes für die Tabelle `staffmember`
--
ALTER TABLE `staffmember`
  ADD PRIMARY KEY (`staffID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `client`
--
ALTER TABLE `client`
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `dispatcher`
--
ALTER TABLE `dispatcher`
  MODIFY `dispatcherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `manufacturer`
--
ALTER TABLE `manufacturer`
  MODIFY `manuID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `shippingteam`
--
ALTER TABLE `shippingteam`
  MODIFY `shippingteamID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `staffmember`
--
ALTER TABLE `staffmember`
  MODIFY `staffID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `dispatcher`
--
ALTER TABLE `dispatcher`
  ADD CONSTRAINT `dispatcher_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `ordertab` (`orderID`);

--
-- Constraints der Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  ADD CONSTRAINT `ordertab_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`);

--
-- Constraints der Tabelle `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`manuID`) REFERENCES `manufacturer` (`manuID`);

--
-- Constraints der Tabelle `productorder`
--
ALTER TABLE `productorder`
  ADD CONSTRAINT `productorder_ibfk_1` FOREIGN KEY (`orderID_FK`) REFERENCES `ordertab` (`orderID`),
  ADD CONSTRAINT `productorder_ibfk_2` FOREIGN KEY (`productID_FK`) REFERENCES `product` (`productID`);

--
-- Constraints der Tabelle `shippingteam`
--
ALTER TABLE `shippingteam`
  ADD CONSTRAINT `shippingteam_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staffmember` (`staffID`);

--
-- Constraints der Tabelle `shippinteamproduct`
--
ALTER TABLE `shippinteamproduct`
  ADD CONSTRAINT `shippinteamproduct_ibfk_1` FOREIGN KEY (`productID_FK`) REFERENCES `product` (`productID`),
  ADD CONSTRAINT `shippinteamproduct_ibfk_2` FOREIGN KEY (`shippingteamID_FK`) REFERENCES `shippingteam` (`shippingteamID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
