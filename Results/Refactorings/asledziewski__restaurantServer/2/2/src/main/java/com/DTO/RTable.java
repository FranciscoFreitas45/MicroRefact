package com.DTO;

import com.pl.edu.wat.wcy.pz.restaurantServer.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class RTable {

 private  Long rtableId;

 private  int number;

 private  int size;

 private  String status;
/*
 private  List<Reservation> reservations;
*/
 private List<Bill> bills;


 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://localhost:8080";


public List<Bill> getBills(){
return this.bills;
}


public Long getRTableId(){
    return rtableId;
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

/*
public List<Reservation> getReservations(){
return this.reservations;
}
*/

public void setStatus(String status){
    this.status = status;
 


}
public void setStatus2(String status){
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ this.rtableId).concat("/setStatus"))

            .queryParam("status",status);
    System.out.println(builder.toUriString());
    restTemplate.put(builder.toUriString(),null);

}

    @Override
    public String toString() {
        return "RTable{" +
                "rTableId=" + rtableId +
                ", number=" + number +
                ", size=" + size +
                ", status='" + status + '\'' +
                ", bills=" + bills +
                ", restTemplate=" + restTemplate +
                ", url='" + url + '\'' +
                '}';
    }

}