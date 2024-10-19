package com.dss.carrito.servicios;

import com.dss.carrito.entidades.Producto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class CarritoService {
    private List<Producto> productos = new ArrayList<>();

    public void addProduct(Producto product){
        productos.add(product);
    }

    public List<Producto> getAllProducts(){
        return productos;
    }

    public void deleteProduct(Long id){
        productos.removeIf(producto -> producto.getId().equals(id));
    }

    public void checkout(){
        productos.clear();
    }

    public double getTotal(){
        return productos.stream().mapToDouble(Producto::getPrice).sum();
    }
}
