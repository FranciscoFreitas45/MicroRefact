import cn.offway.athena.domain.PhBanner;
import cn.offway.athena.domain.PhGoodsCategory;
import cn.offway.athena.domain.PhGoodsType;
import cn.offway.athena.properties.QiniuProperties;
import cn.offway.athena.service.PhGoodsCategoryService;
import cn.offway.athena.service.PhGoodsTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping
public class GoodsCategoryController {

 private  Logger logger;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  PhGoodsCategoryService goodsCategoryService;

@Autowired
 private  PhGoodsTypeService goodsTypeService;


@ResponseBody
@RequestMapping("/goodsCategory_top")
public boolean top(Long id,Long sort,Long theId){
    PhGoodsCategory phGoodsCategory = goodsCategoryService.findOne(id);
    if (phGoodsCategory != null) {
        goodsCategoryService.resort(sort, theId);
        phGoodsCategory.setSort(sort);
        goodsCategoryService.save(phGoodsCategory);
    }
    return true;
}


@ResponseBody
@RequestMapping("/goodsCategory_list")
public Map<String,Object> getList(HttpServletRequest request){
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    long id = Long.valueOf(request.getParameter("id"));
    Sort sort = new Sort("sort");
    Page<PhGoodsCategory> pages = goodsCategoryService.findByPid(id, new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort));
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
@RequestMapping("/goodsCategory_get")
public PhGoodsCategory get(Long id){
    return goodsCategoryService.findOne(id);
}


@ResponseBody
@RequestMapping("/goodsCategory_save")
public boolean save(PhGoodsCategory goodsCategory){
    goodsCategory.setCreateTime(new Date());
    PhGoodsType goodsType = goodsTypeService.findOne(goodsCategory.getGoodsType());
    if (goodsType != null) {
        goodsCategory.setGoodsTypeName(goodsType.getName());
    }
    goodsCategoryService.save(goodsCategory);
    return true;
}


@RequestMapping("/goodsCategory.html")
public String index(ModelMap map,Long id){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    map.addAttribute("theId", id);
    PhGoodsType goodsType = goodsTypeService.findOne(Long.valueOf(id));
    if (goodsType != null) {
        map.addAttribute("theName", goodsType.getName());
    }
    return "goodsCategory_index";
}


@ResponseBody
@RequestMapping("/goodsCategory_del")
public boolean delete(Long[] ids){
    for (Long id : ids) {
        goodsCategoryService.del(id);
    }
    return true;
}


}