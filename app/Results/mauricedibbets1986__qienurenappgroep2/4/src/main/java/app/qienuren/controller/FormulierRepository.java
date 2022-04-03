package app.qienuren.controller;
 import app.qienuren.model.Formulier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FormulierRepository extends CrudRepository<Formulier, Long>{


public List<Formulier> getArchief(long id);

public List<Formulier> getTijdelijkeFormulieren(long id);

public void verwijderFormulierUitTijdelijkeLijst(long id,Formulier tf);

public void voegFormulierToe(long id,Formulier tf);

public void voegFormulierToeAanArchief(long id,Formulier f);

public void koppelFormulier(long id,Formulier formulierTijdelijk);

public void setArchief(long id,List<Formulier> archief);

public void setTijdelijkeFormulieren(long id,List<Formulier> tijdelijkeFormulieren);

public List<Formulier> getArchief(long id);

public List<Formulier> getTijdelijkeFormulieren(long id);

public void verwijderFormulierUitTijdelijkeLijst(long id,Formulier tf);

public void voegFormulierToe(long id,Formulier tf);

public void voegFormulierToeAanArchief(long id,Formulier f);

public void koppelFormulier(long id,Formulier formulierTijdelijk);

public void setArchief(long id,List<Formulier> archief);

public void setTijdelijkeFormulieren(long id,List<Formulier> tijdelijkeFormulieren);

}