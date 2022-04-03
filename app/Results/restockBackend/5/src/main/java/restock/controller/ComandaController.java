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
import restock.dto.ComandaBotiga;
import restock.entities.Botiga;
import restock.entities.Comanda;
import restock.entities.DetallComanda;
import restock.entities.Organitzacio;
import restock.services.ComandaBusiness;
import restock.services.DetallComandaBusiness;
@RestController
@RequestMapping("/comanda")
public class ComandaController {

@Autowired
 private  ComandaBusiness comandaBusiness;

@Autowired
 private  DetallComandaBusiness detallComandaBusiness;


@RequestMapping(path = "/baixa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> baixa(ComandaBotiga comandaBotiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Boolean deleted = comandaBusiness.eliminaComanda(comandaBotiga);
        if (deleted == false) {
            return new ResponseEntity<>("No s'ha pogut borrar la comanda", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Comanda borrada correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/alta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> alta(ComandaBotiga comandaBotiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Comanda comandaRetorn = comandaBusiness.guardaComanda(comandaBotiga);
        if ((comandaRetorn == null)) {
            return new ResponseEntity<>("No s'ha pogut donar d'alta la comanda", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Comanda enviada correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/cercarPendentsPerOrganitzacio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getComandesPendentsPerOrganitzacio(Organitzacio org){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Comanda> listComandes = comandaBusiness.cercaComandesPendentsPerOrganitzacio(org);
        if (listComandes == null) {
            return new ResponseEntity<>("No s'han trobat comandes pendents", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listComandes, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/veureDetallComanda", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> veureDetallComanda(Comanda comanda){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<DetallComanda> listDetallComanda = detallComandaBusiness.cercaDetallComanda(comanda);
        if ((listDetallComanda.size() == 0)) {
            return new ResponseEntity<>("No s'ha trobat el detall de la comanda", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listDetallComanda, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/modificacio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> modificacio(ComandaBotiga comandaBotiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Comanda comandaRetorn = comandaBusiness.modificarComanda(comandaBotiga);
        if ((comandaRetorn == null)) {
            return new ResponseEntity<>("No s'ha pogut donar d'alta la comanda", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Comanda enviada correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/cercarPerBotiga", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getComandesPerBotiga(Botiga botiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Comanda> listComandes = comandaBusiness.cercaComandesPerBotiga(botiga);
        if ((listComandes.size() == 0)) {
            return new ResponseEntity<>("No s'han trobat comandes", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listComandes, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/cercarPerOrganitzacio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getComandesPerOrganitzacio(Organitzacio org){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Comanda> listComandes = comandaBusiness.cercaComandesPerOrganitzacio(org);
        if ((listComandes.size() == 0)) {
            return new ResponseEntity<>("No s'han trobat comandes", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listComandes, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/cercarPendentsPerBotiga", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getComandesPendentsPerBotiga(Botiga botiga){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Comanda> listComandes = comandaBusiness.cercaComandesPendentsPerBotiga(botiga);
        if (listComandes == null) {
            return new ResponseEntity<>("No s'han trobat comandes pendents de rebre", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listComandes, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


}