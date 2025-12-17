package com.stockstore.stockstore.shared.service;

public interface EmaiLService {
    void sendSimpleEmail(String to, String subject, String body);
}
