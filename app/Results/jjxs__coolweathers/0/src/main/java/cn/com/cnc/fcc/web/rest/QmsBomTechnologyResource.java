package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.service.util.DateUtil;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.repository.QmsBomTechnologyRepository;
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
public class QmsBomTechnologyResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

@Resource
 private  DateUtil dateUtil;

public QmsBomTechnologyResource(QmsBomTechnologyRepository qmsBomTechnologyRepository) {
    this.qmsBomTechnologyRepository = qmsBomTechnologyRepository;
}
@DeleteMapping("/qms-bom-technologies/{id}")
@Timed
public ResponseEntity<Void> deleteQmsBomTechnology(Long id){
    log.debug("REST request to delete QmsBomTechnology : {}", id);
    qmsBomTechnologyRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-bom-technologies")
@Timed
public ResponseEntity<QmsBomTechnology> updateQmsBomTechnology(QmsBomTechnology qmsBomTechnology){
    log.debug("REST request to update QmsBomTechnology : {}", qmsBomTechnology);
    if (qmsBomTechnology.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsBomTechnology.setModifyUser(user.getUsername());
    qmsBomTechnology.setModifyTime(dateUtil.getDBNowDate());
    QmsBomTechnology result = qmsBomTechnologyRepository.save(qmsBomTechnology);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsBomTechnology.getId().toString())).body(result);
}


@PostMapping("/qms-bom-technologies")
@Timed
public ResponseEntity<QmsBomTechnology> createQmsBomTechnology(QmsBomTechnology qmsBomTechnology){
    log.debug("REST request to save QmsBomTechnology : {}", qmsBomTechnology);
    if (qmsBomTechnology.getId() != null) {
        throw new BadRequestAlertException("A new qmsBomTechnology cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 如果传过来的值为null，则应该改为""
    if (qmsBomTechnology.getCompPkid() == null) {
        qmsBomTechnology.setCompPkid("");
    }
    if (qmsBomTechnology.getRemark() == null) {
        qmsBomTechnology.setRemark("");
    }
    if (qmsBomTechnology.getReserveFirst() == null) {
        qmsBomTechnology.setReserveFirst("");
    }
    if (qmsBomTechnology.getReserveSecond() == null) {
        qmsBomTechnology.setReserveSecond("");
    }
    if (qmsBomTechnology.getReserveThird() == null) {
        qmsBomTechnology.setReserveThird("");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsBomTechnology.setMakeUser(user.getUsername());
    qmsBomTechnology.setModifyUser(user.getUsername());
    qmsBomTechnology.setMakeTime(dateUtil.getDBNowDate());
    qmsBomTechnology.setModifyTime(dateUtil.getDBNowDate());
    QmsBomTechnology result = qmsBomTechnologyRepository.save(qmsBomTechnology);
    return ResponseEntity.created(new URI("/api/qms-bom-technologies/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-bom-technologies")
@Timed
public ResponseEntity<List<QmsBomTechnology>> getAllQmsBomTechnologies(Pageable pageable){
    log.debug("REST request to get a page of QmsBomTechnologies");
    Page<QmsBomTechnology> page = qmsBomTechnologyRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-bom-technologies");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-bom-technologies/{id}")
@Timed
public ResponseEntity<QmsBomTechnology> getQmsBomTechnology(Long id){
    log.debug("REST request to get QmsBomTechnology : {}", id);
    Optional<QmsBomTechnology> qmsBomTechnology = qmsBomTechnologyRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsBomTechnology);
}


}