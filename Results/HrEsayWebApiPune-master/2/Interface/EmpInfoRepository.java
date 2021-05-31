public interface EmpInfoRepository {

   public List<EmpInfo> getEmpListLocId(String fromDate,String toDate,List<Integer> locId);
   public List<EmpInfo> getEmpListAlllocId(String fromDate,List<Integer> locId);
   public List<EmpInfo> getEmpListForHod(String fromDate,int userId);
   public List<EmpInfo> getEmpListAll();
}