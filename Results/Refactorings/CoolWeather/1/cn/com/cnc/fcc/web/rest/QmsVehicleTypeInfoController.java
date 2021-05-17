import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.QmsBom;
import cn.com.cnc.fcc.domain.QmsVehicleTypeClass;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import cn.com.cnc.fcc.repository.QmsBomRepository;
import cn.com.cnc.fcc.repository.QmsVehicleTypeClassRepository;
import cn.com.cnc.fcc.repository.QmsVehicleTypeInfoRepository;
import cn.com.cnc.fcc.service.QmsVehicleTypeInfoService;
import cn.com.cnc.fcc.service.dto.VehicleTypeInfoDTO;
import cn.com.cnc.fcc.service.impl.QmsVehicleTypeInfoServiceImpl;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.web.rest.errors.BadRequestAlertException;
import cn.com.cnc.fcc.web.rest.util.HeaderUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
@RestController
@RequestMapping("/api")
public class QmsVehicleTypeInfoController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

@Resource
 private  QmsBomRepository qmsBomRepository;

 private  QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository;

 private  QmsVehicleTypeInfoService qmsVehicleTypeInfoService;

 private  QmsVehicleTypeClassRepository qmsVehicleTypeClassRepository;

 private  QmsVehicleTypeInfoServiceImpl qmsVehicleTypeInfoServiceImpl;

@Resource
 private  PageUtil pageUtil;


