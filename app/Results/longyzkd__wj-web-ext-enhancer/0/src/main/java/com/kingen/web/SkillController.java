package com.kingen.web;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.PersonSkillScore;
import com.kingen.bean.SkillCat;
import com.kingen.service.skill.SkillService;
import com.kingen.util.FastJsonUtil;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.util.TreeConverter;
import com.kingen.util.mapper.BeanMapper;
import com.kingen.vo.SkillAndScoreVo;
import com.kingen.vo.TreeNode;
@Controller
@RequestMapping(value = "/skill")
public class SkillController extends CommonController{

 private  Logger logger;

@Autowired
 private  SkillService service;


@RequestMapping(value = "score/data")
public void scoredata(String userId,Page<SkillAndScoreVo> page,HttpServletResponse response){
    page = service.findPersonScore(page, userId);
    writeJson(response, page);
}


@RequestMapping(value = "/score")
@RequiresPermissions("skillScore:view")
@ControllerLogAnnotation(moduleName = "资源管理", option = "技能管理")
public String score(Model m,HttpServletResponse response){
    return "skill/score-list";
}


@RequestMapping(value = "/treeData")
@ResponseBody
public Object data(Page<SkillCat> page,HttpServletResponse response){
    List<SkillCat> all = service.list();
    List<TreeNode> allConverted = BeanMapper.mapList(all, TreeNode.class);
    Map<String, Object> json = Maps.newHashMapWithExpectedSize(1);
    // 必须要加chidlren,直接return toComplexJsonString(offices);不行.而且jsonString里面中文会变成???乱码
    json.put("children", TreeConverter.toComplexTree(allConverted));
    return json;
}


@ControllerLogAnnotation(moduleName = "资源管理-技能分类管理", option = "删除")
@RequestMapping(value = "deleteCascade")
public void deleteCascade(String id,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.delCascade(id);
        json = JsonResultBuilder.success(true).msg("删除成功").json();
    } catch (Exception e) {
        json = JsonResultBuilder.success(false).msg("系统出错").json();
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


@RequestMapping(value = "score/saveBatch")
@ControllerLogAnnotation(moduleName = "资源管理-技能管理", option = "保存")
public void saveBatch(String userId,String scores,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        List<PersonSkillScore> scoresCollect = FastJsonUtil.toList(scores, PersonSkillScore.class);
        service.saveBatch(userId, scoresCollect);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // service ：回滚、记录异常日志
        // TODO Auto-generated catch block
        e.printStackTrace();
        logger.error(e.getMessage());
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
    }
    writeJson(response, json);
}


@RequestMapping(value = "/one")
public void one(String id,HttpServletResponse response){
    SkillCat u = service.unique(id);
    writeJson(response, u);
}


@RequestMapping(value = "save")
@ControllerLogAnnotation(moduleName = "资源管理-技能分类管理", option = "新增")
public void save(SkillCat data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.add(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // service ：回滚、记录异常日志
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "update")
@ControllerLogAnnotation(moduleName = "资源管理-技能分类管理", option = "编辑")
public void update(SkillCat data,HttpServletResponse response){
    JSONObject json = new JSONObject();
    try {
        service.updateForm(data);
        json = JsonResultBuilder.success(true).msg("保存成功").json();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        json = JsonResultBuilder.success(false).msg(e.getMessage()).json();
        logger.error(e.getMessage());
    }
    writeJson(response, json);
}


@RequestMapping(value = "toEdit")
public String toEdit(String id,String pid,String action,HttpServletResponse response,HttpServletRequest request,Model model){
    model.addAttribute("action", action);
    // null的话 前台是空串
    model.addAttribute("id", id);
    // null的话 前台是空串
    model.addAttribute("pid", pid);
    return "skill/edit";
}


@RequestMapping(value = "/")
@RequiresPermissions("skill:view")
@ControllerLogAnnotation(moduleName = "资源管理", option = "技能分类管理")
public String execute(Model m,HttpServletResponse response){
    return "skill/tree";
}


}