import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsPartsAssemblyRelation;
import cn.com.cnc.fcc.repository.QmsPartsAssemblyRelationRepository;
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
public class QmsPartsAssemblyRelationResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsPartsAssemblyRelationRepository qmsPartsAssemblyRelationRepository;


@PostMapping("/qms-parts-assembly-relations")
@Timed
public ResponseEntity<QmsPartsAssemblyRelation> createQmsPartsAssemblyRelation(QmsPartsAssemblyRelation qmsPartsAssemblyRelation){
    log.debug("REST request to save QmsPartsAssemblyRelation : {}", qmsPartsAssemblyRelation);
    if (qmsPartsAssemblyRelation.getId() != null) {
        throw new BadRequestAlertException("A new qmsPartsAssemblyRelation cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsPartsAssemblyRelation result = qmsPartsAssemblyRelationRepository.save(qmsPartsAssemblyRelation);
    return ResponseEntity.created(new URI("/api/qms-parts-assembly-relations/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-parts-assembly-relations")
@Timed
public ResponseEntity<List<QmsPartsAssemblyRelation>> getAllQmsPartsAssemblyRelations(Pageable pageable){
    log.debug("REST request to get a page of QmsPartsAssemblyRelations");
    Page<QmsPartsAssemblyRelation> page = qmsPartsAssemblyRelationRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-parts-assembly-relations");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-parts-assembly-relations/{id}")
@Timed
public ResponseEntity<QmsPartsAssemblyRelation> getQmsPartsAssemblyRelation(Long id){
    log.debug("REST request to get QmsPartsAssemblyRelation : {}", id);
    Optional<QmsPartsAssemblyRelation> qmsPartsAssemblyRelation = qmsPartsAssemblyRelationRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsPartsAssemblyRelation);
}


@DeleteMapping("/qms-parts-assembly-relations/{id}")
@Timed
public ResponseEntity<Void> deleteQmsPartsAssemblyRelation(Long id){
    log.debug("REST request to delete QmsPartsAssemblyRelation : {}", id);
    qmsPartsAssemblyRelationRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-parts-assembly-relations")
@Timed
public ResponseEntity<QmsPartsAssemblyRelation> updateQmsPartsAssemblyRelation(QmsPartsAssemblyRelation qmsPartsAssemblyRelation){
    log.debug("REST request to update QmsPartsAssemblyRelation : {}", qmsPartsAssemblyRelation);
    if (qmsPartsAssemblyRelation.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsPartsAssemblyRelation result = qmsPartsAssemblyRelationRepository.save(qmsPartsAssemblyRelation);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsPartsAssemblyRelation.getId().toString())).body(result);
}


}