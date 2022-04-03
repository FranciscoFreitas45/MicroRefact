package restock.services;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.dto.Cercador;
import restock.entities.Botiga;
import restock.entities.Usuari;
import restock.repository.BotigaRepository;
import restock.repository.UsuariRepository;
@Component
public class BotigaBusiness {

@Autowired
 private  UsuariRepository usuariRepository;

@Autowired
 private  BotigaRepository botigaRepository;


public Botiga modificaResponsableBotiga(Botiga botiga){
    Botiga botigaExistent = botigaRepository.findById(botiga.getId());
    Usuari userExistent = null;
    if (botiga.getUsuari().getId() != null) {
        userExistent = usuariRepository.findById(botiga.getUsuari().getId());
    } else {
        userExistent = usuariRepository.findByUserAndOrganitzacioId(botiga.getUsuari().getUser(), botiga.getOrganitzacio().getId());
    }
    // La botiga existeix i s'asigna usuari nou.
    if (userExistent == null && botigaExistent != null) {
        botiga.getUsuari().setOrganitzacio(botiga.getOrganitzacio());
        usuariRepository.save(botiga.getUsuari());
        Usuari userNou = usuariRepository.findByUserAndOrganitzacioId(botiga.getUsuari().getUser(), botiga.getOrganitzacio().getId());
        botigaExistent.setUsuari(userNou);
        botigaRepository.save(botigaExistent);
        return botiga;
    } else // La botiga existex i s'asigna usuari existent
    if (userExistent != null && botigaExistent != null) {
        botigaExistent.setUsuari(userExistent);
        botigaRepository.save(botigaExistent);
        return botiga;
    } else
        return null;
}


public List<Botiga> cercarBotiga(Cercador cercadorBotiga){
    List<Botiga> llistaBotigues = null;
    String camp = cercadorBotiga.getCamp();
    Integer orgId = cercadorBotiga.getOrgId();
    llistaBotigues = botigaRepository.cercaBotiga(camp, orgId);
    if (llistaBotigues != null) {
        return llistaBotigues;
    } else {
        return null;
    }
}


public Botiga getBotigaOfResponsable(Usuari usuari){
    Botiga botiga = botigaRepository.findByUsuariId(usuari.getId());
    return botiga;
}


public Botiga guardaBotiga(Botiga botiga){
    Botiga botigaExistent = botigaRepository.findByNomAndOrganitzacioId(botiga.getNom(), botiga.getOrganitzacio().getId());
    Usuari userExistent = null;
    if (botiga.getUsuari().getId() != null) {
        userExistent = usuariRepository.findById(botiga.getUsuari().getId());
    } else {
        userExistent = usuariRepository.findByUserAndOrganitzacioId(botiga.getUsuari().getUser(), botiga.getOrganitzacio().getId());
    }
    // No existeix ni usuari responsable ni botiga i es donen d'alta els dos.
    if (userExistent == null && botigaExistent == null) {
        botiga.getUsuari().setOrganitzacio(botiga.getOrganitzacio());
        usuariRepository.save(botiga.getUsuari());
        botigaRepository.save(botiga);
        Botiga botigaNova = botigaRepository.findById(botiga.getId());
        Usuari userNou = usuariRepository.findByUserAndOrganitzacioId(botiga.getUsuari().getUser(), botiga.getOrganitzacio().getId());
        botigaNova.setUsuari(userNou);
        botigaRepository.save(botigaNova);
        return botiga;
    } else // La botiga no existex pero ja existeix responsable. Es dona d'alta botiga
    if (userExistent != null && botigaExistent == null) {
        botiga.setUsuari(userExistent);
        botigaRepository.save(botiga);
        return botiga;
    } else // La botiga ja existeix.
    if (botigaExistent != null) {
        return null;
    } else
        return null;
}


public Botiga eliminaBotiga(Botiga botiga){
    Botiga botigaExistent = botigaRepository.findById(botiga.getId());
    // Si la botiga existeix es podra donar de baixa.
    if (botigaExistent != null) {
        botigaRepository.delete(botigaExistent);
        return botiga;
    } else {
        return null;
    }
}


public Botiga modificaBotiga(Botiga botiga){
    Botiga botigaExistent = botigaRepository.findById(botiga.getId());
    // Si no existeix botiga no es pot modificar.
    if (botigaExistent != null) {
        botigaExistent.setNom(botiga.getNom());
        botigaExistent.setCarrer(botiga.getCarrer());
        botigaExistent.setCodiPostal(botiga.getCodiPostal());
        botigaExistent.setNumero(botiga.getNumero());
        botigaExistent.setOrganitzacio(botiga.getOrganitzacio());
        botigaExistent.setPais(botiga.getPais());
        botigaExistent.setPoblacio(botiga.getPoblacio());
        botigaRepository.save(botigaExistent);
        return botiga;
    } else {
        return null;
    }
}


public List<Botiga> getBotiguesPerOrganitzacio(Integer orgId){
    List<Botiga> botigues = botigaRepository.findByOrganitzacioId(orgId);
    return botigues;
}


}