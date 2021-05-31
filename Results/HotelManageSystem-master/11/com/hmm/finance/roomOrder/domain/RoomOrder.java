import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.employee.entity.Employee;
import com.hmm.finance.roomOrder.util.RoomOrderStatus;
@Entity
@Table(name = "t_room_order")
public class RoomOrder {

 private  Long bookRoomNo;

 private  Long RoomNo;

 private  String roomType;

 private  String booksource;

 private  Float roomPrice;

 private  Date checkInTime;

 private  Date checkOutTime;

 private  String bookGuest;

 private  Long bookPhone;

 private  String remark;

 private  Float totalIncome;

 private  Float realIncome;

 private  Float shouldIncome;

 private  Employee employee;

 private  RoomOrderStatus roomOrderStatus;


public Float getRoomPrice(){
    return roomPrice;
}


public Float getTotalIncome(){
    return totalIncome;
}


public void setEmployee(Employee employee){
    this.employee = employee;
}


@Enumerated(EnumType.STRING)
public RoomOrderStatus getRoomOrderStatus(){
    return roomOrderStatus;
}


public void setRoomType(String roomType){
    this.roomType = roomType;
}


public Long getBookPhone(){
    return bookPhone;
}


public String getBooksource(){
    return booksource;
}


public void setBookRoomNo(Long bookRoomNo){
    this.bookRoomNo = bookRoomNo;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getCheckOutTime(){
    return checkOutTime;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


@OneToOne
@JoinColumn(name = "employeeId")
public Employee getEmployee(){
    return employee;
}


public void setBooksource(String booksource){
    this.booksource = booksource;
}


public void setRoomNo(Long roomNo){
    RoomNo = roomNo;
}


public void setBookPhone(Long bookPhone){
    this.bookPhone = bookPhone;
}


public void setRoomOrderStatus(RoomOrderStatus roomOrderStatus){
    this.roomOrderStatus = roomOrderStatus;
}


public Long getRoomNo(){
    return RoomNo;
}


@Id
public Long getBookRoomNo(){
    return bookRoomNo;
}


public void setRoomPrice(Float roomPrice){
    this.roomPrice = roomPrice;
}


public void setCheckInTime(Date checkInTime){
    this.checkInTime = checkInTime;
}


public Float getRealIncome(){
    return realIncome;
}


public String getRoomType(){
    return roomType;
}


public Float getShouldIncome(){
    return shouldIncome;
}


public void setShouldIncome(Float shouldIncome){
    this.shouldIncome = shouldIncome;
}


public String getBookGuest(){
    return bookGuest;
}


public void setBookGuest(String bookGuest){
    this.bookGuest = bookGuest;
}


public void setTotalIncome(Float totalIncome){
    this.totalIncome = totalIncome;
}


public void setRealIncome(Float realIncome){
    this.realIncome = realIncome;
}


public void setCheckOutTime(Date checkOutTime){
    this.checkOutTime = checkOutTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getCheckInTime(){
    return checkInTime;
}


}