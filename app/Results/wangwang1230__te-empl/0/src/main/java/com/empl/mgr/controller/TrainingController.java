package com.empl.mgr.controller;
 import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.empl.mgr.annotation.SecureValid;
import com.empl.mgr.constant.MethodType;
import com.empl.mgr.controller.support.AbstractController;
import com.empl.mgr.service.EmployeesService;
import com.empl.mgr.service.TrainingService;
import com.empl.mgr.support.JSONReturn;
@Scope
@Controller
@RequestMapping(value = "training")
public class TrainingController extends AbstractController{

@Autowired
 private  TrainingService trainingService;

@Autowired
 private  EmployeesService employeesService;


@ResponseBody
@RequestMapping(value = "modifyTraining")
@SecureValid(code = "01003", desc = "修改培训项目", type = MethodType.MODIFY)
public JSONReturn modifyTraining(long id,String name,String description,String startTime,String endTime,boolean isInsertAttend,HttpSession httpSession){
    return trainingService.modifyTraining(id, name, description, startTime, endTime, isInsertAttend, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findTrainingList")
@SecureValid(code = "01003", desc = "获取培训项目列表", type = MethodType.FIND)
public JSONReturn findTrainingList(String searchValue,int page,HttpSession httpSession){
    return trainingService.findTrainingList(searchValue, page, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "addTraining")
@SecureValid(code = "01003", desc = "添加培训项目", type = MethodType.ADD)
public JSONReturn addTraining(String name,String description,String startTime,String endTime,boolean isInsertAttend,HttpSession httpSession){
    return trainingService.addTraining(name, description, startTime, endTime, isInsertAttend, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findEmployeesList")
@SecureValid(code = "01003", desc = "搜索员工列表", type = MethodType.FIND)
public JSONReturn findEmployeesList(long trainingId,long deptId,String searchVal,HttpSession http){
    return employeesService.findEmployeesList(trainingId, deptId, searchVal, acctName(http));
}


@ResponseBody
@RequestMapping(value = "start")
@SecureValid(code = "01003", desc = "开始培训项目", type = MethodType.MODIFY)
public JSONReturn start(long id,HttpSession httpSession){
    return trainingService.start(id, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "evaluationEmployeesTraining")
@SecureValid(code = "01003", desc = "为员工培训打分", type = MethodType.MODIFY)
public JSONReturn evaluationEmployeesTraining(long id,String note,HttpSession http){
    return trainingService.evaluationEmployeesTraining(id, note, acctName(http));
}


@ResponseBody
@RequestMapping(value = "addAllTraining")
@SecureValid(code = "01003", desc = "添加所有员工记录", type = MethodType.ADD)
public JSONReturn addAllTraining(long trainingId,long deptId,String searchVal,HttpSession http){
    return employeesService.addAllTraining(trainingId, deptId, searchVal, acctName(http));
}


@ResponseBody
@RequestMapping(value = "stopEmployeesTraining")
@SecureValid(code = "01003", desc = "停止员工培训", type = MethodType.MODIFY)
public JSONReturn stopEmployeesTraining(long id,String note,HttpSession http){
    return trainingService.stopEmployeesTraining(id, note, acctName(http));
}


@ResponseBody
@RequestMapping(value = "delete")
@SecureValid(code = "01003", desc = "根据ID查看详细信息", type = MethodType.DELETE)
public JSONReturn delete(long id,HttpSession httpSession){
    return trainingService.delete(id, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findRecord")
@SecureValid(code = "01003", desc = "显示报名记录", type = MethodType.FIND)
public JSONReturn showRecord(long trainingId,HttpSession httpSession){
    return trainingService.findRecord(trainingId, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findTrainingInfoById")
@SecureValid(code = "01003", desc = "根据ID查看详细信息", type = MethodType.FIND)
public JSONReturn findTrainingInfoById(long id,HttpSession httpSession){
    return trainingService.findTrainingInfoById(id, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "stop")
@SecureValid(code = "01003", desc = "结束培训项目", type = MethodType.MODIFY)
public JSONReturn stop(long id,String note,String enddate,HttpSession httpSession){
    return trainingService.stop(id, note, enddate, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "findTrainingPage")
@SecureValid(code = "01003", desc = "获取培训项目列表分页", type = MethodType.FIND)
public JSONReturn findTrainingPage(String searchValue,int page,HttpSession httpSession){
    return trainingService.findTrainingPage(searchValue, page, acctName(httpSession));
}


@ResponseBody
@RequestMapping(value = "delEmployeesTrainingRecord")
@SecureValid(code = "01003", desc = "删除员工培训记录", type = MethodType.DELETE)
public JSONReturn delEmployeesTrainingRecord(long id,HttpSession http){
    return trainingService.delEmployeesTrainingRecord(id, acctName(http));
}


@ResponseBody
@RequestMapping(value = "addTrainingByEmplId")
@SecureValid(code = "01003", desc = "添加员工培训记录", type = MethodType.ADD)
public JSONReturn addTrainingByEmplId(long emplId,long trainingId,HttpSession http){
    return trainingService.addTrainingByEmplId(emplId, trainingId, acctName(http));
}


}