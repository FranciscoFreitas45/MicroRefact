import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacRole;
import cn.com.cnc.fcc.repository.RbacRoleRepository;
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
public class RbacRoleResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacRoleRepository rbacRoleRepository;


@PostMapping("/rbac-roles")
@Timed
public ResponseEntity<RbacRole> createRbacRole(RbacRole rbacRole){
    log.debug("REST request to save RbacRole : {}", rbacRole);
    if (rbacRole.getId() != null) {
        throw new BadRequestAlertException("A new rbacRole cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacRole result = rbacRoleRepository.save(rbacRole);
    return ResponseEntity.created(new URI("/api/rbac-roles/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/rbac-roles")
@Timed
public ResponseEntity<RbacRole> updateRbacRole(RbacRole rbacRole){
    log.debug("REST request to update RbacRole : {}", rbacRole);
    if (rbacRole.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacRole result = rbacRoleRepository.save(rbacRole);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacRole.getId().toString())).body(result);
}


@DeleteMapping("/rbac-roles/{id}")
@Timed
public ResponseEntity<Void> deleteRbacRole(Long id){
    log.debug("REST request to delete RbacRole : {}", id);
    rbacRoleRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/rbac-roles")
@Timed
public ResponseEntity<List<RbacRole>> getAllRbacRoles(Pageable pageable){
    log.debug("REST request to get a page of RbacRoles");
    Page<RbacRole> page = rbacRoleRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-roles");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/rbac-roles/{id}")
@Timed
public ResponseEntity<RbacRole> getRbacRole(Long id){
    log.debug("REST request to get RbacRole : {}", id);
    Optional<RbacRole> rbacRole = rbacRoleRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacRole);
}


}