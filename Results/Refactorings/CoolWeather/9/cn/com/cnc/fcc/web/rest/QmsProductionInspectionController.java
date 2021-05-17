import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsProductionInspection;
import cn.com.cnc.fcc.domain.QmsProductionInspectionValue;
import cn.com.cnc.fcc.repository.QmsBomTechnologyRepository;
import cn.com.cnc.fcc.repository.QmsProductionInspectionValueRepository;
import cn.com.cnc.fcc.service.QmsProductionInspectionService;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.service.dto.ProductProcessCheckDTO;
import cn.com.cnc.fcc.service.impl.QmsProductionInspectionImpl;
import cn.com.cnc.fcc.repository.QmsProductionInspectionRepository;
import cn.com.cnc.fcc.repository.QmsProductRepository;
import cn.com.cnc.fcc.domain.QmsProduct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@RestController
@RequestMapping("/api")
public class QmsProductionInspectionController {

 private  Logger log;

 private  String ENTITY_NAME;

 private  String dataFormat;

 private  EntityManagerFactory emf;

 private  QmsProductionInspectionImpl qmsProductionInspectionImpl;

@Resource
 private  QmsProductionInspectionValueRepository qmsProductionInspectionValueRepository;

 private  QmsProductionInspectionRepository qmsProductionInspectionRepository;

 private  QmsBomTechnologyRepository qmsBomTechnologyRepository;

 private  QmsProductRepository qmsProductRepository;

 private  QmsProductionInspectionService qmsProductionInspectionService;

@Resource
 private  PageUtil pageUtil;

@Resource
 private  DateUtil dateUtil;


@GetMapping("/productProcess/repeatCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String bomTechnologyId = request.getParameter("bomTechnologyId");
    Integer bti = Integer.valueOf(bomTechnologyId);
    String serialNumber = request.getParameter("serialNumber");
    // 如果这个值在数据库里找到了，则返回1
    List<QmsProductionInspection> list = qmsProductionInspectionRepository.findByBomTechnologyIdAndSerialNumber(bti, serialNumber);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@PostMapping("/productProcess/allCheck")
@Timed
@SuppressWarnings("unchecked")
public JSONObject checkProductionRelation(JSONObject jsonObject){
    JSONObject params = jsonObject.getJSONObject("params");
    JSONObject result = new JSONObject();
    result.put("code", "1");
    String materielId = params.getString("materielId");
    List<QmsBomTechnology> qmsBomTechnologyList = qmsBomTechnologyRepository.findByMaterielId(Integer.valueOf(materielId));
    // 工艺表不存在
    if (qmsBomTechnologyList.size() == 0) {
        result.put("code", "2");
    }
    return result;
}


@GetMapping("/productProcess/serialNumberCheck")
@Timed
@SuppressWarnings("unchecked")
public String serialNumberCheck(HttpServletRequest request){
    String result = "success";
    // 得到传过来的值
    String productNum = request.getParameter("serialNumber");
    String materielId = request.getParameter("materielId");
    Integer mid = Integer.valueOf(materielId);
    List<QmsProduct> list = qmsProductRepository.findByProductNumAndMaterielId(productNum, mid);
    if (list.size() != 0) {
        return result;
    } else {
        // session取得用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 取得用户信息
        UserDetails user = (UserDetails) authentication.getPrincipal();
        // 保存到产品表
        QmsProduct qmsProduct = new QmsProduct();
        qmsProduct.setProductNum(productNum);
        qmsProduct.setMaterielId(mid);
        qmsProduct.setMakeUser(user.getUsername());
        qmsProduct.setModifyUser(user.getUsername());
        qmsProduct.setMakeTime(dateUtil.getDBNowDate());
        qmsProduct.setModifyTime(dateUtil.getDBNowDate());
        // 新增一条到产品表
        qmsProductRepository.save(qmsProduct);
        return result;
    }
}


@GetMapping("/productProcess/{id}")
@Timed
public ResponseEntity<ProductProcessCheckDTO> getQmsProductionInspection(Long id){
    log.debug("REST request to get QmsProductionInspection : {}", id);
    ProductProcessCheckDTO qmsProductionInspection = qmsProductionInspectionImpl.findById(id);
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(id.toString()), "C");
    qmsProductionInspection.setCheckNumber(null);
    if (qmsProductionInspectionValueOptional.isPresent()) {
        qmsProductionInspection.setCheckNumber(qmsProductionInspectionValueOptional.get().getCheckNumber());
    }
    return ResponseEntity.ok().body(qmsProductionInspection);
}


@GetMapping("/productProcess/deleteChack")
@Timed
@SuppressWarnings("unchecked")
public JSONObject deleteChack(HttpServletRequest request){
    JSONObject returnData = new JSONObject();
    returnData.put("code", "1");
    // 生产检验ID
    String pid = request.getParameter("pid");
    // 返回0查验成功，返回1检验失败
    Integer piSize = qmsProductionInspectionValueRepository.findByInspectionId(Integer.valueOf(pid)).size();
    if (piSize > 0) {
        returnData.put("code", "0");
    }
    return returnData;
}


@GetMapping("/productProcess/productProcessFindAll")
@Timed
public ResponseEntity<List<ProductProcessCheckDTO>> getAllQmsProcess(HttpServletRequest request,Pageable pageable){
    // 取得一览数据
    List<ProductProcessCheckDTO> productProcessCheck = qmsProductionInspectionImpl.qmsProductProcessFindAll(request);
    Page<ProductProcessCheckDTO> page = pageUtil.listToPage(productProcessCheck, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qms-vehicle-type-infos/getVehicleTypePop");
    // 返回值
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@GetMapping("/productProcess/chackPreProcess")
@Timed
@SuppressWarnings("unchecked")
public JSONObject chackPreProcess(HttpServletRequest request){
    // 工艺Id
    String bomTechnologyId = request.getParameter("bomTechnologyId");
    // 编号
    String serialNumber = request.getParameter("serialNumber");
    // 返回0查验成功，返回1检验失败
    JSONObject result = qmsProductionInspectionService.chackPreProcess(bomTechnologyId, serialNumber);
    return result;
}


@PostMapping("/productProcess/doBatchGeneration")
@Timed
@SuppressWarnings("unchecked")
public JSONObject doBatchGeneration(QmsProductionInspection qmsProductionInspection){
    JSONObject returnData = new JSONObject();
    // 物料id
    Integer materId = qmsProductionInspection.getMaterielId();
    List<QmsBomTechnology> bomTechnologyList = qmsBomTechnologyRepository.findByMaterielIdAndIsDefaultAndFlagStatus(materId, "1", "0");
    String result = qmsProductionInspectionService.doBatchGeneration(qmsProductionInspection, bomTechnologyList);
    returnData.put("code", result);
    return returnData;
}


}