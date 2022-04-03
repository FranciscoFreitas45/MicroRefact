package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
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


public void setBillPositions(List<BillPosition> billPositions){
    this.billPositions = billPositions;
}


public void setBillId(Long billId){
    this.billId = billId;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public Date getCreationDate(){
    return creationDate;
}


public double getValue(){
    return value;
}


public void setCreationDate(Date creationDate){
    this.creationDate = creationDate;
}


public Long getBillId(){
    return billId;
}


public void setValue(double value){
    this.value = value;
}


public void setRTableId(Long rTableId){
    this.rTableId = rTableId;
}


public void changeValue(double value){
    this.value += value;
}


}