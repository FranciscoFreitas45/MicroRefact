package br.com.fatecmogidascruzes.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.Interface.File;
public class FileImpl implements File{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


}