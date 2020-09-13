-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2020 at 04:37 PM
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

--
-- Dumping data for table `tbl_contact`
--

INSERT INTO `tbl_contact` (`id`, `txt_username`, `txt_email`, `txt_comment`) VALUES
(1, 'Ashraf Shorbaji', 'Ashrafshorbaji6@gmail.com', 'This Comment Just For Testing ...!');

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
  `ItemPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_fruits`
--

INSERT INTO `tbl_fruits` (`id`, `Image_Path`, `Elites`, `ItemName`, `ItemPrice`) VALUES
(4, 'Images/Fruits/a03ababa44372fe8191aff2dbe2d13568a533c9e18d904790232da552870de18istockphoto-466175630-612x612.jpg', 'First', 'tomato', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_marketing`
--

CREATE TABLE `tbl_marketing` (
  `id` int(225) NOT NULL,
  `Image_Path` text NOT NULL,
  `ProductPrice` int(11) NOT NULL,
  `ProductName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_marketing`
--

INSERT INTO `tbl_marketing` (`id`, `Image_Path`, `ProductPrice`, `ProductName`) VALUES
(1, 'Images/SuperMarket/35ad5870b12e0cff4ee0693279871f0aunnamed.jpg', 1, 'Orange Juice');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_meals`
--

CREATE TABLE `tbl_meals` (
  `id` int(225) NOT NULL,
  `Image_Path` text NOT NULL,
  `NameRestaurant` varchar(200) NOT NULL,
  `NameMeal` varchar(100) NOT NULL,
  `PriceMeal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_meals`
--

INSERT INTO `tbl_meals` (`id`, `Image_Path`, `NameRestaurant`, `NameMeal`, `PriceMeal`) VALUES
(3, 'Images/Food/e91050548f2ea320d8680a879e8a2adcunnamed.jpg', 'AlRyalat', 'Mansaf', 50),
(4, 'Images/Food/26a9fcea62eef23f5a31525eb480f6d625842hlmjo.jpg', 'AlRyalat', 'Msakhan', 20),
(5, 'Images/Food/ba8669010d25fbc49525fdbf34c30b342132123132.jpg', 'The Golden Meal', 'Mandi', 20.5);

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
  `TypeFood` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_restaurants`
--

INSERT INTO `tbl_restaurants` (`id`, `Image_Path`, `NameRestaurant`, `TypeFood`) VALUES
(1, 'Images/Restaurants/6e89da82e23168511e838aa72525f5548b6b1a4982a28e5fc9947321eb545c2eMeat-1.jpg', 'Thigh', '5'),
(2, 'Images/Restaurants/405d264e048d4c414a99108e7dde1e05g_20190726_162841_39.jpg', 'Released Meat', '7');

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
  `PointsCustomer` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `UserName`, `Password_Col`, `EmailUser`, `PointsCustomer`) VALUES
(3, 'test', '098f6bcd4621d373cade4e832627b4f6', 'ttttttt@yahoo.com', 0),
(4, 'testing', 'ae2b1fca515949e5d54fb22b8ed95575', 'testing@yahoo.com', 0);

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
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_marketing`
--
ALTER TABLE `tbl_marketing`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_meals`
--
ALTER TABLE `tbl_meals`
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `id` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
