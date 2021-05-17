import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.offway.athena.domain.PhCode;
import cn.offway.athena.service.PhCodeService;
@Controller
public class CodeController {

 private  Logger logger;

@Autowired
 private  PhCodeService phCodeService;


@RequestMapping("/code.html")
public String code(ModelMap map){
    return "code";
}


@ResponseBody
@PostMapping("/code-one")
public PhCode findOne(Long id){
    return phCodeService.findOne(id);
}


@PostMapping("/code-save")
@ResponseBody
public boolean codeSave(PhCode phCode){
    if ("".equals(phCode.getId()) || phCode.getId() == null) {
        phCode.setCode("" + RandomUtils.nextInt(1000, 9999));
        phCode.setCreateTime(new Date());
        phCode.setStatus("0");
        phCodeService.save(phCode);
    } else {
        PhCode code = phCodeService.findOne(phCode.getId());
        code.setPhone(phCode.getPhone());
        code.setRealName(phCode.getRealName());
        code.setPosition(phCode.getPosition());
        phCodeService.save(code);
    }
    return true;
}


@ResponseBody
@RequestMapping("/code-data")
public Map<String,Object> codeData(HttpServletRequest request,String status,String code,String phone){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Page<PhCode> pages = phCodeService.findByPage(status.trim(), code.trim(), phone.trim(), new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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


@PostMapping("/code-del")
@ResponseBody
public boolean codeDel(Long id){
    phCodeService.coddel(id);
    return true;
}


}