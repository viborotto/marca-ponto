package com.example.marcaponto.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "pontos")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Usuario usuario;

    @Column(name = "dataHoraBatida")
    private LocalDateTime dataHoraBatida;

    @Column(name = "tipoBatida")
    private String tipoBatida;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDate() {
        return dataHoraBatida;
    }

    public String getTipoBatida() {
        return tipoBatida;
    }

    public void setDate(LocalDateTime dataHoraBatida) {
        this.dataHoraBatida = dataHoraBatida;
    }

    public void setTipoBatida(String tipoBatida) {
        this.tipoBatida = tipoBatida;
    }
}
