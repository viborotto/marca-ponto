package com.example.marcaponto.repository;

import com.example.marcaponto.model.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PontoRepository extends JpaRepository<Ponto, Long> {

}
