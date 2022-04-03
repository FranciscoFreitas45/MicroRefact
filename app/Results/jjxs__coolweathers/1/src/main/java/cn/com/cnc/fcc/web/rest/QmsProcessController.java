package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsProcess;
import cn.com.cnc.fcc.repository.QmsBomTechnologyRepository;
import cn.com.cnc.fcc.repository.QmsProcessRepository;
import cn.com.cnc.fcc.service.QmsProcessService;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.net.URISyntaxException;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/api")
public class QmsProcessController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

 private  EntityManagerFactory emf;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  QmsProcessService qmsProcessService;

@Resource
 private  QmsProcessRepository qmsProcessRepository;

@Resource
 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

public QmsProcessController(EntityManagerFactory emf) {
    this.emf = emf;
}
@GetMapping("/qms-processes/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("samecheck");
    // 如果这个值在数据库里找到了，则返回1
    List<QmsProcess> list = qmsProcessRepository.findByProcessCd(cd);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@RequestMapping("/qms-processes/upload")
public JSONObject upload(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    returnData = qmsProcessService.uploadData(files);
    return returnData;
}


@GetMapping("/qms-processes/getProcessInfo/index")
@Timed
public ResponseEntity<List<QmsProcess>> getgetProcessInfoForCodeName(HttpServletRequest request,Pageable pageable){
    // 模糊查询参数
    String processName = request.getParameter("processName");
    String processCd = request.getParameter("processCd");
    // 如果是null 就设置成空字符串
    if (processCd == null) {
        processCd = "";
    }
    // 如果是null 就设置成空字符串
    if (processName == null) {
        processName = "";
    }
    // 分页模糊查询
    Page<QmsProcess> QmsProcess = qmsProcessRepository.getRoleInfoForCodeName(pageable, processCd, processName);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(QmsProcess, "/api/qms-processes/getProcessInfo");
    // 返回值
    return new ResponseEntity<>(QmsProcess.getContent(), headers, HttpStatus.OK);
}


@GetMapping("/qms-processes/index")
@Timed
public ResponseEntity<List<QmsProcess>> getAllQmsProcess(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllQmsProcess");
    // 模糊查询供应商编码
    String bianMa = request.getParameter("bianMa");
    // 模糊查询供应商名称
    String gongName = request.getParameter("gongName");
    // 取得一览数据
    Page<QmsProcess> page = qmsProcessService.qmsProcessFindAll(bianMa, gongName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-processes-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-processes/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    // 如果这个值在数据库里找到了，则返回1
    List<QmsBomTechnology> list = qmsBomTechnologyRepository.findByProcessId(it);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}