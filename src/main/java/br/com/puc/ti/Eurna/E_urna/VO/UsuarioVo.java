package br.com.puc.ti.Eurna.E_urna.VO;

import br.com.puc.ti.Eurna.E_urna.Enum.TipoUsuarioEnum;
import lombok.Data;

@Data
public class UsuarioVo  {

  private Long id;
  private String nomeUsuario;
  private String numeroMatriculaPessoa;
  private String email;
  private String senhaUsuario;
  private String curso;
  private TipoUsuarioEnum tipoUsuarioEnum;
}
