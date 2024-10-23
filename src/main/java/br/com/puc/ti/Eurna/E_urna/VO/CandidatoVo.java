package br.com.puc.ti.Eurna.E_urna.VO;

import lombok.Data;

@Data
public class CandidatoVo {
  private Long id;
  private String nomeCandidato;
  private String cursoCandidato;
  private long numeroCandidato;
  private PleitoVo pleito_Id_Candidato;
}
