package com.btg.operaciones.controllers;

import com.btg.operaciones.models.TransaccionRequest;
import com.btg.operaciones.services.transaccion.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService){
        this.transaccionService = transaccionService;
    }

    @PostMapping()
    public ResponseEntity<?> crearTransaccion(@Valid @RequestBody TransaccionRequest transaccionRequest){
        var response = this.transaccionService.guardarTransaccion(transaccionRequest);
        return ResponseEntity.ok(response);
    }


}
