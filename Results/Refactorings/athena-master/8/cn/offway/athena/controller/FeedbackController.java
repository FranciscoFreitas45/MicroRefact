import cn.offway.athena.domain;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util;
@Controller
@RequestMapping
public class FeedbackController {

 private  Logger logger;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  PhFeedbackService feedbackService;

@Autowired
 private  PhFeedbackDetailService feedbackDetailService;

@Autowired
 private  PhBrandService brandService;

@Autowired
 private  PhGoodsService goodsService;

@Autowired
 private  PhRoleadminService roleadminService;

@Autowired
 private  PhBrandadminService brandadminService;


@RequestMapping("/feedback_modify.html")
public String modify(ModelMap map,String id){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    map.addAttribute("theId", id);
    return "feedback_compose";
}


@RequestMapping("/feedback_getBrand")
@ResponseBody
public List<PhBrand> getBrandList(String prefix){
    return brandService.findAll(prefix);
}


@ResponseBody
@RequestMapping("/feedback_detail_list")
public Map<String,Object> getList(int sEcho,int iDisplayStart,int iDisplayLength,long id,String starName,String sTime,String eTime,HttpServletRequest request){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    Date sTimeDate = null, eTimeDate = null;
    if (StringUtils.isNotBlank(sTime)) {
        sTimeDate = DateTime.parse(sTime, format).toDate();
    }
    if (StringUtils.isNotBlank(eTime)) {
        eTimeDate = DateTime.parse(eTime, format).toDate();
    }
    PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Sort.Direction.fromString(sortDir), sortName);
    Page<PhFeedbackDetail> pages = feedbackDetailService.findByPid(id, starName, sTimeDate, eTimeDate, pr);
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
@RequestMapping("/feedback_detail_get")
public PhFeedbackDetail get(Long id){
    return feedbackDetailService.findOne(id);
}


@RequestMapping("/feedback_getGoods")
@ResponseBody
public List<PhGoods> getGoodsList(int type,String value,String brandId){
    switch(type) {
        case 0:
            return goodsService.findAll("brandId", value, brandId);
        case 1:
            return goodsService.findAll("type", value, brandId);
        case 2:
            return goodsService.findAll("category", value, brandId);
        case 3:
            return goodsService.findAll("keyword", value, brandId);
        default:
            return null;
    }
}


@ResponseBody
@RequestMapping("/feedback_detail_save")
@Transactional
public boolean save(PhFeedbackDetail detail,String[] images,String action,String[] goodsIDs,String brandIDStr){
    if ("add".equals(action)) {
        if (goodsIDs.length != 0) {
            HashMap<Long, List<String>> map = new HashMap<>();
            // 将商品ID按所属品牌分类
            for (String goodsId : goodsIDs) {
                PhGoods goods = goodsService.findOne(Long.valueOf(goodsId));
                if (goods != null) {
                    if (map.containsKey(goods.getBrandId())) {
                        List<String> list = map.get(goods.getBrandId());
                        list.add(goodsId);
                        map.put(goods.getBrandId(), list);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(goodsId);
                        map.put(goods.getBrandId(), list);
                    }
                }
            }
            // 按品牌ID依次添加
            for (long bid : map.keySet()) {
                PhFeedbackDetail obj = SerializationUtils.clone(detail);
                obj.setBrandId(bid);
                saveObj(obj, images, map.get(bid).toArray(new String[0]));
            }
        } else {
            // 按品牌ID依次添加
            for (String bid : brandIDStr.split(",")) {
                PhFeedbackDetail obj = SerializationUtils.clone(detail);
                obj.setBrandId(Long.valueOf(bid));
                saveObj(obj, images, new String[0]);
            }
        }
    } else {
        PhFeedbackDetail feedbackDetailFull = feedbackDetailService.findOne(detail.getId());
        PhFeedback feedback = feedbackService.findOne(feedbackDetailFull.getPid());
        feedback.setImgNum(feedback.getImgNum() - feedbackDetailFull.getImgNum() + images.length);
        feedback.setUpdateTime(new Date());
        if (!detail.getStarName().equals(feedbackDetailFull.getStarName())) {
            long oldNum = feedbackDetailService.checkStarName(feedbackDetailFull.getPid(), feedbackDetailFull.getStarName());
            long newNum = feedbackDetailService.checkStarName(feedbackDetailFull.getPid(), detail.getStarName());
            if (oldNum == 1) {
                feedback.setStarNum(feedback.getStarNum() - 1);
            }
            if (newNum == 0) {
                feedback.setStarNum(feedback.getStarNum() + 1);
            }
        }
        feedbackDetailFull.setStarName(detail.getStarName());
        feedbackDetailFull.setWeibo(detail.getWeibo());
        feedbackDetailFull.setRemark(detail.getRemark());
        feedbackDetailFull.setImgNum((long) images.length);
        feedbackDetailFull.setImgUrl(String.join(",", images));
        feedbackDetailFull.setGoodsId(String.join(",", goodsIDs));
        feedbackDetailService.save(feedbackDetailFull);
        feedbackService.save(feedback);
    }
    return true;
}


@ResponseBody
@RequestMapping("/feedback_del")
public boolean deleteAll(Long[] ids){
    for (Long id : ids) {
        feedbackService.delete(id);
        feedbackDetailService.delByPid(id);
    }
    return true;
}


@RequestMapping("/feedback_detail.html")
public String index(ModelMap map,Long id,String starName){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    map.addAttribute("theId", id);
    map.addAttribute("starName", starName);
    PhFeedback obj = feedbackService.findOne(id);
    if (obj != null) {
        map.addAttribute("theName", obj.getBrandName());
    }
    return "feedback_detail";
}


@RequestMapping("/feedback_insert.html")
public String insert(ModelMap map){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    map.addAttribute("theId", "XYZ");
    return "feedback_compose";
}


@ResponseBody
@RequestMapping("/feedback_goods")
public List<PhGoods> goods(String ids){
    List<PhGoods> list = new ArrayList<>();
    for (String id : ids.split(",")) {
        list.add(goodsService.findOne(Long.valueOf(id)));
    }
    return list;
}


@Transactional
public void saveObj(PhFeedbackDetail detail,String[] images,String[] goodsIDs){
    PhBrand brand = brandService.findOne(detail.getBrandId());
    if (brand != null) {
        detail.setBrandLogo(brand.getLogo());
        detail.setBrandName(brand.getName());
        detail.setBackTime(new Date());
        detail.setImgNum((long) images.length);
        detail.setImgUrl(String.join(",", images));
        detail.setGoodsId(String.join(",", goodsIDs));
        PhFeedback feedback = feedbackService.findByBrandId(brand.getId());
        if (feedback != null) {
            feedback.setImgNum(feedback.getImgNum() + images.length);
            feedback.setUpdateTime(new Date());
            long num = feedbackDetailService.checkStarName(feedback.getId(), detail.getStarName());
            if (num == 0) {
                feedback.setStarNum(feedback.getStarNum() + 1);
            }
        } else {
            feedback = new PhFeedback();
            feedback.setImgNum((long) images.length);
            feedback.setUpdateTime(new Date());
            feedback.setBrandId(brand.getId());
            feedback.setBrandLogo(brand.getLogo());
            feedback.setBrandName(brand.getName());
            feedback.setStarNum(1L);
        }
        PhFeedback feedbackSaved = feedbackService.save(feedback);
        detail.setPid(feedbackSaved.getId());
        feedbackDetailService.save(detail);
    }
}


@ResponseBody
@RequestMapping("/feedback_detail_del")
@Transactional
public boolean delete(Long[] ids){
    for (Long id : ids) {
        PhFeedbackDetail detail = feedbackDetailService.findOne(id);
        PhFeedback feedback = feedbackService.findOne(detail.getPid());
        feedback.setImgNum(feedback.getImgNum() - detail.getImgNum());
        long unique = feedbackDetailService.checkStarName(detail.getPid(), detail.getStarName());
        if (unique == 1) {
            feedback.setStarNum(feedback.getStarNum() - 1);
        }
        feedbackService.save(feedback);
        feedbackDetailService.delete(id);
    }
    return true;
}


}