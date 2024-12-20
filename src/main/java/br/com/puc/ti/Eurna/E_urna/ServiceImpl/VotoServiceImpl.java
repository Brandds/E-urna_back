package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.Repository.UsuarioRepository;
import br.com.puc.ti.Eurna.E_urna.Repository.VotoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.VotoService;
import br.com.puc.ti.Eurna.E_urna.VO.VotoVo;
import br.com.puc.ti.Eurna.E_urna.VO.VotosVO;

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
  
    voto.setNumeroCandidato(entity.getCandidatoId());
    voto.setUsuarioId(entity.getUsuarioId());
    voto.setPleitoId(entity.getPleitoId());

    return voto;

  } 

  @Override
  public List<Voto> getAllVoto() {
    return votoRepository.findAll();
  }

  @Override
  public Voto adicionarVoto(VotoVo entity) {
      Optional<Voto> voto = votoRepository.findVoto(entity.getUsuarioId(), entity.getPleitoId());
      if(!voto.isPresent()){
        Voto newVoto = toEntity(entity);
        votoRepository.save(newVoto);
         return newVoto;
      }
      return null;
    }

  @Override
  public Usuario getVotoUsuario(Long id, Long id2) {
    // List<Voto> voto = votoRepository.findByPleitoIdAndUsuarioId(id, id2);
    // if(voto.size() > 0){
    //   Optional<Usuario> usuario =  usuarioRepository.findById(id);
    //   return usuario.get();
    // }
    return null;
  }

  @Override
  public Integer calcularVotoUusuario(Long id){
    return votoRepository.findTotalVotos(id);
  }

  @Override
  public List<VotosVO> findAllVotosGroupedByCandidato(Long id){
    List<Object[]> lista = votoRepository.findAllVotosGroupedByCandidato(id);
    return lista.stream()
      .map(obj -> new VotosVO(
        ((Long) obj[0]).longValue(),
        ((Long) obj[1]).longValue()
      ))
      .collect(Collectors.toList());
  }

  
}
  

