import cn.offway.athena.domain;
import cn.offway.athena.dto.sf.ReqAddOrder;
import cn.offway.athena.service;
import cn.offway.athena.utils.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
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
import java.util;
@Controller
public class DeliverController {

 private  Logger logger;

@Autowired
 private  PhOrderExpressInfoService phOrderExpressInfoService;

@Autowired
 private  PhOrderGoodsService phOrderGoodsService;

@Autowired
 private  PhOrderInfoService phOrderInfoService;

@Autowired
 private  PhBrandService brandService;

@Autowired
 private  PhAddressService addressService;

@Autowired
 private  SfExpressService sfExpressService;

@Autowired
 private  PhAddressBrandService addressBrandService;


@ResponseBody
@RequestMapping("/deliver-cancel")
public boolean cancel(String orderNo){
    try {
        phOrderInfoService.cancel(orderNo);
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    return true;
}


public PhOrderExpressInfo createExpressOfBrand(PhOrderInfo orderInfo){
    // 保存订单物流
    PhAddress toAddress = addressService.findOne(orderInfo.getAddressId());
    PhBrand phBrand = brandService.findOne(orderInfo.getBrandId());
    PhAddressBrand brandAddress = addressBrandService.findOne(phBrand.getAddrId() == null ? 1L : phBrand.getAddrId());
    PhOrderExpressInfo phOrderExpressInfo = new PhOrderExpressInfo();
    phOrderExpressInfo.setCreateTime(new Date());
    phOrderExpressInfo.setExpressOrderNo("无");
    phOrderExpressInfo.setFromPhone(brandAddress.getPhone());
    phOrderExpressInfo.setFromCity(brandAddress.getCity());
    phOrderExpressInfo.setFromContent(brandAddress.getContent());
    phOrderExpressInfo.setFromCounty(brandAddress.getCounty());
    phOrderExpressInfo.setFromProvince(brandAddress.getProvince());
    phOrderExpressInfo.setFromRealName(brandAddress.getRealName());
    phOrderExpressInfo.setOrderNo(orderInfo.getOrderNo());
    phOrderExpressInfo.setStatus("0");
    phOrderExpressInfo.setToPhone(toAddress.getPhone());
    phOrderExpressInfo.setToCity(toAddress.getCity());
    phOrderExpressInfo.setToContent(toAddress.getContent());
    phOrderExpressInfo.setToCounty(toAddress.getCounty());
    phOrderExpressInfo.setToProvince(toAddress.getProvince());
    phOrderExpressInfo.setToRealName(toAddress.getRealName());
    phOrderExpressInfo.setType("0");
    phOrderExpressInfo.setReturnId(phBrand.getReturnAddrId() == null ? 1L : phBrand.getReturnAddrId());
    return phOrderExpressInfo;
}


@ResponseBody
@RequestMapping("/deliver-cancel-goods")
@Transactional
public boolean cancelGoods(Long gid){
    PhOrderGoods orderGoods = phOrderGoodsService.findOne(gid);
    if (orderGoods != null) {
        orderGoods.setState("2");
        phOrderGoodsService.save(orderGoods);
        // 更新订单状态
        PhOrderInfo orderInfo = phOrderInfoService.findByOrderNo(orderGoods.getOrderNo());
        if (phOrderGoodsService.getRest(orderInfo.getOrderNo()) == 0) {
            orderInfo.setStatus("1");
        } else {
            orderInfo.setStatus("7");
        }
        phOrderInfoService.save(orderInfo);
    }
    return true;
}


@ResponseBody
@RequestMapping("/deliver-goods")
public List<PhOrderGoods> phOrderGoods(String orderNo,String batch){
    if (StringUtils.isNotBlank(batch)) {
        return phOrderGoodsService.findByOrderNo(orderNo, batch);
    } else {
        return phOrderGoodsService.findNormalByOrderNo(orderNo);
    // return phOrderGoodsService.findByOrderNo(orderNo);
    }
}


@ResponseBody
@RequestMapping("/deliver-data")
public Map<String,Object> deliverData(HttpServletRequest request,String orderNo,String realName,String position,String unionid,Long brandId,String isOffway){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName);
    Page<PhOrderInfo> pages = phOrderInfoService.findByPage(realName.trim(), position.trim(), orderNo.trim(), null != unionid ? unionid.trim() : unionid, brandId, isOffway, pr);
    ObjectMapper mapper = new ObjectMapper();
    List<Map<String, Object>> list = new ArrayList<>();
    for (PhOrderInfo info : pages.getContent()) {
        Map<String, Object> m = mapper.convertValue(info, Map.class);
        PhAddress address = addressService.findOne(info.getAddressId());
        if (address != null) {
            m.put("toRealName", address.getRealName());
            m.put("toPhone", address.getPhone());
            m.put("toContent", address.getContent());
        } else {
            m.put("toRealName", "");
            m.put("toPhone", "");
            m.put("toContent", "");
        }
        list.add(m);
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
@RequestMapping("/deliver-save")
@Transactional
public boolean save(String[] ids,String expressNo,String orderNo){
    PhOrderInfo orderInfo = phOrderInfoService.findByOrderNo(orderNo);
    PhOrderExpressInfo phOrderExpressInfo;
    if (StringUtils.isNotBlank(expressNo)) {
        if (orderInfo != null) {
            phOrderExpressInfo = createExpressOfBrand(orderInfo);
            long batch = -2;
            phOrderExpressInfo.setMailNo(expressNo);
            for (String id : ids) {
                PhOrderGoods orderGoods = phOrderGoodsService.findOne(Long.valueOf(id));
                if (orderGoods != null) {
                    if (batch == -2) {
                        batch = phOrderGoodsService.getMaxBatch(orderGoods.getOrderNo());
                    }
                    orderGoods.setMailNo(expressNo);
                    orderGoods.setBatch(batch + 1);
                    orderGoods.setRemark("商家发货");
                    phOrderGoodsService.save(orderGoods);
                }
            }
            phOrderExpressInfo.setBatch(batch + 1);
            phOrderExpressInfoService.save(phOrderExpressInfo);
            // 状态[0-已下单,1-已发货,2-已寄回,3-已收货,4-已取消,5-已部分收货,6-审核,7-部分寄出]
            if (phOrderGoodsService.getRest(orderNo) == 0) {
                orderInfo.setStatus("1");
            } else {
                orderInfo.setStatus("7");
            }
            phOrderInfoService.save(orderInfo);
        }
    } else {
        /*
             * 1.修改订单状态
             * 2.快递预约上门
             */
        phOrderExpressInfo = createExpressOfPlatform(orderInfo);
        JsonResult result = callSF(phOrderExpressInfo);
        if ("200".equals(result.getCode())) {
            long batch = -2;
            String mailNo = String.valueOf(result.getData());
            phOrderExpressInfo.setMailNo(mailNo);
            for (String id : ids) {
                PhOrderGoods orderGoods = phOrderGoodsService.findOne(Long.valueOf(id));
                if (orderGoods != null) {
                    if (batch == -2) {
                        batch = phOrderGoodsService.getMaxBatch(orderGoods.getOrderNo());
                    }
                    orderGoods.setMailNo(mailNo);
                    orderGoods.setBatch(batch + 1);
                    orderGoods.setRemark("平台发货");
                    phOrderGoodsService.save(orderGoods);
                }
            }
            phOrderExpressInfo.setBatch(batch + 1);
            phOrderExpressInfoService.save(phOrderExpressInfo);
            // 状态[0-已下单,1-已发货,2-已寄回,3-已收货,4-已取消,5-已部分收货,6-审核,7-部分寄出]
            if (phOrderGoodsService.getRest(orderNo) == 0) {
                orderInfo.setStatus("1");
            } else {
                orderInfo.setStatus("7");
            }
            phOrderInfoService.save(orderInfo);
        }
    }
    return true;
}


public JsonResult callSF(PhOrderExpressInfo phOrderExpressInfo){
    ReqAddOrder addOrder = new ReqAddOrder();
    addOrder.setD_province(phOrderExpressInfo.getToProvince());
    addOrder.setD_city(phOrderExpressInfo.getToCity());
    addOrder.setD_county(phOrderExpressInfo.getToCounty());
    addOrder.setD_address(phOrderExpressInfo.getToContent());
    addOrder.setD_contact(phOrderExpressInfo.getToRealName());
    addOrder.setD_tel(phOrderExpressInfo.getToPhone());
    addOrder.setJ_province(phOrderExpressInfo.getFromProvince());
    addOrder.setJ_city(phOrderExpressInfo.getFromCity());
    addOrder.setJ_county(phOrderExpressInfo.getFromCounty());
    addOrder.setJ_address(phOrderExpressInfo.getFromContent());
    addOrder.setJ_contact(phOrderExpressInfo.getFromRealName());
    addOrder.setJ_tel(phOrderExpressInfo.getFromPhone());
    addOrder.setOrder_source("OFFWAY");
    addOrder.setOrder_id(phOrderExpressInfo.getExpressOrderNo());
    // 付款方式：1:寄方付2:收方付3:第三方付
    addOrder.setPay_method("2");
    addOrder.setRemark("");
    addOrder.setSendstarttime("");
    return sfExpressService.addOrder(addOrder);
}


@RequestMapping("/deliver.html")
public String deliver(ModelMap map,Authentication authentication){
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    map.addAttribute("brands", brandService.findByIds(brandIds));
    return "deliver";
}


@ResponseBody
@RequestMapping("/deliver-address")
public PhAddress userAddr(String orderNo){
    PhOrderInfo info = phOrderInfoService.findByOrderNo(orderNo);
    if (info != null) {
        return addressService.findOne(info.getAddressId());
    }
    return null;
}


public PhOrderExpressInfo createExpressOfPlatform(PhOrderInfo orderInfo){
    // 保存订单物流
    PhAddress toAddress = addressService.findOne(orderInfo.getAddressId());
    PhAddress offwayAddress = addressService.findOne(1L);
    PhOrderExpressInfo phOrderExpressInfo = new PhOrderExpressInfo();
    phOrderExpressInfo.setCreateTime(new Date());
    phOrderExpressInfo.setExpressOrderNo(phOrderInfoService.generateOrderNo("SF"));
    phOrderExpressInfo.setFromPhone(offwayAddress.getPhone());
    phOrderExpressInfo.setFromCity(offwayAddress.getCity());
    phOrderExpressInfo.setFromContent(offwayAddress.getContent());
    phOrderExpressInfo.setFromCounty(offwayAddress.getCounty());
    phOrderExpressInfo.setFromProvince(offwayAddress.getProvince());
    phOrderExpressInfo.setFromRealName(offwayAddress.getRealName());
    phOrderExpressInfo.setOrderNo(orderInfo.getOrderNo());
    phOrderExpressInfo.setStatus("0");
    phOrderExpressInfo.setToPhone(toAddress.getPhone());
    phOrderExpressInfo.setToCity(toAddress.getCity());
    phOrderExpressInfo.setToContent(toAddress.getContent());
    phOrderExpressInfo.setToCounty(toAddress.getCounty());
    phOrderExpressInfo.setToProvince(toAddress.getProvince());
    phOrderExpressInfo.setToRealName(toAddress.getRealName());
    phOrderExpressInfo.setType("0");
    phOrderExpressInfo.setReturnId(1L);
    return phOrderExpressInfo;
}


@ResponseBody
@RequestMapping("/deliver-address-get")
public String getUserAddr(String orderNo){
    PhOrderInfo info = phOrderInfoService.findByOrderNo(orderNo);
    if (info != null) {
        if (info.getAddressId() != 1L) {
            PhAddress address = addressService.findOne(info.getAddressId());
            return StringUtils.trimToEmpty(address.getProvince()) + StringUtils.trimToEmpty(address.getCity()) + StringUtils.trimToEmpty(address.getCounty()) + address.getContent() + address.getRealName() + address.getPhone();
        } else {
            PhOrderExpressInfo expressInfo = phOrderExpressInfoService.findByOrderNoAndType(orderNo, "0");
            return StringUtils.trimToEmpty(expressInfo.getToProvince()) + StringUtils.trimToEmpty(expressInfo.getToCity()) + StringUtils.trimToEmpty(expressInfo.getToCounty()) + expressInfo.getToContent() + expressInfo.getToRealName() + expressInfo.getToPhone();
        }
    }
    return "";
}


}