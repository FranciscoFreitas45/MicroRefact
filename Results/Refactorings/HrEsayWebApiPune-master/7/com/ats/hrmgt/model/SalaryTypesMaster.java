import javax.persistence;
@Entity
@Table(name = "mst_salary_types")
public class SalaryTypesMaster {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "sal_type_id")
 private  int salTypeId;

@Column(name = "sal_type_name")
 private  String salTypeName;

@Column(name = "description")
 private  String description;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "workinghr ")
 private  double workinghr;

@Column(name = "cal_sal")
 private  int calSal;

@Column(name = "show_in_payslip_pdf")
 private  int showInPayslipPdf;

@Column(name = "show_excel")
 private  int showExcel;

@Column(name = "del_status")
 private  int delStatus;


public String getSalTypeName(){
    return salTypeName;
}


public int getShowInPayslipPdf(){
    return showInPayslipPdf;
}


public int getShowExcel(){
    return showExcel;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public int getSalTypeId(){
    return salTypeId;
}


public void setShowInPayslipPdf(int showInPayslipPdf){
    this.showInPayslipPdf = showInPayslipPdf;
}


public double getWorkinghr(){
    return workinghr;
}


public void setWorkinghr(double workinghr){
    this.workinghr = workinghr;
}


public void setSalTypeName(String salTypeName){
    this.salTypeName = salTypeName;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public int getCalSal(){
    return calSal;
}


public void setSalTypeId(int salTypeId){
    this.salTypeId = salTypeId;
}


public void setCalSal(int calSal){
    this.calSal = calSal;
}


public void setShowExcel(int showExcel){
    this.showExcel = showExcel;
}


@Override
public String toString(){
    return "SalaryTypesMaster [salTypeId=" + salTypeId + ", salTypeName=" + salTypeName + ", description=" + description + ", companyId=" + companyId + ", workinghr=" + workinghr + ", calSal=" + calSal + ", showInPayslipPdf=" + showInPayslipPdf + ", showExcel=" + showExcel + ", delStatus=" + delStatus + "]";
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


}