package restock.controller;
 import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import restock.dto.Cercador;
import restock.entities.Familia;
import restock.entities.Producte;
import restock.entities.SubFamilia;
import restock.entities.Usuari;
import restock.services.ProducteBusiness;
import restock.DTO.Usuari;
@RestController
@RequestMapping("/producte")
public class ProducteController {

@Autowired
 private  ProducteBusiness producteBusiness;

/**
 * Producte controller.
 */
public ProducteController() {
}
@RequestMapping(path = "/getProductes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getProductesDeOrganitzacio(Integer orgId){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Producte> productes = producteBusiness.getProductesPerOrganitzacio(orgId);
        return new ResponseEntity<>(productes, httpHeaders, HttpStatus.OK);
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


@RequestMapping(path = "/getProductesOfProveedor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getProductesDeProveedor(Usuari usuari){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Producte> productes = producteBusiness.getProductesPerProveidor(usuari.getId());
        return new ResponseEntity<>(productes, httpHeaders, HttpStatus.OK);
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


@RequestMapping(path = "/baixa", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> baixa(Producte producte){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Producte producteRetorn = producteBusiness.eliminaProducte(producte);
        if ((producteRetorn == null)) {
            return new ResponseEntity<>("El producte no s'ha pogut eliminar", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Producte donat de baixa correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/families", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getFamilies(){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Familia> familiaList = producteBusiness.getFamilies();
        if ((familiaList.size() == 0)) {
            return new ResponseEntity<>("No existeixen families", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(familiaList, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


@RequestMapping(path = "/alta", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> alta(Producte producte){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Producte producteRetorn = producteBusiness.guardaProducte(producte);
        if ((producteRetorn == null)) {
            return new ResponseEntity<>("El producte ja existeix", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Producte donat d'alta correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/subfamilia/{famId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getSubfamiliesPerFamiliaId(Integer famId){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<SubFamilia> subfamiliaList = producteBusiness.getSubfamiliaPerFamilia(famId);
        if ((subfamiliaList.size() == 0)) {
            return new ResponseEntity<>("Aquesta familia no té subfamilies", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(subfamiliaList, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}


@RequestMapping(path = "/modificacio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> modificacio(Producte producte){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final Producte producteRetorn = producteBusiness.modificaProducte(producte);
        if ((producteRetorn == null)) {
            return new ResponseEntity<>("No s’ha pogut modificar el producte", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Producte modificat correctament", httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(path = "/cercar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> getProductes(Cercador cercadorProductes){
    try {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final List<Producte> listProducte = producteBusiness.cercarProducte(cercadorProductes);
        if ((listProducte.size() == 0)) {
            return new ResponseEntity<>("No s'han trobat resultats", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(listProducte, httpHeaders, HttpStatus.OK);
        }
    } catch (final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}


}