package com.stockstore.stockstore.security.user.dto.user;

public record UserDetailDTO(Long id, String name, String lastname, String email, String phoneNumber, String role, boolean banned) {
}
