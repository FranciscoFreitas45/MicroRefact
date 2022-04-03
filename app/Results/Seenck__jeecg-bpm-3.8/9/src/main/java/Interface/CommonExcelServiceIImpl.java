package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.CommonExcelServiceI;
public class CommonExcelServiceIImpl implements CommonExcelServiceI{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public HSSFWorkbook exportExcel(String title,Collection<?> titleSet,Collection<?> dataSet){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/exportExcel"))
    .queryParam("title",title)
    .queryParam("titleSet",titleSet)
    .queryParam("dataSet",dataSet)
;  HSSFWorkbook aux = restTemplate.getForObject(builder.toUriString(), HSSFWorkbook.class);

 return aux;
}


}