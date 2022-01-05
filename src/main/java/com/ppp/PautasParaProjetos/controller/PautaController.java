package com.ppp.PautasParaProjetos.controller;

import com.ppp.PautasParaProjetos.dto.PautaDto;
import com.ppp.PautasParaProjetos.model.Pauta;
import com.ppp.PautasParaProjetos.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ws/pautas")
public class PautaController {

    @Autowired
    private PautaRepository pautaRepository;

    @GetMapping
    public List<PautaDto> listaDePautas(){
        List<Pauta> pautas = pautaRepository.findAll();
        return PautaDto.toPautaDto(pautas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listaPautaPeloId(@PathVariable Long id){
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if(pauta.isPresent()){
            return ResponseEntity.ok(new PautaDto(pauta.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado uma pauta com esse id: " + id);
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<?> cadastrarPauta(@RequestBody @Valid PautaDto dto){
        PautaDto pautaDto = new PautaDto(dto.getNome(), dto.getLimiteDaSessao(), dto.getDescricao());
        pautaDto.verificaDataLimite(pautaDto);
        Pauta pauta = pautaDto.toPauta(pautaRepository);
        pautaRepository.save(pauta);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PautaDto(pauta));
    }

    @DeleteMapping("/del")
    @Transactional
    public ResponseEntity<?> deletarTodosOsAssociados(){
        List<Pauta> pautas = pautaRepository.findAll();
        if(!pautas.isEmpty()){
            pautaRepository.deleteAll(pautas);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foram encontradas pautas para exclusão");
    }

    @DeleteMapping("/del/{id}")
    @Transactional
    public ResponseEntity<?> deletarPauta(@PathVariable Long id){
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if(pauta.isPresent()){
            pautaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado a pauta de id: " + id);
    }

}
