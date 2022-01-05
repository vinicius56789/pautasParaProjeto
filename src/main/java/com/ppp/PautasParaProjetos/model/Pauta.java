package com.ppp.PautasParaProjetos.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pauta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="GMT-3")
    private LocalDateTime dataCriacaoPauta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone="GMT-3")
    private LocalDateTime limiteDaSessao;
    private String descricao;

    public Pauta(String nome, LocalDateTime limiteDaSessao, String descricao){
        this.nome = nome;
        this.dataCriacaoPauta = LocalDateTime.now();
        this.limiteDaSessao = limiteDaSessao;
        this.descricao = descricao;
    }

}
