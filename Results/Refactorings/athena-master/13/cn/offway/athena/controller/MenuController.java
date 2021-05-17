import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import cn.offway.athena.domain.PhResource;
import cn.offway.athena.service.PhResourceService;
@Controller
public class MenuController {

 private  Logger logger;

@Autowired
 private  PhResourceService phResourceService;


@ResponseBody
@PostMapping("/menus-save")
public boolean save(PhResource phResource){
    try {
        phResource.setCreatedtime(new Date());
        phResourceService.save(phResource);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("保存菜单异常,{}", JSON.toJSONString(phResource), e);
        return false;
    }
}


@ResponseBody
@PostMapping("/menus-one")
public PhResource findOne(Long id){
    return phResourceService.findOne(id);
}


@ResponseBody
@PostMapping("/menus-parent")
public List<PhResource> resources(Long parentId){
    return phResourceService.findByParentId(parentId);
}


@RequestMapping("/menus.html")
public String menus(){
    return "menus";
}


@ResponseBody
@PostMapping("/menus-parent-notnull")
public List<PhResource> findByParentIdNotNull(){
    return phResourceService.findByParentIdNotNull();
}


@ResponseBody
@RequestMapping("/menus-data")
public Map<String,Object> menusData(HttpServletRequest request,String name,String link,Long parentId){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Page<PhResource> pages = phResourceService.findByPage(name, link, parentId, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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
@PostMapping("/menus-delete")
public boolean delete(String ids){
    try {
        phResourceService.deleteResource(ids);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("删除菜单异常,id:{}", ids, e);
        return false;
    }
}


}