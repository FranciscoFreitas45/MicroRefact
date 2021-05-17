import javax.persistence;
@Entity
public class LwfChallanData {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "emp_count")
 private  int empCount;

@Column(name = "count_lwf")
 private  int countLwf;

@Column(name = "lwf_no_count")
 private  int lwfNoCount;

@Column(name = "mlwf")
 private  float mlwf;

@Column(name = "employer_mlwf")
 private  float employerMlwf;

@Column(name = "location_id")
 private  int locationId;

@Column(name = "month")
 private  int month;

@Column(name = "employer_value")
 private  float employerValue;

@Column(name = "employee_value")
 private  float employeeValue;


public void setEmployerMlwf(float employerMlwf){
    this.employerMlwf = employerMlwf;
}


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


public int getEmpCount(){
    return empCount;
}


public float getMlwf(){
    return mlwf;
}


public void setEmployeeValue(float employeeValue){
    this.employeeValue = employeeValue;
}


public void setLwfNoCount(int lwfNoCount){
    this.lwfNoCount = lwfNoCount;
}


public String getId(){
    return id;
}


public void setCountLwf(int countLwf){
    this.countLwf = countLwf;
}


public int getCountLwf(){
    return countLwf;
}


public int getLwfNoCount(){
    return lwfNoCount;
}


public void setId(String id){
    this.id = id;
}


public void setLocationId(int locationId){
    this.locationId = locationId;
}


public void setEmpCount(int empCount){
    this.empCount = empCount;
}


@Override
public String toString(){
    return "LwfChallanData [id=" + id + ", empCount=" + empCount + ", countLwf=" + countLwf + ", lwfNoCount=" + lwfNoCount + ", mlwf=" + mlwf + ", employerMlwf=" + employerMlwf + ", locationId=" + locationId + ", month=" + month + ", employerValue=" + employerValue + ", employeeValue=" + employeeValue + "]";
}


public int getMonth(){
    return month;
}


public float getEmployeeValue(){
    return employeeValue;
}


public void setMlwf(float mlwf){
    this.mlwf = mlwf;
}


public float getEmployerMlwf(){
    return employerMlwf;
}


}