package app.qienuren.model;
 import javax.persistence;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import app.qienuren.Request.PersoonRequest;
import app.qienuren.Request.Impl.PersoonRequestImpl;
import app.qienuren.DTO.Persoon;
@Entity
public class Formulier {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  long maand;

 private  long jaar;

@Transient
 private  Persoon medewerker;

 private  AdminStatus adminStatus;

 private  OpdrachtgeverStatus opdrachtgeverStatus;

@OneToMany(cascade = CascadeType.ALL)
 private  List<WerkDag> werkDagen;

 private  boolean tijdelijkFormulier;

 private  boolean ingezondenFormulier;

@Column(name = "idN8E9")
 private long idN8E9;

@Transient
 private PersoonRequest persoonrequest = new PersoonRequestImpl();;

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
public void setJaar(long jaar){
    this.jaar = jaar;
}


public void setIngezondenFormulier(boolean ingezondenFormulier){
    this.ingezondenFormulier = ingezondenFormulier;
}


public void setMedewerker(Persoon medewerker){
this.idN8E9 = medewerker.getMedewerker() ;
persoonrequest.setMedewerker(medewerker,this.idN8E9);
 this.medewerker = medewerker;
}



public AdminStatus getAdminStatus(){
    return adminStatus;
}


public Persoon getMedewerker(){
  this.medewerker = persoonrequest.getMedewerker(this.idN8E9);
return this.medewerker;
}}



public long getMaand(){
    return maand;
}


public boolean isIngezondenFormulier(){
    return ingezondenFormulier;
}


public OpdrachtgeverStatus getOpdrachtgeverStatus(){
    return opdrachtgeverStatus;
}


public void setOpdrachtgeverStatus(OpdrachtgeverStatus opdrachtgeverStatus){
    this.opdrachtgeverStatus = opdrachtgeverStatus;
}


public long getId(){
    return id;
}


public List<WerkDag> getWerkDagen(){
    return werkDagen;
}


public boolean isTijdelijkFormulier(){
    return tijdelijkFormulier;
}


public int dagenInMaand(long jaar,long maand){
    YearMonth yearMonthObject = YearMonth.of((int) jaar, (int) maand);
    ;
    // 28
    int daysInMonth = yearMonthObject.lengthOfMonth();
    System.out.println(daysInMonth);
    return daysInMonth;
}


public void setAdminStatus(AdminStatus adminStatus){
    this.adminStatus = adminStatus;
}


public void setMaand(long maand){
    this.maand = maand;
}


public void setId(long id){
    this.id = id;
}


public void setWerkDagen(List<WerkDag> werkDagen){
    this.werkDagen = werkDagen;
}


public void setTijdelijkFormulier(boolean tijdelijkFormulier){
    this.tijdelijkFormulier = tijdelijkFormulier;
}


public long getJaar(){
    return jaar;
}


}