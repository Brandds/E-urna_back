package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Repository.UsuarioRepository;
import br.com.puc.ti.Eurna.E_urna.Service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;
  
  @Override
  public boolean validarUsuario(String matricula, String senha) {
    Optional<Usuario> userMatricula = usuarioRepository.findByNumMatricula(matricula);

    if (userMatricula.isPresent()) {
      Usuario usuario = userMatricula.get();

      return usuario.getSenha() != null && usuario.getSenha().equals(senha);
    }
    return false;
  }

  @Override
  public boolean validarCadastroUsuario(String newMatricula) {
    Optional<Usuario> existeMatricula = usuarioRepository.findByNumMatricula(newMatricula);

    if (existeMatricula.isPresent()) {
      return false;  // Matrícula já existe
    }
    return true;  // Matrícula disponível
  }
}
