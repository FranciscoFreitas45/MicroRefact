package Interface;
public interface BrotherhoodService {

   public List<Brotherhood> findAll();
   public Brotherhood save(Brotherhood h);
   public void deleteBrotherhood();
}