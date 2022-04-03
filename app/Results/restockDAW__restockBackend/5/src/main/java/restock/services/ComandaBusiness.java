package restock.services;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.dto.ComandaBotiga;
import restock.entities.Botiga;
import restock.entities.Comanda;
import restock.entities.DetallComanda;
import restock.entities.Organitzacio;
import restock.entities.Proveidor;
import restock.repository.BotigaRepository;
import restock.repository.ComandaRepository;
import restock.repository.DetallComandaRepository;
import restock.repository.ProducteRepository;
import restock.repository.ProveidorRepository;
import restock.Interface.ProveidorRepository;
import restock.Interface.ProducteRepository;
import restock.Interface.BotigaRepository;
import restock.DTO.Organitzacio;
import restock.DTO.Proveidor;
@Component
public class ComandaBusiness {

@Autowired
 private  ComandaRepository comandaRepository;

@Autowired
 private  DetallComandaRepository detallComandaRepository;

@Autowired
 private  ProveidorRepository proveidorRepository;

@Autowired
 private  ProducteRepository producteRepository;

@Autowired
 private  BotigaRepository botigaRepository;


public List<Comanda> cercaComandesPendentsPerBotiga(Botiga botiga){
    List<Comanda> comandes = new ArrayList<Comanda>();
    comandes = comandaRepository.findPendentsByBotigaId(botiga.getId(), new Date());
    if (comandes.size() > 0) {
        return comandes;
    } else
        return null;
}


public List<Comanda> cercaComandesPendentsPerOrganitzacio(Organitzacio org){
    List<Botiga> botigues = new ArrayList<Botiga>();
    botigues = botigaRepository.findByOrganitzacioId(org.getId());
    List<Comanda> comandes = new ArrayList<Comanda>();
    if (botigues.size() > 0) {
        for (Botiga botiga : botigues) {
            List<Comanda> comandaPerBotiga = comandaRepository.findPendentsByBotigaId(botiga.getId(), new Date());
            comandes.addAll(comandaPerBotiga);
        }
    }
    if (comandes.size() > 0) {
        return comandes;
    } else {
        return null;
    }
}


public Comanda guardaComanda(ComandaBotiga comandaBotiga){
    if (comandaBotiga.getDetallComandaList().size() > 0) {
        Comanda comanda = new Comanda();
        comanda.setBotiga(botigaRepository.findById(comandaBotiga.getBotiga().getId()));
        comanda.setDatacomanda(new Date());
        Proveidor proveidor = proveidorRepository.findById(producteRepository.findById(comandaBotiga.getDetallComandaList().get(0).getProducte().getId()).getProveidor().getId());
        comanda.setProveidor(proveidor);
        // ja posem data de recepcio tenint en compte el plaç d'entrega del proveidor
        Integer plasEntrega = proveidor.getPlasEntrega();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(comanda.getDatacomanda());
        calendar.add(Calendar.DAY_OF_YEAR, plasEntrega);
        Date dataRecepcio = calendar.getTime();
        comanda.setDatarecepcio(dataRecepcio);
        comandaRepository.save(comanda);
        // guardem la llista de productes amb la comanda que hem donat d'alta
        for (DetallComanda detallComanda : comandaBotiga.getDetallComandaList()) {
            DetallComanda detallComandaAGuardar = new DetallComanda();
            detallComandaAGuardar.setComanda(comanda);
            detallComandaAGuardar.setProducte(producteRepository.findById(detallComanda.getProducte().getId()));
            detallComandaAGuardar.setQuantitat(detallComanda.getQuantitat());
            detallComandaRepository.save(detallComandaAGuardar);
        }
        return comanda;
    } else {
        return null;
    }
}


public List<Comanda> cercaComandesPerBotiga(Botiga botiga){
    List<Comanda> comandes = new ArrayList<Comanda>();
    comandes = comandaRepository.findByBotigaId(botiga.getId());
    if (comandes.size() > 0) {
        return comandes;
    } else
        return null;
}


public Comanda modificarComanda(ComandaBotiga comandaBotiga){
    if (comandaBotiga.getDetallComandaList().size() > 0) {
        Integer comandaId = comandaBotiga.getDetallComandaList().get(0).getComanda().getId();
        List<DetallComanda> detallComandaList = detallComandaRepository.findByComandaId(comandaId);
        detallComandaRepository.delete(detallComandaList);
        Comanda comanda = comandaRepository.findOne(comandaId);
        comandaRepository.delete(comanda);
        return guardaComanda(comandaBotiga);
    } else {
        return null;
    }
}


public List<Comanda> cercaComandesPerOrganitzacio(Organitzacio org){
    List<Botiga> botigues = new ArrayList<Botiga>();
    botigues = botigaRepository.findByOrganitzacioId(org.getId());
    List<Comanda> comandes = new ArrayList<Comanda>();
    if (botigues.size() > 0) {
        for (Botiga botiga : botigues) {
            List<Comanda> comandaPerBotiga = comandaRepository.findByBotigaId(botiga.getId());
            comandes.addAll(comandaPerBotiga);
        }
    }
    if (comandes.size() > 0) {
        return comandes;
    } else
        return null;
}


public Boolean eliminaComanda(ComandaBotiga comandaBotiga){
    if (comandaBotiga.getDetallComandaList().size() > 0) {
        Integer comandaId = comandaBotiga.getDetallComandaList().get(0).getComanda().getId();
        List<DetallComanda> detallComandaList = detallComandaRepository.findByComandaId(comandaId);
        detallComandaRepository.delete(detallComandaList);
        Comanda comanda = comandaRepository.findOne(comandaId);
        comandaRepository.delete(comanda);
        return true;
    } else {
        return null;
    }
}


}