-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 15. Jan 2020 um 14:33
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
-- Datenbank: `felldgoodltd`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `client`
--

CREATE TABLE `client` (
  `clientID` int(11) NOT NULL,
  `clientname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientaddress` varchar(60) COLLATE utf8_german2_ci NOT NULL,
  `clientemail` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientphone` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `shippingarea` varchar(10) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `client`
--

INSERT INTO `client` (`clientID`, `clientname`, `clientaddress`, `clientemail`, `clientphone`, `shippingarea`) VALUES
(1, 'Mister Hemp', 'Landstraße 72, 4020 Linz', 'hemp@green.com', '+4366412349330', 'NORD'),
(2, 'Super Trooper', 'Weitwegweg 999, 9020 Klagenfurt', 'trooper@deathstar.com', '+43680596404', 'OST'),
(3, 'Daniela Tunichtgut', 'Josefgasse 11, 7020 Loipersbach', 'unfug@bledsinn.com', '+436763049404', 'SÜD'),
(4, 'John Wick', 'Brennendes Höllentor 666, 6020 Innsbruck', 'john@wick.com', '+436649787775', 'WEST'),
(5, 'Matthias Riedl', 'Kettenbrückengasse 23, 1050 Wien', 'matthias.riedl@expleo.com', '+43664523449', 'NORD'),
(6, 'Wolfgang Figl', 'Musterweg 34, 1040 Wien', 'wolfgang.figl@expleo.com', '+436761234098', 'OST'),
(7, 'Markus Gehbauer', 'Keltengasse 4, 1230 Wien', 'mgehbauer@gmail.com', '+436992348906', 'SÜD'),
(8, 'Marion Herms', 'Gertergasse 87, 8020 Graz', 'herms@gmail.com', '+4368012367593', 'WEST'),
(9, 'Daniela Suchny', 'Reiterweg 4, 1080 Wien', 'danisuchny@gmail.com', '+436804356777', 'NORD'),
(10, 'Peter Suchny', 'Reiterweg 4, 1080 Wien', 'petersuchny@gmail.com', '+436804829437', 'OST'),
(11, 'Edmund Sackbauer', 'Hasengasse 38, 1010 Wien', 'mundl@gmail.com', '+4367687345690', 'SÜD'),
(12, 'Otto Bauer', 'Josef-Messner-Straße 12, 5020 Salzburg', 'ottob@gmx.at', '+43664130303', 'WEST'),
(13, 'Hermine Granger', 'Muggelweg 45, 4020 Linz', 'hermineg@wizzard.wz', '+436993567544', 'NORD'),
(14, 'Dobby Elf', 'Winkelgasse 1, 6850', 'dobbyelf@gmail.com', '+436808887754', 'OST'),
(15, 'Martina Haselnuss', 'Nussgasse 42, 3390 Melk', 'nusserl@spongebob.com', '+4366022233444', 'SÜD'),
(16, 'Sigmund Ferdanz', 'Stummergasse 6, 1220 Wien', 'sigi@hotmail.com', '+43664122233', 'WEST');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dispatcher`
--

CREATE TABLE `dispatcher` (
  `dispatcherID` int(11) NOT NULL,
  `dispname` varchar(20) COLLATE utf8_german2_ci NOT NULL,
  `orderID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `dispatcher`
--

INSERT INTO `dispatcher` (`dispatcherID`, `dispname`, `orderID`) VALUES
(1, 'DHL', 1),
(2, 'GLS', 2),
(3, 'UPS', 3),
(4, 'Post', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dispatcherclient`
--

CREATE TABLE `dispatcherclient` (
  `dispatcherID_FK` int(11) NOT NULL,
  `clientID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `dispatcherclient`
--

INSERT INTO `dispatcherclient` (`dispatcherID_FK`, `clientID_FK`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dispatcherorder`
--

CREATE TABLE `dispatcherorder` (
  `dispatcherID_FK` int(11) NOT NULL,
  `orderID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `dispatcherorder`
--

INSERT INTO `dispatcherorder` (`dispatcherID_FK`, `orderID_FK`) VALUES
(1, 1),
(2, 2);

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

--
-- Daten für Tabelle `orderamount`
--

INSERT INTO `orderamount` (`amountID`, `amount`, `orderlistID_FK`) VALUES
(1, 10, 1),
(2, 5, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orderlist`
--

CREATE TABLE `orderlist` (
  `orderlistID` int(11) NOT NULL,
  `orderID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `orderlist`
--

INSERT INTO `orderlist` (`orderlistID`, `orderID_FK`) VALUES
(1, 1),
(2, 2);

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

--
-- Daten für Tabelle `ordertab`
--

INSERT INTO `ordertab` (`orderID`, `total`, `date`, `clientID`, `shippingteamID_FK`) VALUES
(1, 600, '2020-01-30', 1, 1),
(2, 799, '2020-02-12', 2, 2),
(3, 600, '2020-01-30', 3, 3),
(4, 799, '2020-02-12', 4, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `productname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
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

INSERT INTO `product` (`productID`, `productname`, `status`, `amount`, `bulkprice`, `category`, `availabilty`, `singleprice`, `description`, `location`, `manuID`) VALUES
(1, 'Mega Tee', 1, 8, 9, 'Tea', 1, 2, 'Green Tea from China', 'Zone 1', 1),
(2, 'Super Duper Kaffee', 0, 10, 20, 'Coffee', 0, 3, 'Best coffee ever!', 'Zone 2', 2),
(3, 'Coke Zero', 1, 8, 6, 'Soft Drinks', 1, 1, 'Sparkling Coke', 'Zone 3', 3),
(4, 'Red Bull', 0, 100, 900, 'Energy Drinks', 1, 10, 'Fliiiieg Baby', 'Zone 4', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `productorderlist`
--

CREATE TABLE `productorderlist` (
  `productID_FK` int(11) NOT NULL,
  `orderlistID_FK` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `productorderlist`
--

INSERT INTO `productorderlist` (`productID_FK`, `orderlistID_FK`) VALUES
(1, 1),
(2, 2),
(1, 1),
(2, 2);

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

--
-- Daten für Tabelle `shippinteamproduct`
--

INSERT INTO `shippinteamproduct` (`shippingteamID_FK`, `productID_FK`) VALUES
(1, 1),
(2, 2);

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
  ADD UNIQUE KEY `shippingteamID_FK` (`shippingteamID_FK`,`productID_FK`) USING BTREE,
  ADD UNIQUE KEY `productID_FK` (`productID_FK`) USING BTREE;

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
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

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
  MODIFY `amountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `orderlistID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
