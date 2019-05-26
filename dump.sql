SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `CMS`
--

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

-- REMOVE IT IF NEEDED
CREATE DATABASE CMS;
USE CMS;
-- END

CREATE TABLE `articles` (
                          `id` int(11) NOT NULL,
                          `text` text NOT NULL,
                          `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`id`, `text`, `title`) VALUES
(12, 'Pour Marine Le Pen, ce succès oblige Emmanuel Macron... ', 'Elections'),
 (13, 'De notre point de vue, et aussi de celui de notre nostalgie ­galopante, un champion devrait toujours ressembler à Henri Leconte', 'Un apéro avec Henri Leconte');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
                          `id` int(11) NOT NULL,
                          `text` text NOT NULL,
                          `idArticle` int(11) NOT NULL,
                          `datecreation` varchar(255) NOT NULL,
                          `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `text`, `idArticle`, `datecreation`, `username`) VALUES
(126, 'Super article.', 13, '24 mai 2019 à 13:22:25', 'jane@gmail.com'),
(127, 'Tres bon.', 12, '25 mai 2019 à 11:54:53', 'jack@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
                       `id` int(11) NOT NULL,
                       `username` varchar(255) NOT NULL,
                       `password` varchar(255) NOT NULL,
                       `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
                       `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `isAdmin`, `email`) VALUES
(1, 'alex', '$argon2i$v=19$m=65536,t=10,p=1$xYY3qb8J//YXsOaa+zkp0w$tl9yW0AA1i0q+oFUd0C8s/ixwyokqo+4dIwZEIScygk', 1, 'alexZerah@gmail.com'),
(3, 'salomon', '$argon2i$v=19$m=65536,t=10,p=1$xYY3qb8J//YXsOaa+zkp0w$tl9yW0AA1i0q+oFUd0C8s/ixwyokqo+4dIwZEIScygk', 0, 'salomon@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ida` (`idArticle`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `articles`
--
ALTER TABLE `articles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `ida` FOREIGN KEY (`idArticle`) REFERENCES `articles` (`id`) ON DELETE CASCADE;
