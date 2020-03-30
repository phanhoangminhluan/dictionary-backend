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
