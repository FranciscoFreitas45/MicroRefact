package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsEnclosure;
import cn.com.cnc.fcc.repository.QmsEnclosureRepository;
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
public class QmsEnclosureResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsEnclosureRepository qmsEnclosureRepository;

public QmsEnclosureResource(QmsEnclosureRepository qmsEnclosureRepository) {
    this.qmsEnclosureRepository = qmsEnclosureRepository;
}
@GetMapping("/qms-enclosures/{id}")
@Timed
public ResponseEntity<QmsEnclosure> getQmsEnclosure(Long id){
    log.debug("REST request to get QmsEnclosure : {}", id);
    Optional<QmsEnclosure> qmsEnclosure = qmsEnclosureRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsEnclosure);
}


@PostMapping("/qms-enclosures")
@Timed
public ResponseEntity<QmsEnclosure> createQmsEnclosure(QmsEnclosure qmsEnclosure){
    log.debug("REST request to save QmsEnclosure : {}", qmsEnclosure);
    if (qmsEnclosure.getId() != null) {
        throw new BadRequestAlertException("A new qmsEnclosure cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsEnclosure result = qmsEnclosureRepository.save(qmsEnclosure);
    return ResponseEntity.created(new URI("/api/qms-enclosures/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-enclosures")
@Timed
public ResponseEntity<QmsEnclosure> updateQmsEnclosure(QmsEnclosure qmsEnclosure){
    log.debug("REST request to update QmsEnclosure : {}", qmsEnclosure);
    if (qmsEnclosure.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsEnclosure result = qmsEnclosureRepository.save(qmsEnclosure);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsEnclosure.getId().toString())).body(result);
}


@GetMapping("/qms-enclosures")
@Timed
public ResponseEntity<List<QmsEnclosure>> getAllQmsEnclosures(Pageable pageable){
    log.debug("REST request to get a page of QmsEnclosures");
    Page<QmsEnclosure> page = qmsEnclosureRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-enclosures");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-enclosures/{id}")
@Timed
public ResponseEntity<Void> deleteQmsEnclosure(Long id){
    log.debug("REST request to delete QmsEnclosure : {}", id);
    qmsEnclosureRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}