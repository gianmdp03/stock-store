package com.stockstore.stockstore.security.user.service.impl;

import com.stockstore.stockstore.exception.BadRequestException;
import com.stockstore.stockstore.exception.NotFoundException;
import com.stockstore.stockstore.security.user.Enum.Role;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdatePassDTO;
import com.stockstore.stockstore.security.user.model.User;
import com.stockstore.stockstore.security.user.repository.UserRepository;
import com.stockstore.stockstore.security.user.service.AuthenticationService;
import com.stockstore.stockstore.security.user.service.JwtService;
import com.stockstore.stockstore.shared.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    @Override
    @Transactional
    public AuthenticationResponseDTO register(UserRequestDTO request) {
        var user = new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.name(),
                request.lastname(),
                Role.USER
        );
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponseDTO(jwtToken);
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponseDTO(jwtToken);
    }

    @Override
    @Transactional
    public void forgotPassword(String email){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            SecureRandom secureRandom = new SecureRandom();
            String token = String.valueOf(100000 + secureRandom.nextInt(999999));
            user.setSecurityToken(token);
            user.setTokenExpirationTime(LocalDateTime.now().plusMinutes(20));
            String body = "Estimado usuario " + user.getName() + " " + user.getLastname() + "\n\n" +
                    "Aquí tiene el código de verificación para cambiar su contraseña: " + user.getSecurityToken() + "\n\n" +
                    "El mismo estará disponible por 20 minutos";
            emailService.sendEmail(email, "Cambiar contraseña", body);
        }
    }

    @Override
    @Transactional
    public String validateCode(String email, String code){
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            if(user.getSecurityToken().equals(code) && user.getTokenExpirationTime().isAfter(LocalDateTime.now()))
            {
                String token = UUID.randomUUID().toString();
                user.setSecurityToken(token);
                return token;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean changeForgottenPassword(String email, String token, String password)
    {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if(existingUser.isPresent()){
            User user = existingUser.get();
            if(user.getSecurityToken().equals(token)){
                user.setPassword(passwordEncoder.encode(password));
                user.setSecurityToken(null);
                user.setTokenExpirationTime(null);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean changePassword(String email, UserUpdatePassDTO dto) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new BadRequestException("Invalid request"));
        if(passwordEncoder.matches(dto.currentPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(dto.newPassword()));
            return true;
        }
        return false;
    }
}
