package ecorota.api.repository;

import ecorota.api.repository.entity.OpcoesDeslocamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OpcoesDeslocamentoRepository extends JpaRepository<OpcoesDeslocamento, Long> {

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO
                opcoes_deslocamento (id_usr_htc, scl)
            VALUES
                ( :idHist, :scl)
            """, nativeQuery = true)
    void insert(@Param("idHist") Long idHist,
                @Param("scl") boolean selecionado);

    @Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
    Long getLastInsertedId();
}
