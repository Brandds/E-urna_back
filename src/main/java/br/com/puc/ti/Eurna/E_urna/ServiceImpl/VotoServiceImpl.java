package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Voto;
import br.com.puc.ti.Eurna.E_urna.Repository.VotoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.VotoService;

@Service
public class VotoServiceImpl implements VotoService {

  @Autowired
  private VotoRepository votoRepository;

  @Override
  public List<Voto> getAllVoto() {
    return votoRepository.findAll();
  }
  
}
