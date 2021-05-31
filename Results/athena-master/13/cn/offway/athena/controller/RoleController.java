import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import cn.offway.athena.domain.PhRole;
import cn.offway.athena.service.PhRoleService;
import cn.offway.athena.service.PhRoleresourceService;
@Controller
public class RoleController {

 private  Logger logger;

@Autowired
 private  PhRoleService phRoleService;

@Autowired
 private  PhRoleresourceService phRoleresourceService;


@ResponseBody
@GetMapping("/roles-one")
public Map<String,Object> rolesOne(Long id){
    Map<String, Object> resultMap = new HashMap<>();
    resultMap.put("role", phRoleService.findOne(id));
    resultMap.put("resourceIds", phRoleresourceService.findSourceIdByRoleId(id));
    return resultMap;
}


@RequestMapping("/roles.html")
public String roles(){
    return "roles";
}


@ResponseBody
@PostMapping("/roles-save")
public boolean save(PhRole phRole,Long[] resources){
    try {
        phRoleService.save(phRole, resources);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("保存角色异常,phRole:{},resources:{}", JSON.toJSONString(phRole, SerializerFeature.WriteMapNullValue), resources, e);
        return false;
    }
}


@ResponseBody
@RequestMapping("/roles-data")
public Map<String,Object> rolesData(HttpServletRequest request,String name){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Page<PhRole> pages = phRoleService.findByPage(name, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
    // 为操作次数加1，必须这样做
    int initEcho = sEcho + 1;
    Map<String, Object> map = new HashMap<>();
    map.put("sEcho", initEcho);
    // 数据总条数
    map.put("iTotalRecords", pages.getTotalElements());
    // 显示的条数
    map.put("iTotalDisplayRecords", pages.getTotalElements());
    // 数据集合
    map.put("aData", pages.getContent());
    return map;
}


@ResponseBody
@PostMapping("/roles-delete")
public boolean delete(String ids){
    try {
        phRoleService.deleteRole(ids);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("删除角色异常,用户id:{}", ids, e);
        return false;
    }
}


@ResponseBody
@GetMapping("/roles-all")
public List<PhRole> findAll(){
    return phRoleService.findAll();
}


}