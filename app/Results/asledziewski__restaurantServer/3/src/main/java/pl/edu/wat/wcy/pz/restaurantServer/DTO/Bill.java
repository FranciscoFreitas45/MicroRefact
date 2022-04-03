package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence;
import java.util.Date;
import java.util.List;
public class Bill {

 private  Long billId;

 private  String status;

 private  Date creationDate;

 private  Long rTableId;

 private  double value;

 private  List<BillPosition> billPositions;


public List<BillPosition> getBillPositions(){
    return billPositions;
}


public Long getRTableId(){
    return rTableId;
}


public String getStatus(){
    return status;
}


public Date getCreationDate(){
    return creationDate;
}


public double getValue(){
    return value;
}


public Long getBillId(){
    return billId;
}


}