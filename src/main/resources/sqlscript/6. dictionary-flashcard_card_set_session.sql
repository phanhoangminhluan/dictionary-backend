DROP TABLE IF EXISTS `card_set_session`;

CREATE TABLE `card_set_session` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `card_set_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`card_set_id`),
  CONSTRAINT `` FOREIGN KEY (`card_set_id`) REFERENCES `card_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `card_set_session` WRITE;
INSERT INTO `card_set_session` VALUES ('0caf2d6d-9f74-40fa-ae20-4bd6639f654a','2020-03-18 12:17:26','13062788-4dee-42eb-b843-38a1d5b9f6bf'),('70cae99b-289d-46c9-96fb-813d08697f4c','2020-03-18 12:19:21','d8c3a06a-770f-4523-8d88-8efc8107a186'),('77b7798b-46ab-4391-9772-4064806aea09','2020-03-20 11:14:37','80324c83-5551-421a-a08b-c717c3b3a395'),('7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad','2020-03-20 10:38:30','39b04b2b-86cf-404a-802b-1bdcac10969e'),('c1a3efa1-5f4e-4d20-bb51-d1ae3496d074','2020-03-20 11:06:43','190f1137-1ded-4c2e-b44a-734baf10606c');
UNLOCK TABLES;
