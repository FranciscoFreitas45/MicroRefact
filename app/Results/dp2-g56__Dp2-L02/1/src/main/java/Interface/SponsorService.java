package Interface;
public interface SponsorService {

   public Sponsor loggedSponsor();
   public Sponsor reconstructSponsor(Sponsor Sponsor,BindingResult binding);
   public Sponsor save(Sponsor h);
}