package com.byr.warehouse.controller;
 import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.byr.warehouse.Service.ApplyEnterService;
import com.byr.warehouse.Service.ExcelService;
import com.byr.warehouse.Service.LogService;
import com.byr.warehouse.constant.ApplyStatus;
import com.byr.warehouse.constant.Operation;
import com.byr.warehouse.dao.ApplyEnterRepository;
import com.byr.warehouse.dao.CommonRepository;
import com.byr.warehouse.dao.EntrepotStatusRepository;
import com.byr.warehouse.myexception.StoreException;
import com.byr.warehouse.pojo.ApplyEnter;
import com.byr.warehouse.pojo.EntrepotStatus;
import com.byr.warehouse.pojo.User;
import com.byr.warehouse.sheduler.DaliyComputeShedule;
import com.byr.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation;
import org.springframework.web.multipart.MultipartFile;
import java.io;
import java.util.Date;
import java.util.List;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.DaliyComputeShedule;
import com.byr.warehouse.Interface.LogService;
import com.byr.warehouse.DTO.User;
@Controller
public class ApplyEnterController {

 private Logger logger;

@Autowired
 private  EntrepotStatusRepository entrepotStatusRepository;

@Autowired
 private  DaliyComputeShedule daliyComputeShedule;

@Autowired
 private  ApplyEnterService applyEnterService;

@Autowired
 private  ExcelService<ApplyEnter> excelService;

@Autowired
 private  ApplyEnterRepository applyEnterRepository;

@Resource
 private  ResourceLoader resourceLoader;

@Resource
 private  JdbcTemplate jdbcTemplate;

@Autowired
 private  CommonRepository<ApplyEnter> commonRepository;

@Autowired
 private  LogService logService;

 private  Integer pagesize;


@RequestMapping(value = "/applyin-addapply")
public String saveApply(ApplyEnter applyEnter,BindingResult bindingResult,HttpServletRequest request){
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        throw new StoreException("用户尚未登录");
    }
    System.err.println("当前操作用户:" + user);
    // 保存
    applyEnter.setApplyPersonId(user.getUsername());
    applyEnter.setStatus(ApplyStatus.WAIT_TO_ENSURE.toString());
    applyEnter.setApplyDate(new Date());
    System.err.println("插入数据" + applyEnter);
    applyEnterRepository.save(applyEnter);
    logService.saveOpLog(user.getUsername(), Operation.APPLY_ENTER.getOperation(), "成功", JSON.toJSONString(applyEnter));
    String page = "entrance_apply_wait";
    return "redirect:/applyin-getNotAllowApplyEnter?pagenum=1";
}


@RequestMapping(value = "/applyin-getNotAllowApplyEnter", method = { RequestMethod.GET, RequestMethod.POST })
public String getNotAllowed(ApplyEnter applyEnter,int pagenum,ModelMap modelMap,HttpServletRequest request){
    String page = "entrance_apply";
    User user = (User) request.getSession().getAttribute("user");
    System.err.println("当前操作人：" + user);
    if (user == null) {
        throw new StoreException("用户尚未登录");
    }
    Long id = user.getId();
    if (applyEnter != null) {
        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(applyEnter, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" Status !='已确认' AND applyPersonId = '" + user.getUsername() + "'");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
        List<ApplyEnter> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class));
        logger.debug("未确认的申请" + applyEnters);
        modelMap.addAttribute("applys", applyEnters);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    } else {
        Pageable pageable = PageRequest.of(pagenum, pagesize);
        Page<ApplyEnter> pager = applyEnterRepository.findApplyEnterByStatusNot(ApplyStatus.ENSURE, pageable);
        modelMap.addAttribute("applys", pager.getContent());
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", pager.getTotalPages());
    }
    modelMap.addAttribute("username", user.getUsername());
    return page;
}


@RequestMapping(value = "/applyin-toBeEnsured", method = { RequestMethod.GET, RequestMethod.POST })
public String getNotAllowedByEnsure(ApplyEnter applyEnter,int pagenum,ModelMap modelMap,HttpServletRequest request){
    String page = "entrance_apply_wait";
    if (applyEnter != null) {
        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(applyEnter, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" Status ='待审核'");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
        List<ApplyEnter> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class));
        logger.debug("待处理的入库申请" + applyEnters);
        modelMap.addAttribute("applys", applyEnters);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    } else {
        Pageable pageable = PageRequest.of(pagenum, pagesize);
        Page<ApplyEnter> pager = applyEnterRepository.findApplyEnterByStatusNot(ApplyStatus.ENSURE, pageable);
        modelMap.addAttribute("applys", pager.getContent());
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", pager.getTotalPages());
    }
    return page;
}