@PostMapping("/qms-vehicle-type-infos/createInfo")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> createQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to save QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo);
    // 判断车型ID是否为空
    if (qmsVehicleTypeInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsVehicleTypeInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsVehicleTypeInfo qmsVehicleTypeInfoCheckType = new QmsVehicleTypeInfo();
    qmsVehicleTypeInfoCheckType = qmsVehicleTypeInfoRepository.findByVehicleTypeAndFlagStatus(qmsVehicleTypeInfo.getVehicleType(), "0");
    // 判断车型ID是否为空
    if (qmsVehicleTypeInfoCheckType != null) {
        throw new BadRequestAlertException("DataAlreadyID", ENTITY_NAME, "idexists");
    }
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    log.info("用户信息" + user);
    // 创建人重新赋值
    qmsVehicleTypeInfo.setMakeUser(user.getUsername());
    // 创建时间重新赋值
    qmsVehicleTypeInfo.setMakeTime(dateUtil.getDBNowDate());
    // 更新人重复值
    qmsVehicleTypeInfo.setModifyUser(user.getUsername());
    // 更新时间重新赋值
    qmsVehicleTypeInfo.setModifyTime(dateUtil.getDBNowDate());
    // 是否删除标志赋值：0 未删除 、1 删除
    qmsVehicleTypeInfo.setFlagStatus("0");
    // 车型数据插入
    QmsVehicleTypeInfo result = qmsVehicleTypeInfoRepository.save(qmsVehicleTypeInfo);
    // 返回信息
    return ResponseEntity.created(new URI("/api/qms-vehicle-type-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-vehicle-type-infos/indexInfo")
@Timed
public ResponseEntity<List<QmsVehicleTypeInfo>> getAllQmsVehicleTypeNewInfo(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsVehicleTypeInfos");
    // 模糊查询车型
    String carType = request.getParameter("carType");
    // 模糊查询车型名称
    String carTypeName = request.getParameter("carTypeName");
    // 取得一览数据
    Page<QmsVehicleTypeInfo> page = qmsVehicleTypeInfoService.qmsVehicleTypeFindAll(carType, carTypeName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vehicle-type-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/qms-vehicle-type-infos/deleteInfo")
@Timed
public Integer deleteQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to delete QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo.getId());
    // 实例化结果集
    List<QmsBom> qmsBom = new ArrayList<QmsBom>();
    Integer resultNumber = 0;
    // 取得车型质量控制点表中是否存在改车型数据
    qmsBom = qmsBomRepository.findByVehicleIdAndFlagStatus(Integer.valueOf(qmsVehicleTypeInfo.getId().toString()), "0");
    try {
        // 判断是否存在
        if (qmsBom.size() == 0) {
            // 删除数据
            resultNumber = qmsVehicleTypeInfoService.updateVehicleTypeFlag(qmsVehicleTypeInfo);
        } else {
            // 该车型仍在使用中
            resultNumber = 2;
        }
    } catch (Exception e) {
        // 打印日志
        log.info(e.getMessage());
    }
    // 返回结果
    return resultNumber;
}


@GetMapping("/qms-vehicle-type-classes/detaile/{id}")
@Timed
public ResponseEntity<QmsVehicleTypeClass> getQmsVehicleTypeClass(String id){
    log.debug("REST request to get QmsVehicleTypeClass : {}", id);
    Optional<QmsVehicleTypeClass> qmsVehicleTypeClass = qmsVehicleTypeClassRepository.findByVehicleClassAndFlagStatus(id, "0");
    List<QmsVehicleTypeClass> qmsVehicleTypeClassInfo = qmsVehicleTypeClassRepository.findByFlagStatusAndVehicleClass("0", id);
    if (qmsVehicleTypeClassInfo.size() == 0) {
        qmsVehicleTypeClass = Optional.ofNullable(new QmsVehicleTypeClass());
    }
    return ResponseUtil.wrapOrNotFound(qmsVehicleTypeClass);
}


@GetMapping("/qms-vehicle-type-infos/getVehicleTypePop")
@Timed
public ResponseEntity<List<VehicleTypeInfoDTO>> getVehicleTypePopInfo(HttpServletRequest request,Pageable pageable){
    // 模糊查询参数
    String vehicleType = request.getParameter("vehicleType");
    String vehicleTypeName = request.getParameter("vehicleTypeName");
    // 如果是null 就设置成空字符串
    if (vehicleType == null) {
        vehicleType = "";
    }
    // 如果是null 就设置成空字符串
    if (vehicleTypeName == null) {
        vehicleTypeName = "";
    }
    // 分页模糊查询
    List<VehicleTypeInfoDTO> vehicleTypeInfo = qmsVehicleTypeInfoServiceImpl.getVehicleTypePopInfo(vehicleType, vehicleTypeName);
    Page<VehicleTypeInfoDTO> page = pageUtil.listToPage(vehicleTypeInfo, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vehicle-type-infos/getVehicleTypePop");
    // 返回值
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-vehicle-type-infos/updateInfo")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> updateQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to update QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo);
    // 判断车型ID是否为空
    if (qmsVehicleTypeInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // 实例化结果集
    QmsVehicleTypeInfo result = new QmsVehicleTypeInfo();
    // 实例化结果集
    List<QmsVehicleTypeInfo> listInfo = new ArrayList<QmsVehicleTypeInfo>();
    // 实例化结果集
    QmsVehicleTypeInfo qmsVehicleTypeInfoCheckType = new QmsVehicleTypeInfo();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 车型更新排他
    listInfo = qmsVehicleTypeInfoRepository.findByIdAndFlagStatus(qmsVehicleTypeInfo.getId(), "0");
    // 取得该车型是否已存在
    qmsVehicleTypeInfoCheckType = qmsVehicleTypeInfoRepository.findByVehicleTypeAndFlagStatus(qmsVehicleTypeInfo.getVehicleType(), "0");
    // 判断是否渠道数据
    if (qmsVehicleTypeInfoCheckType != null) {
        // 判断是否为当前数据
        if (!qmsVehicleTypeInfoCheckType.getId().toString().equals(qmsVehicleTypeInfo.getId().toString())) {
            // 车型已被修改
            throw new BadRequestAlertException("DataAlreadyID", ENTITY_NAME, "dataModifiedInfo");
        }
    }
    // 判断该条数据是否已被更改
    if (listInfo.size() != 0) {
        if (dateUtil.compareByDataFormatterTo(qmsVehicleTypeInfo.getModifyTime(), listInfo.get(0).getModifyTime(), dataFormat)) {
            qmsVehicleTypeInfo.setModifyTime(dateUtil.getDBNowDate());
            qmsVehicleTypeInfo.setModifyUser(user.getUsername());
            // 更新车型信息
            result = qmsVehicleTypeInfoRepository.save(qmsVehicleTypeInfo);
        } else {
            // 车型已被修改
            throw new BadRequestAlertException("dataModified", ENTITY_NAME, "dataModifiedInfo");
        }
    } else {
        // 车型不存在
        throw new BadRequestAlertException("dataDoesNotExis", ENTITY_NAME, "dataDoesNotExisInfo");
    }
    // 返回信息
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsVehicleTypeInfo.getId().toString())).body(result);
}


}