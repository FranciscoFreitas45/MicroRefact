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
import cn.com.cnc.fcc.domain.QmsBom;
import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import cn.com.cnc.fcc.repository.QmsBomRepository;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.repository.QmsVehicleTypeInfoRepository;
import cn.com.cnc.fcc.service.QmsBomTreeService;
import cn.com.cnc.fcc.service.dto.DropDowmValueDTO;
import cn.com.cnc.fcc.service.dto.QmsBomDetaileDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.TreeCommon;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsBomTreeController {

 private  Logger log;

@Autowired
 private  QmsBomTreeService qmsBomTreeService;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  TreeCommon treeCommon;

 private  String ENTITY_NAME;

 private  QmsBomRepository qmsBomRepository;

 private  QmsMaterielRepository qmsMaterielRepository;

 private  QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository;


@PutMapping("/qms-bom-infos/UpdateInfo")
@Timed
public ResponseEntity<QmsBom> updateOrganizationInfo(QmsBom qmsOrganizationInfo){
    log.debug("REST request to update QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // 实例化结果集
    List<QmsBom> organizationInfo = new ArrayList<QmsBom>();
    // 根据组织编码取得数据
    organizationInfo = qmsBomRepository.findByVehicleIdAndParentMaterielIDAndMaterielIdAndFlagStatusAndIdNot(qmsOrganizationInfo.getVehicleId(), qmsOrganizationInfo.getParentMaterielID(), qmsOrganizationInfo.getMaterielId(), "0", qmsOrganizationInfo.getId());
    // 判断组织编码是否重复
    if (organizationInfo.size() > 0) {
        // 编码重复返回错误信息
        throw new BadRequestAlertException("codingDuplication", ENTITY_NAME, "idexists");
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
    QmsBom result = qmsBomRepository.save(qmsOrganizationInfo);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsOrganizationInfo.getId().toString())).body(result);
}


@RequestMapping("/qms-bom-infos/getAllList")
public HashMap<String,Object> getDictionaryTess(HashMap<String,Object> param){
    HashMap<String, Object> data = new HashMap<String, Object>();
    // 取得数据集合返回结果
    List<QmsOrganizationInfoLeftDTO> qmsOrganizationInfoDTO = new ArrayList<QmsOrganizationInfoLeftDTO>();
    // 实例化集合所有数据
    List<QmsOrganizationInfoDTO> qmsOrganizationInfoDTOBack = new ArrayList<QmsOrganizationInfoDTO>();
    // 实例化集合父级节点
    List<QmsOrganizationInfoDTO> parentNodeList = new ArrayList<QmsOrganizationInfoDTO>();
    // 组织编码
    String materielCd = param.get("organizationCd").toString();
    // 组织名称
    String materielName = param.get("organizationName").toString();
    // 取得父节点数据
    parentNodeList = qmsBomTreeService.getParentNodeListInfo(materielCd, materielName);
    // 取得所有数据
    qmsOrganizationInfoDTOBack = qmsBomTreeService.organListInfo();
    if (qmsOrganizationInfoDTOBack.size() > 0) {
        qmsOrganizationInfoDTO = treeCommon.TreeStructureUtilBom(qmsOrganizationInfoDTOBack, parentNodeList);
    }
    data.put("qmsOrganization", qmsOrganizationInfoDTO);
    return data;
}


@GetMapping("/qms-bom-infos/deleteInfo")
public Integer deleteOrganizationTypeInfo(HttpServletRequest request){
    String id = request.getParameter("materielIdInfo");
    String hidVehicleType = request.getParameter("hidVehicleType");
    // 实例化接受参数
    Integer resultNumber = 0;
    try {
        // 删除节点
        resultNumber = qmsBomTreeService.deleteNodeInfos(id, hidVehicleType);
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    }
    // 返回结果
    return resultNumber;
}


@PostMapping("/qms-bom-infos/CreateInfo")
@Timed
public ResponseEntity<QmsBom> createOrganizationInfo(QmsBom qmsOrganizationInfo){
    log.debug("REST request to save QmsOrganizationInfo : {}", qmsOrganizationInfo);
    if (qmsOrganizationInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsOrganizationInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    // 实例化结果集
    List<QmsBom> organizationInfo = new ArrayList<QmsBom>();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 如果父级id为空判断
    if ("0".equals(qmsOrganizationInfo.getParentMaterielID()) || "".equals(qmsOrganizationInfo.getParentMaterielID()) || null == qmsOrganizationInfo.getParentMaterielID()) {
        // 根据组织编码取得数据
        organizationInfo = qmsBomRepository.findByVehicleIdAndMaterielIdAndParentMaterielIDAndFlagStatus(qmsOrganizationInfo.getVehicleId(), qmsOrganizationInfo.getMaterielId(), 0, "0");
        // 判断组织编码是否重复
        if (organizationInfo.size() > 0) {
            // 编码重复返回错误信息
            throw new BadRequestAlertException("codingDuplication", ENTITY_NAME, "idexists");
        }
    } else {
        // 根据组织编码取得数据
        organizationInfo = qmsBomRepository.findByVehicleIdAndParentMaterielIDAndMaterielIdAndFlagStatus(qmsOrganizationInfo.getVehicleId(), qmsOrganizationInfo.getParentMaterielID(), qmsOrganizationInfo.getMaterielId(), "0");
        // 判断组织编码是否重复
        if (organizationInfo.size() > 0) {
            // 编码重复返回错误信息
            throw new BadRequestAlertException("codingDuplication", ENTITY_NAME, "idexists");
        }
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
    QmsBom result = qmsBomRepository.save(qmsOrganizationInfo);
    return ResponseEntity.created(new URI("/api/qms-bom-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@RequestMapping("/qms-bom-infos/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsBomTreeService.uploadUserDepart(files);
    // 返回导入结果
    return returnData;
}


@GetMapping("/qms-bom-infos/carType/{id}")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> getQmsVehicleTypeClass(String id){
    log.debug("REST request to get QmsVehicleTypeClass : {}", id);
    Optional<QmsVehicleTypeInfo> qmsVehicleTypeClass = qmsVehicleTypeInfoRepository.findByFlagStatusAndVehicleTypeOrderById("0", id);
    List<QmsVehicleTypeInfo> qmsVehicleTypeClassInfo = qmsVehicleTypeInfoRepository.findByFlagStatusAndVehicleType("0", id);
    if (qmsVehicleTypeClassInfo.size() == 0) {
        qmsVehicleTypeClass = Optional.ofNullable(new QmsVehicleTypeInfo());
    }
    return ResponseUtil.wrapOrNotFound(qmsVehicleTypeClass);
}


@GetMapping("/qms-bom-infos/getAllCarType")
public List<DropDowmValueDTO> getCarTypeInfo(){
    // 实例化参数
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    // 取得信息
    dropDowmValueDTO = qmsBomTreeService.getCarTypeInfo();
    // 返回结果集
    return dropDowmValueDTO;
}


@GetMapping("/qms-bom-infos/materielName/{materielCd}")
@Timed
public ResponseEntity<QmsMateriel> getQmsMateriel(String materielCd){
    log.debug("REST request to get QmsMateriel : {}", materielCd);
    // 取得物料信息
    List<QmsMateriel> qmsMaterielList = qmsMaterielRepository.findByMaterielCdAndFlagStatus(materielCd, "0");
    // 实例化结果集
    QmsMateriel qmsMaterielInfo = new QmsMateriel();
    // 判断是否物料信息
    if (qmsMaterielList.size() != 0) {
        // 赋值
        qmsMaterielInfo = qmsMaterielList.get(0);
    }
    // 赋值
    Optional<QmsMateriel> qmsMateriel = Optional.ofNullable(qmsMaterielInfo);
    // 返回
    return ResponseUtil.wrapOrNotFound(qmsMateriel);
}


@GetMapping("/qms-bom-infos/detail/{id}")
@Timed
public ResponseEntity<QmsBomDetaileDTO> getOrganizationInfoss(Long id){
    log.debug("REST request to get QmsOrganizationInfo : {}", id);
    Optional<QmsBom> qmsOrganizationInfo = qmsBomRepository.findById(id);
    Optional<QmsBomDetaileDTO> qmsBomDetaileDTO = Optional.ofNullable(new QmsBomDetaileDTO());
    // 根据上级ID取得上级名字
    List<QmsMateriel> qmsOrgan = new ArrayList<QmsMateriel>();
    if (null != qmsOrganizationInfo.get().getParentMaterielID() && qmsOrganizationInfo.get().getParentMaterielID() != 0) {
        qmsOrgan = qmsMaterielRepository.findByIdAndFlagStatus(Long.valueOf(qmsOrganizationInfo.get().getParentMaterielID()), "0");
    }
    if (qmsOrgan.size() != 0) {
        // 重新赋值
        qmsBomDetaileDTO.get().setParentMaterielName(qmsOrgan.get(0).getMaterielName());
    }
    // 根据上级ID取得上级名字
    List<QmsMateriel> qmsOrgaName = qmsMaterielRepository.findByIdAndFlagStatus(Long.valueOf(qmsOrganizationInfo.get().getMaterielId()), "0");
    if (qmsOrgaName.size() != 0) {
        // 重新赋值
        qmsBomDetaileDTO.get().setMaterielName(qmsOrgaName.get(0).getMaterielName());
        qmsBomDetaileDTO.get().setmId(qmsOrgaName.get(0).getId());
        qmsBomDetaileDTO.get().setMaterielCd(qmsOrgaName.get(0).getMaterielCd());
    }
    List<QmsVehicleTypeInfo> qmsVehicleTypeInfo = qmsVehicleTypeInfoRepository.findByFlagStatusAndId("0", Long.valueOf(qmsOrganizationInfo.get().getVehicleId()));
    if (qmsVehicleTypeInfo.size() != 0) {
        qmsBomDetaileDTO.get().setVehicleId(qmsVehicleTypeInfo.get(0).getVehicleType());
        qmsBomDetaileDTO.get().setVehicleName(qmsVehicleTypeInfo.get(0).getVehicleTypeName());
    }
    // 重新赋值
    qmsBomDetaileDTO.get().setRootMaterielId(qmsOrganizationInfo.get().getRootMaterielId());
    qmsBomDetaileDTO.get().setMaterielId(qmsOrganizationInfo.get().getMaterielId());
    qmsBomDetaileDTO.get().setParentMaterielID(qmsOrganizationInfo.get().getParentMaterielID());
    qmsBomDetaileDTO.get().setRemark(qmsOrganizationInfo.get().getRemark());
    qmsBomDetaileDTO.get().setId(qmsOrganizationInfo.get().getId());
    qmsBomDetaileDTO.get().setMakeTime(qmsOrganizationInfo.get().getMakeTime());
    qmsBomDetaileDTO.get().setMakeUser(qmsOrganizationInfo.get().getMakeUser());
    qmsBomDetaileDTO.get().setvId(qmsOrganizationInfo.get().getVehicleId());
    // 返回值
    return ResponseUtil.wrapOrNotFound(qmsBomDetaileDTO);
}


}