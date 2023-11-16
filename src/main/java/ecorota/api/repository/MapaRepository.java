package ecorota.api.repository;

import ecorota.api.repository.entity.Mapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MapaRepository extends JpaRepository<Mapa, Long> {

    @Query(value = """
            select
                *
            from
                mapa
            where
                mapa.id_lcl = :id
            """, nativeQuery = true)
    Mapa buscarMapaPorLocal(@Param("id") Long id);

}
