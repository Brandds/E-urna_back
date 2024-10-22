package br.com.puc.ti.Eurna.E_urna.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "voto")
public class Voto {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "numero_votos")
  private Integer numeroVotos;

  @Column(name = "data_registro")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dataRegistro;

  @ManyToOne
  @JoinColumn(name = "matricula_candidato", nullable = false)
  private Candidato candidato;


}
