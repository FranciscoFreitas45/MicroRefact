import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

@Autowired
 private  ApplicationProperties applicationProperties;


@Override
public void addResourceHandlers(ResourceHandlerRegistry registry){
    // 使用外部路径
    if (System.getProperty("os.name").indexOf("Linux") >= 0) {
        registry.addResourceHandler("/preview/**").addResourceLocations("file:" + applicationProperties.getWeb().getFileShare());
    } else {
        registry.addResourceHandler("/preview/**").addResourceLocations("file:/" + applicationProperties.getWeb().getFileShare());
    }
}


}