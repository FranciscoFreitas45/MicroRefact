import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsNotice;
import cn.com.cnc.fcc.repository.QmsNoticeRepository;
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
public class QmsNoticeResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsNoticeRepository qmsNoticeRepository;


@GetMapping("/qms-notices")
@Timed
public ResponseEntity<List<QmsNotice>> getAllQmsNotices(Pageable pageable){
    log.debug("REST request to get a page of QmsNotices");
    Page<QmsNotice> page = qmsNoticeRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-notices");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-notices/{id}")
@Timed
public ResponseEntity<QmsNotice> getQmsNotice(Long id){
    log.debug("REST request to get QmsNotice : {}", id);
    Optional<QmsNotice> qmsNotice = qmsNoticeRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsNotice);
}


@PostMapping("/qms-notices")
@Timed
public ResponseEntity<QmsNotice> createQmsNotice(QmsNotice qmsNotice){
    log.debug("REST request to save QmsNotice : {}", qmsNotice);
    if (qmsNotice.getId() != null) {
        throw new BadRequestAlertException("A new qmsNotice cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsNotice result = qmsNoticeRepository.save(qmsNotice);
    return ResponseEntity.created(new URI("/api/qms-notices/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@DeleteMapping("/qms-notices/{id}")
@Timed
public ResponseEntity<Void> deleteQmsNotice(Long id){
    log.debug("REST request to delete QmsNotice : {}", id);
    qmsNoticeRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-notices")
@Timed
public ResponseEntity<QmsNotice> updateQmsNotice(QmsNotice qmsNotice){
    log.debug("REST request to update QmsNotice : {}", qmsNotice);
    if (qmsNotice.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsNotice result = qmsNoticeRepository.save(qmsNotice);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsNotice.getId().toString())).body(result);
}


}