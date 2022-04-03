package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.service.QmsSuppliersInfoService;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMaterielSupplier;
import cn.com.cnc.fcc.repository.QmsMaterielSupplierRepository;
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
public class QmsMaterielSupplierResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsMaterielSupplierRepository qmsMaterielSupplierRepository;

public QmsMaterielSupplierResource(QmsMaterielSupplierRepository qmsMaterielSupplierRepository) {
    this.qmsMaterielSupplierRepository = qmsMaterielSupplierRepository;
}
@GetMapping("/qms-materiel-suppliers")
@Timed
public ResponseEntity<List<QmsMaterielSupplier>> getAllQmsMaterielSuppliers(Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielSuppliers");
    Page<QmsMaterielSupplier> page = qmsMaterielSupplierRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-suppliers");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-materiel-suppliers")
@Timed
public ResponseEntity<QmsMaterielSupplier> updateQmsMaterielSupplier(QmsMaterielSupplier qmsMaterielSupplier){
    log.debug("REST request to update QmsMaterielSupplier : {}", qmsMaterielSupplier);
    if (qmsMaterielSupplier.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    Integer supplierId = qmsMaterielSupplier.getSupplierId();
    Integer materielId = qmsMaterielSupplier.getMaterielId();
    Long id = qmsMaterielSupplier.getId();
    Optional<QmsMaterielSupplier> check = qmsMaterielSupplierRepository.findByMaterielIdAndSupplierIdAndIdNot(materielId, supplierId, id);
    QmsMaterielSupplier result = qmsMaterielSupplierRepository.save(qmsMaterielSupplier);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsMaterielSupplier.getId().toString())).body(result);
}


@PostMapping("/qms-materiel-suppliers")
@Timed
public ResponseEntity<QmsMaterielSupplier> createQmsMaterielSupplier(QmsMaterielSupplier qmsMaterielSupplier){
    log.debug("REST request to save QmsMaterielSupplier : {}", qmsMaterielSupplier);
    if (qmsMaterielSupplier.getId() != null) {
        throw new BadRequestAlertException("A new qmsMaterielSupplier cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsMaterielSupplier result = qmsMaterielSupplierRepository.save(qmsMaterielSupplier);
    return ResponseEntity.created(new URI("/api/qms-materiel-suppliers/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-materiel-suppliers/{id}")
@Timed
public ResponseEntity<QmsMaterielSupplier> getQmsMaterielSupplier(Long id){
    log.debug("REST request to get QmsMaterielSupplier : {}", id);
    Optional<QmsMaterielSupplier> qmsMaterielSupplier = qmsMaterielSupplierRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMaterielSupplier);
}


@DeleteMapping("/qms-materiel-suppliers/{id}")
@Timed
public ResponseEntity<Void> deleteQmsMaterielSupplier(Long id){
    log.debug("REST request to delete QmsMaterielSupplier : {}", id);
    qmsMaterielSupplierRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}