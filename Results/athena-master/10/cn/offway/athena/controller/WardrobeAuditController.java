import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhUserInfo;
import cn.offway.athena.domain.PhWardrobe;
import cn.offway.athena.domain.PhWardrobeAudit;
import cn.offway.athena.service.PhBrandService;
import cn.offway.athena.service.PhUserInfoService;
import cn.offway.athena.service.PhWardrobeAuditService;
import cn.offway.athena.service.PhWardrobeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class WardrobeAuditController {

 private  Logger logger;

@Autowired
 private  PhWardrobeAuditService wardrobeAuditService;

@Autowired
 private  PhWardrobeService wardrobeService;

@Autowired
 private  PhBrandService brandService;

@Autowired
 private  PhUserInfoService userInfoService;


@ResponseBody
@RequestMapping("/audit_up")
public boolean allow(Long id){
    PhWardrobeAudit obj = wardrobeAuditService.findOne(id);
    if (obj != null) {
        PhWardrobe wardrobe = wardrobeService.findOne(obj.getWardrobeId());
        obj.setState("1");
        wardrobeAuditService.save(obj);
        wardrobe.setState("1");
        wardrobeService.save(wardrobe);
        PhUserInfo userInfo = userInfoService.findByUnionid(obj.getUnionid());
        if (userInfo != null) {
            sendMsg(userInfo.getMiniopenid(), null);
        }
    }
    return true;
}


@ResponseBody
@RequestMapping("/audit_list")
public Map<String,Object> getStockList(int sEcho,int iDisplayStart,int iDisplayLength,String brandId,String goodsName,String goodsId,String state,Authentication authentication){
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds;
    if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
        brandIds = null;
    } else {
        brandIds = phAdmin.getBrandIds();
    }
    PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort);
    Page<PhWardrobeAudit> pages = wardrobeAuditService.listAll(brandId, goodsName, goodsId, state, brandIds, pr);
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
@RequestMapping("/audit_down")
public boolean deny(Long id,String str){
    PhWardrobeAudit obj = wardrobeAuditService.findOne(id);
    if (obj != null) {
        PhWardrobe wardrobe = wardrobeService.findOne(obj.getWardrobeId());
        obj.setState("2");
        obj.setReason(str);
        wardrobeAuditService.save(obj);
        wardrobe.setState("2");
        wardrobeService.save(wardrobe);
        PhUserInfo userInfo = userInfoService.findByUnionid(obj.getUnionid());
        if (userInfo != null) {
            sendMsg(userInfo.getMiniopenid(), str);
        }
    }
    return true;
}


@ResponseBody
@RequestMapping("/audit_findOne")
public PhWardrobeAudit findOne(Long id){
    return wardrobeAuditService.findOne(id);
}


@RequestMapping("/audit.html")
public String index(ModelMap map,Authentication authentication){
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
        map.addAttribute("brands", brandService.findAll());
    } else {
        List<Long> brandIds = phAdmin.getBrandIds();
        map.addAttribute("brands", brandService.findByIds(brandIds));
    }
    return "audit_index";
}


public void sendMsg(String openid,String approvalContent){
    String result = "审核通知";
    String content = "你在OFFWAY MODE SHOWROOM申请的服装已通过审核，请及时提交订单借衣。";
    if (StringUtils.isNotBlank(approvalContent)) {
        result = "审核失败";
        content = "您在OFFWAY MODE SHOWROOM申请的借衣未通过审核，理由：" + approvalContent;
    }
    Map<String, Object> args = new HashMap<>();
    args.put("touser", openid);
    args.put("template_id", "Kp9iDQ5mUycHBTroqgGttJB5fQyxBZcmBpI-zTHAUwc");
    Map<String, Object> data = new HashMap<>();
    Map<String, String> k1 = new HashMap<>();
    k1.put("value", result);
    data.put("thing4", k1);
    Map<String, String> k2 = new HashMap<>();
    k2.put("value", content);
    data.put("thing5", k2);
    args.put("data", data);
// PhAuthServiceImpl.sendSubscribeMsg(args, PhAuthServiceImpl.getToken());
}


}