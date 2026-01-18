package com.stockstore.stockstore.online.dto.address;

public record AddressDetailDTO(Long id,
                               String name,
                               String street,
                               String number,
                               String apartment,
                               String zipCode,
                               String city,
                               String province) {}
