package restock.services;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import restock.dto.Login;
import restock.entities.Usuari;
import restock.repository.UsuariRepository;
@Component
public class UsuariBusiness {

@Autowired
 private  UsuariRepository usuariRepository;


public List<Usuari> getUsuarisPerOrganitzacio(Integer orgId){
    List<Usuari> usuaris = usuariRepository.findByOrganitzacioId(orgId);
    return usuaris;
}


public List<Usuari> getResponsables(){
    List<Usuari> Usuaris = usuariRepository.findResponsables();
    return Usuaris;
}


public Usuari getUserById(Integer usuariId){
    Usuari userExistent = usuariRepository.getOne(usuariId);
    if (userExistent == null) {
        return null;
    } else {
        return userExistent;
    }
}


public Usuari modificaUsuari(Usuari usuari){
    Usuari userExistent = usuariRepository.findByUser(usuari.getUser());
    // Si no existeix usuari no es pot modificar.
    if (userExistent != null) {
        userExistent.setNom(usuari.getNom());
        userExistent.setCognom1(usuari.getCognom1());
        userExistent.setCognom2(usuari.getCognom2());
        userExistent.setCorreu(usuari.getCorreu());
        userExistent.setDataNaixement(usuari.getDataNaixement());
        userExistent.setNif(usuari.getNif());
        usuariRepository.save(userExistent);
        return usuari;
    } else {
        return null;
    }
}


public List<Usuari> getTotsElsUsuaris(){
    List<Usuari> Usuaris = usuariRepository.findAll();
    return Usuaris;
}


public Usuari guardaUsuari(Usuari usuari){
    Usuari userExistent = usuariRepository.findByUser(usuari.getUser());
    // No existeix usuari es dona d'alta.
    if (userExistent == null) {
        usuariRepository.save(usuari);
        return usuari;
    } else {
        return null;
    }
}


public List<Usuari> getResponsablesPerOrganitzacio(Integer orgId){
    List<Usuari> usuaris = usuariRepository.findResponsablesOfOrganitzacio(orgId);
    return usuaris;
}


public Usuari login(Login login){
    Usuari userExistent = usuariRepository.findByUserAndPassword(login.getUser(), login.getPassword());
    if (userExistent == null) {
        return null;
    } else {
        return userExistent;
    }
}


public List<Usuari> cercarUsuari(String camp){
    List<Usuari> llistaUsuaris = null;
    llistaUsuaris = usuariRepository.cercaUsuari(camp);
    if (llistaUsuaris != null) {
        return llistaUsuaris;
    } else {
        return null;
    }
}


public Usuari eliminaUsuari(Usuari usuari){
    Usuari userExistent = usuariRepository.findByUserAndRol(usuari.getUser(), usuari.getRol());
    // Si usuari no es administrador es podra donar de baixa.
    if (userExistent != null && usuari.getRol().getId() == 2) {
        usuariRepository.delete(userExistent);
        return usuari;
    } else {
        return null;
    }
}


}