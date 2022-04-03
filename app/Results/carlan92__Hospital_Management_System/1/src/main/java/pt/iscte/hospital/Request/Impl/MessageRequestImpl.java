package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Message;
import pt.iscte.hospital.Request.MessageRequest;
public class MessageRequestImpl implements MessageRequest{

 private RestTemplate restTemplate = new RestTemplate();;


}