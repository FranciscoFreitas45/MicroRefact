import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsEnclosure;
import cn.com.cnc.fcc.domain.QmsProcess;
import cn.com.cnc.fcc.domain.QmsProductionInspection;
import cn.com.cnc.fcc.domain.QmsQualityControlDetails;
import cn.com.cnc.fcc.repository.QmsBomRepository;
import cn.com.cnc.fcc.repository.QmsBomTechnologyRepository;
import cn.com.cnc.fcc.repository.QmsEnclosureRepository;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.repository.QmsPartsAssemblyRelationRepository;
import cn.com.cnc.fcc.repository.QmsProcessRepository;
import cn.com.cnc.fcc.repository.QmsProductionInspectionRepository;
import cn.com.cnc.fcc.repository.QmsQualityControlDetailsRepository;
import cn.com.cnc.fcc.service.QmsProcessInformationsService;
import cn.com.cnc.fcc.service.dto.BomTechnologGroupDTO;
import cn.com.cnc.fcc.service.dto.DropDowmValueDTO;
import cn.com.cnc.fcc.service.dto.ProcessInfomationsRightListDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoDTO;
import cn.com.cnc.fcc.service.dto.QmsOrganizationInfoLeftDTO;
import cn.com.cnc.fcc.service.dto.QmsPartsAssemblyRelationOwnerDTO;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.TreeCommon;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsProcessInformationsController {

 private  Logger log;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  TreeCommon treeCommon;

@Autowired
 private  QmsProcessInformationsService qmsProcessInformationsService;

 private  QmsBomRepository qmsBomRepository;

 private  QmsMaterielRepository qmsMaterielRepository;

 private  QmsProcessRepository qmsProcessRepository;

 private  QmsProductionInspectionRepository qmsProductionInspectionRepository;

 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

 private  QmsQualityControlDetailsRepository qmsQualityControlDetailsRepository;

 private  QmsPartsAssemblyRelationRepository qmsPartsAssemblyRelationRepository;

 private  QmsEnclosureRepository qmsEnclosureRepository;


@GetMapping("/process-informations-infos/deleteFile")
public Boolean deleteEnclosure(Integer inspectionInfoId,String inspectionKbn,String enclosureAddress){
    Boolean result = qmsProcessInformationsService.deleteEnclosure(inspectionInfoId, inspectionKbn, enclosureAddress);
    return result;
}


@SuppressWarnings({ "rawtypes", "unchecked" })
@GetMapping("/process-informations-infos/getEnclosureList/{id}")
@Timed
public ResponseEntity<BomTechnologGroupDTO> getEnclosureList(Long id){
    // 实例化结果集
    BomTechnologGroupDTO bomTechnologGroupDTO = new BomTechnologGroupDTO();
    QmsBomTechnology qmsBomTechnology = qmsBomTechnologyRepository.findByIdAndFlagStatus(id, "0");
    List<QmsBomTechnology> qmsBomTechnologyList = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndFlagStatus(qmsBomTechnology.getMaterielId(), qmsBomTechnology.getTechnologyCd(), "0");
    List groutId = new ArrayList();
    for (int a = 0; a < qmsBomTechnologyList.size(); a++) {
        groutId.add(Integer.valueOf(qmsBomTechnologyList.get(a).getId().toString()));
    }
    // 实例化结果集：工艺文件
    List<QmsEnclosure> qmsEnclosure = new ArrayList<QmsEnclosure>();
    // 根据ID取得工艺文件信息
    qmsEnclosure = qmsEnclosureRepository.findByInspectionInfoIdIn(groutId);
    // 判断是否获取到值
    if (qmsEnclosure.size() != 0) {
        bomTechnologGroupDTO.setQmsEnclosure(qmsEnclosure);
    }
    // 返回值
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bomTechnologGroupDTO));
}


