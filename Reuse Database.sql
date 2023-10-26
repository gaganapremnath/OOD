-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2022 at 10:13 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reusebooks`
--

-- --------------------------------------------------------

--
-- Table structure for table `authors`
--

CREATE TABLE `authors` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `book_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` bigint(20) NOT NULL,
  `edition` varchar(255) DEFAULT NULL,
  `is_available` bit(1) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `edition`, `is_available`, `isbn`, `title`) VALUES
(5, '1', b'1', 'ISBN 1', 'Book 1'),
(8, '1', b'1', 'ISBN 2', 'Book 2'),
(11, '20', b'0', 'ISBN 3', 'Book 3'),
(14, '20', b'1', 'ISBN 3', 'Book 3');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(25);

-- --------------------------------------------------------

--
-- Table structure for table `prices`
--

CREATE TABLE `prices` (
  `id` bigint(20) NOT NULL,
  `price` varchar(255) DEFAULT NULL,
  `book_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prices`
--

INSERT INTO `prices` (`id`, `price`, `book_id`) VALUES
(6, '100', 5),
(9, '1000', 8),
(12, '90', 11),
(15, '100', 14),
(19, '81', 11),
(22, '73', 11);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `suid` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`suid`, `name`) VALUES
(1, 'Jaynam'),
(2, 'Raj'),
(3, 'Test User 3'),
(4, 'Test User 4');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  `price_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `type_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `book_id`, `price_id`, `student_id`, `type_id`) VALUES
(7, 5, 6, 1, 1),
(10, 8, 9, 1, 1),
(13, 11, 12, 2, 1),
(16, 14, 15, 1, 1),
(17, 11, 12, 3, 2),
(20, 11, 19, 3, 1),
(21, 11, 19, 1, 2),
(23, 11, 22, 1, 1),
(24, 11, 22, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`id`, `Name`) VALUES
(1, 'Bought'),
(2, 'Sell');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa0aigfuc5sofk40pgpegigvnh` (`book_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prices`
--
ALTER TABLE `prices`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKadmuf8xqxsddjocdhe58cie1r` (`book_id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`suid`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhwis5rd79vrejvuuuc513px7a` (`book_id`),
  ADD KEY `FKhcs5nxb6wpoqwfy6jwpip26hh` (`price_id`),
  ADD KEY `FK79fflsn23798fyh9hp8h66xkn` (`student_id`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authors`
--
ALTER TABLE `authors`
  ADD CONSTRAINT `FKa0aigfuc5sofk40pgpegigvnh` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `prices`
--
ALTER TABLE `prices`
  ADD CONSTRAINT `FKadmuf8xqxsddjocdhe58cie1r` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `FK79fflsn23798fyh9hp8h66xkn` FOREIGN KEY (`student_id`) REFERENCES `students` (`suid`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKhcs5nxb6wpoqwfy6jwpip26hh` FOREIGN KEY (`price_id`) REFERENCES `prices` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FKhwis5rd79vrejvuuuc513px7a` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
