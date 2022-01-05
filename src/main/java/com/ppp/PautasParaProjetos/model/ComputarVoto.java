package com.ppp.PautasParaProjetos.model;

import com.ppp.PautasParaProjetos.dto.AssociadoDto;
import com.ppp.PautasParaProjetos.dto.PautaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputarVoto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Voto voto;
    @ManyToOne
    private Associado associado;
    @OneToOne
    private Pauta pauta;

    public ComputarVoto(Voto voto, Associado associado, Pauta pauta) {
        this.associado = associado;
        this.voto = voto;
        this.pauta = pauta;
    }
}
