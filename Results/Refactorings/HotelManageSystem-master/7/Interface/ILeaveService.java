public interface ILeaveService {

   public float findTotalLeaveTimes(String userName);
   public int findTatalPersonLeave();
   public List<Map<Object,Object>> findByyearAndOntudytimeleave(Integer year,String userName);
   public List<Map<Object,Object>> findleave(Integer year);
}