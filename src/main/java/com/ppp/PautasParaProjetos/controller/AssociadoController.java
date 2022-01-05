package com.ppp.PautasParaProjetos.controller;

import com.ppp.PautasParaProjetos.dto.AssociadoDto;
import com.ppp.PautasParaProjetos.model.Associado;
import com.ppp.PautasParaProjetos.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ws/associados")
public class AssociadoController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @GetMapping
    public List<AssociadoDto> listaDeAssociados(){
        List<Associado> associados = associadoRepository.findAll();
        return AssociadoDto.toAssociadoDto(associados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDto> listaAssociadoPeloId(@PathVariable Long id){
        Optional<Associado> associado = associadoRepository.findById(id);
        if(associado.isPresent()){
            return ResponseEntity.ok(new AssociadoDto(associado.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<AssociadoDto> cadastrarNovoAssociado(@RequestBody @Valid AssociadoDto dto){
        Associado associado = dto.toAssociado(associadoRepository);
        associadoRepository.save(associado);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AssociadoDto(associado));
    }

    @DeleteMapping("/del/{id}")
    @Transactional
    public ResponseEntity<String> deletarAssociado(@PathVariable Long id){
        Optional<Associado> associado = associadoRepository.findById(id);
        if(associado.isPresent()) {
            associadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar esse associado: " + id);
    }

}
