package br.com.puc.ti.Eurna.E_urna.Entity;

import br.com.puc.ti.Eurna.E_urna.VO.UsuarioVo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
  @Column(name= "num_Matricula", unique = true)
  private String numMatricula;
  private String email;
  private String senha;
  public Usuario(){}
  
  public void adicionarUsuario(UsuarioVo usuarioVo){
    setNome(usuarioVo.getNomeUsuario());
    setNumMatricula(usuarioVo.getNumeroMatriculaPessoa());
    setSenha(usuarioVo.getSenhaUsuario());
    setEmail(null);
  }
  
}
