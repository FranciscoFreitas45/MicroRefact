package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.RbacUser;
import cn.com.cnc.fcc.service.dto.UserDTO;
import cn.com.cnc.fcc.web.rest.errors.InternalServerErrorException;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacMenu;
import cn.com.cnc.fcc.repository.RbacMenuRepository;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@RestController
@RequestMapping("/api")
public class RbacMenuResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  RbacMenuRepository rbacMenuRepository;

 private  EntityManagerFactory emf;

public RbacMenuResource(RbacMenuRepository rbacMenuRepository, EntityManagerFactory emf) {
    this.rbacMenuRepository = rbacMenuRepository;
    this.emf = emf;
}
@DeleteMapping("/rbac-menus/{id}")
@Timed
public ResponseEntity<Void> deleteRbacMenu(Long id){
    log.debug("REST request to delete RbacMenu : {}", id);
    rbacMenuRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@GetMapping("/rbac-menus")
@Timed
public ResponseEntity<List<RbacMenu>> getAllRbacMenus(Pageable pageable){
    log.debug("REST request to get a page of RbacMenus");
    Page<RbacMenu> page = rbacMenuRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rbac-menus");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/rbac-menus/{id}")
@Timed
public ResponseEntity<RbacMenu> getRbacMenu(Long id){
    log.debug("REST request to get RbacMenu : {}", id);
    Optional<RbacMenu> rbacMenu = rbacMenuRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(rbacMenu);
}


@PostMapping("/rbac-menus")
@Timed
public ResponseEntity<RbacMenu> createRbacMenu(RbacMenu rbacMenu){
    log.debug("REST request to save RbacMenu : {}", rbacMenu);
    if (rbacMenu.getId() != null) {
        throw new BadRequestAlertException("A new rbacMenu cannot already have an ID", ENTITY_NAME, "idexists");
    }
    RbacMenu result = rbacMenuRepository.save(rbacMenu);
    return ResponseEntity.created(new URI("/api/rbac-menus/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@PutMapping("/rbac-menus")
@Timed
public ResponseEntity<RbacMenu> updateRbacMenu(RbacMenu rbacMenu){
    log.debug("REST request to update RbacMenu : {}", rbacMenu);
    if (rbacMenu.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    RbacMenu result = rbacMenuRepository.save(rbacMenu);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rbacMenu.getId().toString())).body(result);
}


}