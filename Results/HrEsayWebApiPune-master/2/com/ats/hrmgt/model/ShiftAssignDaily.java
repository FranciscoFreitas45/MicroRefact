import javax.persistence;
@Entity
@Table(name = "t_shift_assign_daily")
public class ShiftAssignDaily {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "emp_id")
 private  int empId;

@Column(name = "shift_id")
 private  int shiftId;

@Column(name = "shift_date")
 private  String shiftDate;

@Column(name = "month")
 private  int month;

@Column(name = "year")
 private  int year;

@Column(name = "extra1")
 private  int extra1;

@Column(name = "extra2")
 private  int extra2;

@Column(name = "var1")
 private  String var1;

@Column(name = "var2")
 private  String var2;

@Column(name = "emp_code")
 private  String empCode;


public void setShiftId(int shiftId){
    this.shiftId = shiftId;
}


public void setMonth(int month){
    this.month = month;
}


public String getVar1(){
    return var1;
}


public String getVar2(){
    return var2;
}


public int getId(){
    return id;
}


public int getEmpId(){
    return empId;
}


public void setYear(int year){
    this.year = year;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public int getShiftId(){
    return shiftId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getEmpCode(){
    return empCode;
}


public void setExtra2(int extra2){
    this.extra2 = extra2;
}


public int getYear(){
    return year;
}


public void setExtra1(int extra1){
    this.extra1 = extra1;
}


public int getExtra1(){
    return extra1;
}


public void setShiftDate(String shiftDate){
    this.shiftDate = shiftDate;
}


public void setId(int id){
    this.id = id;
}


public int getExtra2(){
    return extra2;
}


@Override
public String toString(){
    return "ShiftAssignDaily [id=" + id + ", empId=" + empId + ", shiftId=" + shiftId + ", shiftDate=" + shiftDate + ", month=" + month + ", year=" + year + ", extra1=" + extra1 + ", extra2=" + extra2 + ", var1=" + var1 + ", var2=" + var2 + ", empCode=" + empCode + "]";
}


public int getMonth(){
    return month;
}


public void setVar2(String var2){
    this.var2 = var2;
}


public void setVar1(String var1){
    this.var1 = var1;
}


public String getShiftDate(){
    return shiftDate;
}


}