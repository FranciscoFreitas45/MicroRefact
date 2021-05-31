import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhResource;
@Controller
@RequestMapping
public class IndexController {

 private  Logger logger;


@RequestMapping("/resoures")
@ResponseBody
public List<PhResource> resoures(PhAdmin admin){
    return admin.getResources();
}


@RequestMapping("/")
public String index(){
    return "home";
}


@RequestMapping("/login.html")
public String login(){
    return "login";
}


}