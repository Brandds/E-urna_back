package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.Repository.UsuarioRepository;
import br.com.puc.ti.Eurna.E_urna.Repository.VotoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.VotoService;
import br.com.puc.ti.Eurna.E_urna.VO.VotoVo;

@Service
public class VotoServiceImpl implements VotoService {

  @Autowired
  private VotoRepository votoRepository;

  @Autowired 
  private UsuarioRepository usuarioRepository;

  public Voto toEntity(VotoVo entity){
    Voto voto = new Voto();
    LocalDate localDate = LocalDate.now();
    Date dataRegistro = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    voto.setNumeroVotos(1);
    voto.setDataRegistro(dataRegistro);

    voto.setCandidato(new Candidato(entity.getCandidatoVo().getId()));
    voto.setUsuario(new Usuario(entity.getUsuarioVo().getId()));
    voto.setPleito(new Pleito(entity.getPleitoVo().getId()));
    return voto;

  } 

  @Override
  public List<Voto> getAllVoto() {
    return votoRepository.findAll();
  }

  @Override
  public Voto adicionarVoto(VotoVo entity) {
      Optional<Voto> voto = votoRepository.findByUsuario(entity.getUsuarioVo().getId());
      if(!voto.isPresent()){
        Voto newVoto = toEntity(entity);
        votoRepository.save(newVoto);
         return newVoto;
      }
      return null;
    }

  @Override
  public Usuario getVotoUsuario(Long id) {
    Optional<Voto> votoUsuario = votoRepository.findByUsuario(id);
    if(votoUsuario.isPresent()){
      Optional<Usuario> usuario =  usuarioRepository.findById(id);
      return usuario.get();
    }
    return null;
  }

  @Override
  public Integer calcularVotoUusuario(Long id){
    return votoRepository.findVotosCanidados(id);
  }
}
  

