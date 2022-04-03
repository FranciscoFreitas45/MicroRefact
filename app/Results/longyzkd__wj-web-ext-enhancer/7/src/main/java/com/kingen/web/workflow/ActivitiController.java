package com.kingen.web.workflow;
 import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kingen.bean.User;
import com.kingen.bean.workflow.BaseVO;
import com.kingen.service.account.AccountService;
import com.kingen.service.workflow.ProcessService;
import com.kingen.service.workflow.WorkflowDeployService;
import com.kingen.service.workflow.WorkflowTraceService;
import com.kingen.util.ActivitiUtils;
import com.kingen.util.Constants;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.util.workflow.WorkflowUtils;
import com.kingen.web.CommonController;
import Interface.AccountService;
import DTO.Page;
import DTO.Pagination;
@Controller
@RequestMapping("/workflow")
public class ActivitiController extends CommonController{

 private  Logger logger;

@Autowired
 protected  AccountService userService;

@Autowired
 protected  WorkflowTraceService traceService;

@Autowired
 private  ProcessService processService;

@Autowired
 private  RepositoryService repositoryService;

@Autowired
 private  WorkflowDeployService workflowProcessDefinitionService;

@Autowired
 private ProcessEngineConfiguration processEngineConfiguration;

@Autowired
 protected  RuntimeService runtimeService;

@Autowired
 private ProcessEngineFactoryBean processEngine;


@RequiresPermissions("admin:process:*")
@RequestMapping(value = "/process/redeploy/single")
public String redeploySingle(String exportDir,String resourceName,String diagramResourceName,String deploymentId,RedirectAttributes redirectAttributes){
    try {
        this.repositoryService.deleteDeployment(deploymentId, true);
        // 方法一：通过classpath/deploy目录下的.zip或.bar文件部署
        String processKey = resourceName.substring(0, resourceName.indexOf('.')) + ".zip";
        workflowProcessDefinitionService.redeploySingleFrom(exportDir, processKey);
        // 方法二：通过classpath/bpmn下的流程描述文件部署--流程图错乱，一直提倡用打包部署没有任何问题。
        // workflowProcessDefinitionService.redeployBpmn(exportDir, resourceName,diagramResourceName);
        redirectAttributes.addFlashAttribute("message", "已重新部署流程！");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("message", "重新部署流程失败！");
        throw e;
    }
    return "redirect:/processAction/process/listProcess_page";
}


@RequestMapping(value = "/process/finished")
public String finished(Model model,Page page){
    return "workflow/finished-process";
}


@RequestMapping(value = "/process/list/data")
public void listProcessData(Page page,HttpServletRequest request,HttpServletResponse response){
    // objects保存两个对象，Object[0]:是ProcessDefinition（流程定义），Object[1]:是Deployment（流程部署）
    List<Object[]> objects = new ArrayList<Object[]>();
    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
    // int[] pageParams = PaginationThreadUtils.setPage(processDefinitionQuery.list().size());
    // List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(pageParams[0], pageParams[1]);
    page.setTotal(processDefinitionQuery.list().size());
    List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(page.getFirstResult(), page.getLimit());
    /*
    	for (ProcessDefinition processDefinition : processDefinitionList) {
    		String deploymentId = processDefinition.getDeploymentId();
    		Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
    		objects.add(new Object[]{processDefinition, deployment});
    	}
    	*/
    // Pagination pagination = PaginationThreadUtils.get();
    // mav.addObject("obj", objects);
    // mav.addObject("page", pagination.getPageStr());
    // page.setDataList(objects);
    // page.setDataList(processDefinitionList);
    page.setDataList(ActivitiUtils.mapSetter(processDefinitionList));
    writeJson(response, page);
// writeJsonInclude(response, page,new String[]{"id"});
// writeJackson(response, page,null,new String[]{"id"});
// return page;
}


@RequestMapping(value = "/process/delete")
@ResponseBody
public JSONObject delete(String deploymentId){
    repositoryService.deleteDeployment(deploymentId, true);
    return JsonResultBuilder.success(true).msg("流程删除成功！").json();
// return "redirect:/processAction/process/listProcess_page";
}


// 使用SpEL 解决 @PathVariable出现点号"."时导致路径参数截断获取不全
@RequestMapping(value = "/process/convert-to-model/{processDefinitionId:.+}")
@ResponseBody
public JSONObject convertToModel(String processDefinitionId){
    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
    InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
    BpmnJsonConverter converter = new BpmnJsonConverter();
    com.fasterxml.jackson.databind.node.ObjectNode modelNode = converter.convertToJson(bpmnModel);
    org.activiti.engine.repository.Model modelData = repositoryService.newModel();
    modelData.setKey(processDefinition.getKey());
    modelData.setName(processDefinition.getResourceName());
    modelData.setCategory(processDefinition.getDeploymentId());
    ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
    modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
    modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
    modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
    // 导入进来的流程默认直接部署了，在转换为Model的时候，状态改为启用（已经部署）
    modelObjectNode.put(Constants.STATUS, Constants.ActivitiStatusEnum.work.getIndex());
    modelData.setMetaInfo(modelObjectNode.toString());
    repositoryService.saveModel(modelData);
    repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
    return JsonResultBuilder.success(true).msg("转换成功！").json();
// return "redirect:/processAction/process/listProcess_page";
}


@RequestMapping(value = "/todoTask")
public String todoTask(HttpSession session,Model model){
    return "task/task-list";
}


@RequestMapping(value = "/process/trace/auto/{executionId}")
public void readResource(String executionId,HttpServletResponse response){
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
    List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
    // 不使用spring请使用下面的两行代码
    // ProcessEngineImpl defaultProcessEngine = (ProcessEngineImpl) ProcessEngines.getDefaultProcessEngine();
    // Context.setProcessEngineConfiguration(defaultProcessEngine.getProcessEngineConfiguration());
    // 使用spring注入引擎请使用下面的这行代码
    processEngineConfiguration = processEngine.getProcessEngineConfiguration();
    Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
    ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
    InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds);
    // 输出资源内容到相应对象
    byte[] b = new byte[1024];
    int len;
    while ((len = imageStream.read(b, 0, 1024)) != -1) {
        response.getOutputStream().write(b, 0, len);
    }
}


