package cn.gson.oasys.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.Interface.AttachmentDao;
public class AttachmentDaoImpl implements AttachmentDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


}