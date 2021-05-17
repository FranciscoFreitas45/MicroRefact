import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.PapiToken;
import cn.com.cnc.fcc.repository.PapiTokenRepository;
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
public class PapiTokenResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  PapiTokenRepository papiTokenRepository;


@PutMapping("/papi-tokens")
@Timed
public ResponseEntity<PapiToken> updatePapiToken(PapiToken papiToken){
    log.debug("REST request to update PapiToken : {}", papiToken);
    if (papiToken.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    PapiToken result = papiTokenRepository.save(papiToken);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, papiToken.getId().toString())).body(result);
}


@GetMapping("/papi-tokens/{id}")
@Timed
public ResponseEntity<PapiToken> getPapiToken(Long id){
    log.debug("REST request to get PapiToken : {}", id);
    Optional<PapiToken> papiToken = papiTokenRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(papiToken);
}


@GetMapping("/papi-tokens")
@Timed
public List<PapiToken> getAllPapiTokens(){
    log.debug("REST request to get all PapiTokens");
    return papiTokenRepository.findAll();
}


@DeleteMapping("/papi-tokens/{id}")
@Timed
public ResponseEntity<Void> deletePapiToken(Long id){
    log.debug("REST request to delete PapiToken : {}", id);
    papiTokenRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/papi-tokens")
@Timed
public ResponseEntity<PapiToken> createPapiToken(PapiToken papiToken){
    log.debug("REST request to save PapiToken : {}", papiToken);
    if (papiToken.getId() != null) {
        throw new BadRequestAlertException("A new papiToken cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PapiToken result = papiTokenRepository.save(papiToken);
    return ResponseEntity.created(new URI("/api/papi-tokens/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}