import javax.persistence;
public class SalaryTypesMaster {

 private  int salTypeId;

 private  String salTypeName;

 private  String description;

 private  int companyId;

 private  double workinghr;

 private  int calSal;

 private  int showInPayslipPdf;

 private  int showExcel;

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


public String getDescription(){
    return description;
}


public int getSalTypeId(){
    return salTypeId;
}


public double getWorkinghr(){
    return workinghr;
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


}