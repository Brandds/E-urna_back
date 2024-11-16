package br.com.puc.ti.Eurna.E_urna.Controollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Repository.UsuarioRepository;
import br.com.puc.ti.Eurna.E_urna.Service.UsuarioService;
import br.com.puc.ti.Eurna.E_urna.VO.UsuarioVo;



@RestController
@RequestMapping("/usuario")
public class UsuarioController {
  
  @Autowired UsuarioService usuarioService;

  @Autowired UsuarioRepository usuarioRepository;


  
  @GetMapping("/usuarioAll")
  public ResponseEntity<List<UsuarioVo>> getAllUsuarios(){
    return new ResponseEntity<>(usuarioService.getUsuarios(), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UsuarioVo login){
    
    
    UsuarioVo usuarioVo = usuarioService.validarUsuario(login.getNumeroMatriculaPessoa(),login.getSenhaUsuario());

    if(usuarioVo != null){
      return ResponseEntity.ok(usuarioVo);
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
  @PutMapping("/updateUsuario/{id}")
  public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody UsuarioVo entity) {
      Usuario usuario = usuarioService.updateUsuario(id, entity);

      if(usuario != null){
      return ResponseEntity.ok(usuario);
      }
      
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não foi encontrado");
  }

  @PutMapping("/removerUsuario/{id}")
  public ResponseEntity removerUsuario(@PathVariable Long id) {
      return (usuarioService.removerUsuario(id) ?
       ResponseEntity.ok("Usuario removido com sucesso") :
       ResponseEntity.badRequest().body("Usuario não encontrado")
      );
  }
}
