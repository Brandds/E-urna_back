package br.com.puc.ti.Eurna.E_urna.Entity;

import java.util.Date;
import java.util.List;

import br.com.puc.ti.Eurna.E_urna.Enum.StatusPleito;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
  
  @Column(name= "nome_pleito")
  private String nomePleito;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private StatusPleito status;

  @Column(name = "data_inicio")
  private Date dataInicio;

  @Column(name = "data_termino")
  private Date dataTermino;

  @Column(name = "votos_totais")
  private int votosTotais;
  
  
  @OneToMany(mappedBy = "pleito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Candidato> candidatos;

  public Pleito(Long id){
    this.id = id;
  }

  public Pleito(){}
  
  private StatusPleito calcularStatus(){
    Date hoje = new Date();
    
    if(hoje.before(dataTermino)){
      return StatusPleito.ATIVO;
    }else{
      return  StatusPleito.ENCERRADO;
    }


  }
}
