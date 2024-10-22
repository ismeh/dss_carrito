package com.dss.carrito.servicios;

import com.dss.carrito.entidades.Producto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@NoArgsConstructor
public class CarritoService {
    private List<Producto> productos = new ArrayList<>();

    public void addProduct(Producto product){
        productos.add(product);
    }

    public List<Producto> getAllProducts(){
        return productos;
    }

    public void deleteProduct(Long id){
        for (Producto producto : productos) {
            if(producto.getId().equals(id)){
                productos.remove(producto);
                return;
            }
        }
    }

    public void checkout(){
//        generarPDFFactura();
        productos.clear();
    }

    public double getTotal(){
        return productos.stream().mapToDouble(Producto::getPrice).sum();
    }

//    private generarPDFFactura(){
//        // Generar PDF
//
//    }
}
