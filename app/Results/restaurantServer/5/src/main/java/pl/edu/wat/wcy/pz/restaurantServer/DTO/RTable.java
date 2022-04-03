package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Bill;
public class RTable {

 private  Long rTableId;

 private  int number;

 private  int size;

 private  String status;

 private  List<Reservation> reservations;

 private  List<Bill> bills;


public List<Bill> getBills(){
    return bills;
}


public Long getRTableId(){
    return rTableId;
}


public void setBills(List<Bill> bills){
    this.bills = bills;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public int getNumber(){
    return number;
}


public int getSize(){
    return size;
}


public void setRTableId(Long rTableId){
    this.rTableId = rTableId;
}


public List<Reservation> getReservations(){
    return reservations;
}


}