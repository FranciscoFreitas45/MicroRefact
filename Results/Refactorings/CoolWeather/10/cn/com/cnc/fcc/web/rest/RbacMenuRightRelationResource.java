import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacMenuRightRelation;
import cn.com.cnc.fcc.repository.RbacMenuRightRelationRepository;
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
public class RbacMenuRightRelationResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacMenuRightRelationRepository rbacMenuRightRelationRepository;


@PutMapping("/rbac-menu-right-relations")
@Timed
public ResponseEntity<RbacMenuRightRelation> updateRbacMenuRightRelation(RbacMenuRightRelation rbacMenuRightRelation){
    log.debug("REST request to update RbacMenuRightRelation : {}", rbacMenuRightRelation);
    if (rbacMenuRightRelation.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacMenuRightRelation result = rbacMenuRightRelationRepository.save(rbacMenuRightRelation);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacMenuRightRelation.getId().toString())).body(result);
}


@DeleteMapping("/rbac-menu-right-relations/{id}")
@Timed
public ResponseEntity<Void> deleteRbacMenuRightRelation(Long id){
    log.debug("REST request to delete RbacMenuRightRelation : {}", id);
    rbacMenuRightRelationRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/rbac-menu-right-relations")
@Timed
public ResponseEntity<List<RbacMenuRightRelation>> getAllRbacMenuRightRelations(Pageable pageable){
    log.debug("REST request to get a page of RbacMenuRightRelations");
    Page<RbacMenuRightRelation> page = rbacMenuRightRelationRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-menu-right-relations");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/rbac-menu-right-relations")
@Timed
public ResponseEntity<RbacMenuRightRelation> createRbacMenuRightRelation(RbacMenuRightRelation rbacMenuRightRelation){
    log.debug("REST request to save RbacMenuRightRelation : {}", rbacMenuRightRelation);
    if (rbacMenuRightRelation.getId() != null) {
        throw new BadRequestAlertException("A new rbacMenuRightRelation cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacMenuRightRelation result = rbacMenuRightRelationRepository.save(rbacMenuRightRelation);
    return ResponseEntity.created(new URI("/api/rbac-menu-right-relations/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/rbac-menu-right-relations/{id}")
@Timed
public ResponseEntity<RbacMenuRightRelation> getRbacMenuRightRelation(Long id){
    log.debug("REST request to get RbacMenuRightRelation : {}", id);
    Optional<RbacMenuRightRelation> rbacMenuRightRelation = rbacMenuRightRelationRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacMenuRightRelation);
}


}