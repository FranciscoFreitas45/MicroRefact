import cn.offway.athena.domain.PhAddressBrand;
import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhBrand;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.PhAddressBrandService;
import cn.offway.athena.service.PhBrandService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util;
@Controller
public class BrandController {

 private  Logger logger;

@Autowired
 private  PhBrandService phBrandService;

@Autowired
 private  PhAddressBrandService addressBrandService;

@Autowired
 private  QiniuProperties qiniuProperties;


public PhAddressBrand saveAddress(String address_jsonStr,Long addrId,Long bid){
    PhAddressBrand address = new PhAddressBrand();
    address.setBrand(bid);
    JSONObject addrObj = JSON.parseObject(address_jsonStr);
    address.setProvince(addrObj.getString("province"));
    address.setCity(addrObj.getString("city"));
    address.setCounty(addrObj.getString("county"));
    address.setContent(addrObj.getString("content"));
    address.setRealName(addrObj.getString("realName"));
    address.setPhone(addrObj.getString("phone"));
    address.setRemark(address_jsonStr);
    if (addrId != null) {
        address.setId(addrId);
    } else {
        address.setCreateTime(new Date());
    }
    return addressBrandService.save(address);
}


@ResponseBody
@PostMapping("/brand-save")
public boolean save(PhBrand phBrand,String address_send_jsonStr,String address_back_jsonStr){
    try {
        phBrand.setCreateTime(new Date());
        phBrand.setStatus("1");
        PhBrand brandSaved = phBrandService.save(phBrand);
        // 发货地址
        if (StringUtils.isNotBlank(address_send_jsonStr)) {
            PhAddressBrand addressObj = saveAddress(address_send_jsonStr, brandSaved.getAddrId(), brandSaved.getId());
            brandSaved.setAddrId(addressObj.getId());
        }
        // 退货地址
        if (StringUtils.isNotBlank(address_back_jsonStr)) {
            PhAddressBrand addressObjBack = saveAddress(address_back_jsonStr, brandSaved.getReturnAddrId(), brandSaved.getId());
            brandSaved.setReturnAddrId(addressObjBack.getId());
        }
        phBrandService.save(brandSaved);
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("保存品牌异常,{}", JSON.toJSONString(phBrand), e);
        return false;
    }
}


@ResponseBody
@PostMapping("/brand-one")
public Map<String,Object> findOne(Long id){
    PhBrand brand = phBrandService.findOne(id);
    ObjectMapper mapper = new ObjectMapper();
    if (brand != null) {
        Map<String, Object> map = mapper.convertValue(brand, Map.class);
        PhAddressBrand address;
        if (brand.getAddrId() == null) {
            address = new PhAddressBrand();
        } else {
            address = addressBrandService.findOne(brand.getAddrId());
        }
        map.put("address", address);
        PhAddressBrand addressBack;
        if (brand.getReturnAddrId() == null) {
            addressBack = new PhAddressBrand();
        } else {
            addressBack = addressBrandService.findOne(brand.getReturnAddrId());
        }
        map.put("addressBack", addressBack);
        return map;
    }
    return null;
}


@ResponseBody
@GetMapping("/brand-showImgId")
public List<PhBrand> findByShowImgId(Long showImgId){
    return phBrandService.findByShowImgId(showImgId);
}


@ResponseBody
@PostMapping("/brand-update")
public boolean update(Long[] ids,String status){
    List<PhBrand> brands = phBrandService.findByIds(Arrays.asList(ids));
    for (PhBrand brand : brands) {
        if (StringUtils.isNotBlank(status)) {
            brand.setStatus(status);
        }
    }
    phBrandService.save(brands);
    return true;
}


@RequestMapping("/brand.html")
public String brand(ModelMap map,Authentication authentication){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
        map.addAttribute("isAdmin", "1");
    } else {
        map.addAttribute("isAdmin", "0");
    }
    return "brand";
}


@ResponseBody
@RequestMapping("/brand-data")
public Map<String,Object> stockData(HttpServletRequest request,Long id,String name,int sEcho,int iDisplayStart,int iDisplayLength,Authentication authentication){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds;
    if (phAdmin.getRoleIds().contains(BigInteger.ONE)) {
        brandIds = null;
    } else {
        brandIds = phAdmin.getBrandIds();
    }
    PageRequest pr = new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName);
    Page<PhBrand> pages = phBrandService.findByPage(id, null != name ? name.trim() : name, brandIds, pr);
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