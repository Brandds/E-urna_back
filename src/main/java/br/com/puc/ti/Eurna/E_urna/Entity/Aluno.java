package br.com.puc.ti.Eurna.E_urna.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private Long matricula;
  private String email;
  private String senha;
  public Aluno(){}
  
  public void adicionar(){
    String nome2 = getNome();
  }
  
}
