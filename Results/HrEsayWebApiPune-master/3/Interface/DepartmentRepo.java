public interface DepartmentRepo {

   public List<Department> findByNameAndCompanyIdAndDepartIdNot(String trim,int compId,int primaryKey);
   public List<Department> findByNameAndCompanyId(String dept,int compId);
}