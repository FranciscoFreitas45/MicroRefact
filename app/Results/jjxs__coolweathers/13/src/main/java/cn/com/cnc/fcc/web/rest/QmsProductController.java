package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository.QmsMaterielRepository;
import cn.com.cnc.fcc.repository.QmsProductRepository;
import cn.com.cnc.fcc.repository.QmsProductionInspectionRepository;
import cn.com.cnc.fcc.service.QmsProductService;
import cn.com.cnc.fcc.service.dto.ProductDTO;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/api")
public class QmsProductController {

 private  Logger log;

@Resource
 private  PageUtil pageUtil;

@Autowired
 private  QmsProductService qmsProductService;

@Autowired
 private  QmsMaterielRepository qmsMaterielRepository;

@Autowired
 private  QmsProductRepository qmsProductRepository;

@Autowired
 private  QmsProductionInspectionRepository qmsProductionInspectionRepository;


@GetMapping("/qms-products/selectTabDataTwo")
@Timed
public ResponseEntity<List<QmsProductRelationTabTwoDTO>> getAllTabDataTwo(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllProduct");
    String productRelation = request.getParameter("productRelation");
    List<QmsProductRelationTabTwoDTO> QmsProductRelationTabTwoDTO = qmsProductService.qmsProductFindTabDataTwo(productRelation);
    Page<QmsProductRelationTabTwoDTO> page = pageUtil.listToPage(QmsProductRelationTabTwoDTO, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-products/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-products/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String productNum = request.getParameter("productNum");
    String materielId = request.getParameter("materielId");
    Integer it = Integer.valueOf(materielId);
    // 如果这个值在数据库里找到了，则返回1
    List<QmsProduct> list = qmsProductRepository.findByProductNumAndMaterielId(productNum, it);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@GetMapping("/qms-products/materKeyUp")
@Timed
@SuppressWarnings("unchecked")
public List<QmsMateriel> getThisKeyUpMaterielList(HttpServletRequest request){
    String materielCd = request.getParameter("materielCd");
    List<QmsMateriel> list = qmsMaterielRepository.findByMaterielCdAndFlagStatus(materielCd, "0");
    return list;
}


@RequestMapping("/qms-products/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsProductService.uploadData(files);
    return returnData;
}


@GetMapping("/qms-products/index")
@Timed
public ResponseEntity<List<ProductDTO>> getAllProduct(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllProduct");
    String productNumIn = request.getParameter("productNumIn");
    String materielCdIn = request.getParameter("materielCdIn");
    String materielNameIn = request.getParameter("materielNameIn");
    String productBatchIn = request.getParameter("productBatchIn");
    String materielId = request.getParameter("materielId");
    String sp = request.getParameter("sp");
    List<ProductDTO> ProductDTO = qmsProductService.qmsProductFindAll(productNumIn, materielCdIn, materielNameIn, productBatchIn, materielId, sp);
    Page<ProductDTO> page = pageUtil.listToPage(ProductDTO, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-products/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-products/selectTabData")
@Timed
public ResponseEntity<List<QmsProductRelationTabDTO>> getAllTabData(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllProduct");
    String productRelation = request.getParameter("productRelation");
    List<QmsProductRelationTabDTO> QmsProductRelationTabDTO = qmsProductService.qmsProductFindTabData(productRelation);
    Page<QmsProductRelationTabDTO> page = pageUtil.listToPage(QmsProductRelationTabDTO, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-products/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-products/materCodeAndName")
@Timed
@SuppressWarnings("unchecked")
public List<QmsMateriel> getMaterielList(HttpServletRequest request){
    String data = request.getParameter("data");
    Long it = Long.valueOf(data);
    List<QmsMateriel> list = qmsMaterielRepository.findByIdAndFlagStatus(it, "0");
    return list;
}


@GetMapping("/qms-products/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    String productNum = request.getParameter("productNum");
    // 如果这个值在数据库里找到了，则返回1
    List<QmsProductionInspection> list = qmsProductionInspectionRepository.findByMaterielIdAndSerialNumber(it, productNum);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@GetMapping("/qms-products/selectTabDataThree")
@Timed
public ResponseEntity<List<QmsProductRelationTabThreeDTO>> getAllTabDataThree(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllProduct");
    String productRelation = request.getParameter("productRelation");
    List<QmsProductRelationTabThreeDTO> QmsProductRelationTabThreeDTO = qmsProductService.qmsProductFindTabDataThree(productRelation);
    Page<QmsProductRelationTabThreeDTO> page = pageUtil.listToPage(QmsProductRelationTabThreeDTO, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-products/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}