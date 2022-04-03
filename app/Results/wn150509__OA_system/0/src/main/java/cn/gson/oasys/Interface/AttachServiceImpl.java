package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.AttachService;
public class AttachServiceImpl implements AttachService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}