package restock.controller;
 import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import restock.dto.Cercador;
import restock.entities.Botiga;
import restock.services.BotigaBusiness;
@RestController
@RequestMapping("/botiga")
public class BotigaController {

@Autowired
 private  BotigaBusiness botigaBusiness;

/**
 * Botiga controller.
 */
public BotigaController() {
}
@RequestMapping(path = "/baixa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> baixa(Botiga botiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Botiga botigaRetorn = botigaBusiness.eliminaBotiga(botiga);
        if ((botigaRetorn == null)) {
            return new ResponseEntity<>("La botiga no s'ha pogut eliminar", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Botiga donada de baixa correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/alta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> alta(Botiga botiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Botiga botigaRetorn = botigaBusiness.guardaBotiga(botiga);
        if ((botigaRetorn == null)) {
            return new ResponseEntity<>("La botiga ja existeix", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Botiga donada d'alta correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/modificacioResponsable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> modificacioResponsable(Botiga botiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Botiga botigaRetorn = botigaBusiness.modificaResponsableBotiga(botiga);
        if ((botigaRetorn == null)) {
            return new ResponseEntity<>("No s’ha pogut modificar el responsable la botiga", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Responsable modificat correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/modificacio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> modificacio(Botiga botiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Botiga botigaRetorn = botigaBusiness.modificaBotiga(botiga);
        if ((botigaRetorn == null)) {
            return new ResponseEntity<>("No s’ha pogut modificar la botiga", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Botiga modificada correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/getAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getTotesLesBotigues(Integer orgId){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Botiga> botigues = botigaBusiness.getBotiguesPerOrganitzacio(orgId);
        return new ResponseEntity<>(botigues, httpHeaders, HttpStatus.OK);
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


@RequestMapping(path = "/cercar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getBotigues(Cercador cercadorBotiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Botiga> listBotigues = botigaBusiness.cercarBotiga(cercadorBotiga);
        if ((listBotigues.size() == 0)) {
            return new ResponseEntity<>("No s'han trobat resultats", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listBotigues, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


}