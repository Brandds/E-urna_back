package br.com.puc.ti.Eurna.E_urna.VO;

import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import lombok.Data;

@Data
public class CandidatoVo {
  private String nomeCandidato;
  private String cursoCandidato;
  private Integer numeroCandidato;
  private PleitoVo pleito_Id_Candidato;
}
