package com.dss.carrito.controladores;

import com.dss.carrito.servicios.DatabaseExportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("export")
public class AdminController {
    private DatabaseExportService databaseExportService;

    @GetMapping()
    public ResponseEntity<byte[]> downloadSQLFile() throws IOException {
        // Generamos el código SQL
        byte[] sqlByteContent = databaseExportService.export();

        // Creamos el archivo en memoria
        ByteArrayInputStream in = new ByteArrayInputStream(sqlByteContent);

        // Establecemos los headers para la descarga del archivo
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=productos.sql");

        // Retornamos el archivo para su descarga
        return new ResponseEntity<>(in.readAllBytes(), headers, HttpStatus.OK);
    }
//        return databaseExportService.export();
//        return "redirect:/";
}
