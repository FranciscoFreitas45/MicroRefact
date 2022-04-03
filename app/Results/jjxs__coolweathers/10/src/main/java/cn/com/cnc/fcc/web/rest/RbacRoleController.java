package cn.com.cnc.fcc.web.rest;
 import cn.com.cnc.fcc.domain.RbacUserRightRelation;
import cn.com.cnc.fcc.repository.RbacUserRightRelationRepository;
import com.codahale.metrics.annotation.Timed;
import cn.com.cnc.fcc.domain.RbacRole;
import cn.com.cnc.fcc.repository.RbacRoleRepository;
import cn.com.cnc.fcc.service.RbacRoleService;
import cn.com.cnc.fcc.service.dto.RbacRightDTO;
import cn.com.cnc.fcc.service.dto.RbacRoleDTO;
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
public class RbacRoleController {

 private  Logger log;

 private  RbacRoleRepository rbacRoleRepository;

 private  RbacRoleService rbacRoleService;

@Autowired
 private  RbacUserRightRelationRepository rbacUserRightRelationRepository;

public RbacRoleController(RbacRoleRepository rbacRoleRepository, RbacRoleService rbacRoleService) {
    this.rbacRoleRepository = rbacRoleRepository;
    this.rbacRoleService = rbacRoleService;
}
@GetMapping("/rbac-roles/getRoleInfo/index")
@Timed
public ResponseEntity<List<RbacRole>> getRoleInfoForCodeName(HttpServletRequest request,Pageable pageable){
    // 模糊查询参数
    String roleName = request.getParameter("roleName");
    String roleCode = request.getParameter("roleCode");
    // 如果是null 就设置成空字符串
    if (roleCode == null) {
        roleCode = "";
    }
    // 如果是null 就设置成空字符串
    if (roleName == null) {
        roleName = "";
    }
    // 分页模糊查询
    Page<RbacRole> RbacRole = rbacRoleRepository.getRoleInfoForCodeName(pageable, roleName, roleCode);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(RbacRole, "/api/rbac-roles/getRoleInfo");
    // 返回值
    return new ResponseEntity<>(RbacRole.getContent(), headers, HttpStatus.OK);
}


@GetMapping("/rbac-roles/sameCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer sameCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("samecheck");
    // 如果这个值在数据库里找到了，则返回1
    List<RbacRole> list = rbacRoleRepository.findByRoleCode(cd);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


@PostMapping("/rbac-roles/updateRole")
@Timed
public Integer updateRole(HttpServletRequest request,RbacRole rbacRole){
    String selectListVal = request.getParameter("selectListVal");
    // 插入返回结果
    Integer updateRole = 0;
    try {
        updateRole = rbacRoleService.updateRole(selectListVal, rbacRole);
    } catch (Exception e) {
    // TODO: handle exception
    }
    return updateRole;
}


@GetMapping("/rbac-roles/getRightList")
@Timed
public List<RbacRightDTO> getRightList(){
    // 返回值
    return rbacRoleService.getRightList();
}


@PostMapping("/rbac-roles/createRole")
@Timed
public Integer createRole(HttpServletRequest request,RbacRole rbacRole){
    String selectListVal = request.getParameter("selectListVal");
    // 插入返回结果
    Integer createRole = 0;
    try {
        createRole = rbacRoleService.createRole(selectListVal, rbacRole);
    } catch (Exception e) {
    // TODO: handle exception
    }
    return createRole;
}


@GetMapping("/rbac-roles/getRoleInfo")
@Timed
public ResponseEntity<List<RbacRole>> getRoleInfo(HttpServletRequest request,Pageable pageable){
    // 模糊查询参数
    String roleName = request.getParameter("roleName");
    String roleCode = request.getParameter("roleCode");
    // 分页模糊查询
    Page<RbacRole> RbacRole = rbacRoleRepository.getRoleInfoForCodeName(pageable, roleName, roleCode);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(RbacRole, "/api/rbac-roles/getRoleInfo");
    // 返回值
    return new ResponseEntity<>(RbacRole.getContent(), headers, HttpStatus.OK);
}


@GetMapping("/rbac-role-right-relations/RoleRightInfo/{roleId}")
@Timed
public List<RbacRoleDTO> RoleRightInfo(Integer roleId){
    // 返回值
    return rbacRoleService.getRoleRightInfo(roleId);
}


@GetMapping("/rbac-roles/deleteCheck")
@Timed
@SuppressWarnings("unchecked")
public Integer deleteCheck(HttpServletRequest request){
    Integer resultNumber = 0;
    // 得到传过来的值
    String cd = request.getParameter("deletecheck");
    Integer it = Integer.valueOf(cd);
    // 如果这个值在数据库里找到了，则返回1
    List<RbacUserRightRelation> list = rbacUserRightRelationRepository.findByRoleId(it);
    if (list.size() != 0) {
        resultNumber = 1;
    } else {
        resultNumber = 0;
    }
    return resultNumber;
}


}