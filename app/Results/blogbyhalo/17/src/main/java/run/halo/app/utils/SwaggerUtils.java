package run.halo.app.utils;
 import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import run.halo.app.model.entity.User;
import run.halo.app.security.authentication.Authentication;
import run.halo.app.security.support.UserDetail;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
public class SwaggerUtils {

 public  List<Response> GLOBAL_RESPONSES;

private SwaggerUtils() {
}
public Consumer<AlternateTypePropertyBuilder> propertyBuilder(Class<?> clazz,String name){
    return propertyBuilder -> propertyBuilder.type(clazz).name(name).canRead(true).canWrite(true);
}


public Optional<Class<?>> classFor(String className){
    try {
        return Optional.of(Class.forName(className, false, SwaggerUtils.class.getClassLoader()));
    } catch (ClassNotFoundException e) {
        return Optional.empty();
    }
}


public Class<?>[] initIgnore(){
    final Set<Class<?>> ignoredClasses = new HashSet<>();
    ignoredClasses.add(User.class);
    ignoredClasses.add(UserDetail.class);
    ignoredClasses.add(Authentication.class);
    classFor(User.class.getName()).ifPresent(ignoredClasses::add);
    return ignoredClasses.toArray(Class[]::new);
}


public Docket defaultDocket(){
    return new Docket(DocumentationType.OAS_30).forCodeGeneration(true).ignoredParameterTypes(initIgnore()).useDefaultResponseMessages(false).globalResponses(HttpMethod.GET, GLOBAL_RESPONSES).globalResponses(HttpMethod.POST, GLOBAL_RESPONSES).globalResponses(HttpMethod.DELETE, GLOBAL_RESPONSES).globalResponses(HttpMethod.PATCH, GLOBAL_RESPONSES).globalResponses(HttpMethod.PUT, GLOBAL_RESPONSES);
}


public Type emptyMixin(Class<?> clazz){
    return customMixin(clazz, Collections.emptyList());
}


public Type customMixin(Class<?> clazz,List<Consumer<AlternateTypePropertyBuilder>> properties){
    Assert.notNull(clazz, "Class must not be null");
    final var typeBuilder = new AlternateTypeBuilder().fullyQualifiedClassName(String.format("%s.generated.%s", clazz.getPackage().getName(), clazz.getSimpleName()));
    properties.forEach(typeBuilder::property);
    return typeBuilder.build();
}


}