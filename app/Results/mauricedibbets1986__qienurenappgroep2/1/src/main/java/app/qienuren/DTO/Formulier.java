package app.qienuren.DTO;
 import javax.persistence;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import app.qienuren.Request.PersoonRequest;
import app.qienuren.Request.Impl.PersoonRequestImpl;
import app.qienuren.DTO.Persoon;
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
public void setIngezondenFormulier(boolean ingezondenFormulier){
    this.ingezondenFormulier = ingezondenFormulier;
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


public int dagenInMaand(long jaar,long maand){
    YearMonth yearMonthObject = YearMonth.of((int) jaar, (int) maand);
    ;
    // 28
    int daysInMonth = yearMonthObject.lengthOfMonth();
    System.out.println(daysInMonth);
    return daysInMonth;
}


public void setMaand(long maand){
    this.maand = maand;
}


public void setWerkDagen(List<WerkDag> werkDagen){
    this.werkDagen = werkDagen;
}


public long getJaar(){
    return jaar;
}


}