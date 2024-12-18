package com.dss.carrito.servicios;

import com.dss.carrito.entidades.Producto;
import com.dss.carrito.repositorios.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private CarritoService carritoService;
    private ProductRepo productRepo;

    public List<Producto> getAllProduct(){
        return productRepo.findAll();
    }

//    Alternativa: devolver un producto utilizando ().orElse(null)
    public Optional<Producto> getProductById(Long id){
        return productRepo.findById(id);
    }

    public void saveProduct(Producto product){
        productRepo.save(product);
    }

    public void deleteProduct(Long id){
        carritoService.deleteProduct(id);
        productRepo.deleteById(id);
    }

    public void updateProduct(Long id, Producto product){
        productRepo.save(product);
    }

    public void updateProduct(Producto product){
        productRepo.save(product);
    }
}
