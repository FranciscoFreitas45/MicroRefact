package restock.dto;
 import java.util.List;
import restock.entities.Botiga;
import restock.entities.DetallComanda;
public class ComandaBotiga {

 private  long serialVersionUID;

 private  List<DetallComanda> detallComandaList;

 private  Botiga botiga;


public Botiga getBotiga(){
    return botiga;
}


public void setBotiga(Botiga botiga){
    this.botiga = botiga;
}


public List<DetallComanda> getDetallComandaList(){
    return detallComandaList;
}


public void setDetallComandaList(List<DetallComanda> detallComandaList){
    this.detallComandaList = detallComandaList;
}


}