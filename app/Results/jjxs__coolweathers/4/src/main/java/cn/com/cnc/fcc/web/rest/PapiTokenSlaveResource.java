package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.PapiTokenSlave;
import cn.com.cnc.fcc.repository.PapiTokenSlaveRepository;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class PapiTokenSlaveResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  PapiTokenSlaveRepository papiTokenSlaveRepository;

public PapiTokenSlaveResource(PapiTokenSlaveRepository papiTokenSlaveRepository) {
    this.papiTokenSlaveRepository = papiTokenSlaveRepository;
}
@PutMapping("/papi-token-slaves")
@Timed
public ResponseEntity<PapiTokenSlave> updatePapiTokenSlave(PapiTokenSlave papiTokenSlave){
    log.debug("REST request to update PapiTokenSlave : {}", papiTokenSlave);
    if (papiTokenSlave.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    PapiTokenSlave result = papiTokenSlaveRepository.save(papiTokenSlave);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, papiTokenSlave.getId().toString())).body(result);
}


@PostMapping("/papi-token-slaves")
@Timed
public ResponseEntity<PapiTokenSlave> createPapiTokenSlave(PapiTokenSlave papiTokenSlave){
    log.debug("REST request to save PapiTokenSlave : {}", papiTokenSlave);
    if (papiTokenSlave.getId() != null) {
        throw new BadRequestAlertException("A new papiTokenSlave cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PapiTokenSlave result = papiTokenSlaveRepository.save(papiTokenSlave);
    return ResponseEntity.created(new URI("/api/papi-token-slaves/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/papi-token-slaves/{id}")
@Timed
public ResponseEntity<PapiTokenSlave> getPapiTokenSlave(Long id){
    log.debug("REST request to get PapiTokenSlave : {}", id);
    Optional<PapiTokenSlave> papiTokenSlave = papiTokenSlaveRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(papiTokenSlave);
}


@GetMapping("/papi-token-slaves")
@Timed
public List<PapiTokenSlave> getAllPapiTokenSlaves(){
    log.debug("REST request to get all PapiTokenSlaves");
    return papiTokenSlaveRepository.findAll();
}


@DeleteMapping("/papi-token-slaves/{id}")
@Timed
public ResponseEntity<Void> deletePapiTokenSlave(Long id){
    log.debug("REST request to delete PapiTokenSlave : {}", id);
    papiTokenSlaveRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}