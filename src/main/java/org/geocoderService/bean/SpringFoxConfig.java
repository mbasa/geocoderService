/**
 * パッケージ名：org.geocoderService.bean
 * ファイル名  ：SpringFoxConfig.java
 * 
 * @author mbasa
 * @since Apr 28, 2021
 */
package org.geocoderService.bean;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 説明：
 *
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    /**
     * コンストラクタ
     *
     */
    public SpringFoxConfig() {
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build().
                apiInfo(apiInfo());
    }  

    private ApiInfo apiInfo() {

        return new ApiInfo(
                "geocoderService",
                "pgGeocoder Web Service",
                "v0.1",
                null,
                null,
                "GNU GENERAL PUBLIC LICENSE",
                "https://www.gnu.org/licenses/gpl-3.0.en.html",
                Collections.emptyList());
    }

}
