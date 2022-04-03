package pl.edu.wat.wcy.pz.restaurantServer.entity;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import java.util.List;
import pl.edu.wat.wcy.pz.restaurantServer.Request.ReservationRequest;
import pl.edu.wat.wcy.pz.restaurantServer.Request.Impl.ReservationRequestImpl;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.Request.BillRequest;
import pl.edu.wat.wcy.pz.restaurantServer.Request.Impl.BillRequestImpl;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Bill;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RTable {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "RTABLE_ID")
 private  Long rTableId;

@NaturalId
@Column(name = "NUMBER")
 private  int number;

@Column(name = "SIZE")
 private  int size;

@Column(name = "STATUS")
 private  String status;

@Transient
 private  List<Reservation> reservations;

@Transient
 private  List<Bill> bills;

@Transient
 private ReservationRequest reservationrequest = new ReservationRequestImpl();;

@Transient
 private BillRequest billrequest = new BillRequestImpl();;


public List<Bill> getBills(){
  this.bills = billrequest.getBills(this.rTableId);
return this.bills;
}


public Long getRTableId(){
    return rTableId;
}


public void setReservations(List<Reservation> reservations){
 reservationrequest.setReservations(reservations,this.rTableId);
}



public void setBills(List<Bill> bills){
 billrequest.setBills(bills,this.rTableId);
}



public void addBill(Bill bill){
 billrequest.addBill(bill,this.rTableId);
}



public String getStatus(){
    return status;
}


public void setNumber(int number){
    this.number = number;
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


public void setSize(int size){
    this.size = size;
}


public void setRTableId(Long rTableId){
    this.rTableId = rTableId;
}


public List<Reservation> getReservations(){
  this.reservations = reservationrequest.getReservations(this.rTableId);
return this.reservations;
}


}