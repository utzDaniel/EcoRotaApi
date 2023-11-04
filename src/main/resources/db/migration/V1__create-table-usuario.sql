DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) NOT NULL,
  `eml` varchar(150) NOT NULL,
  `sen` varchar(255) NOT NULL,
  `rol` varchar(30) NOT NULL,
  `atv` tinyint(1) NOT NULL,
  `cod_vrf` varchar(6) DEFAULT NULL,
  `id_opc_tjt` int NOT NULL,
  `onb_atv` tinyint(1) NOT NULL,
  `mtr_atv` tinyint(1) NOT NULL,
  `bcc_atv` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USUARIO_EMAIL` (`eml`),
  KEY `IDX_USUARIO_EMAIL` (`eml`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `usuarios` (nom, eml, sen, rol, atv, id_opc_tjt, onb_atv, mtr_atv, bcc_atv)
VALUE("Daniel", "danieldosanjos.36@gmail.com","$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.", "ADMIN", 1, 2, 1, 1, 0);


