package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.List;
import br.com.puc.ti.Eurna.E_urna.Entity.Voto;

import org.springframework.stereotype.Service;

@Service
public interface VotoService {
 List<Voto> getAllVoto();
  
}
