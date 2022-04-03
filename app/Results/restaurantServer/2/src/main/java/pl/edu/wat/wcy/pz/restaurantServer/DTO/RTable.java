package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import java.util.List;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder ;




public class RTable {

 private  Long rTableId;

 private  int number;

 private  int size;

 private  String status;

 private  List<Reservation> reservations;

 private  List<Bill> bills;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public List<Bill> getBills(){
    return bills;
}


public Long getRTableId(){
    return rTableId;
}


public String getStatus(){
    return status;
}


public int getNumber(){
    return number;
}


public int getSize(){
    return size;
}


public List<Reservation> getReservations(){
    return reservations;
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ rTableId).concat("/setStatus"))

.queryParam("status",status);
restTemplate.put(builder.toUriString(),null);
}


}