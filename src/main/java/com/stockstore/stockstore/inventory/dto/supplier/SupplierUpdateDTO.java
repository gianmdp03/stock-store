package com.stockstore.stockstore.inventory.dto.supplier;

import jakarta.validation.constraints.Email;

public record SupplierUpdateDTO (String name, @Email String email, String phoneNumber){
}
