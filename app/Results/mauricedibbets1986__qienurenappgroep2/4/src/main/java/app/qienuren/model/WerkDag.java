package app.qienuren.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Entity
public class WerkDag {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  LocalDate datum;

 private  double opdrachtUren;

 private  double overwerkUren;

 private  double verlofUren;

 private  double ziekteUren;

 private  double trainingsUren;

 private  double overigeUren;

 private  String overigeUrenUitleg;

public WerkDag() {
}public WerkDag(long jaar, long maand, int dag) {
    int invoerJaar = (int) jaar;
    int invoerMaand = (int) maand;
    LocalDate invoer = LocalDate.of(invoerJaar, invoerMaand, dag);
    setDatum(invoer);
}
public long getId(){
    return id;
}


public double getOverigeUren(){
    return overigeUren;
}


public double getVerlofUren(){
    return verlofUren;
}


public double getZiekteUren(){
    return ziekteUren;
}


public LocalDate getDatum(){
    return datum;
}


public void setTrainingsUren(double trainingsUren){
    this.trainingsUren = trainingsUren;
}


public double getOpdrachtUren(){
    return opdrachtUren;
}


public void setOverwerkUren(double overwerkUren){
    this.overwerkUren = overwerkUren;
}


public void setDatum(LocalDate datum){
    this.datum = datum;
}


public void setId(long id){
    this.id = id;
}


public void setOverigeUren(double overigeUren){
    this.overigeUren = overigeUren;
}


public String getOverigeUrenUitleg(){
    return overigeUrenUitleg;
}


public double getOverwerkUren(){
    return overwerkUren;
}


public double getTrainingsUren(){
    return trainingsUren;
}


public void setOverigeUrenUitleg(String overigeUrenUitleg){
    this.overigeUrenUitleg = overigeUrenUitleg;
}


public void setOpdrachtUren(double opdrachtUren){
    this.opdrachtUren = opdrachtUren;
}


public void setVerlofUren(double verlofUren){
    this.verlofUren = verlofUren;
}


public void setZiekteUren(double ziekteUren){
    this.ziekteUren = ziekteUren;
}


}