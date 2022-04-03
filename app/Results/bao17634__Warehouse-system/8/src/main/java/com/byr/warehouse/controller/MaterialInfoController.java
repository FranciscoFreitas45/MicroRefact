package com.byr.warehouse.controller;
 import com.byr.warehouse.dao.CommonRepository;
import com.byr.warehouse.dao.MaterialInfoRepository;
import com.byr.warehouse.pojo.MaterialInfo;
import com.byr.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;
@Controller
@RequestMapping("/materialInfo")
public class MaterialInfoController {

 private Logger logger;

 private  MaterialInfoRepository materialInfoRepository;

@Resource
 private  JdbcTemplate jdbcTemplate;

@Autowired
 private  CommonRepository<MaterialInfo> commonRepository;

 private  Integer pagesize;


@RequestMapping("/save")
public String saveMaterialInfo(MaterialInfo materialInfo){
    materialInfoRepository.save(materialInfo);
    return "redirect:materialInfo/getAll?pagenum=1";
}


@RequestMapping("/getAll")
public String getAll(MaterialInfo materialInfo,int pagenum,ModelMap modelMap){
    String page = "stores_list";
    if (materialInfo != null) {
        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(materialInfo, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" AND Status =='已确认'");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(MaterialInfo.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) + "," + pagesize);
        List<MaterialInfo> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(MaterialInfo.class));
        modelMap.addAttribute("applys", applyEnters);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    } else {
        Pageable pageable = PageRequest.of(pagenum, pagesize);
        Page<MaterialInfo> pager = materialInfoRepository.findAll(pageable);
        modelMap.addAttribute("applys", pager.getContent());
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", pager.getTotalPages());
    }
    return page;
}


@RequestMapping("/update")
public String updateMaterialInfo(MaterialInfo materialInfo){
    materialInfoRepository.save(materialInfo);
    return "redirect:materialInfo/getAll?pagenum=1";
}


@RequestMapping("/delete")
public String deleteMaterialInfo(int id){
    MaterialInfo materialInfo = materialInfoRepository.findMaterialInfoByid(id);
    materialInfoRepository.delete(materialInfo);
    return "redirect:materialInfo/getAll?pagenum=1";
}


}