import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository;
import cn.com.cnc.fcc.service.QmsQualityControlDetailsService;
import cn.com.cnc.fcc.service.dto.ProductProcessCheckDTO;
import cn.com.cnc.fcc.service.dto.QmsPartsAssemblyRelationDto;
import cn.com.cnc.fcc.service.dto.QmsQualityControlDetailsDto;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.PageUtil;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
public class QmsQualityControlDetailsController {

 private  Logger log;

 private  String ENTITY_NAME;

@Resource
 private  QmsEnclosureRepository qmsEnclosureRepository;

@Resource
 private  QmsQualityControlDetailsService qmsQualityControlDetailsService;

@Resource
 private  PageUtil pageUtil;

 private  QmsProductionInspectionResultRepository qmsProductionInspectionResultRepository;

 private  RbacUserRepository rbacUserRepository;

@Resource
 private  QmsProductionInspectionValueRepository qmsProductionInspectionValueRepository;

@Resource
 private  DateUtil dateUtil;


@PostMapping("/productProcessSelfCheck/createQmsProductionInspectionResult")
@Timed
@SuppressWarnings("unchecked")
public JSONObject createQmsProductionInspectionResult(JSONObject jsonObject){
    JSONObject returnData = new JSONObject();
    String code = qmsQualityControlDetailsService.checkProductionRelation(jsonObject);
    returnData.put("result", code);
    return returnData;
}


@GetMapping("/productProcessSelfCheck/editDistinguish")
@Timed
@SuppressWarnings("unchecked")
public JSONObject editDistinguish(HttpServletRequest request){
    JSONObject returnData = new JSONObject();
    // 1 新规标识
    returnData.put("code", 1);
    // 生产检验Id
    String inspectionId = request.getParameter("inspectionId");
    // 互检专区
    String inspectionDiff = request.getParameter("inspectionDiff");
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValue = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(inspectionId), inspectionDiff);
    // 0 更新标识
    if (qmsProductionInspectionValue.isPresent()) {
        returnData.put("code", 0);
    }
    return returnData;
}


@GetMapping("/productProcessSelfCheck/control_details")
@Timed
@SuppressWarnings("unchecked")
public List<QmsQualityControlDetailsDto> findQmsQualityControlDetailsByTechId(HttpServletRequest request){
    // 得到传过来的值
    String pid = request.getParameter("pid");
    // 是否是查看画面
    String isDetails = request.getParameter("isDetails");
    List<QmsQualityControlDetailsDto> result = qmsQualityControlDetailsService.findByBomTechnologyId(Integer.valueOf(pid), isDetails);
    // session取得用户信息
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // // 取得用户信息
    // UserDetails user = (UserDetails) authentication.getPrincipal();
    // List<RbacUser> userList = rbacUserRepository.findByUserCode(user.getUsername());
    // if (result.size()>0) {
    // result.get(0).setMakeUser(userList.get(0).getUserName());
    // }
    return result;
}


@GetMapping("/productProcessSelfCheck/obtainFile")
@Timed
@SuppressWarnings("unchecked")
public List<QmsEnclosure> saveAll(HttpServletRequest request){
    // 生产检验表Id
    String qmsProductionInspectionId = request.getParameter("qmsProductionInspectionId");
    List<QmsEnclosure> enclosureList = new ArrayList<>();
    Optional<QmsProductionInspectionValue> valueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(qmsProductionInspectionId), "C");
    if (valueOptional.isPresent()) {
        enclosureList = qmsEnclosureRepository.findAllByInspectionInfoIdAndInspectionKbn(Integer.valueOf(valueOptional.get().getId().toString()), "2");
    }
    return enclosureList;
}


@PostMapping("/productProcessSelfCheck/checkProductionRelation")
@Timed
@SuppressWarnings("unchecked")
public JSONObject checkProductionRelation(JSONObject jsonObject){
    JSONObject returnData = new JSONObject();
    String result = qmsQualityControlDetailsService.checkProductionRelation(jsonObject);
    returnData.put("result", result);
    return returnData;
}


