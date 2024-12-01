package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVotosVO;

@Service
public interface PleitoService {
  List<PleitoVo> getAllPleito();
  Pleito adicionarPleito(PleitoVo entity);
  List<Pleito> buscarPorDataTermino(boolean status);
  Pleito atualizarPleito(Long id);
  boolean removerPleito(Long id);
  Candidato getGanhadorPleito();
  PleitoVotosVO ganhadorPleitoVotosVO(Long id);
  Integer totalVotosPleito(Long id);
  List<CandidatoVo> candidatoPleito(Long id);
  PleitoVo getPleitoId(Long id);
}
