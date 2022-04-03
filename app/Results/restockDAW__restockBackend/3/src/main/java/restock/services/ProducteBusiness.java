package restock.services;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.dto.Cercador;
import restock.entities.Familia;
import restock.entities.Producte;
import restock.entities.SubFamilia;
import restock.repository.FamiliaRepository;
import restock.repository.ProducteRepository;
import restock.repository.SubfamiliaRepository;
import restock.DTO.Cercador;
@Component
public class ProducteBusiness {

@Autowired
 private  ProducteRepository producteRepository;

@Autowired
 private  FamiliaRepository familiaRepository;

@Autowired
 private  SubfamiliaRepository subfamiliaRepository;


public List<Producte> getProductesPerProveidor(Integer provId){
    List<Producte> productes = producteRepository.findByProveidorId(provId);
    return productes;
}


public Producte modificaProducte(Producte producte){
    Producte producteExistent = producteRepository.findById(producte.getId());
    // Si no existeix producte no es pot modificar.
    if (producteExistent != null) {
        producteExistent.setMarca(producte.getMarca());
        producteExistent.setModel(producte.getModel());
        producteExistent.setDescripcio(producte.getDescripcio());
        producteExistent.setPreu(producte.getPreu());
        producteExistent.setPreuVenda(producte.getPreuVenda());
        producteExistent.setSubfamilia(producte.getSubfamilia());
        producteExistent.setProveidor(producte.getProveidor());
        producteRepository.save(producteExistent);
        return producte;
    } else {
        return null;
    }
}


public List<Familia> getFamilies(){
    List<Familia> families = familiaRepository.findAll();
    return families;
}


public Producte eliminaProducte(Producte producte){
    Producte producteExistent = producteRepository.findById(producte.getId());
    // Si el producte existeix es podra donar de baixa.
    if (producteExistent != null) {
        producteRepository.delete(producteExistent);
        return producte;
    } else {
        return null;
    }
}


public Producte guardaProducte(Producte producte){
    Producte producteExistent = producteRepository.findByMarcaAndModelAndSubfamiliaAndProveidor(producte.getMarca(), producte.getModel(), producte.getSubfamilia().getId(), producte.getProveidor().getId());
    // No existeix el producte es dona d'alta.
    if (producteExistent == null) {
        producteRepository.save(producte);
        return producte;
    // El producte ja existeix.
    } else
        return null;
}


public List<Producte> cercarProducte(Cercador cercadorProducte){
    List<Producte> llistaProductes = null;
    String camp = cercadorProducte.getCamp();
    Integer orgId = cercadorProducte.getOrgId();
    llistaProductes = producteRepository.cercaProducte(camp, orgId);
    if (llistaProductes != null) {
        return llistaProductes;
    } else {
        return null;
    }
}


public List<SubFamilia> getSubfamiliaPerFamilia(Integer famId){
    List<SubFamilia> subfamilies = subfamiliaRepository.findByFamiliaId(famId);
    return subfamilies;
}


public List<Producte> getProductesPerOrganitzacio(Integer orgId){
    List<Producte> productes = producteRepository.findByOrganitzacioId(orgId);
    return productes;
}


}