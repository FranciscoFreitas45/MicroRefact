package com.kingen.service.workflow;
 import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;
import javax.transaction.Transactional;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kingen.util.Constants;
import com.kingen.util.workflow.WorkflowUtils;
@Service
@Transactional
public class WorkflowDeployService {

 protected  Logger logger;

@Autowired
 protected  RepositoryService repositoryService;


public void deploySingleProcessByBpmn(ResourceLoader resourceLoader,String processKey,String exportDir){
    System.out.println("processKey: " + processKey);
    String classpathResourceUrl = "classpath:/bpmn/" + processKey;
    Resource resource = resourceLoader.getResource(classpathResourceUrl);
    if (resource.exists()) {
        InputStream inputStream = resource.getInputStream();
        if (inputStream == null) {
            logger.warn("ignore deploy workflow module: {}", classpathResourceUrl);
        } else {
            logger.debug("finded workflow module: {}, deploy it!", classpathResourceUrl);
            System.out.println(resource.getFilename());
            String resourceName = resource.getFilename();
            DeploymentBuilder builder = repositoryService.createDeployment();
            // builder.addClasspathResource("bpmn/"+resourceName); 使用addClasspathResource方法将流程描述文件和流程图部署到数据库中
            builder.addInputStream(resourceName, inputStream);
            builder.enableDuplicateFiltering();
            builder.deploy();
        }
    }
}


public List<String> loadBpmnFile(ResourceLoader resourceLoader){
    List<String> processKeys = new ArrayList<String>();
    String classpathResourceUrl = "classpath:/bpmn/";
    Resource resource = resourceLoader.getResource(classpathResourceUrl);
    File[] listFiles = resource.getFile().listFiles();
    if (listFiles.length != 0) {
        for (File f : listFiles) {
            String fileName = f.getName();
            if (fileName.endsWith(".bpmn") || fileName.endsWith(".png")) {
                processKeys.add(fileName);
            }
        }
    } else {
        logger.debug("The classpath:/deploy/ is empty!");
    }
    if (processKeys.size() == 0) {
        logger.debug("can not find .bpmn or .png in classpath:/bpmn/ !");
        throw new Exception("can not find .zip or .bar in classpath:/bpmn/ !");
    }
    return processKeys;
}


public void deploySingleProcess(ResourceLoader resourceLoader,String processKey,String exportDir){
    String classpathResourceUrl = "classpath:/deploy/" + processKey;
    logger.debug("read workflow from: {}", classpathResourceUrl);
    Resource resource = resourceLoader.getResource(classpathResourceUrl);
    logger.info(" resource: " + resource.exists());
    if (resource.exists()) {
        InputStream inputStream = resource.getInputStream();
        if (inputStream == null) {
            logger.warn("ignore deploy workflow module: {}", classpathResourceUrl);
        } else {
            logger.debug("finded workflow module: {}, deploy it!", classpathResourceUrl);
            ZipInputStream zis = new ZipInputStream(inputStream);
            DeploymentBuilder builder = repositoryService.createDeployment();
            builder.addZipInputStream(zis);
            // 该方法仅仅将DeploymentBuilder 的 isDuplicateFilterEnabled 属性设置为true
            // 目的是过滤重复部署（如果资源没有发生变化则不会重复部署）
            builder.enableDuplicateFiltering();
            Deployment deployment = builder.deploy();
            // Deployment deployment = repositoryService.createDeployment().addZipInputStream(zis).deploy(); //此方法会重复部署，不管资源变没变化
            // export diagram
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
            for (ProcessDefinition processDefinition : list) {
                WorkflowUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
            }
        }
    } else {
        logger.error("the file:[ {} ]is not exists!", classpathResourceUrl);
    }
}


public void redeployBpmn(String exportDir,String processKey){
    this.deployFromClassPathBpmn(exportDir, processKey);
}


public void redeployAllBpmn(String exportDir){
    this.deployFromClassPathBpmn(exportDir);
}


public Deployment deployModelWithStatus(Model modelData,ObjectNode modelNode){
    byte[] bpmnBytes = null;
    // 修改model的状态为 启用
    ObjectMapper mapper = new ObjectMapper();
    String metaInfo = modelData.getMetaInfo();
    ObjectNode node = mapper.readValue(metaInfo, ObjectNode.class);
    // String status = node.get(STATUS).asText();
    node.put(Constants.STATUS, Constants.ActivitiStatusEnum.work.getIndex());
    modelData.setMetaInfo(node.toString());
    repositoryService.saveModel(modelData);
    BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
    bpmnBytes = new BpmnXMLConverter().convertToXML(model);
    String processName = modelData.getName() + ".bpmn20.xml";
    Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes)).deploy();
    return deployment;
}


public List<String> loadDeployFile(ResourceLoader resourceLoader){
    List<String> processKeys = new ArrayList<>();
    String classpathResourceUrl = "classpath:/deploy/";
    Resource resource = resourceLoader.getResource(classpathResourceUrl);
    File[] listFiles = resource.getFile().listFiles();
    if (listFiles.length != 0) {
        for (File f : listFiles) {
            if (f.getName().endsWith(".zip") || f.getName().endsWith(".bar")) {
                processKeys.add(f.getName());
            }
        }
    } else {
        logger.debug("The classpath:/deploy/ is empty!");
        throw new Exception("The classpath:/deploy/ is empty!");
    }
    if (processKeys.size() == 0) {
        logger.debug("can not find .zip or .bar in classpath:/deploy/ !");
        throw new Exception("can not find .zip or .bar in classpath:/deploy/ !");
    }
    return processKeys;
}


public void redeploySingleFrom(String exportDir,String processKey){
    this.deployFromClasspath(exportDir, processKey);
}


public void deployAllFromClasspath(String exportDir){
    this.deployFromClasspath(exportDir);
}


public void deployFromClasspath(String exportDir,String processKey){
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    // String[] processKeys = {"salary", "vacation", "expense", "CountSalary"};
    List<String> processKeys = loadDeployFile(resourceLoader);
    for (String loopProcessKey : processKeys) {
        if (ArrayUtils.isNotEmpty(processKey)) {
            // 部署单个流程
            if (ArrayUtils.contains(processKey, loopProcessKey)) {
                logger.debug("hit module of {}", (Object[]) processKey);
                deploySingleProcess(resourceLoader, loopProcessKey, exportDir);
            } else {
                logger.debug("module: {} not equals process key: {}, ignore and continue find next.", loopProcessKey, processKey);
            }
        } else {
            // 部署所有流程
            deploySingleProcess(resourceLoader, loopProcessKey, exportDir);
        }
    }
}


public void deployFromClassPathBpmn(String exportDir,String processKey){
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    // String[] processKeys = {"salary.bpmn", "vacation.bpmn", "expense.bpmn", "CountSalary.bpmn"};
    List<String> processKeys = loadBpmnFile(resourceLoader);
    for (String loopProcessKey : processKeys) {
        if (ArrayUtils.isNotEmpty(processKey)) {
            // 部署单个流程
            if (ArrayUtils.contains(processKey, loopProcessKey)) {
                logger.debug("hit module of {}", (Object[]) processKey);
                deploySingleProcessByBpmn(resourceLoader, loopProcessKey, exportDir);
            } else {
                logger.debug("module: {} not equals process key: {}, ignore and continue find next.", loopProcessKey, processKey);
            }
        } else {
            // 部署所有流程
            deploySingleProcessByBpmn(resourceLoader, loopProcessKey, exportDir);
        }
    }
}


}