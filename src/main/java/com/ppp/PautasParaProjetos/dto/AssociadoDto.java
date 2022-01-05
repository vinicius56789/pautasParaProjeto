package com.ppp.PautasParaProjetos.dto;

import com.ppp.PautasParaProjetos.model.Associado;
import com.ppp.PautasParaProjetos.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociadoDto {

    private Long id;
    private String nome;

    public AssociadoDto (Associado associado){
        this.id = associado.getId();
        this.nome = associado.getNome();
    }

    public static List<AssociadoDto> toAssociadoDto(List<Associado> associados){
        return associados.stream().map(AssociadoDto::new).collect(Collectors.toList());
    }

    public Associado toAssociado(AssociadoRepository associadoRepository) {
        return new Associado(nome);
    }
}
