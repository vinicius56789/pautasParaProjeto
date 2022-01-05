package com.ppp.PautasParaProjetos.repository;

import com.ppp.PautasParaProjetos.model.ComputarVoto;
import com.ppp.PautasParaProjetos.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComputarVotoRepository extends JpaRepository<ComputarVoto, Long> {

    List<ComputarVoto> findByPauta(Pauta id);

}
