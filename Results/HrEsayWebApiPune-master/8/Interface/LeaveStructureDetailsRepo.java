public interface LeaveStructureDetailsRepo {

   public List<LeaveStructureDetails> findByLvsIdAndDelStatus(int lvsId,int i);
   public Object saveAll(Object Object);
}