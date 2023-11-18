package ecorota.api.repository;

import ecorota.api.repository.entity.Local;
import ecorota.api.service.domain.MapaLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocaRepository extends JpaRepository<Local, Long> {

    @Query(value = """
            SELECT
                locais.id as id,
                locais.nom as nome,
                mapa.pos_x as x,
                mapa.pos_y as y
            FROM
                locais
            INNER JOIN
                mapa ON mapa.id_lcl = locais.id
            """, nativeQuery = true)
    List<MapaLocal> buscarMapaLocal();

}
