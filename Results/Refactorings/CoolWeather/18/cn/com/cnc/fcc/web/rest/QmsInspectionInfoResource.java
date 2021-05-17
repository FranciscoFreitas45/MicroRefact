import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsInspectionInfo;
import cn.com.cnc.fcc.repository.QmsInspectionInfoRepository;
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
public class QmsInspectionInfoResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsInspectionInfoRepository qmsInspectionInfoRepository;


@PostMapping("/qms-inspection-infos")
@Timed
public ResponseEntity<QmsInspectionInfo> createQmsInspectionInfo(QmsInspectionInfo qmsInspectionInfo){
    log.debug("REST request to save QmsInspectionInfo : {}", qmsInspectionInfo);
    if (qmsInspectionInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsInspectionInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsInspectionInfo result = qmsInspectionInfoRepository.save(qmsInspectionInfo);
    return ResponseEntity.created(new URI("/api/qms-inspection-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@DeleteMapping("/qms-inspection-infos/{id}")
@Timed
public ResponseEntity<Void> deleteQmsInspectionInfo(Long id){
    log.debug("REST request to delete QmsInspectionInfo : {}", id);
    qmsInspectionInfoRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-inspection-infos")
@Timed
public ResponseEntity<QmsInspectionInfo> updateQmsInspectionInfo(QmsInspectionInfo qmsInspectionInfo){
    log.debug("REST request to update QmsInspectionInfo : {}", qmsInspectionInfo);
    if (qmsInspectionInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsInspectionInfo result = qmsInspectionInfoRepository.save(qmsInspectionInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsInspectionInfo.getId().toString())).body(result);
}


@GetMapping("/qms-inspection-infos/{id}")
@Timed
public ResponseEntity<QmsInspectionInfo> getQmsInspectionInfo(Long id){
    log.debug("REST request to get QmsInspectionInfo : {}", id);
    Optional<QmsInspectionInfo> qmsInspectionInfo = qmsInspectionInfoRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsInspectionInfo);
}


@GetMapping("/qms-inspection-infos")
@Timed
public ResponseEntity<List<QmsInspectionInfo>> getAllQmsInspectionInfos(Pageable pageable){
    log.debug("REST request to get a page of QmsInspectionInfos");
    Page<QmsInspectionInfo> page = qmsInspectionInfoRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-inspection-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}