package com.example.marcaponto.service;

import com.example.marcaponto.exception.ResourceNotFoundException;
import com.example.marcaponto.model.Ponto;
import com.example.marcaponto.model.Usuario;
import com.example.marcaponto.repository.PontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    public List<Ponto> lista(){
        return pontoRepository.findAll();
    }

    public Ponto save(Ponto ponto) {

        pontoRepository.save(ponto);

        Optional<Ponto> novoPonto =  pontoRepository.findById(ponto.getId()) ;

        return novoPonto.get();
    }



}
