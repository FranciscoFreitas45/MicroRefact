package edu.xr.campusweibo.web.rest;
 import edu.xr.campusweibo.config.DefaultProfileUtil;
import io.github.jhipster.config.JHipsterProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/api")
public class ProfileInfoResource {

 private  Environment env;

 private  JHipsterProperties jHipsterProperties;

 private  String[] activeProfiles;

 private  String ribbonEnv;


public String[] getActiveProfiles(){
    return activeProfiles;
}


public String getRibbonEnv(){
    return ribbonEnv;
}


}