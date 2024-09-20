package br.com.puc.ti.Eurna.E_urna.Controollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.ti.Eurna.E_urna.Entity.Aluno;
import br.com.puc.ti.Eurna.E_urna.Service.AlunoService;
import br.com.puc.ti.Eurna.E_urna.VO.AlunoVO;


@RestController
@RequestMapping("/api/v1")
public class LoginController {
  
  @Autowired
  private  AlunoService alunoService;


  
  @GetMapping("/users")
  public Aluno getTodosUsuarios(){
    Aluno all = new Aluno();
    return all;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AlunoVO login){
    

    boolean validarUsuario = alunoService.validarUsuario(login.getNome(),login.getSenha());

    if(validarUsuario){
      return ResponseEntity.ok("Usuario bem sucedido");
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario ou senha invalidos");

  }
}
