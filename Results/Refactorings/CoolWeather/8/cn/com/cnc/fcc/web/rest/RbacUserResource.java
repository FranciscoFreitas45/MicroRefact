import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.repository.RbacUserRepository;
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
public class RbacUserResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacUserRepository rbacUserRepository;


@PutMapping("/rbac-users")
@Timed
public ResponseEntity<RbacUser> updateRbacUser(RbacUser rbacUser){
    log.debug("REST request to update RbacUser : {}", rbacUser);
    if (rbacUser.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacUser result = rbacUserRepository.save(rbacUser);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacUser.getId().toString())).body(result);
}


@DeleteMapping("/rbac-users/{id}")
@Timed
public ResponseEntity<Void> deleteRbacUser(Long id){
    log.debug("REST request to delete RbacUser : {}", id);
    rbacUserRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/rbac-users/{id}")
@Timed
public ResponseEntity<RbacUser> getRbacUser(Long id){
    log.debug("REST request to get RbacUser : {}", id);
    Optional<RbacUser> rbacUser = rbacUserRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacUser);
}


@GetMapping("/rbac-users")
@Timed
public ResponseEntity<List<RbacUser>> getAllRbacUsers(Pageable pageable){
    log.debug("REST request to get a page of RbacUsers");
    Page<RbacUser> page = rbacUserRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-users");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/rbac-users")
@Timed
public ResponseEntity<RbacUser> createRbacUser(RbacUser rbacUser){
    log.debug("REST request to save RbacUser : {}", rbacUser);
    if (rbacUser.getId() != null) {
        throw new BadRequestAlertException("A new rbacUser cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacUser result = rbacUserRepository.save(rbacUser);
    return ResponseEntity.created(new URI("/api/rbac-users/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}