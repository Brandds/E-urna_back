package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Repository.PleitoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.PleitoService;

@Service
public class PleitoServiceImpl implements PleitoService {
  @Autowired
  private PleitoRepository pleitoRepository;

  @Override
  public List<Pleito> getAllPleito(){
    List<Pleito> pleitos = pleitoRepository.findAll();
    return pleitos;
  }
}
