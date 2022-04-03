package Interface;
public interface BrotherhoodService {

   public List<Brotherhood> findAll();
   public Brotherhood save(Brotherhood h);
   public Brotherhood createBrotherhood();
   public Brotherhood reconstruct(FormObjectBrotherhood formObjectBrotherhood,BindingResult binding);
   public Brotherhood saveCreate(Brotherhood bro);
}