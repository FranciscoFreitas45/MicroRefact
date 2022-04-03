package cn.com.cnc.fcc.web.rest;
 import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsOrganizationInfo;
import cn.com.cnc.fcc.repository.QmsOrganizationInfoRepository;
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
public class QmsOrganizationInfoResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsOrganizationInfoRepository qmsOrganizationInfoRepository;

public QmsOrganizationInfoResource(QmsOrganizationInfoRepository qmsOrganizationInfoRepository) {
    this.qmsOrganizationInfoRepository = qmsOrganizationInfoRepository;
}
@GetMapping("/qms-organization-infos")
@Timed
public ResponseEntity<List<QmsOrganizationInfo>> getAllQmsOrganizationInfos(Pageable pageable){
    log.debug("REST request to get a page of QmsOrganizationInfos");
    Page<QmsOrganizationInfo> page = qmsOrganizationInfoRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-organization-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-organization-infos")
@Timed
public ResponseEntity<QmsOrganizationInfo> updateQmsOrganizationInfo(QmsOrganizationInfo qmsOrganizationInfo){
    log.debug("REST request to update QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsOrganizationInfo result = qmsOrganizationInfoRepository.save(qmsOrganizationInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsOrganizationInfo.getId().toString())).body(result);
}


@DeleteMapping("/qms-organization-infos/{id}")
@Timed
public ResponseEntity<Void> deleteQmsOrganizationInfo(Long id){
    log.debug("REST request to delete QmsOrganizationInfo : {}", id);
    qmsOrganizationInfoRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-organization-infos")
@Timed
public ResponseEntity<QmsOrganizationInfo> createQmsOrganizationInfo(QmsOrganizationInfo qmsOrganizationInfo){
    log.debug("REST request to save QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsOrganizationInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsOrganizationInfo result = qmsOrganizationInfoRepository.save(qmsOrganizationInfo);
    return ResponseEntity.created(new URI("/api/qms-organization-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-organization-infos/{id}")
@Timed
public ResponseEntity<QmsOrganizationInfo> getQmsOrganizationInfo(Long id){
    log.debug("REST request to get QmsOrganizationInfo : {}", id);
    Optional<QmsOrganizationInfo> qmsOrganizationInfo = qmsOrganizationInfoRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsOrganizationInfo);
}


}