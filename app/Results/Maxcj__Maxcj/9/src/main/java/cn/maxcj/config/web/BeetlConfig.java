package cn.maxcj.config.web;
 import cn.maxcj.config.properties.BeetlProperties;
import cn.maxcj.core.beetl.BeetlConfiguration;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BeetlConfig {

@Autowired
 private BeetlProperties beetlProperties;


@Bean(initMethod = "init")
public BeetlConfiguration beetlConfiguration(){
    BeetlConfiguration beetlConfiguration = new BeetlConfiguration();
    beetlConfiguration.setResourceLoader(new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(), beetlProperties.getPrefix()));
    beetlConfiguration.setConfigProperties(beetlProperties.getProperties());
    return beetlConfiguration;
}


@Bean
public BeetlSpringViewResolver beetlViewResolver(){
    BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
    beetlSpringViewResolver.setConfig(beetlConfiguration());
    beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
    beetlSpringViewResolver.setOrder(0);
    return beetlSpringViewResolver;
}


}