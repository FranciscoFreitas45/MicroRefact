package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsQualityControlDetails;
import cn.com.cnc.fcc.repository.QmsQualityControlDetailsRepository;
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
public class QmsQualityControlDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsQualityControlDetailsRepository qmsQualityControlDetailsRepository;

public QmsQualityControlDetailsResource(QmsQualityControlDetailsRepository qmsQualityControlDetailsRepository) {
    this.qmsQualityControlDetailsRepository = qmsQualityControlDetailsRepository;
}
@PutMapping("/qms-quality-control-details")
@Timed
public ResponseEntity<QmsQualityControlDetails> updateQmsQualityControlDetails(QmsQualityControlDetails qmsQualityControlDetails){
    log.debug("REST request to update QmsQualityControlDetails : {}", qmsQualityControlDetails);
    if (qmsQualityControlDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsQualityControlDetails result = qmsQualityControlDetailsRepository.save(qmsQualityControlDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsQualityControlDetails.getId().toString())).body(result);
}


@GetMapping("/qms-quality-control-details/{id}")
@Timed
public ResponseEntity<QmsQualityControlDetails> getQmsQualityControlDetails(Long id){
    log.debug("REST request to get QmsQualityControlDetails : {}", id);
    Optional<QmsQualityControlDetails> qmsQualityControlDetails = qmsQualityControlDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsQualityControlDetails);
}


@GetMapping("/qms-quality-control-details")
@Timed
public ResponseEntity<List<QmsQualityControlDetails>> getAllQmsQualityControlDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsQualityControlDetails");
    Page<QmsQualityControlDetails> page = qmsQualityControlDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-quality-control-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-quality-control-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsQualityControlDetails(Long id){
    log.debug("REST request to delete QmsQualityControlDetails : {}", id);
    qmsQualityControlDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-quality-control-details")
@Timed
public ResponseEntity<QmsQualityControlDetails> createQmsQualityControlDetails(QmsQualityControlDetails qmsQualityControlDetails){
    log.debug("REST request to save QmsQualityControlDetails : {}", qmsQualityControlDetails);
    if (qmsQualityControlDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsQualityControlDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsQualityControlDetails result = qmsQualityControlDetailsRepository.save(qmsQualityControlDetails);
    return ResponseEntity.created(new URI("/api/qms-quality-control-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}