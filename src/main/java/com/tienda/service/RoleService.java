package com.tienda.service;

import com.tienda.domain.Role;
import com.tienda.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    //Se usa para crear de forma automática una única instancia de la clase
    @Autowired
    private RoleRepository roleRepository;

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        //Se usa "activos" si se desea limitar la vista a solo roles activas
        var lista = roleRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Role getRole(Role role) {
        return roleRepository.findById(role.getRol()).orElse(null);
    }

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public boolean delete(Role role) {
        try {
            roleRepository.delete(role);
            roleRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
