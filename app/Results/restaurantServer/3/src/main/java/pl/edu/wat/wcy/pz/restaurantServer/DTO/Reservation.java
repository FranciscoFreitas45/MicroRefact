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


public Long getReservationId(){
    return reservationId;
}


public String getDateTime(){
    return dateTime;
}


public Long getRTableId(){
    return rTableId;
}


public String getStatus(){
    return status;
}


public int getAttendees(){
    return attendees;
}


public String getDateDays(){
    return dateDays;
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


}