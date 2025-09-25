package springboot_ecommerce.project.e_commerce.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
public class PricingClient {

    private final RestTemplate restTemplate;
    private final String pricingBaseUrl;

    public PricingClient(RestTemplate restTemplate,
                         @Value("${pricing.service.url}") String pricingBaseUrl) {
        this.restTemplate = restTemplate;
        this.pricingBaseUrl = pricingBaseUrl;
    }

    public BigDecimal getQuote(Long productId, int quantity) {
        String url = pricingBaseUrl + "/api/pricing/quote?productId=" + productId + "&quantity=" + quantity;
        String response = restTemplate.getForObject(url, String.class);
        return new BigDecimal(response);
    }
}

