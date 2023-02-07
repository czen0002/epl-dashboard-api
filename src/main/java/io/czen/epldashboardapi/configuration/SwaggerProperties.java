package io.czen.epldashboardapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger")
public record SwaggerProperties(
        String title,
        String description,
        String version,
        String termsOfServiceUrl,
        String contactName,
        String contactUrl,
        String contactEmail,
        String license,
        String licenseUrl) {
}