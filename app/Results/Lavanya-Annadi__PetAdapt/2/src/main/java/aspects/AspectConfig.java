package aspects;
 import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableLoadTimeWeaving.AspectJWeaving;
@EnableLoadTimeWeaving(aspectjWeaving = AspectJWeaving.ENABLED)
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableSpringConfigured
@ComponentScan(basePackages = { "aspects", "io.swagger.controller" })
public class AspectConfig {


@Bean
public MyAspect myLogicLoggingAspect(){
    return Aspects.aspectOf(MyAspect.class);
}


@Bean
public InstrumentationLoadTimeWeaver loadTimeWeaver(){
    InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
    return loadTimeWeaver;
}


}