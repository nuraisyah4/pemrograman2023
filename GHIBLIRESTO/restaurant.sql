-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 23, 2024 at 08:12 PM
-- Server version: 10.11.6-MariaDB-0+deb12u1
-- PHP Version: 8.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restaurant`
--

-- --------------------------------------------------------

--
-- Table structure for table `makanan`
--

CREATE TABLE `makanan` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `pathImage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `makanan`
--

INSERT INTO `makanan` (`id`, `name`, `price`, `pathImage`) VALUES
(1, 'Nasi Goreng', 15000.00, '/Images/MenuImage/image1.jpeg'),
(2, 'Mie Goreng', 12000.00, '/Images/MenuImage/image2.jpeg'),
(3, 'Ayam Goreng', 25000.00, '/Images/MenuImage/image3.jpeg'),
(4, 'Sate Ayam', 20000.00, '/Images/MenuImage/image4.jpeg'),
(5, 'Bakso', 18000.00, '/Images/MenuImage/image5.jpeg'),
(6, 'Soto Ayam', 15000.00, '/Images/MenuImage/image6.jpeg'),
(13, 'admin', 123.00, '/Images/MenuImage/image6.jpeg'),
(14, 'admin', 123.00, '/Images/MenuImage/image1.jpeg'),
(15, 'jerry', 123.00, '/Images/MenuImage/image1.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `minuman`
--

CREATE TABLE `minuman` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `pathImage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `minuman`
--

INSERT INTO `minuman` (`id`, `name`, `price`, `pathImage`) VALUES
(1, 'Teh Botol', 5.00, '/Images/MenuImage/image1.jpeg'),
(2, 'Es Jeruk', 4.50, '/Images/MenuImage/image1.jpeg'),
(3, 'Kopi Hitam', 6.00, '/Images/MenuImage/image1.jpeg'),
(4, 'Susu Cokelat', 4.00, '/Images/MenuImage/image1.jpeg'),
(5, 'Jus Mangga', 7.50, '/Images/MenuImage/image1.jpeg'),
(6, 'Es Teh', 3.00, '/Images/MenuImage/image1.jpeg'),
(7, 'Air Mineral', 2.00, '/Images/MenuImage/image1.jpeg'),
(8, 'Jus Alpukat', 8.00, '/Images/MenuImage/image1.jpeg'),
(9, 'Lemon Tea', 4.75, '/Images/MenuImage/image1.jpeg'),
(10, 'Soda Gembira', 5.50, '/Images/MenuImage/image1.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `item_name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `username`, `item_name`, `quantity`, `total_price`) VALUES
(1, 'customer_username', 'Bakso', 1, 18000.00),
(3, 'customer_username', 'admin', 1, 123.00),
(4, 'customer_username', 'admin', 1, 123.00),
(5, 'customer_username', 'Nasi Goreng', 1, 15000.00),
(6, 'customer_username', 'Kopi Hitam', 1, 6.00),
(7, 'customer_username', 'Kopi Hitam', 1, 6.00),
(9, 'customer_username', 'Kopi Hitam', 1, 6.00),
(10, 'customer_username', 'Kopi Hitam', 1, 6.00),
(11, 'customer_username', 'Kopi Hitam', 1, 6.00),
(12, 'customer_username', 'Kopi Hitam', 1, 6.00),
(13, 'customer_username', 'Kopi Hitam', 1, 6.00),
(14, 'customer_username', 'Kopi Hitam', 1, 6.00),
(15, 'customer_username', 'Kopi Hitam', 1, 6.00),
(16, 'customer_username', 'Kopi Hitam', 1, 6.00),
(17, 'customer_username', 'Teh Botol', 1, 5.00),
(18, 'customer_username', 'Teh Botol', 1, 5.00),
(19, 'customer_username', 'Teh Botol', 1, 5.00),
(20, 'customer_username', 'Teh Botol', 1, 5.00),
(21, 'customer_username', 'Teh Botol', 1, 5.00),
(22, 'customer_username', 'Teh Botol', 1, 5.00);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('admin','customer') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', '123', 'admin'),
(2, '1', '1', 'customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `makanan`
--
ALTER TABLE `makanan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `minuman`
--
ALTER TABLE `minuman`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
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
-- AUTO_INCREMENT for table `makanan`
--
ALTER TABLE `makanan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `minuman`
--
ALTER TABLE `minuman`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
