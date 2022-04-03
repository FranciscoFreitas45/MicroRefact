package Interface;
public interface BrotherhoodService {

   public void loggedAsBrotherhood();
   public Brotherhood loggedBrotherhood();
   public Brotherhood save(Brotherhood h);
   public Brotherhood securityAndBrotherhood();
   public List<Parade> getParadesByBrotherhood(Brotherhood b);
}