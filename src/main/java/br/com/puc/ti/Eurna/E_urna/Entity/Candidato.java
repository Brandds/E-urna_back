package br.com.puc.ti.Eurna.E_urna.Entity;

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
    private Integer numeroCandidato;  

  @ManyToOne
  @JoinColumn(name = "pleito_id", nullable = false)
  private Pleito pleito;

}
