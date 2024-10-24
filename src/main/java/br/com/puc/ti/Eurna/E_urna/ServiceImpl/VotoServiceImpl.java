package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.Repository.VotoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.VotoService;
import br.com.puc.ti.Eurna.E_urna.VO.VotoVo;

@Service
public class VotoServiceImpl implements VotoService {

  @Autowired
  private VotoRepository votoRepository;

  public Voto toEntity(VotoVo entity){
    Voto voto = new Voto();
    LocalDate localDate = LocalDate.now();
    Date dataRegistro = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    voto.setNumeroVotos(1);
    voto.setDataRegistro(dataRegistro);

    voto.setCandidato(new Candidato(entity.getCandidatoVo().getNumeroCandidato(), entity.getCandidatoVo().getId()));
    return voto;

  } 

  @Override
  public List<Voto> getAllVoto() {
    return votoRepository.findAll();
  }

  @Override
  public Optional<Voto> findById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public Voto adicionarVoto(VotoVo entity) {
    // TODO 
    if(!votoRepository.findByCandidato(entity.getCandidatoVo().getId()).isPresent()){
      Optional<Voto> voto = votoRepository.findById(entity.getId());
      if(voto.isPresent()){
        voto.get().setNumeroVotos(voto.get().getNumeroVotos() + 1);
        return voto.get();
      }else{
        Voto newVoto = toEntity(entity);
        votoRepository.save(newVoto);
      return newVoto;
      }
    }

    return null;
  }
  
}
