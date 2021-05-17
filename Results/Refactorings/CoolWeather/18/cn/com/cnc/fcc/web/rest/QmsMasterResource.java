import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMaster;
import cn.com.cnc.fcc.repository.QmsMasterRepository;
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
public class QmsMasterResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsMasterRepository qmsMasterRepository;


@PostMapping("/qms-masters")
@Timed
public ResponseEntity<QmsMaster> createQmsMaster(QmsMaster qmsMaster){
    log.debug("REST request to save QmsMaster : {}", qmsMaster);
    if (qmsMaster.getId() != null) {
        throw new BadRequestAlertException("A new qmsMaster cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsMaster result = qmsMasterRepository.save(qmsMaster);
    return ResponseEntity.created(new URI("/api/qms-masters/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-masters/{id}")
@Timed
public ResponseEntity<QmsMaster> getQmsMaster(Long id){
    log.debug("REST request to get QmsMaster : {}", id);
    Optional<QmsMaster> qmsMaster = qmsMasterRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMaster);
}


@DeleteMapping("/qms-masters/{id}")
@Timed
public ResponseEntity<Void> deleteQmsMaster(Long id){
    log.debug("REST request to delete QmsMaster : {}", id);
    qmsMasterRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-masters")
@Timed
public ResponseEntity<QmsMaster> updateQmsMaster(QmsMaster qmsMaster){
    log.debug("REST request to update QmsMaster : {}", qmsMaster);
    if (qmsMaster.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsMaster result = qmsMasterRepository.save(qmsMaster);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsMaster.getId().toString())).body(result);
}


@GetMapping("/qms-masters")
@Timed
public ResponseEntity<List<QmsMaster>> getAllQmsMasters(Pageable pageable){
    log.debug("REST request to get a page of QmsMasters");
    Page<QmsMaster> page = qmsMasterRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-masters");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}