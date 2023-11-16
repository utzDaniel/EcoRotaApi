DROP TABLE IF EXISTS `locais`;
CREATE TABLE `locais` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mapa`;
CREATE TABLE `mapa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pos_x` int NOT NULL,
  `pos_y` int NOT NULL,
  `id_lcl` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_lcl`) REFERENCES locais(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `transporte_linha`;
CREATE TABLE `transporte_linha` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `num` INT NOT NULL,
   `nom` VARCHAR(255) NOT NULL,
   `psg` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `transporte_horarios`;
CREATE TABLE `transporte_horarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dat` DATETIME NOT NULL,
  `id_tpt_lin` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_tpt_lin`) REFERENCES `transporte_linha`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `trajetos`;
CREATE TABLE `trajetos` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `id_tpt` int NOT NULL,
    `pto` tinyint(1) NOT NULL,
    `id_map` bigint(20) NULL,
    `id_tpt_lin` bigint(20) NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_map`) REFERENCES `mapa`(`id`),
    FOREIGN KEY (`id_tpt_lin`) REFERENCES `transporte_linha`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO trajetos (id_tpt, pto, id_map, id_tpt_lin) VALUES(4, 0, null, null);
INSERT INTO trajetos (id_tpt, pto, id_map, id_tpt_lin) VALUES(3, 0, null, null);
