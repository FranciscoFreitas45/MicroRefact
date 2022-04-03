package Interface;
public interface SponsorService {

   public Sponsor createSponsor();
   public Sponsor reconstruct(FormObjectSponsor formObjectSponsor,BindingResult binding);
   public Sponsor saveCreate(Sponsor bro);
}