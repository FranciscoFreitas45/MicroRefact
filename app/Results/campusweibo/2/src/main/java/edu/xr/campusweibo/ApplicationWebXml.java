package edu.xr.campusweibo;
 import edu.xr.campusweibo.config.DefaultProfileUtil;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
public class ApplicationWebXml extends SpringBootServletInitializer{


@Override
public SpringApplicationBuilder configure(SpringApplicationBuilder application){
    /**
     * set a default to use when no profile is configured.
     */
    DefaultProfileUtil.addDefaultProfile(application.application());
    return application.sources(CampusweiboApp.class);
}


}