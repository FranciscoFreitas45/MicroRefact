package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.QmsMateriel;
import cn.com.cnc.fcc.domain.QmsSupplier;
import cn.com.cnc.fcc.domain.QmsSupplierClass;
import cn.com.cnc.fcc.domain.QmsUnit;
import cn.com.cnc.fcc.repository.QmsSupplierRepository;
import cn.com.cnc.fcc.repository.QmsUnitRepository;
import cn.com.cnc.fcc.service.QmsSupplierClassService;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
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
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/api")
public class QmsSupplierClassController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

@Autowired
 private  QmsSupplierClassService qmsSupplierClassService;

@Autowired
 private  QmsSupplierRepository qmsSupplierRepository;


@GetMapping("/qms-supplier-classes/index")
@Timed
public ResponseEntity<List<QmsSupplierClass>> getAllQmsSupplierClass(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllQmsUnit");
    // 模糊查询供应商分类编码
    String bianMa = request.getParameter("bianMa");
    // 模糊查询供应商分类名称
    String gongName = request.getParameter("gongName");
    // 取得一览数据
    Page<QmsSupplierClass> page = qmsSupplierClassService.qmsSupplierClassFindAll(bianMa, gongName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-supplierClasses-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-supplier-classes/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String id = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(id);
    // 如果这个值在数据库里找到了，则返回1
    List<QmsSupplier> list = qmsSupplierRepository.findBySupplierClassId(it);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}