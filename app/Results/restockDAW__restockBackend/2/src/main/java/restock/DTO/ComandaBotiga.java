package restock.DTO;
 import java.util.List;
import restock.entities.Botiga;
import restock.entities.DetallComanda;
import restock.Interface.Botiga;
public class ComandaBotiga {

 private  long serialVersionUID;

 private  List<DetallComanda> detallComandaList;

 private  Botiga botiga;

/**
 * Comanda botiga.
 */
public ComandaBotiga() {
    super();
}/**
 * Comanda botiga.
 *
 * @param detallComandaList
 * @param botiga
 */
public ComandaBotiga(List<DetallComanda> detallComandaList, Botiga botiga) {
    super();
    this.detallComandaList = detallComandaList;
    this.botiga = botiga;
}
public Botiga getBotiga(){
    return botiga;
}


public List<DetallComanda> getDetallComandaList(){
    return detallComandaList;
}


}