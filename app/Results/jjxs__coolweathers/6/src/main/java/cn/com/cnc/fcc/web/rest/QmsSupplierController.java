package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.QmsSupplier;
import cn.com.cnc.fcc.domain.QmsSupplierClass;
import cn.com.cnc.fcc.repository.QmsSupplierRepository;
import cn.com.cnc.fcc.service.QmsSupplierClassService;
import cn.com.cnc.fcc.service.QmsSuppliersInfoService;
import cn.com.cnc.fcc.service.dto.SupplierPopDto;
import cn.com.cnc.fcc.service.util.PageUtil;
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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@RequestMapping("/api")
public class QmsSupplierController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

@Resource
 private  PageUtil pageUtill;

@Autowired
 private  QmsSupplierClassService qmsSupplierClassService;

@Autowired
 private  QmsSupplierRepository qmsSupplierRepository;

@Autowired
 private  QmsSuppliersInfoService qmsSuppliersInfoService;


@GetMapping("/qms-suppliers/search")
@Timed
public ResponseEntity<List<SupplierPopDto>> getAllQmsSuppliers(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of QmsSuppliers");
    String supplierCd = request.getParameter("supplierCd");
    String supplierName = request.getParameter("supplierName");
    String materielId = "";
    if (request.getParameter("materielId") != null) {
        materielId = request.getParameter("materielId");
    }
    List<SupplierPopDto> qmsSuppliers = qmsSuppliersInfoService.findBySupplierCdAndSupplierName(supplierCd, supplierName, materielId);
    Page<SupplierPopDto> page = pageUtill.listToPage(qmsSuppliers, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-suppliers/search");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}