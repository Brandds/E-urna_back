package br.com.puc.ti.Eurna.E_urna.Controollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Service.PleitoService;

@RestController
@RequestMapping("/api/v3")
public class PleitoController {
  
  @Autowired
  private PleitoService pleitoService;

  @GetMapping("/pleitoAll")
  public ResponseEntity<?> getAllPleito() {
      List<Pleito> listaPleito = pleitoService.getAllPleito();

      if(listaPleito.size() == 0){
        return new ResponseEntity<>("Não nenhum pleito cadastrado",HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(listaPleito, HttpStatus.OK);
  }
  
}
