package com.zis.purchase.action;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.bean.TempImportTaskStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportTaskView;
import com.zis.Interface.DoPurchaseService;
public class TempImportViewAction extends ActionSupport{

 private  long serialVersionUID;

 private  Integer taskId;

 private  String status;

 private  DoPurchaseService doPurchaseService;


public Integer getTaskId(){
    return taskId;
}


public void setTaskId(Integer taskId){
    this.taskId = taskId;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public String getStatus(){
    return status;
}


public String viewTask(){
    List<TempImportTask> list = this.doPurchaseService.findAllTempImportTask();
    List<TempImportTaskView> viewList = new ArrayList<TempImportTaskView>();
    for (TempImportTask task : list) {
        viewList.add(buildTaskView(task));
    }
    ActionContext context = ActionContext.getContext();
    context.put("taskList", viewList);
    return SUCCESS;
}


public TempImportTaskView buildTaskView(TempImportTask task){
    TempImportTaskView view = new TempImportTaskView();
    BeanUtils.copyProperties(task, view);
    view.setBizTypeDisplay(TempImportTaskBizTypeEnum.parseEnum(task.getBizType()).getDisplayValue());
    view.setStatusDisplay(TempImportTaskStatus.getDisplay(task.getStatus()));
    return view;
}


public void setStatus(String status){
    this.status = status;
}


}