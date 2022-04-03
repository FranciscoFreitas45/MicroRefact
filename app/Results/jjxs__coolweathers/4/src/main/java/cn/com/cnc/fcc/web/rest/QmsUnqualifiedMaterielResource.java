package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsUnqualifiedMateriel;
import cn.com.cnc.fcc.repository.QmsUnqualifiedMaterielRepository;
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
public class QmsUnqualifiedMaterielResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsUnqualifiedMaterielRepository qmsUnqualifiedMaterielRepository;

public QmsUnqualifiedMaterielResource(QmsUnqualifiedMaterielRepository qmsUnqualifiedMaterielRepository) {
    this.qmsUnqualifiedMaterielRepository = qmsUnqualifiedMaterielRepository;
}
@DeleteMapping("/qms-unqualified-materiels/{id}")
@Timed
public ResponseEntity<Void> deleteQmsUnqualifiedMateriel(Long id){
    log.debug("REST request to delete QmsUnqualifiedMateriel : {}", id);
    qmsUnqualifiedMaterielRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-unqualified-materiels")
@Timed
public ResponseEntity<QmsUnqualifiedMateriel> updateQmsUnqualifiedMateriel(QmsUnqualifiedMateriel qmsUnqualifiedMateriel){
    log.debug("REST request to update QmsUnqualifiedMateriel : {}", qmsUnqualifiedMateriel);
    if (qmsUnqualifiedMateriel.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsUnqualifiedMateriel result = qmsUnqualifiedMaterielRepository.save(qmsUnqualifiedMateriel);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsUnqualifiedMateriel.getId().toString())).body(result);
}


@PostMapping("/qms-unqualified-materiels")
@Timed
public ResponseEntity<QmsUnqualifiedMateriel> createQmsUnqualifiedMateriel(QmsUnqualifiedMateriel qmsUnqualifiedMateriel){
    log.debug("REST request to save QmsUnqualifiedMateriel : {}", qmsUnqualifiedMateriel);
    if (qmsUnqualifiedMateriel.getId() != null) {
        throw new BadRequestAlertException("A new qmsUnqualifiedMateriel cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsUnqualifiedMateriel result = qmsUnqualifiedMaterielRepository.save(qmsUnqualifiedMateriel);
    return ResponseEntity.created(new URI("/api/qms-unqualified-materiels/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-unqualified-materiels")
@Timed
public ResponseEntity<List<QmsUnqualifiedMateriel>> getAllQmsUnqualifiedMateriels(Pageable pageable){
    log.debug("REST request to get a page of QmsUnqualifiedMateriels");
    Page<QmsUnqualifiedMateriel> page = qmsUnqualifiedMaterielRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-unqualified-materiels");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-unqualified-materiels/{id}")
@Timed
public ResponseEntity<QmsUnqualifiedMateriel> getQmsUnqualifiedMateriel(Long id){
    log.debug("REST request to get QmsUnqualifiedMateriel : {}", id);
    Optional<QmsUnqualifiedMateriel> qmsUnqualifiedMateriel = qmsUnqualifiedMaterielRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsUnqualifiedMateriel);
}


}