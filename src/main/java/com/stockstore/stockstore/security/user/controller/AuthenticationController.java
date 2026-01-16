package com.stockstore.stockstore.security.user.controller;

import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationPasswordDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;
import com.stockstore.stockstore.security.user.service.AuthenticationService;
import com.stockstore.stockstore.shared.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserRequestDTO request) {
        AuthenticationResponseDTO authResponse = authenticationService.register(request);
        ResponseCookie cookie = createAccessTokenCookie(authResponse.token());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> authenticate(@RequestBody AuthenticationRequestDTO request) {
        AuthenticationResponseDTO authResponse = authenticationService.authenticate(request);
        ResponseCookie cookie = createAccessTokenCookie(authResponse.token());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping("/password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody AuthenticationPasswordDTO dto){
        authenticationService.forgotPassword(dto.email());
        return ResponseEntity.status(HttpStatus.OK).body("Si el email es correcto, se envió un código de verificación");
    }

    @PostMapping("/verify/{code}")
    public ResponseEntity<String> validateCode(@Valid @RequestBody AuthenticationPasswordDTO dto, @PathVariable String code){
        String token = authenticationService.validateCode(dto.email(), code);
        if(token == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code is not valid");
        }
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PatchMapping("/password/change")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody AuthenticationPasswordDTO dto){
        boolean flag = authenticationService.changePassword(dto.email(), dto.token(), dto.password());
        if(!flag){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
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

    // Método auxiliar para crear la cookie con las configuraciones de seguridad
    private ResponseCookie createAccessTokenCookie(String token) {
        return ResponseCookie.from("accessToken", token)
                .httpOnly(true)       // JavaScript no puede leerla
                .secure(false)        // True para HTTPS, 'false' para localhost
                .path("/")            // Disponible para toda la app
                .maxAge(24 * 60 * 60) // 24 horas (mismo tiempo que JwtService)
                .sameSite("Strict")   // O "Lax", ayuda a prevenir CSRF
                .build();
    }
}
