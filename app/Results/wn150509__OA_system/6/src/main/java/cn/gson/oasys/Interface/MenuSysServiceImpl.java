package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.MenuSysService;
public class MenuSysServiceImpl implements MenuSysService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public void findMenuSys(HttpServletRequest req,User user){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findMenuSys"))
    .queryParam("req",req)
    .queryParam("user",user)
;
  restTemplate.put(builder.toUriString(), null);
}


}