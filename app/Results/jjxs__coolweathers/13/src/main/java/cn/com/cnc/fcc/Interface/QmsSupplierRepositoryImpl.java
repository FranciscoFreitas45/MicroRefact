package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsSupplierRepository;
public class QmsSupplierRepositoryImpl implements QmsSupplierRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public Optional<QmsSupplier> findQmsSupplierBySupplierCdAndFlagStatus(String supplierCd,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findQmsSupplierBySupplierCdAndFlagStatus"))
    .queryParam("supplierCd",supplierCd)
    .queryParam("flagStatus",flagStatus)
;  Optional<QmsSupplier> aux = restTemplate.getForObject(builder.toUriString(), Optional<QmsSupplier>.class);

 return aux;
}


}