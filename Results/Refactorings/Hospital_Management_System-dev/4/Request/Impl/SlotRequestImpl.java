import org.springframework.web.client.RestTemplate;
public class SlotRequestImpl implements SlotRequest{

 private RestTemplate restTemplate;


public void setSlot(Slot slot,Long Long){
 restTemplate.put('http://1/Appointment/{id}/Slot/setSlot',slot,Long);
 return ;
}


public Slot getSlot(Long Long){
 Slot aux = restTemplate.getForObject('http://1/Appointment/{id}/Slot/getSlot',Slot.class,Long);
return aux;
}


}