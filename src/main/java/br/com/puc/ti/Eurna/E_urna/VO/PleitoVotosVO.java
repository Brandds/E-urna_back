package br.com.puc.ti.Eurna.E_urna.VO;

import lombok.Data;

@Data
public class PleitoVotosVO {
  private String nomePleito;
  private String candidatoNome;
  private Long totalVotos;
  private Long votosPleito;
  
  public PleitoVotosVO(String nomePleito, String candidatoNome, Long totalVotos, Long votosPleito) {
    this.nomePleito = nomePleito;
    this.candidatoNome = candidatoNome;
    this.totalVotos = totalVotos;
    this.votosPleito = votosPleito;
  }
  public PleitoVotosVO(){}

}
