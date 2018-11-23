-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 20, 2018 at 04:06 PM
-- Server version: 10.1.24-MariaDB
-- PHP Version: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlineauctionsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator_account`
--

CREATE TABLE `administrator_account` (
  `aId` int(11) NOT NULL,
  `USER_NAME` varchar(60) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator_account`
--

INSERT INTO `administrator_account` (`aId`, `USER_NAME`, `PASSWORD`) VALUES
(111001, 'Ashan', '12345'),
(111002, 'admin', '123');

-- --------------------------------------------------------

--
-- Table structure for table `biddetails`
--

CREATE TABLE `biddetails` (
  `bidID` int(11) NOT NULL,
  `bidPrice` float NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `bidDate` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `biddetails`
--

INSERT INTO `biddetails` (`bidID`, `bidPrice`, `cid`, `pid`, `bidDate`) VALUES
(1, 350001, 1, 3, '2018-05-07 15:01:55'),
(2, 48100, 18, 2, '2018-05-07 15:02:58'),
(3, 350006, 1, 3, '2018-05-07 15:57:13'),
(4, 350007, 18, 3, '2018-05-07 16:51:32'),
(5, 350008, 1, 3, '2018-05-07 16:53:39'),
(6, 350011, 1, 3, '2018-05-08 11:25:48'),
(7, 350012, 18, 3, '2018-05-08 15:34:13'),
(9, 76100, 1, 4, '2018-05-09 17:11:45'),
(10, 350013, 1, 3, '2018-05-09 17:15:04'),
(11, 350015, 18, 3, '2018-05-11 11:59:52'),
(12, 350100, 18, 3, '2018-05-11 12:01:12'),
(13, 350101, 1, 3, '2018-05-11 18:51:30'),
(14, 350102, 18, 3, '2018-05-11 18:53:13'),
(15, 52001, 1, 6, '2018-05-16 17:09:21');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `cid` int(11) NOT NULL,
  `cfname` varchar(15) NOT NULL,
  `clname` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `addrL1` varchar(30) DEFAULT NULL,
  `addrL2` varchar(30) DEFAULT NULL,
  `addrL3` varchar(30) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `DoB` varchar(10) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`cid`, `cfname`, `clname`, `email`, `addrL1`, `addrL2`, `addrL3`, `phone`, `DoB`, `gender`, `password`) VALUES
