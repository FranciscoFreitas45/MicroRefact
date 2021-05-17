import cn.com.cnc.fcc.service.util.DateUtil;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsSupplierClass;
import cn.com.cnc.fcc.repository.QmsSupplierClassRepository;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class QmsSupplierClassResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsSupplierClassRepository qmsSupplierClassRepository;

@Resource
 private  DateUtil dateUtil;


@PutMapping("/qms-supplier-classes")
@Timed
public ResponseEntity<QmsSupplierClass> updateQmsSupplierClass(QmsSupplierClass qmsSupplierClass){
    log.debug("REST request to update QmsSupplierClass : {}", qmsSupplierClass);
    if (qmsSupplierClass.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsSupplierClass.setModifyUser(user.getUsername());
    qmsSupplierClass.setModifyTime(dateUtil.getDBNowDate());
    QmsSupplierClass result = qmsSupplierClassRepository.save(qmsSupplierClass);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsSupplierClass.getId().toString())).body(result);
}


@GetMapping("/qms-supplier-classes/{id}")
@Timed
public ResponseEntity<QmsSupplierClass> getQmsSupplierClass(Long id){
    log.debug("REST request to get QmsSupplierClass : {}", id);
    Optional<QmsSupplierClass> qmsSupplierClass = qmsSupplierClassRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsSupplierClass);
}


@PostMapping("/qms-supplier-classes")
@Timed
public ResponseEntity<QmsSupplierClass> createQmsSupplierClass(QmsSupplierClass qmsSupplierClass){
    log.debug("REST request to save QmsSupplierClass : {}", qmsSupplierClass);
    if (qmsSupplierClass.getId() != null) {
        throw new BadRequestAlertException("A new qmsSupplierClass cannot already have an ID", ENTITY_NAME, "idexists");
    }
    if (qmsSupplierClass.getSuppkierClassName() == null) {
        qmsSupplierClass.setSuppkierClassName("");
    }
    if (qmsSupplierClass.getRemark() == null) {
        qmsSupplierClass.setRemark("");
    }
    if (qmsSupplierClass.getCompPkid() == null) {
        qmsSupplierClass.setCompPkid("");
    }
    if (qmsSupplierClass.getReserveFirst() == null) {
        qmsSupplierClass.setReserveFirst("");
    }
    if (qmsSupplierClass.getReserveSecond() == null) {
        qmsSupplierClass.setReserveSecond("");
    }
    if (qmsSupplierClass.getReserveThird() == null) {
        qmsSupplierClass.setReserveThird("");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsSupplierClass.setMakeUser(user.getUsername());
    qmsSupplierClass.setModifyUser(user.getUsername());
    qmsSupplierClass.setMakeTime(dateUtil.getDBNowDate());
    qmsSupplierClass.setModifyTime(dateUtil.getDBNowDate());
    QmsSupplierClass result = qmsSupplierClassRepository.save(qmsSupplierClass);
    return ResponseEntity.created(new URI("/api/qms-supplier-classes/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-supplier-classes")
@Timed
public ResponseEntity<List<QmsSupplierClass>> getAllQmsSupplierClasses(Pageable pageable){
    log.debug("REST request to get a page of QmsSupplierClasses");
    Page<QmsSupplierClass> page = qmsSupplierClassRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-supplier-classes");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@DeleteMapping("/qms-supplier-classes/{id}")
@Timed
public ResponseEntity<Void> deleteQmsSupplierClass(Long id){
    log.debug("REST request to delete QmsSupplierClass : {}", id);
    qmsSupplierClassRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


}