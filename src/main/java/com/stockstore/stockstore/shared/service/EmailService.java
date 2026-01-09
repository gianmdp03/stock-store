package com.stockstore.stockstore.shared.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
