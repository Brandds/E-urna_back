package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVo;

@Service
public interface PleitoService {
  List<Pleito> getAllPleito();
  Pleito adicionarPleito(PleitoVo entity);
  List<Pleito> buscarPorDataTermino(boolean status);
  Pleito atualizarPleito(Long id);
  boolean removerPleito(Long id);
}
