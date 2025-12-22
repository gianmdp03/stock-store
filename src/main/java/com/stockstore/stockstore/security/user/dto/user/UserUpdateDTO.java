package com.stockstore.stockstore.security.user.dto.user;

import jakarta.validation.constraints.Email;

public record UserUpdateDTO (String name, String lastname, @Email String email, String username){
}
