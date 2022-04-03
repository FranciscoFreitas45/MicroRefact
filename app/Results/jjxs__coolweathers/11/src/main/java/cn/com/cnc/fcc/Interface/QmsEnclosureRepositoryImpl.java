package cn.com.cnc.fcc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.cnc.fcc.Interface.QmsEnclosureRepository;
public class QmsEnclosureRepositoryImpl implements QmsEnclosureRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public List<QmsEnclosure> findAllByInspectionInfoIdAndInspectionKbn(Integer inspectionInfoId,String inpectionKbn){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllByInspectionInfoIdAndInspectionKbn"))
    .queryParam("inspectionInfoId",inspectionInfoId)
    .queryParam("inpectionKbn",inpectionKbn)
;  List<QmsEnclosure> aux = restTemplate.getForObject(builder.toUriString(), List<QmsEnclosure>.class);

 return aux;
}


}