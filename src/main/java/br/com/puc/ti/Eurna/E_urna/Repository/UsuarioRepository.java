package br.com.puc.ti.Eurna.E_urna.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findBySenha(String senhaUsuario);
  Optional<Usuario> findByNumMatricula(String numMatricula);
}
