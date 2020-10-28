DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `role` WRITE;

INSERT INTO `role` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');

UNLOCK TABLES;

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

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `term` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `definition` text NOT NULL,
  `card_set_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `card_card_set_id_fk` (`card_set_id`),
  CONSTRAINT `card_cardset` FOREIGN KEY (`card_set_id`) REFERENCES `card_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `card` WRITE;

INSERT INTO `card` VALUES ('054cfd51-e1ae-447f-bd1b-5fd8ea4ef03a','test02','02','d8c3a06a-770f-4523-8d88-8efc8107a186'),('172a676b-1e39-4ecb-9b02-d8b6e28defc2','dictionary','A dictionary is a collection of words and their definitions (and sometimes other information). Such collections are usually printed as books, but some are now designed for use on computers.','886f31c9-4e17-41b9-a31b-49d197b88ec5'),('18ae520e-c15e-4951-96c1-7e1fd72d115f','Speak ','To speak is to talk. If a UFO lands in your yard, it will be easier to speak with the aliens if they\'ve learned how to speak English — or if you know how to speak.','190f1137-1ded-4c2e-b44a-734baf10606c'),('1e645e0c-7c88-403d-9996-43e81e7d50ba','stamps','When you stamp, you step hard, either on the ground or on some object. If you\'re terrified of bugs, you might be tempted to stamp on a big, weird looking beetle.','a70ad9e9-a5f5-4b7c-9e97-3e3b301e600c'),('2708d668-d610-411f-8b8f-0dbb52d75d28','grandmother','Your grandmother is your mom or dad\'s mother. In many traditional cultures, a grandmother lives with her adult child\'s family and helps raise her grandchildren','80324c83-5551-421a-a08b-c717c3b3a395'),('39b769f7-7a8e-4ab7-9188-2160024691e0','funfair','a place of outside entertainment where there are machines for riding on and games that can be played for prizes','39b04b2b-86cf-404a-802b-1bdcac10969e'),('3e93de5f-25bb-42a7-8624-d3a73cc2b326','personification','Personification means \"giving humans qualities to an abstract idea,\" as in a movie villain who is the personification of evil.','80324c83-5551-421a-a08b-c717c3b3a395'),('486cb1c2-513b-43d1-82a3-1663ceabd706','test01','right','d8c3a06a-770f-4523-8d88-8efc8107a186'),('49533544-1e14-456f-a2c9-ae4eee56d876','adrenaline-spiking','the level of adrenaline in a person\'s system has risen sharply, and may not be sustained','39b04b2b-86cf-404a-802b-1bdcac10969e'),('4ba37560-9a21-48a2-acf3-c377f8304e08','purpose','When you do something with purpose, you do it with determination. When your activities have a purpose, you have an aim or intention in mind.','886f31c9-4e17-41b9-a31b-49d197b88ec5'),('50c9f4bb-c4aa-4c67-806b-3d7e250e27e4','circumstances','Circumstances are factors or conditions that play a part in determining an outcome. Given the current economic circumstances, a lot of good candidates just can\'t find jobs.','a70ad9e9-a5f5-4b7c-9e97-3e3b301e600c'),('5b1341a7-814f-4200-9759-f6b9e2829a5e','Companionship','Companionship','190f1137-1ded-4c2e-b44a-734baf10606c'),('62c316aa-bd02-4846-95f5-0acdd2d091ea','happenstance','A happenstance is a coincidental event. If you call your brother on the phone, that\'s intentional. If you bump into him in a restaurant, it\'s happenstance.','a70ad9e9-a5f5-4b7c-9e97-3e3b301e600c'),('64b3edf6-4130-4464-b929-013a7bc4907d','means','Someone who has wealth beyond the average person is said to be a person of means. Means can also mean the way something happens. You should try to resolve your fight with your brother through peaceful means––noogies are so undignified.','a70ad9e9-a5f5-4b7c-9e97-3e3b301e600c'),('76f63c74-6ec0-44ba-9d2c-e12bd3ecb270','adulterate','If you adulterate something, you mess it up. You may not want to adulterate the beauty of freshly fallen snow by shoveling it, but how else are you going to get to work?','80324c83-5551-421a-a08b-c717c3b3a395'),('811f7527-50aa-43c7-be04-a6beba7fa7ab','woman ','When a little girl grows up, she becomes a woman, otherwise known as an adult female.','80324c83-5551-421a-a08b-c717c3b3a395'),('8a32bd45-c6f1-4767-b838-f841734b2934','get','Get is one of those little words with a hundred applications. A common meaning is fetch, as in, go get a dictionary off the shelf.','886f31c9-4e17-41b9-a31b-49d197b88ec5'),('8e4de443-12e8-4153-a1ce-da497f933fac','might','Might is physical strength or power. If you try with all your might to climb a difficult rock wall, it means that you try as hard as you can to get to the top.','a70ad9e9-a5f5-4b7c-9e97-3e3b301e600c'),('9e90a5db-2c94-4361-8f82-c78e85cc58a8','Lear','To learn is to gain new knowledge or skills through study, practice or experience. You probably learn something every day.','190f1137-1ded-4c2e-b44a-734baf10606c'),('a778e245-dd6b-4b87-9e48-12f28e0e85c6','Choose','Choose','190f1137-1ded-4c2e-b44a-734baf10606c'),('a7e4e086-cada-40a8-997e-665520ee8520','impetus','something that encourages a particular activity or makes that activity more energetic or effective.','39b04b2b-86cf-404a-802b-1bdcac10969e'),('af4b41d5-4c61-48b9-8a80-65c232d742c7','love','3','13062788-4dee-42eb-b843-38a1d5b9f6bf'),('b3db68b4-bcf8-42b1-9240-033d90ac05d7','self-proclaimed','said or announced about yourself','39b04b2b-86cf-404a-802b-1bdcac10969e'),('b51d90ef-660a-4080-9208-990c56944ad4','collection ','A collection is a group of things, often a group created by someone. For example, many kids have a collection of comic books.','886f31c9-4e17-41b9-a31b-49d197b88ec5'),('c0ba20b0-b202-4fa4-b1e5-6da42ad8ae8f','mother','A mother is a female parent: mothers nurture and mother children. It\'s also a term for an elderly woman or mother superior','80324c83-5551-421a-a08b-c717c3b3a395'),('c36ebda3-0148-4f13-9f6e-56519c6e7014','anticipation','a feeling of excitement about something that is going to happen in the near future','39b04b2b-86cf-404a-802b-1bdcac10969e'),('c7a21c90-209d-47b6-8ffa-83c4c7c5b165','determination','Determination can be a decision, or the focus you need to get something done.','886f31c9-4e17-41b9-a31b-49d197b88ec5'),('c86d47e4-049f-4956-ac3c-8b7f0bbb3a2e','seething','Seething describes anger you can barely contain. If you are seething and something sets you off, you just might explode into a rage.','886f31c9-4e17-41b9-a31b-49d197b88ec5'),('c949dde4-893a-4924-a680-52e0b2a9bcf6','Shelter ','Shelter ','190f1137-1ded-4c2e-b44a-734baf10606c'),('cadd706b-8fae-4920-ab71-6e39024ce764','bare','without any clothes or not covered by anything','39b04b2b-86cf-404a-802b-1bdcac10969e'),('cf510ec0-b61a-47a6-8bf8-525f0aafe3f5','villain','A villain is a bad person — real or made up. In books, movies, current events, or history, the villain is the character who does mean, evil things on purpose.','80324c83-5551-421a-a08b-c717c3b3a395'),('cff1f5c6-2e31-438f-84c6-9f68055c4e87','message','A message is a communication or statement conveyed from one person or group to another. If you call my house phone and I’m out running an errand, you’ll be asked to “please leave a message after the beep.”','80324c83-5551-421a-a08b-c717c3b3a395'),('f6568ef3-bb14-4486-9b13-c0936279eef4','Soccer ','Soccer is a sport that\'s played with a round black and white ball that players pass to each other using their feet. To win a soccer match, your side has to kick the ball into the goal more times than your rivals do. Go team!','190f1137-1ded-4c2e-b44a-734baf10606c'),('fa2d71b1-5971-46a7-a927-4de3bc6aa9bd','Learn ','To learn is to gain new knowledge or skills through study, practice or experience. You probably learn something every day.','190f1137-1ded-4c2e-b44a-734baf10606c');

