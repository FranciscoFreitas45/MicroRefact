package com.pl.edu.wat.wcy.pz.restaurantServer.entity;

import com.DTO.Bill;
import com.DTO.Reservation;
import com.Request.BillRequest;
import com.Request.Impl.BillRequestImpl;
import com.Request.Impl.ReservationRequestImpl;
import com.Request.ReservationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
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
 private ReservationRequest reservationrequestimpl = new ReservationRequestImpl();

@Transient
 private BillRequest billrequestimpl = new BillRequestImpl();

/*
public List<Bill> getBills(){
  this.bills = billrequestimpl.getBills(this.rTableId);
return this.bills;
}
*/

public Long getRTableId(){
    return rTableId;
}


public void setReservations(List<Reservation> reservations) {
    reservationrequestimpl.setReservations(reservations, this.rTableId);
}


public void setBills(List<Bill> bills) {
        billrequestimpl.setBills(bills, this.rTableId);

    }

public void addBill(Bill bill){
        billrequestimpl.addBill(bill, this.rTableId);

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

/*
public List<Reservation> getReservations(){
  this.reservations = reservationrequestimpl.getReservations(this.rTableId);
return this.reservations;
}*/


}