package com.dss.carrito.controladores;

import com.dss.carrito.entidades.Producto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return listProducts(model);
    }

    @RequestMapping(value = "form/producto")
    public String formProduct(Model model, @RequestParam(value="edit", required = false) Boolean edit, @ModelAttribute Producto product){
//        if (product != null && edit != null && edit){
////            Producto producto = new Producto();
////            producto.setId(product.getId());
////            producto.setName(product.getName());
////            producto.setPrice(product.getPrice());
//            model.addAttribute("prev_product", product);
//            return "formulario-producto?id=" + product.getId() + "&name=" + product.getName() + "&price=" + product.getPrice();
//
//        }
        return "formulario-producto";
    }

    @RequestMapping(value = "cart")
    public String cart(Model model){
        List<Producto> productosCarrito = carritoController.getAllProducts();
        model.addAttribute("allProductos", productosCarrito);
        model.addAttribute("total_price", carritoController.getTotal());
        return "carrito";
    }

    @GetMapping(value = "login")
    public String login(Model model, @RequestParam(value = "error", required = false) Boolean error){
        if (error != null && error)
            model.addAttribute("error", error);

        return "login";
    }

    @GetMapping(value = "logout")
    public String logout(){
        return "redirect:/";
    }


}
