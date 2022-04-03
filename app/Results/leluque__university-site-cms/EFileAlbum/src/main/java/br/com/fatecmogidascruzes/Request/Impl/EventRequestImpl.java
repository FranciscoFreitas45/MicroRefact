package br.com.fatecmogidascruzes.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.DTO.Event;
import br.com.fatecmogidascruzes.Request.EventRequest;
public class EventRequestImpl implements EventRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}