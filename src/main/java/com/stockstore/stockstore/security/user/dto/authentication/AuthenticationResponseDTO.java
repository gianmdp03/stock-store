package com.stockstore.stockstore.security.user.dto.authentication;

import com.stockstore.stockstore.security.user.dto.user.UserDetailDTO;

public record AuthenticationResponseDTO(String token, UserDetailDTO dto) {
}
