package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.HstServerInfoDetails;
import cn.com.cnc.fcc.repository.HstServerInfoDetailsRepository;
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
public class HstServerInfoDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  HstServerInfoDetailsRepository hstServerInfoDetailsRepository;

public HstServerInfoDetailsResource(HstServerInfoDetailsRepository hstServerInfoDetailsRepository) {
    this.hstServerInfoDetailsRepository = hstServerInfoDetailsRepository;
}
@PutMapping("/hst-server-info-details")
@Timed
public ResponseEntity<HstServerInfoDetails> updateHstServerInfoDetails(HstServerInfoDetails hstServerInfoDetails){
    log.debug("REST request to update HstServerInfoDetails : {}", hstServerInfoDetails);
    if (hstServerInfoDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    HstServerInfoDetails result = hstServerInfoDetailsRepository.save(hstServerInfoDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hstServerInfoDetails.getId().toString())).body(result);
}


@GetMapping("/hst-server-info-details")
@Timed
public List<HstServerInfoDetails> getAllHstServerInfoDetails(){
    log.debug("REST request to get all HstServerInfoDetails");
    return hstServerInfoDetailsRepository.findAll();
}


@DeleteMapping("/hst-server-info-details/{id}")
@Timed
public ResponseEntity<Void> deleteHstServerInfoDetails(Long id){
    log.debug("REST request to delete HstServerInfoDetails : {}", id);
    hstServerInfoDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/hst-server-info-details/{id}")
@Timed
public ResponseEntity<HstServerInfoDetails> getHstServerInfoDetails(Long id){
    log.debug("REST request to get HstServerInfoDetails : {}", id);
    Optional<HstServerInfoDetails> hstServerInfoDetails = hstServerInfoDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(hstServerInfoDetails);
}


@PostMapping("/hst-server-info-details")
@Timed
public ResponseEntity<HstServerInfoDetails> createHstServerInfoDetails(HstServerInfoDetails hstServerInfoDetails){
    log.debug("REST request to save HstServerInfoDetails : {}", hstServerInfoDetails);
    if (hstServerInfoDetails.getId() != null) {
        throw new BadRequestAlertException("A new hstServerInfoDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    HstServerInfoDetails result = hstServerInfoDetailsRepository.save(hstServerInfoDetails);
    return ResponseEntity.created(new URI("/api/hst-server-info-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}