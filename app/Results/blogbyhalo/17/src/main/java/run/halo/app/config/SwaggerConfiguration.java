package run.halo.app.config;
 import run.halo.app.model.support.HaloConst.ADMIN_TOKEN_HEADER_NAME;
import run.halo.app.model.support.HaloConst.ADMIN_TOKEN_QUERY_NAME;
import run.halo.app.model.support.HaloConst.API_ACCESS_KEY_HEADER_NAME;
import run.halo.app.model.support.HaloConst.API_ACCESS_KEY_QUERY_NAME;
import run.halo.app.model.support.HaloConst.HALO_VERSION;
import run.halo.app.utils.SwaggerUtils.customMixin;
import run.halo.app.utils.SwaggerUtils.propertyBuilder;
import springfox.documentation.schema.AlternateTypeRules.newRule;
import com.fasterxml.classmate.TypeResolver;
import io.swagger.models.auth.In;
import java.lang.reflect.Type;
import java.time.temporal.Temporal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;
import run.halo.app.utils.SwaggerUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
@Slf4j
@Configuration
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfiguration {


public Type sortMixin(){
    return customMixin(Sort.class, Collections.singletonList(propertyBuilder(String[].class, "sort")));
}


public boolean getHasPrevious()


public List<SecurityScheme> contentApiKeys(){
    return Arrays.asList(new ApiKey(API_ACCESS_KEY_HEADER_NAME, API_ACCESS_KEY_HEADER_NAME, In.HEADER.name()), new ApiKey(API_ACCESS_KEY_QUERY_NAME, API_ACCESS_KEY_QUERY_NAME, In.QUERY.name()));
}


public int getPage()


public List<T> getContent()


@Bean
public UiConfiguration uiConfig(){
    return UiConfigurationBuilder.builder().deepLinking(true).displayOperationId(false).defaultModelsExpandDepth(1).defaultModelExpandDepth(1).defaultModelRendering(ModelRendering.EXAMPLE).displayRequestDuration(false).docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null).operationsSorter(OperationsSorter.ALPHA).showExtensions(false).showCommonExtensions(false).tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).validatorUrl(null).build();
}


@Override
public List<AlternateTypeRule> rules(){
    return Arrays.asList(newRule(resolver.resolve(Page.class, WildcardType.class), resolver.resolve(CustomizedPage.class, WildcardType.class)), newRule(resolver.resolve(Pageable.class), resolver.resolve(pageableMixin())), newRule(resolver.resolve(Sort.class), resolver.resolve(sortMixin())));
}


public List<SecurityReference> adminApiAuths(){
    AuthorizationScope[] authorizationScopes = { new AuthorizationScope("Admin api", "Access admin api") };
    return Arrays.asList(new SecurityReference(ADMIN_TOKEN_HEADER_NAME, authorizationScopes), new SecurityReference(ADMIN_TOKEN_QUERY_NAME, authorizationScopes));
}


@Bean
public SecurityConfiguration security(){
    return SecurityConfigurationBuilder.builder().clientId("halo-app-client-id").clientSecret("halo-app-client-secret").realm("halo-app-realm").appName("halo-app").scopeSeparator(",").additionalQueryStringParams(null).useBasicAuthenticationWithAccessCodeGrant(false).build();
}


public List<SecurityReference> contentApiAuths(){
    AuthorizationScope[] authorizationScopes = { new AuthorizationScope("content api", "Access content api") };
    return Arrays.asList(new SecurityReference(API_ACCESS_KEY_HEADER_NAME, authorizationScopes), new SecurityReference(API_ACCESS_KEY_QUERY_NAME, authorizationScopes));
}


public List<SecurityContext> contentSecurityContext(){
    final PathMatcher pathMatcher = new AntPathMatcher();
    return Collections.singletonList(SecurityContext.builder().securityReferences(contentApiAuths()).operationSelector(operationContext -> {
        var requestMappingPattern = operationContext.requestMappingPattern();
        return pathMatcher.match("/api/content/**/*", requestMappingPattern);
    }).build());
}


@Bean
public AlternateTypeRuleConvention customizeConvention(TypeResolver resolver){
    return new AlternateTypeRuleConvention() {

        @Override
        public int getOrder() {
            return Ordered.LOWEST_PRECEDENCE;
        }

        @Override
        public List<AlternateTypeRule> rules() {
            return Arrays.asList(newRule(resolver.resolve(Page.class, WildcardType.class), resolver.resolve(CustomizedPage.class, WildcardType.class)), newRule(resolver.resolve(Pageable.class), resolver.resolve(pageableMixin())), newRule(resolver.resolve(Sort.class), resolver.resolve(sortMixin())));
        }
    };
}


public List<SecurityScheme> adminApiKeys(){
    return Arrays.asList(new ApiKey(ADMIN_TOKEN_HEADER_NAME, ADMIN_TOKEN_HEADER_NAME, In.HEADER.name()), new ApiKey(ADMIN_TOKEN_QUERY_NAME, ADMIN_TOKEN_QUERY_NAME, In.QUERY.name()));
}


public boolean getHasNext()


@Bean
public Docket haloDefaultApi(){
    return buildApiDocket("run.halo.app.content.api", "run.halo.app.controller.content.api", "/api/content/**").securitySchemes(contentApiKeys()).securityContexts(contentSecurityContext());
}


public ApiInfo apiInfo(){
    return new ApiInfoBuilder().title("Halo API Documentation").description("Documentation for Halo API").version(HALO_VERSION).termsOfServiceUrl("https://github.com/halo-dev").contact(new Contact("halo-dev", "https://github.com/halo-dev/halo/issues", "hi@halo.run")).license("GNU General Public License v3.0").licenseUrl("https://github.com/halo-dev/halo/blob/master/LICENSE").build();
}


public List<SecurityContext> adminSecurityContext(){
    final PathMatcher pathMatcher = new AntPathMatcher();
    return Collections.singletonList(SecurityContext.builder().securityReferences(adminApiAuths()).operationSelector(operationContext -> {
        var requestMappingPattern = operationContext.requestMappingPattern();
        return pathMatcher.match("/api/admin/**/*", requestMappingPattern);
    }).build());
}


public boolean getHasContent()


public Type pageableMixin(){
    return customMixin(Pageable.class, Arrays.asList(propertyBuilder(Integer.class, "page"), propertyBuilder(Integer.class, "size"), propertyBuilder(String[].class, "sort")));
}


public int getRpp()


public boolean getIsFirst()


public boolean getIsEmpty()


@Bean
public Docket haloAdminApi(){
    return buildApiDocket("run.halo.app.admin.api", "run.halo.app.controller.admin", "/api/admin/**").securitySchemes(adminApiKeys()).securityContexts(adminSecurityContext());
}


public Docket buildApiDocket(String groupName,String basePackage,String antPattern){
    Assert.hasText(groupName, "Group name must not be blank");
    Assert.hasText(basePackage, "Base package must not be blank");
    Assert.hasText(antPattern, "Ant pattern must not be blank");
    return SwaggerUtils.defaultDocket().groupName(groupName).select().apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.ant(antPattern)).build().apiInfo(apiInfo()).directModelSubstitute(Temporal.class, String.class);
}


@Override
public int getOrder(){
    return Ordered.LOWEST_PRECEDENCE;
}


public long getTotal()


public int getPages()


}