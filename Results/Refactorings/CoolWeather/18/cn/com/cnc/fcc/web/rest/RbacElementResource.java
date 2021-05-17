import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacElement;
import cn.com.cnc.fcc.repository.RbacElementRepository;
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
public class RbacElementResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacElementRepository rbacElementRepository;


@GetMapping("/rbac-elements")
@Timed
public ResponseEntity<List<RbacElement>> getAllRbacElements(Pageable pageable){
    log.debug("REST request to get a page of RbacElements");
    Page<RbacElement> page = rbacElementRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-elements");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/rbac-elements")
@Timed
public ResponseEntity<RbacElement> updateRbacElement(RbacElement rbacElement){
    log.debug("REST request to update RbacElement : {}", rbacElement);
    if (rbacElement.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacElement result = rbacElementRepository.save(rbacElement);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacElement.getId().toString())).body(result);
}


@DeleteMapping("/rbac-elements/{id}")
@Timed
public ResponseEntity<Void> deleteRbacElement(Long id){
    log.debug("REST request to delete RbacElement : {}", id);
    rbacElementRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/rbac-elements/{id}")
@Timed
public ResponseEntity<RbacElement> getRbacElement(Long id){
    log.debug("REST request to get RbacElement : {}", id);
    Optional<RbacElement> rbacElement = rbacElementRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacElement);
}


@PostMapping("/rbac-elements")
@Timed
public ResponseEntity<RbacElement> createRbacElement(RbacElement rbacElement){
    log.debug("REST request to save RbacElement : {}", rbacElement);
    if (rbacElement.getId() != null) {
        throw new BadRequestAlertException("A new rbacElement cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacElement result = rbacElementRepository.save(rbacElement);
    return ResponseEntity.created(new URI("/api/rbac-elements/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}