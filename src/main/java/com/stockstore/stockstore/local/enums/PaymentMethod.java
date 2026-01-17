package com.stockstore.stockstore.local.enums;

public enum PaymentMethod {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    QR,
    BANK_TRANSFER;

    public static PaymentMethod fromStringOrDefault(String value){
        if(value == null){
            return CASH;
        }

        try {
            return PaymentMethod.valueOf(value.toUpperCase());
        }catch (IllegalArgumentException e){
            return CASH;
        }
    }
}
