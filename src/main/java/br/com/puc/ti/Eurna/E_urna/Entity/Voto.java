package br.com.puc.ti.Eurna.E_urna.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  @Temporal(TemporalType.DATE)
  private Date dataRegistro;

  
  @Column(name = "pleito_id")
  private Long pleitoId;

  @Column(name = "numero_candidato")
  private Long numeroCandidato;
  
  @Column(name = "usuario_id")
  private Long usuarioId;
}
