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

  @Query(value = "SELECT SUM(numero_votos) FROM voto WHERE numero_candidato = :id", nativeQuery = true)
  Integer findTotalVotos(@Param("id") Long candidatoId);


  @Query(value = "SELECT * FROM voto WHERE usuario_id = :usuarioId AND pleito_id = :pleitoId", nativeQuery = true)
  Optional<Voto> findVoto(@Param("usuarioId") Long usuarioId, @Param("pleitoId") Long pleitoId);

  @Query(value = "SELECT numero_candidato, SUM(numero_votos) AS total_votos " +
               "FROM voto " +
               "WHERE pleito_id = :pleitoId " +
               "GROUP BY numero_candidato", nativeQuery = true)
  List<Object[]> findAllVotosGroupedByCandidato(@Param("pleitoId") Long pleitoId);


}

