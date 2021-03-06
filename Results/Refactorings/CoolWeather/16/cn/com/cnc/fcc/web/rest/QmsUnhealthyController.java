import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsUnhealthy;
import cn.com.cnc.fcc.repository.QmsUnhealthyRepository;
import cn.com.cnc.fcc.service.QmsUnhealthyService;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.TreeCommon;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsUnhealthyController {

 private  Logger log;

 private  String ENTITY_NAME;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  QmsUnhealthyService qmsUnhealthyService;

@Autowired
 private  TreeCommon treeCommon;

 private  QmsUnhealthyRepository qmsUnhealthyRepository;


@GetMapping("/qms-unhealthies/detail/{id}")
@Timed
public ResponseEntity<QmsUnhealthy> getUnhealthiesInfoss(Long id){
    log.debug("REST request to get QmsOrganizationInfo : {}", id);
    Optional<QmsUnhealthy> qmsOrganizationInfo = qmsUnhealthyRepository.findById(id);
    // ????????????ID??????????????????
    List<QmsUnhealthy> qmsOrgan = qmsUnhealthyRepository.findByUnhealthyCdAndFlagStatus(qmsOrganizationInfo.get().getParentCd(), "0");
    if (qmsOrgan.size() != 0) {
        // ????????????
        qmsOrganizationInfo.get().setCompPkid(qmsOrgan.get(0).getUnhealthyName());
    }
    return ResponseUtil.wrapOrNotFound(qmsOrganizationInfo);
}


@PostMapping("/qms-unhealthies/deleteInfo/{id}")
@Timed
public Integer deleteUnhealthiesInfo(String id){
    // ?????????????????????
    Integer resultNumber = 0;
    try {
        // ????????????
        resultNumber = qmsUnhealthyService.deleteNodeInfos(id);
    } catch (Exception e) {
        // ????????????
        log.info(e.getMessage());
    }
    // ????????????
    return resultNumber;
}


@PutMapping("/qms-unhealthies/UpdateInfo")
@Timed
public ResponseEntity<QmsUnhealthy> updateUnhealthiesInfo(QmsUnhealthy qmsOrganizationInfo){
    log.debug("REST request to update QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session??????????????????
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // ??????????????????
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // ??????id?????????????????????
    List<QmsUnhealthy> isUserList = qmsUnhealthyRepository.findByIdAndFlagStatus(qmsOrganizationInfo.getId(), "0");
    // ?????????????????????
    if ("1".equals(isUserList.get(0).getIsUse()) && "0".equals(qmsOrganizationInfo.getIsUse())) {
        // ????????????????????????????????????
        List<QmsUnhealthy> checkIsUserInfo = qmsUnhealthyService.getCheckIsUser(qmsOrganizationInfo.getUnhealthyCd());
        if (checkIsUserInfo.size() != 0) {
            throw new BadRequestAlertException("subordinateIsUser", ENTITY_NAME, "idnull");
        }
    }
    // ????????????????????????0
    qmsOrganizationInfo.setFlagStatus("0");
    // ??????????????????
    qmsOrganizationInfo.setModifyTime(dateUtil.getDBNowDate());
    // ???????????????
    qmsOrganizationInfo.setModifyUser(user.getUsername());
    QmsUnhealthy result = qmsUnhealthyRepository.save(qmsOrganizationInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsOrganizationInfo.getId().toString())).body(result);
}


@RequestMapping("/qms-unhealthies/getAllList")
public HashMap<String,Object> getUnhealthiesTess(HashMap<String,Object> param){
    HashMap<String, Object> data = new HashMap<String, Object>();
    // ??????????????????????????????
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // ???????????????????????????
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoDTO>();
    // ???????????????????????????
    List<QmsOrganizationInfoDTO> parentNodeList = new ArrayList<QmsOrganizationInfoDTO>();
    // ????????????
    String organizationCd = param.get("organizationCd").toString();
    // ????????????
    String organizationName = param.get("organizationName").toString();
    // ?????????????????????
    parentNodeList = qmsUnhealthyService.getParentNodeListInfo(organizationCd, organizationName);
    // ??????????????????
    qmsOrganizationInfoDTOBack = qmsUnhealthyService.organListInfo();
    if (qmsOrganizationInfoDTOBack.size() > 0) {
        qmsOrganizationInfoDTO = treeCommon.TreeStructureUtil(qmsOrganizationInfoDTOBack, parentNodeList);
    }
    data.put("qmsOrganization", qmsOrganizationInfoDTO);
    return data;
}


@PostMapping("/qms-unhealthies/CreateInfo")
@Timed
public ResponseEntity<QmsUnhealthy> createUnhealthiesInfo(QmsUnhealthy qmsOrganizationInfo){
    log.debug("REST request to save QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsOrganizationInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // ??????????????????
    List<QmsUnhealthy> organizationInfo = new ArrayList<QmsUnhealthy>();
    // session??????????????????
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // ??????????????????
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // ??????????????????????????????
    if (!"".equals(qmsOrganizationInfo.getParentCd())) {
        List<QmsUnhealthy> qmsOrgan = qmsUnhealthyRepository.findByUnhealthyCdAndFlagStatus(qmsOrganizationInfo.getParentCd(), "0");
        // ??????????????????????????????
        if (qmsOrgan.size() == 0) {
            // ??????????????????????????????
            throw new BadRequestAlertException("noParentInfo", ENTITY_NAME, "idexists");
        }
    }
    // ??????????????????????????????
    organizationInfo = qmsUnhealthyRepository.findByUnhealthyCdAndFlagStatus(qmsOrganizationInfo.getUnhealthyCd(), "0");
    // ??????????????????????????????
    if (organizationInfo.size() > 0) {
        // ??????????????????????????????
        throw new BadRequestAlertException("codingDuplication", ENTITY_NAME, "idexists");
    }
    // ????????????????????????0
    qmsOrganizationInfo.setFlagStatus("0");
    // ???????????????
    qmsOrganizationInfo.setMakeUser(user.getUsername());
    // ??????????????????
    qmsOrganizationInfo.setMakeTime(dateUtil.getDBNowDate());
    // ???????????????
    qmsOrganizationInfo.setModifyUser(user.getUsername());
    // ??????????????????
    qmsOrganizationInfo.setModifyTime(dateUtil.getDBNowDate());
    QmsUnhealthy result = qmsUnhealthyRepository.save(qmsOrganizationInfo);
    return ResponseEntity.created(new URI("/api/qms-unhealthies/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}