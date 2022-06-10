package projetPOEIspring.poeidata.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${info.project.name}")
    private String projectName;

    @Value("${info.project.desc}")
    private String projectDescription;

    @Value("${info.project.version}")
    private String projectVersion;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(this.projectName)
                                .description(this.projectDescription)
                                .version(this.projectVersion)
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://springdoc.org")
                                )
                );
    }
}
