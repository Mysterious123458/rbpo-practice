package ru.mtuci.coursemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProxyController {
    @GetMapping("/api/proxy")
    public String proxy(@RequestParam("targetUrl") String targetUrl) {
        RestTemplate rt = new RestTemplate();
        // nosemgrep: SSRF — подтверждённый учебный дефект, исключение конкретной строки
        // обосновано в docs/security-findings.md (изолированный учебный стенд).
        return rt.getForObject(targetUrl, String.class);
    }
}
