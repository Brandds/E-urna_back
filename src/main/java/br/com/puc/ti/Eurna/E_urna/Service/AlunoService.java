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
  
  public boolean validarUsuario(String matricula , String senha){
    Optional<Aluno> userMatricula = alunoRepository.findByNumMatricula(matricula);

    if(userMatricula.isPresent()){
      Aluno aluno = userMatricula.get();

      return aluno.getSenha() != null && aluno.getSenha().equals(senha);
    }
    return false;

  }
}
