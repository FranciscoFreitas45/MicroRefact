import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import cn.com.cnc.fcc.domain.QmsMaterielType;
import cn.com.cnc.fcc.domain.materialTypeSelectionDto;
import cn.com.cnc.fcc.repository.QmsMaterielTypeRepository;
import cn.com.cnc.fcc.service.QmsMaterielTypeService;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.service.util.TreeCommon;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsMaterielTypeController {

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

@Resource
 private  PageUtil pageUtil;

@Autowired
 private  TreeCommon treeCommon;

@Autowired
 private  QmsMaterielTypeService qmsMaterielTypeService;

 private  String ENTITY_NAME;

 private  QmsMaterielTypeRepository qmsMaterielTypeRepository;


@GetMapping("/qms-materiel-types/popupData")
@Timed
public ResponseEntity<List<materialTypeSelectionDto>> getAllQmsMaterielTypes(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllQmsMaterielTypes");
    String bianMa = request.getParameter("bianMa");
    String gongName = request.getParameter("gongName");
    List<materialTypeSelectionDto> materialTypeSelectionDtos = qmsMaterielTypeService.qmsMaterialTypeFindAll(bianMa, gongName);
    Page<materialTypeSelectionDto> page = pageUtil.listToPage(materialTypeSelectionDtos, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-materiel-types/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-materiel-types/UpdateInfo")
@Timed
public ResponseEntity<QmsMaterielType> updateOrganizationInfo(QmsMaterielType qmsOrganizationInfo){
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
    QmsMaterielType result = qmsMaterielTypeRepository.save(qmsOrganizationInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsOrganizationInfo.getId().toString())).body(result);
}


@RequestMapping("/qms-materiel-types/getAllList")
public HashMap<String,Object> getDictionaryTess(HashMap<String,Object> param){
    HashMap<String, Object> data = new HashMap<String, Object>();
    // 取得数据集合返回结果
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 实例化集合所有数据
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoDTO>();
    // 实例化集合父级节点
    List<QmsOrganizationInfoDTO> parentNodeList = new ArrayList<QmsOrganizationInfoDTO>();
    // 物料分类编码
    String organizationCd = param.get("organizationCd").toString();
    // 物料分类名称
    String organizationName = param.get("organizationName").toString();
    // 取得父节点数据
    parentNodeList = qmsMaterielTypeService.getParentNodeListInfo(organizationCd, organizationName);
    // 取得所有数据
    qmsOrganizationInfoDTOBack = qmsMaterielTypeService.organListInfo();
    if (qmsOrganizationInfoDTOBack.size() > 0) {
        qmsOrganizationInfoDTO = treeCommon.TreeStructureUtil(qmsOrganizationInfoDTOBack, parentNodeList);
    }
    data.put("qmsOrganization", qmsOrganizationInfoDTO);
    return data;
}


@PostMapping("/qms-materiel-types/deleteInfo/{id}")
@Timed
public Integer deleteOrganizationTypeInfo(String id){
    // 实例化接受参数
    Integer resultNumber = 0;
    try {
        // 删除节点
        resultNumber = qmsMaterielTypeService.deleteNodeInfos(id);
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    }
    // 返回结果
    return resultNumber;
}


@PostMapping("/qms-materiel-types/CreateInfo")
@Timed
public ResponseEntity<QmsMaterielType> createOrganizationInfo(QmsMaterielType qmsOrganizationInfo){
    log.debug("REST request to save QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsOrganizationInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 实例化结果集
    List<QmsMaterielType> organizationInfo = new ArrayList<QmsMaterielType>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 判断上级缺陷是否存在
    if (!"".equals(qmsOrganizationInfo.getParentCd())) {
        List<QmsMaterielType> qmsOrgan = qmsMaterielTypeRepository.findByMaterielTypeCdAndFlagStatus(qmsOrganizationInfo.getParentCd(), "0");
        // 判断缺陷编码是否重复
        if (qmsOrgan.size() == 0) {
            // 编码重复返回错误信息
            throw new BadRequestAlertException("noParentInfo", ENTITY_NAME, "idexists");
        }
    }
    // 根据物料分类编码取得数据
    organizationInfo = qmsMaterielTypeRepository.findByMaterielTypeCdAndFlagStatus(qmsOrganizationInfo.getMaterielTypeCd(), "0");
    // 判断物料分类编码是否重复
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
    QmsMaterielType result = qmsMaterielTypeRepository.save(qmsOrganizationInfo);
    return ResponseEntity.created(new URI("/api/qms-organization-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@RequestMapping("/qms-materiel-types/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsMaterielTypeService.uploadUserDepart(files);
    return returnData;
}


@GetMapping("/qms-materiel-types/detail/{id}")
@Timed
public ResponseEntity<QmsMaterielType> getOrganizationInfoss(Long id){
    log.debug("REST request to get QmsOrganizationInfo : {}", id);
    Optional<QmsMaterielType> qmsOrganizationInfo = qmsMaterielTypeRepository.findById(id);
    // 根据上级ID取得上级名字
    List<QmsMaterielType> qmsOrgan = qmsMaterielTypeRepository.findByMaterielTypeCdAndFlagStatus(qmsOrganizationInfo.get().getParentCd(), "0");
    if (qmsOrgan.size() != 0) {
        // 重新赋值
        qmsOrganizationInfo.get().setCompPkid(qmsOrgan.get(0).getMaterielTypeName());
    }
    return ResponseUtil.wrapOrNotFound(qmsOrganizationInfo);
}


}