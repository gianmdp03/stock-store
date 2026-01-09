package com.stockstore.stockstore.security.user.controller;

import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;
import com.stockstore.stockstore.security.user.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDTO request) {
        AuthenticationResponseDTO authResponse = authenticationService.register(request);

        // Creamos la cookie
        ResponseCookie cookie = createAccessTokenCookie(authResponse.token());

        // La agregamos al header de la respuesta
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> authenticate(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO authResponse = authenticationService.authenticate(request);

        // Creamos la cookie
        ResponseCookie cookie = createAccessTokenCookie(authResponse.token());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    // Método auxiliar para crear la cookie con las configuraciones de seguridad
    private ResponseCookie createAccessTokenCookie(String token) {
        return ResponseCookie.from("accessToken", token)
                .httpOnly(true)       // Importante: JavaScript no puede leerla (protección XSS)
                .secure(false)        // Ponlo en 'true' si usas HTTPS (producción), 'false' para localhost
                .path("/")            // Disponible para toda la app
                .maxAge(24 * 60 * 60) // 24 horas (mismo tiempo que tu JwtService)
                .sameSite("Strict")   // O "Lax", ayuda a prevenir CSRF
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false) // Igual que en login
                .path("/")
                .maxAge(0) // Esto borra la cookie inmediatamente
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
