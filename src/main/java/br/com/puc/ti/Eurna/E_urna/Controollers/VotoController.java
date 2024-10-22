package br.com.puc.ti.Eurna.E_urna.Controollers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v4")
public class VotoController {

  @GetMapping("/votoAll")
  public  ResponseEntity<?>getVotoAll() {
      return new String();
  }
  
  
}
