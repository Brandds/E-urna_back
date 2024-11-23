package br.com.puc.ti.Eurna.E_urna.VO;

import lombok.Data;

@Data
public class VotosVO {
  private Long numero_candidato;
  private Long total_votos;

  public VotosVO(){}
  public VotosVO(Long numeroCandidato, Long totalVotos){
    this.numero_candidato = numeroCandidato;
    this.total_votos = totalVotos;
  }
}
