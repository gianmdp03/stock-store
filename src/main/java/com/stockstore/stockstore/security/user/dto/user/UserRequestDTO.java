package com.stockstore.stockstore.security.user.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank String name, @NotBlank String lastname,
                             @NotBlank @Email String email, @NotBlank String username, @NotBlank String password) {
}
