package com.tienda.repository;

import com.tienda.domain.Rol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long>{
    
}
