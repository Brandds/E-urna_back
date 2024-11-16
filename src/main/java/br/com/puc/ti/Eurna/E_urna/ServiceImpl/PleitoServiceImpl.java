package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Enum.StatusPleito;
import br.com.puc.ti.Eurna.E_urna.Repository.CandidatoRepository;
import br.com.puc.ti.Eurna.E_urna.Repository.PleitoRepository;
import br.com.puc.ti.Eurna.E_urna.Service.PleitoService;
import br.com.puc.ti.Eurna.E_urna.VO.CandidatoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVo;
import br.com.puc.ti.Eurna.E_urna.VO.PleitoVotosVO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;





@Service
public class PleitoServiceImpl implements PleitoService {

  @PersistenceContext
	private EntityManager entityManager;

  @Autowired
  private PleitoRepository pleitoRepository;
  
  @Autowired
  private CandidatoRepository candidatoRepository;

  public Pleito toEntity(PleitoVo vo){
    Pleito newPleito = new Pleito();

    newPleito.setNomePleito(vo.getNomePleito());
    newPleito.setDataInicio(vo.getData_inicio());
    newPleito.setDataTermino(vo.getData_termino());
    newPleito.setVotosTotais(vo.getVotoTotais());
    newPleito.setStatus(vo.getStatus().ATIVO);

    return newPleito;
  }

  @Override
  public List<PleitoVo> getAllPleito(){
    pleitoRepository.encerrarPleitos();
    List<Pleito> pleitos = pleitoRepository.findAll();
    List<PleitoVo> pleitoVO = new ArrayList<>();

    for(Pleito pleito : pleitos) pleitoVO.add(pleito.toVo());
    
    return pleitoVO;
  }
  
  @Override
  public Pleito adicionarPleito(PleitoVo vo){
    Pleito pleito = toEntity(vo);
    pleitoRepository.save(pleito);

    if(vo.getCandidatos().size() != 0){
      int tamnhanho = vo.getCandidatos().size();
      List<CandidatoVo> listCandidato = vo.getCandidatos();
      for(int i = 0; tamnhanho > i;i++ ){
        Candidato candidato = new  Candidato();
        candidato = addCandidato(listCandidato.get(i), pleito ); 
        candidatoRepository.save(candidato);
      }
    }
    return pleito;
  }

  public Candidato addCandidato(CandidatoVo vo, Pleito pleito){
    Optional<Candidato> candidato =  candidatoRepository.findById(vo.getId());
    
    if(candidato.isPresent()){
      candidato.get().setPleito(pleito);
    }
    return candidato.get();
  }
  
  @Override
  public List<Pleito> buscarPorDataTermino(boolean status){
    if(status){
      return pleitoRepository.findPleitoStatus(StatusPleito.ATIVO);
    }
    return pleitoRepository.findPleitoStatus(StatusPleito.ENCERRADO);
  }

  @Override
  public Pleito atualizarPleito(Long id) {
    int valor = pleitoRepository.updateStatus(id);

    if(valor == 0){
      return null;
    }
    Optional<Pleito> voto = pleitoRepository.findById(id);
    return voto.get();
  }

  @Override
  public boolean removerPleito(Long id){
    Optional<Pleito> pleito = pleitoRepository.findById(id);

    if(pleito.isPresent()){
      pleitoRepository.delete(pleito.get());
      return true;
    }

    return false;
  }

  @Override
  public Candidato getGanhadorPleito() {
    // TODO Auto-generated method stub
    return null;
    
  }

  @Override
  public PleitoVotosVO ganhadorPleitoVotosVO(Long id) {

    StringBuilder hql = new StringBuilder();
    hql = hqlGanhadorPleito(hql);
    Query query = entityManager.createNativeQuery(hql.toString());
    query.setParameter("pleitoId", id);

    List<Object[]> lista = query.getResultList();
    PleitoVotosVO pleitoVotosVO = new PleitoVotosVO();
    for(Object[] obj : lista){
      if(obj[0] != null){
        pleitoVotosVO.setNomePleito((String) obj[0]);
      }
      if(obj[1] != null){
        pleitoVotosVO.setCandidatoNome((String) obj[1]);
      }
      if(obj[2] != null){
        pleitoVotosVO.setTotalVotos((Long) obj[2]);
      }
    }
    return pleitoVotosVO;
  }

  @Override
  public Integer totalVotosPleito(Long id) {
    StringBuilder hql = new StringBuilder();
    hql = hqlTotalVotosPleito(hql);
    Query query = entityManager.createNativeQuery(hql.toString());
    query.setParameter("pleitoId", id);

    BigDecimal receberValor = (BigDecimal) query.getSingleResult();
    Integer valor =  receberValor != null ? receberValor.intValue() + 1 : null;
    return valor;

  }
  
  private StringBuilder hqlGanhadorPleito(StringBuilder hql){
    hql.append(" SELECT ");
    hql.append(" cast(p.nome_pleito as varchar) as nomePleito, ");
    hql.append(" cast(c.nome as varchar) as candidatoNome, ");
    hql.append(" SUM(v.numero_votos) as totalVotos ");
    hql.append(" FROM ");
    hql.append(" voto v ");
    hql.append(" JOIN ");
    hql.append(" candidato c ON v.numero_candidato = c.id ");
    hql.append(" JOIN ");
    hql.append(" pleito p ON v.pleito_id = p.id");
    hql.append(" WHERE ");
    hql.append(" v.pleito_id = :pleitoId");
    hql.append(" GROUP BY ");
    hql.append(" p.nome_pleito, c.nome");
    hql.append(" LIMIT 1");
    return hql;
  }

  private StringBuilder hqlTotalVotosPleito(StringBuilder hql){
    hql.append(" SELECT ");
    hql.append(" SUM(SUM(v.numero_votos)) OVER ");
    hql.append(" (PARTITION BY p.nome_pleito) AS votosPleito");
    hql.append(" FROM ");
    hql.append(" voto v");
    hql.append(" JOIN ");
    hql.append(" candidato c ON v.numero_candidato = c.id ");
    hql.append(" JOIN ");
    hql.append(" pleito p ON v.pleito_id = c.id ");
    hql.append(" WHERE ");
    hql.append(" v.pleito_id = :pleitoId ");
    hql.append(" GROUP BY ");
    hql.append(" p.nome_pleito, c.nome ");
    hql.append(" LIMIT 1");
    return hql;
  }
  @Override
  public List<CandidatoVo> candidatoPleito(Long id) {
    List<Candidato> candidatos = candidatoRepository.findByPleitoId(id);
    List<CandidatoVo> lista = new ArrayList<>();

    if(candidatos.size() > 0){
      for(Candidato candidato : candidatos){
        lista.add(candidato.toVo());
      }
    }

    return lista;
  }

  
}
