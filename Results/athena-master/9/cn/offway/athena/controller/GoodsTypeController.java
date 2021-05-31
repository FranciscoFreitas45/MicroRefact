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
import java.util;
@Controller
@RequestMapping
public class GoodsTypeController {

 private  Logger logger;

@Autowired
 private  QiniuProperties qiniuProperties;

@Autowired
 private  PhGoodsTypeService goodsTypeService;

@Autowired
 private  PhGoodsCategoryService goodsCategoryService;


@ResponseBody
@RequestMapping("/goodsType_listAll")
public List<Object> getAll(){
    List<Object> ret = new ArrayList<>();
    List<PhGoodsType> typeList = goodsTypeService.findAll();
    List<PhGoodsCategory> categoryList = goodsCategoryService.findAll();
    ret.addAll(typeList);
    ret.addAll(categoryList);
    return ret;
}


@ResponseBody
@RequestMapping("/goodsType_list")
public Map<String,Object> getList(HttpServletRequest request){
    int sEcho = Integer.parseInt(request.getParameter("sEcho"));
    int iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
    int iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
    Sort sort = new Sort("id");
    Page<PhGoodsType> pages = goodsTypeService.findAll(new PageRequest(iDisplayStart == 0 ? 0 : iDisplayStart / iDisplayLength, iDisplayLength < 0 ? 9999999 : iDisplayLength, sort));
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
@RequestMapping("/goodsType_find")
public Map<String,Object> find(Long id){
    Map<String, Object> map = new HashMap<>();
    PhGoodsType goodsType = goodsTypeService.findOne(id);
    if (goodsType != null) {
        map.put("main", goodsType);
        map.put("categoryList", goodsCategoryService.findByPid(goodsType.getId()));
    }
    return map;
}


@ResponseBody
@RequestMapping("/goodsType_get")
public PhGoodsType get(Long id){
    return goodsTypeService.findOne(id);
}


@ResponseBody
@RequestMapping("/goodsType_save")
public boolean save(PhGoodsType goodsType){
    goodsType.setCreateTime(new Date());
    goodsTypeService.save(goodsType);
    return true;
}


@RequestMapping("/goodsType.html")
public String index(ModelMap map){
    map.addAttribute("qiniuUrl", qiniuProperties.getUrl());
    return "goodsType_index";
}


@ResponseBody
@RequestMapping("/goodsType_del")
public boolean delete(Long[] ids){
    for (Long id : ids) {
        goodsTypeService.del(id);
        goodsCategoryService.delByPid(id);
    }
    return true;
}


}