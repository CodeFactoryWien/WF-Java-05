-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 15. Jan 2020 um 10:39
-- Server-Version: 10.1.38-MariaDB
-- PHP-Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `feelgoodltd`
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
-- Tabellenstruktur für Tabelle `dispatcherclient`
--

CREATE TABLE `dispatcherclient` (
  `dispatcherID_FK` int(11) NOT NULL,
  `clientID_FK` int(11) NOT NULL,
  `clientaddress` varchar(40) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dispatcherorder`
--

CREATE TABLE `dispatcherorder` (
  `dispatcherID_FK` int(11) NOT NULL,
  `orderID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `manufacturer`
--

CREATE TABLE `manufacturer` (
  `manuID` int(11) NOT NULL,
  `manuname` varchar(20) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `manufacturer`
--

INSERT INTO `manufacturer` (`manuID`, `manuname`) VALUES
(1, 'Sonnentor'),
(2, 'Relax Drinks'),
(3, 'Natural'),
(4, 'Muraurer');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orderamount`
--

CREATE TABLE `orderamount` (
  `amountID` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `orderlistID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orderlist`
--

CREATE TABLE `orderlist` (
  `orderlistID` int(11) NOT NULL,
  `orderID_FK` int(11) NOT NULL
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
  `shippingteamID_FK` int(11) NOT NULL
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

--
-- Daten für Tabelle `product`
--

INSERT INTO `product` (`productID`, `status`, `amount`, `bulkprice`, `category`, `availabilty`, `singleprice`, `description`, `location`, `manuID`) VALUES
(2, 1, 10, 9, 'Tea', 1, 1, 'Green Tea from China', 'Zone 1', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `productorderlist`
--

CREATE TABLE `productorderlist` (
  `productID_FK` int(11) NOT NULL,
  `orderlistID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `shippingteam`
--

CREATE TABLE `shippingteam` (
  `shippingteamID` int(11) NOT NULL,
  `teamname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `shippingarea` varchar(10) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `shippingteam`
--

INSERT INTO `shippingteam` (`shippingteamID`, `teamname`, `shippingarea`) VALUES
(1, 'A', 'NORD'),
(2, 'B', 'OST'),
(3, 'C', 'SÜD'),
(4, 'D', 'WEST');

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
  `role` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `shippingteamID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `staffmember`
--

INSERT INTO `staffmember` (`staffID`, `staffname`, `staffphone`, `staffemail`, `staffaddress`, `staffsvnumber`, `role`, `shippingteamID`) VALUES
(1, 'Test Dummie', '+01707504', 'ÍT@feeldgood.com', 'Poldergasse 5, 8020 Graz', 1234442342, 'IT', 1),
(2, 'Max Mustermann', '+018493303', 'max@feeldgood.com', 'Musterplatz 45, 1100 Wien', 1876123456, 'Accounting', 2),
(3, 'Mister Muster', '+019987665', 'Mister@feeldgood.com', 'Hohlplatz 34, 1200 Wien', 2147483647, 'Sales', 3),
(4, 'Olaf Dodo', '+01845642', 'olaf@olaf.de', 'Musterplatz 122, 5040 Salzburg', 6723333, 'Sales', 4);

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
-- Indizes für die Tabelle `dispatcherclient`
--
ALTER TABLE `dispatcherclient`
  ADD UNIQUE KEY `dispatcherID_FK` (`dispatcherID_FK`,`clientID_FK`),
  ADD KEY `clientID_FK` (`clientID_FK`);

--
-- Indizes für die Tabelle `dispatcherorder`
--
ALTER TABLE `dispatcherorder`
  ADD UNIQUE KEY `dispatcherID_FK` (`dispatcherID_FK`,`orderID_FK`),
  ADD KEY `orderID_FK` (`orderID_FK`);

--
-- Indizes für die Tabelle `manufacturer`
--
ALTER TABLE `manufacturer`
  ADD PRIMARY KEY (`manuID`);

--
-- Indizes für die Tabelle `orderamount`
--
ALTER TABLE `orderamount`
  ADD PRIMARY KEY (`amountID`),
  ADD KEY `orderlistID_FK` (`orderlistID_FK`);

--
-- Indizes für die Tabelle `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`orderlistID`),
  ADD KEY `orderID_FK` (`orderID_FK`);

--
-- Indizes für die Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `clientID` (`clientID`),
  ADD KEY `shippingteamID_FK` (`shippingteamID_FK`);

--
-- Indizes für die Tabelle `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`),
  ADD KEY `manuID` (`manuID`);

--
-- Indizes für die Tabelle `productorderlist`
--
ALTER TABLE `productorderlist`
  ADD KEY `orderlistID_FK` (`orderlistID_FK`),
  ADD KEY `productID_FK` (`productID_FK`);

--
-- Indizes für die Tabelle `shippingteam`
--
ALTER TABLE `shippingteam`
  ADD PRIMARY KEY (`shippingteamID`);

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
  ADD PRIMARY KEY (`staffID`),
  ADD KEY `shippingteamID` (`shippingteamID`);

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
  MODIFY `manuID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `orderamount`
--
ALTER TABLE `orderamount`
  MODIFY `amountID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `orderlistID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `shippingteam`
--
ALTER TABLE `shippingteam`
  MODIFY `shippingteamID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `staffmember`
--
ALTER TABLE `staffmember`
  MODIFY `staffID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `dispatcher`
--
ALTER TABLE `dispatcher`
  ADD CONSTRAINT `dispatcher_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `ordertab` (`orderID`);

--
-- Constraints der Tabelle `dispatcherclient`
--
ALTER TABLE `dispatcherclient`
  ADD CONSTRAINT `dispatcherclient_ibfk_1` FOREIGN KEY (`clientID_FK`) REFERENCES `client` (`clientID`),
  ADD CONSTRAINT `dispatcherclient_ibfk_2` FOREIGN KEY (`dispatcherID_FK`) REFERENCES `dispatcher` (`dispatcherID`);

--
-- Constraints der Tabelle `dispatcherorder`
--
ALTER TABLE `dispatcherorder`
  ADD CONSTRAINT `dispatcherorder_ibfk_1` FOREIGN KEY (`dispatcherID_FK`) REFERENCES `dispatcher` (`dispatcherID`),
  ADD CONSTRAINT `dispatcherorder_ibfk_2` FOREIGN KEY (`orderID_FK`) REFERENCES `ordertab` (`orderID`);

--
-- Constraints der Tabelle `orderamount`
--
ALTER TABLE `orderamount`
  ADD CONSTRAINT `orderamount_ibfk_1` FOREIGN KEY (`orderlistID_FK`) REFERENCES `orderlist` (`orderlistID`);

--
-- Constraints der Tabelle `orderlist`
--
ALTER TABLE `orderlist`
  ADD CONSTRAINT `orderlist_ibfk_1` FOREIGN KEY (`orderID_FK`) REFERENCES `ordertab` (`orderID`);

--
-- Constraints der Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  ADD CONSTRAINT `ordertab_ibfk_1` FOREIGN KEY (`clientID`) REFERENCES `client` (`clientID`),
  ADD CONSTRAINT `ordertab_ibfk_2` FOREIGN KEY (`shippingteamID_FK`) REFERENCES `shippingteam` (`shippingteamID`);

--
-- Constraints der Tabelle `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`manuID`) REFERENCES `manufacturer` (`manuID`);

--
-- Constraints der Tabelle `productorderlist`
--
ALTER TABLE `productorderlist`
  ADD CONSTRAINT `productorderlist_ibfk_1` FOREIGN KEY (`orderlistID_FK`) REFERENCES `orderlist` (`orderlistID`),
  ADD CONSTRAINT `productorderlist_ibfk_2` FOREIGN KEY (`productID_FK`) REFERENCES `product` (`productID`);

--
-- Constraints der Tabelle `shippinteamproduct`
--
ALTER TABLE `shippinteamproduct`
  ADD CONSTRAINT `shippinteamproduct_ibfk_1` FOREIGN KEY (`productID_FK`) REFERENCES `product` (`productID`),
  ADD CONSTRAINT `shippinteamproduct_ibfk_2` FOREIGN KEY (`shippingteamID_FK`) REFERENCES `shippingteam` (`shippingteamID`);

--
-- Constraints der Tabelle `staffmember`
--
ALTER TABLE `staffmember`
  ADD CONSTRAINT `staffmember_ibfk_1` FOREIGN KEY (`shippingteamID`) REFERENCES `shippingteam` (`shippingteamID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
