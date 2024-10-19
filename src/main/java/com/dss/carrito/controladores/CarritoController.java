package com.dss.carrito.controladores;

import com.dss.carrito.entidades.Producto;
import com.dss.carrito.servicios.CarritoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("cart")
public class CarritoController {
    private CarritoService carritoService;

    @RequestMapping(value = "products")
    public List<Producto> getAllProducts(){
        return carritoService.getAllProducts();
    }

    @PostMapping()
    public String saveProduct(@ModelAttribute Producto product){
        carritoService.addProduct(product);
        return "redirect:productos";
    }

    @PostMapping(value = "delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        carritoService.deleteProduct(id);
        return "redirect:/cart";
    }

    @PostMapping(value = "checkout")
    public String checkout(){
        carritoService.checkout();
        return "redirect:/cart";
    }

    public double getTotal(){
        return carritoService.getTotal();
    }
}
