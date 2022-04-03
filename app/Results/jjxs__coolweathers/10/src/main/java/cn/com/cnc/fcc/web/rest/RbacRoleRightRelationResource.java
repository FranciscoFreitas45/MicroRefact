package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacRoleRightRelation;
import cn.com.cnc.fcc.repository.RbacRoleRightRelationRepository;
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
public class RbacRoleRightRelationResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacRoleRightRelationRepository rbacRoleRightRelationRepository;

public RbacRoleRightRelationResource(RbacRoleRightRelationRepository rbacRoleRightRelationRepository) {
    this.rbacRoleRightRelationRepository = rbacRoleRightRelationRepository;
}
@PutMapping("/rbac-role-right-relations")
@Timed
public ResponseEntity<RbacRoleRightRelation> updateRbacRoleRightRelation(RbacRoleRightRelation rbacRoleRightRelation){
    log.debug("REST request to update RbacRoleRightRelation : {}", rbacRoleRightRelation);
    if (rbacRoleRightRelation.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacRoleRightRelation result = rbacRoleRightRelationRepository.save(rbacRoleRightRelation);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacRoleRightRelation.getId().toString())).body(result);
}


@GetMapping("/rbac-role-right-relations")
@Timed
public ResponseEntity<List<RbacRoleRightRelation>> getAllRbacRoleRightRelations(Pageable pageable){
    log.debug("REST request to get a page of RbacRoleRightRelations");
    Page<RbacRoleRightRelation> page = rbacRoleRightRelationRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-role-right-relations");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/rbac-role-right-relations/{id}")
@Timed
public ResponseEntity<Void> deleteRbacRoleRightRelation(Long id){
    log.debug("REST request to delete RbacRoleRightRelation : {}", id);
    rbacRoleRightRelationRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/rbac-role-right-relations")
@Timed
public ResponseEntity<RbacRoleRightRelation> createRbacRoleRightRelation(RbacRoleRightRelation rbacRoleRightRelation){
    log.debug("REST request to save RbacRoleRightRelation : {}", rbacRoleRightRelation);
    if (rbacRoleRightRelation.getId() != null) {
        throw new BadRequestAlertException("A new rbacRoleRightRelation cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacRoleRightRelation result = rbacRoleRightRelationRepository.save(rbacRoleRightRelation);
    return ResponseEntity.created(new URI("/api/rbac-role-right-relations/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/rbac-role-right-relations/{id}")
@Timed
public ResponseEntity<RbacRoleRightRelation> getRbacRoleRightRelation(Long id){
    log.debug("REST request to get RbacRoleRightRelation : {}", id);
    Optional<RbacRoleRightRelation> rbacRoleRightRelation = rbacRoleRightRelationRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacRoleRightRelation);
}


}