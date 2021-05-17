import cn.com.cnc.fcc.service.util.DateUtil;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
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
public class QmsMaterielResource {

 private  Logger log;

 private  String ENTITY_NAME;

 private  QmsMaterielRepository qmsMaterielRepository;

@Resource
 private  DateUtil dateUtil;


@PutMapping("/qms-materiels")
@Timed
public ResponseEntity<QmsMateriel> updateQmsMateriel(QmsMateriel qmsMateriel){
    log.debug("REST request to update QmsMateriel : {}", qmsMateriel);
    if (qmsMateriel.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsMateriel.setMakeUser(user.getUsername());
    qmsMateriel.setModifyUser(user.getUsername());
    qmsMateriel.setMakeTime(dateUtil.getDBNowDate());
    qmsMateriel.setModifyTime(dateUtil.getDBNowDate());
    QmsMateriel result = qmsMaterielRepository.save(qmsMateriel);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsMateriel.getId().toString())).body(result);
}


@DeleteMapping("/qms-materiels/{id}")
@Timed
public ResponseEntity<Void> deleteQmsMateriel(Long id){
    log.debug("REST request to delete QmsMateriel : {}", id);
    qmsMaterielRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@PostMapping("/qms-materiels")
@Timed
public ResponseEntity<QmsMateriel> createQmsMateriel(QmsMateriel qmsMateriel){
    log.debug("REST request to save QmsMateriel : {}", qmsMateriel);
    if (qmsMateriel.getId() != null) {
        throw new BadRequestAlertException("A new qmsMateriel cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 如果传过来的值为null，则应该改为""
    if (qmsMateriel.getMaterielName() == null) {
        qmsMateriel.setMaterielName("");
    }
    if (qmsMateriel.getFigureNumber() == null) {
        qmsMateriel.setFigureNumber("");
    }
    if (qmsMateriel.getInnerCd() == null) {
        qmsMateriel.setInnerCd("");
    }
    if (qmsMateriel.getAbcNumber() == null) {
        qmsMateriel.setAbcNumber("");
    }
    if (qmsMateriel.getProductMode() == null) {
        qmsMateriel.setProductMode("");
    }
    if (qmsMateriel.getPropertyType() == null) {
        qmsMateriel.setPropertyType("");
    }
    if (qmsMateriel.getSpecificationType() == null) {
        qmsMateriel.setSpecificationType("");
    }
    if (qmsMateriel.getOrganizationCd() == null) {
        qmsMateriel.setOrganizationCd("");
    }
    if (qmsMateriel.getInHouseType() == null) {
        qmsMateriel.setInHouseType("");
    }
    if (qmsMateriel.getQualityLevel() == null) {
        qmsMateriel.setQualityLevel("");
    }
    if (qmsMateriel.getTexTure() == null) {
        qmsMateriel.setTexTure("");
    }
    if (qmsMateriel.getEightPrevention() == null) {
        qmsMateriel.setEightPrevention("");
    }
    if (qmsMateriel.getIfKey() == null) {
        qmsMateriel.setIfKey("");
    }
    if (qmsMateriel.getUbiety() == null) {
        qmsMateriel.setUbiety("");
    }
    if (qmsMateriel.getSapCd() == null) {
        qmsMateriel.setSapCd("");
    }
    if (qmsMateriel.getIsCheck() == null) {
        qmsMateriel.setIsCheck("");
    }
    if (qmsMateriel.getCompPkid() == null) {
        qmsMateriel.setCompPkid("");
    }
    if (qmsMateriel.getRemark() == null) {
        qmsMateriel.setRemark("");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    qmsMateriel.setMakeUser(user.getUsername());
    qmsMateriel.setModifyUser(user.getUsername());
    qmsMateriel.setMakeTime(dateUtil.getDBNowDate());
    qmsMateriel.setModifyTime(dateUtil.getDBNowDate());
    QmsMateriel result = qmsMaterielRepository.save(qmsMateriel);
    return ResponseEntity.created(new URI("/api/qms-materiels/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-materiels/{id}")
@Timed
public ResponseEntity<QmsMateriel> getQmsMateriel(Long id){
    log.debug("REST request to get QmsMateriel : {}", id);
    Optional<QmsMateriel> qmsMateriel = qmsMaterielRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(qmsMateriel);
}


@GetMapping("/qms-materiels")
@Timed
public ResponseEntity<List<QmsMateriel>> getAllQmsMateriels(Pageable pageable){
    log.debug("REST request to get a page of QmsMateriels");
    Page<QmsMateriel> page = qmsMaterielRepository.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiels");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}