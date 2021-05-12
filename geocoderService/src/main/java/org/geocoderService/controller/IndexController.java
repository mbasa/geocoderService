/**
 * パッケージ名：org.geocoderService.controller
 * ファイル名  ：IndexController.java
 * 
 * @author mbasa
 * @since Apr 30, 2021
 */
package org.geocoderService.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 説明： Redirects "/" to "/swagger-ui.html"
 *
 */
@Configuration
public class IndexController implements WebMvcConfigurer  {

    /**
     * コンストラクタ
     *
     */
    public IndexController() {
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

}
