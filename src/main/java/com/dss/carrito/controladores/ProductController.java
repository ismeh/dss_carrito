package com.dss.carrito.controladores;

import com.dss.carrito.entidades.Producto;
import com.dss.carrito.servicios.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {
    private ProductService productService;

    @GetMapping
    public List<Producto> getAllProduct(){
        return productService.getAllProduct();
    }

    //    Alternativa: devolver un producto utilizando ().orElse(null)
    @GetMapping(value="{id}")
    public Producto getProductById(@PathVariable Long id){
        Optional<Producto> product = productService.getProductById(id);
        return product.orElse(null);
    }

//    @PostMapping()
//    public void saveProduct(@RequestBody Producto product){
//        productService.saveProduct(product);
//    }

    @PostMapping()
    public String saveProduct(@ModelAttribute Producto product){
        productService.saveProduct(product);
        return "redirect:/form/producto";
    }

//    @DeleteMapping(value = "{id}")
//    public void deleteProduct(@PathVariable Long id){
//        productService.deleteProduct(id);
//    }

//    @PostMapping(value = "delete/{id}")
//    public void deleteProduct(@PathVariable Long id){
//        productService.deleteProduct(id);
//    }

    @PostMapping(value = "delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/productos";
    }

    @PostMapping(value = "update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Producto updatedProduct){
        Optional<Producto> optionalProduct = productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Producto existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());

            // Guardar cambios
            productService.saveProduct(existingProduct);
        }
        return "redirect:/productos";
    }

}
