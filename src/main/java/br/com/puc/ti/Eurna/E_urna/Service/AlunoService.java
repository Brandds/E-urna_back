package br.com.puc.ti.Eurna.E_urna.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Aluno;
import br.com.puc.ti.Eurna.E_urna.Repository.AlunoRepository;

@Service
public class AlunoService {

  @Autowired
  private AlunoRepository alunoRepository;
  
  public boolean validarUsuario(String username, String senha){
    Optional<Aluno> useOptional =  alunoRepository.findByNome(username);

    if(useOptional.isPresent()){
      Aluno aluno = useOptional.get();

      return aluno.getSenha().equals(senha);
    }
    return false;

  }
}
