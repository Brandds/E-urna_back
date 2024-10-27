package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;

@Service
public interface CandidatoService {
  
  List<Candidato> findAll();
  Optional<Candidato> findByCandidato(Long id);
  Candidato adicionarCandidato(CandidatoVo entity);
  Candidato updateCandidato(Long id, CandidatoVo  entity );
  boolean removerCandidato(Long id);
}
