import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMaterielDetails;
import cn.com.cnc.fcc.repository.QmsMaterielDetailsRepository;
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
public class QmsMaterielDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsMaterielDetailsRepository qmsMaterielDetailsRepository;


@PutMapping("/qms-materiel-details")
@Timed
public ResponseEntity<QmsMaterielDetails> updateQmsMaterielDetails(QmsMaterielDetails qmsMaterielDetails){
    log.debug("REST request to update QmsMaterielDetails : {}", qmsMaterielDetails);
    if (qmsMaterielDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsMaterielDetails result = qmsMaterielDetailsRepository.save(qmsMaterielDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsMaterielDetails.getId().toString())).body(result);
}


@GetMapping("/qms-materiel-details")
@Timed
public ResponseEntity<List<QmsMaterielDetails>> getAllQmsMaterielDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielDetails");
    Page<QmsMaterielDetails> page = qmsMaterielDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-materiel-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsMaterielDetails(Long id){
    log.debug("REST request to delete QmsMaterielDetails : {}", id);
    qmsMaterielDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-materiel-details")
@Timed
public ResponseEntity<QmsMaterielDetails> createQmsMaterielDetails(QmsMaterielDetails qmsMaterielDetails){
    log.debug("REST request to save QmsMaterielDetails : {}", qmsMaterielDetails);
    if (qmsMaterielDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsMaterielDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsMaterielDetails result = qmsMaterielDetailsRepository.save(qmsMaterielDetails);
    return ResponseEntity.created(new URI("/api/qms-materiel-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-materiel-details/{id}")
@Timed
public ResponseEntity<QmsMaterielDetails> getQmsMaterielDetails(Long id){
    log.debug("REST request to get QmsMaterielDetails : {}", id);
    Optional<QmsMaterielDetails> qmsMaterielDetails = qmsMaterielDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMaterielDetails);
}


}