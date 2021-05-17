import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository.RbacRoleRightRelationRepository;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.repository.RbacRightRepository;
import cn.com.cnc.fcc.service.RbacRightService;
import cn.com.cnc.fcc.service.dto.RbacRightDTO;
import cn.com.cnc.fcc.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import java.net.URISyntaxException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api")
public class RbacRightController {

 private  Logger log;

 private  RbacRightRepository rbacRightRepository;

 private  RbacRightService rbacRightService;

@Autowired
 private  RbacRoleRightRelationRepository rbacRoleRightRelationRepository;


@GetMapping("/rbac-menu-right-relations/RightMenuInfo/{rightId}")
@Timed
public List<RbacRightDTO> RightMenuInfo(Integer rightId){
    // 返回值
    return rbacRightService.getRightMenuInfo(rightId);
}


@GetMapping("/rbac-rights/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("samecheck");
    // 如果这个值在数据库里找到了，则返回1
    List<RbacRight> list = rbacRightRepository.findByRightCode(cd);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@PostMapping("/rbac-rights/updateRight")
@Timed
public Integer updateRight(RbacRightDTO rbacRightDTO){
    log.debug("前台传输过来的参数" + rbacRightDTO);
    log.debug("前台传输过来的参数" + rbacRightDTO.getMenuList());
    log.debug("前台传输过来的参数" + rbacRightDTO.getRbacRight());
    // 插入返回结果
    Integer updateRight = 0;
    try {
        updateRight = rbacRightService.updateRight(rbacRightDTO.getMenuList(), rbacRightDTO.getRbacRight());
    } catch (Exception e) {
    // TODO: handle exception
    }
    return updateRight;
}


@PostMapping("/rbac-rights/createRight")
@Timed
public Integer createRight(RbacRightDTO rbacRightDTO){
    log.debug("前台传输过来的参数" + rbacRightDTO);
    log.debug("前台传输过来的参数" + rbacRightDTO.getMenuList());
    log.debug("前台传输过来的参数" + rbacRightDTO.getRbacRight());
    // 插入返回结果
    Integer createRight = 0;
    try {
        createRight = rbacRightService.createRight(rbacRightDTO.getMenuList(), rbacRightDTO.getRbacRight());
    } catch (Exception e) {
    // TODO: handle exception
    }
    return createRight;
}


@GetMapping("/rbac-rights/getRightInfo")
@Timed
public ResponseEntity<List<RbacRight>> getRoleInfo(HttpServletRequest request,Pageable pageable){
    // 模糊查询参数
    String rightName = request.getParameter("rightName");
    String rightCode = request.getParameter("rightCode");
    // 分页模糊查询
    Page<RbacRight> rbacRight = rbacRightRepository.getRightInfo(pageable, rightName, rightCode);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(rbacRight, "/api/rbac-rights/getRightInfo");
    // 返回值
    return new ResponseEntity<>(rbacRight.getContent(), headers, HttpStatus.OK);
}


@GetMapping("/rbac-rights/getMenuInfo")
@Timed
public List<RbacMenu> getMenuInfo(){
    // 返回值
    return rbacRightService.getMenuInfo();
}


@GetMapping("/rbac-rights/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    // 如果这个值在数据库里找到了，则返回1
    List<RbacRoleRightRelation> list = rbacRoleRightRelationRepository.findByRightId(it);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}