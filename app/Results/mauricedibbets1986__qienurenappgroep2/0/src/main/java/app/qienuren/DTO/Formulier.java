package app.qienuren.DTO;
 import javax.persistence;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
public class Formulier {

 private  long id;

 private  long maand;

 private  long jaar;

 private  Persoon medewerker;

 private  AdminStatus adminStatus;

 private  OpdrachtgeverStatus opdrachtgeverStatus;

 private  List<WerkDag> werkDagen;

 private  boolean tijdelijkFormulier;

 private  boolean ingezondenFormulier;

public Formulier() {
}public Formulier(long maand, long jaar) {
    this.setMaand(maand);
    this.setJaar(jaar);
    int aantalDagen = dagenInMaand(jaar, maand);
    for (int dag = 1; dag <= aantalDagen; dag++) {
        this.werkDagen.add(new WerkDag(jaar, maand, dag));
    }
    this.tijdelijkFormulier = true;
}
public AdminStatus getAdminStatus(){
    return adminStatus;
}


public Persoon getMedewerker(){
    return medewerker;
}


public long getMaand(){
    return maand;
}


public OpdrachtgeverStatus getOpdrachtgeverStatus(){
    return opdrachtgeverStatus;
}


public long getId(){
    return id;
}


public List<WerkDag> getWerkDagen(){
    return werkDagen;
}


public long getJaar(){
    return jaar;
}


}