package com.stockstore.stockstore.security.user.service;

import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationRequestDTO;
import com.stockstore.stockstore.security.user.dto.authentication.AuthenticationResponseDTO;
import com.stockstore.stockstore.security.user.dto.user.UserRequestDTO;

public interface AuthenticationService {
    AuthenticationResponseDTO register(UserRequestDTO request);
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);
}
