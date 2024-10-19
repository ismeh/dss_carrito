package com.dss.carrito.controladores;

import com.dss.carrito.entidades.Producto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UriController {
    ProductController productController;
    CarritoController carritoController;

    @RequestMapping(value = "productos")
    public String listProducts(Model model){
//        Producto x = new Producto();
//        x.setName("asd");
//        x.setPrice(2);
//        productController.saveProduct(x);
        List<Producto> allProducts = productController.getAllProduct();
        model.addAttribute("allProductos", allProducts);
        return "productos";
    }

    @RequestMapping(value = "/")
    public String root(Model model){
        String template = listProducts(model);
        return template;
    }

    @RequestMapping(value = "form/producto")
    public String formProduct(){
        return "formulario-producto";
    }

    @RequestMapping(value = "cart")
    public String cart(Model model){
        List<Producto> productosCarrito = carritoController.getAllProducts();
        model.addAttribute("allProductos", productosCarrito);
        model.addAttribute("total_price", carritoController.getTotal());
        return "carrito";
    }

}
