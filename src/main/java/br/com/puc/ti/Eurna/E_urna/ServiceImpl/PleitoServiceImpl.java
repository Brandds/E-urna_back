package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Enum.StatusPleito;
import br.com.puc.ti.Eurna.E_urna.Repository.CandidatoRepository;
import br.com.puc.ti.Eurna.E_urna.Repository.PleitoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.PleitoService;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVo;

@Service
public class PleitoServiceImpl implements PleitoService {

  @Autowired
  private PleitoRepository pleitoRepository;
  
  @Autowired
  private CandidatoRepository candidatoRepository;

  public Pleito toEntity(PleitoVo vo){
    Pleito newPleito = new Pleito();

    newPleito.setNomePleito(vo.getNomePleito());
    newPleito.setDataInicio(vo.getData_inicio());
    newPleito.setDataTermino(vo.getData_termino());
    newPleito.setVotosTotais(vo.getVotoTotais());
    newPleito.setStatus(vo.getStatus().ATIVO);

    return newPleito;
  }

  @Override
  public List<Pleito> getAllPleito(){
    pleitoRepository.encerrarPleitos();
    List<Pleito> pleitos = pleitoRepository.findAll();
    return pleitos;
  }
  
  @Override
  public Pleito adicionarPleito(PleitoVo vo){
    Pleito pleito = toEntity(vo);
    pleitoRepository.save(pleito);

    if(vo.getCandidatos().size() != 0){
      int tamnhanho = vo.getCandidatos().size();
      List<CandidatoVo> listCandidato = vo.getCandidatos();
      for(int i = 0; tamnhanho > i;i++ ){
        Candidato candidato = new  Candidato();
        candidato = addCandidato(listCandidato.get(i), pleito ); 
        candidatoRepository.save(candidato);
      }
    }
    return pleito;
  }

  public Candidato addCandidato(CandidatoVo vo, Pleito pleito){
    Optional<Candidato> candidato =  candidatoRepository.findById(vo.getId());
    
    if(candidato.isPresent()){
      candidato.get().setPleito(pleito);
    }
    return candidato.get();
  }
  
  @Override
  public List<Pleito> buscarPorDataTermino(boolean status){
    if(status){
      return pleitoRepository.findPleitoStatus(StatusPleito.ATIVO);
    }
    return pleitoRepository.findPleitoStatus(StatusPleito.ENCERRADO);
  }

  @Override
  public Pleito atualizarPleito(Long id) {
    int valor = pleitoRepository.updateStatus(id);

    if(valor == 0){
      return null;
    }
    Optional<Pleito> voto = pleitoRepository.findById(id);
    return voto.get();
  }

  @Override
  public boolean removerPleito(Long id){
    Optional<Pleito> pleito = pleitoRepository.findById(id);

    if(pleito.isPresent()){
      pleitoRepository.delete(pleito.get());
      return true;
    }

    return false;
  }
}
