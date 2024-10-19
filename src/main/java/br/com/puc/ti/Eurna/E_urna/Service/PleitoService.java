package br.com.puc.ti.Eurna.E_urna.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;

@Service
public interface PleitoService {
  List<Pleito> getAllPleito();
}
