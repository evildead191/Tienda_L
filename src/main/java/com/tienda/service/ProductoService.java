package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoService {

    //Se usa para crear de forma automática una única instancia de la clase
    @Autowired
    private ProductoRepository productoRepository;

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        //Se usa "activos" si se desea limitar la vista a solo productos activas
        var lista = productoRepository.findAll();

        if (activos) { //Solo si se desean los registros de productos activas
            lista.removeIf(e -> !e.isActivo());
        }

        return lista;
    }

    @Transactional(readOnly = true)
    public Producto getProductos(Producto producto) {
        return productoRepository.findById(producto.getIdProducto()).orElse(null);
    }

    //Se usa para indicar que se hará una transacción a una BD
    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Transactional
    public boolean delete(Producto producto) {
        try {
            productoRepository.delete(producto);
            productoRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Se llama al método consultaAmpliada
    @Transactional(readOnly = true)
    public List<Producto> query1(double precioInf, double precioSup) {
        return productoRepository.findByPrecioBetweenOrderByPrecio(precioInf, precioSup);
    }

    //Se llama al método JPQL
    @Transactional(readOnly = true)
    public List<Producto> query2(double precioInf, double precioSup) {
        return productoRepository.consultaJPQL(precioInf, precioSup);
    }

    //Se llama al método SQL
    @Transactional(readOnly = true)
    public List<Producto> query3(double precioInf, double precioSup) {
        return productoRepository.consultaSQL(precioInf, precioSup);
    }

    @Transactional(readOnly = true)
    public List<Producto> getProductosPorNombre(String nombre) {
        return productoRepository.findByDescripcionContainingIgnoreCase(nombre);
    }

}
