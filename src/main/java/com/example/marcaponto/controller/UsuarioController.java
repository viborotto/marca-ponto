package com.example.marcaponto.controller;


import com.example.marcaponto.exception.ResourceNotFoundException;
import com.example.marcaponto.model.Usuario;
import com.example.marcaponto.repository.UsuarioRepository;
import com.example.marcaponto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioService.lista();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        return usuarioService.consultar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario createUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable(value = "id") Long id, @Valid @RequestBody Usuario usuario) throws ResourceNotFoundException {
       return usuarioService.alterar(usuario, id);
    }
}
