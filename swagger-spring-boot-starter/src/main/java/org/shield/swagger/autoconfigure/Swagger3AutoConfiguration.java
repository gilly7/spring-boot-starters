package org.shield.swagger.autoconfigure;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class Swagger3AutoConfiguration {

    @Autowired
    private SwaggerProperties props;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
                .build().globalRequestParameters(globalRequestParameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(props.title).description(props.description).version(props.version).build();
    }

    private List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder().in(ParameterType.HEADER)
                .name("Authorization").required(false)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return Collections.singletonList(parameterBuilder.build());
    }
}
