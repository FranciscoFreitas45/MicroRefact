import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import cn.com.cnc.fcc.repository.QmsVehicleTypeInfoRepository;
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
public class QmsVehicleTypeInfoResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository;


@PostMapping("/qms-vehicle-type-infos")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> createQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to save QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo);
    if (qmsVehicleTypeInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsVehicleTypeInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsVehicleTypeInfo result = qmsVehicleTypeInfoRepository.save(qmsVehicleTypeInfo);
    return ResponseEntity.created(new URI("/api/qms-vehicle-type-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-vehicle-type-infos")
@Timed
public ResponseEntity<List<QmsVehicleTypeInfo>> getAllQmsVehicleTypeInfos(Pageable pageable){
    log.debug("REST request to get a page of QmsVehicleTypeInfos");
    Page<QmsVehicleTypeInfo> page = qmsVehicleTypeInfoRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vehicle-type-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-vehicle-type-infos/{id}")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> getQmsVehicleTypeInfo(Long id){
    log.debug("REST request to get QmsVehicleTypeInfo : {}", id);
    Optional<QmsVehicleTypeInfo> qmsVehicleTypeInfo = qmsVehicleTypeInfoRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsVehicleTypeInfo);
}


@DeleteMapping("/qms-vehicle-type-infos/{id}")
@Timed
public ResponseEntity<Void> deleteQmsVehicleTypeInfo(Long id){
    log.debug("REST request to delete QmsVehicleTypeInfo : {}", id);
    qmsVehicleTypeInfoRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PutMapping("/qms-vehicle-type-infos")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> updateQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to update QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo);
    if (qmsVehicleTypeInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QmsVehicleTypeInfo result = qmsVehicleTypeInfoRepository.save(qmsVehicleTypeInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsVehicleTypeInfo.getId().toString())).body(result);
}


}