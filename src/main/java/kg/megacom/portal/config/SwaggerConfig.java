package kg.megacom.portal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "База знаний", description = "Раздел для агрегации общекорпоративных знаний."),
                @Tag(name = "Сотрудники компании", description = "Справочник сотрудников компании. Можно просмотреть по департаментам."),
                @Tag(name = "Новости компании", description = "Новости компании"),
                @Tag(name = "Заявки", description = "Заявки на офисное обслуживание"),
        }
)
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Портал MEGA!")
                        .description("Корпоративный портал компании MEGA!")
                        .version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("portal-api")
                .packagesToScan("kg.megacom.portal.controllers")
                .pathsToMatch("/**")
                .build();
    }
}
