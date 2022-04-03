package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsEntryInspection;
import cn.com.cnc.fcc.repository.QmsEntryInspectionRepository;
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
public class QmsEntryInspectionResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsEntryInspectionRepository qmsEntryInspectionRepository;

public QmsEntryInspectionResource(QmsEntryInspectionRepository qmsEntryInspectionRepository) {
    this.qmsEntryInspectionRepository = qmsEntryInspectionRepository;
}
@PostMapping("/qms-entry-inspections")
@Timed
public ResponseEntity<QmsEntryInspection> createQmsEntryInspection(QmsEntryInspection qmsEntryInspection){
    log.debug("REST request to save QmsEntryInspection : {}", qmsEntryInspection);
    if (qmsEntryInspection.getId() != null) {
        throw new BadRequestAlertException("A new qmsEntryInspection cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsEntryInspection result = qmsEntryInspectionRepository.save(qmsEntryInspection);
    return ResponseEntity.created(new URI("/api/qms-entry-inspections/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-entry-inspections")
@Timed
public ResponseEntity<QmsEntryInspection> updateQmsEntryInspection(QmsEntryInspection qmsEntryInspection){
    log.debug("REST request to update QmsEntryInspection : {}", qmsEntryInspection);
    if (qmsEntryInspection.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsEntryInspection result = qmsEntryInspectionRepository.save(qmsEntryInspection);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsEntryInspection.getId().toString())).body(result);
}


@DeleteMapping("/qms-entry-inspections/{id}")
@Timed
public ResponseEntity<Void> deleteQmsEntryInspection(Long id){
    log.debug("REST request to delete QmsEntryInspection : {}", id);
    qmsEntryInspectionRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-entry-inspections")
@Timed
public ResponseEntity<List<QmsEntryInspection>> getAllQmsEntryInspections(Pageable pageable){
    log.debug("REST request to get a page of QmsEntryInspections");
    Page<QmsEntryInspection> page = qmsEntryInspectionRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-entry-inspections");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-entry-inspections/{id}")
@Timed
public ResponseEntity<QmsEntryInspection> getQmsEntryInspection(Long id){
    log.debug("REST request to get QmsEntryInspection : {}", id);
    Optional<QmsEntryInspection> qmsEntryInspection = qmsEntryInspectionRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsEntryInspection);
}


}