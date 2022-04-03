package cn.maxcj.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.Interface.IClubinfoService;
public class IClubinfoServiceImpl implements IClubinfoService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public List<Map<String,Object>> queryClubInfo(Integer deptid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryClubInfo"))
    .queryParam("deptid",deptid)
;  List<Map<String,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,Object>>.class);

 return aux;
}


}