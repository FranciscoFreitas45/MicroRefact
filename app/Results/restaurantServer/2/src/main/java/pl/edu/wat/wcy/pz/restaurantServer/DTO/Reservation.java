package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.Date;
public class Reservation {

 private  Long reservationId;

 private  Date date;

 private  String dateDays;

 private  String dateTime;

 private  Long rTableId;

 private  int rTableNumber;

 private  int attendees;

 private  Long userId;

 private  String status;


public void setDateDays(String dateDays){
    this.dateDays = dateDays;
}


public Long getReservationId(){
    return reservationId;
}


public String getDateTime(){
    return dateTime;
}


public Long getRTableId(){
    return rTableId;
}


public void setrTableNumber(int rTableNumber){
    this.rTableNumber = rTableNumber;
}


public void setReservationId(Long reservationId){
    this.reservationId = reservationId;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public int getAttendees(){
    return attendees;
}


public void setDateTime(String dateTime){
    this.dateTime = dateTime;
}


public String getDateDays(){
    return dateDays;
}


public void setAttendees(int attendees){
    this.attendees = attendees;
}


public void setRTableId(Long rTableId){
    this.rTableId = rTableId;
}


public void setDate(Date date){
    this.date = date;
}


public Date getDate(){
    return date;
}


public int getrTableNumber(){
    return rTableNumber;
}


public Long getUserId(){
    return userId;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}