import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsProductionInspectionResult;
import cn.com.cnc.fcc.repository.QmsProductionInspectionResultRepository;
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
public class QmsProductionInspectionResultResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsProductionInspectionResultRepository qmsProductionInspectionResultRepository;


@GetMapping("/qms-production-inspection-results/{id}")
@Timed
public ResponseEntity<QmsProductionInspectionResult> getQmsProductionInspectionResult(Long id){
    log.debug("REST request to get QmsProductionInspectionResult : {}", id);
    Optional<QmsProductionInspectionResult> qmsProductionInspectionResult = qmsProductionInspectionResultRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsProductionInspectionResult);
}


@PostMapping("/qms-production-inspection-results")
@Timed
public ResponseEntity<QmsProductionInspectionResult> createQmsProductionInspectionResult(QmsProductionInspectionResult qmsProductionInspectionResult){
    log.debug("REST request to save QmsProductionInspectionResult : {}", qmsProductionInspectionResult);
    if (qmsProductionInspectionResult.getId() != null) {
        throw new BadRequestAlertException("A new qmsProductionInspectionResult cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsProductionInspectionResult result = qmsProductionInspectionResultRepository.save(qmsProductionInspectionResult);
    return ResponseEntity.created(new URI("/api/qms-production-inspection-results/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@DeleteMapping("/qms-production-inspection-results/{id}")
@Timed
public ResponseEntity<Void> deleteQmsProductionInspectionResult(Long id){
    log.debug("REST request to delete QmsProductionInspectionResult : {}", id);
    qmsProductionInspectionResultRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-production-inspection-results")
@Timed
public ResponseEntity<QmsProductionInspectionResult> updateQmsProductionInspectionResult(QmsProductionInspectionResult qmsProductionInspectionResult){
    log.debug("REST request to update QmsProductionInspectionResult : {}", qmsProductionInspectionResult);
    if (qmsProductionInspectionResult.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsProductionInspectionResult result = qmsProductionInspectionResultRepository.save(qmsProductionInspectionResult);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsProductionInspectionResult.getId().toString())).body(result);
}


@GetMapping("/qms-production-inspection-results")
@Timed
public ResponseEntity<List<QmsProductionInspectionResult>> getAllQmsProductionInspectionResults(Pageable pageable){
    log.debug("REST request to get a page of QmsProductionInspectionResults");
    Page<QmsProductionInspectionResult> page = qmsProductionInspectionResultRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-production-inspection-results");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}