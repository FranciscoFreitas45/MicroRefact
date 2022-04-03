package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsProductionRelation;
import cn.com.cnc.fcc.repository.QmsProductionRelationRepository;
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
public class QmsProductionRelationResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsProductionRelationRepository qmsProductionRelationRepository;

public QmsProductionRelationResource(QmsProductionRelationRepository qmsProductionRelationRepository) {
    this.qmsProductionRelationRepository = qmsProductionRelationRepository;
}
@GetMapping("/qms-production-relations")
@Timed
public ResponseEntity<List<QmsProductionRelation>> getAllQmsProductionRelations(Pageable pageable){
    log.debug("REST request to get a page of QmsProductionRelations");
    Page<QmsProductionRelation> page = qmsProductionRelationRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-production-relations");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-production-relations/{id}")
@Timed
public ResponseEntity<QmsProductionRelation> getQmsProductionRelation(Long id){
    log.debug("REST request to get QmsProductionRelation : {}", id);
    Optional<QmsProductionRelation> qmsProductionRelation = qmsProductionRelationRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsProductionRelation);
}


@DeleteMapping("/qms-production-relations/{id}")
@Timed
public ResponseEntity<Void> deleteQmsProductionRelation(Long id){
    log.debug("REST request to delete QmsProductionRelation : {}", id);
    qmsProductionRelationRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-production-relations")
@Timed
public ResponseEntity<QmsProductionRelation> createQmsProductionRelation(QmsProductionRelation qmsProductionRelation){
    log.debug("REST request to save QmsProductionRelation : {}", qmsProductionRelation);
    if (qmsProductionRelation.getId() != null) {
        throw new BadRequestAlertException("A new qmsProductionRelation cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsProductionRelation result = qmsProductionRelationRepository.save(qmsProductionRelation);
    return ResponseEntity.created(new URI("/api/qms-production-relations/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-production-relations")
@Timed
public ResponseEntity<QmsProductionRelation> updateQmsProductionRelation(QmsProductionRelation qmsProductionRelation){
    log.debug("REST request to update QmsProductionRelation : {}", qmsProductionRelation);
    if (qmsProductionRelation.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsProductionRelation result = qmsProductionRelationRepository.save(qmsProductionRelation);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsProductionRelation.getId().toString())).body(result);
}


}