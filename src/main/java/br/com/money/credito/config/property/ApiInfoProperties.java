package br.com.money.credito.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties("api-credito.api-info")
public class ApiInfoProperties {

    private String title;
    private String description;
    private String version;
    private String basePackage;

}
