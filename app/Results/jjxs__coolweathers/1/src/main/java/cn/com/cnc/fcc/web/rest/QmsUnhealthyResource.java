package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsUnhealthy;
import cn.com.cnc.fcc.repository.QmsUnhealthyRepository;
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
public class QmsUnhealthyResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsUnhealthyRepository qmsUnhealthyRepository;

public QmsUnhealthyResource(QmsUnhealthyRepository qmsUnhealthyRepository) {
    this.qmsUnhealthyRepository = qmsUnhealthyRepository;
}
@PostMapping("/qms-unhealthies")
@Timed
public ResponseEntity<QmsUnhealthy> createQmsUnhealthy(QmsUnhealthy qmsUnhealthy){
    log.debug("REST request to save QmsUnhealthy : {}", qmsUnhealthy);
    if (qmsUnhealthy.getId() != null) {
        throw new BadRequestAlertException("A new qmsUnhealthy cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsUnhealthy result = qmsUnhealthyRepository.save(qmsUnhealthy);
    return ResponseEntity.created(new URI("/api/qms-unhealthies/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-unhealthies")
@Timed
public ResponseEntity<QmsUnhealthy> updateQmsUnhealthy(QmsUnhealthy qmsUnhealthy){
    log.debug("REST request to update QmsUnhealthy : {}", qmsUnhealthy);
    if (qmsUnhealthy.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsUnhealthy result = qmsUnhealthyRepository.save(qmsUnhealthy);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsUnhealthy.getId().toString())).body(result);
}


@GetMapping("/qms-unhealthies")
@Timed
public ResponseEntity<List<QmsUnhealthy>> getAllQmsUnhealthies(Pageable pageable){
    log.debug("REST request to get a page of QmsUnhealthies");
    Page<QmsUnhealthy> page = qmsUnhealthyRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-unhealthies");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-unhealthies/{id}")
@Timed
public ResponseEntity<QmsUnhealthy> getQmsUnhealthy(Long id){
    log.debug("REST request to get QmsUnhealthy : {}", id);
    Optional<QmsUnhealthy> qmsUnhealthy = qmsUnhealthyRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsUnhealthy);
}


@DeleteMapping("/qms-unhealthies/{id}")
@Timed
public ResponseEntity<Void> deleteQmsUnhealthy(Long id){
    log.debug("REST request to delete QmsUnhealthy : {}", id);
    qmsUnhealthyRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}