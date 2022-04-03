package app.qienuren.model;
 import javax.persistence.Entity;
@Entity
public class TijdelijkFormulier extends Formulier{

public TijdelijkFormulier() {
}public TijdelijkFormulier(long maand, long jaar) {
    super(maand, jaar);
}
}