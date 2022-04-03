package org.live.school.controller;
 import org.live.common.response.DataTableModel;
import org.live.common.response.ResponseModel;
import org.live.common.response.SimpleResponseModel;
import org.live.common.systemlog.LogLevel;
import org.live.common.systemlog.OperateType;
import org.live.common.systemlog.SystemLog;
import org.live.common.utils.CopyPropertiesUtils;
import org.live.school.entity.Department;
import org.live.school.entity.Major;
import org.live.school.service.DepartmentService;
import org.live.school.service.MajorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("school/")
public class MajorController {

 private  String MODULE;

 private  Logger LOGGER;

@Autowired
 private  DepartmentService departmentService;

@Autowired
 private  MajorService majorService;


@SystemLog(description = "请求专业数据", logLevel = LogLevel.WARN, operateType = OperateType.QUERY)
@RequestMapping(value = "major/data", method = RequestMethod.POST)
@ResponseBody
public DataTableModel data(HttpServletRequest request){
    return majorService.findPage(request);
}


@SystemLog(description = "添加专业记录", logLevel = LogLevel.WARN, operateType = OperateType.ADD)
@RequestMapping(value = "major", method = RequestMethod.POST)
@ResponseBody
public ResponseModel<Object> save(Major major){
    // 添加创建时间
    major.setCreateTime(new Date());
    /**
     * 保存专业记录 *
     */
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        model.setData(majorService.save(major));
        model.success();
    } catch (Exception e) {
        LOGGER.error("添加专业记录异常", e);
        model.error();
    }
    return model;
}


@SystemLog(description = "修改专业记录", logLevel = LogLevel.WARN, operateType = OperateType.UPDATE)
@RequestMapping(value = "major", method = RequestMethod.PUT)
@ResponseBody
public ResponseModel<Object> update(Major major){
    Major entity = null;
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        if (major.getId() != null) {
            // 取得原始记录
            entity = majorService.get(major.getId());
            // 更新记录
            CopyPropertiesUtils.copyPropertiesIgnoreNull(entity, major);
        } else {
            /**
             * id为空异常 *
             */
            model.error();
            return model;
        }
        /**
         * 保存专业记录 *
         */
        model.setData(majorService.save(entity));
        model.success();
    } catch (Exception e) {
        LOGGER.error("添加专业记录异常", e);
        model.error();
    }
    return model;
}


@SystemLog(description = "删除多个专业记录", logLevel = LogLevel.ERROR, operateType = OperateType.DELETE)
@RequestMapping(value = "major", method = RequestMethod.DELETE)
@ResponseBody
public ResponseModel del(List<String> ids){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        for (String id : ids) {
            majorService.delete(id);
        }
        model.success();
    } catch (Exception e) {
        LOGGER.error("删除专业记录异常", e);
        model.error();
    }
    return model;
}


@RequestMapping(value = "major/page", method = RequestMethod.GET)
public ModelAndView page(ModelAndView mv){
    List<Department> departments = departmentService.findAll();
    mv.addObject("departments", departments);
    mv.setViewName(MODULE + "/major");
    return mv;
}


}