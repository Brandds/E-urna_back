package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.VO.UsuarioVo;

@Service
public interface UsuarioService {
  
  UsuarioVo validarUsuario(String matricula , String senha);

  boolean validarCadastroUsuario(String newMatricula);

  List<UsuarioVo> getUsuarios();

  Usuario updateUsuario(Long id, UsuarioVo usuarioVo);

  boolean removerUsuario(Long id);

}
