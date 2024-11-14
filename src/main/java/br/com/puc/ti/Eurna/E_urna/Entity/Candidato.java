package br.com.puc.ti.Eurna.E_urna.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "candidato")
@Entity
public class Candidato {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String curso;

  @Column(name = "numero_candidato", nullable = false, unique = true)
  private Long numeroCandidato;  
      
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "pleito_id", nullable = false)
  private Pleito pleito;

  public Candidato(){}
  public Candidato(Long id){
    this.id = id;
  }
  public Candidato(Long _numeroCandidato, Long _id){
    this.id = _id;
    this.numeroCandidato = _numeroCandidato;
  }
  
}