@GetMapping("/process-informations-infos/editInfo/{id}")
@Timed
public ResponseEntity<BomTechnologGroupDTO> getPurchaseOrder(Long id){
    // 实例化结果集
    BomTechnologGroupDTO bomTechnologGroupDTO = new BomTechnologGroupDTO();
    // 实例化结果集：基本信息
    QmsBomTechnology qmsBomTechnology = new QmsBomTechnology();
    // 实例化结果集：质量控制项点
    List<QmsQualityControlDetails> qmsQualityControlDetails = new ArrayList<QmsQualityControlDetails>();
    // 实例化结果集：装配关系
    List<QmsPartsAssemblyRelationOwnerDTO> qmsPartsAssemblyRelation = new ArrayList<QmsPartsAssemblyRelationOwnerDTO>();
    // 实例化结果集：工艺文件
    List<QmsEnclosure> qmsEnclosure = new ArrayList<QmsEnclosure>();
    // 根据ID取得基本信息
    qmsBomTechnology = qmsBomTechnologyRepository.findByIdAndFlagStatus(id, "0");
    // 判断是否取到信息
    if (null != qmsBomTechnology) {
        bomTechnologGroupDTO.setQmsBomTechnology(qmsBomTechnology);
    }
    // 根据ID取得质量控制项点信息
    qmsQualityControlDetails = qmsQualityControlDetailsRepository.findByBomTechnologyIdAndFlagStatus(Integer.valueOf(id.toString()), "0");
    // 判断是否获取到数据
    if (qmsQualityControlDetails.size() != 0) {
        bomTechnologGroupDTO.setQmsQualityControlDetails(qmsQualityControlDetails);
    }
    // 根据ID取得装配关系信息
    qmsPartsAssemblyRelation = qmsProcessInformationsService.getFlagStatusAndBomTechnologyId("0", Integer.valueOf(id.toString()));
    // 判断是否获取到值
    if (qmsPartsAssemblyRelation.size() != 0) {
        bomTechnologGroupDTO.setQmsPartsAssemblyRelation(qmsPartsAssemblyRelation);
    }
    // 根据ID取得工艺文件信息
    qmsEnclosure = qmsEnclosureRepository.findByInspectionInfoId(Integer.valueOf(id.toString()));
    // 判断是否获取到值
    if (qmsEnclosure.size() != 0) {
        bomTechnologGroupDTO.setQmsEnclosure(qmsEnclosure);
    }
    // 返回值
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bomTechnologGroupDTO));
}


@GetMapping("/process-informations-infos/selectExistenceInfo")
public QmsBomTechnology selectExistenceInfo(HttpServletRequest request){
    String masterCd = request.getParameter("masterCd");
    String technologyCd = request.getParameter("technologyCd");
    QmsBomTechnology qmsBomTechnology = new QmsBomTechnology();
    try {
        // 根据物料ID和工艺编码取值
        List<QmsBomTechnology> qmsBomTechnologyList = qmsBomTechnologyRepository.findByMaterielIdAndTechnologyCdAndFlagStatus(Integer.valueOf(masterCd), technologyCd, "0");
        if (qmsBomTechnologyList.size() != 0) {
            qmsBomTechnology = qmsBomTechnologyList.get(0);
        }
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    // 返回结果集
    return qmsBomTechnology;
}


@GetMapping("/process-informations-infos/detaileInfo")
public QmsProcess getQmsVehicleTypeClass(HttpServletRequest request){
    QmsProcess qmsVehicleTypeClass = new QmsProcess();
    List<QmsProcess> qmsVehicleTypeClassInfo = new ArrayList<QmsProcess>();
    String id = request.getParameter("orderNo");
    String processCd = request.getParameter("processCd");
    if (!"".equals(id)) {
        qmsVehicleTypeClass = qmsProcessRepository.findByFlagStatusAndId("0", Long.valueOf(id));
        qmsVehicleTypeClassInfo = qmsProcessRepository.findByIdAndFlagStatus(Long.valueOf(id), "0");
    } else if (!"".equals(processCd)) {
        qmsVehicleTypeClass = qmsProcessRepository.findByProcessCdAndFlagStatus(processCd, "0");
        qmsVehicleTypeClassInfo = qmsProcessRepository.findByFlagStatusAndProcessCd("0", processCd);
    }
    return qmsVehicleTypeClass;
}


@GetMapping("/process-informations-infos/getEnclosure/{id}")
@Timed
public ResponseEntity<BomTechnologGroupDTO> getEnclosure(Long id){
    // 实例化结果集
    BomTechnologGroupDTO bomTechnologGroupDTO = new BomTechnologGroupDTO();
    // 实例化结果集：工艺文件
    List<QmsEnclosure> qmsEnclosure = new ArrayList<QmsEnclosure>();
    // 根据ID取得工艺文件信息
    qmsEnclosure = qmsEnclosureRepository.findByInspectionInfoId(Integer.valueOf(id.toString()));
    // 判断是否获取到值
    if (qmsEnclosure.size() != 0) {
        bomTechnologGroupDTO.setQmsEnclosure(qmsEnclosure);
    }
    // 返回值
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bomTechnologGroupDTO));
}


