package br.com.puc.ti.Eurna.E_urna.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.ti.Eurna.E_urna.Entity.Usuario;
import br.com.puc.ti.Eurna.E_urna.Repository.UsuarioRepository;
import br.com.puc.ti.Eurna.E_urna.Service.UsuarioService;
import br.com.puc.ti.Eurna.E_urna.VO.UsuarioVo;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioRepository usuarioRepository;
  
  public Usuario toEntity(Usuario usuario, UsuarioVo vo){
    usuario.setCurso(vo.getCurso());
    usuario.setEmail(vo.getEmail());
    usuario.setSenha(vo.getSenhaUsuario());
    return usuario;
  }

  @Override
  public UsuarioVo validarUsuario(String matricula, String senha) {
    Optional<Usuario> userMatricula = usuarioRepository.findByNumMatricula(matricula);

    if (userMatricula.isPresent()) {
      Usuario usuario = userMatricula.get();

      if(usuario.getSenha() != null && usuario.getSenha().equals(senha)){
        return usuario.toVo();
      }
    }
    return null;
  }

  @Override
  public boolean validarCadastroUsuario(String newMatricula) {
    Optional<Usuario> existeMatricula = usuarioRepository.findByNumMatricula(newMatricula);

    if (existeMatricula.isPresent()) {
      return false;  // Matrícula já existe
    }
    return true;  // Matrícula disponível
  }

  @Override
  public List<UsuarioVo> getUsuarios(){
    List<Usuario> usuarios = usuarioRepository.findAll();
    List<UsuarioVo> usuarioVo = new  ArrayList<>();

    for(Usuario usuarioVo2 : usuarios){
      usuarioVo.add(usuarioVo2.toVo());
    }
    return usuarioVo;
  }

  @Override
  public Usuario updateUsuario(Long id, UsuarioVo usuarioVo) {
    Optional<Usuario> usuario = usuarioRepository.findById(id);

    if(usuario.isPresent()){
      Usuario updateUsuario = toEntity(usuario.get(), usuarioVo);
      usuarioRepository.save(updateUsuario);
      return usuario.get();
    }
    
    return null;
  }

  @Override 
  public boolean removerUsuario(Long id)
  {
      Optional<Usuario> usuario =usuarioRepository.findById(id);

      if(usuario.isPresent()){
        usuarioRepository.delete(usuario.get());
        return true;
      }
      return false;

  }
}
