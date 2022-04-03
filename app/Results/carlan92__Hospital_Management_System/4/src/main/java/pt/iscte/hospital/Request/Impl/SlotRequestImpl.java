package pt.iscte.hospital.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.DTO.Slot;
import pt.iscte.hospital.Request.SlotRequest;
public class SlotRequestImpl implements SlotRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setSlot(Slot slot,Long slotId){
 restTemplate.put("http://2/Appointment/{id}/Slot/setSlot",slot,slotId);
 return ;
}


public Slot getSlot(Long slotId){
 Slot aux = restTemplate.getForObject("http://2/Appointment/{id}/Slot/getSlot",Slot.class,slotId);
return aux;
}


}