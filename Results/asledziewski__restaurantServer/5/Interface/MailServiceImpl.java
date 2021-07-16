import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class MailServiceImpl implements MailService{

 private RestTemplate restTemplate;

  String url = "http://6";


public void sendEmail(String destination,String subject,String content){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendEmail"))
    .queryParam("destination",destination)
    .queryParam("subject",subject)
    .queryParam("content",content);

  restTemplate.put(builder.toUriString(), null)



}