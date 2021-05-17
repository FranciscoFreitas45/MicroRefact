import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMaterielType;
import cn.com.cnc.fcc.repository.QmsMaterielTypeRepository;
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
public class QmsMaterielTypeResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsMaterielTypeRepository qmsMaterielTypeRepository;


@GetMapping("/qms-materiel-types")
@Timed
public ResponseEntity<List<QmsMaterielType>> getAllQmsMaterielTypes(Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielTypes");
    Page<QmsMaterielType> page = qmsMaterielTypeRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-types");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/qms-materiel-types")
@Timed
public ResponseEntity<QmsMaterielType> createQmsMaterielType(QmsMaterielType qmsMaterielType){
    log.debug("REST request to save QmsMaterielType : {}", qmsMaterielType);
    if (qmsMaterielType.getId() != null) {
        throw new BadRequestAlertException("A new qmsMaterielType cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsMaterielType result = qmsMaterielTypeRepository.save(qmsMaterielType);
    return ResponseEntity.created(new URI("/api/qms-materiel-types/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@DeleteMapping("/qms-materiel-types/{id}")
@Timed
public ResponseEntity<Void> deleteQmsMaterielType(Long id){
    log.debug("REST request to delete QmsMaterielType : {}", id);
    qmsMaterielTypeRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/qms-materiel-types/{id}")
@Timed
public ResponseEntity<QmsMaterielType> getQmsMaterielType(Long id){
    log.debug("REST request to get QmsMaterielType : {}", id);
    Optional<QmsMaterielType> qmsMaterielType = qmsMaterielTypeRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMaterielType);
}


@PutMapping("/qms-materiel-types")
@Timed
public ResponseEntity<QmsMaterielType> updateQmsMaterielType(QmsMaterielType qmsMaterielType){
    log.debug("REST request to update QmsMaterielType : {}", qmsMaterielType);
    if (qmsMaterielType.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsMaterielType result = qmsMaterielTypeRepository.save(qmsMaterielType);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsMaterielType.getId().toString())).body(result);
}


}