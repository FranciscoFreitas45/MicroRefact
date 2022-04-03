package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsMaterielEntryRepository;
public class QmsMaterielEntryRepositoryImpl implements QmsMaterielEntryRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public List<QmsMaterielEntry> findByMaterielIdAndFlagStatus(Integer materielId,String flagStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMaterielIdAndFlagStatus"))
    .queryParam("materielId",materielId)
    .queryParam("flagStatus",flagStatus)
;  List<QmsMaterielEntry> aux = restTemplate.getForObject(builder.toUriString(), List<QmsMaterielEntry>.class);

 return aux;
}


}