package ecorota.api.repository;

import ecorota.api.repository.entity.UsuariosHistorico;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<UsuariosHistorico, Long> {

    List<UsuariosHistorico> findAllByUsuarioId(Long userId);
    Page<UsuariosHistorico> findAllByUsuarioId(Long userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO
                usuarios_historico (id_usr, id_lcl_prt, id_lcl_dst, dat_inc)
            VALUES
                ( :idUser, :idPar, :idDes, NOW())
            """, nativeQuery = true)
    void insert(@Param("idUser") Long idUser,
                @Param("idPar") Long idPar,
                @Param("idDes") Long idDes);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertedId();
}
