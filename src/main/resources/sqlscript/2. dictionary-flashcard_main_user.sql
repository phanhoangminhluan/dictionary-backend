DROP TABLE IF EXISTS `main_user`;

CREATE TABLE `main_user` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `main_user_role_id_fk` (`role_id`),
  CONSTRAINT `main_user_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `main_user` WRITE;

INSERT INTO `main_user` VALUES ('ad1','minhluan1998@gmail.com','$2a$12$vdRbtr0Sgh3stHBp5TxZeOnymLkr2gkd3L7ZBHBFvboWL0UccuePq',2),('ad2','tramy2002@gmail.com','$2a$12$vdRbtr0Sgh3stHBp5TxZeOnymLkr2gkd3L7ZBHBFvboWL0UccuePq',2),('minhluan1998','tramy2002@gmail.com','$2a$12$Liis45Ld.N0uJ7I.zw3Gku8HNW8S31xK6R.legU7QcKxr5b8yWwTW',2),('test09','test09@gmail.com','$2a$12$B7X/W1gS2OiB7.K.932QOes9GNcRPXDw//BYz7mvn/DSdl1owHXau',2),('thuongdth','thuongdth10@gmail.com','$2a$12$5FKJLfgRk75xfpHCdAQqw.YBC78KLx1iuzUUT3uTahDFgWLovhZ.u',2),('thuongdth0','thuongdth10@gmail.com','$2a$12$E2Fwf1iymO8x6kVZ32p6CO/Eey1CVMgPUSIt1w037YGXoyYKfIE22',1);

UNLOCK TABLES;
