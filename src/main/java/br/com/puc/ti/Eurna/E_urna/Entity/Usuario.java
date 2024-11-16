package br.com.puc.ti.Eurna.E_urna.Entity;

import br.com.puc.ti.Eurna.E_urna.Enum.TipoUsuarioEnum;
import br.com.puc.ti.Eurna.E_urna.VO.UsuarioVo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(name= "num_matricula", unique = true)
  private String numMatricula;
  
  private String email;
  
  private String senha;
  
  private String curso;

  @Enumerated(EnumType.STRING)
  @Column(name ="tipo_usuario", nullable = false)
  private TipoUsuarioEnum tipoUsuarioEnum = TipoUsuarioEnum.ALUNO;
  public Usuario(){}
  
  public void adicionarUsuario(UsuarioVo usuarioVo){
    setNome(usuarioVo.getNomeUsuario());
    setNumMatricula(usuarioVo.getNumeroMatriculaPessoa());
    setSenha(usuarioVo.getSenhaUsuario());
    setEmail(usuarioVo.getEmail());
    setCurso(usuarioVo.getCurso());
    setTipoUsuarioEnum(usuarioVo.getNumeroMatriculaPessoa().length() > 6 ?
      TipoUsuarioEnum.ADMIN : TipoUsuarioEnum.ALUNO
    );
  }

  public UsuarioVo toVo(){
    UsuarioVo usuarioVo = new UsuarioVo();
    usuarioVo.setCurso(getCurso());
    usuarioVo.setEmail(getEmail());
    usuarioVo.setId(getId());
    usuarioVo.setNomeUsuario(getNome());
    usuarioVo.setTipoUsuarioEnum(getTipoUsuarioEnum());

    return usuarioVo;
  }

  public Usuario( Long _id){
    this.id = _id;
  }
  
 
}