@GetMapping("/productProcessSelfCheck/deleteAll")
@Timed
public JSONObject deleteAll(HttpServletRequest request){
    JSONObject result = new JSONObject();
    // 生产检验ID
    String pid = request.getParameter("pid");
    // 取得一览数据
    String code = qmsQualityControlDetailsService.deleteAll(pid);
    result.put("code", code);
    // 返回值
    return result;
}


@GetMapping("/productProcessSelfCheck/assembly_relation")
@Timed
@SuppressWarnings("unchecked")
public List<QmsPartsAssemblyRelationDto> findAssemblyRelationByTechId(HttpServletRequest request){
    // 得到传过来的值
    String pid = request.getParameter("pid");
    List<QmsPartsAssemblyRelationDto> result = qmsQualityControlDetailsService.findAssemblyRelationByPid(Integer.valueOf(pid));
    // // session取得用户信息
    // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // // 取得用户信息
    // UserDetails user = (UserDetails) authentication.getPrincipal();
    // 
    // if (result.size()>0) {
    // result.get(0).setMakeUser(user.getUsername());
    // }
    return result;
}


@GetMapping("/productProcessSelfCheck/{id}")
@Timed
public ResponseEntity<ProductProcessCheckDTO> getQmsProductionInspection(Long id){
    log.debug("REST request to get QmsProductionInspection : {}", id);
    ProductProcessCheckDTO qmsProductionInspection = qmsQualityControlDetailsService.findById(id);
    Optional<QmsProductionInspectionValue> qmsProductionInspectionValueOptional = qmsProductionInspectionValueRepository.findByInspectionIdAndInspectionDiff(Integer.valueOf(id.toString()), "C");
    qmsProductionInspection.setCheckNumber(null);
    if (qmsProductionInspectionValueOptional.isPresent()) {
        qmsProductionInspection.setCheckNumber(qmsProductionInspectionValueOptional.get().getCheckNumber());
    }
    return ResponseEntity.ok().body(qmsProductionInspection);
}


@GetMapping("/productProcessSelfCheck/productProcessSelfFindAll")
@Timed
public ResponseEntity<List<ProductProcessCheckDTO>> getAllQmsSelfProcess(HttpServletRequest request,Pageable pageable){
    // 取得一览数据
    List<ProductProcessCheckDTO> productProcessCheck = qmsQualityControlDetailsService.qmsProductProcessSelfFindAll(request);
    Page<ProductProcessCheckDTO> page = pageUtil.listToPage(productProcessCheck, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productProcessSelfCheck/productProcessSelfFindAll");
    // 返回值
    return ResponseEntity.ok().headers(headers).body(page.getContent());
}


@PostMapping("/productProcessSelfCheck/chackPreProcess")
@Timed
@SuppressWarnings("unchecked")
public JSONObject chackPreProcess(JSONObject jsonObject){
    // 返回0查验成功，返回1检验失败
    JSONObject result = qmsQualityControlDetailsService.chackPreProcess(jsonObject);
    return result;
}


@PostMapping("/productProcessSelfCheck/updateQmsProductionInspectionValues")
@Timed
@SuppressWarnings("unchecked")
public JSONObject updateQmsProductionInspectionValues(JSONObject jsonObject){
    JSONObject returnData = new JSONObject();
    // 生产检验Id
    String inspectionId = jsonObject.getString("inspectionId");
    // 合格区分
    String isOk = jsonObject.getString("isOk");
    // 互检专区
    String inspectionDiff = jsonObject.getString("inspectionDiff");
    // 检验编号
    String checkNumber = jsonObject.getString("checkNumber");
    String result = qmsQualityControlDetailsService.updateQmsProductionInspectionValues(inspectionId, checkNumber, isOk, inspectionDiff);
    returnData.put("result", result);
    return returnData;
}


@GetMapping("/productProcessSelfCheck/deleteCheck")
@Timed
public JSONObject deleteCheck(HttpServletRequest request){
    JSONObject result = new JSONObject();
    String pid = request.getParameter("pid");
    String code = qmsQualityControlDetailsService.deleteCheck(pid);
    result.put("code", code);
    return result;
}


}