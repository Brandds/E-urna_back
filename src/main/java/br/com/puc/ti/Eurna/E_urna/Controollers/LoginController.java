package br.com.puc.ti.Eurna.E_urna.Controollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Repository.UsuarioRepository;
import br.com.puc.ti.Eurna.E_urna.Service.UsuarioService;
import br.com.puc.ti.Eurna.E_urna.VO.UsuarioVo;


@RestController
@RequestMapping("/api/v1")
public class LoginController {
  
  @Autowired UsuarioService usuarioService;

  @Autowired UsuarioRepository usuarioRepository;


  
  @GetMapping("/usuarioAll")
  public ResponseEntity<List<Usuario>> getAllUsuarios(){
    return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UsuarioVo login){
    
    
    boolean validarUsuario = usuarioService.validarUsuario(login.getNumeroMatriculaPessoa(),login.getSenhaUsuario());

    if(validarUsuario){
      return ResponseEntity.ok("Usuario bem sucedido");
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario ou senha invalidos");

  }
  @PostMapping("/cadastro")

  public ResponseEntity<?> criarUsuario(@RequestBody UsuarioVo newEnity) {
      
      if(usuarioService.validarCadastroUsuario(newEnity.getNumeroMatriculaPessoa()))
      {
        Usuario usuario = new Usuario();
        usuario.adicionarUsuario(newEnity);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario criado com sucesso!");
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Esse usuário já existe");
  }
  
}
