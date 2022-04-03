package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsBomTechnologyRepository;
public class QmsBomTechnologyRepositoryImpl implements QmsBomTechnologyRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


}