package com.dss.carrito.servicios;

import com.dss.carrito.entidades.Producto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    public ResponseEntity<byte[]> checkout() throws DocumentException, FileNotFoundException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("ticket.pdf"));
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//        Chunk chunk = new Chunk("Hello World", font);
//
//        document.add(chunk);
//        document.close();



        // Creamos el archivo en memoria
        ByteArrayInputStream in = new ByteArrayInputStream(generarFactura());

        // Establecemos los headers para la descarga del archivo
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=ticket.txt");

        //Vaciamos el carrito
        productos.clear();

        // Retornamos el archivo para su descarga
        return new ResponseEntity<>(in.readAllBytes(), headers, HttpStatus.OK);
    }

    public double getTotal(){
        return productos.stream().mapToDouble(Producto::getPrice).sum();
    }

    private byte[] generarFactura(){
        StringBuilder sb = new StringBuilder();
        int total = 0;

        //Introducción
        sb.append("Mi Tienda Online \n");
        //Fecha de hoy to string
        sb.append(java.time.LocalDate.now().toString() + "\n");

        //Productos
        sb.append("Descripción\tImporte\n");

        for (Producto producto : productos) {
            sb.append(producto.getName())
                    .append("\t")
                    .append(producto.getPrice())
                    .append("\n");
            total += producto.getPrice();
        }

        sb.append("Total: ")
                .append(total)
                .append("\n");

        return sb.toString().getBytes();

    }
}
