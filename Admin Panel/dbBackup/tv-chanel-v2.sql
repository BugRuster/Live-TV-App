-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 11, 2022 at 05:21 AM
-- Server version: 8.0.30-0ubuntu0.20.04.2
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tv-chanel-v2`
--

-- --------------------------------------------------------

--
-- Table structure for table `addvertises`
--

CREATE TABLE `addvertises` (
  `id` bigint UNSIGNED NOT NULL,
  `admob_inter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `admob_banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `admob_native` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `admob_appopen` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `admob_reward` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fb_inter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fb_banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fb_native` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fb_reward` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `appnex_inter` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `appnex_banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `startapp_app_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `iron_source_ads` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `app_lovin` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `add_count` int DEFAULT NULL,
  `add_count_native` int DEFAULT NULL,
  `addtype_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `addvertises`
--

INSERT INTO `addvertises` (`id`, `admob_inter`, `admob_banner`, `admob_native`, `admob_appopen`, `admob_reward`, `fb_inter`, `fb_banner`, `fb_native`, `fb_reward`, `appnex_inter`, `appnex_banner`, `startapp_app_id`, `iron_source_ads`, `app_lovin`, `add_count`, `add_count_native`, `addtype_id`, `created_at`, `updated_at`) VALUES
(1, 'ca-app-pub-3940256099942544/1033173712', 'ca-app-pub-3940256099942544/6300978111', 'ca-app-pub-3940256099942544/2247696110', 'ca-app-pub-3940256099942544/3419835294', 'ca-app-pub-/5224354917', 'IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID', 'IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID', 'IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID', 'IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID', '1a018115-4916-4955-80b0-9985e458a6c2', '4371929', '4324sfddfsd', '423324234324re', '444444', 2, 3, 1, NULL, '2021-10-09 08:24:31');

-- --------------------------------------------------------

--
-- Table structure for table `add_types`
--

