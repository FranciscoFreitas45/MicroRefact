package app.qienuren.Interface;
public interface InterneMedewerkerService {

   public InterneMedewerker addInterneMederwerker(InterneMedewerker interneMedewerker);
   public Iterable<InterneMedewerker> getAllInterneMedewerkers();
   public InterneMedewerker wijzigGegevens(long oorspronkelijkeId,long id);
}