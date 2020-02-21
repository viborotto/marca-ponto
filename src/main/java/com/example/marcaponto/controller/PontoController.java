package com.example.marcaponto.controller;

import com.example.marcaponto.exception.ResourceNotFoundException;
import com.example.marcaponto.model.Ponto;
import com.example.marcaponto.repository.PontoRepository;
import com.example.marcaponto.repository.UsuarioRepository;
import com.example.marcaponto.service.PontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ponto")
public class PontoController {

    @Autowired
    private PontoService pontoService;

    @GetMapping("/lista")
    public List<Ponto> getAllPontos(){
        return pontoService.lista();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ponto createPonto(@Valid @RequestBody Ponto ponto) {
        return pontoService.save(ponto);
    }

}
