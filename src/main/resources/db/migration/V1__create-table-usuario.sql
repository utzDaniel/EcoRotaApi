DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `role` varchar(30) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `codigo_verificador` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USUARIO_EMAIL` (`email`),
  KEY `IDX_USUARIO_EMAIL` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` (nome, email, senha, role, ativo)
VALUE("Daniel", "danieldosanjos.36@gmail.com","$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.", "ADMIN", 1);
UNLOCK TABLES;


