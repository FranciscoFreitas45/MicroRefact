package app.qienuren.Request;
import app.qienuren.DTO.Formulier;
public interface FormulierRequest {

   public List<Formulier> getArchief(long id);
   public List<Formulier> getTijdelijkeFormulieren(long id);
   public void verwijderFormulierUitTijdelijkeLijst(Formulier tf,long id);
   public void voegFormulierToe(Formulier tf,long id);
   public void voegFormulierToeAanArchief(Formulier f,long id);
   public void koppelFormulier(Formulier formulierTijdelijk,long id);
   public void setArchief(List<Formulier> archief,long id);
   public void setTijdelijkeFormulieren(List<Formulier> tijdelijkeFormulieren,long id);
}