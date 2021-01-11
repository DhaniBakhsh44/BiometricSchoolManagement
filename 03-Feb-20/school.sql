-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2021 at 03:21 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `school`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `class_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `attendance_date` varchar(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`class_id`, `scheme_id`, `term`, `attendance_date`, `status`, `remarks`) VALUES
(1, 1, 0, '11/22/2020', 1, ''),
(12, 5, 0, '11/22/2020', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `attendance_detail`
--

CREATE TABLE `attendance_detail` (
  `class_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `attendance_date` varchar(10) DEFAULT NULL,
  `roll_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `attendance_detail`
--

INSERT INTO `attendance_detail` (`class_id`, `scheme_id`, `term`, `attendance_date`, `roll_no`, `status`, `remarks`) VALUES
(1, 1, 0, '11/22/2020', 1, 1, 'null'),
(1, 1, 0, '11/22/2020', 7, 0, 'null'),
(1, 1, 0, '11/22/2020', 9, 1, 'null'),
(12, 5, 0, '11/22/2020', 2, 1, 'null'),
(12, 5, 0, '11/22/2020', 10, 0, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `class_id` int(10) DEFAULT NULL,
  `class_name` varchar(10) DEFAULT NULL,
  `class_no` varchar(10) DEFAULT NULL,
  `section` varchar(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `class_name`, `class_no`, `section`, `status`, `remarks`) VALUES
(1, 'Nursery', 'Nursery', 'A', 1, 'good'),
(2, 'KG-1', 'KG-1', 'A', 1, 'goodd'),
(3, 'KG-2', 'KG-2', 'null', 1, 'good'),
(4, 'One', '1', 'null', 1, 'acha '),
(5, 'Two', '2', 'A', 1, ''),
(6, 'Three', '3', 'A', 1, ''),
(7, 'Four', '4', 'null', 1, ''),
(8, 'Five', '5', 'A', 1, ''),
(9, 'Six', '6', 'A', 1, ''),
(10, 'Seven', '7', 'A', 1, ''),
(11, 'Eight', '8', 'A', 1, ''),
(12, 'Nine', '9', 'A', 1, ''),
(13, 'Ten', '10', 'null', 1, 'NO');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `scheme_id` int(10) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  `course_no` int(10) DEFAULT NULL,
  `course_title` varchar(15) DEFAULT NULL,
  `course_keyword` varchar(15) DEFAULT NULL,
  `min_marks` int(2) DEFAULT NULL,
  `max_marks` int(3) DEFAULT NULL,
  `subject_type` varchar(15) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`scheme_id`, `class_id`, `course_no`, `course_title`, `course_keyword`, `min_marks`, `max_marks`, `subject_type`, `status`, `remarks`) VALUES
(1, 1, 1, 'English', 'English', 33, 100, 'Compulsory Th', 1, 'null'),
(1, 1, 2, 'Sindhi', 'Sindhi', 33, 100, '', 1, ''),
(1, 1, 3, 'Urdu', 'Urdu', 33, 100, '', 1, ''),
(1, 1, 4, 'Drawing', 'Drawing', 33, 100, '', 1, ''),
(1, 1, 5, 'Social Studies', 'Social Studies', 33, 100, 'Theory', 1, 'null'),
(6, 13, 8, 'English', 'English', 0, 0, 'Compulsory Th', 1, 'null'),
(6, 13, 9, 'Physics', 'phy', 0, 0, 'Theory', 1, 'null'),
(6, 13, 10, 'Physics-Pract', 'phy-Practicle', 33, 100, 'Practical', 1, 'null'),
(5, 12, 11, 'Physics', 'phy-Practicle', 0, 0, 'Practical', 1, 'null'),
(5, 12, 12, 'Physics', 'phy', 0, 0, 'Theory', 1, 'null'),
(5, 12, 13, 'Sindhi', 'sindhi', 0, 0, 'Compulsory Th', 1, 'null'),
(2, 2, 14, 'Sindhi', 'sindhi', 33, 100, 'Compulsory Th', 1, 'null'),
(2, 2, 15, 'urdu', 'urdu', 33, 100, 'Compulsory Th', 1, 'null'),
(1, 1, 16, 'Islamic Studies', 'Islamic', 33, 100, 'Compulsory Th', 1, 'null'),
(4, 12, 17, 'Physics', 'phy', 0, 0, 'Theory', 1, 'null'),
(4, 12, 18, 'Physics-prac', 'phy-prac', 0, 0, 'Practical', 1, 'null'),
(4, 12, 19, 'English', 'English', 0, 0, 'Compulsory Th', 1, 'null'),
(4, 12, 20, 'Chemistry', 'Chemistry', 33, 100, 'Compulsory Th', 1, 'null'),
(4, 12, 21, 'Chemistry-prac', 'Chemistry', 33, 100, 'Practical', 1, 'null'),
(2, 2, 22, 'Englis', 'Engl', 33, 44, 'Compulsory Th', 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `exam_list`
--

CREATE TABLE `exam_list` (
  `scheme_id` int(10) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `exam_list` int(10) DEFAULT NULL,
  `el_year` int(4) DEFAULT NULL,
  `el_month` int(2) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `exam_list`
--

INSERT INTO `exam_list` (`scheme_id`, `class_id`, `term`, `exam_list`, `el_year`, `el_month`, `type`, `status`, `remarks`) VALUES
(1, 1, 1, 27, 2010, 1, 'Regular', 1, 'null'),
(1, 1, 2, 29, 2010, 10, 'Regular', 1, 'null'),
(4, 12, 1, 30, 2010, 4, 'Regular', 1, 'null'),
(4, 12, 2, 31, 2010, 11, 'Regular', 1, 'null'),
(5, 12, 1, 32, 2011, 7, 'Regular', 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `exam_list_detail`
--

CREATE TABLE `exam_list_detail` (
  `scheme_id` int(10) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `exam_list` int(10) DEFAULT NULL,
  `roll_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `exam_list_detail`
