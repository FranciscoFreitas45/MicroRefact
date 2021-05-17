import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacRight;
import cn.com.cnc.fcc.repository.RbacRightRepository;
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
public class RbacRightResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacRightRepository rbacRightRepository;


@DeleteMapping("/rbac-rights/{id}")
@Timed
public ResponseEntity<Void> deleteRbacRight(Long id){
    log.debug("REST request to delete RbacRight : {}", id);
    rbacRightRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/rbac-rights")
@Timed
public ResponseEntity<RbacRight> createRbacRight(RbacRight rbacRight){
    log.debug("REST request to save RbacRight : {}", rbacRight);
    if (rbacRight.getId() != null) {
        throw new BadRequestAlertException("A new rbacRight cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacRight result = rbacRightRepository.save(rbacRight);
    return ResponseEntity.created(new URI("/api/rbac-rights/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/rbac-rights")
@Timed
public ResponseEntity<RbacRight> updateRbacRight(RbacRight rbacRight){
    log.debug("REST request to update RbacRight : {}", rbacRight);
    if (rbacRight.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacRight result = rbacRightRepository.save(rbacRight);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacRight.getId().toString())).body(result);
}


@GetMapping("/rbac-rights")
@Timed
public ResponseEntity<List<RbacRight>> getAllRbacRights(Pageable pageable){
    log.debug("REST request to get a page of RbacRights");
    Page<RbacRight> page = rbacRightRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-rights");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/rbac-rights/{id}")
@Timed
public ResponseEntity<RbacRight> getRbacRight(Long id){
    log.debug("REST request to get RbacRight : {}", id);
    Optional<RbacRight> rbacRight = rbacRightRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacRight);
}


}