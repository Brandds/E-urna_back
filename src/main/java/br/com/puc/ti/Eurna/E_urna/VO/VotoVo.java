package br.com.puc.ti.Eurna.E_urna.VO;

import java.util.Date;

import lombok.Data;

@Data
public class VotoVo {

  private Long id;
  private Integer numeroVotos;
  private Date dataRegistro;
  private UsuarioVo usuarioVo;
}
