package com.example.music.R2Config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cloudflare.r2")
@Getter
@Setter
public class CloudflareProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
