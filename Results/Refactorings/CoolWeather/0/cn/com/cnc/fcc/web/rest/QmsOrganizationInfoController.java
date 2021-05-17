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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsOrganizationInfo;
import cn.com.cnc.fcc.repository.QmsOrganizationInfoRepository;
import cn.com.cnc.fcc.service.QmsOrganizationInfoService;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.TreeCommon;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsOrganizationInfoController {

 private  Logger log;

 private  QmsOrganizationInfoService qmsOrganizationInfoService;

 private  QmsOrganizationInfoRepository qmsOrganizationInfoRepository;

 private  String ENTITY_NAME;

@Resource
 private  DateUtil dateUtil;

 private  TreeCommon treeCommon;


@PutMapping("/qms-organization-infos/UpdateInfo")
@Timed
public ResponseEntity<QmsOrganizationInfo> updateOrganizationInfo(QmsOrganizationInfo qmsOrganizationInfo){
    log.debug("REST request to update QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 删除标志初始赋值0
    qmsOrganizationInfo.setFlagStatus("0");
    // 创建时间赋值
    qmsOrganizationInfo.setModifyTime(dateUtil.getDBNowDate());
    // 更新人赋值
    qmsOrganizationInfo.setModifyUser(user.getUsername());
    QmsOrganizationInfo result = qmsOrganizationInfoRepository.save(qmsOrganizationInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsOrganizationInfo.getId().toString())).body(result);
}


@RequestMapping("/qms-organization-infos/getAllList")
public HashMap<String,Object> getDictionaryTess(HashMap<String,Object> param){
    HashMap<String, Object> data = new HashMap<String, Object>();
    // 取得数据集合返回结果
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 实例化集合所有数据
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoDTO>();
    // 实例化集合父级节点
    List<QmsOrganizationInfoDTO> parentNodeList = new ArrayList<QmsOrganizationInfoDTO>();
    // 组织编码
    String organizationCd = param.get("organizationCd").toString();
    // 组织名称
    String organizationName = param.get("organizationName").toString();
    // 取得父节点数据
    parentNodeList = qmsOrganizationInfoService.getParentNodeListInfo(organizationCd, organizationName);
    // 取得所有数据
    qmsOrganizationInfoDTOBack = qmsOrganizationInfoService.organListInfo();
    if (qmsOrganizationInfoDTOBack.size() > 0) {
        qmsOrganizationInfoDTO = treeCommon.TreeStructureUtil(qmsOrganizationInfoDTOBack, parentNodeList);
    }
    data.put("qmsOrganization", qmsOrganizationInfoDTO);
    return data;
}


@PostMapping("/qms-organization-infos/deleteInfo/{id}")
@Timed
public Integer deleteOrganizationTypeInfo(String id){
    // 实例化接受参数
    Integer resultNumber = 0;
    try {
        // 删除节点
        resultNumber = qmsOrganizationInfoService.deleteNodeInfos(id);
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    }
    // 返回结果
    return resultNumber;
}


@PostMapping("/qms-organization-infos/CreateInfo")
@Timed
public ResponseEntity<QmsOrganizationInfo> createOrganizationInfo(QmsOrganizationInfo qmsOrganizationInfo){
    log.debug("REST request to save QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsOrganizationInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 实例化结果集
    List<QmsOrganizationInfo> organizationInfo = new ArrayList<QmsOrganizationInfo>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 判断上级缺陷是否存在
    if (!"".equals(qmsOrganizationInfo.getParentCd())) {
        List<QmsOrganizationInfo> qmsOrgan = qmsOrganizationInfoRepository.findByOrganizationCdAndFlagStatus(qmsOrganizationInfo.getParentCd(), "0");
        // 判断缺陷编码是否重复
        if (qmsOrgan.size() == 0) {
            // 编码重复返回错误信息
            throw new BadRequestAlertException("noParentInfo", ENTITY_NAME, "idexists");
        }
    }
    // 根据组织编码取得数据
    organizationInfo = qmsOrganizationInfoRepository.findByOrganizationCdAndFlagStatus(qmsOrganizationInfo.getOrganizationCd(), "0");
    // 判断组织编码是否重复
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
    QmsOrganizationInfo result = qmsOrganizationInfoRepository.save(qmsOrganizationInfo);
    return ResponseEntity.created(new URI("/api/qms-organization-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@RequestMapping("/qms-organization-infos/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsOrganizationInfoService.uploadUserDepart(files);
    return returnData;
}


@GetMapping("/qms-organization-infos/detail/{id}")
@Timed
public ResponseEntity<QmsOrganizationInfo> getOrganizationInfoss(Long id){
    log.debug("REST request to get QmsOrganizationInfo : {}", id);
    Optional<QmsOrganizationInfo> qmsOrganizationInfo = qmsOrganizationInfoRepository.findById(id);
    // 根据上级ID取得上级名字
    List<QmsOrganizationInfo> qmsOrgan = qmsOrganizationInfoRepository.findByOrganizationCdAndFlagStatus(qmsOrganizationInfo.get().getParentCd(), "0");
    if (qmsOrgan.size() != 0) {
        // 重新赋值
        qmsOrganizationInfo.get().setCompPkid(qmsOrgan.get(0).getOrganizationName());
    }
    return ResponseUtil.wrapOrNotFound(qmsOrganizationInfo);
}


}