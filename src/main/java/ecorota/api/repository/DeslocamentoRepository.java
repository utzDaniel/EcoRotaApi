package ecorota.api.repository;

import ecorota.api.repository.entity.Deslocamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeslocamentoRepository extends JpaRepository<Deslocamento, Long> {

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO
                deslocamento (dtc, tmp, ems_crb, id_opc_dlc, id_tjt)
            VALUES
                (:disct, :tempo, :carb, :idOp, :idTjt);
            """, nativeQuery = true)
    void insert(@Param("disct") int distancia,
                @Param("tempo") int tempo,
                @Param("carb") int carbono,
                @Param("idOp") Long idOpcao,
                @Param("idTjt") Long idTrajeto);

}
