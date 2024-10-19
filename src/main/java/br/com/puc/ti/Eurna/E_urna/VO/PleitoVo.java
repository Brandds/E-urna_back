package br.com.puc.ti.Eurna.E_urna.VO;

import java.util.Date;
import java.util.List;

import br.com.puc.ti.Eurna.E_urna.Entity.Candidato;
import lombok.Data;


@Data
public class PleitoVo {
  private Long id;
  private Date data_inicio;
  private Date data_termino;
  private int votoTotais;
  private List<Candidato> candidatos;
}
