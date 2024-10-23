package br.com.puc.ti.Eurna.E_urna.VO;

import lombok.Data;

@Data
public class UsuarioVo  {

  private Long id;
  private String nomeUsuario;
  private String numeroMatriculaPessoa;
  private String email;
  private String senhaUsuario;
  private String curso;
}