UNLOCK TABLES;

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

DROP TABLE IF EXISTS `studiable_card`;
CREATE TABLE `studiable_card` (
  `card_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `card_set_session_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_remember` bit(1) DEFAULT b'0',
  `remember_count` int(11) DEFAULT '0',
  `forget_count` int(11) DEFAULT '0',
  PRIMARY KEY (`card_id`,`card_set_session_id`),
  KEY `studiable_card_card_set_session_id_fk` (`card_set_session_id`),
  CONSTRAINT `studiable_card_card_id_fk` FOREIGN KEY (`card_id`) REFERENCES `card` (`id`),
  CONSTRAINT `studiable_card_card_set_session_id_fk` FOREIGN KEY (`card_set_session_id`) REFERENCES `card_set_session` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `studiable_card` WRITE;
INSERT INTO `studiable_card` VALUES ('054cfd51-e1ae-447f-bd1b-5fd8ea4ef03a','70cae99b-289d-46c9-96fb-813d08697f4c',_binary '\0',0,0),('18ae520e-c15e-4951-96c1-7e1fd72d115f','c1a3efa1-5f4e-4d20-bb51-d1ae3496d074',_binary '\0',0,1),('2708d668-d610-411f-8b8f-0dbb52d75d28','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('39b769f7-7a8e-4ab7-9188-2160024691e0','7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad',_binary '\0',0,0),('3e93de5f-25bb-42a7-8624-d3a73cc2b326','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('486cb1c2-513b-43d1-82a3-1663ceabd706','70cae99b-289d-46c9-96fb-813d08697f4c',_binary '\0',0,0),('49533544-1e14-456f-a2c9-ae4eee56d876','7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad',_binary '\0',0,0),('5b1341a7-814f-4200-9759-f6b9e2829a5e','c1a3efa1-5f4e-4d20-bb51-d1ae3496d074',_binary '\0',0,1),('76f63c74-6ec0-44ba-9d2c-e12bd3ecb270','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('811f7527-50aa-43c7-be04-a6beba7fa7ab','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('a778e245-dd6b-4b87-9e48-12f28e0e85c6','c1a3efa1-5f4e-4d20-bb51-d1ae3496d074',_binary '\0',0,1),('a7e4e086-cada-40a8-997e-665520ee8520','7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad',_binary '\0',0,1),('af4b41d5-4c61-48b9-8a80-65c232d742c7','0caf2d6d-9f74-40fa-ae20-4bd6639f654a',_binary '',1,0),('b3db68b4-bcf8-42b1-9240-033d90ac05d7','7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad',_binary '\0',0,1),('c0ba20b0-b202-4fa4-b1e5-6da42ad8ae8f','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('c36ebda3-0148-4f13-9f6e-56519c6e7014','7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad',_binary '\0',0,0),('c949dde4-893a-4924-a680-52e0b2a9bcf6','c1a3efa1-5f4e-4d20-bb51-d1ae3496d074',_binary '\0',0,1),('cadd706b-8fae-4920-ab71-6e39024ce764','7f132bd7-b7d1-4c2d-aef8-ae0828d5c8ad',_binary '\0',0,0),('cf510ec0-b61a-47a6-8bf8-525f0aafe3f5','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('cff1f5c6-2e31-438f-84c6-9f68055c4e87','77b7798b-46ab-4391-9772-4064806aea09',_binary '\0',0,0),('f6568ef3-bb14-4486-9b13-c0936279eef4','c1a3efa1-5f4e-4d20-bb51-d1ae3496d074',_binary '\0',0,1),('fa2d71b1-5971-46a7-a927-4de3bc6aa9bd','c1a3efa1-5f4e-4d20-bb51-d1ae3496d074',_binary '',1,0);
UNLOCK TABLES;
