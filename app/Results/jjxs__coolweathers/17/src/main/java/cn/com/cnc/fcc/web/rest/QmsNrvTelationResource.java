package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsNrvTelation;
import cn.com.cnc.fcc.repository.QmsNrvTelationRepository;
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
public class QmsNrvTelationResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsNrvTelationRepository qmsNrvTelationRepository;

public QmsNrvTelationResource(QmsNrvTelationRepository qmsNrvTelationRepository) {
    this.qmsNrvTelationRepository = qmsNrvTelationRepository;
}
@GetMapping("/qms-nrv-telations")
@Timed
public ResponseEntity<List<QmsNrvTelation>> getAllQmsNrvTelations(Pageable pageable){
    log.debug("REST request to get a page of QmsNrvTelations");
    Page<QmsNrvTelation> page = qmsNrvTelationRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-nrv-telations");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-nrv-telations/{id}")
@Timed
public ResponseEntity<QmsNrvTelation> getQmsNrvTelation(Long id){
    log.debug("REST request to get QmsNrvTelation : {}", id);
    Optional<QmsNrvTelation> qmsNrvTelation = qmsNrvTelationRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsNrvTelation);
}


@PutMapping("/qms-nrv-telations")
@Timed
public ResponseEntity<QmsNrvTelation> updateQmsNrvTelation(QmsNrvTelation qmsNrvTelation){
    log.debug("REST request to update QmsNrvTelation : {}", qmsNrvTelation);
    if (qmsNrvTelation.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsNrvTelation result = qmsNrvTelationRepository.save(qmsNrvTelation);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsNrvTelation.getId().toString())).body(result);
}


@DeleteMapping("/qms-nrv-telations/{id}")
@Timed
public ResponseEntity<Void> deleteQmsNrvTelation(Long id){
    log.debug("REST request to delete QmsNrvTelation : {}", id);
    qmsNrvTelationRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-nrv-telations")
@Timed
public ResponseEntity<QmsNrvTelation> createQmsNrvTelation(QmsNrvTelation qmsNrvTelation){
    log.debug("REST request to save QmsNrvTelation : {}", qmsNrvTelation);
    if (qmsNrvTelation.getId() != null) {
        throw new BadRequestAlertException("A new qmsNrvTelation cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsNrvTelation result = qmsNrvTelationRepository.save(qmsNrvTelation);
    return ResponseEntity.created(new URI("/api/qms-nrv-telations/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}