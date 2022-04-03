package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.QmsUnit;
import cn.com.cnc.fcc.service.util.DateUtil;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsControlDetails;
import cn.com.cnc.fcc.repository.QmsControlDetailsRepository;
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
public class QmsControlDetailsResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsControlDetailsRepository qmsControlDetailsRepository;

@Resource
 private  DateUtil dateUtil;

public QmsControlDetailsResource(QmsControlDetailsRepository qmsControlDetailsRepository) {
    this.qmsControlDetailsRepository = qmsControlDetailsRepository;
}
@DeleteMapping("/qms-control-details/{id}")
@Timed
public ResponseEntity<Void> deleteQmsControlDetails(Long id){
    log.debug("REST request to delete QmsControlDetails : {}", id);
    qmsControlDetailsRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-control-details")
@Timed
public ResponseEntity<QmsControlDetails> createQmsControlDetails(QmsControlDetails qmsControlDetails){
    log.debug("REST request to save QmsControlDetails : {}", qmsControlDetails);
    if (qmsControlDetails.getId() != null) {
        throw new BadRequestAlertException("A new qmsControlDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 如果传过来的值为null，则应该改为""
    if (qmsControlDetails.getInspectionItem() == null) {
        qmsControlDetails.setInspectionItem("");
    }
    if (qmsControlDetails.getTechnicalRequirement() == null) {
        qmsControlDetails.setTechnicalRequirement("");
    }
    if (qmsControlDetails.getInspectionInstrument() == null) {
        qmsControlDetails.setInspectionInstrument("");
    }
    if (qmsControlDetails.getCompPkid() == null) {
        qmsControlDetails.setCompPkid("");
    }
    if (qmsControlDetails.getRemark() == null) {
        qmsControlDetails.setRemark("");
    }
    if (qmsControlDetails.getReserveFirst() == null) {
        qmsControlDetails.setReserveFirst("");
    }
    if (qmsControlDetails.getReserveSecond() == null) {
        qmsControlDetails.setReserveSecond("");
    }
    if (qmsControlDetails.getReserveThird() == null) {
        qmsControlDetails.setReserveThird("");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsControlDetails.setMakeUser(user.getUsername());
    qmsControlDetails.setModifyUser(user.getUsername());
    qmsControlDetails.setMakeTime(dateUtil.getDBNowDate());
    qmsControlDetails.setModifyTime(dateUtil.getDBNowDate());
    QmsControlDetails result = qmsControlDetailsRepository.save(qmsControlDetails);
    return ResponseEntity.created(new URI("/api/qms-control-details/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-control-details/{id}")
@Timed
public ResponseEntity<QmsControlDetails> getQmsControlDetails(Long id){
    log.debug("REST request to get QmsControlDetails : {}", id);
    Optional<QmsControlDetails> qmsControlDetails = qmsControlDetailsRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsControlDetails);
}


@GetMapping("/qms-control-details")
@Timed
public ResponseEntity<List<QmsControlDetails>> getAllQmsControlDetails(Pageable pageable){
    log.debug("REST request to get a page of QmsControlDetails");
    Page<QmsControlDetails> page = qmsControlDetailsRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-control-details");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-control-details")
@Timed
public ResponseEntity<QmsControlDetails> updateQmsControlDetails(QmsControlDetails qmsControlDetails){
    log.debug("REST request to update QmsControlDetails : {}", qmsControlDetails);
    if (qmsControlDetails.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsControlDetails.setModifyUser(user.getUsername());
    qmsControlDetails.setModifyTime(dateUtil.getDBNowDate());
    QmsControlDetails result = qmsControlDetailsRepository.save(qmsControlDetails);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsControlDetails.getId().toString())).body(result);
}


}