import javax.persistence;
@Entity
public class EmpDetailForOptionalHoliday {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "emp_id")
 private  int empId;

@Column(name = "emp_code")
 private  String empCode;

@Column(name = "emp_name")
 private  String empName;

@Column(name = "holiday_category")
 private  int holidayCategory;

@Column(name = "weekend_category")
 private  int weekendCategory;

@Column(name = "dept_name")
 private  String deptName;

@Column(name = "emp_desgn")
 private  String empDesgn;

@Column(name = "loc_name")
 private  String locName;

@Column(name = "wo_cat_name")
 private  String woCatName;

@Column(name = "ho_cat_name")
 private  String hoCatName;

@Column(name = "used_ho")
 private  int usedHo;

@Column(name = "optional_holiday")
 private  int optionalHoliday;

@Column(name = "apply_ho")
 private  int applyHo;


public String getEmpDesgn(){
    return empDesgn;
}


public void setOptionalHoliday(int optionalHoliday){
    this.optionalHoliday = optionalHoliday;
}


public String getLocName(){
    return locName;
}


public void setWoCatName(String woCatName){
    this.woCatName = woCatName;
}


public void setHoCatName(String hoCatName){
    this.hoCatName = hoCatName;
}


public void setLocName(String locName){
    this.locName = locName;
}


public void setWeekendCategory(int weekendCategory){
    this.weekendCategory = weekendCategory;
}


public void setEmpCode(String empCode){
    this.empCode = empCode;
}


public String getEmpName(){
    return empName;
}


public void setUsedHo(int usedHo){
    this.usedHo = usedHo;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public int getUsedHo(){
    return usedHo;
}


public int getOptionalHoliday(){
    return optionalHoliday;
}


public int getApplyHo(){
    return applyHo;
}


public void setApplyHo(int applyHo){
    this.applyHo = applyHo;
}


public String getHoCatName(){
    return hoCatName;
}


public int getEmpId(){
    return empId;
}


public String getDeptName(){
    return deptName;
}


public void setEmpDesgn(String empDesgn){
    this.empDesgn = empDesgn;
}


public void setEmpId(int empId){
    this.empId = empId;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getEmpCode(){
    return empCode;
}


public int getHolidayCategory(){
    return holidayCategory;
}


@Override
public String toString(){
    return "EmpDetailForOptionalHoliday [empId=" + empId + ", empCode=" + empCode + ", empName=" + empName + ", holidayCategory=" + holidayCategory + ", weekendCategory=" + weekendCategory + ", deptName=" + deptName + ", empDesgn=" + empDesgn + ", locName=" + locName + ", woCatName=" + woCatName + ", hoCatName=" + hoCatName + ", usedHo=" + usedHo + ", optionalHoliday=" + optionalHoliday + ", applyHo=" + applyHo + "]";
}


public int getWeekendCategory(){
    return weekendCategory;
}


public void setHolidayCategory(int holidayCategory){
    this.holidayCategory = holidayCategory;
}


public String getWoCatName(){
    return woCatName;
}


}