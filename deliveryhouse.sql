-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2020 at 03:51 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deliveryhouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_contact`
--

CREATE TABLE `tbl_contact` (
  `id` int(225) NOT NULL,
  `txt_username` varchar(100) NOT NULL,
  `txt_email` varchar(100) NOT NULL,
  `txt_comment` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_control`
--

CREATE TABLE `tbl_control` (
  `id` int(225) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password_Col` varchar(100) NOT NULL,
  `Status_Col` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_control`
--

INSERT INTO `tbl_control` (`id`, `UserName`, `Password_Col`, `Status_Col`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'testing', 'testing', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drivercar`
--

CREATE TABLE `tbl_drivercar` (
  `id` int(225) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password_Col` varchar(100) NOT NULL,
  `EmailUser` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_drivercar`
--

INSERT INTO `tbl_drivercar` (`id`, `UserName`, `Password_Col`, `EmailUser`) VALUES
(1, 'driver', '1235', 'driverone@yahoo.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_fruits`
--

CREATE TABLE `tbl_fruits` (
  `id` int(225) NOT NULL,
  `Image_Path` text NOT NULL,
  `Elites` text NOT NULL,
  `ItemName` text NOT NULL,
  `Description` varchar(200) NOT NULL,
  `ItemPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_fruits`
--

INSERT INTO `tbl_fruits` (`id`, `Image_Path`, `Elites`, `ItemName`, `Description`, `ItemPrice`) VALUES
(1, 'Images/Fruits/418fd54b319cf9fe3719949f3da5fb3b9f1b4a44a7a75c485dcdad3258d758daphoto-1582284540020-8acbe03f4924.jpg', 'First', 'tomato', 'cultivated in Jordan valley with the highest care.', 2.75),
(4, 'Images/Fruits/13f3eb9183d8ac3f016f6c0758020e2f62183a6a6ee019c27daa2c8d1c86fb4fphoto-1509963906410-fceef97f22f8.jpg', 'Third', 'tomato', 'imported from Sudan', 1),
(5, 'Images/Fruits/2d6b5c18d17a3a5346867a425e22f4caphoto-1508313880080-c4bef0730395.jpg', 'First', 'potato', 'cultivated in Jordan valley ', 1.5),
(6, 'Images/Fruits/8b000c19fccf6ba94fbd42a0c86e63a2bbd3a0e514b0a4141dd38d6ad5a508a6photo-1566055803687-0665ef48df90.jpg', 'Second', 'potato', 'planted in greenhouses in Amman', 1),
(7, 'Images/Fruits/200f389628c33e1c7fd38feceba2c3c5ab252b23bfade151c0469f17e24a233bphoto-1564847783050-8afa11e5e875.jpg', 'Third', 'potato', 'imported from Bahrain', 1.7),
(8, 'Images/Fruits/978b8c8d93a58cbb1d2a3112073114f1754645a97ad604894f7b1183373ca20484722c031662ccfe750c78e3ca75e7a8photo-1511915274835-e2e34894d687.jpg', 'Second', 'Tomato', 'imported from KSA', 1.5),
(9, 'Images/Fruits/c5ae1dfd6124533dc2e86568dc593411banana2.jpg', 'Second', 'Banana', 'Cultivated in Jordan Valley', 1.45),
(10, 'Images/Fruits/33f6243294db234c16b12dca77181175download.jpg', 'Second', 'Orange', 'Imported from Syria', 1.3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_marketing`
--

CREATE TABLE `tbl_marketing` (
  `id` int(225) NOT NULL,
  `Image_Path` text NOT NULL,
  `ProductPrice` int(11) NOT NULL,
  `ProductName` varchar(100) NOT NULL,
  `Description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_marketing`
--

INSERT INTO `tbl_marketing` (`id`, `Image_Path`, `ProductPrice`, `ProductName`, `Description`) VALUES
(1, 'Images/SuperMarket/9ff4a3712180e07b7c80da933a0fd2b5maxim pineapple.webp', 1, 'pineapple', 'Maxim Pineapple \r\ncontains 12 pieces \r\n500g\r\n83 kcalorie '),
(2, 'Images/SuperMarket/a1a71d15972728a64e57021a35088c6egoodi pineapple.jpg', 1, 'pineapple', 'Goodi Pineapple ,contains 8 pieces,360g,70 kcalorie '),
(3, 'Images/SuperMarket/311ca3879fa253f302667aa8c8c2b416fava beans plain medammes california .jpg', 1, 'fava beans plain medammes ', 'fava beans plain medammes California, \r\n50g\r\n\r\n'),
(4, 'Images/SuperMarket/c54762affe3f4afd3394838b9a45bd1ffava beans plain medammes americana.jpg', 1, 'fava beans plain medammes ', 'fava beans plain medammes Americana \r\n100g');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_meals`
--

CREATE TABLE `tbl_meals` (
  `id` int(225) NOT NULL,
  `Image_Path` text NOT NULL,
  `NameRestaurant` varchar(200) NOT NULL,
  `NameMeal` varchar(100) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `PriceMeal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_meals`
--

INSERT INTO `tbl_meals` (`id`, `Image_Path`, `NameRestaurant`, `NameMeal`, `Description`, `PriceMeal`) VALUES
(5, 'Images/Food/2970834620233d09b86312c5ff43ae0726a9fcea62eef23f5a31525eb480f6d625842hlmjo.jpg', 'ALBaik', 'msakhan', 'this meal is for two persons and you can decide its ingredients from our application', 5),
(6, 'Images/Food/c00fdc5727debc2bf869e09595cb97ee2c0dcfd34e875764d3f8b67705640932e91050548f2ea320d8680a879e8a2adcunnamed.jpg', 'Central Alia', 'Mansaf', 'this meal is for two persons and you can decide its ingredients from our application', 8),
(7, 'Images/Food/b5807316013ab0f969d3db2b6bcbb69bba6deb8d2c7eea106851817a7f92b47dÙƒÙŠÙÙŠØ©_Ø·Ø¨Ø®_ÙƒØ¨Ø³Ø©_Ø§Ù„Ø¯Ø¬Ø§Ø¬.jpg', 'ALSarawat', 'kabsa', 'this meal is for two persons and you can decide its ingredients from our application', 4),
(8, 'Images/Food/fb938736a22385ddac2f2a068e4f241dØ·Ø±ÙŠÙ‚Ø©_Ø¹Ù…Ù„_Ù…Ù‚Ù„ÙˆØ¨Ø©_Ø§Ù„Ø¯Ø¬Ø§Ø¬.jpg', 'ALRyallat', 'Maqlooba', 'this meal is for two persons and you can decide its ingredients from our application', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_orderpopular`
--

CREATE TABLE `tbl_orderpopular` (
  `id` int(225) NOT NULL,
  `ItemOne` varchar(50) DEFAULT NULL,
  `ItemTwo` varchar(50) DEFAULT NULL,
  `ItemThree` varchar(30) DEFAULT NULL,
  `ItemFour` varchar(30) DEFAULT NULL,
  `ItemFive` varchar(50) DEFAULT NULL,
  `ItemSix` varchar(50) DEFAULT NULL,
  `ItemSeven` varchar(50) DEFAULT NULL,
  `ItemEight` varchar(50) DEFAULT NULL,
  `CustomerSession` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_orderpopular`
--

INSERT INTO `tbl_orderpopular` (`id`, `ItemOne`, `ItemTwo`, `ItemThree`, `ItemFour`, `ItemFive`, `ItemSix`, `ItemSeven`, `ItemEight`, `CustomerSession`) VALUES
(1, 'Null', 'Pistachio 4 JD', 'Oil 2 JD', 'Null', 'Null', 'Null', 'Null', 'Chickens  3 JD', 'ttttttt@yahoo.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_orders`
--

CREATE TABLE `tbl_orders` (
  `id` int(255) NOT NULL,
  `ItenName` varchar(30) NOT NULL,
  `Pricing` varchar(30) NOT NULL,
  `Quantity` varchar(50) NOT NULL,
  `pincode` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_orders`
--

INSERT INTO `tbl_orders` (`id`, `ItenName`, `Pricing`, `Quantity`, `pincode`) VALUES
(2, 'tomato', '30', '4', 'ttttttt@yahoo.com'),
(3, 'Chicken Shawarma', '2', '5', 'ttttttt@yahoo.com'),
(4, 'Meats ', '10', '2', 'ttttttt@yahoo.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_restaurants`
--

CREATE TABLE `tbl_restaurants` (
  `id` int(225) NOT NULL,
  `Image_Path` text NOT NULL,
  `NameRestaurant` varchar(100) NOT NULL,
  `TypeFood` varchar(100) NOT NULL,
  `Name` text NOT NULL,
  `Description` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_restaurants`
--

INSERT INTO `tbl_restaurants` (`id`, `Image_Path`, `NameRestaurant`, `TypeFood`, `Name`, `Description`) VALUES
(10, 'Images/Restaurants/8873119bd4632760c52ac940e33125bfwingswatanyeh.jpg', 'Chicken wings', '2.50', '', 'alwatanyeah it contains 8 pieces'),
(11, 'Images/Restaurants/31a536a281e8ee7ddabd9d5e52acebd8sadia wings.jpg', 'Chicken wings', '2.30', '', 'sadia wings it contains 7 pieces'),
(12, 'Images/Restaurants/b77984497a415c1602517b9f2b5fc436aljazera breast.jpg', 'Chicken breast', '3.00', '', 'Aljazeera breast each one is .50kg contains two pieces'),
(13, 'Images/Restaurants/1a73988cb4d223c32bd1f61ec2eb76e9sadia breast.jpg', 'Chicken breast', '2.30', '', 'sadia and it contains two pieces each one is 0.35kg'),
(14, 'Images/Restaurants/9e2b142ac070d08c5a7a59fa79ad0155Americana-Frozen-Chicken-1100g-Shrink-Wrap-Design-FN-Ara-3D-1.jpg', 'Chicken', '4.00', '', 'Americana Frozen chicken 1100g'),
(15, 'Images/Restaurants/a6bba89a962be8f0f369afc3b382b355images.jpg', 'Chicken', '3.75', '', 'aljazera frozen chcken 990g'),
(16, 'Images/Restaurants/d83c421fab0af9f729df6f6fa13149a5b2fd0740da44b9ee0e58af3778ffdef5.jpg', 'Sheep Meat', '2.00', '', 'steak from alwatanyeh ,it is 60g ');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_secure`
--

CREATE TABLE `tbl_secure` (
  `id` int(225) NOT NULL,
  `CustomerName` text NOT NULL,
  `CustomerCode` text,
  `CustomerStatus` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_secure`
--

INSERT INTO `tbl_secure` (`id`, `CustomerName`, `CustomerCode`, `CustomerStatus`) VALUES
(1, 'ttttttt@yahoo.com', '2744', 'Available'),
(2, 'ttttttt@yahoo.com', '8631', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(225) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password_Col` varchar(100) NOT NULL,
  `EmailUser` varchar(100) NOT NULL,
  `PointsCustomer` int(30) NOT NULL,
  `locationUser` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `UserName`, `Password_Col`, `EmailUser`, `PointsCustomer`, `locationUser`) VALUES
(3, 'test', '098f6bcd4621d373cade4e832627b4f6', 'ttttttt@yahoo.com', 0, 'University of Applied Sciences Al Arab St 21, Amman'),
(4, 'testing', 'ae2b1fca515949e5d54fb22b8ed95575', 'testing@yahoo.com', 0, 'Zarqa - New Zarqa Government Hospital - Street 10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_contact`
--
ALTER TABLE `tbl_contact`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_control`
--
ALTER TABLE `tbl_control`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drivercar`
--
ALTER TABLE `tbl_drivercar`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_fruits`
--
ALTER TABLE `tbl_fruits`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_marketing`
--
ALTER TABLE `tbl_marketing`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_meals`
--
ALTER TABLE `tbl_meals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_orderpopular`
--
ALTER TABLE `tbl_orderpopular`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_restaurants`
--
ALTER TABLE `tbl_restaurants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_secure`
--
ALTER TABLE `tbl_secure`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_contact`
--
ALTER TABLE `tbl_contact`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_control`
--
ALTER TABLE `tbl_control`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_drivercar`
--
ALTER TABLE `tbl_drivercar`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_fruits`
--
ALTER TABLE `tbl_fruits`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_marketing`
--
ALTER TABLE `tbl_marketing`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_meals`
--
ALTER TABLE `tbl_meals`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_orderpopular`
--
ALTER TABLE `tbl_orderpopular`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_orders`
--
ALTER TABLE `tbl_orders`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_restaurants`
--
ALTER TABLE `tbl_restaurants`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tbl_secure`
--
ALTER TABLE `tbl_secure`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
