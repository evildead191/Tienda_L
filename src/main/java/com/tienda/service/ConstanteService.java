package com.tienda.service;

import com.tienda.domain.Constante;
import com.tienda.repository.ConstanteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConstanteService {

    //Se usa para crear de forma automática una única instancia de la clase
    @Autowired
    private ConstanteRepository constanteRepository;

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional(readOnly = true)
    public List<Constante> getConstantes() {
        //Se usa "activos" si se desea limitar la vista a solo constantes activas
        var lista = constanteRepository.findAll();
        return lista;
    }

    @Transactional(readOnly = true)
    public Constante getConstantes(Constante constante) {
        return constanteRepository.findById(constante.getIdConstante()).orElse(null);
    }

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional
    public void save(Constante constante) {
        constanteRepository.save(constante);
    }

    @Transactional
    public boolean delete(Constante constante) {
        try {
            constanteRepository.delete(constante);
            constanteRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional(readOnly = true)
    public Constante getConstantePorAtributo(String atributo) {
        return constanteRepository.findByAtributo(atributo);
    }

}
