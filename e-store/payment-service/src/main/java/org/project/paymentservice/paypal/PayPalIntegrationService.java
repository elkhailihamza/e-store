package org.project.paymentservice.paypal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PayPalIntegrationService {

    @Value("${paypal.client-id}")
    private String clientId;

    @Value("${paypal.client-secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    private final RestTemplate restTemplate = new RestTemplate();

    public String obtainAccessToken() {
        String url;
        if ("sandbox".equalsIgnoreCase(mode)) {
            url = "https://api-m.sandbox.paypal.com/v1/oauth2/token";
        } else {
            url = "https://api-m.paypal.com/v1/oauth2/token";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

        ResponseEntity<TokenResponse> response =
                restTemplate.postForEntity(url, request, TokenResponse.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to obtain PayPal token: " + response.getBody());
        }

        return response.getBody().access_token;
    }

    public void createPayPalPayment(Double amount, String currency) {
        String token = obtainAccessToken();

        String paymentUrl;
        if ("sandbox".equalsIgnoreCase(mode)) {
            paymentUrl = "https://api-m.sandbox.paypal.com/v1/payments/payment";
        } else {
            paymentUrl = "https://api-m.paypal.com/v1/payments/payment";
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);


        String json = """
        {
          "intent": "sale",
          "payer": { "payment_method": "paypal" },
          "transactions": [{
            "amount": {
              "total": "%s",
              "currency": "%s"
            },
            "description": "E-Sales Payment"
          }],
          "redirect_urls": {
            "return_url": "https://example.com/success",
            "cancel_url": "https://example.com/cancel"
          }
        }
        """.formatted(amount, currency);

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<String> response =
                restTemplate.postForEntity(paymentUrl, request, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(
                    "Failed to create PayPal payment: " + response.getBody());
        }


    }

    static class TokenResponse {
        public String access_token;
        public String token_type;
        public String app_id;
        public int expires_in;
    }
}
