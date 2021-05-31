import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.offway.athena.domain.PhAuth;
import cn.offway.athena.service.PhAuthService;
@Controller
public class AuthController {

 private  Logger logger;

@Autowired
 private  PhAuthService phAuthService;


@PostMapping("/auth-update")
@ResponseBody
public boolean authUpdate(Long id,String status,String approvalContent,Authentication authentication){
    return phAuthService.authUpdate(id, status, approvalContent, authentication);
}


@RequestMapping("/auth.html")
public String activityPrizes(ModelMap map){
    return "auth";
}


@ResponseBody
@RequestMapping("/auth-data")
public Map<String,Object> activityPrizesData(HttpServletRequest request,String status,String nickName,String unionid){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Page<PhAuth> pages = phAuthService.findByPage(status.trim(), nickName.trim(), unionid.trim(), new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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
@PostMapping("/auth-one")
public PhAuth findOne(Long id){
    return phAuthService.findOne(id);
}


}