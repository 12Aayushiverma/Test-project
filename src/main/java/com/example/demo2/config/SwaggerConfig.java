package com.example.demo2.config;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static springfox.documentation.builders.PathSelectors.regex;
import java.util.List;
import org.springframework.context.annotation.*;
import com.google.common.collect.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

   public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

   @Bean
   public Docket swaggerPersonApi10() {
      return new Docket(DocumentationType.SWAGGER_2).groupName("TestDemo")
            .securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
            .select().apis(RequestHandlerSelectors.basePackage("com.example.demo2.controller"))
            .paths(PathSelectors.any()).build().produces(Sets.newHashSet(APPLICATION_JSON_VALUE))
            .consumes(Sets.newHashSet(APPLICATION_JSON_VALUE));
   }

   private ApiKey apiKey() {
      return new ApiKey("JWT","Authorization", "header");
   }

   private SecurityContext securityContext() {
      return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(regex(DEFAULT_INCLUDE_PATTERN))
            .build();
   }

   List<SecurityReference> defaultAuth() {
      AuthorizationScope authorizationScope = new AuthorizationScope("global",
            "accessEverthing");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
   }
}