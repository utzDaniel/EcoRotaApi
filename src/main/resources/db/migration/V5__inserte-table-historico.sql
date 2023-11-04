START TRANSACTION;
    INSERT INTO `usuarios_historico`(`id_usr`, `id_lcl_prt`, `id_lcl_dst`, `dat_inc`)
    VALUES ( 1, 1, 2, '2023-11-04 19:14:00');
    SET @id_usr_htc = LAST_INSERT_ID();
    INSERT INTO `opcoes_deslocamento`(`id_usr_htc`, `scl`)
    VALUES (@id_usr_htc, 1);
    SET @id_opc_dlc = LAST_INSERT_ID();
    INSERT INTO `deslocamento`(`dtc`, `tmp`, `ems_crb`, `id_opc_dlc`, `id_tjt`)
    VALUES (800.0, 25, 125, @id_opc_dlc, 1);
    INSERT INTO `deslocamento`(`dtc`, `tmp`, `ems_crb`, `id_opc_dlc`, `id_tjt`)
    VALUES (300.0, 12, 70, @id_opc_dlc, 3);
    INSERT INTO `opcoes_deslocamento`(`id_usr_htc`, `scl`)
    VALUES (@id_usr_htc, 0);
    SET @id_opc_dlc = LAST_INSERT_ID();
    INSERT INTO `deslocamento`(`dtc`, `tmp`, `ems_crb`, `id_opc_dlc`, `id_tjt`)
    VALUES (900.0, 32, 140, @id_opc_dlc, 2);
COMMIT;