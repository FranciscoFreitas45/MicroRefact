package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import Interface.DataGrid;
public class DataGridImpl implements DataGrid{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public int getRows(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRows"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public String getField(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getField"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}