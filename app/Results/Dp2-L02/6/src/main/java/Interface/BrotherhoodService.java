package Interface;
public interface BrotherhoodService {

   public Brotherhood createBrotherhood();
   public Brotherhood reconstruct(FormObjectBrotherhood formObjectBrotherhood,BindingResult binding);
   public Brotherhood saveCreate(Brotherhood bro);
}