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

import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Service.PleitoService;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVotosVO;





@RestController
@RequestMapping("/pleito")
public class PleitoController {
  
  @Autowired
  private PleitoService pleitoService;

  @GetMapping("/pleitoAll")
  public ResponseEntity<?> getAllPleito() {
      List<PleitoVo> listaPleito = pleitoService.getAllPleito();

      if(listaPleito.size() == 0){
        return new ResponseEntity<>("Não nenhum pleito cadastrado",HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(listaPleito, HttpStatus.OK);
  }
  
  @GetMapping("/pleitoAllStatusAtivo/{status}")
  public ResponseEntity<?> getALLPleitoDisponivel(@PathVariable boolean status) {
    List<Pleito> pleito = pleitoService.buscarPorDataTermino(status);
    if(pleito.size() > 0){
      return new ResponseEntity<>(pleito, HttpStatus.OK);
    }
    return new ResponseEntity<>("Não foi encontrado nenhum pleito", HttpStatus.NO_CONTENT);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<?> criarPleito(@RequestBody PleitoVo entity) {
      Pleito pleito = pleitoService.adicionarPleito(entity);

      if(pleito != null){
        return new ResponseEntity<>("Pleito criado com sucesso", HttpStatus.OK);
      }
      return new ResponseEntity<>("Erro ao criar pleito", HttpStatus.BAD_REQUEST);
  }
  
  @PutMapping("/atualizarPleito/{id}")
  public ResponseEntity atualizarPleito(@PathVariable Long id) {
      Pleito pleito = pleitoService.atualizarPleito(id);
      if(pleito  != null){
        return ResponseEntity.ok(pleito);
      }
      return ResponseEntity.badRequest().body("Não encontramos o pleito");
  }
 
  @PutMapping("/removerPleito/{id}")
  public ResponseEntity removerPleito(@PathVariable Long id) {
      return (pleitoService.removerPleito(id) ? 
      ResponseEntity.ok("Pleito excluido com sucesso") :
      ResponseEntity.badRequest().body("Pleito não foi encontrado")
      );
  }

  @GetMapping("/ganhadorPleito/{id}")
  public ResponseEntity getGanhadorPleito(@PathVariable Long id) {
    PleitoVotosVO ganhador = pleitoService.ganhadorPleitoVotosVO(id);
    return ResponseEntity.ok(ganhador);
  }

  @GetMapping("/allPleitosVoto/{id}")
  public ResponseEntity getAllPleitos(@PathVariable Long id) {
      return ResponseEntity.ok(pleitoService.totalVotosPleito(id));
  }

  @PostMapping("/buscarCadidato/{id}")
  public ResponseEntity<?> postMethodName(@PathVariable Long id) {
      List<CandidatoVo> lista = pleitoService.candidatoPleito(id);
      return  lista.size() > 0 ? ResponseEntity.ok(lista) : ResponseEntity.ok(lista);
  }

  @PostMapping("/buscarPleitoId/{id}")
  public ResponseEntity<PleitoVo> getPleitoId(@PathVariable Long id) {
      PleitoVo pleitoVo = pleitoService.getPleitoId(id);
      return ResponseEntity.ok(pleitoVo);
  }
  
  
}
