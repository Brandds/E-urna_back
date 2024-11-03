package br.com.puc.ti.Eurna.E_urna.Controollers;

import java.util.List;
import java.util.Optional;

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

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Service.CandidatoService;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

  @Autowired
  private CandidatoService candidatoService;

  @GetMapping("/candidatosAll")
  public ResponseEntity<List<Candidato>> getAllCandidato() {
      return new ResponseEntity<>(candidatoService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/buscarCandidato/{id}")
  public ResponseEntity<?> buscarCandidato(@PathVariable Long id) {
      Optional<Candidato> candidato = candidatoService.findByCandidato(id);
      if (candidato.isEmpty()) { 
        String menssagemErro = "Candidato não encontrado";
        return new ResponseEntity<>(menssagemErro ,HttpStatus.NOT_FOUND); // Retorna 404 se não encontrado
    }

    // Retorna o candidato encontrado com o status 200
    return new ResponseEntity<>(candidato.get(), HttpStatus.OK);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<?> criarCandidato(@RequestBody CandidatoVo entity) {
      Candidato candidato = candidatoService.adicionarCandidato(entity);

      if(candidato != null){
        String mensagem = "Candidato criado com sucesso";
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
      }
      return new ResponseEntity<>("Candidato já criado", HttpStatus.NOT_FOUND) ;
  }
  
  @PutMapping("/updateCandidato/{id}")
  public ResponseEntity updateCandidato(@PathVariable Long id , @RequestBody CandidatoVo entity) {
      Candidato candidato = candidatoService.updateCandidato(id, entity);

      if(candidato != null) {
        return ResponseEntity.ok(candidato);
      }
      return ResponseEntity.badRequest().body("Não encontramos esse candidato");
  }
  @PutMapping("/removerCandidato/{id}")
  public ResponseEntity removerCandidato(@PathVariable Long id) {
      return (candidatoService.removerCandidato(id) ? 
      ResponseEntity.ok("Candidato removido com sucesso") : 
      ResponseEntity.badRequest().body("Candidato não encontrado"));
  }

  
}
