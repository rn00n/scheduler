package scheduler.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("api info")
                .license("jkh").licenseUrl("http://jkh.com").version("1").build();
    }
}