// process:vacation,salary,expense:running
@RequiresPermissions("user:process:running*")
@RequestMapping(value = "/process/runingProcessInstance/{businessType}/list_page")
public String getRuningProcessInstance(String businessType,HttpSession session,Model model){
    User user = getCurrentUser();
    List<BaseVO> baseVO = null;
    if (BaseVO.VACATION.equals(businessType)) {
        // 请假
        baseVO = this.processService.listRuningVacation(user);
        model.addAttribute("businessType", BaseVO.VACATION);
    } else if (BaseVO.SALARY.equals(businessType)) {
    // //调薪
    // baseVO = this.processService.listRuningSalaryAdjust(user);
    // model.addAttribute("businessType", BaseVO.SALARY);
    } else if (BaseVO.EXPENSE.equals(businessType)) {
    // //报销
    // baseVO = this.processService.listRuningExpense(user);
    // model.addAttribute("businessType", BaseVO.EXPENSE);
    }
    Pagination pagination = PaginationThreadUtils.get();
    model.addAttribute("page", pagination.getPageStr());
    model.addAttribute("baseList", baseVO);
    return "apply/list_running";
}


@RequestMapping(value = "/process/deploy")
public void deploy(String exportDir,MultipartFile file,RedirectAttributes redirectAttributes,HttpServletResponse response){
    // @Value("${export.diagram.path}")
    // @Value("#{APP_PROPERTIES['export.diagram.path']}") String exportDir,
    String fileName = file.getOriginalFilename();
    JSONObject jsonObject = new JSONObject();
    try {
        InputStream fileInputStream = file.getInputStream();
        Deployment deployment = null;
        String extension = FilenameUtils.getExtension(fileName);
        if (extension.equals("zip") || extension.equals("bar")) {
            ZipInputStream zip = new ZipInputStream(fileInputStream);
            deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
        } else {
            deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
        }
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
        for (ProcessDefinition processDefinition : list) {
            WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
        }
        // redirectAttributes.addFlashAttribute("message", "流程部署成功！");
        jsonObject = JsonResultBuilder.success(true).msg("流程部署成功！").json();
    } catch (Exception e) {
        // redirectAttributes.addFlashAttribute("message", "流程部署失败！");
        jsonObject = JsonResultBuilder.success(false).msg("流程部署失败！").json();
        logger.error("error on deploy process, because of file input stream", e);
    }
    // return "redirect:/processAction/process/listProcess_page";
    writeJson(response, jsonObject);
}


