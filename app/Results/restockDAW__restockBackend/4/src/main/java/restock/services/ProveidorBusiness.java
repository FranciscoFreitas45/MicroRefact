package restock.services;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.dto.Cercador;
import restock.entities.Proveidor;
import restock.repository.ProveidorRepository;
@Component
public class ProveidorBusiness {

@Autowired
 private  ProveidorRepository proveidorRepository;


public Proveidor eliminaProveidor(Proveidor proveidor){
    Proveidor proveidorExistent = proveidorRepository.findById(proveidor.getId());
    // Si el proveidor existeix es podra donar de baixa.
    if (proveidorExistent != null) {
        proveidorRepository.delete(proveidorExistent);
        return proveidor;
    } else {
        return null;
    }
}


public Proveidor guardaProveidor(Proveidor proveidor){
    Proveidor proveidorExistent = proveidorRepository.findByNomAndOrganitzacioId(proveidor.getNom(), proveidor.getOrganitzacio().getId());
    // No existeix el proveidor es dona d'alta.
    if (proveidorExistent == null) {
        proveidorRepository.save(proveidor);
        return proveidor;
    // El proveidor ja existeix.
    } else
        return null;
}


public List<Proveidor> getProveidorsPerOrganitzacio(Integer orgId){
    List<Proveidor> proveidors = proveidorRepository.findByOrganitzacioId(orgId);
    return proveidors;
}


public Proveidor modificaProveidor(Proveidor proveidor){
    Proveidor proveidorExistent = proveidorRepository.findById(proveidor.getId());
    // Si no existeix proveidor no es pot modificar.
    if (proveidorExistent != null) {
        proveidorExistent.setNif(proveidor.getNif());
        proveidorExistent.setNom(proveidor.getNom());
        proveidorExistent.setCarrer(proveidor.getCarrer());
        proveidorExistent.setCodiPostal(proveidor.getCodiPostal());
        proveidorExistent.setNumero(proveidor.getNumero());
        proveidorExistent.setOrganitzacio(proveidor.getOrganitzacio());
        proveidorExistent.setPais(proveidor.getPais());
        proveidorExistent.setPlasEntrega(proveidor.getPlasEntrega());
        proveidorExistent.setPoblacio(proveidor.getPoblacio());
        // proveidorExistent.setOrganitzacio(proveidor.getOrganitzacio());
        proveidorRepository.save(proveidorExistent);
        return proveidor;
    } else {
        return null;
    }
}


public List<Proveidor> cercarProveidor(Cercador cercadorProveidor){
    List<Proveidor> llistaProveidors = null;
    String camp = cercadorProveidor.getCamp();
    Integer orgId = cercadorProveidor.getOrgId();
    llistaProveidors = proveidorRepository.cercaProveidor(camp, orgId);
    if (llistaProveidors != null) {
        return llistaProveidors;
    } else {
        return null;
    }
}


}