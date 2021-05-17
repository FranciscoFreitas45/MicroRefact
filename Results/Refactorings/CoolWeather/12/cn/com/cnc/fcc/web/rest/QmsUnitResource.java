import cn.com.cnc.fcc.service.util.DateUtil;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsUnit;
import cn.com.cnc.fcc.repository.QmsUnitRepository;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
public class QmsUnitResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsUnitRepository qmsUnitRepository;

@Resource
 private  DateUtil dateUtil;


@PutMapping("/qms-units")
@Timed
public ResponseEntity<QmsUnit> updateQmsUnit(QmsUnit qmsUnit){
    log.debug("REST request to update QmsUnit : {}", qmsUnit);
    if (qmsUnit.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsUnit.setModifyUser(user.getUsername());
    qmsUnit.setModifyTime(dateUtil.getDBNowDate());
    QmsUnit result = qmsUnitRepository.save(qmsUnit);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsUnit.getId().toString())).body(result);
}


@DeleteMapping("/qms-units/{id}")
@Timed
public ResponseEntity<Void> deleteQmsUnit(Long id){
    log.debug("REST request to delete QmsUnit : {}", id);
    qmsUnitRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-units")
@Timed
public ResponseEntity<QmsUnit> createQmsUnit(QmsUnit qmsUnit){
    log.debug("REST request to save QmsUnit : {}", qmsUnit);
    if (qmsUnit.getId() != null) {
        throw new BadRequestAlertException("A new qmsUnit cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 如果传过来的值为null，则应该改为""
    if (qmsUnit.getUnitName() == null) {
        qmsUnit.setUnitName("");
    }
    if (qmsUnit.getCompPkid() == null) {
        qmsUnit.setCompPkid("");
    }
    if (qmsUnit.getRemark() == null) {
        qmsUnit.setRemark("");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsUnit.setMakeUser(user.getUsername());
    qmsUnit.setModifyUser(user.getUsername());
    qmsUnit.setMakeTime(dateUtil.getDBNowDate());
    qmsUnit.setModifyTime(dateUtil.getDBNowDate());
    QmsUnit result = qmsUnitRepository.save(qmsUnit);
    return ResponseEntity.created(new URI("/api/qms-units/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-units")
@Timed
public ResponseEntity<List<QmsUnit>> getAllQmsUnits(Pageable pageable){
    log.debug("REST request to get a page of QmsUnits");
    Page<QmsUnit> page = qmsUnitRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-units");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-units/{id}")
@Timed
public ResponseEntity<QmsUnit> getQmsUnit(Long id){
    log.debug("REST request to get QmsUnit : {}", id);
    Optional<QmsUnit> qmsUnit = qmsUnitRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsUnit);
}


}