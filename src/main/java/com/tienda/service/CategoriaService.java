package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    //Se usa para crear de forma automática una única instancia de la clase
    @Autowired
    private CategoriaRepository categoriaRepository;

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        //Se usa "activos" si se desea limitar la vista a solo categorias activas
        var lista = categoriaRepository.findAll();

        if (activos) { //Solo si se desean los registros de categorias activas
            lista.removeIf(e -> !e.isActivo());
        }

        return lista;
    }

    @Transactional(readOnly = true)
    public Categoria getCategorias(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Transactional
    public boolean delete(Categoria categoria) {
        try {
            categoriaRepository.delete(categoria);
            categoriaRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
