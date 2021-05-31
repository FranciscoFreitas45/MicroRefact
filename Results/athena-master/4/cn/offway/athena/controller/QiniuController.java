import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.QiniuService;
@RestController
@RequestMapping("/qiniu")
public class QiniuController {

 private  Logger logger;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  QiniuService qiniuService;


@PostMapping("/delete")
public boolean delete(String url){
    return qiniuService.qiniuDelete(url.replace(qiniuProperties.getUrl() + "/", ""));
}


@GetMapping("/token")
public String token(){
    return qiniuService.token();
}


}