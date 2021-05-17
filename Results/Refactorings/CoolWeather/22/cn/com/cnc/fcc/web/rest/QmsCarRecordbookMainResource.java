import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsCarRecordbookMain;
import cn.com.cnc.fcc.repository.QmsCarRecordbookMainRepository;
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
public class QmsCarRecordbookMainResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsCarRecordbookMainRepository qmsCarRecordbookMainRepository;


@GetMapping("/qms-car-recordbook-mains/{id}")
@Timed
public ResponseEntity<QmsCarRecordbookMain> getQmsCarRecordbookMain(Long id){
    log.debug("REST request to get QmsCarRecordbookMain : {}", id);
    Optional<QmsCarRecordbookMain> qmsCarRecordbookMain = qmsCarRecordbookMainRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsCarRecordbookMain);
}


@GetMapping("/qms-car-recordbook-mains")
@Timed
public ResponseEntity<List<QmsCarRecordbookMain>> getAllQmsCarRecordbookMains(Pageable pageable){
    log.debug("REST request to get a page of QmsCarRecordbookMains");
    Page<QmsCarRecordbookMain> page = qmsCarRecordbookMainRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-car-recordbook-mains");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-car-recordbook-mains/{id}")
@Timed
public ResponseEntity<Void> deleteQmsCarRecordbookMain(Long id){
    log.debug("REST request to delete QmsCarRecordbookMain : {}", id);
    qmsCarRecordbookMainRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-car-recordbook-mains")
@Timed
public ResponseEntity<QmsCarRecordbookMain> createQmsCarRecordbookMain(QmsCarRecordbookMain qmsCarRecordbookMain){
    log.debug("REST request to save QmsCarRecordbookMain : {}", qmsCarRecordbookMain);
    if (qmsCarRecordbookMain.getId() != null) {
        throw new BadRequestAlertException("A new qmsCarRecordbookMain cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsCarRecordbookMain result = qmsCarRecordbookMainRepository.save(qmsCarRecordbookMain);
    return ResponseEntity.created(new URI("/api/qms-car-recordbook-mains/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-car-recordbook-mains")
@Timed
public ResponseEntity<QmsCarRecordbookMain> updateQmsCarRecordbookMain(QmsCarRecordbookMain qmsCarRecordbookMain){
    log.debug("REST request to update QmsCarRecordbookMain : {}", qmsCarRecordbookMain);
    if (qmsCarRecordbookMain.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsCarRecordbookMain result = qmsCarRecordbookMainRepository.save(qmsCarRecordbookMain);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsCarRecordbookMain.getId().toString())).body(result);
}


}