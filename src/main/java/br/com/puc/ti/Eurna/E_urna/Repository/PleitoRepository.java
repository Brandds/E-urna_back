package br.com.puc.ti.Eurna.E_urna.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.puc.ti.Eurna.E_urna.Entity.Pleito;
import br.com.puc.ti.Eurna.E_urna.Enum.StatusPleito;


@Repository
public interface PleitoRepository  extends JpaRepository<Pleito, Long>{
  Optional<Pleito> findById(Long id);

  @Query("SELECT p FROM Pleito p LEFT JOIN FETCH p.candidatos c WHERE p.status = :status")
  List<Pleito> findPleitoStatus(@Param("status") StatusPleito status);

  @Modifying
  @Transactional
  @Query("UPDATE Pleito p  SET p.status = 'ENCERRADO' WHERE p.id = :id")
  int updateStatus(@Param("id") Long id);

  @Modifying
  @Transactional
  @Query("UPDATE Pleito p SET p.status = 'ENCERRADO' WHERE p.dataTermino <= CURRENT_TIMESTAMP")
  int encerrarPleitos();
}
