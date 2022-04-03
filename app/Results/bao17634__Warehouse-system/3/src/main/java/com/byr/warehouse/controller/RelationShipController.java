package com.byr.warehouse.controller;
 import com.byr.warehouse.dao.CommonRepository;
import com.byr.warehouse.dao.RelationShipRepository;
import com.byr.warehouse.pojo.RelationShip;
import com.byr.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;
@Controller
public class RelationShipController {

 private Logger logger;

@Autowired
 private  RelationShipRepository relationShipRepository;

@Resource
 private  JdbcTemplate jdbcTemplate;

@Autowired
 private  CommonRepository<RelationShip> commonRepository;

 private  Integer pagesize;


@RequestMapping(value = "/relationship-save", method = { RequestMethod.GET, RequestMethod.POST })
public String saveRelationShip(RelationShip relationShip){
    relationShipRepository.save(relationShip);
    return "redirect:/relationship-findAll?pagenum=1";
}


@RequestMapping(value = "/relationship-findAll", method = { RequestMethod.GET, RequestMethod.POST })
public String getAll(RelationShip relationShip,int pagenum,ModelMap modelMap){
    String page = "supplier_relation";
    StringBuffer sql = null;
    try {
        sql = commonRepository.getFiledValues(relationShip, pagenum);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
    sql.append(" 1 = 1");
    int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationShip.class)).size();
    sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
    List<RelationShip> ships = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(RelationShip.class));
    logger.debug("显示仓库列表：" + ships);
    modelMap.addAttribute("ships", ships);
    modelMap.addAttribute("page", pagenum);
    modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    return page;
}


@ResponseBody
@RequestMapping("/relationship-getById")
public RelationShip getRelationshipById(int id){
    RelationShip relationShipByid = relationShipRepository.findRelationShipByid(id);
    System.err.println("获取关系详情" + relationShipByid);
    return relationShipByid;
}


@RequestMapping("/relationship-delete")
public String deleteRelationShip(int id){
    RelationShip relationShip = relationShipRepository.findRelationShipByid(id);
    relationShipRepository.delete(relationShip);
    return "redirect:/relationship-findAll?pagenum=1";
}


}