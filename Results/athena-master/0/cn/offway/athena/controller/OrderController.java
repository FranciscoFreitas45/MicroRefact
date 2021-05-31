import cn.offway.athena.domain;
import cn.offway.athena.service;
import cn.offway.athena.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util;
@Controller
public class OrderController {

 private  Logger logger;

@Autowired
 private  PhOrderInfoService phOrderInfoService;

@Autowired
 private  PhOrderGoodsService phOrderGoodsService;

@Autowired
 private  PhOrderExpressInfoService phOrderExpressInfoService;

@Autowired
 private  PhGoodsStockService phGoodsStockService;

@Autowired
 private  PhBrandService phBrandService;

@Autowired
 private  PhOrderRemarkService phOrderRemarkService;


@ResponseBody
@RequestMapping("/order_trackOrder")
public String trackOrder(String mailNo){
    return queryExpress("shunfeng", mailNo);
}


public String queryExpress(String expressCode,String mailNo){
    String key = "uyUDaSuE5009";
    String customer = "28B3DE9A2485E14FE0DAD40604A8922C";
    Map<String, String> innerParam = new HashMap<>();
    innerParam.put("com", expressCode);
    innerParam.put("num", mailNo);
    String innerParamStr = JSON.toJSONString(innerParam);
    String signStr = innerParamStr + key + customer;
    String sign = DigestUtils.md5Hex(signStr.getBytes()).toUpperCase();
    Map<String, String> param = new HashMap<>();
    param.put("customer", customer);
    param.put("param", innerParamStr);
    param.put("sign", sign);
    return HttpClientUtil.post("https://poll.kuaidi100.com/poll/query.do", param);
}


@ResponseBody
@RequestMapping("/order-update")
@Transactional
public boolean orderUpdate(String orderNo,String status,Authentication auth){
    try {
        PhOrderInfo phOrderInfo = phOrderInfoService.findByOrderNo(orderNo);
        phOrderInfo.setStatus(status);
        phOrderInfo.setRemark("修改订单状态为：" + status + ",修改时间：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + ",修改人：" + auth.getName());
        phOrderInfoService.save(phOrderInfo);
        if ("1".equals(status)) {
            for (PhOrderGoods goods : phOrderGoodsService.findNormalByOrderNo(orderNo)) {
                goods.setBatch(0L);
                goods.setMailNo("外部发货");
                phOrderGoodsService.save(goods);
            }
        }
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("order-update异常orderNo:{},status:{}", orderNo, status, e);
        return false;
    }
}


@ResponseBody
@RequestMapping("order-goods-page")
public Map<String,Object> phOrderGoodsPage(String orderNo){
    List<PhOrderGoods> pages = phOrderGoodsService.findByOrderNo(orderNo);
    // 为操作次数加1，必须这样做
    int initEcho = 1;
    Map<String, Object> map = new HashMap<>();
    map.put("sEcho", initEcho);
    // 数据总条数
    map.put("iTotalRecords", pages.size());
    // 显示的条数
    map.put("iTotalDisplayRecords", pages.size());
    // 数据集合
    map.put("aData", pages);
    return map;
}


@ResponseBody
@RequestMapping("/order-express")
public PhOrderExpressInfo phOrderExpress(String orderNo,String type){
    return phOrderExpressInfoService.findByOrderNoAndType(orderNo, type);
}


@ResponseBody
@RequestMapping("/order-goods")
public List<PhOrderGoods> phOrderGoods(String orderNo){
    return phOrderGoodsService.findByOrderNo(orderNo);
}


@ResponseBody
@RequestMapping("order-delremark")
public boolean delremark(Long id){
    phOrderRemarkService.delete(id);
    return true;
}


@RequestMapping("/order-return.html")
public String orderReturn(ModelMap map,Authentication authentication){
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    map.addAttribute("brands", phBrandService.findByIds(brandIds));
    return "order-return";
}


@ResponseBody
@RequestMapping("/order-data")
public Map<String,Object> orderData(HttpServletRequest request,String orderNo,String unionid,String sku,String realName,String position,String status,Long brandId,String isOffway,String isUpload,Authentication authentication,String users,String size,String sTime,String eTime,int sEcho,int iDisplayStart,int iDisplayLength){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds;
    if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
        brandIds = new ArrayList<>();
    } else {
        brandIds = phAdmin.getBrandIds();
    }
    Date sTimeDate = null, eTimeDate = null;
    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    if (StringUtils.isNotBlank(sTime)) {
        sTimeDate = DateTime.parse(sTime, format).toDate();
    }
    if (StringUtils.isNotBlank(eTime)) {
        eTimeDate = DateTime.parse(eTime, format).toDate();
    }
    PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName);
    Page<PhOrderInfo> pages = phOrderInfoService.findByPage(sku, isUpload, realName.trim(), position.trim(), orderNo.trim(), null != unionid ? unionid.trim() : unionid, status.trim(), brandId, isOffway, brandIds, users, size, sTimeDate, eTimeDate, pr);
    List<Map<String, Object>> list = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    for (PhOrderInfo info : pages.getContent()) {
        long i = 0;
        for (PhOrderGoods goods : phOrderGoodsService.findByOrderNo(info.getOrderNo())) {
            if (goods.getBatch() != null && ((i & 1 << goods.getBatch()) == 0)) {
                i |= 1 << goods.getBatch();
                Map<String, Object> m = mapper.convertValue(info, Map.class);
                m.put("batch", goods.getBatch());
                list.add(m);
            }
        }
    }
    // 为操作次数加1，必须这样做
    int initEcho = sEcho + 1;
    Map<String, Object> map = new HashMap<>();
    map.put("sEcho", initEcho);
    // 数据总条数
    map.put("iTotalRecords", pages.getTotalElements());
    // 显示的条数
    map.put("iTotalDisplayRecords", pages.getTotalElements());
    // 数据集合
    map.put("aData", list);
    return map;
}


