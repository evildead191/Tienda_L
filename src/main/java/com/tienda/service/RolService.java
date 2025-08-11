package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {

    //Se usa para crear de forma automática una única instancia de la clase
    @Autowired
    private RolRepository rolRepository;

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional(readOnly = true)
    public List<Rol> getRoles() {
        var lista = rolRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Rol getRol(Rol rol) {
        return rolRepository.findById(rol.getIdRol()).orElse(null);
    }

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    @Transactional
    public boolean delete(Rol rol) {
        try {
            rolRepository.delete(rol);
            rolRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
