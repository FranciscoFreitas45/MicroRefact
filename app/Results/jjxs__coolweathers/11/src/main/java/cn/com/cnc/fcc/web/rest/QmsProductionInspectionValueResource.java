package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.service.util.DateUtil;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsProductionInspectionValue;
import cn.com.cnc.fcc.repository.QmsProductionInspectionValueRepository;
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
public class QmsProductionInspectionValueResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsProductionInspectionValueRepository qmsProductionInspectionValueRepository;

@Resource
 private  DateUtil dateUtil;

public QmsProductionInspectionValueResource(QmsProductionInspectionValueRepository qmsProductionInspectionValueRepository) {
    this.qmsProductionInspectionValueRepository = qmsProductionInspectionValueRepository;
}
@PutMapping("/qms-production-inspection-values")
@Timed
public ResponseEntity<QmsProductionInspectionValue> updateQmsProductionInspectionValue(QmsProductionInspectionValue qmsProductionInspectionValue){
    log.debug("REST request to update QmsProductionInspectionValue : {}", qmsProductionInspectionValue);
    if (qmsProductionInspectionValue.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsProductionInspectionValue result = qmsProductionInspectionValueRepository.save(qmsProductionInspectionValue);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsProductionInspectionValue.getId().toString())).body(result);
}


@DeleteMapping("/qms-production-inspection-values/{id}")
@Timed
public ResponseEntity<Void> deleteQmsProductionInspectionValue(Long id){
    log.debug("REST request to delete QmsProductionInspectionValue : {}", id);
    qmsProductionInspectionValueRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-production-inspection-values")
@Timed
public ResponseEntity<QmsProductionInspectionValue> createQmsProductionInspectionValue(QmsProductionInspectionValue qmsProductionInspectionValue){
    log.debug("REST request to save QmsProductionInspectionValue : {}", qmsProductionInspectionValue);
    if (qmsProductionInspectionValue.getId() != null) {
        throw new BadRequestAlertException("A new qmsProductionInspectionValue cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsProductionInspectionValue.setMakeUser(user.getUsername());
    qmsProductionInspectionValue.setModifyUser(user.getUsername());
    qmsProductionInspectionValue.setMakeTime(dateUtil.getDBNowDate());
    qmsProductionInspectionValue.setModifyTime(dateUtil.getDBNowDate());
    QmsProductionInspectionValue result = qmsProductionInspectionValueRepository.save(qmsProductionInspectionValue);
    return ResponseEntity.created(new URI("/api/qms-production-inspection-values/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-production-inspection-values/{id}")
@Timed
public ResponseEntity<QmsProductionInspectionValue> getQmsProductionInspectionValue(Long id){
    log.debug("REST request to get QmsProductionInspectionValue : {}", id);
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValue = qmsProductionInspectionValueRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsProductionInspectionValue);
}


@GetMapping("/qms-production-inspection-values")
@Timed
public ResponseEntity<List<QmsProductionInspectionValue>> getAllQmsProductionInspectionValues(Pageable pageable){
    log.debug("REST request to get a page of QmsProductionInspectionValues");
    Page<QmsProductionInspectionValue> page = qmsProductionInspectionValueRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-production-inspection-values");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}