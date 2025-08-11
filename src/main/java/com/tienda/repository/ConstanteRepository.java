package com.tienda.repository;

import com.tienda.domain.Constante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstanteRepository extends JpaRepository<Constante, Long>{

    public Constante findByAtributo (String atributo);
    
}