(1, 'Nadeesh', 'Hasaranga', 'nadeeshhasaranga@gmail.com', 'No 20', 'wakwella Rd', 'Galle', '0773112451', '1996-03-29', 'male', '12345'),
(2, 'Shalika', 'Ashan', 'shalikaashan@outlook.com', '244/B/2', 'Paleegala', 'Eheliyagoda', '0767391611', '1997-10-08', 'male', '12345678'),
(3, 'Thushari', 'Dhammika', 'thusharidhammika@gmail.com', '244/B/2', 'Paleegala', 'Eheliyagoda', '0713456987', '1972-09-28', 'female', '12345678'),
(4, 'Shalika', 'Ashan', 'shalikaashan@hotmail.com', '244/B/2', 'Paleegala', 'Eheliyagoda', '1234567890', '1997-08-08', NULL, '12345678'),
(5, 'Dinuka', 'Kulathunga', 'dinukakulathunga@gmail.com', '100', 'New Kandy Rd', 'Malabe', '0713456987', '', NULL, '12345678'),
(6, 'Lakeesha', 'Jayamini', 'lakeeshanjayamini@gmail.com', '124/B,', 'Madola', 'Avissawella', '0713934621', '', 'female', '12345678'),
(13, 'hirusha', 'randika', 'hirusharandika01@gmail.com', '185/a', 'Paleegala', 'Eheliyagoda', '0771501659', '2002-06-17', 'male', '02270048'),
(15, 'hirusha', 'randika', 'hirusharandika@gmail.com', '185/a', 'Paleegala', 'Eheliyagoda', '0771501659', '2002-06-17', 'male', 'Eheliyagoda'),
(16, 'hirusha', 'randika', 'hirusharandika02@gmail.com', '185/a', 'Paleegala', 'Eheliyagoda', '0771501659', '2002-06-17', 'male', 'Eheliyagoda'),
(18, 'Pavani', 'Nishedhana', 'pavaniwithanage@gmail.com', '100', 'Dandeniya', 'Eheliyagoda', '0767391456', '', 'female', 'Eheliyagoda'),
(19, 'Tharini', 'Perera', 'thariniperera@gmail.com', '22', 'Millawitiya', 'Kuruwita', '0713594917', '', 'female', 'Eheliyagoda'),
(21, 'Dinuka', 'Kulathunga', 'dinukakulathunga01@gmail.com', '124/B,', 'New Kandy Rd', 'Malabe', '0713456987', '1996-10-14', 'male', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `myfavorite`
--

CREATE TABLE `myfavorite` (
  `cid` int(11) NOT NULL,
  `pid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `myfavorite`
--

INSERT INTO `myfavorite` (`cid`, `pid`) VALUES
(1, 2),
(1, 3),
(1, 4),
(1, 6),
(18, 2),
(18, 3),
(18, 4);

-- --------------------------------------------------------

--
-- Table structure for table `myorders`
--

CREATE TABLE `myorders` (
  `orderId` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `myorders`
--

INSERT INTO `myorders` (`orderId`, `pid`, `cid`, `price`) VALUES
(1, 2, 18, 48100);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `pid` int(11) NOT NULL,
  `pname` varchar(120) DEFAULT NULL,
  `category` varchar(15) DEFAULT NULL,
  `addeddate` varchar(12) DEFAULT NULL,
  `bidPeriod_days` int(11) DEFAULT NULL,
  `pcondition` varchar(15) DEFAULT NULL,
  `lastBidPrice` float DEFAULT NULL,
  `sellerId` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `model` varchar(30) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'await',
  `addedTime` varchar(10) NOT NULL DEFAULT '00:00:00',
  `wonBy` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`pid`, `pname`, `category`, `addeddate`, `bidPeriod_days`, `pcondition`, `lastBidPrice`, `sellerId`, `description`, `brand`, `model`, `status`, `addedTime`, `wonBy`) VALUES
(1, 'Dell Inspiron 3537', 'computer', '2018-04-28', 6, 'pre owned', 45000, 5, 'Dell Inspiron 15 3537 15 - 4GB RAM - Windows 8 - Laptop - HDMI', 'Dell', 'INSPIRON 3537', 'suspend', '11:11:11', 0),
(2, 'HP Pavilion G6', 'computer', '2018-05-07', 3, 'New', 48100, 2, 'HP Pavilion G9 4GB RAM, 2GB VGA', 'HP', 'Pavilion', 'auction', '00:00:00', 18),
(3, 'Microsoft SurfaceBook', 'computer', '2018-05-01', 12, 'New', 350102, 2, 'SurfaceBook 16GB RAM, Intel Core i7', 'Microsoft', 'SurfaceBook', 'auction', '00:00:00', 0),
(4, 'Asus VivoBook Pro 15', 'computer', '2018-05-02', 12, 'New', 76100, 2, 'Asus vivobok pro i5,High quality 8GB RAM, 2GB VGA', '', 'VivoBook', 'auction', '00:00:00', 1),
(5, 'HP Pavilion G6', 'computer', '2018-04-19', 12, 'pre owned', 30000, 3, 'HP Pavilion G9 4GB RAM, 2GB VGA', 'HP', 'Pavilion', 'auction', '00:00:00', 0),
(6, 'HP Pavilion G6', 'computer', '2018-05-05', 12, 'New', 52001, 2, 'HP Pavilion G9 4GB RAM, 2GB VGA', 'HP', 'Pavilion', 'auction', '00:00:00', 0),
(582, 'Samsung Galaxy Note 8', 'others', '2018-05-19', 15, 'new', 117000, 2, '1 year company warranty', 'Samsung', 'SM-950FD', 'auction', '20:51:02', 0);

-- --------------------------------------------------------

--
-- Table structure for table `product_picture`
--

CREATE TABLE `product_picture` (
  `pid` int(11) NOT NULL,
  `imageid` int(11) NOT NULL,
  `imgPath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product_picture`
--

INSERT INTO `product_picture` (`pid`, `imageid`, `imgPath`) VALUES
(2, 1, 'style\\img\\2\\001.jpg'),
(2, 2, 'style\\img\\2\\002.jpg'),
(2, 3, 'style\\img\\2\\003.jpg'),
(3, 4, 'style\\img\\3\\004.jpg'),
(2, 5, 'style\\img\\2\\005.jpg'),
(2, 6, 'style\\img\\2\\006.jpg'),
(3, 7, 'style\\img\\3\\007.jpg'),
(3, 8, 'style\\img\\3\\008.jpg'),
(3, 9, 'style\\img\\3\\009.jpg'),
(3, 10, 'style\\img\\3\\010.jpg'),
(4, 11, 'style\\img\\4\\011.jpg'),
(4, 12, 'style\\img\\4\\012.jpg'),
(4, 13, 'style\\img\\4\\013.jpg'),
(4, 14, 'style\\img\\4\\014.jpg'),
(4, 15, 'style\\img\\4\\015.jpg'),
(5, 16, 'style\\img\\5\\016.jpg'),
(5, 17, 'style\\img\\5\\017.jpg'),
(5, 18, 'style\\img\\5\\018.jpg'),
(5, 19, 'style\\img\\5\\019.jpg'),
(5, 20, 'style\\img\\5\\020.jpg'),
(6, 21, 'style\\img\\6\\021.jpg'),
(6, 22, 'style\\img\\6\\022.jpg'),
(6, 23, 'style\\img\\6\\023.jpg'),
(6, 24, 'style\\img\\6\\024.jpg'),
(6, 25, 'style\\img\\6\\025.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `userprofile`
--

CREATE TABLE `userprofile` (
  `userID` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `secondName` varchar(50) NOT NULL,
  `gender` varchar(7) DEFAULT 'Male',
  `email` varchar(50) DEFAULT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  `dob` varchar(10) DEFAULT NULL,
  `addressLine1` varchar(50) DEFAULT NULL,
  `addressLine2` varchar(50) DEFAULT NULL,
  `addressLine3` varchar(50) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `accountType` varchar(7) NOT NULL DEFAULT 'buyer'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userprofile`
--

INSERT INTO `userprofile` (`userID`, `firstName`, `secondName`, `gender`, `email`, `phoneNumber`, `dob`, `addressLine1`, `addressLine2`, `addressLine3`, `password`, `accountType`) VALUES
(3, 'Shalika', 'Ashan', 'Male', 'shalikaashan@outlook.com', '0767391611', '1997-10-08', '244/B/2', 'Paleegala', 'Eheliyagoda', '12345', 'buyer'),
(5, 'Shalika', 'Ashan', 'Male', 'shalikaashan@hotmail.com', '0767391613', '1997-10-08', '244/B/2', 'Paleegala', 'Eheliyagoda', '12345', 'buyer'),
(6, 'Dinuka', 'Kulathunga', 'Male', 'dinukakulathunga@outlook.com', '0717456321', '1996-04-22', '100/B', 'New Kandy Road', 'Malabe', 'dinukakulathunga@outlook.com', 'buyer'),
(7, 'Dinuka', 'Kulathunga', 'Male', 'dinukakulathunga@hotmail.com', '0717456321', '1996-04-22', '100/B', 'New Kandy Road', 'Malabe', 'dinukakulathunga@hotmail.com', 'seller');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator_account`
--
ALTER TABLE `administrator_account`
  ADD PRIMARY KEY (`aId`);

--
-- Indexes for table `biddetails`
--
ALTER TABLE `biddetails`
  ADD PRIMARY KEY (`bidID`),
  ADD KEY `cid_fk` (`cid`),
  ADD KEY `pid_fk` (`pid`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cid`),
  ADD UNIQUE KEY `email_un` (`email`);

--
-- Indexes for table `myfavorite`
--
ALTER TABLE `myfavorite`
  ADD PRIMARY KEY (`cid`,`pid`),
  ADD KEY `fk_pid` (`pid`);

--
-- Indexes for table `myorders`
--
ALTER TABLE `myorders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `fkmyorders_pid` (`pid`),
  ADD KEY `fkmyorders_cid` (`cid`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pid`),
  ADD KEY `sellerId_fk` (`sellerId`);

