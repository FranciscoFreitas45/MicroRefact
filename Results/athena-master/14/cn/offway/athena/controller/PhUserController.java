import cn.offway.athena.domain.PhUserInfo;
import cn.offway.athena.service.PhUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Controller
public class PhUserController {

 private  Logger logger;

@Autowired
 private  PhUserInfoService phUserInfoService;


@RequestMapping("/phUsers.html")
public String phUsers(ModelMap map){
    return "phUsers";
}


@ResponseBody
@RequestMapping("/phUsers-data")
public Map<String,Object> phUsersData(HttpServletRequest request,String nickname,String unionid,String phone,String id,String real_name,String isAuth,int sEcho,int iDisplayStart,int iDisplayLength){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName);
    Page<PhUserInfo> pages = phUserInfoService.findByPage(nickname.trim(), unionid.trim(), phone.trim(), id.trim(), real_name.trim(), isAuth.trim(), pr);
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


}