package com.hepsiemlak.todo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private List<ApiKey> apiKeys() {
        return Arrays.asList(new ApiKey("JWT", "Authorization", ""));
    }

    private List<SecurityReference> securityReferences() {
        return Arrays.asList(SecurityReference.builder()
                .reference("JWT")
                .scopes(new AuthorizationScope[0])
                .build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("To-Do Swagger")
                .description("Swagger for HepsiEmlak To-Do API")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(apiKeys())
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(
                        SecurityContext.builder()
                                .securityReferences(securityReferences())
                                .build())
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hepsiemlak.todo"))
                .build();
    }
}
