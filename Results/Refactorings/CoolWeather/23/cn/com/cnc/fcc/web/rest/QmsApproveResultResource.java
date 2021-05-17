import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsApproveResult;
import cn.com.cnc.fcc.repository.QmsApproveResultRepository;
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
public class QmsApproveResultResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsApproveResultRepository qmsApproveResultRepository;


@PostMapping("/qms-approve-results")
@Timed
public ResponseEntity<QmsApproveResult> createQmsApproveResult(QmsApproveResult qmsApproveResult){
    log.debug("REST request to save QmsApproveResult : {}", qmsApproveResult);
    if (qmsApproveResult.getId() != null) {
        throw new BadRequestAlertException("A new qmsApproveResult cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsApproveResult result = qmsApproveResultRepository.save(qmsApproveResult);
    return ResponseEntity.created(new URI("/api/qms-approve-results/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-approve-results")
@Timed
public ResponseEntity<List<QmsApproveResult>> getAllQmsApproveResults(Pageable pageable){
    log.debug("REST request to get a page of QmsApproveResults");
    Page<QmsApproveResult> page = qmsApproveResultRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-approve-results");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-approve-results/{id}")
@Timed
public ResponseEntity<Void> deleteQmsApproveResult(Long id){
    log.debug("REST request to delete QmsApproveResult : {}", id);
    qmsApproveResultRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-approve-results/{id}")
@Timed
public ResponseEntity<QmsApproveResult> getQmsApproveResult(Long id){
    log.debug("REST request to get QmsApproveResult : {}", id);
    Optional<QmsApproveResult> qmsApproveResult = qmsApproveResultRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsApproveResult);
}


@PutMapping("/qms-approve-results")
@Timed
public ResponseEntity<QmsApproveResult> updateQmsApproveResult(QmsApproveResult qmsApproveResult){
    log.debug("REST request to update QmsApproveResult : {}", qmsApproveResult);
    if (qmsApproveResult.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsApproveResult result = qmsApproveResultRepository.save(qmsApproveResult);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsApproveResult.getId().toString())).body(result);
}


}