@PostMapping("/process-informations-infos/deleteQualityControl")
@Timed
public Integer deleteQualityControl(QmsPartsAssemblyRelationOwnerDTO qmsPartsAssemblyRelationDTO){
    Integer resultInfo = 0;
    List<QmsProductionInspection> qmsProductionInspection = new ArrayList<QmsProductionInspection>();
    try {
        // 根据工艺id取得信息
        qmsProductionInspection = qmsProductionInspectionRepository.findByBomTechnologyIdAndFlagStatus(Integer.valueOf(qmsPartsAssemblyRelationDTO.getBomTechnologyId()), "0");
        // 判断信息是否已经存在
        if (qmsProductionInspection.size() == 0) {
            // 数据删除
            qmsQualityControlDetailsRepository.deleteById(qmsPartsAssemblyRelationDTO.getId());
        } else {
            resultInfo = 1;
        }
    } catch (Exception e) {
        resultInfo = 5;
        log.info(e.getMessage());
    }
    // 返回信息
    return resultInfo;
}


@DeleteMapping("/process-informations-infos/deleteAllInfo/{id}")
@Timed
@Transactional
public Integer deleteAllInfo(Long id){
    // 返回结果实例化
    Integer backInfo = 0;
    List<QmsProductionInspection> qmsProductionInspection = new ArrayList<QmsProductionInspection>();
    try {
        // 根据工艺id取得信息
        qmsProductionInspection = qmsProductionInspectionRepository.findByBomTechnologyIdAndFlagStatus(Integer.valueOf(id.toString()), "0");
        // 判断是否已存在
        if (qmsProductionInspection.size() == 0) {
            Integer qcd = qmsQualityControlDetailsRepository.deleteByBomTechnologyId(Integer.valueOf(id.toString()));
            if (qcd == 0) {
                // 返回1表示已存在
                backInfo = 2;
            // throw new RuntimeException("更新默认工艺失败");
            } else {
                // 返回1表示已存在
                backInfo = 0;
            }
            Integer qpa = qmsPartsAssemblyRelationRepository.deleteByBomTechnologyId(Integer.valueOf(id.toString()));
            if (qpa == 0) {
                // 返回1表示已存在
                backInfo = 3;
            // throw new RuntimeException("更新默认工艺失败");
            } else {
                // 返回1表示已存在
                backInfo = 0;
            }
            Integer qe = qmsEnclosureRepository.deleteByInspectionInfoId(Integer.valueOf(id.toString()));
            if (qe == 0) {
                // 返回1表示已存在
                backInfo = 4;
            // throw new RuntimeException("更新默认工艺失败");
            } else {
                // 返回1表示已存在
                backInfo = 0;
            }
            // 根据id查询判断
            qmsBomTechnologyRepository.deleteById(id);
        } else {
            // 返回1表示已存在
            backInfo = 1;
        }
    } catch (Exception e) {
        // 事物回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
    // 返回结果实例化
    return backInfo;
}


@GetMapping("/process-informations-infos/getAllWorkTeam")
public List<DropDowmValueDTO> getWorkTeamInfo(){
    // 实例化参数
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    // 取得信息
    dropDowmValueDTO = qmsProcessInformationsService.getWorkTeamInfo();
    // 返回结果集
    return dropDowmValueDTO;
}


@PostMapping("/process-informations-infos/deletePartsAssembly")
@Timed
public Integer deletePartsAssembly(QmsQualityControlDetails qmsQualityControlDetails){
    Integer resultInfo = 0;
    List<QmsProductionInspection> qmsProductionInspection = new ArrayList<QmsProductionInspection>();
    try {
        // 根据工艺id取得信息
        qmsProductionInspection = qmsProductionInspectionRepository.findByBomTechnologyIdAndFlagStatus(Integer.valueOf(qmsQualityControlDetails.getBomTechnologyId()), "0");
        // 判断信息是否已经存在
        if (qmsProductionInspection.size() == 0) {
            // 数据删除
            qmsPartsAssemblyRelationRepository.deleteById(qmsQualityControlDetails.getId());
        } else {
            resultInfo = 1;
        }
    } catch (Exception e) {
        resultInfo = 5;
        log.info(e.getMessage());
    }
    // 返回信息
    return resultInfo;
}


@GetMapping("/process-informations-infos/updateDefaultProcessInfo")
public Integer updateDefaultProcessInfo(HttpServletRequest request){
    String technologyCd = request.getParameter("technologyCd");
    String hiddenRightMaterielId = request.getParameter("hiddenRightMaterielId");
    Integer backInfo = 0;
    try {
        // 根据物料id取得信息
        backInfo = qmsProcessInformationsService.updateDefaultProcessInfo(Integer.valueOf(hiddenRightMaterielId), technologyCd);
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return backInfo;
}


@RequestMapping("/process-informations-infos/copyProcessInfo")
public HashMap<String,Object> copyProcessInfo(HashMap<String,Object> param){
    // 返回值设置
    HashMap<String, Object> backInfo = new HashMap<String, Object>();
    String technologyCd = param.get("technologyCd").toString();
    String technologyName = param.get("technologyName").toString();
    String hiddenRightMaterielId = param.get("hiddenRightMaterielId").toString();
    String copyTechnologyCd = param.get("copyTechnologyCd").toString();
    try {
        // 根据物料id取得信息
        backInfo = qmsProcessInformationsService.createCopyProcessInfo(Integer.valueOf(hiddenRightMaterielId), technologyCd, technologyName, copyTechnologyCd);
    } catch (Exception e) {
        backInfo.put("error", "更细失败");
        log.info(e.getMessage());
    }
    // 返回值
    return backInfo;
}


@GetMapping("/process-informations-infos/getAllSubordinateUnits")
public List<DropDowmValueDTO> getSubordinateUnitsInfo(){
    // 实例化参数
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    // 取得信息
    dropDowmValueDTO = qmsProcessInformationsService.getSubordinateUnitsInfo();
    // 返回结果集
    return dropDowmValueDTO;
}


@RequestMapping("/process-informations-infos/getAllList")
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
    parentNodeList = qmsProcessInformationsService.getParentNodeListInfo(materielCd, materielName);
    // 取得所有数据
    qmsOrganizationInfoDTOBack = qmsProcessInformationsService.organListInfo();
    if (qmsOrganizationInfoDTOBack.size() > 0) {
        qmsOrganizationInfoDTO = treeCommon.TreeStructureUtilBom(qmsOrganizationInfoDTOBack, parentNodeList);
    }
    data.put("qmsOrganization", qmsOrganizationInfoDTO);
    return data;
}


@GetMapping("/process-informations-infos/getAllInspectionRole")
public List<DropDowmValueDTO> getCarTypeInfo(){
    // 实例化参数
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    // 取得信息
    dropDowmValueDTO = qmsProcessInformationsService.getCarTypeInfo();
    // 返回结果集
    return dropDowmValueDTO;
}


@GetMapping("/process-informations-infos/getAllTechnology")
public List<DropDowmValueDTO> getAllTechnology(HttpServletRequest request){
    String masterCd = request.getParameter("masterCd");
    // 实例化参数
    List<DropDowmValueDTO> dropDowmValueDTO = new ArrayList<DropDowmValueDTO>();
    // 取得信息
    dropDowmValueDTO = qmsProcessInformationsService.getAllTechnology(masterCd);
    // 返回结果集
    return dropDowmValueDTO;
}


@PostMapping("/process-informations-infos/CreateInfo")
@Timed
public Integer createInfo(BomTechnologGroupDTO bomTechnologGroupDTO){
    // 返回结果
    Integer backResult = 0;
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO);
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsBomTechnology());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsEnclosure());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsPartsAssemblyRelation());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsQualityControlDetails());
    try {
        backResult = qmsProcessInformationsService.createInfo(bomTechnologGroupDTO);
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    // 返回结果
    return backResult;
}


