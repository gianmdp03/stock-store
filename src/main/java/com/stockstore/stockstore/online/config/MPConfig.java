package com.stockstore.stockstore.online.config;

import com.mercadopago.MercadoPagoConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MPConfig {
    @Value("${mp.access-token}")
    private String accessToken;

    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
        System.out.println("----- Mercado Pago Configurado Exitosamente -----");
    }
}
