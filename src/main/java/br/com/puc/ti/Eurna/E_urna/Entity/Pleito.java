package br.com.puc.ti.Eurna.E_urna.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Pleito {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(name = "data_inicio")
  private Date dataInicio;

  @Column(name = "data_termino")
  private Date dataTermino;

  @Column(name = "votos_totais")
  private int votosTotais;
  
  @OneToMany(mappedBy = "pleito", cascade = CascadeType.ALL)
    private List<Candidato> candidatos;
}
