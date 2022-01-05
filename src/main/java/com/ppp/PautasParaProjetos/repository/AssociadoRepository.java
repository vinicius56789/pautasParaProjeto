package com.ppp.PautasParaProjetos.repository;

import com.ppp.PautasParaProjetos.dto.AssociadoDto;
import com.ppp.PautasParaProjetos.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Associado findByNome(String nomeDoAssociado);
}
