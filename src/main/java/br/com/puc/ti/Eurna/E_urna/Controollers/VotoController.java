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

import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.Service.VotoService;
import br.com.puc.ti.Eurna.E_urna.VO.VotoVo;



@RestController
@RequestMapping("/api/v4")
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
  public ResponseEntity buscarVoto(@PathVariable Long id) {
      Voto voto = votoService.getVotoUsuario(id);
      if(voto != null){
        return  new  ResponseEntity<>("Voto encontrado", HttpStatus.OK);
      }
      return new ResponseEntity<>("Não foi encontrado o voto", HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/votosCandidato/{id}")
  public Integer postMethodName(@PathVariable Long id) {
      return votoService.calcularVotoUusuario(id);
  }
  
  
  
}
