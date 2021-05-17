import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsEntryControlDetails;
import cn.com.cnc.fcc.repository.QmsEntryControlDetailsRepository;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class QmsEntryControlDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsEntryControlDetailsRepository qmsEntryControlDetailsRepository;


@DeleteMapping("/qms-entry-control-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsEntryControlDetails(Long id){
    log.debug("REST request to delete QmsEntryControlDetails : {}", id);
    qmsEntryControlDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-entry-control-details")
@Timed
public ResponseEntity<QmsEntryControlDetails> updateQmsEntryControlDetails(QmsEntryControlDetails qmsEntryControlDetails){
    log.debug("REST request to update QmsEntryControlDetails : {}", qmsEntryControlDetails);
    if (qmsEntryControlDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsEntryControlDetails result = qmsEntryControlDetailsRepository.save(qmsEntryControlDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsEntryControlDetails.getId().toString())).body(result);
}


@GetMapping("/qms-entry-control-details")
@Timed
public ResponseEntity<List<QmsEntryControlDetails>> getAllQmsEntryControlDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsEntryControlDetails");
    Page<QmsEntryControlDetails> page = qmsEntryControlDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-entry-control-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/qms-entry-control-details")
@Timed
public ResponseEntity<QmsEntryControlDetails> createQmsEntryControlDetails(QmsEntryControlDetails qmsEntryControlDetails){
    log.debug("REST request to save QmsEntryControlDetails : {}", qmsEntryControlDetails);
    if (qmsEntryControlDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsEntryControlDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsEntryControlDetails result = qmsEntryControlDetailsRepository.save(qmsEntryControlDetails);
    return ResponseEntity.created(new URI("/api/qms-entry-control-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-entry-control-details/{id}")
@Timed
public ResponseEntity<QmsEntryControlDetails> getQmsEntryControlDetails(Long id){
    log.debug("REST request to get QmsEntryControlDetails : {}", id);
    Optional<QmsEntryControlDetails> qmsEntryControlDetails = qmsEntryControlDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsEntryControlDetails);
}


}