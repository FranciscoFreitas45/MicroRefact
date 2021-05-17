import java.util.List;
public class PayRollDataForProcessing {

 private  List<EmpSalaryInfoForPayroll> list;

 private  List<Allowances> allowancelist;

 private  List<GetPayrollGeneratedList> payrollGeneratedList;

 private  List<GetOnelineReport> onelinereportlist;

 private  List<GetDeptPayReport> deptreportlist;


public List<GetDeptPayReport> getDeptreportlist(){
    return deptreportlist;
}


public List<GetPayrollGeneratedList> getPayrollGeneratedList(){
    return payrollGeneratedList;
}


public void setPayrollGeneratedList(List<GetPayrollGeneratedList> payrollGeneratedList){
    this.payrollGeneratedList = payrollGeneratedList;
}


public void setAllowancelist(List<Allowances> allowancelist){
    this.allowancelist = allowancelist;
}


public List<EmpSalaryInfoForPayroll> getList(){
    return list;
}


public List<GetOnelineReport> getOnelinereportlist(){
    return onelinereportlist;
}


@Override
public String toString(){
    return "PayRollDataForProcessing [list=" + list + ", allowancelist=" + allowancelist + ", payrollGeneratedList=" + payrollGeneratedList + ", onelinereportlist=" + onelinereportlist + ", deptreportlist=" + deptreportlist + "]";
}


public List<Allowances> getAllowancelist(){
    return allowancelist;
}


public void setList(List<EmpSalaryInfoForPayroll> list){
    this.list = list;
}


public void setDeptreportlist(List<GetDeptPayReport> deptreportlist){
    this.deptreportlist = deptreportlist;
}


public void setOnelinereportlist(List<GetOnelineReport> onelinereportlist){
    this.onelinereportlist = onelinereportlist;
}


}