--

INSERT INTO `exam_list_detail` (`scheme_id`, `class_id`, `term`, `exam_list`, `roll_no`, `status`, `remarks`) VALUES
(1, 1, 1, 27, 1, 1, 'null'),
(1, 1, 1, 27, 7, 1, 'null'),
(1, 1, 1, 27, 9, 1, 'null'),
(1, 1, 2, 29, 7, 1, 'null'),
(4, 12, 1, 30, 3, 1, 'null'),
(4, 12, 2, 31, 8, 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `marksheet`
--

CREATE TABLE `marksheet` (
  `marksheet_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `exam_list` int(10) DEFAULT NULL,
  `roll_no` int(10) DEFAULT NULL,
  `seat_no` int(2) DEFAULT NULL,
  `total_marks` int(3) DEFAULT NULL,
  `marks_in_words` varchar(10) DEFAULT NULL,
  `obtain_marks` int(3) DEFAULT NULL,
  `percentage` int(3) DEFAULT NULL,
  `grade` varchar(4) DEFAULT NULL,
  `rank` varchar(10) DEFAULT NULL,
  `position` varchar(10) DEFAULT NULL,
  `total_days` int(10) DEFAULT NULL,
  `present_days` int(10) DEFAULT NULL,
  `date_of_declaration` varchar(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `scheme`
--

CREATE TABLE `scheme` (
  `scheme_id` int(10) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  `scheme_name` varchar(15) DEFAULT NULL,
  `max_marks` int(3) DEFAULT NULL,
  `min_marks` int(2) DEFAULT NULL,
  `passing_marks` int(3) DEFAULT NULL,
  `last_update` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `scheme`
--

INSERT INTO `scheme` (`scheme_id`, `class_id`, `scheme_name`, `max_marks`, `min_marks`, `passing_marks`, `last_update`, `status`, `remarks`) VALUES
(1, 1, 'Nursery', 0, 0, 0, 'null', 1, 'null'),
(2, 2, 'KG1', 0, 0, 0, '', 1, ''),
(4, 12, 'Nine Science', 0, 0, 0, 'null', 1, 'null'),
(5, 12, 'Nine Arts', 0, 0, 0, 'null', 1, 'null'),
(6, 13, 'Ten Science', 0, 0, 0, '', 1, ''),
(7, 13, 'Ten Arts', 0, 0, 0, '', 1, ''),
(8, 4, 'one', 0, 0, 0, '', 1, ''),
(43, 3, 'KG-2', 0, 0, 0, '08-Feb-20', 1, ''),
(45, 5, 'Two', 0, 0, 0, '08-Feb-20', 1, ''),
(46, 6, 'Three', 0, 0, 0, '08-Feb-20', 1, ''),
(47, 7, 'Four', 0, 0, 0, '08-Feb-20', 1, ''),
(48, 8, 'Five', 0, 0, 0, '08-Feb-20', 1, ''),
(49, 9, 'Six', 0, 0, 0, '08-Feb-20', 1, ''),
(50, 10, 'Seven', 0, 0, 0, '08-Feb-20', 1, ''),
(51, 11, 'Eight', 0, 0, 0, '08-Feb-20', 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `roll_no` int(10) DEFAULT NULL,
  `student_name` varchar(15) DEFAULT NULL,
  `father_name` varchar(15) DEFAULT NULL,
  `mother_name` varchar(15) DEFAULT NULL,
  `surname` varchar(10) DEFAULT NULL,
  `date_of_birth` varchar(15) DEFAULT NULL,
  `place_of_birth` varchar(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `guardian_name` varchar(10) DEFAULT NULL,
  `guardian_occupation` varchar(10) DEFAULT NULL,
  `phone_no` varchar(11) DEFAULT NULL,
  `mobile_no` varchar(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `class_admission` varchar(10) DEFAULT NULL,
  `last_attendent_school` varchar(25) DEFAULT NULL,
  `admission_date` varchar(15) DEFAULT NULL,
  `admission_fees` int(15) DEFAULT NULL,
  `finger_template` varchar(4) DEFAULT NULL,
  `finger_image` varchar(4) DEFAULT NULL,
  `profile_image` varchar(4) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `previous_roll_no` int(3) DEFAULT NULL,
  `entry_test_marks` int(3) DEFAULT NULL,
  `entry_test_remarks` varchar(4) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`roll_no`, `student_name`, `father_name`, `mother_name`, `surname`, `date_of_birth`, `place_of_birth`, `gender`, `guardian_name`, `guardian_occupation`, `phone_no`, `mobile_no`, `address`, `class_admission`, `last_attendent_school`, `admission_date`, `admission_fees`, `finger_template`, `finger_image`, `profile_image`, `status`, `previous_roll_no`, `entry_test_marks`, `entry_test_remarks`, `remarks`) VALUES
(1, 'Muhammad Younis', 'Atta Muhammad', '---', 'Abbasi', '05-12-199', 'Larkana', 'm', 'Father', '', '123', '1234', 'Hyd', 'null', 'Khan Muhammad High School', '01-Feb-20', 0, 'null', 'null', 'null', 1, 0, 0, 'null', 'no'),
(2, 'Suhail h', 'Naseer Ahmed', 'sss', 'jatoi', '08-6-1997', 'Larkana', 'm', 'null', 'null', '0977', '0987', 'jam', 'null', 'Khan Muhammad', '08-Feb-20', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(3, 'DHANI BAKHSH', 'GUL MUHAMMAD', '-----', 'Panhwer', '3-Nov-202', 'MIRPUR KHAS', 'Male', 'null', 'null', '2324', '876', 'HYDERABAD SINDH', 'null', 'GOVT', '3-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', 'NO'),
(7, 'Farooq', 'Jamil ali', '-----', 'Chaki', '3-Jun-199', 'Mpk', 'Male', 'null', 'null', 'null', 'null', 'karachi', 'null', 'little fox', '3-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', 'no'),
(8, 'Younis', 'ali', '', 'abbasi', '5-Nov-202', '', 'Male', 'father', 'null', 'null', 'null', 'MPK', 'null', 'govt', '5-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(9, 'javed', 'ali khan', '', 'khoso', '5-Nov-202', '', 'Male', '', 'null', 'null', 'null', '', 'null', '', '5-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(10, 'kasim', '', '', '', '5-Nov-202', '', 'Male', '', 'null', 'null', 'null', '', 'null', '', '5-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(13, 'qasim', '', '', '', '5-Nov-202', '', 'Male', '', 'null', 'null', 'null', '', 'null', '', '5-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(15, 'Hyder', '', '', '', '5-Nov-202', '', 'Male', '', 'null', 'null', 'null', '', 'null', '', '5-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(NULL, '', '', '', '', '28-Nov-2020', '', 'Male', '', '', '', '', '', 'null', '', '28-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', ''),
(NULL, 'Rashid', 'ali', '--------', 'khan', '28-Nov-2020', 'hyd', 'Male', 'father', 'jobless', '9897789', '987979', 'hyderabad', 'null', '', '28-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', 'good'),
(23, 'Kamran', 'shah', '----', 'baloch', '28-Nov-2020', 'MPK', 'Male', 'Father', 'shop', '09808', '09809', 'hyd', 'null', '', '28-Nov-2020', 0, 'null', 'null', 'null', 1, 0, 0, 'null', 'hello');

-- --------------------------------------------------------

--
-- Table structure for table `student_class`
--

CREATE TABLE `student_class` (
  `class_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `roll_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_class`
--

INSERT INTO `student_class` (`class_id`, `scheme_id`, `roll_no`, `status`, `remarks`) VALUES
(1, 1, 1, 1, 'null'),
(1, 1, 7, 1, 'null'),
(1, 1, 9, 1, 'null'),
(12, 4, 3, 1, 'null'),
(12, 4, 8, 1, 'null'),
(12, 5, 2, 1, 'null'),
(12, 5, 10, 1, 'null'),
(2, 2, 15, 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `student_term`
--

CREATE TABLE `student_term` (
  `class_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `roll_no` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `student_term`
--

INSERT INTO `student_term` (`class_id`, `scheme_id`, `term`, `roll_no`, `status`, `remarks`) VALUES
(1, 1, 1, 1, 1, 'null'),
(1, 1, 1, 7, 1, 'null'),
(1, 1, 1, 9, 1, 'null'),
(1, 1, 2, 7, 1, 'null'),
(12, 4, 2, 8, 1, 'null'),
(12, 5, 1, 2, 1, 'null'),
(12, 4, 1, 3, 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `subject_result`
--

CREATE TABLE `subject_result` (
  `marksheet_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `exam_list` int(10) DEFAULT NULL,
  `roll_no` int(10) DEFAULT NULL,
  `course_no` int(10) DEFAULT NULL,
  `obtained_marks` int(10) DEFAULT NULL,
  `max_marks` int(3) DEFAULT NULL,
  `min_marks` int(2) DEFAULT NULL,
  `percentage` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` varchar(10) DEFAULT NULL,
  `teacher_name` varchar(10) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `surname` varchar(10) DEFAULT NULL,
  `birth_date` varchar(10) DEFAULT NULL,
  `cnic` varchar(10) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `address` varchar(10) DEFAULT NULL,
  `join_date` varchar(10) DEFAULT NULL,
  `remarks` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `term`
--

CREATE TABLE `term` (
  `class_id` int(10) DEFAULT NULL,
  `scheme_id` int(10) DEFAULT NULL,
  `term` int(10) DEFAULT NULL,
  `term_month` int(2) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `remarks` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `term`
--

INSERT INTO `term` (`class_id`, `scheme_id`, `term`, `term_month`, `status`, `remarks`) VALUES
(1, 1, 1, 3, 1, ''),
(1, 1, 2, 11, 1, ''),
(12, 4, 1, 1, 1, ''),
(12, 4, 2, 10, 1, ''),
(12, 5, 1, 1, 1, '');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