@ResponseBody
@RequestMapping("/order-confirm-back")
@Transactional
public boolean confirmBack(String id,Long[] ids){
    for (long gid : ids) {
        PhOrderGoods goods = phOrderGoodsService.findOne(gid);
        if (goods != null) {
            /* 状态:[0-未收回,1-已收回] **/
            goods.setState("1");
            phOrderGoodsService.save(goods);
        }
    }
    PhOrderInfo orderInfo = phOrderInfoService.findByOrderNo(id);
    if (orderInfo != null) {
        int a = 0, b = 0;
        for (PhOrderGoods goods : phOrderGoodsService.findByOrderNo(orderInfo.getOrderNo())) {
            if ("0".equals(goods.getState())) {
                a++;
            } else {
                b++;
            }
        }
        if (a == 0) {
            /*状态[0-已下单,1-已发货,2-已寄回,3-已收货,4-已取消,5-已部分收货]*/
            orderInfo.setStatus("3");
        } else if (b == 0) {
            /*状态[0-已下单,1-已发货,2-已寄回,3-已收货,4-已取消,5-已部分收货]*/
            orderInfo.setStatus("2");
        } else {
            /*状态[0-已下单,1-已发货,2-已寄回,3-已收货,4-已取消,5-已部分收货]*/
            orderInfo.setStatus("5");
        }
        phOrderInfoService.save(orderInfo);
    }
    return true;
}


@ResponseBody
@RequestMapping("order-remarkbyid")
public Map<String,Object> remarkbyid(HttpServletRequest request,String id){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Page<PhOrderRemark> pages = phOrderRemarkService.findAllByPage(id, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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
@RequestMapping("order-addremark")
public boolean addremark(String id,String content){
    try {
        PhOrderRemark orderRemark = new PhOrderRemark();
        PhOrderInfo orderInfo = phOrderInfoService.findOne(Long.valueOf(id));
        orderRemark.setContent(content);
        orderRemark.setCreateTime(new Date());
        orderRemark.setOrdersNo(orderInfo.getOrderNo());
        orderRemark.setOrdersId(id);
        phOrderRemarkService.save(orderRemark);
        orderInfo.setExtra("1");
        phOrderInfoService.save(orderInfo);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("order-addremark异常id:{},content:{}", id, content, e);
        return false;
    }
}


@RequestMapping("/order.html")
public String order(ModelMap map,Authentication authentication,String brandId){
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    map.addAttribute("brands", phBrandService.findByIds(brandIds));
    map.addAttribute("brandId", brandId);
    return "order";
}


@ResponseBody
@RequestMapping("/order-check")
public boolean orderCheck(String orderNo){
    try {
        return phGoodsStockService.updateStock(orderNo);
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("order-check异常orderNo:{}", orderNo, e);
        return false;
    }
}


}