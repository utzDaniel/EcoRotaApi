DROP TABLE IF EXISTS `usuarios_historico`;
CREATE TABLE `usuarios_historico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usr` bigint(20) NOT NULL,
  `id_lcl_prt` bigint(20) NOT NULL,
  `id_lcl_dst` bigint(20) NOT NULL,
  `dat_inc` DATETIME NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (`id_usr`) REFERENCES usuarios(`id`),
   FOREIGN KEY (`id_lcl_prt`) REFERENCES locais(`id`),
   FOREIGN KEY (`id_lcl_dst`) REFERENCES locais(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `opcoes_deslocamento`;
CREATE TABLE `opcoes_deslocamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usr_htc` bigint(20) NOT NULL,
  `scl` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_usr_htc`) REFERENCES usuarios_historico(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `deslocamento`;
CREATE TABLE `deslocamento` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `dtc` INT NOT NULL,
   `tmp` INT NOT NULL,
   `ems_crb` INT NOT NULL,
   `id_opc_dlc` bigint(20) NOT NULL,
   `id_tjt` bigint(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_opc_dlc`) REFERENCES opcoes_deslocamento(`id`),
    FOREIGN KEY (`id_tjt`) REFERENCES trajetos(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
