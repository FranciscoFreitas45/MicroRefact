package com.yalcin.config;
 import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import java.nio.charset.StandardCharsets;
@Configuration
public class ThymeleafConfig {


@Bean
public DatabaseTemplateResolver htmlTemplateResolver(){
    DatabaseTemplateResolver emailTemplateResolver = new DatabaseTemplateResolver();
    emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
    return emailTemplateResolver;
}


@Bean
public SpringTemplateEngine springTemplateEngine(){
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(htmlTemplateResolver());
    return templateEngine;
}


}