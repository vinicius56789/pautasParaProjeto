package com.ppp.PautasParaProjetos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ppp.PautasParaProjetos.model.Pauta;
import com.ppp.PautasParaProjetos.repository.PautaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PautaDto {

    private Long id;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacaoPauta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime limiteDaSessao;
    private String descricao;

    public PautaDto (Pauta pauta){
        this.id = pauta.getId();
        this.nome = pauta.getNome();
        this.dataCriacaoPauta = pauta.getDataCriacaoPauta();
        this.limiteDaSessao = pauta.getLimiteDaSessao();
        this.descricao = pauta.getDescricao();
    }

    public PautaDto(String nome, LocalDateTime limiteDaSessao, String descricao){
        this.nome = nome;
        this.limiteDaSessao = limiteDaSessao;
        this.descricao = descricao;
    }

    public static List<PautaDto> toPautaDto(List<Pauta> pauta){
        return pauta.stream().map(PautaDto::new).collect(Collectors.toList());
    }

    public Pauta toPauta(PautaRepository pautaRepository){
        return new Pauta(nome, limiteDaSessao, descricao);
    }

    public void verificaDataLimite(PautaDto pautaDto) {
        pautaDto.setDataCriacaoPauta(LocalDateTime.now());
        if (pautaDto.getLimiteDaSessao() == null) {
            pautaDto.setLimiteDaSessao(LocalDateTime.of(2000, 03, 15, 15, 31));
        }
        if (pautaDto.getLimiteDaSessao().compareTo(pautaDto.getDataCriacaoPauta()) != 1) {
            pautaDto.setLimiteDaSessao(pautaDto.getDataCriacaoPauta().plusMinutes(1));
        }
    }

}
