package com.ppp.PautasParaProjetos.repository;

import com.ppp.PautasParaProjetos.dto.PautaDto;
import com.ppp.PautasParaProjetos.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Pauta findByNome(String nomeDaPauta);
}
