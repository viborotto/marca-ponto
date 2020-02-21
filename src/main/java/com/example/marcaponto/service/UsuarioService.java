package com.example.marcaponto.service;

import com.example.marcaponto.exception.ResourceNotFoundException;
import com.example.marcaponto.model.Usuario;
import com.example.marcaponto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> lista(){
        return usuarioRepository.findAll();
    }

    public Usuario consultar(Long id) throws ResourceNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado para esse id :: " + id));
        return usuario;
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario alterar(Usuario usuario, Long id) throws ResourceNotFoundException {
        Usuario usuarioCadastrado = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado pra esse id :: " + id));

        usuarioCadastrado.setNomeCompleto(usuario.getNomeCompleto());
        usuarioCadastrado.setCpf(usuario.getCpf());
        usuarioCadastrado.setEmail(usuario.getEmail());

        return usuarioCadastrado ;
    }
}
