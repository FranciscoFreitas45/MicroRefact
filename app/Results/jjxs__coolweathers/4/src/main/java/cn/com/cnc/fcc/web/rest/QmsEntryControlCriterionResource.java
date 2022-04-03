package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsEntryControlCriterion;
import cn.com.cnc.fcc.repository.QmsEntryControlCriterionRepository;
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
public class QmsEntryControlCriterionResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsEntryControlCriterionRepository qmsEntryControlCriterionRepository;

public QmsEntryControlCriterionResource(QmsEntryControlCriterionRepository qmsEntryControlCriterionRepository) {
    this.qmsEntryControlCriterionRepository = qmsEntryControlCriterionRepository;
}
@PostMapping("/qms-entry-control-criteria")
@Timed
public ResponseEntity<QmsEntryControlCriterion> createQmsEntryControlCriterion(QmsEntryControlCriterion qmsEntryControlCriterion){
    log.debug("REST request to save QmsEntryControlCriterion : {}", qmsEntryControlCriterion);
    if (qmsEntryControlCriterion.getId() != null) {
        throw new BadRequestAlertException("A new qmsEntryControlCriterion cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsEntryControlCriterion result = qmsEntryControlCriterionRepository.save(qmsEntryControlCriterion);
    return ResponseEntity.created(new URI("/api/qms-entry-control-criteria/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-entry-control-criteria/{id}")
@Timed
public ResponseEntity<QmsEntryControlCriterion> getQmsEntryControlCriterion(Long id){
    log.debug("REST request to get QmsEntryControlCriterion : {}", id);
    Optional<QmsEntryControlCriterion> qmsEntryControlCriterion = qmsEntryControlCriterionRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsEntryControlCriterion);
}


@PutMapping("/qms-entry-control-criteria")
@Timed
public ResponseEntity<QmsEntryControlCriterion> updateQmsEntryControlCriterion(QmsEntryControlCriterion qmsEntryControlCriterion){
    log.debug("REST request to update QmsEntryControlCriterion : {}", qmsEntryControlCriterion);
    if (qmsEntryControlCriterion.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsEntryControlCriterion result = qmsEntryControlCriterionRepository.save(qmsEntryControlCriterion);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsEntryControlCriterion.getId().toString())).body(result);
}


@GetMapping("/qms-entry-control-criteria")
@Timed
public ResponseEntity<List<QmsEntryControlCriterion>> getAllQmsEntryControlCriteria(Pageable pageable){
    log.debug("REST request to get a page of QmsEntryControlCriteria");
    Page<QmsEntryControlCriterion> page = qmsEntryControlCriterionRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-entry-control-criteria");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-entry-control-criteria/{id}")
@Timed
public ResponseEntity<Void> deleteQmsEntryControlCriterion(Long id){
    log.debug("REST request to delete QmsEntryControlCriterion : {}", id);
    qmsEntryControlCriterionRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}