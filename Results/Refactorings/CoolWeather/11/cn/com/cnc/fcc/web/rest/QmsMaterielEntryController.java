import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository;
import cn.com.cnc.fcc.service.QmsMaterielEntryService;
import cn.com.cnc.fcc.service.dto.QmsMaterielEntryDto;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class QmsMaterielEntryController {

 private  Logger log;

 private  String ENTITY_NAME;

@Resource
 private  PageUtil pageUtil;

@Resource
 private  DateUtil dateUtil;

 private  QmsMaterielEntryRepository qmsMaterielEntryRepository;

 private  QmsMaterielDetailsRepository qmsMaterielDetailsRepository;

 private  QmsEnclosureRepository qmsEnclosureRepository;

 private  QmsMaterielEntryService qmsMaterielEntryService;

 private  RbacUserRightRelationRepository rbacUserRightRelationRepository;

 private  RbacRoleRepository rbacRoleRepository;


@DeleteMapping("/qms-materiel-entries/{id}/delete")
@Timed
public ResponseEntity<Void> deleteQmsMaterielEntry(Long id){
    log.debug("REST request to delete QmsMaterielEntry : {}", id);
    qmsMaterielDetailsRepository.deleteByEntryId(id.intValue());
    qmsMaterielEntryRepository.deleteById(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
}


@RequestMapping("/qms-materiel-entries/upload")
public JSONObject uploadInfo(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsMaterielEntryService.uploadData(files);
    return returnData;
}


@GetMapping("/qms-materiel-entries/editHeaderSearch")
@Timed
public JSONObject getEditLoad(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielEntries");
    JSONObject info = new JSONObject();
    String id = request.getParameter("id");
    info = qmsMaterielEntryService.getEditHeader(id);
    return info;
}


@GetMapping("/qms-materiel-entries/search")
@Timed
public ResponseEntity<List<QmsMaterielEntryDto>> getAllQmsMaterielEntries(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielEntries");
    // qmsMaterielEntryService.qmsMaterielFindAll();
    String materielCd = request.getParameter("materielCd");
    String materielName = request.getParameter("materielName");
    String figureNumber = request.getParameter("figureNumber");
    String supplierCd = request.getParameter("supplierCd");
    String supplierName = request.getParameter("supplierName");
    String specificationType = request.getParameter("specificationType");
    String purchaseOrderNumber = request.getParameter("purchaseOrderNumber");
    String flagInspect = request.getParameter("flagInspect");
    String enclosure = request.getParameter("enclosure");
    List<QmsMaterielEntryDto> qmsMaterielEntryDtos = qmsMaterielEntryService.qmsMaterielEntryFindAll(materielCd, materielName, figureNumber, supplierCd, supplierName, specificationType, purchaseOrderNumber, flagInspect, enclosure);
    Page<QmsMaterielEntryDto> page = pageUtil.listToPage(qmsMaterielEntryDtos, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-entries/search");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-materiel-entries/send")
@Timed
public JSONObject createQmsMaterielEntrySend(HttpServletRequest request){
    log.debug("REST request to save QmsEntryControlDetails : {} qmsEntryControlDetails");
    JSONObject result = new JSONObject();
    String id = request.getParameter("id");
    String userId = request.getParameter("userId");
    String userName = request.getParameter("userName");
    result.put("status", "1");
    try {
        Optional<QmsMaterielEntry> qmsMaterielEntry = qmsMaterielEntryRepository.findById(Long.parseLong(id));
        if (qmsMaterielEntry.isPresent()) {
            QmsMaterielEntry qmsMaterielEntryUpdate = qmsMaterielEntry.get();
            qmsMaterielEntryUpdate.setInspectionUserId(Integer.parseInt(userId));
            qmsMaterielEntryUpdate.setInspectionTime(dateUtil.getDBNowDate());
            qmsMaterielEntryRepository.save(qmsMaterielEntryUpdate);
        }
        QmsNotice qmsNotice = new QmsNotice();
        qmsNotice.setNoticeType("0");
        Optional<RbacUserRightRelation> rbacUserRightRelation = rbacUserRightRelationRepository.findByUserId(Integer.parseInt(userId));
        RbacUserRightRelation rbacUserRightRelationSearch;
        if (rbacUserRightRelation.isPresent()) {
            rbacUserRightRelationSearch = rbacUserRightRelation.get();
            Optional<RbacRole> rbacRole = rbacRoleRepository.findById(rbacUserRightRelationSearch.getRoleId().longValue());
            if (rbacRole.isPresent()) {
                RbacRole rbacRoleSearch = rbacRole.get();
                qmsNotice.setNoticeRole(rbacRoleSearch.getRoleName());
            }
        }
        qmsNotice.setNoticeUser(userName);
        qmsNotice.setNoticeInfo("");
        qmsNotice.setReserveFirst("入场报检");
    } catch (Exception e) {
        System.out.println(e);
        result.put("status", "2");
    } finally {
        return result;
    }
}


@GetMapping("/qms-materiel-entries/enclosureLoad")
@Timed
public List<QmsEnclosure> getEnclosureLoad(HttpServletRequest request){
    log.debug("REST request to get a page of QmsMaterielEntries");
    String id = request.getParameter("id");
    List<QmsEnclosure> qmsEnclosures = qmsEnclosureRepository.findAllByInspectionInfoIdAndInspectionKbn(Integer.parseInt(id), "1");
    return qmsEnclosures;
}


@PostMapping("/qms-materiel-entries/add")
@Timed
public JSONObject createQmsEntryControlDetails(JSONObject body){
    log.debug("REST request to save QmsEntryControlDetails : {} qmsEntryControlDetails");
    JSONObject result = new JSONObject();
    result.put("status", "1");
    try {
        result.put("status", "1");
        result = qmsMaterielEntryService.saveData(body);
    } catch (Exception e) {
        System.out.println(e);
        result.put("status", "2");
    } finally {
        return result;
    }
}


@GetMapping("/qms-materiel-entries/editDetailsSearch")
@Timed
public List<QmsMaterielDetails> getDetailsLoad(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsMaterielEntries");
    String id = request.getParameter("id");
    List<QmsMaterielDetails> qmsMaterielDetails = qmsMaterielDetailsRepository.findAllByEntryIdAndFlagStatus(Integer.parseInt(id), "0");
    return qmsMaterielDetails;
}


@GetMapping("/qms-materiel-entries/deletFlag")
@Timed
public JSONObject getDeleteCheck(HttpServletRequest request){
    log.debug("REST request to get a page of QmsMaterielEntries");
    JSONObject info = new JSONObject();
    Long id = Long.parseLong(request.getParameter("id"));
    Optional<QmsMaterielEntry> qmsMaterielEntryDto = qmsMaterielEntryRepository.findById(id);
    if (qmsMaterielEntryDto.isPresent()) {
        QmsMaterielEntry qmsMaterielEntry = qmsMaterielEntryDto.get();
        if (qmsMaterielEntry.getInspectionUserId() == null) {
            info.put("status", "1");
        } else {
            info.put("status", "2");
        }
    } else {
        info.put("status", "3");
    }
    return info;
}


}