@RequestMapping("/applyin-updateStatus")
public String ensureApply(int enterId,HttpServletRequest request){
    String page = "entrance_apply_wait";
    logger.debug("查找ID为" + enterId + "的入库单");
    ApplyEnter apply = applyEnterRepository.findApplyEnterByenterId(enterId);
    apply.setStatus(ApplyStatus.ENSURE);
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        throw new StoreException("用户尚未登录");
    }
    apply.setEnsurePersonId(user.getUsername());
    apply.setApplyDate(new Date());
    // 更新
    applyEnterRepository.save(apply);
    // 添加到仓库中
    EntrepotStatus entrepotStatus = new EntrepotStatus();
    entrepotStatus.setEnterCode(apply.getEnterCode());
    entrepotStatus.setMaterialCode(apply.getMaterialCode());
    entrepotStatus.setEntranceDate(new Date());
    entrepotStatus.setUpdateDate(new Date());
    entrepotStatus.setProduceDate(apply.getProduceDate());
    entrepotStatus.setGoodsStatus("未检验");
    entrepotStatus.setEntrepotType(apply.getTreasury());
    entrepotStatus.setMaterialSpec(apply.getSpec());
    entrepotStatus.setProductName(apply.getProductName());
    entrepotStatus.setSupplyName(apply.getGoodsFrom());
    entrepotStatus.setTaxCode(apply.getBillNumber());
    entrepotStatus.setTotalSize(apply.getNumber());
    // 发票号码
    entrepotStatus.setTaxCode(apply.getBillNumber());
    entrepotStatus.setPosition(apply.getPosition());
    entrepotStatus.setUpdateDate(new Date());
    entrepotStatusRepository.save(entrepotStatus);
    logService.saveOpLog(user.getUsername(), Operation.ENSURE_ENTER.getOperation(), "成功", JSON.toJSONString(apply));
    return "redirect:/applyin-toBeEnsured?pagenum=1";
}


@RequestMapping(value = "/applyin-batchApply")
public String batchApply(MultipartFile file,HttpServletRequest request){
    List<ApplyEnter> applyEnters = excelService.ImportExcelService(file, new ApplyEnter());
    User user1 = (User) request.getSession().getAttribute("user");
    if (user1 == null) {
        throw new StoreException("用户尚未登录");
    }
    logger.debug(new Date() + "批量导入入库申请：" + new Gson().toJson(applyEnters));
    String success = "";
    for (ApplyEnter applyEnter : applyEnters) {
        User user = (User) request.getSession().getAttribute("user");
        applyEnter.setApplyPersonId(user.getUsername());
        applyEnter.setApplyDate(new Date());
        applyEnter.setStatus("待审核");
        applyEnterRepository.save(applyEnter);
        success = success + applyEnter.getEnterCode() + "--";
    }
    logService.saveOpLog(user1.getUsername(), Operation.APPLY_ENTER_BATCH.getOperation(), "成功", JSON.toJSONString(applyEnters));
    request.getSession().setAttribute("message", "导入成功的记录(入库编号)：" + success);
    return "redirect:/applyin-getNotAllowApplyEnter?pagenum=1";
}


@RequestMapping(value = "/applyin-getHistory", method = { RequestMethod.GET, RequestMethod.POST })
public String FindAllApplyEnter(ApplyEnter applyEnter,int pagenum,ModelMap modelMap){
    String page = "entrance_apply_history";
    if (applyEnter != null) {
        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(applyEnter, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" Status = '已确认'");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
        List<ApplyEnter> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class));
        logger.debug("已确认的申请" + applyEnters);
        modelMap.addAttribute("applys", applyEnters);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    } else {
        Pageable pageable = PageRequest.of(pagenum, pagesize);
        Page<ApplyEnter> pager = applyEnterRepository.findApplyEnterByStatus(ApplyStatus.ENSURE, pageable);
        modelMap.addAttribute("applys", pager.getContent());
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", pager.getTotalPages());
    }
    return page;
}


@RequestMapping("/applyin-deleteById")
public String deleteApplyById(int enterId,HttpServletRequest request){
    // System.err.println("删除ID"+enterId);
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        throw new StoreException("用户尚未登录");
    }
    ApplyEnter applyEnter = applyEnterRepository.findApplyEnterByenterId(enterId);
    applyEnterRepository.delete(applyEnter);
    logService.saveOpLog(user.getUsername(), Operation.DELETE_APPLY_ENTER.getOperation(), "成功", JSON.toJSONString(applyEnter));
    return "redirect:/applyin-getNotAllowApplyEnter?pagenum=1";
}


