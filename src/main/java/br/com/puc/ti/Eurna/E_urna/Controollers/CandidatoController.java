package br.com.puc.ti.Eurna.E_urna.Controollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Service.CandidatoService;



@Controller
@RequestMapping("/api/v2")
public class CandidatoController {

  @Autowired
  private CandidatoService candidatoService;

  @GetMapping("/candidatosAll")
  public ResponseEntity<List<Candidato>> getAllCandidato() {
      return new ResponseEntity<>(candidatoService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/buscarCandidato/{id}")
  public ResponseEntity<Candidato> buscarCandidato(@PathVariable Long id) {
      if(candidatoService.findByCandidato(id) == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(candidatoService.findByCandidato(id).get(), HttpStatus.OK);
  }
  
  
  
}
