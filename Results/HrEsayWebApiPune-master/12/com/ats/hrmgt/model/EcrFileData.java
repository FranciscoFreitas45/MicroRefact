import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class EcrFileData {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  String id;

 private  String uan;

 private  String firstName;

 private  String middleName;

 private  String surname;

 private  int grossSalary;

 private  int epfWages;

 private  int epfWages_employer;

 private  int epsWages;

 private  int employeePf;

 private  int employerEps;

 private  int employerPf;

 private  int ncpDays;

 private  int adv;


public int getEpfWages_employer(){
    return epfWages_employer;
}


public int getGrossSalary(){
    return grossSalary;
}


public int getEmployerPf(){
    return employerPf;
}


public String getId(){
    return id;
}


public String getMiddleName(){
    return middleName;
}


public void setSurname(String surname){
    this.surname = surname;
}


public int getEpfWages(){
    return epfWages;
}


public void setEmployerEps(int employerEps){
    this.employerEps = employerEps;
}


public void setId(String id){
    this.id = id;
}


public void setEpfWages(int epfWages){
    this.epfWages = epfWages;
}


public void setMiddleName(String middleName){
    this.middleName = middleName;
}


public void setEpsWages(int epsWages){
    this.epsWages = epsWages;
}


public int getEmployerEps(){
    return employerEps;
}


public void setUan(String uan){
    this.uan = uan;
}


public void setEpfWages_employer(int epfWages_employer){
    this.epfWages_employer = epfWages_employer;
}


public String getUan(){
    return uan;
}


public int getEpsWages(){
    return epsWages;
}


public int getNcpDays(){
    return ncpDays;
}


public void setEmployeePf(int employeePf){
    this.employeePf = employeePf;
}


public void setAdv(int adv){
    this.adv = adv;
}


public void setGrossSalary(int grossSalary){
    this.grossSalary = grossSalary;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public int getAdv(){
    return adv;
}


public void setNcpDays(int ncpDays){
    this.ncpDays = ncpDays;
}


public void setEmployerPf(int employerPf){
    this.employerPf = employerPf;
}


@Override
public String toString(){
    return "EcrFileData [id=" + id + ", uan=" + uan + ", firstName=" + firstName + ", middleName=" + middleName + ", surname=" + surname + ", grossSalary=" + grossSalary + ", epfWages=" + epfWages + ", epfWages_employer=" + epfWages_employer + ", epsWages=" + epsWages + ", employeePf=" + employeePf + ", employerEps=" + employerEps + ", employerPf=" + employerPf + ", ncpDays=" + ncpDays + ", adv=" + adv + "]";
}


public String getFirstName(){
    return firstName;
}


public int getEmployeePf(){
    return employeePf;
}


public String getSurname(){
    return surname;
}


}