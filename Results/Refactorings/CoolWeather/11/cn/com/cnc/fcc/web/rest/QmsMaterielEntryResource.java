import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMaterielEntry;
import cn.com.cnc.fcc.repository.QmsMaterielEntryRepository;
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
public class QmsMaterielEntryResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsMaterielEntryRepository qmsMaterielEntryRepository;


@DeleteMapping("/qms-materiel-entries/{id}")
@Timed
public ResponseEntity<Void> deleteQmsMaterielEntry(Long id){
    log.debug("REST request to delete QmsMaterielEntry : {}", id);
    qmsMaterielEntryRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-materiel-entries")
@Timed
public ResponseEntity<List<QmsMaterielEntry>> getAllQmsMaterielEntries(Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielEntries");
    Page<QmsMaterielEntry> page = qmsMaterielEntryRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-entries");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-materiel-entries")
@Timed
public ResponseEntity<QmsMaterielEntry> updateQmsMaterielEntry(QmsMaterielEntry qmsMaterielEntry){
    log.debug("REST request to update QmsMaterielEntry : {}", qmsMaterielEntry);
    if (qmsMaterielEntry.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsMaterielEntry result = qmsMaterielEntryRepository.save(qmsMaterielEntry);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsMaterielEntry.getId().toString())).body(result);
}


@GetMapping("/qms-materiel-entries/{id}")
@Timed
public ResponseEntity<QmsMaterielEntry> getQmsMaterielEntry(Long id){
    log.debug("REST request to get QmsMaterielEntry : {}", id);
    Optional<QmsMaterielEntry> qmsMaterielEntry = qmsMaterielEntryRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMaterielEntry);
}


@PostMapping("/qms-materiel-entries")
@Timed
public ResponseEntity<QmsMaterielEntry> createQmsMaterielEntry(QmsMaterielEntry qmsMaterielEntry){
    log.debug("REST request to save QmsMaterielEntry : {}", qmsMaterielEntry);
    if (qmsMaterielEntry.getId() != null) {
        throw new BadRequestAlertException("A new qmsMaterielEntry cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsMaterielEntry result = qmsMaterielEntryRepository.save(qmsMaterielEntry);
    return ResponseEntity.created(new URI("/api/qms-materiel-entries/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}