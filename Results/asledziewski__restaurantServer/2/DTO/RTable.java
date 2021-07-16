import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import javax.persistence;
import java.util.List;
public class RTable {

 private  Long rTableId;

 private  int number;

 private  int size;

 private  String status;

 private  List<Reservation> reservations;

 private  List<Bill> bills;

 private ReservationRequestImpl reservationrequestimpl;

 private BillRequestImpl billrequestimpl;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public List<Bill> getBills(){
  this.bills = billrequestimpl.getBills(this.rTableId);
return this.bills;
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
  this.reservations = reservationrequestimpl.getReservations(this.rTableId);
return this.reservations;
}


public void setStatus(String status){
    this.status = status;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ rTableId).concat("/setStatus"));

.queryParam("status",status);
restTemplate.put(builder.toUriString(),null);
}


}