package br.com.puc.ti.Eurna.E_urna.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.puc.ti.Eurna.E_urna.Entity.Aluno;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  Optional<Aluno> findBySenha(String senhaUsuario);
  Optional<Aluno> findByNumMatricula(String numMatricula);;
}
