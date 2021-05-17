import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsProductionTask;
import cn.com.cnc.fcc.repository.QmsProductionTaskRepository;
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
public class QmsProductionTaskResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsProductionTaskRepository qmsProductionTaskRepository;


@GetMapping("/qms-production-tasks/{id}")
@Timed
public ResponseEntity<QmsProductionTask> getQmsProductionTask(Long id){
    log.debug("REST request to get QmsProductionTask : {}", id);
    Optional<QmsProductionTask> qmsProductionTask = qmsProductionTaskRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsProductionTask);
}


@PutMapping("/qms-production-tasks")
@Timed
public ResponseEntity<QmsProductionTask> updateQmsProductionTask(QmsProductionTask qmsProductionTask){
    log.debug("REST request to update QmsProductionTask : {}", qmsProductionTask);
    if (qmsProductionTask.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsProductionTask result = qmsProductionTaskRepository.save(qmsProductionTask);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsProductionTask.getId().toString())).body(result);
}


@DeleteMapping("/qms-production-tasks/{id}")
@Timed
public ResponseEntity<Void> deleteQmsProductionTask(Long id){
    log.debug("REST request to delete QmsProductionTask : {}", id);
    qmsProductionTaskRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-production-tasks")
@Timed
public ResponseEntity<List<QmsProductionTask>> getAllQmsProductionTasks(Pageable pageable){
    log.debug("REST request to get a page of QmsProductionTasks");
    Page<QmsProductionTask> page = qmsProductionTaskRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-production-tasks");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/qms-production-tasks")
@Timed
public ResponseEntity<QmsProductionTask> createQmsProductionTask(QmsProductionTask qmsProductionTask){
    log.debug("REST request to save QmsProductionTask : {}", qmsProductionTask);
    if (qmsProductionTask.getId() != null) {
        throw new BadRequestAlertException("A new qmsProductionTask cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsProductionTask result = qmsProductionTaskRepository.save(qmsProductionTask);
    return ResponseEntity.created(new URI("/api/qms-production-tasks/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}