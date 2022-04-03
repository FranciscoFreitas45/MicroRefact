package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IClubinfoService;
public class IClubinfoServiceImpl implements IClubinfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public void init(Integer deptid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))
    .queryParam("deptid",deptid)
;
  restTemplate.put(builder.toUriString(), null);
}


public Clubinfo getClubInfoByDeptid(Integer deptid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getClubInfoByDeptid"))
    .queryParam("deptid",deptid)
;  Clubinfo aux = restTemplate.getForObject(builder.toUriString(), Clubinfo.class);

 return aux;
}


public Object deleteById(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteById"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}