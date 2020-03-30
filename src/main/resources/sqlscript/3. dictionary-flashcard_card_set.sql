DROP TABLE IF EXISTS `card_set`;

CREATE TABLE `card_set` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `card_set_main_user_id_fk` (`user_id`),
  CONSTRAINT `card_set_main_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `main_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `card_set` WRITE;

INSERT INTO `card_set` VALUES ('13062788-4dee-42eb-b843-38a1d5b9f6bf','ABC','ad2','2020-03-18 08:28:24'),('190f1137-1ded-4c2e-b44a-734baf10606c','Learn_day','ad2','2020-03-15 21:34:57'),('39b04b2b-86cf-404a-802b-1bdcac10969e','List of new words','ad2','2020-03-15 21:38:39'),('80324c83-5551-421a-a08b-c717c3b3a395','Learn_day_two','ad2','2020-03-15 21:39:09'),('886f31c9-4e17-41b9-a31b-49d197b88ec5','Learn_day_three','ad2','2020-03-15 21:43:00'),('a70ad9e9-a5f5-4b7c-9e97-3e3b301e600c','Learn_day_four','ad2','2020-03-15 21:47:41'),('d8c3a06a-770f-4523-8d88-8efc8107a186','test','test09','2020-03-18 12:19:07');

UNLOCK TABLES;