@RequestMapping(value = "/todoTask/data")
public void todoTaskData(Page<BaseVO> page,HttpSession session,Model model,HttpServletRequest request,HttpServletResponse response){
    String userId = getCurrentUser().getUserId();
    User user = this.userService.unique(userId);
    page = this.processService.findTodoTask(user, page);
    // model.addAttribute("tasklist", taskList);
    // model.addAttribute("taskType", BaseVO.CANDIDATE);
    writeJson(response, page);
}


@Deprecated
@RequestMapping(value = "/process/showDiagram/{processInstanceId}", method = RequestMethod.GET)
public void showDiagram(String processInstanceId,HttpServletResponse response){
    InputStream imageStream = this.processService.getDiagram(processInstanceId);
    // 输出资源内容到相应对象
    byte[] b = new byte[1024];
    int len;
    while ((len = imageStream.read(b, 0, 1024)) != -1) {
        response.getOutputStream().write(b, 0, len);
    }
}


@RequestMapping(value = "/process/running")
public String running(Model model,Page page){
    return "workflow/running-process";
}


@RequestMapping(value = "/finishedTask")
public String finishedTask(HttpSession session,Model model){
    return "task/task-end";
}


@RequestMapping(value = "/finishedTask/data")
public void finishedTaskData(Page<BaseVO> page,HttpSession session,Model model,HttpServletResponse response){
    User user = getCurrentUser();
    page = this.processService.findFinishedTaskInstances(user, page);
    // model.addAttribute("tasklist", tasklist);
    // model.addAttribute("taskType", BaseVO.FINISHED);
    // return "task/end_task";
    writeJson(response, page);
}


@RequestMapping(value = "/resource/process-definition")
public void loadByDeployment(String processDefinitionId,String resourceType,HttpServletResponse response){
    InputStream resourceAsStream = this.processService.getDiagramByProDefinitionId_noTrace(resourceType, processDefinitionId);
    byte[] b = new byte[1024];
    int len = -1;
    while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
        response.getOutputStream().write(b, 0, len);
    }
}


@RequestMapping(value = "/process/finished/data")
public void listFinishedProcess(Page page,Model model,HttpServletResponse response){
    page = this.processService.findFinishedProcessInstances(page);
    // model.addAttribute("taskType", BaseVO.FINISHED);
    writeJson(response, page);
}


@RequiresPermissions("admin:process:suspend,active")
@RequestMapping(value = "/process/updateProcessStatusByProDefinitionId")
public String updateProcessStatusByProDefinitionId(String status,String processDefinitionId,RedirectAttributes redirectAttributes){
    // 如果用/{status}/{processDefinitionId} rest风格，@PathVariable获取的processDefinitionId 为com.zml.oa,实际是com.zml.oa.vacation:1:32529.难道是BUG?
    if (status.equals("active")) {
        redirectAttributes.addFlashAttribute("message", "已激活ID为[" + processDefinitionId + "]的流程定义。");
        repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
    } else if (status.equals("suspend")) {
        repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
        redirectAttributes.addFlashAttribute("message", "已挂起ID为[" + processDefinitionId + "]的流程定义。");
    }
    return "redirect:/processAction/process/listProcess_page";
}


