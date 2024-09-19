package br.com.puc.ti.Eurna.E_urna.Controollers;

import org.apache.catalina.connector.Response;
import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.ti.Eurna.E_urna.Entity.Aluno;
import br.com.puc.ti.Eurna.E_urna.VO.LoginVO;

@RestController
@RequestMapping("/api/v1")
public class LoginController {
  
  @GetMapping("/users")
  public Aluno getTodosUsuarios(){
    Aluno all = new Aluno();
    return all;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginVO> login(@RequestBody LoginVO login){
    LoginVO loginVO = new LoginVO();

    return loginVO;

  }
}
