/**
 * パッケージ名：org.geocoderService.bean
 * ファイル名  ：SpringDocConfig.java
 * 
 * @author mbasa
 * @since Apr 28, 2021
 */
package org.geocoderService.bean;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * 説明：
 *
 */
@Configuration
public class SpringDocConfig {

    /**
     * コンストラクタ
     *
     */
    public SpringDocConfig() {
    }

    /*
     * @Bean
     * public Docket api() {
     * return new Docket(DocumentationType.SWAGGER_2)
     * .select()
     * .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
     * .paths(PathSelectors.any())
     * .build().
     * apiInfo(apiInfo());
     * }
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI geocoderServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("geocoderService API")
                        .description("pgGeocoder Web Service")
                        .version("v0.1")
                        .license(new License().name("GNU GENERAL PUBLIC LICENSE")
                                .url("https://www.gnu.org/licenses/gpl-3.0.en.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("pgGeocoder Documentation")
                        .url("https://github.com/mbasa/pgGeocoder/wiki"));
    }

    /*
     * private ApiInfo apiInfo() {
     * 
     * return new ApiInfo(
     * "geocoderService",
     * "pgGeocoder Web Service",
     * "v0.1",
     * null,
     * null,
     * "GNU GENERAL PUBLIC LICENSE",
     * "https://www.gnu.org/licenses/gpl-3.0.en.html",
     * Collections.emptyList());
     * }
     */
}
