package ecorota.api.repository;

import ecorota.api.repository.entity.UsuariosHistorico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<UsuariosHistorico, Long> {

    List<UsuariosHistorico> findAllByUsuarioId(Long userId);
    Page<UsuariosHistorico> findAllByUsuarioId(Long userId, Pageable pageable);

}
