package demo;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

import demo.catalog.Catalog;
import demo.config.DatabaseInitializer;
import demo.product.Product;
import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableNeo4jRepositories
@EnableConfigurationProperties
//@EnableTransactionManagement
@EnableEurekaClient
@EnableHystrix
@EnableSwagger2
public class InventoryApplication {

    @Bean
    public Docket docket() {
        ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2).select();
        apiSelectorBuilder.apis(withClassAnnotation(Api.class));
        return apiSelectorBuilder
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(
                    new ApiInfoBuilder().title("inventory-service Doc")
                    .description("inventory-service Doc")
                    .version("1.0")
                    .termsOfServiceUrl("https://github.com/wangzheng422/spring-cloud-event-sourcing-example")
                    .contact(new Contact("George", "https://github.com/wangzheng422", "wangzheng422@gmail.com"))
                    .build())
                .forCodeGeneration(true);
    }

    @Bean
	UiConfiguration uiConfig() {
		       
        return UiConfigurationBuilder.builder().validatorUrl("").build();
    }

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Configuration
    protected static class RepositoryMvcConfiguration extends RepositoryRestConfigurerAdapter {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.exposeIdsFor(Catalog.class, Product.class);
        }
    }

    @Bean
    @Profile({"docker", "cloud", "development"})
    CommandLineRunner commandLineRunner(DatabaseInitializer databaseInitializer) {
        return args -> {
            // Initialize the database for end to end integration testing
            databaseInitializer.populate();
        };
    }

    @Component
    public static class CustomizedRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.setBasePath("/api");
        }
    }
}

