-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 23. Jan 2020 um 08:52
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
-- Datenbank: `feelgoodltd`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `client`
--

CREATE TABLE `client` (
  `clientID` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientaddress` varchar(60) COLLATE utf8_german2_ci NOT NULL,
  `clientemail` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `clientphone` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `shippingarea` varchar(10) COLLATE utf8_german2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `client`
--

INSERT INTO `client` (`clientID`, `username`, `password`, `clientname`, `clientaddress`, `clientemail`, `clientphone`, `shippingarea`) VALUES
(1, 'user1', 'password1', 'Mister Hemp', 'Landstraße 72, 4020 Linz', 'hemp@green.com', '+4366412349330', 'NORD'),
(2, 'user2', 'password2', 'Super Trooper', 'Weitwegweg 999, 1060 Wien', 'trooper@deathstar.com', '+43680596404', 'OST'),
(3, 'user3', 'password3', 'user3', 'password3, Daniela Tunichtgut', 'Josefgasse 11', '9020 Klagenfurt', 'WEST'),
(4, 'user4', 'password4', 'John Wick', 'Brennendes Höllentor 666, 6020 Innsbruck', 'john@wick.com', '+436649787775', 'WEST'),
(5, 'user5', 'password5', 'Matthias Riedl', 'Kettenbrückengasse 23, 4020 Linz', 'matthias.riedl@expleo.com', '+43664523449', 'NORD'),
(6, 'user6', 'password6', 'Wolfgang Figl', 'Musterweg 34, 1040 Wien', 'wolfgang.figl@expleo.com', '+436761234098', 'OST'),
(7, 'user7', 'password7', 'Markus Gehbauer', 'Keltengasse 4, 9020 Klagenfurt', 'mgehbauer@gmail.com', '+436992348906', 'SÜD'),
(8, 'user8', 'password8', 'Marion Herms', 'Gertergasse 87, 6020 Innsbruck', 'herms@gmail.com', '+4368012367593', 'WEST'),
(9, 'user9', 'password9', 'Daniela Suchny', 'Reiterweg 4, 4020 Linz', 'danisuchny@gmail.com', '+436804356777', 'NORD'),
(10, 'user10', 'password10', 'Peter Suchny', 'Reiterweg 4, 1080 Wien', 'petersuchny@gmail.com', '+436804829437', 'OST'),
(11, 'user11', 'password11', 'Edmund Sackbauer', 'Hasengasse 38, 9020 Klagenfurt', 'mundl@gmail.com', '+4367687345690', 'SÜD'),
(12, 'user12', 'password12', 'Otto Bauer', 'Josef-Messner-Straße 12, 6020 Innsbruck', 'ottob@gmx.at', '+43664130303', 'WEST'),
(13, 'user13', 'password13', 'Hermine Granger', 'Muggelweg 45, 4020 Linz', 'hermineg@wizzard.wz', '+436993567544', 'NORD'),
(14, 'user14', 'password14', 'Dobby Elf', 'Winkelgasse 1, 1210 Wien', 'dobbyelf@gmail.com', '+436808887754', 'OST'),
(15, 'user15', 'password15', 'Martina Haselnuss', 'Nussgasse 42, 9020 Klagenfurt', 'nusserl@spongebob.com', '+4366022233444', 'SÜD'),
(16, 'user16', 'password16', 'Sigmund Ferdanz', 'Stummergasse 6, 6020 Innsbruck', 'sigi@hotmail.com', '+43664122233', 'WEST'),
(17, 'user17', 'password17', 'Mex', 'Theflex', 'flexible@flexi.com', '+43664272233', 'NORD'),
(18, 'user18', 'password18', 'Kurt Klima', 'Mühlenweg 8, 1220 Wien', 'mm@mm.com', '+436645362800', 'OST');

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
(1, 5),
(1, 9),
(1, 13),
(2, 2),
(2, 6),
(2, 10),
(2, 14),
(3, 3),
(3, 7),
(3, 11),
(3, 15),
(4, 4),
(4, 8),
(4, 12),
(4, 16);

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
(1, 5),
(1, 9),
(1, 13),
(2, 2),
(2, 6),
(2, 10),
(2, 14),
(3, 3),
(3, 7),
(3, 11),
(3, 15),
(4, 4),
(4, 8),
(4, 12),
(4, 16);

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
(1, 'Happy Tea GmbH'),
(2, 'Strong Coffeine Ltd.'),
(3, 'Sugarland KG'),
(4, 'Speedy Ltd.');

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
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ordertab`
--

CREATE TABLE `ordertab` (
  `orderID` int(11) NOT NULL,
  `status` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `total` float NOT NULL,
  `date` date NOT NULL,
  `clientID` int(11) NOT NULL,
  `shippingteamID_FK` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `ordertab`
--

INSERT INTO `ordertab` (`orderID`, `status`, `total`, `date`, `clientID`, `shippingteamID_FK`) VALUES
(1, 'Order received', 679.99, '2020-01-05', 1, 1),
(2, 'Order received', 420.99, '2020-01-12', 2, 2),
(3, 'Order sent', 599.99, '2020-01-09', 3, 3),
(4, 'Order received', 99.79, '2020-01-12', 4, 4),
(5, 'Order received', 1129.89, '2019-12-18', 5, 1),
(6, 'Order sent', 120.99, '2019-12-04', 6, 2),
(7, 'Order completed', 458.99, '2020-01-06', 7, 3),
(8, 'Order sent', 89.99, '2020-01-03', 8, 4),
(9, 'Order completed', 129.79, '2019-12-19', 9, 1),
(10, 'Order received', 344.89, '2019-12-02', 10, 2),
(11, 'Order completed', 4499.99, '2020-01-05', 11, 3),
(12, 'Order completed', 19.99, '2020-01-03', 12, 4),
(13, 'Order sent', 999.99, '2019-12-12', 13, 1),
(14, 'Order sent', 333.89, '2019-12-09', 14, 2),
(15, 'Order completed', 639.89, '2020-01-21', 15, 3),
(16, 'Order sent', 70.89, '2020-01-09', 16, 4),
(17, 'Order received', 8, '2020-01-18', 4, 1),
(18, 'Order received', 11, '2020-01-19', 3, 1),
(19, 'Order received', 11, '2020-01-19', 3, 1),
(20, 'Order received', 17, '2020-01-13', 4, 1),
(21, '0', 11.4, '2020-01-21', 1, NULL),
(22, '0', 11.4, '2020-01-21', 1, NULL),
(23, 'Orders received', 8.25, '2020-01-22', 1, 1),
(24, 'Orders received', 14.3, '2020-01-22', 5, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `category` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `productname` varchar(30) COLLATE utf8_german2_ci NOT NULL,
  `description` varchar(200) COLLATE utf8_german2_ci NOT NULL,
  `instock` int(11) NOT NULL,
  `singleprice` float NOT NULL,
  `bulkprice` float NOT NULL,
  `availabilty` tinyint(1) NOT NULL,
  `location` varchar(20) COLLATE utf8_german2_ci NOT NULL,
  `manuID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `product`
--

INSERT INTO `product` (`productID`, `category`, `productname`, `description`, `instock`, `singleprice`, `bulkprice`, `availabilty`, `location`, `manuID`) VALUES
(0, 'Coffee', 'Caffe Wakka Wakka', 'Get loco with the real wakka wakka taste!', 89, 8.99, 8.39, 1, 'Zone 2', 2),
(1, 'Tea', 'Green Tea', 'Original Green Tea from China.', 900, 1.29, 1.09, 1, 'Zone 1', 1),
(2, 'Tea', 'Balck Tea', 'The taste is strong, bold, full-bodied flavor.', 0, 1.19, 0.99, 0, 'Zone 1', 1),
(3, 'Tea', 'Chai Tea', 'Chai Tea is essentially black tea paired with warm spices.', 1000, 1.49, 1.39, 1, 'Zone 1', 1),
(4, 'Tea', 'Herbal Tea', 'It\'s made from dried herbs, fruits, and flowers, which can create a wide range of delicate flavors.', 1000, 1.09, 0.97, 1, 'Zone 1', 1),
(5, 'Tea', 'Matcha Tea', 'Matcha is made by grinding up green tea leaves into a powder.', 57, 1.59, 1.22, 1, 'Zone 1', 1),
(6, 'Tea', 'Oolong Tea', 'Oolong, or wulong, tea is semi-oxidized and picked later in the season than green tea.', 1200, 1.69, 1.29, 1, 'Zone 1', 1),
(8, 'Coffee', 'Cafe au lait', 'Strong coffee made with scalded milk in a 1:1 ratio.', 679, 2.99, 2.49, 1, 'Zone 2', 2),
(9, 'Coffee', 'Cappuccino', 'Espresso made with hot milk and steamed milk foam.', 340, 2.79, 2.59, 1, 'Zone 2', 2),
(10, 'Coffee', 'Flat White', 'An espresso made similar to a latte only with textured milk.', 785, 1.99, 1.49, 1, 'Zone 2', 2),
(11, 'Coffee', 'Espressino', 'A drink made from espresso, steamed milk and cocoa powder.', 1090, 4.29, 3.89, 1, 'Zone 2', 2),
(12, 'Coffee', 'Macchiato', 'An espresso made with a small amount of foamed milk, similar to a cappuccino only stronger.', 0, 2.19, 1.79, 0, 'Zone 2', 2),
(13, 'Coffee', 'Vienna Coffee', 'Coffee or espresso made with whipped cream with milk added on some occasions.', 646, 5.79, 5.59, 1, 'Zone 2', 2),
(14, 'Soft Drinks', 'Almdudler', 'Lemonade fresh from the alpes.', 282, 1.09, 0.99, 1, 'Zone 3', 3),
(15, 'Soft Drinks', 'Fanta Orange', 'Sparkling leomande with orange flavour.', 2000, 0.99, 0.79, 1, 'Zone 3', 3),
(16, 'Soft Drinks', 'Fanta Lemon', 'Sparkling leomande with lemon flavour.', 1845, 0.99, 0.79, 1, 'Zone 3', 3),
(17, 'Soft Drinks', 'Fanta Mango', 'Sparkling leomande with mango flavour.', 209, 0.99, 0.79, 1, 'Zone 3', 3),
(18, 'Soft Drinks', 'Fanta Grapefruit', 'Sparkling leomande with grapefruit flavour.', 368, 0.99, 0.79, 1, 'Zone 3', 3),
(19, 'Soft Drinks', 'Sprite', 'Kill your thurst with sparkling limes.', 0, 0.89, 0.76, 0, 'Zone 3', 3),
(20, 'Energy Drinks', 'Red Bull', 'A mix of sugar, synthetic caffeine, taurine and several B vitamins, all of which are well-known for their energy-promoting qualities.', 630, 1.49, 1.29, 1, 'Zone 4', 4),
(21, 'Energy Drinks', 'Red Bull Sugarfree', 'A mix of sugar alternatives, synthetic caffeine, taurine and several B vitamins, all of which are well-known for their energy-promoting qualities.', 467, 1.49, 1.29, 1, 'Zone 4', 4),
(22, 'Energy Drinks', 'Monster Energy Green', 'Monster Energy drinks are a tool of the devil.', 776, 1.59, 1.39, 1, 'Zone 4', 4),
(23, 'Energy Drinks', 'Monster Juiced', 'Contains 16% fruit juice along with Monster Energy\'s standard energy blend.', 29, 1.49, 1.29, 1, 'Zone 4', 4),
(24, 'Energy Drinks', 'Flying Horse', 'If you wanna fly like a horse, drink 6 of a kind!', 66, 1.19, 1.09, 1, 'Zone 4', 4),
(25, 'Energy Drinks', 'Blaue Sau', 'The famous Blaue Sau drink is back! Get blue thungs with a blue pig!', 24, 0.99, 0.89, 1, 'Zone 4', 4),
(26, 'Energy Drinks', 'The Holy CF', 'Legendary drink from the Code Factory.', 0, 99.99, 99.98, 0, 'Zone 4', 4),
(27, 'Coffee', 'Steamed Black Coffee', 'Feel the taste of the Black Coffee!', 356, 2.69, 2.19, 1, 'Zone 2', 2),
(28, 'Coffee', 'Milano', 'Coffee made with whipped cream with milk added on some occasions.', 796, 4.79, 4.49, 1, 'Zone 2', 2),
(29, 'Energy Drinks', 'Hempy Energy', 'Get the natural energy kick from hemp!', 56, 1.49, 1.29, 1, 'Zone 4', 4),
(30, 'Energy Drinks', 'Melon Insanity', 'Fruity melon explosion in your mouth!', 9, 1.79, 1.59, 1, 'Zone 4', 4),
(31, 'Energy Drinks', 'Core Energizer', 'The name says all!', 6, 1.09, 0.99, 1, '-- Select Option --', 4),
(32, 'Tea', 'White Tea', 'Naturally sweet, delicate taste with a balance of floral and fruity undertones.', 190, 1.89, 1.69, 1, 'Zone 1', 1),
(33, 'Tea', 'Mate Tea', 'Mate tea comes from the South American yerba plant, and the leaves are blanched, dried, aged, and cut into loose leaf tea.', 106, 1.49, 1.39, 1, 'Zone 1', 1),
(34, 'Tea', 'Rooibos Tea', 'Naturally sweet, and can have warm, nutty, or vanilla tones.', 438, 1.39, 1.29, 1, 'Zone 1', 1),
(35, 'Soft Drinks', 'Coca Cola', 'Coca-Cola, or Coke, is a carbonated soft drink manufactured by The Coca-Cola Company.', 2340, 0.89, 0.79, 1, 'Zone 3', 3),
(36, 'Soft Drinks', 'Coca Cola Zero', 'All the great taste of Coca-Cola, without the calories. ', 2000, 0.89, 0.79, 1, 'Zone 3', 3),
(37, 'Soft Drinks', 'Coca Cola Light', 'All the great taste of Coca-Cola, without the calories. ', 2090, 0.89, 0.79, 1, 'Zone 3', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `productorderlist`
--

CREATE TABLE `productorderlist` (
  `orderlistID_FK` int(11) NOT NULL,
  `productID_FK` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `subtotal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_german2_ci;

--
-- Daten für Tabelle `productorderlist`
--

INSERT INTO `productorderlist` (`orderlistID_FK`, `productID_FK`, `amount`, `subtotal`) VALUES
(5, 0, 10, 83.9),
(1, 1, 5, 6.45),
(24, 1, 3, 3.87),
(2, 4, 67, 64.99),
(4, 5, 500, 610),
(23, 5, 2, 3.18),
(4, 6, 760, 980.4),
(23, 6, 3, 5.07),
(5, 9, 10, 25.9),
(2, 10, 123, 183.27),
(5, 12, 10, 17.9),
(1, 13, 10, 55.9),
(2, 15, 10, 7.9),
(1, 17, 35, 27.65),
(2, 18, 78, 61.62),
(3, 21, 10, 12.9),
(2, 23, 9, 13.41),
(3, 23, 5, 7.45),
(2, 28, 20, 89.8),
(3, 28, 54, 242.46),
(6, 29, 300, 387),
(24, 33, 7, 10.43),
(1, 35, 4, 3.56),
(3, 35, 100, 79),
(3, 36, 100, 79),
(3, 37, 100, 79);

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
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 8),
(2, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 13),
(3, 14),
(3, 15),
(3, 16),
(3, 17),
(3, 18),
(3, 19),
(4, 20),
(4, 21),
(4, 22),
(4, 23),
(4, 24),
(4, 25),
(4, 26);

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
(2, 'Max Mustermann', '+018493303', 'max@feeldgood.com', 'Musterplatz 45, 1100 Wien', 1876123456, 'Accounting', 1),
(3, 'Mister Muster', '+019987665', 'Mister@feeldgood.com', 'Hohlplatz 34, 1200 Wien', 2147483647, 'Sales', 4),
(4, 'Olaf Dodo', '+01845642', 'olaf@olaf.de', 'Musterplatz 122, 5040 Salzburg', 1787762333, 'Warehouse Worker', 4),
(6, 'Theodor Salter', '+01847733', 'salter.t@gmx.at', 'Grafenweg 56, 1220 Wien', 1870762432, 'IT', 2),
(7, 'Gerda Rogers', '+43664222939', 'gerdarogers@gmail.com', 'Hubergasse 34, 1090 Wien', 1682442812, 'IT', 3),
(8, 'Thomas Muster', '+01847223', 'mustertype@ffg.de', 'Musterplatz 12, 1100 Wien', 1666123216, 'Accounting', 3),
(9, 'Zazu Sembawae', '+43680234332', 'zztop@rock.com', 'Huttengasse 2, 1160 Wien', 267248377, 'Sales', 3),
(10, 'Johannes Huemer', '+436806687320', 'jhuemer@hotmail.com', 'Pilgramgasse 4, 1050 Wien', 1627762444, 'Warehouse Worker', 3),
(11, 'Bernd Wilaf', '+436763453838', 'pwilaf@dbz.jp', 'Drachenballweg 111, 1210 Wien', 1007762556, 'Warehouse Worker', 1),
(12, 'Georg Fernos', '+436643334432', 'ffr@hotmail.com', 'Kirchengasse 67, 1070 Wien', 1827262888, 'Warehouse Worker', 2),
(13, 'Maxwell Fork', '+43664775433', 'fork@gmx.at', 'Zummergasse 9, 1220 Wien', 1822762467, 'IT', 4),
(14, 'Gerlinde Swoboda', '+018477442', 'swobi@feeldgood.com', 'Musterplatz 89, 1020 Wien', 1999123444, 'Accounting', 4),
(15, 'Tom Remus', '+01847999', 'tomremus@gmx.de', 'Maiweg 66, 5020 Salzburg', 1777123222, 'Accounting', 2),
(16, 'Olivia Jones', '+019987211', 'jones@feeldgood.com', 'Burggasse 9, 1070 Wien', 1147488823, 'Sales', 1),
(17, 'Werner Kern', '+43680237788', 'werner@gmail.com', 'Hütteldorferstraße 5, 1150 Wie', 1111344445, 'Sales', 2);

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
  ADD UNIQUE KEY `productID_FK_2` (`productID_FK`,`orderlistID_FK`),
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
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

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
-- AUTO_INCREMENT für Tabelle `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `orderlistID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT für Tabelle `ordertab`
--
ALTER TABLE `ordertab`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT für Tabelle `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT für Tabelle `shippingteam`
--
ALTER TABLE `shippingteam`
  MODIFY `shippingteamID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `staffmember`
--
ALTER TABLE `staffmember`
  MODIFY `staffID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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
