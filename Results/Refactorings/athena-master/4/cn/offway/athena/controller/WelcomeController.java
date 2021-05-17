import cn.offway.athena.domain.PhConfig;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.PhConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
@Controller
@RequestMapping
public class WelcomeController {

 private  Logger logger;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  PhConfigService configService;


@RequestMapping("/welcome.html")
public String zgll(ModelMap map){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    String url = configService.findContentByName("WELCOME");
    map.addAttribute("image", url);
    return "welcome";
}


@ResponseBody
@RequestMapping("/welcome_save")
public boolean save(String logo){
    PhConfig config = configService.findOne("WELCOME");
    if (config != null) {
        config.setContent(logo);
    } else {
        config = new PhConfig();
        config.setName("WELCOME");
        config.setContent(logo);
        config.setCreateTime(new Date());
    }
    configService.save(config);
    return true;
}


}