import javax.persistence;
@Entity
public class EmpShiftAllocationDetail {

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

@Column(name = "shiftname")
 private  String shiftname;

@Transient
 private  String extra;

@Transient
 private  int extraType;


public int getExtraType(){
    return extraType;
}


public void setExtraType(int extraType){
    this.extraType = extraType;
}


public void setShiftId(int shiftId){
    this.shiftId = shiftId;
}


public void setShiftname(String shiftname){
    this.shiftname = shiftname;
}


public int getId(){
    return id;
}


public int getEmpId(){
    return empId;
}


public int getShiftId(){
    return shiftId;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public String getShiftname(){
    return shiftname;
}


public void setShiftDate(String shiftDate){
    this.shiftDate = shiftDate;
}


public void setId(int id){
    this.id = id;
}


@Override
public String toString(){
    return "EmpShiftAllocationDetail [id=" + id + ", empId=" + empId + ", shiftId=" + shiftId + ", shiftDate=" + shiftDate + ", shiftname=" + shiftname + ", extra=" + extra + ", extraType=" + extraType + "]";
}


public void setExtra(String extra){
    this.extra = extra;
}


public String getShiftDate(){
    return shiftDate;
}


public String getExtra(){
    return extra;
}


}