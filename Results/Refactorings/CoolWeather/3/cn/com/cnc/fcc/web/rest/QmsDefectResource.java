import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsDefect;
import cn.com.cnc.fcc.repository.QmsDefectRepository;
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
public class QmsDefectResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsDefectRepository qmsDefectRepository;


@GetMapping("/qms-defects")
@Timed
public ResponseEntity<List<QmsDefect>> getAllQmsDefects(Pageable pageable){
    log.debug("REST request to get a page of QmsDefects");
    Page<QmsDefect> page = qmsDefectRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-defects");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-defects/{id}")
@Timed
public ResponseEntity<QmsDefect> getQmsDefect(Long id){
    log.debug("REST request to get QmsDefect : {}", id);
    Optional<QmsDefect> qmsDefect = qmsDefectRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsDefect);
}


@DeleteMapping("/qms-defects/{id}")
@Timed
public ResponseEntity<Void> deleteQmsDefect(Long id){
    log.debug("REST request to delete QmsDefect : {}", id);
    qmsDefectRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-defects")
@Timed
public ResponseEntity<QmsDefect> createQmsDefect(QmsDefect qmsDefect){
    log.debug("REST request to save QmsDefect : {}", qmsDefect);
    if (qmsDefect.getId() != null) {
        throw new BadRequestAlertException("A new qmsDefect cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsDefect result = qmsDefectRepository.save(qmsDefect);
    return ResponseEntity.created(new URI("/api/qms-defects/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/qms-defects")
@Timed
public ResponseEntity<QmsDefect> updateQmsDefect(QmsDefect qmsDefect){
    log.debug("REST request to update QmsDefect : {}", qmsDefect);
    if (qmsDefect.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsDefect result = qmsDefectRepository.save(qmsDefect);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsDefect.getId().toString())).body(result);
}


}