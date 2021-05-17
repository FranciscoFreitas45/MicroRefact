import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacUserRightRelation;
import cn.com.cnc.fcc.repository.RbacUserRightRelationRepository;
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
public class RbacUserRightRelationResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacUserRightRelationRepository rbacUserRightRelationRepository;


@PostMapping("/rbac-user-right-relations")
@Timed
public ResponseEntity<RbacUserRightRelation> createRbacUserRightRelation(RbacUserRightRelation rbacUserRightRelation){
    log.debug("REST request to save RbacUserRightRelation : {}", rbacUserRightRelation);
    if (rbacUserRightRelation.getId() != null) {
        throw new BadRequestAlertException("A new rbacUserRightRelation cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacUserRightRelation result = rbacUserRightRelationRepository.save(rbacUserRightRelation);
    return ResponseEntity.created(new URI("/api/rbac-user-right-relations/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/rbac-user-right-relations")
@Timed
public ResponseEntity<RbacUserRightRelation> updateRbacUserRightRelation(RbacUserRightRelation rbacUserRightRelation){
    log.debug("REST request to update RbacUserRightRelation : {}", rbacUserRightRelation);
    if (rbacUserRightRelation.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacUserRightRelation result = rbacUserRightRelationRepository.save(rbacUserRightRelation);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacUserRightRelation.getId().toString())).body(result);
}


@GetMapping("/rbac-user-right-relations")
@Timed
public ResponseEntity<List<RbacUserRightRelation>> getAllRbacUserRightRelations(Pageable pageable){
    log.debug("REST request to get a page of RbacUserRightRelations");
    Page<RbacUserRightRelation> page = rbacUserRightRelationRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-user-right-relations");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/rbac-user-right-relations/{id}")
@Timed
public ResponseEntity<RbacUserRightRelation> getRbacUserRightRelation(Long id){
    log.debug("REST request to get RbacUserRightRelation : {}", id);
    Optional<RbacUserRightRelation> rbacUserRightRelation = rbacUserRightRelationRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacUserRightRelation);
}


@DeleteMapping("/rbac-user-right-relations/{id}")
@Timed
public ResponseEntity<Void> deleteRbacUserRightRelation(Long id){
    log.debug("REST request to delete RbacUserRightRelation : {}", id);
    rbacUserRightRelationRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}