@RequestMapping("/process-informations-infos/detail")
public HashMap<String,Object> getOrganizationInfoss(HashMap<String,Object> param){
    log.debug("REST request to get a page of QmsEntryControlDetails");
    HashMap<String, Object> data = new HashMap<String, Object>();
    // 取得所有数据
    List<ProcessInfomationsRightListDTO> page = qmsProcessInformationsService.selectAllInfo(param);
    if (page.size() != 0) {
        // 取得总条数
        List<ProcessInfomationsRightListDTO> countInfo = qmsProcessInformationsService.getAllInfoNumber(param);
        page.get(0).setNumberCount(countInfo.get(0).getNumberCount());
    }
    data.put("entryControlDtailsInfo", page);
    // 返回值
    return data;
}


@PostMapping("/process-informations-infos/UpdateInfo")
@Timed
public Integer UpdateInfo(BomTechnologGroupDTO bomTechnologGroupDTO){
    // 返回结果
    Integer backResult = 0;
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO);
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsBomTechnology());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsEnclosure());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsPartsAssemblyRelation());
    log.debug("前台传输过来的参数" + bomTechnologGroupDTO.getQmsQualityControlDetails());
    try {
        backResult = qmsProcessInformationsService.updateInfo(bomTechnologGroupDTO);
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    // 返回结果
    return backResult;
}


@GetMapping("/process-informations-infos/uploadFile")
public Boolean uploadEnclosure(HttpServletRequest request){
    Integer inspectionInfoId = Integer.valueOf(request.getParameter("inspectionInfoId"));
    String inspectionKbn = request.getParameter("inspectionKbn");
    String enclosureAddress = request.getParameter("enclosureAddress");
    Boolean result = qmsProcessInformationsService.uploadEnclosure(inspectionInfoId, inspectionKbn, enclosureAddress);
    return result;
}


}