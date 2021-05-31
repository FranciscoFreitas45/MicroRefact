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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhOrderInfo;
import cn.offway.athena.domain.PhShowImage;
import cn.offway.athena.domain.PhUserInfo;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.PhBrandService;
import cn.offway.athena.service.PhOrderInfoService;
import cn.offway.athena.service.PhShowImageService;
import cn.offway.athena.service.PhUserInfoService;
import cn.offway.athena.service.QiniuService;
@Controller
public class ShowImageController {

 private  Logger logger;

@Autowired
 private  PhShowImageService phShowImageService;

@Autowired
 private  PhBrandService phBrandService;

@Autowired
 private  QiniuService qiniuService;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  PhOrderInfoService phOrderInfoService;

@Autowired
 private  PhUserInfoService phUserInfoService;


@ResponseBody
@RequestMapping("/showImage-info")
public PhShowImage showImageInfo(Long id){
    PhShowImage phShowImage = phShowImageService.findOne(id);
    return phShowImage;
}


@ResponseBody
@PostMapping("/showImage-save")
public boolean save(String orderNo,String images,String url,String content,String starName){
    try {
        PhOrderInfo phOrderInfo = phOrderInfoService.findByOrderNo(orderNo.trim());
        PhShowImage phShowImage = new PhShowImage();
        phShowImage.setBrandId(phOrderInfo.getBrandId());
        phShowImage.setBrandLogo(phOrderInfo.getBrandLogo());
        phShowImage.setBrandName(phOrderInfo.getBrandName());
        String unionid = phOrderInfo.getUnionid();
        phShowImage.setUnionid(unionid);
        PhUserInfo phUserInfo = phUserInfoService.findByUnionid(unionid);
        phShowImage.setRealName(phUserInfo.getRealName());
        phShowImage.setPosition(phUserInfo.getPosition());
        phShowImage.setCreateTime(new Date());
        phShowImage.setUrl(url);
        phShowImage.setIsOffway(phOrderInfo.getIsOffway());
        phShowImage.setOrderNo(orderNo);
        phShowImage.setShowImage(images);
        phShowImage.setContent(content);
        phShowImage.setStatus("1");
        phShowImage.setStarName(starName);
        phShowImageService.save(phShowImage);
        phOrderInfo.setIsUpload("1");
        phOrderInfoService.save(phOrderInfo);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}


@RequestMapping("/showImage.html")
public String showImage(ModelMap map,Authentication authentication){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    map.addAttribute("token", qiniuService.token());
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    map.addAttribute("brands", phBrandService.findByIds(brandIds));
    return "showImage";
}


@ResponseBody
@RequestMapping("/showImage-check")
@Transactional
public boolean showImageCheck(String id,String status,String checkContent,Authentication authentication){
    for (String i : id.split(",")) {
        PhShowImage phShowImage = phShowImageService.findOne(Long.valueOf(i));
        phShowImage.setStatus(status);
        phShowImage.setCheckContent(checkContent);
        phShowImage.setCheckName(authentication.getName());
        phShowImage.setCheckTime(new Date());
        phShowImageService.save(phShowImage);
    }
    return true;
}


@ResponseBody
@RequestMapping("/showImage-data")
public Map<String,Object> showImageData(HttpServletRequest request,String realName,String position,String orderNo,String unionid,String status,Long brandId,String isOffway,Authentication authentication){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    Page<PhShowImage> pages = phShowImageService.findByPage(realName.trim(), position.trim(), orderNo.trim(), null != unionid ? unionid.trim() : unionid, status.trim(), brandId, isOffway, brandIds, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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