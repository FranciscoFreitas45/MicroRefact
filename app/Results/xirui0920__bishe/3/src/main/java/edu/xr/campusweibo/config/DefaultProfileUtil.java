package edu.xr.campusweibo.config;
 import io.github.jhipster.config.JHipsterConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.Environment;
import java.util;
public class DefaultProfileUtil {

 private  String SPRING_PROFILE_DEFAULT;

private DefaultProfileUtil() {
}
public String[] getActiveProfiles(Environment env){
    String[] profiles = env.getActiveProfiles();
    if (profiles.length == 0) {
        return env.getDefaultProfiles();
    }
    return profiles;
}


public void addDefaultProfile(SpringApplication app){
    Map<String, Object> defProperties = new HashMap<>();
    /*
        * The default profile to use when no other profiles are defined
        * This cannot be set in the <code>application.yml</code> file.
        * See https://github.com/spring-projects/spring-boot/issues/1219
        */
    defProperties.put(SPRING_PROFILE_DEFAULT, JHipsterConstants.SPRING_PROFILE_DEVELOPMENT);
    app.setDefaultProperties(defProperties);
}


}