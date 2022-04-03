package app.qienuren.Interface;
public interface FormulierService {

   public Iterable<Formulier> getAlleFormulierenVoorAdmin();
   public Formulier AdminStatusGoed(long formulierid,long medewerkerid);
   public Formulier AdminStatusFout(long id,long medewerkerid);
}