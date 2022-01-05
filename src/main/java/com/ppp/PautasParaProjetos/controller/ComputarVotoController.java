package com.ppp.PautasParaProjetos.controller;

import com.ppp.PautasParaProjetos.dto.ComputarVotoDto;
import com.ppp.PautasParaProjetos.model.ComputarVoto;
import com.ppp.PautasParaProjetos.model.Pauta;
import com.ppp.PautasParaProjetos.repository.AssociadoRepository;
import com.ppp.PautasParaProjetos.repository.ComputarVotoRepository;
import com.ppp.PautasParaProjetos.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ws/votacao")
public class ComputarVotoController {

    @Autowired
    private ComputarVotoRepository computarVotoRepository;
    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    @GetMapping
    public List<?> listaVotosDeUmaPauta(@RequestParam(name = "idpauta") Pauta idPauta){
        List<ComputarVoto> votos = computarVotoRepository.findByPauta(idPauta);
        return ComputarVotoDto.toComputarVotoDto(votos);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastarVoto(@RequestBody @Valid ComputarVotoDto dto){
        ComputarVoto computarVoto = dto.toComputarVoto(associadoRepository, pautaRepository);
        computarVotoRepository.save(computarVoto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ComputarVotoDto(computarVoto));
    }
}
