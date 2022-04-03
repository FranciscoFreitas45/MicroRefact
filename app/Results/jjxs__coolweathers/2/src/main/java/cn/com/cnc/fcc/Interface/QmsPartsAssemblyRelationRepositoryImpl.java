package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsPartsAssemblyRelationRepository;
public class QmsPartsAssemblyRelationRepositoryImpl implements QmsPartsAssemblyRelationRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://18";


}