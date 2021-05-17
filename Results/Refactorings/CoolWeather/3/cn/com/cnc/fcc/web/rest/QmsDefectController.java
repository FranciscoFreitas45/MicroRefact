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
import cn.com.cnc.fcc.domain.QmsDefect;
import cn.com.cnc.fcc.domain.QmsUnhealthy;
import cn.com.cnc.fcc.repository.QmsDefectRepository;
import cn.com.cnc.fcc.service.QmsDefectService;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.TreeCommon;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsDefectController {

 private  Logger log;

 private  String ENTITY_NAME;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  TreeCommon treeCommon;

@Autowired
 private  QmsDefectService qmsDefectService;

 private  QmsDefectRepository qmsDefectRepository;


@GetMapping("/qms-defects/detail/{id}")
@Timed
public ResponseEntity<QmsDefect> getUnhealthiesInfoss(Long id){
    log.debug("REST request to get QmsOrganizationInfo : {}", id);
    Optional<QmsDefect> qmsOrganizationInfo = qmsDefectRepository.findById(id);
    // 根据上级ID取得上级名字
    List<QmsDefect> qmsOrgan = qmsDefectRepository.findByDefectCdAndFlagStatus(qmsOrganizationInfo.get().getParentCd(), "0");
    if (qmsOrgan.size() != 0) {
        // 重新赋值
        qmsOrganizationInfo.get().setCompPkid(qmsOrgan.get(0).getDefectName());
    }
    return ResponseUtil.wrapOrNotFound(qmsOrganizationInfo);
}


@PostMapping("/qms-defects/deleteInfo/{id}")
@Timed
public Integer deleteUnhealthiesInfo(String id){
    // 实例化接受参数
    Integer resultNumber = 0;
    try {
        // 删除节点
        resultNumber = qmsDefectService.deleteNodeInfos(id);
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    }
    // 返回结果
    return resultNumber;
}


@PutMapping("/qms-defects/UpdateInfo")
@Timed
public ResponseEntity<QmsDefect> updateUnhealthiesInfo(QmsDefect qmsOrganizationInfo){
    log.debug("REST request to update QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 根据id取得原有效区分
    List<QmsDefect> isUserList = qmsDefectRepository.findByIdAndFlagStatus(qmsOrganizationInfo.getId(), "0");
    // 判断是否有改动
    if ("1".equals(isUserList.get(0).getIsUse()) && "0".equals(qmsOrganizationInfo.getIsUse())) {
        // 取得下级是否存在生效数据
        List<QmsDefect> checkIsUserInfo = qmsDefectService.getCheckIsUser(qmsOrganizationInfo.getDefectCd());
        if (checkIsUserInfo.size() != 0) {
            throw new BadRequestAlertException("subordinateIsUser", ENTITY_NAME, "idnull");
        }
    }
    // 删除标志初始赋值0
    qmsOrganizationInfo.setFlagStatus("0");
    // 创建时间赋值
    qmsOrganizationInfo.setModifyTime(dateUtil.getDBNowDate());
    // 更新人赋值
    qmsOrganizationInfo.setModifyUser(user.getUsername());
    QmsDefect result = qmsDefectRepository.save(qmsOrganizationInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsOrganizationInfo.getId().toString())).body(result);
}


@RequestMapping("/qms-defects/getAllList")
public HashMap<String,Object> getUnhealthiesTess(HashMap<String,Object> param){
    HashMap<String, Object> data = new HashMap<String, Object>();
    // 取得数据集合返回结果
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 实例化集合所有数据
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoDTO>();
    // 实例化集合父级节点
    List<QmsOrganizationInfoDTO> parentNodeList = new ArrayList<QmsOrganizationInfoDTO>();
    // 缺陷编码
    String defectCd = param.get("organizationCd").toString();
    // 缺陷名称
    String defectName = param.get("organizationName").toString();
    // 取得父节点数据
    parentNodeList = qmsDefectService.getParentNodeListInfo(defectCd, defectName);
    // 取得所有数据
    qmsOrganizationInfoDTOBack = qmsDefectService.organListInfo();
    if (qmsOrganizationInfoDTOBack.size() > 0) {
        qmsOrganizationInfoDTO = treeCommon.TreeStructureUtil(qmsOrganizationInfoDTOBack, parentNodeList);
    }
    data.put("qmsOrganization", qmsOrganizationInfoDTO);
    return data;
}


@PostMapping("/qms-defects/CreateInfo")
@Timed
public ResponseEntity<QmsDefect> createUnhealthiesInfo(QmsDefect qmsOrganizationInfo){
    log.debug("REST request to save QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsOrganizationInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 实例化结果集
    List<QmsDefect> organizationInfo = new ArrayList<QmsDefect>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 判断上级缺陷是否存在
    if (!"".equals(qmsOrganizationInfo.getParentCd())) {
        List<QmsDefect> qmsOrgan = qmsDefectRepository.findByDefectCdAndFlagStatus(qmsOrganizationInfo.getParentCd(), "0");
        // 判断缺陷编码是否重复
        if (qmsOrgan.size() == 0) {
            // 编码重复返回错误信息
            throw new BadRequestAlertException("noParentInfo", ENTITY_NAME, "idexists");
        }
    }
    // 根据缺陷编码取得数据
    organizationInfo = qmsDefectRepository.findByDefectCdAndFlagStatus(qmsOrganizationInfo.getDefectCd(), "0");
    // 判断缺陷编码是否重复
    if (organizationInfo.size() > 0) {
        // 编码重复返回错误信息
        throw new BadRequestAlertException("codingDuplication", ENTITY_NAME, "idexists");
    }
    // 删除标志初始赋值0
    qmsOrganizationInfo.setFlagStatus("0");
    // 创建人赋值
    qmsOrganizationInfo.setMakeUser(user.getUsername());
    // 创建时间赋值
    qmsOrganizationInfo.setMakeTime(dateUtil.getDBNowDate());
    // 更新人赋值
    qmsOrganizationInfo.setModifyUser(user.getUsername());
    // 更新时间赋值
    qmsOrganizationInfo.setModifyTime(dateUtil.getDBNowDate());
    QmsDefect result = qmsDefectRepository.save(qmsOrganizationInfo);
    return ResponseEntity.created(new URI("/api/qms-unhealthies/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


}