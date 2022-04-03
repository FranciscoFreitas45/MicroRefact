package restock.services;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.dto.ComandaBotiga;
import restock.entities.Botiga;
import restock.entities.DetallComanda;
import restock.entities.Inventari;
import restock.entities.Organitzacio;
import restock.repository.BotigaRepository;
import restock.repository.InventariRepository;
import restock.DTO.Organitzacio;
import restock.DTO.ComandaBotiga;
import restock.DTO.DetallComanda;
@Component
public class InventariBusiness {

@Autowired
 private  InventariRepository inventariRepository;

@Autowired
 private  BotigaRepository botigaRepository;


public List<Inventari> cercaInventariPerBotiga(Botiga botiga){
    List<Inventari> inventaris = new ArrayList<Inventari>();
    inventaris = inventariRepository.findByBotigaId(botiga.getId());
    if (inventaris.size() > 0) {
        return inventaris;
    } else
        return null;
}


public List<Inventari> cercaInventariPerOrganitzacio(Organitzacio org){
    List<Botiga> botigues = new ArrayList<Botiga>();
    botigues = botigaRepository.findByOrganitzacioId(org.getId());
    List<Inventari> inventaris = new ArrayList<Inventari>();
    if (botigues.size() > 0) {
        for (Botiga botiga : botigues) {
            List<Inventari> inventariPerBotiga = inventariRepository.findByBotigaId(botiga.getId());
            inventaris.addAll(inventariPerBotiga);
        }
    }
    if (inventaris.size() > 0) {
        return inventaris;
    } else
        return null;
}


public List<Inventari> actualitzarInventari(ComandaBotiga comandaBotiga){
    // actualitzem productes i quantitats
    for (DetallComanda detallComanda : comandaBotiga.getDetallComandaList()) {
        Inventari inventari = inventariRepository.findByBotigaIdAndProducteId(comandaBotiga.getBotiga().getId(), detallComanda.getProducte().getId());
        if (inventari != null) {
            inventari.setQuantitat(inventari.getQuantitat() + detallComanda.getQuantitat());
            inventariRepository.save(inventari);
        } else {
            Inventari inv = new Inventari();
            inv.setBotiga(comandaBotiga.getBotiga());
            inv.setQuantitat(detallComanda.getQuantitat());
            inv.setProducte(detallComanda.getProducte());
            inventariRepository.save(inv);
        }
    }
    List<Inventari> inventaris = new ArrayList<Inventari>();
    inventaris = inventariRepository.findByBotigaId(comandaBotiga.getBotiga().getId());
    if (inventaris.size() > 0) {
        return inventaris;
    } else
        return null;
}


}