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


}