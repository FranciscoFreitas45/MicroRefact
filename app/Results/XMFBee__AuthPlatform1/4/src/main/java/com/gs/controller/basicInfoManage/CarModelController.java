package com.gs.controller.basicInfoManage;
 import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.CarModel;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.CarBrandService;
import com.gs.service.CarModelService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gs.Interface.CarModelService;
@Controller
@RequestMapping("/carModel")
public class CarModelController {

 private  Logger logger;

@Resource
 private  CarModelService carModelService;


@ResponseBody
@RequestMapping(value = "addCarModel", method = RequestMethod.POST)
public ControllerResult add(HttpSession session,CarModel carModel){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员";
        if (RoleUtil.checkRoles(roles)) {
            if (carModel != null && !carModel.equals("")) {
                logger.info("添加汽车车型");
                carModelService.insert(carModel);
                return ControllerResult.getSuccessResult("添加汽车车型成功");
            } else {
                return ControllerResult.getFailResult("添加汽车车型失败");
            }
        } else {
            logger.info("此用户无拥有此方法角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
    }
}


@ResponseBody
@RequestMapping(value = "statusOperate", method = RequestMethod.POST)
public ControllerResult inactive(HttpSession session,String id,String status){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员";
        if (RoleUtil.checkRoles(roles)) {
            if (id != null && !id.equals("") && status != null && !status.equals("")) {
                if (status.equals("N")) {
                    carModelService.active(id);
                    logger.info("激活汽车车型成功");
                    return ControllerResult.getSuccessResult("激活汽车车型成功");
                } else {
                    carModelService.inactive(id);
                    logger.info("禁用汽车车型成功");
                    return ControllerResult.getSuccessResult("禁用汽车车型成功");
                }
            } else {
                return ControllerResult.getFailResult("操作失败");
            }
        } else {
            logger.info("此用户无拥有此方法角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
    }
}


@ResponseBody
@RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
public Pager4EasyUI<CarModel> queryByPagerDisable(HttpSession session,String pageNumber,String pageSize){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("分页查询所有被禁用的车型");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("user"));
            pager.setTotalRecords(carModelService.countByDisable((User) session.getAttribute("user")));
            List<CarModel> carModels = carModelService.queryByPagerDisable(pager);
            return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModels);
        } else {
            logger.info("此用户无拥有此方法角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "updateCarModel", method = RequestMethod.POST)
public ControllerResult update(HttpSession session,CarModel carModel){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员";
        if (RoleUtil.checkRoles(roles)) {
            if (carModel != null && !carModel.equals("")) {
                logger.info("修改汽车车型");
                carModelService.update(carModel);
                return ControllerResult.getSuccessResult("修改汽车车型成功");
            } else {
                return ControllerResult.getFailResult("修改汽车车型失败");
            }
        } else {
            logger.info("此用户无拥有此方法角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
    }
}


@ResponseBody
@RequestMapping(value = "queryAllCarBrand", method = RequestMethod.GET)
public List<ComboBox4EasyUI> queryAll(HttpSession session){
    /*if (SessionUtil.isLogin(session) || SessionUtil.isOwnerLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {*/
    logger.info("查询所有汽车车型");
    List<CarModel> carModels = carModelService.queryAll((User) session.getAttribute("user"));
    List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
    for (CarModel c : carModels) {
        ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
        comboBox4EasyUI.setId(c.getModelId());
        comboBox4EasyUI.setText(c.getModelName());
        comboxs.add(comboBox4EasyUI);
    }
    return comboxs;
/*} else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }*/
}


@ResponseBody
@RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
public Pager4EasyUI<CarModel> blurredQuery(HttpSession session,HttpServletRequest request,String pageNumber,String pageSize){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("汽车车型记录模糊查询");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("user"));
            String text = request.getParameter("text");
            String value = request.getParameter("value");
            if (text != null && text != "" && value != null && value != "") {
                List<CarModel> carModels = null;
                CarModel carModel = new CarModel();
                if (text.equals("车型名称/品牌名称")) {
                    carModel.setModelName(value);
                    carModel.setBrandId(value);
                } else if (text.equals("车型名称")) {
                    carModel.setModelName(value);
                } else if (text.equals("品牌名称")) {
                    carModel.setBrandId(value);
                }
                carModels = carModelService.blurredQuery(pager, carModel);
                pager.setTotalRecords(carModelService.countByBlurred(carModel, (User) session.getAttribute("user")));
                System.out.print(carModels);
                return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModels);
            } else {
                // 当在模糊查询输入框中输入的值为空时, 使它查询全部
                pager.setTotalRecords(carModelService.count((User) session.getAttribute("user")));
                List<CarModel> carModels = carModelService.queryByPager(pager);
                return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModels);
            }
        } else {
            logger.info("此用户无拥有供应商记录模糊查询角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "querymodelName", method = RequestMethod.POST)
public Map querymodelName(CarModel carModel){
    logger.info("此车型名称是否已存在此品牌名称");
    int countmodelName = carModelService.querymodelName(carModel.getModelName(), carModel.getModelId());
    Map<String, Boolean> map = new HashMap<String, Boolean>();
    if (countmodelName > 0)
        map.put("valid", false);
    else
        map.put("valid", true);
    return map;
}


@ResponseBody
@RequestMapping(value = "queryByBrandId/{id}", method = RequestMethod.GET)
public List<ComboBox4EasyUI> queryByBrandId(HttpSession session,String id){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            List<CarModel> carModels = carModelService.queryByBrandId(id);
            List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
            for (CarModel c : carModels) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(c.getModelId());
                comboBox4EasyUI.setText(c.getModelName());
                comboxs.add(comboBox4EasyUI);
            }
            return comboxs;
        } else {
            logger.info("此用户无拥有此方法角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "queryByPagerCarModel", method = RequestMethod.GET)
public Pager4EasyUI<CarModel> queryByPager(HttpSession session,String pageNumber,String pageSize){
    if (SessionUtil.isLogin(session)) {
        String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("分页查询所有车型");
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            pager.setUser((User) session.getAttribute("user"));
            pager.setTotalRecords(carModelService.count((User) session.getAttribute("user")));
            List<CarModel> carModels = carModelService.queryByPager(pager);
            return new Pager4EasyUI<CarModel>(pager.getTotalRecords(), carModels);
        } else {
            logger.info("此用户无拥有此方法角色");
            return null;
        }
    } else {
        logger.info("请先登录");
        return null;
    }
}


}