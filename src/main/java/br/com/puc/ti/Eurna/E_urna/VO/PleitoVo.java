package br.com.puc.ti.Eurna.E_urna.VO;

import java.util.Date;
import java.util.List;

import br.com.puc.ti.Eurna.E_urna.Enum.StatusPleito;
import lombok.Data;


@Data
public class PleitoVo {
  private Long id;
  private String nomePleito;
  private StatusPleito status;
  private Date data_inicio;
  private Date data_termino;
  private int votoTotais;
  private List<CandidatoVo> candidatos;

  public PleitoVo(Long id){
    this.id = id;
  }
}