--
-- Indexes for table `product_picture`
--
ALTER TABLE `product_picture`
  ADD PRIMARY KEY (`imageid`);

--
-- Indexes for table `userprofile`
--
ALTER TABLE `userprofile`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `unique_user` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrator_account`
--
ALTER TABLE `administrator_account`
  MODIFY `aId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111003;
--
-- AUTO_INCREMENT for table `biddetails`
--
ALTER TABLE `biddetails`
  MODIFY `bidID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `myorders`
--
ALTER TABLE `myorders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=583;
--
-- AUTO_INCREMENT for table `product_picture`
--
ALTER TABLE `product_picture`
  MODIFY `imageid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `userprofile`
--
ALTER TABLE `userprofile`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `biddetails`
--
ALTER TABLE `biddetails`
  ADD CONSTRAINT `cid_fk` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`),
  ADD CONSTRAINT `pid_fk` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`);

--
-- Constraints for table `myfavorite`
--
ALTER TABLE `myfavorite`
  ADD CONSTRAINT `fk_cid` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`),
  ADD CONSTRAINT `fk_pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`);

--
-- Constraints for table `myorders`
--
ALTER TABLE `myorders`
  ADD CONSTRAINT `fkmyorders_cid` FOREIGN KEY (`cid`) REFERENCES `customer` (`cid`),
  ADD CONSTRAINT `fkmyorders_pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `sellerId_fk` FOREIGN KEY (`sellerId`) REFERENCES `customer` (`cid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
