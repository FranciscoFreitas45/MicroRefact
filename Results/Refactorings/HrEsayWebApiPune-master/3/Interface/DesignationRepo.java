public interface DesignationRepo {

   public List<Designation> findByNameAndCompanyIdAndDesigIdNot(String trim,int compId,int primaryKey);
   public List<Designation> findByNameAndCompanyId(String desgn,int compId);
}