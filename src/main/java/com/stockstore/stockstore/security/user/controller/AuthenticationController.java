package com.stockstore.stockstore.security.user.controller;

import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationPasswordDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserDetailDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdateDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdatePassDTO;
import com.stockstore.stockstore.security.user.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

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

    @PostMapping("/logged/forgot")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody AuthenticationPasswordDTO dto){
        authenticationService.forgotPassword(dto.email());
        return ResponseEntity.status(HttpStatus.OK).body("Si el email es correcto, se envió un código de verificación");
    }

    @PostMapping("/logged/verify/{code}")
    public ResponseEntity<String> validateCode(@Valid @RequestBody AuthenticationPasswordDTO dto, @PathVariable String code){
        String token = authenticationService.validateCode(dto.email(), code);
        if(token == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code is not valid");
        }
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PatchMapping("/logged/forgot/change")
    public ResponseEntity<Void> changeForgottenPassword(@Valid @RequestBody AuthenticationPasswordDTO dto){
        boolean flag = authenticationService.changeForgottenPassword(dto.email(), dto.token(), dto.password());
        if(!flag){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/logged/password")
    public ResponseEntity<Void> changePassword(Authentication authentication, @Valid @RequestBody UserUpdatePassDTO dto){
        boolean flag = authenticationService.changePassword(authentication.getName(), dto);
        if(!flag){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/logged/user")
    public ResponseEntity<Void> updateUser(Authentication authentication, @Valid @RequestBody UserUpdateDTO dto){
        authenticationService.updateUser(authentication.getName(), dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/admin")
    public ResponseEntity<Page<UserDetailDTO>> listUsers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.listUsers(pageable));
    }

    @GetMapping("/admin/banned")
    public ResponseEntity<Page<UserDetailDTO>> listBannedUsers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.listBannedUsers(pageable));
    }

    @GetMapping("/admin/employees")
    public ResponseEntity<Page<UserDetailDTO>> listEmployees(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.listEmployees(pageable));
    }

    @GetMapping("/admin/user/{email}")
    public ResponseEntity<UserDetailDTO> getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.getUserByEmail(email));
    }

    @PostMapping("/admin/promote/employee/{id}")
    public ResponseEntity<Void> promoteToEmployee(@PathVariable Long id){
        authenticationService.promoteToEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/admin/promote/admin/{id}")
    public ResponseEntity<Void> promoteToAdmin(@PathVariable Long id){
        authenticationService.promoteToAdmin(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/admin/ban/{id}")
    public ResponseEntity<Void> toggleBan(@PathVariable Long id){
        authenticationService.toggleBan(id);
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
