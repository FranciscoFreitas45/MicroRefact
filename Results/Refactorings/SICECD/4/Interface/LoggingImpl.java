import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class LoggingImpl implements Logging{

 private RestTemplate restTemplate;

  String url = "http://localhost:teste";


public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario);

  restTemplate.put(builder.toUriString(), null)



public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario);

  restTemplate.put(builder.toUriString(), null)



public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario);

  restTemplate.put(builder.toUriString(), null)



public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario);

  restTemplate.put(builder.toUriString(), null)



public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario);

  restTemplate.put(builder.toUriString(), null)



public void logtrace(String action,String comentario){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("logtrace"))
    .queryParam("action",action)
    .queryParam("comentario",comentario);

  restTemplate.put(builder.toUriString(), null)



}