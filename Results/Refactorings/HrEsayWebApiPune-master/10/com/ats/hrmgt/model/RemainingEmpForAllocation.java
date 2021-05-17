import javax.persistence;
@Entity
public class RemainingEmpForAllocation {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "group_id")
 private  int groupId;

@Column(name = "group_type")
 private  int groupType;

@Column(name = "shift_id")
 private  int shiftId;

@Column(name = "max_date")
 private  String maxDate;

@Column(name = "location_id")
 private  int locationId;


public void setShiftId(int shiftId){
    this.shiftId = shiftId;
}


public int getLocationId(){
    return locationId;
}


public void setGroupId(int groupId){
    this.groupId = groupId;
}


public String getMaxDate(){
    return maxDate;
}


public int getGroupType(){
    return groupType;
}


public void setGroupType(int groupType){
    this.groupType = groupType;
}


public int getEmpId(){
    return empId;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public int getGroupId(){
    return groupId;
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


public void setMaxDate(String maxDate){
    this.maxDate = maxDate;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


@Override
public String toString(){
    return "RemainingEmpForAllocation [empId=" + empId + ", empCode=" + empCode + ", groupId=" + groupId + ", groupType=" + groupType + ", shiftId=" + shiftId + ", maxDate=" + maxDate + ", locationId=" + locationId + "]";
}


}