@RequestMapping(value = "/process/trace/{pid}")
@ResponseBody
public List<Map<String,Object>> traceProcess(String processInstanceId){
    List<Map<String, Object>> activityInfos = traceService.traceProcess(processInstanceId);
    return activityInfos;
}


@RequestMapping(value = "/process/list")
public ModelAndView listProcess(HttpServletRequest request,HttpServletResponse response){
    ModelAndView mav = new ModelAndView("workflow/process-list");
    return mav;
}


@RequestMapping("/claim/{taskId}")
@ResponseBody
public JSONObject claim(String taskId,HttpSession session,RedirectAttributes redirectAttributes){
    User user = getCurrentUser();
    this.processService.doClaim(user, taskId);
    JSONObject jsonObject = JsonResultBuilder.success(true).msg("任务已签收").json();
    return jsonObject;
// redirectAttributes.addFlashAttribute("message", "任务已签收");
// return "redirect:/processAction/todoTaskList_page";
}


@RequestMapping(value = "/process/running/data")
public void listRuningProcess(Page page,Model model,HttpServletResponse response){
    page = this.processService.listRuningProcess(page);
    // model.addAttribute("taskType", BaseVO.RUNNING);
    writeJson(response, page);
}


@RequestMapping(value = "/process/updateProcessStatusByProInstanceId/{status}/{processInstanceId}")
@ResponseBody
public JSONObject updateProcessStatusByProInstanceId(String status,String processInstanceId,RedirectAttributes redirectAttributes){
    JSONObject jsonObject = null;
    if (status.equals("active")) {
        this.processService.activateProcessInstance(processInstanceId);
        jsonObject = JsonResultBuilder.success(true).msg("已激活ID为[ " + processInstanceId + " ]的流程实例。").json();
    // redirectAttributes.addFlashAttribute("message", "已激活ID为[ " + processInstanceId + " ]的流程实例。");
    } else if (status.equals("suspend")) {
        this.processService.suspendProcessInstance(processInstanceId);
        // redirectAttributes.addFlashAttribute("message", "已挂起ID为[ " + processInstanceId + " ]的流程实例。");
        jsonObject = JsonResultBuilder.success(true).msg("已挂起ID为[ " + processInstanceId + " ]的流程实例。").json();
    }
    return jsonObject;
}


@RequiresPermissions("admin:process:*")
@RequestMapping(value = "/process/redeploy/all")
public void redeployAll(String exportDir,HttpServletResponse response,RedirectAttributes redirectAttributes){
    PrintWriter out = response.getWriter();
    try {
        List<Deployment> deploymentList = this.repositoryService.createDeploymentQuery().list();
        // 删除现有所有流程实例
        for (Deployment deployment : deploymentList) {
            String deploymentId = deployment.getId();
            this.repositoryService.deleteDeployment(deploymentId, true);
        }
        // 重新部署全部流程实例
        // 方法一：通过classpath/deploy目录下的.zip或.bar文件部署
        workflowProcessDefinitionService.deployAllFromClasspath(exportDir);
        // 方法二：通过classpath/bpmn下的流程描述文件部署-流程图错乱，一直提倡用打包部署没有任何问题。
        // workflowProcessDefinitionService.redeployBpmn(exportDir);
        // redirectAttributes.addFlashAttribute("message", "已重新部署全部流程！");
        out.print("success");
    } catch (Exception e) {
        // redirectAttributes.addFlashAttribute("message", "重新部署流程失败！");
        out.print("fail");
        throw e;
    }
// return "redirect:/processAction/process/listProcess_page";
}


@RequestMapping(value = "/resource/process-instance")
public void loadByProcessInstance(String resourceType,String processInstanceId,HttpServletResponse response){
    InputStream resourceAsStream = this.processService.getDiagramByProInstanceId_noTrace(resourceType, processInstanceId);
    byte[] b = new byte[1024];
    int len = -1;
    while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
        response.getOutputStream().write(b, 0, len);
    }
}


}