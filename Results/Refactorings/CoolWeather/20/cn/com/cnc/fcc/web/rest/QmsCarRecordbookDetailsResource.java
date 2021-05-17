import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsCarRecordbookDetails;
import cn.com.cnc.fcc.repository.QmsCarRecordbookDetailsRepository;
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
public class QmsCarRecordbookDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsCarRecordbookDetailsRepository qmsCarRecordbookDetailsRepository;


@PostMapping("/qms-car-recordbook-details")
@Timed
public ResponseEntity<QmsCarRecordbookDetails> createQmsCarRecordbookDetails(QmsCarRecordbookDetails qmsCarRecordbookDetails){
    log.debug("REST request to save QmsCarRecordbookDetails : {}", qmsCarRecordbookDetails);
    if (qmsCarRecordbookDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsCarRecordbookDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsCarRecordbookDetails result = qmsCarRecordbookDetailsRepository.save(qmsCarRecordbookDetails);
    return ResponseEntity.created(new URI("/api/qms-car-recordbook-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-car-recordbook-details/{id}")
@Timed
public ResponseEntity<QmsCarRecordbookDetails> getQmsCarRecordbookDetails(Long id){
    log.debug("REST request to get QmsCarRecordbookDetails : {}", id);
    Optional<QmsCarRecordbookDetails> qmsCarRecordbookDetails = qmsCarRecordbookDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsCarRecordbookDetails);
}


@PutMapping("/qms-car-recordbook-details")
@Timed
public ResponseEntity<QmsCarRecordbookDetails> updateQmsCarRecordbookDetails(QmsCarRecordbookDetails qmsCarRecordbookDetails){
    log.debug("REST request to update QmsCarRecordbookDetails : {}", qmsCarRecordbookDetails);
    if (qmsCarRecordbookDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsCarRecordbookDetails result = qmsCarRecordbookDetailsRepository.save(qmsCarRecordbookDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsCarRecordbookDetails.getId().toString())).body(result);
}


@DeleteMapping("/qms-car-recordbook-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsCarRecordbookDetails(Long id){
    log.debug("REST request to delete QmsCarRecordbookDetails : {}", id);
    qmsCarRecordbookDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-car-recordbook-details")
@Timed
public ResponseEntity<List<QmsCarRecordbookDetails>> getAllQmsCarRecordbookDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsCarRecordbookDetails");
    Page<QmsCarRecordbookDetails> page = qmsCarRecordbookDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-car-recordbook-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}