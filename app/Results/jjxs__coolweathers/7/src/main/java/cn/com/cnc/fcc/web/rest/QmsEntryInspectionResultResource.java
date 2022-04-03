package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsEntryInspectionResult;
import cn.com.cnc.fcc.repository.QmsEntryInspectionResultRepository;
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
public class QmsEntryInspectionResultResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsEntryInspectionResultRepository qmsEntryInspectionResultRepository;

public QmsEntryInspectionResultResource(QmsEntryInspectionResultRepository qmsEntryInspectionResultRepository) {
    this.qmsEntryInspectionResultRepository = qmsEntryInspectionResultRepository;
}
@PutMapping("/qms-entry-inspection-results")
@Timed
public ResponseEntity<QmsEntryInspectionResult> updateQmsEntryInspectionResult(QmsEntryInspectionResult qmsEntryInspectionResult){
    log.debug("REST request to update QmsEntryInspectionResult : {}", qmsEntryInspectionResult);
    if (qmsEntryInspectionResult.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsEntryInspectionResult result = qmsEntryInspectionResultRepository.save(qmsEntryInspectionResult);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsEntryInspectionResult.getId().toString())).body(result);
}


@GetMapping("/qms-entry-inspection-results")
@Timed
public ResponseEntity<List<QmsEntryInspectionResult>> getAllQmsEntryInspectionResults(Pageable pageable){
    log.debug("REST request to get a page of QmsEntryInspectionResults");
    Page<QmsEntryInspectionResult> page = qmsEntryInspectionResultRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-entry-inspection-results");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/qms-entry-inspection-results")
@Timed
public ResponseEntity<QmsEntryInspectionResult> createQmsEntryInspectionResult(QmsEntryInspectionResult qmsEntryInspectionResult){
    log.debug("REST request to save QmsEntryInspectionResult : {}", qmsEntryInspectionResult);
    if (qmsEntryInspectionResult.getId() != null) {
        throw new BadRequestAlertException("A new qmsEntryInspectionResult cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsEntryInspectionResult result = qmsEntryInspectionResultRepository.save(qmsEntryInspectionResult);
    return ResponseEntity.created(new URI("/api/qms-entry-inspection-results/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-entry-inspection-results/{id}")
@Timed
public ResponseEntity<QmsEntryInspectionResult> getQmsEntryInspectionResult(Long id){
    log.debug("REST request to get QmsEntryInspectionResult : {}", id);
    Optional<QmsEntryInspectionResult> qmsEntryInspectionResult = qmsEntryInspectionResultRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsEntryInspectionResult);
}


@DeleteMapping("/qms-entry-inspection-results/{id}")
@Timed
public ResponseEntity<Void> deleteQmsEntryInspectionResult(Long id){
    log.debug("REST request to delete QmsEntryInspectionResult : {}", id);
    qmsEntryInspectionResultRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}