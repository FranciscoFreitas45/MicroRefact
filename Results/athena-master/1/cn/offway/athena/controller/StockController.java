import cn.offway.athena.domain.PhAdmin;
import cn.offway.athena.domain.PhGoods;
import cn.offway.athena.domain.PhGoodsStock;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.PhBrandService;
import cn.offway.athena.service.PhGoodsService;
import cn.offway.athena.service.PhGoodsStockService;
import com.alibaba.fastjson.JSON;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util;
@Controller
public class StockController {

 private  Logger logger;

@Autowired
 private  PhGoodsStockService phGoodsStockService;

@Autowired
 private  PhBrandService phBrandService;

@Autowired
 private  PhGoodsService phGoodsService;

@Autowired
 private  QiniuProperties qiniuProperties;


@ResponseBody
@PostMapping("/stock-image")
public String image(String color,Long goodsId){
    return phGoodsStockService.findImage(color, goodsId);
}


@ResponseBody
@PostMapping("/stock-save")
public boolean save(PhGoodsStock phGoodsStock){
    try {
        phGoodsStock.setColor(null == phGoodsStock.getColor() ? null : phGoodsStock.getColor().trim());
        phGoodsStock.setSize(null == phGoodsStock.getSize() ? null : phGoodsStock.getSize().trim());
        phGoodsStock.setSku(null == phGoodsStock.getSku() ? null : phGoodsStock.getSku().trim());
        Long goodsId = phGoodsStock.getGoodsId();
        boolean check = true;
        if (null != phGoodsStock.getId()) {
            PhGoodsStock goodsStock = phGoodsStockService.findOne(phGoodsStock.getId());
            if (goodsStock.getColor().equals(phGoodsStock.getColor()) && goodsStock.getSize().equals(phGoodsStock.getSize())) {
                check = false;
            }
        }
        if (check) {
            int count = phGoodsStockService.countByGoodsIdAndColorAndSize(goodsId, phGoodsStock.getColor(), phGoodsStock.getSize());
            if (count > 0) {
                return false;
            }
        }
        PhGoods phGoods = phGoodsService.findOne(goodsId);
        phGoodsStock.setGoodsName(phGoods.getName());
        phGoodsStock.setGoodsImage(phGoods.getImage());
        phGoodsStock.setBrandId(phGoods.getBrandId());
        phGoodsStock.setBrandLogo(phGoods.getBrandLogo());
        phGoodsStock.setBrandName(phGoods.getBrandName());
        phGoodsStock.setIsOffway(phGoods.getIsOffway());
        phGoodsStock.setCategory(phGoods.getCategory());
        phGoodsStock.setType(phGoods.getType());
        phGoodsStock.setCreateTime(new Date());
        phGoodsStockService.save(phGoodsStock);
        phGoodsStockService.updateImage(goodsId, phGoodsStock.getColor(), phGoodsStock.getImage());
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("保存库存异常,{}", JSON.toJSONString(phGoodsStock), e);
        return false;
    }
}


@ResponseBody
@PostMapping("/stock-one")
public PhGoodsStock findOne(Long id){
    return phGoodsStockService.findOne(id);
}


@ResponseBody
@RequestMapping("/stock-list")
public List<PhGoodsStock> findByGoodsId(Long id){
    return phGoodsStockService.findByPid(id);
}


@RequestMapping("/stock.html")
public String stock(ModelMap map,Authentication authentication){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    map.addAttribute("brands", phBrandService.findByIds(brandIds));
    return "stock";
}


@ResponseBody
@RequestMapping("/stock-data")
public Map<String,Object> stockData(HttpServletRequest request,String sku,Long brandId,String brandName,Long goodsId,String goodsName,String isOffway,String color,String size,Authentication authentication){
    String sortCol = request.getParameter("iSortCol_0");
    String sortName = request.getParameter("mDataProp_" + sortCol);
    String sortDir = request.getParameter("sSortDir_0");
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    PhAdmin phAdmin = (PhAdmin) authentication.getPrincipal();
    List<Long> brandIds = phAdmin.getBrandIds();
    Page<PhGoodsStock> pages = phGoodsStockService.findByPage(sku.trim(), brandId, null != brandName ? brandName.trim() : brandName, goodsId, null != goodsName ? goodsName.trim() : goodsName, isOffway.trim(), color.trim(), size.trim(), brandIds, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, Direction.fromString(sortDir), sortName));
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
@PostMapping("/stock-delete")
public boolean delete(Long[] ids){
    int count = phGoodsStockService.deleteByIds(Arrays.asList(ids));
    return count > 0;
}


}