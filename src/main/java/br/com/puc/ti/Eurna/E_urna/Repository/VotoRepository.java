package br.com.puc.ti.Eurna.E_urna.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.puc.ti.Eurna.E_urna.Entity.Voto;


@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

  // @Query("SELECT v FROM Voto v WHERE v.usuario.id = :id")
  // Optional<Voto> findByUsuario(@Param("id") Long id);

  @Query(value = "SELECT SUM(numero_votos) FROM voto WHERE numero_candidato = :id", nativeQuery = true)
Integer findTotalVotos(@Param("id") Long candidatoId);


  // @Query("SELECT SUM(v.numeroVotos) FROM Voto v WHERE v.pleito.id = :id")
  // Integer findVotosPleito(@Param("id") Long id); 

  @Query(value = "SELECT * FROM voto WHERE usuario_id = :usuarioId AND pleito_id = :pleitoId", nativeQuery = true)
  Optional<Voto> findVoto(@Param("usuarioId") Long usuarioId, @Param("pleitoId") Long pleitoId);

  

}

