package com.stockstore.stockstore.online.controller;

import com.mercadopago.resources.preference.Preference;
import com.stockstore.stockstore.online.dto.preference.PreferenceRequestDTO;
import com.stockstore.stockstore.online.service.MercadoPagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mp")
@RequiredArgsConstructor
public class MercadoPagoController {
    private final MercadoPagoService mercadoPagoService;

    @PostMapping("/single")
    public ResponseEntity<String> createPreference(@Valid @RequestBody List<PreferenceRequestDTO> dto){
        Preference preference = mercadoPagoService.createPreference(dto);
        if(preference == null){
            return ResponseEntity.internalServerError().body("Error al crear la preferencia");
        }
        return ResponseEntity.status(HttpStatus.OK).body(preference.getSandboxInitPoint());
    }

    @PostMapping("/cart")
    public ResponseEntity<String> createPreference(Authentication authentication){
        Preference preference = mercadoPagoService.createPreference(authentication.getName());
        if(preference == null){
            return ResponseEntity.internalServerError().body("Error al crear la preferencia");
        }
        return ResponseEntity.status(HttpStatus.OK).body(preference.getSandboxInitPoint());
    }
}
