package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.QmsBomTechnologyDTO;
import cn.com.cnc.fcc.service.QmsBomTechnologyService;
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
public class QmsBomTechnologyController {

 private  Logger log;

@Resource
 private  PageUtil pageUtil;

@Autowired
 private  QmsBomTechnologyService qmsBomTechnologyService;


@GetMapping("/qms-bom-technologies/popupIndex")
@Timed
public ResponseEntity<List<QmsBomTechnologyDTO>> getAllTechnology(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllProduct");
    String materielCdIn = request.getParameter("materielCdIn");
    String materielNameIn = request.getParameter("materielNameIn");
    String technologyNameIn = request.getParameter("technologyNameIn");
    List<QmsBomTechnologyDTO> QmsBomTechnologyDTO = qmsBomTechnologyService.qmsTechnologyFindAll(materielCdIn, materielNameIn, technologyNameIn);
    Page<QmsBomTechnologyDTO> page = pageUtil.listToPage(QmsBomTechnologyDTO, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-bom-technologies/popupData");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


}