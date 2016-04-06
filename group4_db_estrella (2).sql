-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 17, 2016 at 06:43 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `group4_db_estrella`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `date_ordered` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount_paid` varchar(50) NOT NULL,
  `change` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`ID`, `date_ordered`, `amount_paid`, `change`) VALUES
(1, '2016-03-17 00:58:56', '3500', '30.0'),
(2, '2016-03-17 01:37:06', '100', '28.0'),
(3, '2016-03-16 01:39:29', '500', '45.0'),
(4, '2016-03-17 12:13:45', '', '0.00'),
(5, '2016-03-17 12:18:18', '50', '28.0'),
(6, '2016-03-17 12:27:26', '111', '79.0');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) NOT NULL,
  `qty` float NOT NULL,
  `price` float NOT NULL,
  `re_stock_point` float DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ID`, `name`, `qty`, `price`, `re_stock_point`) VALUES
(1, 'qqqqqqq', 6, 33, 2),
(2, 'qwewqe', 0, 2, 1),
(4, 'eeee', 47, 22, 332),
(9, 'samo', 7, 32, 2),
(10, 'qqwe', 12, 4, 5),
(12, 'dedtsdfs', 5, 34, 2),
(13, 'sample product 1 ', 52, 25, 10),
(14, 'SampleProduct', 1, 54321, 1);

-- --------------------------------------------------------

--
-- Table structure for table `trans`
--

CREATE TABLE IF NOT EXISTS `trans` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `qty_ordered` float NOT NULL,
  `order_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `trans`
--

INSERT INTO `trans` (`ID`, `product_id`, `qty_ordered`, `order_ID`) VALUES
(1, 12, 5, 1),
(2, 1, 100, 1),
(3, 4, 1, 2),
(4, 13, 2, 2),
(5, 1, 1, 3),
(6, 2, 2, 3),
(7, 4, 3, 3),
(8, 9, 4, 3),
(9, 10, 5, 3),
(10, 12, 6, 3),
(11, 14, 1, 4),
(12, 13, 1, 4),
(13, 10, 2, 4),
(14, 10, 3, 4),
(15, 2, 2, 4),
(16, 4, 3, 4),
(17, 4, 1, 5),
(18, 9, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) NOT NULL,
  `password` varchar(55) NOT NULL,
  `user_type` varchar(55) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `username`, `password`, `user_type`) VALUES
(1, 'baquialnilo', '123Password', 'admin'),
(2, 'qwe', 'qwe', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
