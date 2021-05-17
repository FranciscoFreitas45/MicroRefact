import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.offway.athena.domain.PhCreditDetail;
import cn.offway.athena.domain.PhOrderInfo;
import cn.offway.athena.domain.PhUserInfo;
import cn.offway.athena.service.PhCreditDetailService;
import cn.offway.athena.service.PhOrderInfoService;
import cn.offway.athena.service.PhUserInfoService;
@Controller
public class CreditController {

 private  Logger logger;

@Autowired
 private  PhCreditDetailService phCreditDetailService;

@Autowired
 private  PhUserInfoService phUserInfoService;

@Autowired
 private  PhOrderInfoService phOrderInfoService;


@ResponseBody
@RequestMapping("/credit-data")
public Map<String,Object> creditData(HttpServletRequest request,String orderNo,String unionid,String type){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Page<PhCreditDetail> pages = phCreditDetailService.findByPage(orderNo.trim(), unionid.trim(), type.trim(), new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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
@RequestMapping("/credit-save")
public boolean creditSave(String orderNo,String type,Long score,String channel,Authentication authentication){
    PhOrderInfo phOrderInfo = phOrderInfoService.findByOrderNo(orderNo);
    if (null == phOrderInfo) {
        return false;
    }
    PhCreditDetail phCreditDetail = new PhCreditDetail();
    phCreditDetail.setType(type);
    phCreditDetail.setScore(score);
    phCreditDetail.setChannel(channel);
    phCreditDetail.setCreateName(authentication.getName());
    phCreditDetail.setCreateTime(new Date());
    phCreditDetail.setOrderNo(orderNo);
    phCreditDetail.setUnionid(phOrderInfo.getUnionid());
    phCreditDetailService.save(phCreditDetail);
    PhUserInfo phUserInfo = phUserInfoService.findByUnionid(phOrderInfo.getUnionid());
    Long creditScore = phUserInfo.getCreditScore();
    if ("0".equals(type)) {
        creditScore = creditScore.longValue() + score.longValue();
    } else {
        creditScore = creditScore.longValue() - score.longValue();
    }
    phUserInfo.setCreditScore(creditScore);
    phUserInfoService.save(phUserInfo);
    return true;
}


@RequestMapping("/credit.html")
public String credit(ModelMap map){
    return "credit";
}


}