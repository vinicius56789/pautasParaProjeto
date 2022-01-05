package com.ppp.PautasParaProjetos.dto;

import com.ppp.PautasParaProjetos.model.Associado;
import com.ppp.PautasParaProjetos.model.ComputarVoto;
import com.ppp.PautasParaProjetos.model.Pauta;
import com.ppp.PautasParaProjetos.model.Voto;
import com.ppp.PautasParaProjetos.repository.AssociadoRepository;
import com.ppp.PautasParaProjetos.repository.ComputarVotoRepository;
import com.ppp.PautasParaProjetos.repository.PautaRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
public class ComputarVotoDto {

    @Getter @Setter
    private Voto voto;
    @Getter @Setter
    private String nomeDoAssociado;
    @Getter @Setter
    private String nomeDaPauta;
    @Getter @Setter
    private String descricaoDaPauta;
    @Getter @Setter
    private String dataDeCriacaoDaPauta;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ComputarVotoDto(ComputarVoto computarVoto) {
        this.voto = computarVoto.getVoto();
        this.nomeDoAssociado = computarVoto.getAssociado().getNome();
        this.nomeDaPauta = computarVoto.getPauta().getNome();
        this.descricaoDaPauta = computarVoto.getPauta().getDescricao();
        this.dataDeCriacaoDaPauta = computarVoto.getPauta().getDataCriacaoPauta().format(formatter);
    }

    public static List<ComputarVotoDto> toComputarVotoDto(List<ComputarVoto> votos) {
        return votos.stream().map(ComputarVotoDto::new).collect(Collectors.toList());
    }

    public ComputarVoto toComputarVoto(AssociadoRepository associadoRepository, PautaRepository pautaRepository) {
        Associado associado = associadoRepository.findByNome(nomeDoAssociado);
        Pauta pauta = pautaRepository.findByNome(nomeDaPauta);
        return new ComputarVoto(voto, associado, pauta);
    }
}
