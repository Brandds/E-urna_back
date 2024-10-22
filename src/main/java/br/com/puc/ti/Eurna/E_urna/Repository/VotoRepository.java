package br.com.puc.ti.Eurna.E_urna.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.puc.ti.Eurna.E_urna.Entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

  
}
