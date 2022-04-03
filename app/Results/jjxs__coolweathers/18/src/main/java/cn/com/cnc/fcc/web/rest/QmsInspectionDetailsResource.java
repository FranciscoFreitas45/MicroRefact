package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsInspectionDetails;
import cn.com.cnc.fcc.repository.QmsInspectionDetailsRepository;
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
public class QmsInspectionDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsInspectionDetailsRepository qmsInspectionDetailsRepository;

public QmsInspectionDetailsResource(QmsInspectionDetailsRepository qmsInspectionDetailsRepository) {
    this.qmsInspectionDetailsRepository = qmsInspectionDetailsRepository;
}
@PutMapping("/qms-inspection-details")
@Timed
public ResponseEntity<QmsInspectionDetails> updateQmsInspectionDetails(QmsInspectionDetails qmsInspectionDetails){
    log.debug("REST request to update QmsInspectionDetails : {}", qmsInspectionDetails);
    if (qmsInspectionDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsInspectionDetails result = qmsInspectionDetailsRepository.save(qmsInspectionDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsInspectionDetails.getId().toString())).body(result);
}


@GetMapping("/qms-inspection-details")
@Timed
public ResponseEntity<List<QmsInspectionDetails>> getAllQmsInspectionDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsInspectionDetails");
    Page<QmsInspectionDetails> page = qmsInspectionDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-inspection-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-inspection-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsInspectionDetails(Long id){
    log.debug("REST request to delete QmsInspectionDetails : {}", id);
    qmsInspectionDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-inspection-details/{id}")
@Timed
public ResponseEntity<QmsInspectionDetails> getQmsInspectionDetails(Long id){
    log.debug("REST request to get QmsInspectionDetails : {}", id);
    Optional<QmsInspectionDetails> qmsInspectionDetails = qmsInspectionDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsInspectionDetails);
}


@PostMapping("/qms-inspection-details")
@Timed
public ResponseEntity<QmsInspectionDetails> createQmsInspectionDetails(QmsInspectionDetails qmsInspectionDetails){
    log.debug("REST request to save QmsInspectionDetails : {}", qmsInspectionDetails);
    if (qmsInspectionDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsInspectionDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsInspectionDetails result = qmsInspectionDetailsRepository.save(qmsInspectionDetails);
    return ResponseEntity.created(new URI("/api/qms-inspection-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}