package ecorota.api.repository;

import ecorota.api.repository.entity.Trajeto;
import ecorota.api.service.domain.TrajetoMapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrajetoRepository extends JpaRepository<Trajeto, Long> {
    @Query(value = """
            SELECT
                trajetos.id_map as idMapa,
                trajetos.id as idTrajeto,
                trajetos.pto as ponto,
                trajetos.id_tpt as transporte,
                transporte_linha.num as numLinha,
                transporte_linha.psg as passagem
            FROM
                trajetos
            INNER JOIN
                transporte_linha ON transporte_linha.id = trajetos.id_tpt_lin
            where
                trajetos.id_map in (:id)
            """, nativeQuery = true)
    List<TrajetoMapa> buscarTrajetoPorMapa(@Param("id") List<Integer> id);

}
