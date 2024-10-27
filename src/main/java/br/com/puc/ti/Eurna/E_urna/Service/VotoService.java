package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.VO.VotoVo;

@Service
public interface VotoService {
 List<Voto> getAllVoto();
 Usuario getVotoUsuario(Long id);
 Voto adicionarVoto(VotoVo entity);
 Integer calcularVotoUusuario(Long id);
}
