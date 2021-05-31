import javax.persistence;
@Entity
@Table(name = "tbl_mlwf")
public class MlwfMaster {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "location_id")
 private  int locationId;

@Column(name = "month")
 private  int month;

@Column(name = "employer_value")
 private  float employerValue;

@Column(name = "employee_value")
 private  float employeeValue;

@Column(name = "extra_int1")
 private  int extraInt1;

@Column(name = "extra_int2")
 private  int extraInt2;

@Column(name = "extra_var1")
 private  String extraVar1;

@Column(name = "extra_var2")
 private  String extraVar2;


public float getEmployerValue(){
    return employerValue;
}


public int getLocationId(){
    return locationId;
}


public void setMonth(int month){
    this.month = month;
}


public void setEmployerValue(float employerValue){
    this.employerValue = employerValue;
}


public void setEmployeeValue(float employeeValue){
    this.employeeValue = employeeValue;
}


public int getId(){
    return id;
}


public void setExtraVar1(String extraVar1){
    this.extraVar1 = extraVar1;
}


public String getExtraVar2(){
    return extraVar2;
}


public String getExtraVar1(){
    return extraVar1;
}


public void setExtraVar2(String extraVar2){
    this.extraVar2 = extraVar2;
}


public int getExtraInt2(){
    return extraInt2;
}


public void setExtraInt2(int extraInt2){
    this.extraInt2 = extraInt2;
}


public int getExtraInt1(){
    return extraInt1;
}


public void setExtraInt1(int extraInt1){
    this.extraInt1 = extraInt1;
}


public void setId(int id){
    this.id = id;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


@Override
public String toString(){
    return "MlwfMaster [id=" + id + ", locationId=" + locationId + ", month=" + month + ", employerValue=" + employerValue + ", employeeValue=" + employeeValue + ", extraInt1=" + extraInt1 + ", extraInt2=" + extraInt2 + ", extraVar1=" + extraVar1 + ", extraVar2=" + extraVar2 + "]";
}


public int getMonth(){
    return month;
}


public float getEmployeeValue(){
    return employeeValue;
}


}