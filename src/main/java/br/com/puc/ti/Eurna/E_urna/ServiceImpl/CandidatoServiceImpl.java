package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Repository.CandidatoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.CandidatoService;

@Service
public class CandidatoServiceImpl implements CandidatoService  {

  @Autowired
  private CandidatoRepository candidatoRepository;

  @Override
  public List<Candidato> findAll() {
     List<Candidato> candidatos = candidatoRepository.findAll();
    return candidatos;    
  }

  @Override
  public Optional<Candidato> findByCandidato(Long id) {
    // TODO 
    Optional<Candidato> candidato = candidatoRepository.findById(id);

    if(candidato.isPresent()){
      return candidato;
    }
    return null;
    
  }
  
}
