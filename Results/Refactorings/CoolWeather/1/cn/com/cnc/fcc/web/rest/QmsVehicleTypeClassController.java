import cn.com.cnc.fcc.domain.QmsVehicleTypeClass;
import cn.com.cnc.fcc.domain.QmsVehicleTypeInfo;
import cn.com.cnc.fcc.repository.QmsVehicleTypeClassRepository;
import cn.com.cnc.fcc.repository.QmsVehicleTypeInfoRepository;
import cn.com.cnc.fcc.service.QmsVehicTypeClassService;
import cn.com.cnc.fcc.service.util.DateUtil;
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
public class QmsVehicleTypeClassController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

@Autowired
 private  QmsVehicTypeClassService qmsVehicTypeClassService;

@Autowired
 private  QmsVehicleTypeInfoRepository qmsVehicleTypeInfoRepository;

@Autowired
 private  QmsVehicleTypeClassRepository qmsVehicleTypeClassRepository;


@GetMapping("/qms-vehicle-type-classes/index")
@Timed
public ResponseEntity<List<QmsVehicleTypeClass>> getAllQmsVtc(HttpServletRequest request,Pageable pageable){
    log.debug("REST request to get a page of getAllQmsVtc");
    // 模糊查询车型分类编码
    String vehicleClass = request.getParameter("vehicleClass");
    // 模糊查询车型分类名称
    String vehicleClassName = request.getParameter("vehicleClassName");
    // 取得一览数据
    Page<QmsVehicleTypeClass> page = qmsVehicTypeClassService.qmsVtcFindAll(vehicleClass, vehicleClassName, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vtc-infos");
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/qms-vehicle-type-classes/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("samecheck");
    // 如果这个值在数据库里找到了，则返回1
    List<QmsVehicleTypeClass> list = qmsVehicleTypeClassRepository.findByVehicleClass(cd);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@GetMapping("/qms-vehicle-type-classes/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    // 如果这个值在数据库里找到了，则返回1
    List<QmsVehicleTypeInfo> list1 = qmsVehicleTypeInfoRepository.findByVehicleClassId(it);
    if (list1.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}