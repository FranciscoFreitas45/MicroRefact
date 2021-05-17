import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsEquipment;
import cn.com.cnc.fcc.repository.QmsEquipmentRepository;
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
public class QmsEquipmentResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsEquipmentRepository qmsEquipmentRepository;


@PutMapping("/qms-equipments")
@Timed
public ResponseEntity<QmsEquipment> updateQmsEquipment(QmsEquipment qmsEquipment){
    log.debug("REST request to update QmsEquipment : {}", qmsEquipment);
    if (qmsEquipment.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsEquipment result = qmsEquipmentRepository.save(qmsEquipment);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsEquipment.getId().toString())).body(result);
}


@GetMapping("/qms-equipments/{id}")
@Timed
public ResponseEntity<QmsEquipment> getQmsEquipment(Long id){
    log.debug("REST request to get QmsEquipment : {}", id);
    Optional<QmsEquipment> qmsEquipment = qmsEquipmentRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsEquipment);
}


@GetMapping("/qms-equipments")
@Timed
public ResponseEntity<List<QmsEquipment>> getAllQmsEquipments(Pageable pageable){
    log.debug("REST request to get a page of QmsEquipments");
    Page<QmsEquipment> page = qmsEquipmentRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-equipments");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-equipments/{id}")
@Timed
public ResponseEntity<Void> deleteQmsEquipment(Long id){
    log.debug("REST request to delete QmsEquipment : {}", id);
    qmsEquipmentRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-equipments")
@Timed
public ResponseEntity<QmsEquipment> createQmsEquipment(QmsEquipment qmsEquipment){
    log.debug("REST request to save QmsEquipment : {}", qmsEquipment);
    if (qmsEquipment.getId() != null) {
        throw new BadRequestAlertException("A new qmsEquipment cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsEquipment result = qmsEquipmentRepository.save(qmsEquipment);
    return ResponseEntity.created(new URI("/api/qms-equipments/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}