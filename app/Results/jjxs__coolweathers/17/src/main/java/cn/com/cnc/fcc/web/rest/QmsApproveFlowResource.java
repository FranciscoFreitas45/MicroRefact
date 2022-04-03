package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsApproveFlow;
import cn.com.cnc.fcc.repository.QmsApproveFlowRepository;
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
public class QmsApproveFlowResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsApproveFlowRepository qmsApproveFlowRepository;

public QmsApproveFlowResource(QmsApproveFlowRepository qmsApproveFlowRepository) {
    this.qmsApproveFlowRepository = qmsApproveFlowRepository;
}
@GetMapping("/qms-approve-flows/{id}")
@Timed
public ResponseEntity<QmsApproveFlow> getQmsApproveFlow(Long id){
    log.debug("REST request to get QmsApproveFlow : {}", id);
    Optional<QmsApproveFlow> qmsApproveFlow = qmsApproveFlowRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsApproveFlow);
}


@PostMapping("/qms-approve-flows")
@Timed
public ResponseEntity<QmsApproveFlow> createQmsApproveFlow(QmsApproveFlow qmsApproveFlow){
    log.debug("REST request to save QmsApproveFlow : {}", qmsApproveFlow);
    if (qmsApproveFlow.getId() != null) {
        throw new BadRequestAlertException("A new qmsApproveFlow cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsApproveFlow result = qmsApproveFlowRepository.save(qmsApproveFlow);
    return ResponseEntity.created(new URI("/api/qms-approve-flows/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@DeleteMapping("/qms-approve-flows/{id}")
@Timed
public ResponseEntity<Void> deleteQmsApproveFlow(Long id){
    log.debug("REST request to delete QmsApproveFlow : {}", id);
    qmsApproveFlowRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-approve-flows")
@Timed
public ResponseEntity<List<QmsApproveFlow>> getAllQmsApproveFlows(Pageable pageable){
    log.debug("REST request to get a page of QmsApproveFlows");
    Page<QmsApproveFlow> page = qmsApproveFlowRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-approve-flows");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-approve-flows")
@Timed
public ResponseEntity<QmsApproveFlow> updateQmsApproveFlow(QmsApproveFlow qmsApproveFlow){
    log.debug("REST request to update QmsApproveFlow : {}", qmsApproveFlow);
    if (qmsApproveFlow.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsApproveFlow result = qmsApproveFlowRepository.save(qmsApproveFlow);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsApproveFlow.getId().toString())).body(result);
}


}