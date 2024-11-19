package br.com.puc.ti.Eurna.E_urna.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.puc.ti.Eurna.E_urna.Entity.Voto;


@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

  @Query("SELECT v FROM Voto v WHERE v.usuario.id = :id")
  Optional<Voto> findByUsuario(@Param("id") Long id);

  @Query("SELECT SUM(v.numeroVotos) FROM Voto v WHERE v.candidato.id = :id")
  Integer findVotosCanidados(@Param("id") Long id);

  @Query("SELECT SUM(v.numeroVotos) FROM Voto v WHERE v.pleito.id = :id")
  Integer findVotosPleito(@Param("id") Long id); 

  @Query(
        value = "SELECT * FROM voto v " +
                "INNER JOIN pleito p ON p.id = :pleitoId " +
                "WHERE v.usuario_id = :usuarioId",
        nativeQuery = true
    )
    List<Voto> findByPleitoIdAndUsuarioId(@Param("pleitoId") Long pleitoId, @Param("usuarioId") Long usuarioId);
}

