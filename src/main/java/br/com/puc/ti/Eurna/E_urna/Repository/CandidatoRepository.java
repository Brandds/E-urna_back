package br.com.puc.ti.Eurna.E_urna.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;


@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

  Optional<Candidato> findById(Long id);
  Optional<Candidato> findByNumeroCandidato(Integer numeroCandidato);
  List<Candidato> findByPleitoId(Long id);
}
