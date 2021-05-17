public interface TblEmpBankInfoRepo {

   public int deleteEmpBankInfo(int empId);
   public Object save(Object Object);
   public TblEmpBankInfo findByEmpIdAndDelStatus(int empId,int del);
}