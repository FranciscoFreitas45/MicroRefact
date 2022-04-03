package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserHistoryController {

 private UserHistory userhistory;

 private UserHistory userhistory;


@PutMapping
("/setReferer")
public void setReferer(@RequestParam(name = "referer") String referer){
userhistory.setReferer(referer);
}


@PutMapping
("/setParam")
public void setParam(@RequestParam(name = "param") String param){
userhistory.setParam(param);
}


@PutMapping
("/setMaintype")
public void setMaintype(@RequestParam(name = "maintype") String maintype){
userhistory.setMaintype(maintype);
}


@PutMapping
("/setSubtype")
public void setSubtype(@RequestParam(name = "subtype") String subtype){
userhistory.setSubtype(subtype);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
userhistory.setName(name);
}


@PutMapping
("/setAdmin")
public void setAdmin(@RequestParam(name = "admin") boolean admin){
userhistory.setAdmin(admin);
}


@PutMapping
("/setAccessnum")
public void setAccessnum(@RequestParam(name = "accessnum") boolean accessnum){
userhistory.setAccessnum(accessnum);
}


@PutMapping
("/setCreater")
public void setCreater(@RequestParam(name = "creater") String creater){
userhistory.setCreater(creater);
}


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
userhistory.setUsername(username);
}


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
userhistory.setOrgi(orgi);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
userhistory.setTitle(title);
}


@PutMapping
("/setAppid")
public void setAppid(@RequestParam(name = "appid") String appid){
userhistory.setAppid(appid);
}


@PutMapping
("/setSessionid")
public void setSessionid(@RequestParam(name = "sessionid") String sessionid){
userhistory.setSessionid(sessionid);
}


@PutMapping
("/setHostname")
public void setHostname(@RequestParam(name = "hostname") String hostname){
userhistory.setHostname(hostname);
}


@PutMapping
("/setIp")
public void setIp(@RequestParam(name = "ip") String ip){
userhistory.setIp(ip);
}


@PutMapping
("/setCountry")
public void setCountry(@RequestParam(name = "country") String country){
userhistory.setCountry(country);
}


@PutMapping
("/setProvince")
public void setProvince(@RequestParam(name = "province") String province){
userhistory.setProvince(province);
}


@PutMapping
("/setCity")
public void setCity(@RequestParam(name = "city") String city){
userhistory.setCity(city);
}


@PutMapping
("/setIsp")
public void setIsp(@RequestParam(name = "isp") String isp){
userhistory.setIsp(isp);
}


@PutMapping
("/setOstype")
public void setOstype(@RequestParam(name = "ostype") String ostype){
userhistory.setOstype(ostype);
}


@PutMapping
("/setBrowser")
public void setBrowser(@RequestParam(name = "browser") String browser){
userhistory.setBrowser(browser);
}


@PutMapping
("/setMobile")
public void setMobile(@RequestParam(name = "mobile") String mobile){
userhistory.setMobile(mobile);
}


}