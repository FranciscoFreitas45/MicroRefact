package com.zis.purchase.controller;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.bean.TempImportTaskStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportTaskView;
import com.zis.Interface.DoPurchaseService;
@Controller
@RequestMapping(value = "/purchase")
public class TempImportViewController {

@Autowired
 private  DoPurchaseService doPurchaseService;


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/viewTempImportTask")
public String viewTask(ModelMap context,HttpServletRequest request){
    Pageable page = WebHelper.buildPageRequest(request);
    Page<TempImportTask> list = this.doPurchaseService.findAllTempImportTask(page);
    List<TempImportTaskView> viewList = new ArrayList<TempImportTaskView>();
    for (TempImportTask task : list) {
        viewList.add(buildTaskView(task));
    }
    context.put("taskList", viewList);
    context.put("page", page);
    if (list.hasPrevious()) {
        context.put("prePage", page.previousOrFirst().getPageNumber());
    }
    if (list.hasNext()) {
        context.put("nextPage", page.next().getPageNumber());
    }
    return "purchase/tempImportTaskView";
}


public TempImportTaskView buildTaskView(TempImportTask task){
    TempImportTaskView view = new TempImportTaskView();
    BeanUtils.copyProperties(task, view);
    view.setBizTypeDisplay(TempImportTaskBizTypeEnum.parseEnum(task.getBizType()).getDisplayValue());
    view.setStatusDisplay(TempImportTaskStatus.getDisplay(task.getStatus()));
    return view;
}


}