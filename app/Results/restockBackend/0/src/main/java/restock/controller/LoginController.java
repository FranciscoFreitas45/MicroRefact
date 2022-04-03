package restock.controller;
 import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import restock.dto.Login;
import restock.DTO.Botiga;
import restock.entities.Organitzacio;
import restock.entities.Usuari;
import restock.Interface.BotigaBusiness;
import restock.services.OrganitzacioBusiness;
import restock.services.UsuariBusiness;
@RestController
@RequestMapping("/loginUser")
public class LoginController {

@Autowired
 private  UsuariBusiness usuariBusiness;

@Autowired
 private  BotigaBusiness botigaBusiness;

@Autowired
 private  OrganitzacioBusiness organitzacioBusiness;


@RequestMapping(path = "/getBotiga", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getBotiga(Usuari usuari){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Botiga botiga = botigaBusiness.getBotigaOfResponsable(usuari);
        return new ResponseEntity<>(botiga, httpHeaders, HttpStatus.OK);
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> login(Login login){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Usuari usuari = usuariBusiness.login(login);
        if (usuari == null) {
            return new ResponseEntity<>("Aquest usuari no esta registrat", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(usuari, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/getOrganitzacio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getOrganitzacio(Usuari usuari){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Organitzacio organitzacio = organitzacioBusiness.getOrganitzacioOfUsuari(usuari);
        return new ResponseEntity<>(organitzacio, httpHeaders, HttpStatus.OK);
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


}