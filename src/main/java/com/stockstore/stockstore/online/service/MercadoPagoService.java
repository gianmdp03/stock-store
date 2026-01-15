package com.stockstore.stockstore.online.service;

import com.mercadopago.resources.preference.Preference;
import com.stockstore.stockstore.online.dto.preference.PreferenceRequestDTO;

import java.util.List;

public interface MercadoPagoService {
    Preference createPreference(List<PreferenceRequestDTO> requests);
    Preference createPreference(String email);
}
