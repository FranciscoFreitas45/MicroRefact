package cn.com.cnc.fcc.web.rest;
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

public QmsVehicleTypeInfoController(QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository, QmsVehicleTypeClassRepository qmsVehicleTypeClassRepository, QmsVehicleTypeInfoService qmsVehicleTypeInfoService, QmsVehicleTypeInfoServiceImpl qmsVehicleTypeInfoServiceImpl) {
    this.qmsVehicleTypeInfoRepository = qmsVehicleTypeInfoRepository;
    this.qmsVehicleTypeInfoService = qmsVehicleTypeInfoService;
    this.qmsVehicleTypeClassRepository = qmsVehicleTypeClassRepository;
    this.qmsVehicleTypeInfoServiceImpl = qmsVehicleTypeInfoServiceImpl;
}
@PostMapping("/qms-vehicle-type-infos/createInfo")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> createQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to save QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo);
    // ????????????ID????????????
    if (qmsVehicleTypeInfo.getId() != null) {
        throw new BadRequestAlertException("A new qmsVehicleTypeInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    QmsVehicleTypeInfo qmsVehicleTypeInfoCheckType = new QmsVehicleTypeInfo();
    qmsVehicleTypeInfoCheckType = qmsVehicleTypeInfoRepository.findByVehicleTypeAndFlagStatus(qmsVehicleTypeInfo.getVehicleType(), "0");
    // ????????????ID????????????
    if (qmsVehicleTypeInfoCheckType != null) {
        throw new BadRequestAlertException("DataAlreadyID", ENTITY_NAME, "idexists");
    }
    // session??????????????????
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // ??????????????????
    UserDetails user = (UserDetails) authentication.getPrincipal();
    log.info("????????????" + user);
    // ?????????????????????
    qmsVehicleTypeInfo.setMakeUser(user.getUsername());
    // ????????????????????????
    qmsVehicleTypeInfo.setMakeTime(dateUtil.getDBNowDate());
    // ??????????????????
    qmsVehicleTypeInfo.setModifyUser(user.getUsername());
    // ????????????????????????
    qmsVehicleTypeInfo.setModifyTime(dateUtil.getDBNowDate());
    // ???????????????????????????0 ????????? ???1 ??????
    qmsVehicleTypeInfo.setFlagStatus("0");
    // ??????????????????
    QmsVehicleTypeInfo result = qmsVehicleTypeInfoRepository.save(qmsVehicleTypeInfo);
    // ????????????
    return ResponseEntity.created(new URI("/api/qms-vehicle-type-infos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
}


@GetMapping("/qms-vehicle-type-infos/indexInfo")
@Timed
public ResponseEntity<List<QmsVehicleTypeInfo>> getAllQmsVehicleTypeNewInfo(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsVehicleTypeInfos");
    // ??????????????????
    String carType = request.getParameter("carType");
    // ????????????????????????
    String carTypeName = request.getParameter("carTypeName");
    // ??????????????????
    Page<QmsVehicleTypeInfo> page = qmsVehicleTypeInfoService.qmsVehicleTypeFindAll(carType, carTypeName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vehicle-type-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/qms-vehicle-type-infos/deleteInfo")
@Timed
public Integer deleteQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to delete QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo.getId());
    // ??????????????????
    List<QmsBom> qmsBom = new ArrayList<QmsBom>();
    Integer resultNumber = 0;
    // ????????????????????????????????????????????????????????????
    qmsBom = qmsBomRepository.findByVehicleIdAndFlagStatus(Integer.valueOf(qmsVehicleTypeInfo.getId().toString()), "0");
    try {
        // ??????????????????
        if (qmsBom.size() == 0) {
            // ????????????
            resultNumber = qmsVehicleTypeInfoService.updateVehicleTypeFlag(qmsVehicleTypeInfo);
        } else {
            // ????????????????????????
            resultNumber = 2;
        }
    } catch (Exception e) {
        // ????????????
        log.info(e.getMessage());
    }
    // ????????????
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
    // ??????????????????
    String vehicleType = request.getParameter("vehicleType");
    String vehicleTypeName = request.getParameter("vehicleTypeName");
    // ?????????null ????????????????????????
    if (vehicleType == null) {
        vehicleType = "";
    }
    // ?????????null ????????????????????????
    if (vehicleTypeName == null) {
        vehicleTypeName = "";
    }
    // ??????????????????
    List<VehicleTypeInfoDTO> vehicleTypeInfo = qmsVehicleTypeInfoServiceImpl.getVehicleTypePopInfo(vehicleType, vehicleTypeName);
    Page<VehicleTypeInfoDTO> page = pageUtil.listToPage(vehicleTypeInfo, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vehicle-type-infos/getVehicleTypePop");
    // ?????????
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PutMapping("/qms-vehicle-type-infos/updateInfo")
@Timed
public ResponseEntity<QmsVehicleTypeInfo> updateQmsVehicleTypeInfo(QmsVehicleTypeInfo qmsVehicleTypeInfo){
    log.debug("REST request to update QmsVehicleTypeInfo : {}", qmsVehicleTypeInfo);
    // ????????????ID????????????
    if (qmsVehicleTypeInfo.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    // ??????????????????
    QmsVehicleTypeInfo result = new QmsVehicleTypeInfo();
    // ??????????????????
    List<QmsVehicleTypeInfo> listInfo = new ArrayList<QmsVehicleTypeInfo>();
    // ??????????????????
    QmsVehicleTypeInfo qmsVehicleTypeInfoCheckType = new QmsVehicleTypeInfo();
    // session??????????????????
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // ??????????????????
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // ??????????????????
    listInfo = qmsVehicleTypeInfoRepository.findByIdAndFlagStatus(qmsVehicleTypeInfo.getId(), "0");
    // ??????????????????????????????
    qmsVehicleTypeInfoCheckType = qmsVehicleTypeInfoRepository.findByVehicleTypeAndFlagStatus(qmsVehicleTypeInfo.getVehicleType(), "0");
    // ????????????????????????
    if (qmsVehicleTypeInfoCheckType != null) {
        // ???????????????????????????
        if (!qmsVehicleTypeInfoCheckType.getId().toString().equals(qmsVehicleTypeInfo.getId().toString())) {
            // ??????????????????
            throw new BadRequestAlertException("DataAlreadyID", ENTITY_NAME, "dataModifiedInfo");
        }
    }
    // ????????????????????????????????????
    if (listInfo.size() != 0) {
        if (dateUtil.compareByDataFormatterTo(qmsVehicleTypeInfo.getModifyTime(), listInfo.get(0).getModifyTime(), dataFormat)) {
            qmsVehicleTypeInfo.setModifyTime(dateUtil.getDBNowDate());
            qmsVehicleTypeInfo.setModifyUser(user.getUsername());
            // ??????????????????
            result = qmsVehicleTypeInfoRepository.save(qmsVehicleTypeInfo);
        } else {
            // ??????????????????
            throw new BadRequestAlertException("dataModified", ENTITY_NAME, "dataModifiedInfo");
        }
    } else {
        // ???????????????
        throw new BadRequestAlertException("dataDoesNotExis", ENTITY_NAME, "dataDoesNotExisInfo");
    }
    // ????????????
    return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qmsVehicleTypeInfo.getId().toString())).body(result);
}


}