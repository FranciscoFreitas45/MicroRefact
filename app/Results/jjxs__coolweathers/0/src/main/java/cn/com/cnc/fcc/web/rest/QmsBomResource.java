package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsBom;
import cn.com.cnc.fcc.repository.QmsBomRepository;
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
public class QmsBomResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsBomRepository qmsBomRepository;

public QmsBomResource(QmsBomRepository qmsBomRepository) {
    this.qmsBomRepository = qmsBomRepository;
}
@GetMapping("/qms-boms")
@Timed
public ResponseEntity<List<QmsBom>> getAllQmsBoms(Pageable pageable){
    log.debug("REST request to get a page of QmsBoms");
    Page<QmsBom> page = qmsBomRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-boms");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-boms/{id}")
@Timed
public ResponseEntity<Void> deleteQmsBom(Long id){
    log.debug("REST request to delete QmsBom : {}", id);
    qmsBomRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-boms")
@Timed
public ResponseEntity<QmsBom> createQmsBom(QmsBom qmsBom){
    log.debug("REST request to save QmsBom : {}", qmsBom);
    if (qmsBom.getId() != null) {
        throw new BadRequestAlertException("A new qmsBom cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsBom result = qmsBomRepository.save(qmsBom);
    return ResponseEntity.created(new URI("/api/qms-boms/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-boms")
@Timed
public ResponseEntity<QmsBom> updateQmsBom(QmsBom qmsBom){
    log.debug("REST request to update QmsBom : {}", qmsBom);
    if (qmsBom.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsBom result = qmsBomRepository.save(qmsBom);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsBom.getId().toString())).body(result);
}


@GetMapping("/qms-boms/{id}")
@Timed
public ResponseEntity<QmsBom> getQmsBom(Long id){
    log.debug("REST request to get QmsBom : {}", id);
    Optional<QmsBom> qmsBom = qmsBomRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsBom);
}


}