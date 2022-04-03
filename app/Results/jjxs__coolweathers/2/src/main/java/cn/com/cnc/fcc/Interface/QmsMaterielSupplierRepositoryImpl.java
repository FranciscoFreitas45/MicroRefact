package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsMaterielSupplierRepository;
public class QmsMaterielSupplierRepositoryImpl implements QmsMaterielSupplierRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<QmsMaterielSupplier> findByMaterielId(Integer s){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMaterielId"))
    .queryParam("s",s)
;  List<QmsMaterielSupplier> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielSupplier>.class);

 return aux;
}


}