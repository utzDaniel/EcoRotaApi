START TRANSACTION;
    INSERT INTO locais (nom) VALUES ("Praça do Barão");
    SET @id_local = LAST_INSERT_ID();
    INSERT INTO mapa (pos_x, pos_y, id_lcl) VALUES (0, 10, @id_local);
    SET @id_mapa = LAST_INSERT_ID();
    INSERT INTO `transporte_linha` (`num`, `nom`, `psg`) VALUES( 4988, "Sabará/Belo Horizonte", 7.10);
    SET @id_tpt_lin_1 = LAST_INSERT_ID();
    INSERT INTO `transporte_horarios` (`dat`, `id_tpt_lin`)
    VALUES('2023-11-04 12:04:00', @id_tpt_lin_1),
    ('2023-11-04 12:14:00', @id_tpt_lin_1),
    ('2023-11-04 12:24:00', @id_tpt_lin_1);
    INSERT INTO `trajetos` (`id_tpt`, `pto`, `id_map`, `id_tpt_lin`)
    VALUES(1, 1, @id_mapa, @id_tpt_lin_1);
    INSERT INTO `transporte_linha` (`num`, `nom`, `psg`) VALUES( 01, "Nova Vista", 6.35);
    SET @id_tpt_lin_2 = LAST_INSERT_ID();
    INSERT INTO `trajetos` (`id_tpt`, `pto`, `id_map`, `id_tpt_lin`)
    VALUES(1, 0, @id_mapa, @id_tpt_lin_2);
COMMIT;

START TRANSACTION;
    INSERT INTO locais (nom) VALUES ("Comercial 13");
    SET @id_local = LAST_INSERT_ID();
    INSERT INTO mapa (pos_x, pos_y, id_lcl) VALUES (20, 20, @id_local);
    SET @id_mapa = LAST_INSERT_ID();
    INSERT INTO `transporte_linha` (`num`, `nom`, `psg`) VALUES( 02, "Ravena", 6.35);
    SET @id_tpt_lin = LAST_INSERT_ID();
    INSERT INTO `trajetos` (`id_tpt`, `pto`, `id_map`, `id_tpt_lin`)
    VALUES(1, 1, @id_mapa, @id_tpt_lin);
COMMIT;

START TRANSACTION;
    INSERT INTO locais (nom) VALUES ("Drogaria Araujo");
    SET @id_local = LAST_INSERT_ID();
    INSERT INTO mapa (pos_x, pos_y, id_lcl) VALUES (50, 10, @id_local);
COMMIT;
