package app.qienuren.Interface;
public interface PersoonService {

   public Iterable<Persoon> getAllMedewerkers();
   public Persoon wijzigGegevens(long oorspronkelijkeId,long id);
}