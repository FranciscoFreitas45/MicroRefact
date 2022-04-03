package edu.xr.campusweibo;
 import edu.xr.campusweibo.config.ApplicationProperties;
import edu.xr.campusweibo.config.DefaultProfileUtil;
import io.github.jhipster.config.JHipsterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
@ComponentScan
@EnableAutoConfiguration(exclude = { MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class })
@EnableConfigurationProperties({ LiquibaseProperties.class, ApplicationProperties.class })
public class CampusweiboApp {

 private  Logger log;

 private  Environment env;

public CampusweiboApp(Environment env) {
    this.env = env;
}
@PostConstruct
public void initApplication(){
    Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
    if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
        log.error("You have misconfigured your application! It should not run " + "with both the 'dev' and 'prod' profiles at the same time.");
    }
    if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
        log.error("You have misconfigured your application! It should not" + "run with both the 'dev' and 'cloud' profiles at the same time.");
    }
}


public void main(String[] args){
    SpringApplication app = new SpringApplication(CampusweiboApp.class);
    DefaultProfileUtil.addDefaultProfile(app);
    Environment env = app.run(args).getEnvironment();
    String protocol = "http";
    if (env.getProperty("server.ssl.key-store") != null) {
        protocol = "https";
    }
    log.info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t" + "External: \t{}://{}:{}\n\t" + "Profile(s): \t{}\n----------------------------------------------------------", env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol, InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles());
}


}