package restock.services;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.entities.Comanda;
import restock.entities.DetallComanda;
import restock.repository.DetallComandaRepository;
@Component
public class DetallComandaBusiness {

@Autowired
 private  DetallComandaRepository detallComandaRepository;


public List<DetallComanda> cercaDetallComanda(Comanda comanda){
    List<DetallComanda> detallComandaList = new ArrayList<DetallComanda>();
    detallComandaList = detallComandaRepository.findByComandaId(comanda.getId());
    if (detallComandaList.size() > 0) {
        return detallComandaList;
    } else
        return null;
}


}