package br.com.puc.ti.Eurna.E_urna.Controollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.Service.VotoService;
import br.com.puc.ti.Eurna.E_urna.VO.VotoVo;



@RestController
@RequestMapping("/voto")
public class VotoController {

  @Autowired
  private VotoService votoService;

  @GetMapping("/votoAll")
  public  ResponseEntity<?>getVotoAll() {
      return new ResponseEntity<>(votoService.getAllVoto(), HttpStatus.OK);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<?> adicionarVoto(@RequestBody VotoVo entity) {
      Voto newVoto = votoService.adicionarVoto(entity);
      if(newVoto == null){
        return new ResponseEntity<>("Você já votou", HttpStatus.UNPROCESSABLE_ENTITY);
      }
      return new ResponseEntity<>("Foi cadastro o voto!", HttpStatus.OK);
  }
  
  @PostMapping("/buscarVoto/{id}")
  public ResponseEntity<Integer> buscarVoto(@PathVariable Long id) {
      Usuario usuario = votoService.getVotoUsuario(id);
      if(usuario != null){
        return ResponseEntity.ok(1);
      }
      return ResponseEntity.ok(0);

  }

  @PostMapping("/votosCandidato/{id}")
  public ResponseEntity<Integer> calcularVotos(@PathVariable Long id) {
      Integer valor =  votoService.calcularVotoUusuario(id);
      if(valor != null){
        return ResponseEntity.ok(valor);
      }
      return ResponseEntity.ok(0);
      
    }
  
  
  
}
