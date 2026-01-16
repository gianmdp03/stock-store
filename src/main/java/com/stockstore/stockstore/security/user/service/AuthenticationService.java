package com.stockstore.stockstore.security.user.service;

import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdateDTO;
import com.stockstore.stockstore.security.user.dto.user.UserUpdatePassDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO register(UserRequestDTO request);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
    void forgotPassword(String email);
    String validateCode(String email, String code);
    boolean changeForgottenPassword(String email, String token, String password);
    boolean changePassword(String email, UserUpdatePassDTO dto);
    void updateUser(String email, UserUpdateDTO dto);
}
