package com.stockstore.stockstore.shared.service.impl;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.stockstore.stockstore.shared.service.EmaiLService;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

@Service
public class GmailEmailServiceImpl implements EmaiLService {

    private static final String APPLICATION_NAME = "stockstore";
    private static final String ADMIN_EMAIL = "danteskate13@gmail.com";

    private Gmail getGmailService() throws Exception {
        // 1. Cargar el archivo una sola vez
        InputStream in = getClass().getResourceAsStream("/service-account-key.json");
        if (in == null) {
            throw new FileNotFoundException("No se encontró el archivo /service-account-key.json en resources");
        }

        // 2. Crear las credenciales con el orden correcto:

        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(in)
                .createDelegated(ADMIN_EMAIL)
                .createScoped(Collections.singleton(GmailScopes.GMAIL_SEND));

        // 3. Construir el servicio usando las credenciales ya creadas
        return new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    @Override
    public void sendSimpleEmail(String to, String subject, String bodyText) {
        try {
            Gmail service = getGmailService();

            // 1. Crear el MimeMessage (Jakarta Mail)
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage email = new MimeMessage(session);
            email.setFrom(new InternetAddress(ADMIN_EMAIL));
            email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
            email.setSubject(subject);
            email.setText(bodyText);

            // 2. Codificar para la API de Gmail
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessageBytes = buffer.toByteArray();
            String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);

            // 3. Crear el objeto Message de la API de Google
            Message message = new Message();
            message.setRaw(encodedEmail);

            // 4. Enviar
            service.users().messages().send("me", message).execute();

        } catch (Exception e) {
            throw new RuntimeException("Error al intentar enviar el correo vía Gmail API", e);
        }
    }
}
