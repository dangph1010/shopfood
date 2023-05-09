-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 05, 2023 at 10:04 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yummyfood`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblcart`
--

CREATE TABLE `tblcart` (
  `USERNAME` varchar(50) NOT NULL,
  `FOODID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblcart`
--

INSERT INTO `tblcart` (`USERNAME`, `FOODID`, `QUANTITY`) VALUES
('theanh28', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tblcategories`
--

CREATE TABLE `tblcategories` (
  `CATID` int(11) NOT NULL,
  `CATNAME` varchar(100) DEFAULT NULL,
  `IMAGE` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblcategories`
--

INSERT INTO `tblcategories` (`CATID`, `CATNAME`, `IMAGE`) VALUES
(1, 'Fast Food', 'https://firebasestorage.googleapis.com/v0/b/yummyfood-image-storage.appspot.com/o/image%2Fcategory%2FHamburger.png?alt=media&token=74c20c3b-34f1-4fe4-a610-e248e3d4604e'),
(2, 'Cake', 'https://firebasestorage.googleapis.com/v0/b/yummyfood-image-storage.appspot.com/o/image%2Fcategory%2FDessert-PNG-Photo.png?alt=media&token=811fa4f3-3abb-4b75-bfa2-35b4d3dfeb0d'),
(3, 'Drinks', 'https://firebasestorage.googleapis.com/v0/b/yummyfood-image-storage.appspot.com/o/image%2Fcategory%2FDrink-PNG.png?alt=media&token=5a5c1fe4-2ac1-4ff6-907b-f8b7d9883d2d'),
(4, 'Dessert', 'https://firebasestorage.googleapis.com/v0/b/yummyfood-image-storage.appspot.com/o/image%2Fcategory%2Fdessert.png?alt=media&token=33b23a97-7860-4b44-8161-9ac48ba4091a'),
(5, 'Other', 'https://firebasestorage.googleapis.com/v0/b/yummyfood-image-storage.appspot.com/o/image%2Fcategory%2Fother.png?alt=media&token=bc7f8bbc-6c34-45da-a36c-325eef58d5e8');

-- --------------------------------------------------------

--
-- Table structure for table `tblcomment`
--

CREATE TABLE `tblcomment` (
  `FOODID` int(11) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `COMMENT` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblcomment`
--

INSERT INTO `tblcomment` (`FOODID`, `USERNAME`, `COMMENT`) VALUES
(2, 'theanh28', 'Bánh này rất ngon ! '),
(3, 'dat009', 'Sản phẩm rất  okeee '),
(1, 'huuanh12', 'Nên bổ sung thêm đồ uống !'),
(1, 'Dang', 'Nước ngon , trang trí đẹp , 10 điểm !');

-- --------------------------------------------------------

--
-- Table structure for table `tblfavourite`
--

CREATE TABLE `tblfavourite` (
  `IDFAVOURITE` int(11) NOT NULL,
  `FOODID` int(11) NOT NULL,
  `USERNAME` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblfavourite`
--

INSERT INTO `tblfavourite` (`IDFAVOURITE`, `FOODID`, `USERNAME`) VALUES
(84, 21, 'theanh28'),
(89, 22, 'theanh28');

-- --------------------------------------------------------

--
-- Table structure for table `tblfood`
--

CREATE TABLE `tblfood` (
  `FOODID` int(11) NOT NULL,
  `CATID` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `FOODNAME` varchar(550) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `IMAGE` varchar(2000) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblfood`
--

INSERT INTO `tblfood` (`FOODID`, `CATID`, `QUANTITY`, `FOODNAME`, `PRICE`, `IMAGE`, `STATUS`) VALUES
(1, 1, 8, 'Burger Mixed', 49000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670610740600-670293175-burgermixed.jpg?generation=1670610741129096&alt=media', 'new'),
(2, 1, 10, 'Burger Meat', 72000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611071800-992241329-burgermeat.jpg?generation=1670611072393771&alt=media', 'featured'),
(3, 1, 18, 'Burger 2 Cheese Beef', 58000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611084764-296736731-burger2cheesebeef.jpg?generation=1670611085569436&alt=media', 'removed'),
(4, 1, 18, 'Burger Fish', 78000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611095604-384715657-burgerfish.jpg?generation=1670611096229120&alt=media', 'removed'),
(5, 1, 17, 'Burger Cheese Chicken BBQ ', 54000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611122988-679996792-burgercheesechickenBBQ%20.jpg?generation=1670611123619370&alt=media', 'new'),
(6, 1, 24, 'Burger 2 Grilled Beef', 91000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611146221-180342780-burger2grilledbeef.jpg?generation=1670611147631668&alt=media', 'removed'),
(7, 1, 12, 'Burger Roasted Crispy Chicken', 49000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611172460-530390624-burgerroastedcrispychicken.jpg?generation=1670611172930729&alt=media', 'featured'),
(8, 1, 33, 'Crispy Chicken Not Spicy', 33000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611181051-573231248-crispychickennotspicy.jpg?generation=1670611181718854&alt=media', 'featured'),
(9, 1, 28, 'Fried Chicken Wings', 38000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611189322-55319084-friedchickenwings.jpg?generation=1670611189920895&alt=media', 'featured'),
(10, 1, 22, 'Spicy Crispy Chicken', 36000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611202204-74192471-spicycrispychicken.jpg?generation=1670611202714020&alt=media', 'new'),
(11, 1, 26, 'Chicken BBQ', 41000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611299437-520906043-chickenBBQ.jpg?generation=1670611300248578&alt=media', 'featured'),
(12, 5, 32, 'Pizza Shrimp Scampi', 83000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611310043-785182043-pizzashrimpscampi.jpg?generation=1670611310686914&alt=media', 'new'),
(13, 5, 41, 'Pizza Okonomiyaki', 89000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611324348-244049841-pizzaokonomiyaki.jpg?generation=1670611325001793&alt=media', 'new'),
(14, 5, 44, 'Pizza Mixed', 77000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611341938-656346962-pizzamixed.jpg?generation=1670611342938335&alt=media', 'featured'),
(15, 5, 12, 'Pizza Grilled Garlic Tomato ', 90000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611350323-359929775-pizzagrilledgarlictomato.jpg?generation=1670611350967612&alt=media', 'featured'),
(16, 5, 12, 'Pizza Sausage', 79000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611379302-576002169-pizzasausage.jpg?generation=1670611381486324&alt=media', 'new'),
(17, 5, 26, 'Gluten - Free Crust', 86000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611389540-880289576-glutenfreecrust.jpg?generation=1670611390170247&alt=media', 'new'),
(18, 5, 31, 'Pizza Seafood Marinara ', 100000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611397380-155359547-pizzaseafoodmarinara.jpg?generation=1670611398224489&alt=media', 'new'),
(19, 5, 11, 'Spaghetti Bacon Cream Sauce', 56000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611409588-664117268-spaghettibaconcreamsauce.jpg?generation=1670611411069777&alt=media', 'new'),
(20, 5, 13, 'Spaghetti Meat Sauce', 64000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611436667-907188701-spaghettimeatsauce.jpg?generation=1670611437235252&alt=media', 'featured'),
(21, 4, 48, 'Cream puffs', 26000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611462115-884758428-creampuffs.jpg?generation=1670611462833411&alt=media', 'new'),
(22, 4, 21, 'Chocolate Bombs', 46000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611469666-413182663-chocolatebombs.jpg?generation=1670611470503888&alt=media', 'new'),
(23, 4, 41, 'Tiramisu Ice Cream', 39000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611492490-937548337-tiramisuicecream.jpg?generation=1670611493278011&alt=media', 'new'),
(24, 2, 12, 'Dark Chocolate Mousse Cake', 44000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611504389-924774225-darkchocolatemoussecake.jpg?generation=1670611506111656&alt=media', 'new'),
(25, 2, 29, 'Easy Chocolate Pots de Crème', 41000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611514403-441195777-easychocolatepotsdecreme.jpg?generation=1670611515131809&alt=media', 'new'),
(26, 3, 100, 'Orange Iced Tea', 22000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611551771-541284453-orangeicedtea.jpg?generation=1670611552655531&alt=media', 'new'),
(27, 3, 40, 'Oreo Ice Cream Smoothie', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611560204-392511918-oreoicecreamsmoothie.jpg?generation=1670611561680430&alt=media', 'new'),
(28, 3, 50, 'Iced Matcha Latte', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611568073-541197824-icedmatchalatte.jpg?generation=1670611569181605&alt=media', 'new'),
(29, 3, 30, 'Watermelon Lemonade', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611576620-289306002-watermelonlemonade.jpg?generation=1670611578242204&alt=media', 'new'),
(30, 3, 25, 'Mulberry Soda', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611584124-890663402-mulberrysoda.jpg?generation=1670611585494522&alt=media', 'new'),
(36, 1, 150, 'French Fries', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611664507-150520507-frenchfries.jpg?generation=1670611665244582&alt=media', 'hot'),
(37, 5, 150, 'Fried German Sausage', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611674404-188724035-friedgermansausage.jpg?generation=1670611675545022&alt=media', 'hot'),
(38, 4, 150, 'Donut ', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611685211-327528509-donut.jpg?generation=1670611686373956&alt=media', 'hot'),
(39, 5, 150, 'Salad', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611695106-148263606-salad.jpg?generation=1670611695806116&alt=media', 'hot'),
(40, 5, 150, 'Kimbap', 32000, 'https://storage.googleapis.com/download/storage/v1/b/yummyfood-image-storage.appspot.com/o/image%2Ffood%2Fimage-1670611713067-939731541-kimbap.jpg?generation=1670611713738226&alt=media', 'hot');

-- --------------------------------------------------------

--
-- Table structure for table `tblorder`
--

CREATE TABLE `tblorder` (
  `ORDERID` int(11) NOT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(500) DEFAULT NULL,
  `PHONENUMBER` varchar(13) DEFAULT NULL,
  `NOTE` varchar(500) NOT NULL,
  `PAYMENTID` int(11) DEFAULT NULL,
  `ORDERTIME` timestamp NOT NULL DEFAULT current_timestamp(),
  `CONFIRMTIME` timestamp NULL DEFAULT NULL,
  `ORDSTATUS` int(11) DEFAULT NULL,
  `VOUCHERCODE` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblorder`
--

INSERT INTO `tblorder` (`ORDERID`, `USERNAME`, `ADDRESS`, `PHONENUMBER`, `NOTE`, `PAYMENTID`, `ORDERTIME`, `CONFIRMTIME`, `ORDSTATUS`, `VOUCHERCODE`) VALUES
(16, 'theanh28', 'aloalo123', '123696969', '', 1, '2022-12-07 02:04:15', '2022-12-07 02:06:32', 3, NULL),
(36, 'huuanh12', '', '', '', 1, '2022-12-25 18:25:09', '2022-12-25 18:38:25', 3, 'noel1'),
(37, 'huuanh12', 'aasvsdv', '0000091920199', '', 1, '2022-12-25 19:10:48', NULL, 2, NULL),
(38, 'huuanh12', 'aasvsdv', '0000091920199', '', 1, '2022-12-25 19:12:21', NULL, 2, 'noel3');

-- --------------------------------------------------------

--
-- Table structure for table `tblorderdetail`
--

CREATE TABLE `tblorderdetail` (
  `ORDERID` int(11) NOT NULL,
  `FOODID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `TOTAL` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblorderdetail`
--

INSERT INTO `tblorderdetail` (`ORDERID`, `FOODID`, `QUANTITY`, `TOTAL`) VALUES
(16, 12, 3, 249000),
(16, 14, 1, 77000),
(36, 36, 3, 96000),
(37, 2, 1, 72000),
(38, 2, 1, 72000);

-- --------------------------------------------------------

--
-- Table structure for table `tblpayment`
--

CREATE TABLE `tblpayment` (
  `PAYMENTID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblpayment`
--

INSERT INTO `tblpayment` (`PAYMENTID`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Table structure for table `tblrate`
--

CREATE TABLE `tblrate` (
  `RATEID` int(11) NOT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `FOODID` int(11) DEFAULT NULL,
  `RTCOMMENT` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblrate`
--

INSERT INTO `tblrate` (`RATEID`, `USERNAME`, `FOODID`, `RTCOMMENT`) VALUES
(1, 'huuanh12', 1, 'Ngon!!!'),
(2, 'theanh28', 4, 'Vừa miệng!!!'),
(3, 'dat009', 12, 'Cũng khá ngon!!!');

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE `tbluser` (
  `USERNAME` varchar(50) NOT NULL,
  `FULLNAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(80) NOT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `ROLE` int(11) NOT NULL DEFAULT 1,
  `IMAGE` varchar(300) NOT NULL,
  `ADDRESS` varchar(500) DEFAULT NULL,
  `PHONENUMBER` varchar(13) DEFAULT NULL,
  `DATEOFBIRTH` varchar(10) DEFAULT NULL,
  `STATUS` varchar(20) NOT NULL,
  `TOKEN` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbluser`
--

INSERT INTO `tbluser` (`USERNAME`, `FULLNAME`, `PASSWORD`, `EMAIL`, `ROLE`, `IMAGE`, `ADDRESS`, `PHONENUMBER`, `DATEOFBIRTH`, `STATUS`, `TOKEN`) VALUES
('Dang', 'Dang', '$2a$10$j/My8eJREWykpJazqISn1.KiYK0ho3x6.BqY4FKg6R9MOsd03fTXm', 'dang05@gmail.com', 1, 'https://zpsocial-f52-org.zadn.vn/976ed6d9a7cc499210dd.jpg', NULL, NULL, NULL, '', ''),
('dat009', 'Tran Quoc Dat', '$2a$10$vS9AI4utYhXbkPBTPI2SFOdLY5FVR9.U8DhfSnY5O6t4BC.Ri4gCm', 'kemmello2002@gmail.com', 0, 'https://zpsocial-f52-org.zadn.vn/976ed6d9a7cc499210dd.jpg', NULL, NULL, NULL, '', ''),
('dattran', 'Quoc Dat', '$2a$10$jmdjefGLEPwbXPlus1o9X.xSurk8DRuJkYEBH7pMlPepF8WvS9w8O', 'dattqps17272@fpt.edu.vn', 1, 'https://zpsocial-f52-org.zadn.vn/976ed6d9a7cc499210dd.jpg', 'Xuan Thanh , Long An', '0343356282', '10/09/2002', 'banned', ''),
('huuanh12', 'Anh', '$2a$10$ipjhsdnT43MwMRhtL3IG.O8o9egD3DqPucy.ZVMqbEVn2bfttTh9i', 'huuanh9999@gmail.com', 1, 'https://zpsocial-f52-org.zadn.vn/976ed6d9a7cc499210dd.jpg', 'aasvsdv', '0000091920199', '', '', ''),
('tai', 'Tai', '$2a$10$KChL5TOZkneZs9E0rq9xLOj7w3ED9sCu9Ga25glu9NS8GvlulHff2', 'tai@gmail.com', 1, 'https://zpsocial-f52-org.zadn.vn/976ed6d9a7cc499210dd.jpg', NULL, NULL, NULL, '', ''),
('theanh28', 'The Anh', '$2a$10$Y7PG4D/2r.7TYb0wgDGO4OdJJ1uc9rjsYyGE1D1CYoitQ73TWQaXW', 'theanh28@gmail.com', 1, 'https://zpsocial-f52-org.zadn.vn/976ed6d9a7cc499210dd.jpg', 'Quận 3 , HCM', '0343356279', '', 'active', '');

-- --------------------------------------------------------

--
-- Table structure for table `tblvoucher`
--

CREATE TABLE `tblvoucher` (
  `VOUCHERCODE` varchar(50) NOT NULL,
  `QUANTITY` varchar(50) DEFAULT NULL,
  `VOUCHER` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblvoucher`
--

INSERT INTO `tblvoucher` (`VOUCHERCODE`, `QUANTITY`, `VOUCHER`) VALUES
('noel1', '10', 'Tặng 1 ly kem'),
('noel2', '10', 'Tặng 1 thức uống ngẫu nhiên'),
('noel3', '9', 'Giảm 20% tổng hóa đơn');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblcart`
--
ALTER TABLE `tblcart`
  ADD PRIMARY KEY (`USERNAME`,`FOODID`),
  ADD KEY `FK_TBLCART_TBLFOOD` (`FOODID`);

--
-- Indexes for table `tblcategories`
--
ALTER TABLE `tblcategories`
  ADD PRIMARY KEY (`CATID`);

--
-- Indexes for table `tblcomment`
--
ALTER TABLE `tblcomment`
  ADD KEY `FK_TBLCOMMENT_TBLFOOD` (`FOODID`),
  ADD KEY `FK_TBLCOMMENT_TBLUSER` (`USERNAME`);

--
-- Indexes for table `tblfavourite`
--
ALTER TABLE `tblfavourite`
  ADD PRIMARY KEY (`IDFAVOURITE`);

--
-- Indexes for table `tblfood`
--
ALTER TABLE `tblfood`
  ADD PRIMARY KEY (`FOODID`),
  ADD KEY `FK_TBLFOOD_TBLCATEGORIES` (`CATID`);

--
-- Indexes for table `tblorder`
--
ALTER TABLE `tblorder`
  ADD PRIMARY KEY (`ORDERID`),
  ADD KEY `FK_TBLORDER_TBLUSER` (`USERNAME`),
  ADD KEY `FK_TBLORDER_TBLPAYMENT` (`PAYMENTID`),
  ADD KEY `FK_TBLORDER_TBLVOUCHER` (`VOUCHERCODE`);

--
-- Indexes for table `tblorderdetail`
--
ALTER TABLE `tblorderdetail`
  ADD PRIMARY KEY (`ORDERID`,`FOODID`),
  ADD KEY `FK_TBLORDERDETAIL_TBLFOOD` (`FOODID`);

--
-- Indexes for table `tblpayment`
--
ALTER TABLE `tblpayment`
  ADD PRIMARY KEY (`PAYMENTID`);

--
-- Indexes for table `tblrate`
--
ALTER TABLE `tblrate`
  ADD PRIMARY KEY (`RATEID`),
  ADD KEY `FK_TBLRATE_TBLUSER` (`USERNAME`),
  ADD KEY `FK_TBLRATE_TBLFOOD` (`FOODID`);

--
-- Indexes for table `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`USERNAME`);

--
-- Indexes for table `tblvoucher`
--
ALTER TABLE `tblvoucher`
  ADD PRIMARY KEY (`VOUCHERCODE`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblcategories`
--
ALTER TABLE `tblcategories`
  MODIFY `CATID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tblfavourite`
--
ALTER TABLE `tblfavourite`
  MODIFY `IDFAVOURITE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `tblfood`
--
ALTER TABLE `tblfood`
  MODIFY `FOODID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `tblorder`
--
ALTER TABLE `tblorder`
  MODIFY `ORDERID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `tblrate`
--
ALTER TABLE `tblrate`
  MODIFY `RATEID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblcart`
--
ALTER TABLE `tblcart`
  ADD CONSTRAINT `FK_TBLCART_TBLFOOD` FOREIGN KEY (`FOODID`) REFERENCES `tblfood` (`FOODID`),
  ADD CONSTRAINT `FK_TBLCART_TBLUSER` FOREIGN KEY (`USERNAME`) REFERENCES `tbluser` (`USERNAME`);

--
-- Constraints for table `tblcomment`
--
ALTER TABLE `tblcomment`
  ADD CONSTRAINT `FK_TBLCOMMENT_TBLFOOD` FOREIGN KEY (`FOODID`) REFERENCES `tblfood` (`FOODID`),
  ADD CONSTRAINT `FK_TBLCOMMENT_TBLUSER` FOREIGN KEY (`USERNAME`) REFERENCES `tbluser` (`USERNAME`);

--
-- Constraints for table `tblfood`
--
ALTER TABLE `tblfood`
  ADD CONSTRAINT `FK_TBLFOOD_TBLCATEGORIES` FOREIGN KEY (`CATID`) REFERENCES `tblcategories` (`CATID`);

--
-- Constraints for table `tblorder`
--
ALTER TABLE `tblorder`
  ADD CONSTRAINT `FK_TBLORDER_TBLPAYMENT` FOREIGN KEY (`PAYMENTID`) REFERENCES `tblpayment` (`PAYMENTID`),
  ADD CONSTRAINT `FK_TBLORDER_TBLUSER` FOREIGN KEY (`USERNAME`) REFERENCES `tbluser` (`USERNAME`),
  ADD CONSTRAINT `FK_TBLORDER_TBLVOUCHER` FOREIGN KEY (`VOUCHERCODE`) REFERENCES `tblvoucher` (`VOUCHERCODE`);

--
-- Constraints for table `tblorderdetail`
--
ALTER TABLE `tblorderdetail`
  ADD CONSTRAINT `FK_TBLORDERDETAIL_TBLFOOD` FOREIGN KEY (`FOODID`) REFERENCES `tblfood` (`FOODID`),
  ADD CONSTRAINT `FK_TBLORDERDETAIL_TBLORDER` FOREIGN KEY (`ORDERID`) REFERENCES `tblorder` (`ORDERID`);

--
-- Constraints for table `tblrate`
--
ALTER TABLE `tblrate`
  ADD CONSTRAINT `FK_TBLRATE_TBLFOOD` FOREIGN KEY (`FOODID`) REFERENCES `tblfood` (`FOODID`),
  ADD CONSTRAINT `FK_TBLRATE_TBLUSER` FOREIGN KEY (`USERNAME`) REFERENCES `tbluser` (`USERNAME`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