@RequestMapping(value = "/applyin-downloadExcel")
public void doloadExcel(HttpServletResponse response,HttpServletRequest req){
    InputStream inputStream = null;
    ServletOutputStream servletOutputStream = null;
    try {
        String filename = "批量入库申请模板.xlsx";
        String path = "files/批量入库申请模板.xlsx";
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:" + path);
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.addHeader("charset", "utf-8");
        response.addHeader("Pragma", "no-cache");
        String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
        inputStream = resource.getInputStream();
        servletOutputStream = response.getOutputStream();
        IOUtils.copy(inputStream, servletOutputStream);
        response.flushBuffer();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (servletOutputStream != null) {
                servletOutputStream.close();
                servletOutputStream = null;
            }
            if (inputStream != null) {
                inputStream.close();
                inputStream = null;
            }
            // 召唤jvm的垃圾回收器
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


@ResponseBody
@RequestMapping(value = "/applyin-todayCount")
public int getTodayApplyEnters(){
    System.out.println("总数" + applyEnterService.getNumberOfTodayApplyEnter());
    daliyComputeShedule.computeCount();
    return applyEnterService.getNumberOfTodayApplyEnter();
}


@RequestMapping(value = "/getApplyEnterById", method = { RequestMethod.GET, RequestMethod.POST })
@ResponseBody
public ApplyEnter getApplyEnterById(int enterId){
    return applyEnterRepository.findApplyEnterByenterId(enterId);
}


@RequestMapping("/applyin-his-deleteById")
public String deleteHisApplyById(int enterId,HttpServletRequest request){
    // System.err.println("删除ID"+enterId);
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        throw new StoreException("用户尚未登录");
    }
    ApplyEnter applyEnter = applyEnterRepository.findApplyEnterByenterId(enterId);
    applyEnterRepository.delete(applyEnter);
    logService.saveOpLog(user.getUsername(), Operation.DELETE_APPLY_ENTER_HIS.getOperation(), "成功", JSON.toJSONString(applyEnter));
    return "redirect:/applyin-getHistory?pagenum=1";
}


@RequestMapping(value = "/applyin-search", method = { RequestMethod.GET, RequestMethod.POST })
public String doSearch(ApplyEnter applyEnter,ModelMap modelMap,HttpServletRequest request){
    String searchItem = request.getParameter("searchItem");
    String searchValue = request.getParameter("searchValue");
    Integer pagenum = Integer.parseInt(request.getParameter("pagenum"));
    Integer type = Integer.parseInt(request.getParameter("type"));
    String page = "entrance_apply_wait";
    System.out.print(searchItem + "   " + searchValue);
    StringBuffer sql = null;
    try {
        sql = commonRepository.getFiledValues(applyEnter, pagenum);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
    if (searchValue != null || !"".equals(searchValue)) {
        if (type == 1) {
            page = "entrance_apply_wait";
            sql.append(searchItem + " like '%" + searchValue + "%' AND Status !='" + "已确认'");
        } else {
            page = "entrance_apply_history";
            sql.append(searchItem + " like '%" + searchValue + "%' AND Status ='" + "已确认'");
        }
    } else {
        sql.append(" 1 = 1");
    }
    int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class)).size();
    List<ApplyEnter> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyEnter.class));
    logger.debug("待处理的入库申请" + applyEnters);
    modelMap.addAttribute("applys", applyEnters);
    modelMap.addAttribute("searchValue", searchValue);
    modelMap.addAttribute("searchItem", searchItem);
    modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    return page;
}


@RequestMapping(value = "applyin-turndown")
public String turnDownTheApply(int enterId,HttpServletRequest request){
    String page = "entrance_apply_wait";
    ApplyEnter apply = applyEnterRepository.findApplyEnterByenterId(enterId);
    apply.setStatus(ApplyStatus.REFUSED);
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
        throw new StoreException("用户尚未登录");
    }
    apply.setEnsurePersonId(user.getUsername());
    // 更新
    applyEnterRepository.save(apply);
    logService.saveOpLog(user.getUsername(), Operation.REFUSE_ENTER.getOperation(), "成功", JSON.toJSONString(apply));
    return "redirect:/applyin-toBeEnsured?pagenum=1";
}


}