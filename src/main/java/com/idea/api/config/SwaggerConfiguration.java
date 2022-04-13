package com.idea.api.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .genericModelSubstitutes(genericModelSubstitutes())
            .apiInfo(apiInfo())
//            .securityContexts(List.of(securityContext()))
//            .securitySchemes(List.of(apiKey()))
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    private Class<?>[] genericModelSubstitutes() {
        return new Class[]{ResponseEntity.class, DeferredResult.class};
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Ideas API",
                StringUtils.EMPTY,
                StringUtils.EMPTY,
                StringUtils.EMPTY,
                null,
                StringUtils.EMPTY,
                StringUtils.EMPTY,
                List.of()
        );
    }

//    private ApiKey apiKey() {
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth()).build();
//    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}
