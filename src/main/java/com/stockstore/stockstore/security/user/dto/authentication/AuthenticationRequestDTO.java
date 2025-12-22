package com.stockstore.stockstore.security.user.dto.authentication;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(String email, String username, @NotBlank String password) {
}
