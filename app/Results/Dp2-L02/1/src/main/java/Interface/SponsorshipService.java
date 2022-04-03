package Interface;
public interface SponsorshipService {

   public Sponsorship getRandomSponsorship(int paradeId);
   public void updateSpentMoneyOfSponsorship(int paradeId,int sponsorshipId);
}