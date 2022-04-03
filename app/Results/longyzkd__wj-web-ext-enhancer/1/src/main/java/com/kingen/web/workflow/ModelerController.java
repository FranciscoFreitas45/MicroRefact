package com.kingen.web.workflow;
 import java.io.ByteArrayInputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kingen.bean.Log;
import com.kingen.service.workflow.ProcessService;
import com.kingen.service.workflow.WorkflowDeployService;
import com.kingen.util.Constants;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.web.CommonController;
import Interface.WorkflowDeployService;
import DTO.Page;
@Controller
// @RequiresPermissions("admin:*")
@RequestMapping("/workflow/model")
public class ModelerController extends CommonController{

 private  Logger logger;

@Autowired
 private  RepositoryService repositoryService;

@Autowired
 private  WorkflowDeployService deployService;


@RequestMapping(value = "/toEdit")
public ModelAndView modelEdit(String id,String action){
    ModelAndView mav = new ModelAndView("workflow/model-edit");
    mav.addObject("id", id);
    mav.addObject("action", action);
    return mav;
}


@RequestMapping(value = "/toImport")
public ModelAndView toImport(){
    ModelAndView mav = new ModelAndView("workflow/model-import");
    return mav;
}


@RequestMapping(value = "/")
public ModelAndView modelList(){
    ModelAndView mav = new ModelAndView("workflow/model-list");
    return mav;
}


@RequestMapping(value = "listData")
public void listData(Page page,Log vo,HttpServletResponse response){
    ModelQuery modelQuery = repositoryService.createModelQuery();
    // int[] pageParams = PaginationThreadUtils.setPage(modelQuery.list().size());
    List<Model> list = modelQuery.listPage(page.getFirstResult(), page.getLimit());
    writeJson(response, list);
}


@RequestMapping(value = "one")
public void one(String modelId,HttpServletResponse response){
    ModelQuery modelQuery = repositoryService.createModelQuery();
    // int[] pageParams = PaginationThreadUtils.setPage(modelQuery.list().size());
    Model one = modelQuery.modelId(modelId).singleResult();
    writeJson(response, one);
}


@RequestMapping(value = "/create", method = RequestMethod.POST)
public void create(String name,String key,String description,String serviceType,String flowTemplate,HttpServletRequest request,HttpServletResponse response){
    logger.debug("name: " + name + " key: " + key + " des: " + description);
    JSONObject jsonObject = new JSONObject();
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        Model modelData = repositoryService.newModel();
        // metainfo,页面显示description
        // model里面有name，metainfo里面也有name，页面取name值，从model里面取的
        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        description = StringUtils.defaultString(description);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        // 未启用
        modelObjectNode.put(Constants.STATUS, Constants.ActivitiStatusEnum.notWork.getIndex());
        modelData.setMetaInfo(modelObjectNode.toString());
        modelData.setName(name);
        modelData.setKey(StringUtils.defaultString(key));
        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
        // response.sendRedirect(request.getContextPath() + "/modeler/service/editor?id=" + modelData.getId());
        // response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        jsonObject = JsonResultBuilder.success(true).msg("成功").data(modelData.getId()).json();
    } catch (Exception e) {
        logger.error("创建模型失败：", e);
        jsonObject = JsonResultBuilder.success(false).msg("失败").json();
    }
    writeJson(response, jsonObject);
}


@RequestMapping(value = "/update", method = RequestMethod.POST)
public void update(String modelId,String name,String key,String description,HttpServletRequest request,HttpServletResponse response){
    logger.debug("name: " + name + " key: " + key + " des: " + description);
    JSONObject jsonObject = new JSONObject();
    try {
        ObjectMapper objectMapper = new ObjectMapper();
        ModelQuery modelQuery = repositoryService.createModelQuery();
        Model modelData = modelQuery.modelId(modelId).singleResult();
        // metainfo,页面显示description
        // model里面有name，metainfo里面也有name，页面取name值，从model里面取的
        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        description = StringUtils.defaultString(description);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelData.setMetaInfo(modelObjectNode.toString());
        modelData.setName(name);
        modelData.setKey(StringUtils.defaultString(key));
        repositoryService.saveModel(modelData);
        jsonObject = JsonResultBuilder.success(true).msg("成功").data(modelData.getId()).json();
    } catch (Exception e) {
        logger.error("创建模型失败：", e);
        jsonObject = JsonResultBuilder.success(false).msg("失败").json();
    }
    writeJson(response, jsonObject);
}


@RequestMapping(value = "deleteThem")
public void deleteThem(String[] modelIds,HttpServletResponse response){
    Assert.notEmpty(modelIds);
    for (String modelId : modelIds) {
        repositoryService.deleteModel(modelId);
    }
    JSONObject jsonObject = JsonResultBuilder.success(true).msg("删除成功").json();
    writeJson(response, jsonObject);
// return "redirect:/modelAction/listModel_page";
}


@RequestMapping(value = "export/{modelId}")
public void export(String modelId,HttpServletResponse response){
    try {
        Model modelData = repositoryService.getModel(modelId);
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
        BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
        byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
        ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
        IOUtils.copy(in, response.getOutputStream());
        String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.flushBuffer();
    } catch (Exception e) {
        logger.error("导出model的xml文件失败：modelId={}" + modelId, e);
    }
}


@RequestMapping(value = "delete/{modelId}")
public void delete(String modelId,HttpServletResponse response){
    repositoryService.deleteModel(modelId);
    JSONObject jsonObject = JsonResultBuilder.success(true).msg("删除成功").json();
    // return "redirect:/modelAction/listModel_page";
    writeJson(response, jsonObject);
}


@RequestMapping(value = "deploy/{modelId}")
public void deploy(String modelId,RedirectAttributes redirectAttributes,HttpServletResponse response){
    JSONObject jsonObject = new JSONObject();
    try {
        Model modelData = repositoryService.getModel(modelId);
        ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
        Deployment deployment = deployService.deployModelWithStatus(modelData, modelNode);
        // redirectAttributes.addFlashAttribute("message", "部署成功，部署ID=" + deployment.getId());
        jsonObject = JsonResultBuilder.success(true).msg("部署成功，部署ID=" + deployment.getId()).data(deployment.getId()).json();
    } catch (Exception e) {
        // redirectAttributes.addFlashAttribute("message", "根据模型部署流程失败:modelId="+modelId);
        jsonObject = JsonResultBuilder.success(false).msg("根据模型部署流程失败:modelId=" + modelId).json();
        logger.error("根据模型部署流程失败：modelId={}" + modelId, e);
    }
    // return "redirect:/modelAction/listModel_page";
    writeJson(response, jsonObject);
}


}