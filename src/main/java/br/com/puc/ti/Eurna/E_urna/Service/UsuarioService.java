package br.com.puc.ti.Eurna.E_urna.Service;

import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
  
  public boolean validarUsuario(String matricula , String senha);

  public boolean validarCadastroUsuario(String newMatricula);
}
