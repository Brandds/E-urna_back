package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;

@Service
public interface CandidatoService {
  
  List<Candidato> findAll();
  Optional<Candidato> findByCandidato(Long id);
}
