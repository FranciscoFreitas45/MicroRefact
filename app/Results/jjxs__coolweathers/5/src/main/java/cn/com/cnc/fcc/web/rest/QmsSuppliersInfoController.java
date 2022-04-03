package cn.com.cnc.fcc.web.rest;
 import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.service.QmsSuppliersInfoService;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api")
public class QmsSuppliersInfoController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  QmsSuppliersInfoService qmsSuppliersInfoService;

@Resource
 private  QmsSupplierRepository qmsSupplierRepository;

@Resource
 private  QmsSupplierClassRepository qmsSupplierClassRepository;

@Resource
 private  QmsMaterielDetailsRepository qmsMaterielDetailsRepository;

@Resource
 private  QmsMaterielSupplierRepository qmsMaterielSupplierRepository;


@GetMapping("/qms-suppliers/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("samecheck");
    // 如果这个值在数据库里找到了，则返回1
    List<QmsSupplier> list = qmsSupplierRepository.findBySupplierCd(cd);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@RequestMapping("/qms-suppliers/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsSuppliersInfoService.uploadData(files);
    return returnData;
}


@GetMapping("/qms-suppliers/getsuppkierClassList")
@Timed
@SuppressWarnings("unchecked")
public List<QmsSupplierClass> getThisSuppkierClassList(HttpServletRequest request){
    String suppkierClass = request.getParameter("suppkierClass");
    List<QmsSupplierClass> list = qmsSupplierClassRepository.findBySuppkierClass(suppkierClass);
    return list;
}


@GetMapping("/qms-suppliers/supplier")
@Timed
@SuppressWarnings("unchecked")
public List<QmsSupplierClass> getSupplierClasslist(HttpServletRequest request){
    String data = request.getParameter("data");
    Long it = Long.valueOf(data);
    List<QmsSupplierClass> list = qmsSupplierClassRepository.findByIdAndFlagStatus(it, "0");
    return list;
}


@GetMapping("/qms-suppliers/index")
@Timed
public ResponseEntity<List<QmsSupplier>> getAllQmsSuppliersInfoInfo(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsVehicleTypeInfos");
    // 模糊查询供应商编码
    String bianMa = request.getParameter("bianMa");
    // 模糊查询供应商名称
    String gongName = request.getParameter("gongName");
    // 取得一览数据
    Page<QmsSupplier> page = qmsSuppliersInfoService.qmsSuppliersInfoFindAll(bianMa, gongName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-suppliers-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-suppliers/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    // 如果这个值在数据库里找到了，则返回1
    List<QmsMaterielSupplier> list1 = qmsMaterielSupplierRepository.findBySupplierId(it);
    // List<QmsMaterielDetails> list2 = qmsMaterielDetailsRepository.findBySupplierCd(cd);
    if (list1.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}