CREATE TABLE `add_types` (
  `id` bigint UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `add_types`
--

INSERT INTO `add_types` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'Admob ', NULL, NULL),
(2, 'Facebook ', NULL, NULL),
(3, 'App nex', NULL, NULL),
(4, 'Startapp', NULL, NULL),
(5, 'Unity Add', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` bigint UNSIGNED NOT NULL,
  `cat_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_type` int NOT NULL,
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `cat_name`, `image`, `category_type`, `pass`, `created_at`, `updated_at`) VALUES
(7, 'Live', 'images/category/1633328544.jpg', 1, NULL, '2021-06-14 06:13:03', '2021-10-04 06:22:24'),
(8, 'YouTube', 'images/category/1633327881.jpg', 1, '123', '2021-06-14 06:13:22', '2021-10-04 06:11:21'),
(13, 'Cricket', 'images/category/1633327641.jpg', 1, NULL, '2021-06-15 07:28:09', '2021-10-04 06:07:21'),
(15, 'Football', 'images/category/1633327455.jpg', 0, 'hello', '2021-06-15 11:26:46', '2021-10-04 06:04:15'),
(23, 'Music Videos', 'images/category/1632982891.png', 1, NULL, '2021-06-15 12:49:03', '2021-09-30 06:21:31'),
(24, 'Cinema', 'images/category/1633327755.jpg', 1, NULL, '2021-06-15 12:49:15', '2021-10-04 06:09:15'),
(25, 'Animation', 'images/category/1632982962.png', 1, NULL, '2021-06-15 12:49:27', '2021-09-30 06:22:42'),
(26, 'Movies', 'images/category/1632982973.png', 1, NULL, '2021-06-15 12:49:40', '2021-09-30 06:22:53'),
(27, 'News', 'images/category/1633330560.jpg', 1, NULL, '2021-06-15 12:49:50', '2021-10-04 06:56:00'),
(29, 'Sports', 'images/category/1633330683.jpg', 1, NULL, '2021-06-21 10:52:04', '2021-10-04 06:58:03'),
(30, 'Radio', 'images/category/1665394973.jpg', 1, NULL, '2022-05-17 06:05:20', '2022-10-10 09:42:53'),
(31, 'VIP', 'images/category/1665394941.jpg', 0, '555', '2022-09-07 08:44:15', '2022-10-10 09:42:21');

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE `devices` (
  `id` bigint UNSIGNED NOT NULL,
  `packeg_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `device_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`id`, `packeg_name`, `device_token`, `created_at`, `updated_at`) VALUES
(19, 'Sun Jun 27 12:17:27 GMT+06:00 2021', 'e8VXOM6OT5aDLWX7LWFy0D:APA91bG6bo7u5ZfqGt34LPs7VDwTEfxZs7pdaEQ3MM3hiNlp9IIOqfTqZR4k7By2MBSARA_D_a0kpKrBUFpOAoX_ddwtBqKc-P9Dn-QIgp1oC2dCzIn5Qhn6ByWRjjYGZvmtf5q84Krl', '2021-06-27 06:17:28', '2021-06-27 06:17:28'),
(20, 'Sun Jun 27 12:37:22 GMT+06:00 2021', 'dTixUT7ORnWCovgHEoGC9B:APA91bFmQSmVK4hCINRjJccePmqawHE-9MphC_U_-pcwHv1jXDxADUOxqcjVwzOP41vIqgul5jYFXgpSrST1XEiDFFCNcKCvS1bHOXwjU1ucWwmOPa28PXm2PR4uQiDvqEmSKE9jSvrW', '2021-06-27 06:37:23', '2021-06-27 06:37:23'),
(21, 'Sun Jun 27 13:18:31 GMT+06:00 2021', 'dANdlKAuTpq__Q0RAyWuy2:APA91bE3gj9zMCZDeO6w4Vc6z6hcor0478p5eL2RUNviXaLJwQvT0Zp0E61r9IjTZEzx-lejiKxA58A79zd_3fQ6UzNeqKjK7nt8W_yeAjFS8-rNHgZMXgXUc0_MrAKS8OL9zwTBZezd', '2021-06-27 07:18:35', '2021-06-27 07:18:35'),
(22, 'Sun Jun 27 13:31:04 GMT+06:00 2021', 'f6sh80tzQnKwZOdaIM_j3o:APA91bH1wXHuGJIQsVPJBc5IDp6myXtJpXp2oispOn0q1xvfdMt95MARsN7VClXxu9DT7IZHaOlK9NIbXylaYkBraElSjkgI9bWFo5zIfaihMvteA5RGPEUP4s2EhHMMytJ_9jzcVd5B', '2021-06-27 07:31:05', '2021-06-27 07:31:05'),
(23, 'Sun Jun 27 13:35:59 GMT+06:00 2021', 'eQKfVqd8TkuR__3PmCC-7m:APA91bFmLsjuw7CUAX3w6h4XUUP24SkyeQbvR9utGEAr1yu1vO-x8rT79-NwE9bgs2umbBBuAFml50zP97XL1_8pP8xVxECGtECUUtkUFDfSadrdPftz9Z4u_3yqHQ3EXhlXZbjLIv0x', '2021-06-27 07:36:03', '2021-06-27 07:36:03'),
(24, 'Sun Jun 27 09:42:02 GMT+02:00 2021', 'eTnI9JLeS5K2VTJmo6dbUZ:APA91bEtGCRmcHV6V7cN9EGB_JXxhOJrBC-ukJ61psDRORw2v372dOjX5hx8F--Dd8PS5X_AqDuxghO0kycWrE3Cy1mpG20ryhC_qvS31Cfs2N6MjxxHc2n03zfIjyK1Lin8Is5FTANO', '2021-06-27 07:42:02', '2021-06-27 07:42:02'),
(25, 'Sun Jun 27 10:07:28 GMT+02:00 2021', 'eGCh7mlGR5i_vxO9ThVE4w:APA91bFkwHYjmGrJHASTfrdBA7TTswtlmsrzS2mFUL38FsJhaKzrbupekq8Z4DeyfKivoxcEJXOrKGxZTSHeMgn454GjK7obqf1SQ5Gi0sbw4HVztlnr7Y1GTMaRnqEGmhBMvFdkzDWv', '2021-06-27 08:07:24', '2021-06-27 08:07:24'),
(26, 'Sun Jun 27 12:25:06 GMT+02:00 2021', 'fwwUkYf0T2-E-Emk7cqzJk:APA91bHbSpFcsAnAZ06K5w9Lr2mW5KsBjOjwBunKrGrV9VLjFIRpx4Avq-2_0kyFeG8aSVdSRc50DpR91OiAvxNInELMumBlbue82qXuZpyS1DEUB_s9KT9D7n-dUFP7Qi_G64IWKRXu', '2021-06-27 10:25:08', '2021-06-27 10:25:08'),
(27, 'Sun Jun 27 12:27:16 GMT+02:00 2021', 'eCTXuJRfQnqQFcsqcVrHGx:APA91bHSg3SPGbemJt120azEDdaoT_R6nVcxIEyDvUBRtsSJrCyXCkGDusGBOysIsSTZ0S1GTRiNtWYH2smF8xUuwzFsmMBhpaY_a9bS6I_ECzVVJbzFlaVo_Q9B2TwA8xa0hWGJ_1de', '2021-06-27 10:27:16', '2021-06-27 10:27:16'),
(28, 'Sun Jun 27 12:34:21 GMT+02:00 2021', 'eOnRVANLTT20I4nodkoIzU:APA91bGzTPMO8bQ5R0bUKzK4yUHUimEFebSMfEIxM5wLCKh3KpbFnXfem_-6OAaQWLzqRu8xU4eRdaDrgOTBXlbk4g7B3_JMxVpe2cpT0qY3wJPchVrCxiR0m4kF-N6mD1z14_9HBA2i', '2021-06-27 10:34:22', '2021-06-27 10:34:22'),
(29, 'Sun Jun 27 12:39:17 GMT+02:00 2021', 'fdC-AzzSTnWwd_fZwL1xM1:APA91bHmyUJxXcYl7a3ggqtd8frlAZcanqw-f8uQ2qYrrV7ypgRrQCaaKeFSixoxTJW5f1Lcqk21d2r_hvrGd8ODnp9tymTC9d7MY-bVJLgHcQ6us42d_I7fE2XFqhujzapWKDlCuAbi', '2021-06-27 10:39:16', '2021-06-27 10:39:16'),
(30, 'Sun Jun 27 12:39:22 GMT+02:00 2021', 'e_Le8n8rS1iBgCgbVkT7yI:APA91bFU8UIj6WuL9a1nZaH4bdVusmrLR6VxZXKaTRIW1VEFycNRK75If-X2u3zWh-wKSqn2lc-aTc_yTzepmsfE-r_zaMB-JBIeLjwv6FItn9vlfbBSVC6PxQfpJWa7yJxCbKSkUzvv', '2021-06-27 10:39:23', '2021-06-27 10:39:23'),
(31, 'Sun Jun 27 16:49:12 GMT+06:00 2021', 'cBjuPMX7TuyNpdJktj9kzX:APA91bEVYLuwUbYG_sNRxoDJHlKqi-zpXdKj7C9Mt2GURzvqQK-GBrxE_zV8hv32tMHLJizcOcsCQGxSC_wyQ8t4Sg3bBXxT_V81z2NH098MvfQ8F3jA2Sd-qriNs8tPCG2E-Ke2QUa-', '2021-06-27 10:49:13', '2021-06-27 10:49:13'),
(32, 'Sun Jun 27 12:50:21 GMT+02:00 2021', 'ekUk1OUJQouS2HIr1hfMh-:APA91bGxFxinmwP72MRmXu4Ap4au9PwtQPPH6grMMhdxRdRbSq1SrlryLubWfivC65Rjze3cEUMwjrwWYhZEkoMKCarVXXqj004RwU5iO9jiTAgkVQwczXR67SLLCNM0j-ACB920JLRU', '2021-06-27 10:50:19', '2021-06-27 10:50:19'),
(33, 'Mon Jun 28 12:08:36 GMT+06:00 2021', 'dybkoCy3T-2RHis8HxQaEy:APA91bEA-dBTJqbkINs8jbb_fPNZm_XpJIr1lpnmyOGIM4fDh2GwAITROu4Y4Coq-_cJOjgGc0uRGpaWc19KibwO5ZTvFkt_h2I3Cs-V9oXKZKCSoSnBwEZ2iaheb-wrR3BdSZQnhijN', '2021-06-28 06:08:38', '2021-06-28 06:08:38'),
(34, 'Mon Jun 28 10:07:50 GMT+02:00 2021', 'fcqb9LurT6mkbBLwnqibwW:APA91bGg4Bqs50VK84fUwmunkH_l6y_hrlnBmMk8NHGYM3JF2XXWZG7iaxIAUfPdfEZpxs8y7sSFiatcGAKZ_ie2lBNVb4efFWJloei8fd4ixUxkPL5VFrDcHzY2hVDhUNWj3nzwnEKy', '2021-06-28 08:07:52', '2021-06-28 08:07:52'),
(35, 'Mon Jun 28 11:07:51 GMT+02:00 2021', 'fZtsgmFwQvKpgNBJAJMjDX:APA91bEbUgRBASJSIx8HO-Om-2CxX15wPNJyf3D0IiLZGCOFw48a0Tp_pdKEXJw_Pr9_kNP-Sz9M8G3m7XLoyNN8SA4gXUIafnjSGQRE9FwmoOgmF9Z4LH6Ne9XD_WxgfAXgVcH7cJa-', '2021-06-28 09:07:50', '2021-06-28 09:07:50'),
(36, 'Mon Jun 28 13:46:39 GMT+02:00 2021', 'f_6DGV-yT-KMZEMOiOIhqx:APA91bE4KNJfZ13h4iTQu04bHkHtt-UynKUlCw-l5UtLEHSyOc-k6QJKsvpqG-8srnOVbNorhypiRqBsDLFbvxBGQyxnjWhtrQEtXhqFeCfqKYGX9OJ3bg5yGMo3jxMumRLrf73NW8VQ', '2021-06-28 11:47:12', '2021-06-28 11:47:12'),
(37, 'Mon Jun 28 16:39:25 GMT+02:00 2021', 'ctIfLZOkRaq1I_OzAG8_P3:APA91bFaKEe7zPX4OV1SbO5e-vM1pFwnsnKdtom1365O1tcmRtJ5MUgAFz2ZcmVLGvIEl0VVX3cSMT0OitFqW5LPuxoABxLS_HiC1tP88_UgQn3jRNy1QHynB0pVedojgyhq_oErgnz3', '2021-06-28 14:39:26', '2021-06-28 14:39:26'),
(38, 'Tue Jun 29 16:36:44 GMT+06:00 2021', 'cJj8Xy9iTg-0Y7uGbcgnWZ:APA91bFnbcymxRdMW3XcQqR5GFOkyG3jONNKiWCAG23o9deMPEGBYhVhG58f_tvoOTjt7mqPbMKh8DfB70tE0I4qLcA9wMslTyB_LAIEJVXlZF_vEWqaYtyLHtwC0rX96Xhs2P8h5R2Q', '2021-06-29 10:36:46', '2021-06-29 10:36:46'),
(39, 'Tue Jun 29 18:57:40 GMT+02:00 2021', 'c2uQ9r_GS6irQ1bK-tkZzW:APA91bHMsHMmSAN6sIoeDhgE37Yi4ClCyuCqTlFhAiNA2qf0vgi_AcXApD-SRndQmjXED3SwZMMSZZOcY3tFQlUmxMmZ1cJDeK62VAki6RP3JPuMZIaFFWbUv3BlhLoOYk4EvdP2s2om', '2021-06-29 16:57:41', '2021-06-29 16:57:41'),
(40, 'Wed Jun 30 14:03:34 GMT+06:00 2021', 'cHzd0OC7RBuhDjD9UpCgot:APA91bEiutxJjLPGTzsl2STBcY7p-zEjUXGDFjE2yVWH_FZdjpvkVktKKSJzoGZHCRduyXjt1ju8u0d0crZIHytmkgUQ8AsDHTimmt0nH64brMR12eKDC4ZHv_G-bujfhCmB4r9If5Hk', '2021-06-30 08:03:35', '2021-06-30 08:03:35'),
(41, 'Wed Jun 30 14:14:24 GMT+06:00 2021', 'e0wbtNyDRUiE95GEQA_Rz1:APA91bEv_RcYvt-EIVbSJbtnkd5nT2_87nno0GfsKIQjLa6MtXEiQX6hO104KKaHNS3Ha2wGqCqMjIiJbvK565UwPkEhMZe8PEfsyfPCPgKoTiuzkYdUrkJNFwGYtO_7P-f0s-PWrLG0', '2021-06-30 08:14:25', '2021-06-30 08:14:25'),
(42, 'Wed Jun 30 12:20:40 GMT+02:00 2021', 'eXfApB2ZQJ2jwj3Idyb9Mq:APA91bEdKNHZSQyd5RmmECYHONcE-XnMYJr-39xCq30UHUeLZgXEpU1iAoGN4Aq1bZDUAMLIKs6CVKQYOS0mfnvFq0wnmMBOLLpILMMFDmstABsc5hwHyfbhEl8y31Y0F3eDJI8GP396', '2021-06-30 10:20:41', '2021-06-30 10:20:41'),
(43, 'Wed Jun 30 12:52:02 GMT+02:00 2021', 'e_Le8n8rS1iBgCgbVkT7yI:APA91bF0t8qSY3oKXEfpgdr8EpTt1VzawMxBgvxJkhaLV9f2WycZqnZgh4_Ijtn3Pdr3U5wnGQyi5e4m_EZxok7tHtTp0YhHVqs9knd8rtUowpknzBAQZbXpVjdynS6Im9BWmJ5qhRl1', '2021-06-30 10:52:05', '2021-06-30 10:52:05'),
(44, 'Wed Jun 30 12:54:55 GMT+02:00 2021', 'evwvHMg8Rzub9lh262vp-U:APA91bGolDmBGfSb-rgf5X-OiOnkxGPoIn7Fz8AZHSLwuAk7NEiTXUp4--VgbmmfiEdD6IHn47nD6wWZDDp4Fd_Z00FpMQRoWlp436wEFSIBe-zDEt2H_Fvbspsd1VDIsHQ7rpd3v6ty', '2021-06-30 10:54:55', '2021-06-30 10:54:55'),
(45, 'Wed Jun 30 18:17:18 GMT+06:00 2021', 'eQKfVqd8TkuR__3PmCC-7m:APA91bEbHs8G9QoJR8K2pI3RLvLLJhLxCahyS3of48_GCjLxIzS8wSq5MOh4CxAy4mKh0bRqSnimql9BfHE_-EIdAwjBLlV6i9HklBX9IRQlw7eP5ftWbAtnkygrvjrraMBGaXXLEKNM', '2021-06-30 12:17:25', '2021-06-30 12:17:25'),
(46, 'Thu Jul 01 13:32:47 GMT+02:00 2021', 'f6uzxYaSQyirVhvEWlr0gu:APA91bEx5cWc1wCLEXX_9cA-1uoCPORU-dixWn-eEucCgH97yN5RCg0u0GltW1o-OSGhUTXNHLb25HMQDzZEHd58klfk8uvV2jdldslcoCH4Yrtbl44SRDLnNGn33Y8NRDYWr07hwykf', '2021-07-01 11:32:49', '2021-07-01 11:32:49'),
(47, 'Fri Jul 02 15:08:07 GMT+02:00 2021', 'dXz21VosS-On5gRXdna5DI:APA91bGRj01AXcnXzSw9X2N3GdeNTzmLTQIsQGyKvACZvWNNSiA81eN0n71wAIYBco7ZpCXE-KWOFtm2oV7Eo4rqE2Uf0d4hgru3yG9PPWJFJpAb4f0uLyBMSyGy-XbLUZlXXBVz5xAc', '2021-07-02 13:08:07', '2021-07-02 13:08:07'),
(48, 'Fri Jul 02 15:19:12 GMT+02:00 2021', 'e_Le8n8rS1iBgCgbVkT7yI:APA91bHSe9NGboLbn7HJf2R0AuHO2kCwbqPd1r-mtRoeIQ8E8aC231Ivlh97oqIIpzxprNpVgLYEx-iXh3WbydaXZDV4NmWXsxJX9yGBulUTJa9iFw5JZi6hdyQjWzc32ikWZOblp93m', '2021-07-02 13:19:14', '2021-07-02 13:19:14'),
(49, 'Fri Jul 02 15:25:30 GMT+02:00 2021', 'evwvHMg8Rzub9lh262vp-U:APA91bFCt4lGwDxOBM95j4WiL68DePZWZDSsPFaJ1vPYQkEy_XqfFdXoaKWkoImlS-NKEQzyVKHafd8hNz9bwInYFX8nBlFv7MDNWw3oTRb0wPYfJb5_FgXf928wf_YWO9-mHlYCPG_e', '2021-07-02 13:25:24', '2021-07-02 13:25:24'),
(50, 'Sat Jul 03 01:52:08 GMT+02:00 2021', 'cRoRPpgaSYG65-JItBP0o0:APA91bFujzuk9GD5XuQfMuZdGfDUBkueoExSp6eTqmX29sIoNi4hUN3WdRuBe1j53r5wScdPak7IVvqdhDx2kmyo9KyyWzCkY4AmVQH8SNW19LbtipjAu4S_8n_yLc9M-Ii_hj4Wt7oX', '2021-07-02 23:52:08', '2021-07-02 23:52:08'),
(51, 'Sat Jul 03 11:53:46 GMT+02:00 2021', 'dVEELllyROG4FrRjkymaiu:APA91bE1ScLaNrXGObuwM_jQhm_sqhum4p-ApeI9RLX1xbK4l8EnqEVFuBF_BrlZ0CT6jalj5TFAMYMHgwu51BbEovxrhQ7YlMqyrJ-MrDKUvLt6N_nWbsVHr2ND9QIToTQNZ21HwpjR', '2021-07-03 09:53:47', '2021-07-03 09:53:47'),
(52, 'Tue Jul 06 16:27:13 GMT+05:30 2021', 'fb8AqdpiQN-osQxIWLsr7v:APA91bF9Jy38F-yIzK-huumy91ruKyOohogRP14O7pvFZHl83A58tqCgraG-1FByHQRU9BX1I431yFEclrf6ZvjlFZmgyhtEFwVgvRTBy6nxRiplbjzzeOEvsWFhkNIC2UWP1ObDi6qI', '2021-07-06 10:57:15', '2021-07-06 10:57:15'),
(53, 'Tue Jul 06 18:25:19 GMT+06:00 2021', 'eQKfVqd8TkuR__3PmCC-7m:APA91bEgQvoI8yEVhsH5Ucvb6RezObih1BZSWi0zxfPnh6QdUvrFLJNXn8mmWj4odm8T535p7Mx7CMG_ZDzH2_5umLhw6TFr3DX9-nbOOsxEeBMK62i-o9zRPtoGlpFQdIxJnX03u0IQ', '2021-07-06 12:25:21', '2021-07-06 12:25:21'),
(54, 'Tue Jul 06 17:37:03 GMT+05:00 2021', 'ePYqqwNAS4iuoM2pviTuip:APA91bGJIytNW11G4p4c_GxiYPlJvs-k_Y0IFkwfpY1duNjRZ5l_Mk4UHtL1wLC1I6uQ9d3sonyzd6DpuzqkYdBTCKSAjpEDtsk3qUGTn3oBvIjcG5j2WcO6Mz0hnglpi29EGP5ulVBR', '2021-07-06 12:37:05', '2021-07-06 12:37:05'),
(55, 'Wed Jul 07 11:01:29 GMT+05:30 2021', 'd-SDgGhxR_arRSRpdB-xVT:APA91bGSDoZgbFfVnq8KHcXuQipEbiUHt35pFWpKAbRLveXkBPDu_NvbiYNsnF2rFALAlV70SsZJ_3rkptmAu2zLFdqP7jb3igkZF5HEII8l86AfdKXrIQG13rog-y7e48l1NZe-xqVj', '2021-07-07 05:31:29', '2021-07-07 05:31:29'),
(56, 'Sun Jul 11 20:36:32 GMT+06:00 2021', 'eQKfVqd8TkuR__3PmCC-7m:APA91bHIRU_MCM7R00o1I5IUTYDGAJ_YJ7G30Q2rkoObykZ4n8PM724u6znjxxKGhuiHKsVi3Srmqv5Q6QSpTGIspkGssyFKLVmu68NO6Xl1lkpt7H600rOw9e6X1lapeUH4S20WPdxQ', '2021-07-11 14:36:33', '2021-07-11 14:36:33'),
(57, 'Sun Jul 11 16:47:39 GMT+02:00 2021', 'f2mbTI5lReefzayMoSzpZV:APA91bGtSNGipOweqXy7mdgKn4CdNiRh9VELoSJ_Jzq222sqGcDvXv3Vl7fCBxHLdDO91IrezKXqiFYqCFIPKtApdDmNILQzBk7F4c0XHJwZ3PzlBNCqC63h-cumULA1vfXp_X323NXn', '2021-07-11 14:47:41', '2021-07-11 14:47:41'),
(58, 'Wed Jul 14 11:16:42 GMT+06:00 2021', 'canawGeaRxO9xEaYrRocg0:APA91bGPGgxm9VTZdC70SMUrIFSge8BWz11Wz8bMV2DVAFtLzN0xK9YgTbf-N9h4RkQXhfpyuKvi2_g0Z8eXCjuKgJ6i_cln1yvVrn1AEseqgQ2EMfgbluJhrUJPfEP2OWcvl2jN3dyE', '2021-07-14 05:16:43', '2021-07-14 05:16:43'),
(59, 'Wed Jul 14 12:59:50 GMT+06:00 2021', 'dloJkC_pTIOlEaZ3eufuZf:APA91bGHHA7sKhiPSPkYjND8puyxOgIQmI8PZD5ZfbYU58qq0qFvrUIp2S5z8ZS8M6MHMpxlCTH5ftxmfi0ETSpZPvdyn3X6ks7DwdOOdB7n1wWXfFLIwkGD2H8z3744BypPHMx91zde', '2021-07-14 06:59:50', '2021-07-14 06:59:50'),
(60, 'Tue Sep 07 15:13:56 GMT+02:00 2021', 'cgdSji6NSpayqpNbpVPJAY:APA91bENaBJUg2ewJrP828KevQBKJ_rifVvSi4n5072ORBjCshfDRKfHWIQUCisZUlTjdRsgmHc9DS_ZriHct1SeC-7lOgw4xdHmojkMZa-mkUjiqUOCXc0rlY_vdK8yoRUk7toBQwA2', '2021-09-07 13:13:58', '2021-09-07 13:13:58'),
(61, 'Tue Sep 07 19:16:43 GMT+06:00 2021', 'dbpJYuevT5aEn8K-02b8yN:APA91bFXK4jrCvWFS6CmZQho_Oj1Uxj-7Gls8bM5UrRystVDbRERl200eol8I-Ozd1iT8WuhZeMk5jzJf7GOZvcah-IA8uEWbtEAJRllQ2WYOcuwVHH3StMqvd-azqbHDq8QMPJVyZTG', '2021-09-07 13:16:45', '2021-09-07 13:16:45'),
(62, 'Sat Sep 11 17:15:42 GMT+06:00 2021', 'eYwKNQCQQC60NI8Z0P_F86:APA91bFNKrcLU7lXuulcY4cu-dHlia4vgplXbI09vnL_-tTNwm7V0knHkYSD4GyK3-B8MI9theQjb704am9g0aQvvGS5Npr6z66OyUwvlNLtecrrxzJQOuJsjtj-gmjtwlYpnl3hmVS1', '2021-09-11 11:15:42', '2021-09-11 11:15:42'),
(63, 'Sat Sep 11 18:15:06 GMT+02:00 2021', 'e3dDWUjRRQKrNv8KcHIyNt:APA91bHW3tYT4SNVEsUy23HB7UCY90nCKhndJhcV9rSHfPs5qK01bGVhkykZK67s6juNjei1Lr9-SEwIXrZZE78nV1OCKByjlHSxOsZzJ2nXcZ4WkjqokTyNr508LWmExWrT8Ki1OSKm', '2021-09-11 16:15:06', '2021-09-11 16:15:06'),
(64, 'Thu Sep 16 13:34:44 GMT+02:00 2021', 'ebiwMMm1RvyktmalFmsKic:APA91bGqtcYcWUtdUrGjbBa_1Roc8TY7Z2U4DXewMU2MGKE-yr9PUyJGIeiTMx3K1ko7cbpAiuM40gr2wnqiMsIItjJtYAhErGfmq0qoYyYUnETJxM6I7YWrkVVwCFtqdeIhJqwzYb-w', '2021-09-16 11:34:45', '2021-09-16 11:34:45'),
(65, 'Thu Sep 16 20:26:08 GMT+02:00 2021', 'dhgw_M_CQAygBVyZmAKJsQ:APA91bHvEQz0YxP8QUTz7FZbdN8PhLBv-gjYKvbC8fMQvDMZS1406RLkFzWdvK67bVW-auOq9Ms5K0QjlNAVVCF9723qJv5Pd48xRS4a9RdCadKnvY-memzTBgugH6eZVQODvp2WWbH1', '2021-09-16 18:26:09', '2021-09-16 18:26:09'),
(66, 'Sun Sep 19 11:59:05 GMT+06:00 2021', 'c14IKmEdQPCvAWdg0kAOnR:APA91bFSN1GPNeyRbMrSPKcoj69andW-pdl2XmpRlGYt-nxm7PViG9V44kAdo1GClrDtpa3Caj8UnrdrDukDRC3_LFphid_oxnYvSjeLO6Fv8IPKa4T26N-EiIEjzklHs7ZQqPMWAbde', '2021-09-19 05:59:06', '2021-09-19 05:59:06'),
(67, 'Sun Sep 19 19:45:56 GMT+06:00 2021', 'dqEiSWylTLG6h0vGHtM6PI:APA91bHEhgLg-RD4GjbHl1IzsVOuYTCpUGVs2QYOU1K5DORiz3lvz6y9nUlp4PvHXD7XNAJT16KUkweFTB0hNElym_rFFKe3Izqb7TtkvAjd6FgvWBWr1xX_Xbj-o3KzzizskIh5DBRT', '2021-09-19 13:45:59', '2021-09-19 13:45:59'),
(68, 'Sun Sep 19 16:47:40 GMT+02:00 2021', 'fXHiV_awTXmOkBWjUUaYTJ:APA91bGaivaTnEBgLr4XLca8RcolCfWcG-sPMCU5PPAY1Led_QszZ055mN7DPMwg1mEcR9ZV0ztCDdn_Zw2MngXZ2ipVBVJ_vvmXqKxdIaSuXQWMlusLFSYT5G90gxinxxciy3ulVBKg', '2021-09-19 14:47:41', '2021-09-19 14:47:41'),
(69, 'Sun Sep 19 17:23:50 GMT+02:00 2021', 'e6kqUxuqR7itSbbl_7hPmx:APA91bEePxPMDYnvOZ3zibJwWugq3GatQ-MTKawKkMaODHeTzNREaf9tNaHSmxTUqeBs_2kOggUJB_NGIJT-wT7S3XYhxGJ4t68JQV11WNStLCRJyfQZ_-fWNITXgoR7keLzN1xwlrvM', '2021-09-19 15:23:51', '2021-09-19 15:23:51'),
(70, 'Mon Sep 20 05:20:08 GMT 2021', 'dwBqituZSaWMbTYibt_S-i:APA91bHpVlpfomb0QzZYCJz6DKx4TbJjqcj8RzTdklqnSiemMHNrdZAEs3FFJe9Dmfq3xdiyML8c8qWcauLN7GuY0M-J7nJjViPOXkk2ddSAgTPikylC1nPsdAkdToDHH6338MOKb1mx', '2021-09-20 05:20:08', '2021-09-20 05:20:08'),
(71, 'Mon Sep 20 15:47:00 GMT+02:00 2021', 'fuhoAXnvRPGPhxlEPpoG7a:APA91bHpnRwghiLIR-KIctVRJnQKmPhBvqQpm03L0mQu8H0MA5CwMNVtJpbzPpVqUFkY-KuKuLRlXvzD6DSgFoUJD5RtUa2w3_loVgpYFfKyldNLJOnnFQPOfAriRH_MJoce8vtN7MUn', '2021-09-20 13:47:02', '2021-09-20 13:47:02'),
(72, 'Mon Sep 20 19:39:32 GMT+02:00 2021', 'emdaeNJlSWeNivTB1f4CGk:APA91bE7djioYiuKgmzowEC6JtGzhd3RANKIGrLOyUrm3i6ruwIEsjVB9b9v3-stMCRUfGa1uad0OhlA67PcIZctAnwKpYH8CAz_AetVP0WM8Wd5O-ad9kG5H63TUpfiUwvP6fxgoe93', '2021-09-20 17:39:33', '2021-09-20 17:39:33'),
(73, 'Tue Sep 21 13:14:31 GMT+06:00 2021', 'cJRV_tqUTNW85P8qYeS2g7:APA91bExbn_s_UyaX5lW61N3YSISXinsC28Pi7cEQ2Idsg53vkc5YXVzPrWVHWKreVYopQ6pUx_Q3qotK3d5EWF3zseQl95WcCdD1cPqzshVx0yU_I68jLBg52ku9TcrhXVfu0A0qtj7', '2021-09-21 07:14:34', '2021-09-21 07:14:34'),
(74, 'Tue Sep 21 03:42:00 CST 2021', 'dwCrPdJfSsSHa8lG8krQWk:APA91bGrW03-RUMvYBTptoVFRjeNb6HEs1dseONOvSJKLLhq2sRY2_NUe-geyX3LV_K_6wcGuP7sNqCM8aD8B7uo-WRMwdxFhRKhmiOSr8VmKhwmTcJShFcfZg6ge3note_OFSRtrtA_', '2021-09-21 09:42:03', '2021-09-21 09:42:03'),
(75, 'Wed Sep 22 16:16:08 GMT+02:00 2021', 'eWNas9RRQ1-5GmK3mbEmqc:APA91bFZLuu6vvFKCwLKtusa8gd-ZwY2ZxYvnYr71h2RJ6eLtq56XiWhT5-r9SMblBaquv3rUNIZkEpzdSOlJCm_GzfdpD3kMslQ_wyXYcT6Bipy9PZx9h9TvQq8kh7lLjiYzNrMHA-1', '2021-09-22 14:16:09', '2021-09-22 14:16:09'),
(76, 'Mon Sep 20 23:34:43 GMT 2021', 'fhOikKrkT0GQJg2CMVTWOF:APA91bGqcj0JsfaF9lM2OUw4RQjU9G3Qwrhgq6UKV-g5CSa9oTIgfhWz_O-wQEQvFb9hAny1-HLCslYV0jAWfgP96AWpmRVwffJ-jNCGxZ9YfWCa0vlgr0fFvuBCEVggU34HPuM3O74x', '2021-09-23 05:29:11', '2021-09-23 05:29:11'),
(77, 'Thu Sep 23 14:30:45 GMT+02:00 2021', 'dvQEd4LaQFaT50-fJ-mbKW:APA91bEhxak6okS481lAbAiU5WV2L3XUEfNLom8wxcgsx-zHw4GOjHgvPqsvw2iDiX79N5mf4aABN_KD3lVIV1tDUtRygoBFHoJSl8nphxa_p7Ka2cnRTPrXHcpohlJsCXynWNzFMWRM', '2021-09-23 12:30:45', '2021-09-23 12:30:45'),
(78, 'Thu Sep 23 18:48:03 GMT+06:00 2021', 'd85O9p7JSsigMjdrdogXb9:APA91bFqJRKc61hcSsv92puNgpML7Vxvo1J7PNMm8rYelEsAuWxQYFuzLPqTp-UUrpCB1Lw-jtDmVK5Yx0UG-sducUoMuKdMQ3ccmf0LAZbzPDBJfmIawjGDwSIXjP094gmGb3QtxAvY', '2021-09-23 12:48:03', '2021-09-23 12:48:03'),
(79, 'Thu Sep 23 19:12:20 GMT+06:00 2021', 'cZWJvs5PS_eGsidwPeoAXm:APA91bHTC-D84KTZoH5Il_68IGDDOO1M0jeRiFpsqgF9VGQ0WTgHg9W_ReX5Ybo18eJoL3NDL1eVDc4LMRIeGSTY4QYuu1mjGCsGDrkIP_H0QG8DxRN0NBBkqVLUmWO7qaynpQczpG1t', '2021-09-23 13:12:19', '2021-09-23 13:12:19'),
(80, 'Thu Sep 23 16:06:36 GMT+02:00 2021', 'eSsLotpoQn6XfOj7DbgPVL:APA91bGYlUnjV03LJji4Ae8czFH-ZNqXth6j-ClMFHAkZcZLDwMsG838JBoODkk2vfIyKCFAo9RE_qN2r8oRFsnkNYPy1lXLyaSfuXQt_6AA8biWasnWrPzKgPaBUZBXFwEYZZf6W8mx', '2021-09-23 14:06:35', '2021-09-23 14:06:35'),
(81, 'Mon Sep 27 14:39:42 GMT+06:00 2021', 'fmrBGJzfSx6AgO2-5MsSPJ:APA91bEg0isIkTn6dEyuB0qzwy_dg4calRsp620E98GRQwbRpzEhojj9SWImhvSVAW4cLXEUcjqPQmhbXe_0Sv-XcUPvB6rzC4-7sm6NgnKFH9CO0KnuxlR3yILTkxFtqPPMTGh6tvJv', '2021-09-27 08:39:43', '2021-09-27 08:39:43'),
(82, 'Tue Sep 21 01:16:23 GMT 2021', 'crlaOdSURFC-6afsZUESpZ:APA91bFwYyIxwXWG0ikEFQIAZmszkj3VAGc2sPeWINVx-EgbW5K3JkxrgpBL0ql3PlH21We7YAK6nZW_Pego_JehjJmFUxgYgku8RpGuhW6imki1YpdeWZYnhKuHi-b5JQ4Xw1JY2LlN', '2021-09-27 08:41:58', '2021-09-27 08:41:58'),
(83, 'Mon Sep 27 16:45:25 GMT+06:00 2021', 'cMyP92tPQau3n8VU5e7tC-:APA91bFJVEnRzrCVdKObKHFzGoj5hPuRHfxr8_pWTJjuAKAXm1cnkZ-YOaKSsriSQpi2u_NlyW_2hi5e_cDuadkX6bf7c4mLm4WVEStWppyj0mSiiGO-_EuFL7Poasea2JfolpAj22e1', '2021-09-27 10:45:26', '2021-09-27 10:45:26'),
(84, 'Mon Sep 27 12:55:42 GMT+02:00 2021', 'fBJw6ssnQjWI5QKkDdwQAs:APA91bEhFtveqvS0O4IVK6Zw6JEdu8G5ffLnSSoHU4NOv0fWKMq2t6kp6kwv1MAlaiTyK2--ZCCWfT2GuHbyc1iox-vkjzD_or8_I3P0bnq9Fzc0a526u0VMSmYA8MOFELWf9Iyz8jE_', '2021-09-27 10:55:43', '2021-09-27 10:55:43'),
(85, 'Mon Sep 27 19:09:55 GMT+02:00 2021', 'fkRrLSmST4Gq7nayW3cOmB:APA91bFpwcKKiuC8AexhBctqKp-tv7tnoMS8H8oshfXBTR6dSA40S8L5D5vGJlBgL_A7m_fCwCHLBNjH0KDcnIa1Gll2mclEWKK238S_SzDUEGLRvgnRtf3xkjsSzMzZ7m_vls3HRXDc', '2021-09-27 17:09:56', '2021-09-27 17:09:56'),
(86, 'Mon Sep 27 22:09:31 GMT+02:00 2021', 'cONl1AsgRGqLlP95HLXDs6:APA91bEN4qIXjVjteBEVnIPm_OMf-zcglvujtpPqGZGcQ2UWIc8adVNVhC2yqz4anUKPL2V3q_I8VfA104le_jdtN1A1Nn4BozBVXkGDGT9zd1LEPLzN_ivV11sFTvApubEBvoflggIw', '2021-09-27 20:09:31', '2021-09-27 20:09:31'),
(87, 'Tue Sep 28 13:22:14 GMT+06:00 2021', 'fPC9b81SRo-uYl4WpPsuK-:APA91bHa0osLLHn7ZR4DaDsVKx4WOz5Fj1Ld_AfNSf-Rt17CeiBviHyREcW5qkYuz03ZqqU8XHToS6PZ2JZ6yjqx7RCB8H8IWCMFSM-zodjhV1SHG7Bfgw_ydWl00Uyq95QxmgnMnw2u', '2021-09-28 07:22:14', '2021-09-28 07:22:14'),
(88, 'Tue Sep 21 02:05:38 GMT 2021', 'eTVXYLBuR0Gkz_q4XcZIcm:APA91bFCH99sijDOQ9pYKgU-hb-PCI6LVv1Ng44CUyGFTuqT65ufMQVcD538oU1ROnt_d4cA9NQjxVeOTe5oFrf8LuH2DslBJKwRRkINbo6WyeOsRrZTxH9Izd4Jyb9S3zx9-g482fNF', '2021-09-29 11:35:04', '2021-09-29 11:35:04'),
(89, 'Mon Sep 20 22:45:59 GMT 2021', 'fKBegowHQcmedEgcDWAkzN:APA91bF_PVvXbZc3kdXmdIV22czkaoFkjZK3TPV6vi6jlggtDObdO-OqcIdiED4TOcKRyo7PqKpt86ff2307wL1FiGtQOcRpQfzF3LK2MMe1M7emtgT3oTpAUb5CHK85WDGYFfh3LGb-', '2021-09-30 10:16:58', '2021-09-30 10:16:58'),
(90, 'Mon Sep 20 22:44:16 GMT 2021', 'dfBetugUQ5-TP6_sIu160r:APA91bHaEeNPZ0VGvi-yvD__DrEW72U7Rq6xtq4pgFh8b6l4hrWL6F99k3at8mYWmia1Pqpf2D9X0ngit5GL_LFshNx79kWHy-FJvHMqkL1lvVH3CyYTy51Fg3P6rK2tU9LXqyANtFWW', '2021-10-02 09:21:14', '2021-10-02 09:21:14'),
(91, 'Sat Oct 02 21:38:22 GMT+06:00 2021', 'cXWjXM9dSPaEZXbWd27TsR:APA91bHBeXfqrJzJgPavhPd9wm1goyTcyduk3l3I3rgdG2hasp2QAnG_mloJDYtUkX3sdFlqVZpIgIRWnUFGWfcoQCgtMxzkenYlR1j9T4Q4tlJFB_dcXQalwCDbEBUU1H-vHEUJK3HO', '2021-10-02 15:38:22', '2021-10-02 15:38:22'),
(92, 'Sun Oct 03 14:53:27 GMT+05:30 2021', 'cwIoCghaQyeuGS0XYz0ln7:APA91bGWUmIeQRvbxr7OWA_pybV_8wk2Wf26Dx7kez-05s-0_Q2_uCFDeBWjLMQ4uodDNn1ysUl7AY-Jjcr_5c5enDnXl0Gc-trJ9J4uUXXVWzGy_JHtRaaPd83wGfd8UeeJ8A8cREaG', '2021-10-03 09:23:23', '2021-10-03 09:23:23'),
(93, 'Mon Sep 20 22:49:16 GMT 2021', 'cj3yQ7gAQlmbcLZG9Ua7e9:APA91bE0NgmYTosOQdTWMalaGv2_6Ld7iQMw6I-_fBvpP2LUFRncLegSZaijEx0bOF24XU_VOR0WkdeXRQVZLz8eLYO0Y8SkSyyDiuy0VWyXrMJfSPRsbAkNCh836As8WrRZu7id3FGK', '2021-10-03 10:23:18', '2021-10-03 10:23:18'),
(94, 'Mon Oct 04 11:27:44 GMT+06:00 2021', 'cGcc2PGeSzakTxLeq0hC_T:APA91bHlXyi_HVG2QA2gPM5gN08OO3BfnjysNeQdAbH6YXekEmvg89UGOCdkbm7oO7j7BXRM8dekJyiy1-mffShcfB45bTjTSNsBhxDSlsFCWvQQiJY6oGTLpC1QMv_BBhwZ9YGk3qMP', '2021-10-04 05:27:44', '2021-10-04 05:27:44'),
(95, 'Mon Oct 04 12:45:38 GMT+06:00 2021', 'dIXj0fw4Sqam_YO0giiEDr:APA91bHuumXGLdGNnip6LzXtpqhwyRdUm_SyC3UCM0E6QjG6rHziJWEiJKc0vCiUw2JVhvADXqBYp1mmpSVk7lMFdORuXPyb5RrsEvC7e2aWqbQHwWBB-U0d3jwutIOCBjbGdjj-WBQ8', '2021-10-04 06:45:38', '2021-10-04 06:45:38'),
(96, 'Mon Oct 04 12:53:54 GMT+06:00 2021', 'fwx3muYzRdO22LY4Ygixtj:APA91bFRhMA7kihOqcXYjTF9pYV1Be81CGoJ0ajPkFWw0WGV6N5UXiLat71N8cXxTDTNnb1eWP6Vnfcppbrj43IzY65If0_EmlAeb_OTJLnvyFJ6HlBBu8grAl1PU416GAu7Ku4lbsSW', '2021-10-04 06:53:59', '2021-10-04 06:53:59'),
(97, 'Tue Sep 21 00:29:12 GMT 2021', 'd-dNU0l1QAC9_nuAxnJfHN:APA91bEZKUauFGjYtkU3JzhhUXxAZEDf-GcA39Vh0ppvvkWs1Fmc59jwBTWl0q4B51ncO3FNYrqNT3bAv1UwWvELExRjP3x_yQgpoUY5nCrU5oGqi8NIKrBHT9enF3JiL73Ha9t1lNxi', '2021-10-04 07:07:13', '2021-10-04 07:07:13'),
(98, 'Mon Sep 20 22:45:34 GMT 2021', 'fw60CcdXRcCbYTDF00BRSK:APA91bEVB9s_-bMXlcMO6Nkuw-W6CimfECoTCEwkzUxBxn7P-7ggFHwNNqJhQMrAdTtIr3AQ-AaNttQN3i6V5rtsHQUx_2ZKKbiCxzVE5iAyo6cvTneM4GMOMc6lBPZ2vjRXvJaiR_m-', '2021-10-05 11:52:13', '2021-10-05 11:52:13'),
(99, 'Sat Oct 09 14:19:29 GMT+06:00 2021', 'f3aH0AYvTv-3cWVZjHiuYz:APA91bGzphntiFZoP7R_IRXYG7C7RrKTFcGo-DiKK_ZDRON6_lofaybir2a7q0TeaJmJTwID3HiKJbzoKEelZQl16jXpJI2csimGKkXLupREThltP8KUpWlnvlUnHLvuyBSQtyK-6zuU', '2021-10-09 08:19:31', '2021-10-09 08:19:31'),
(100, 'Sat Oct 09 15:35:03 GMT+06:00 2021', 'c4eZz5maRraVobslt4mQ3j:APA91bH3P6QUAfCqh63YJ6qXdezHTtuZhEa0yeaHbjFfB7cHQ_ZL4PMDF48RsXj89aIWsG6tZZjJxe1nz3St6jgpLmAvlcKG-Jpabf6cYoTOXB4-5NKawaobrg2KLqXY0YOP31T-x6Qy', '2021-10-09 09:35:05', '2021-10-09 09:35:05'),
(101, 'Mon Sep 20 22:51:38 GMT 2021', 'cSUwdOYGTm-YE_hURWxBEq:APA91bGjSZxq47j0mNDcl0flI0q1g4ht8Th-kCUa7AqVSMl43Sgk11yvHxPwSe0cvT7-jpGDGZ5NZD_r3Iu9YeivzbVgBbxiAenGajD5o4AgOOEA4O1v8p3hyWLbfXaVwvhKpUoeHcDu', '2021-10-13 05:37:54', '2021-10-13 05:37:54');

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint UNSIGNED NOT NULL,
  `connection` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int UNSIGNED NOT NULL,
  `migration` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2021_06_06_091456_create_categories_table', 1),
(5, '2021_06_06_091516_create_products_table', 1),
(6, '2021_06_06_091539_create_sliders_table', 1),
(7, '2021_06_06_131018_create_notifications_table', 1),
(8, '2021_06_09_073620_create_roles_table', 1),
(9, '2021_06_14_083640_create_url_types_table', 1);

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint UNSIGNED NOT NULL,
  `title` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `title`, `message`, `url`, `image`, `created_at`, `updated_at`) VALUES
(3, 'dasd', 'dasdas', 'dasdas', 'https://images.unsplash.com/photo-1529778873920-4da4926a72c2?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8Y3V0ZSUyMGNhdHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80', '2021-06-28 07:17:08', '2021-06-28 07:17:08'),
(4, 'new title', 'ne message', 'dasdas', 'https://images.unsplash.com/photo-1552519507-da3b142c6e3d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Y2Fyc3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80', '2021-06-28 07:20:50', '2021-06-28 07:20:50'),
(6, 'link', 'redirekt', 'http://google.com', 'https://lagoscreativeenterpriseweek.org/plcew/wp-content/uploads/2019/08/google-apps-559x321.jpg', '2021-06-28 08:07:40', '2021-06-28 08:07:40'),
(7, 'Lajmi i Fundit!', 'Test Google', 'http://google.com', 'https://lagoscreativeenterpriseweek.org/plcew/wp-content/uploads/2019/08/google-apps-559x321.jpg', '2021-06-28 08:08:43', '2021-06-28 08:08:43'),
(8, 'Lajmi i Fundit!', 'Google Test', 'http://telegrafi.com', 'https://lagoscreativeenterpriseweek.org/plcew/wp-content/uploads/2019/08/google-apps-559x321.jpg', '2021-06-28 08:09:32', '2021-06-28 08:09:32'),
(10, 'Tsdeidgw', 'dgfdfgdnf', 'www.google.com', NULL, '2021-06-29 06:48:58', '2021-06-29 06:48:58'),
(11, 'retghtfjkl', 'gjkh,jmhnb', 'www.google.com', NULL, '2021-06-29 06:50:28', '2021-06-29 06:50:28'),
(12, '35etrdfghgsd', 'dgjnfxmc', 'www.google.com', NULL, '2021-06-29 06:52:42', '2021-06-29 06:52:42'),
(13, 'dog', 'xcvbnm', 'www.google.com', NULL, '2021-06-29 06:53:32', '2021-06-29 06:53:32'),
(14, 'afsdgfhgj', 'sdfghf', 'www.google.com', NULL, '2021-06-29 06:54:16', '2021-06-29 06:54:16'),
(15, 'sfddgf', 'sfgd', 'www.google.com', NULL, '2021-06-29 06:55:07', '2021-06-29 06:55:07'),
(16, 'sdfafgf', 'fsgafdg', 'www.google.com', NULL, '2021-06-29 06:55:29', '2021-06-29 06:55:29'),
(17, 'fdghgv', 'fdghjgk', 'www.google.com', NULL, '2021-06-29 06:56:52', '2021-06-29 06:56:52'),
(18, 'fsdghj', 'gdhfj', 'www.google.com', NULL, '2021-06-29 06:57:47', '2021-06-29 06:57:47'),
(19, 'wertyhujg', 'dgfshjk', 'www.google.com', NULL, '2021-06-29 06:58:38', '2021-06-29 06:58:38'),
(20, 'thrggjm', 'dgxfhcnmv', 'www.google.com', NULL, '2021-06-29 07:00:06', '2021-06-29 07:00:06'),
(21, 'hvbnm', 'Chmn', 'www.google.com', NULL, '2021-06-29 07:02:03', '2021-06-29 07:02:03'),
(22, 'ewfghj', 'sghdfx', 'www.google.com', NULL, '2021-06-29 07:02:26', '2021-06-29 07:02:26'),
(23, 'adfgh', 'sgfdhjg', 'www.google.com', NULL, '2021-06-29 07:06:35', '2021-06-29 07:06:35'),
(24, 'fdgs', 'dfsgd', 'www.google.com', NULL, '2021-06-29 07:07:29', '2021-06-29 07:07:29'),
(25, 'reghfj', 'rtdyfhgjk', 'www.google.com', NULL, '2021-06-29 07:10:39', '2021-06-29 07:10:39'),
(26, 'erghjk', 'hfjgkln', 'www.google.com', NULL, '2021-06-29 07:11:18', '2021-06-29 07:11:18'),
(27, 'defgfhj', 'dfghjhk', 'https://www.facebook.com', NULL, '2021-06-29 07:12:23', '2021-06-29 07:12:23'),
(28, 'wreghtj', 'reghfjkhfjg', 'https://vid-mates.com/abir/tv-chanel/public/notification/create', NULL, '2021-06-29 07:39:37', '2021-06-29 07:39:37'),
(29, 'ewqe', 'rewrew', 'www.google.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdHUCgAY8GApM6Bc6QSuQoBdZIrw-ZsRz73Q&usqp=CAU', '2021-06-29 08:33:04', '2021-06-29 08:33:04'),
(30, 'Lajmi i Fundit!', 'Filan Fisteki', 'https://iptv.city', 'https://billboard-advertisements.weebly.com/uploads/3/1/4/1/31417489/9764509.jpg', '2021-06-29 09:35:40', '2021-06-29 09:35:40'),
(31, 'dasdsa', 'dasddsa', NULL, NULL, '2021-06-30 06:07:18', '2021-06-30 06:07:18'),
(32, 'IPTV Shqip', 'Notification', NULL, 'https://iptv.city/logo/iptvshqip512.png', '2021-07-03 09:55:06', '2021-07-03 09:55:06'),
(33, 'ddd', 'ddd', NULL, NULL, '2021-07-03 10:02:30', '2021-07-03 10:02:30'),
(34, 'TEST', 'TEST', 'http://www.max.net/live.html', NULL, '2022-09-07 08:55:19', '2022-09-07 08:55:19');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint UNSIGNED NOT NULL,
  `cat_id` bigint UNSIGNED NOT NULL,
  `url_id` bigint UNSIGNED DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `cat_id`, `url_id`, `name`, `image`, `description`, `url`, `user_agent`, `token`, `extra`, `created_at`, `updated_at`) VALUES
(32, 23, 1, 'Youtube Sample', 'images/products/1624359054.png', 'dasdasd', 'https://www.youtube.com/watch?v=nFjVlf2r9_Q&list=RDnFjVlf2r9_Q', NULL, NULL, NULL, '2021-06-22 10:41:18', '2021-06-22 11:06:47'),
(33, 8, 1, 'Sample Url For Lock Cateogry', 'images/products/1624360501.png', NULL, 'https://www.youtube.com/watch?v=vFN3eNe0_Hs&list=RDnFjVlf2r9_Q&index=3', NULL, NULL, NULL, '2021-06-22 11:15:01', '2022-05-17 06:02:08'),
(39, 30, 9, 'Radio', 'images/products/1633328726.jpg', NULL, 'http://144.217.83.175:8000', NULL, NULL, NULL, '2021-06-23 07:15:37', '2022-05-17 06:46:41'),
(48, 8, 1, 'Yo Yo Honey Singh New Song 2021 | Honey Singh Latest Song | Honey Singh Rap Song | Kasma Nu Khaa', 'images/products/1633329592.jpg', '<br><h1 class=\"title style-scope ytd-video-primary-info-renderer\" style=\"caret-color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 0px; border: 0px; max-height: calc(2 * var(--yt-navbar-title-line-height, 2.4rem)); overflow: hidden; line-height: var(--yt-navbar-title-line-height, 2.4rem); font-family: Roboto, Arial, sans-serif; font-size: var(--ytd-video-primary-info-renderer-title-font-size, var(--yt-navbar-title-font-size, inherit)); font-variant: var(--ytd-video-primary-info-renderer-title-font-variant, inherit); transform: var(--ytd-video-primary-info-renderer-title-transform, none); text-shadow: var(--ytd-video-primary-info-renderer-title-text-shadow, none);\"><yt-formatted-string force-default-style=\"\" class=\"style-scope ytd-video-primary-info-renderer\" style=\"word-break: break-word;\">Yo Yo Honey Singh New Song 2021 | Honey Singh Latest Song | Honey Singh Rap Song | Kasma Nu Khaa</yt-formatted-string></h1>', 'https://www.youtube.com/watch?v=Y5299jOaKQ4&list=RDnFjVlf2r9_Q&index=2', NULL, NULL, NULL, '2021-07-12 11:29:55', '2022-05-17 06:32:43'),
(49, 30, 9, 'VEX Radio', 'images/products/1633329228.jpg', 'test&nbsp;', 'http://144.217.83.175:8000', NULL, NULL, NULL, '2021-09-06 12:24:01', '2022-05-17 06:06:06'),
(50, 30, 9, 'MRM Radio', 'images/products/1633329347.jpg', 'wegjvklsjksdjklsjkg', 'http://144.217.83.175:8000', NULL, NULL, NULL, '2021-09-06 13:29:32', '2022-05-17 06:05:53'),
(51, 30, 9, 'VV Radio', 'images/products/1632999277.png', 'sdfsda', 'http://144.217.83.175:8000', NULL, NULL, NULL, '2021-09-11 05:52:07', '2022-05-17 06:05:36'),
(65, 8, 1, 'Badaami Rang (Official HD Video) Nikk Ft Avneet Kaur | Ikky | Bang Music | Latest Punjabi Songs 2020', 'images/products/1633329685.jpg', '<h1 class=\"title style-scope ytd-video-primary-info-renderer\" style=\"caret-color: rgb(0, 0, 0); margin-right: 0px; margin-bottom: 0px; margin-left: 0px; padding: 0px; border: 0px; max-height: calc(2 * var(--yt-navbar-title-line-height, 2.4rem)); overflow: hidden; line-height: var(--yt-navbar-title-line-height, 2.4rem); font-family: Roboto, Arial, sans-serif; font-size: var(--ytd-video-primary-info-renderer-title-font-size, var(--yt-navbar-title-font-size, inherit)); font-variant: var(--ytd-video-primary-info-renderer-title-font-variant, inherit); transform: var(--ytd-video-primary-info-renderer-title-transform, none); text-shadow: var(--ytd-video-primary-info-renderer-title-text-shadow, none);\"><yt-formatted-string force-default-style=\"\" class=\"style-scope ytd-video-primary-info-renderer\" style=\"word-break: break-word;\">Badaami Rang (Official HD Video) Nikk Ft Avneet Kaur | Ikky | Bang Music | Latest Punjabi Songs 2020</yt-formatted-string></h1>', 'https://www.youtube.com/watch?v=Sl54-j2T_AI', NULL, NULL, NULL, '2021-09-20 06:22:04', '2022-05-17 06:32:59'),
(66, 8, 1, 'Disco Balma', 'images/products/1633329816.jpg', NULL, 'https://www.youtube.com/watch?v=tIzDKpHJ4Xs', NULL, NULL, NULL, '2021-09-20 12:35:12', '2022-05-17 06:46:48'),
(68, 8, 10, 'Web Link', 'images/products/1633187208.png', 'asdfdssdgdfg&nbsp;', 'https://www.youtube.com/watch?v=DhytQnmTtxw', NULL, NULL, NULL, '2021-09-21 05:16:15', '2022-05-17 06:34:45'),
(72, 31, 11, 'Live Movie Link', 'images/products/1652768002.png', NULL, 'http://192.1.154.222:80/stream02', NULL, NULL, NULL, '2022-05-17 06:13:22', '2022-09-07 08:46:35'),
(73, 31, 11, 'rtmp', 'images/products/1662538904.png', NULL, 'rtmp://192.1.154.222:1935/live/hd-live01', NULL, NULL, NULL, '2022-09-07 08:21:44', '2022-09-07 08:45:20');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'admin', NULL, NULL),
(2, 'manager', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `id` bigint UNSIGNED NOT NULL,
  `privacy_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `settings` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fcm_api` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `one_signalID` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `support_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `home_dialogue_link` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `visibility_home_dialogue` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `settings`
--

INSERT INTO `settings` (`id`, `privacy_url`, `settings`, `fcm_api`, `one_signalID`, `support_email`, `token`, `home_dialogue_link`, `visibility_home_dialogue`, `created_at`, `updated_at`) VALUES
(1, 'https://www.google.com', 'locked', 'xxx', 'ea10839d-30dc-4052-90e0-08fec900b73c', 'testin@gmail.com', 'z6l83xa1pJHX', 'https://www.google.com', 'no', NULL, '2022-04-12 06:46:44');

-- --------------------------------------------------------

--
-- Table structure for table `sliders`
--

CREATE TABLE `sliders` (
  `id` bigint UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `product_id` bigint UNSIGNED NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sliders`
--

INSERT INTO `sliders` (`id`, `name`, `product_id`, `image`, `created_at`, `updated_at`) VALUES
(9, 'youtube', 32, 'images/sliders/1633330846.jpg', '2021-06-22 05:24:29', '2021-10-04 07:00:46'),
(12, 'Radio', 50, 'images/sliders/1633331075.jpg', '2021-09-19 15:02:25', '2021-10-04 07:04:35'),
(14, 'slider test', 48, 'images/sliders/1633330999.jpg', '2021-09-30 04:44:06', '2021-10-04 07:03:19');

-- --------------------------------------------------------

--
-- Table structure for table `url_types`
--

CREATE TABLE `url_types` (
  `id` bigint UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `url_types`
--

INSERT INTO `url_types` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'youtube', NULL, NULL),
(9, 'radio', NULL, NULL),
(10, 'web link', NULL, NULL),
(11, 'channel link', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int DEFAULT NULL,
  `unique_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remember_token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `role`, `unique_key`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'admin', 'admin@gmail.com', NULL, '$2y$10$9TPbnSxMVAOfFSHF8CE8mu1Bx8zChPU.XS0hoxNL3luwaxoRGh48.', NULL, NULL, 'hAN6MdgLKp1X2DPKpB3KUl4f8G8qt6ECzCW70Mc3XDxrXdQUJKqokalSXHrs', '2021-06-14 04:26:15', '2021-06-14 04:26:15'),
(4, 'TOMAS', 'tomas@gmail.com', NULL, '$2y$10$M9wz/.c/iE0PeJZIyBVkl.n4JSq5P.91amtWY.PsXI4aC76dQ.ZSq', 2, NULL, NULL, '2021-06-15 13:30:26', '2021-06-15 13:30:26');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addvertises`
--
ALTER TABLE `addvertises`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `add_types`
--
ALTER TABLE `add_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `products_cat_id_foreign` (`cat_id`),
  ADD KEY `url_id` (`url_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sliders`
--
ALTER TABLE `sliders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sliders_product_id_foreign` (`product_id`);

--
-- Indexes for table `url_types`
--
ALTER TABLE `url_types`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addvertises`
--
ALTER TABLE `addvertises`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `add_types`
--
ALTER TABLE `add_types`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `devices`
--
ALTER TABLE `devices`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `settings`
--
ALTER TABLE `settings`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sliders`
--
ALTER TABLE `sliders`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `url_types`
--
ALTER TABLE `url_types`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_cat_id_foreign` FOREIGN KEY (`cat_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`url_id`) REFERENCES `url_types` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Constraints for table `sliders`
--
ALTER TABLE `sliders`
  ADD CONSTRAINT `sliders_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
