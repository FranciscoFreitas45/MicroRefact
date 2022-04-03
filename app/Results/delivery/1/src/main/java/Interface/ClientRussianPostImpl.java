package Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
public class ClientRussianPostImpl implements ClientRussianPost{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public String result(String barcodestring){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/result"))
    .queryParam("barcodestring",barcodestring);
  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}