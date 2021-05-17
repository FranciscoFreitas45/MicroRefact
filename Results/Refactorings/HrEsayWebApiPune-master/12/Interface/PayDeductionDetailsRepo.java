public interface PayDeductionDetailsRepo {

   public List<PayDeductionDetails> getEmpPayDedAllEmp(String fromYear,String fromMonth,String toYear,String toMonth);
   public List<PayDeductionDetails> getEmpPayDed(String fromYear,String fromMonth,String toYear,String toMonth,int empId);
}