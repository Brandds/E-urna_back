package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Repository.CandidatoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.CandidatoService;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;

@Service
public class CandidatoServiceImpl implements CandidatoService  {

  @Autowired
  private CandidatoRepository candidatoRepository;

  public Candidato toEntity(CandidatoVo vo){
    Candidato newCandidato = new Candidato();
  
    newCandidato.setNome(vo.getNomeCandidato());
    newCandidato.setCurso(vo.getCursoCandidato());
    newCandidato.setNumeroCandidato(vo.getNumeroCandidato());
    newCandidato.setPleito(new Pleito(vo.getPleito_Id_Candidato().getId()));


    return newCandidato;
  }

  public Candidato updateToEntity(CandidatoVo vo, Candidato candidato){

    candidato.setCurso(vo.getCursoCandidato());
    candidato.setNumeroCandidato(vo.getNumeroCandidato());
    candidato.setPleito(new Pleito(vo.getPleito_Id_Candidato().getId()));

    return candidato;
  }
  @Override
  public List<Candidato> findAll() {
     List<Candidato> candidatos = candidatoRepository.findAll();
    return candidatos;    
  }

  @Override
  public Optional<Candidato> findByCandidato(Long id) {
     return candidatoRepository.findById(id);
  }

  @Override
  public Candidato adicionarCandidato(CandidatoVo entity) {
    Optional<Candidato> candidato = candidatoRepository.findByNumeroCandidato(entity.getNumeroCandidato());

    if(!candidato.isPresent())
    {
      Candidato newCandidato = toEntity(entity);
      candidatoRepository.save(newCandidato);
      return newCandidato;

    }
    return null;
  }
  
  @Override
  public Candidato updateCandidato(Long id, CandidatoVo entity ){
    Optional<Candidato> candidato = candidatoRepository.findById(id);

    if(candidato.isPresent()){
      return candidatoRepository.save(updateToEntity(entity, candidato.get()));
    }

    return null;
  }

  @Override
  public boolean removerCandidato(Long id){
    Optional<Candidato> candidato = candidatoRepository.findById(id);

    if(candidato.isPresent()){
      candidatoRepository.delete(candidato.get());
      return true;
    }

    return false;

  }
}
