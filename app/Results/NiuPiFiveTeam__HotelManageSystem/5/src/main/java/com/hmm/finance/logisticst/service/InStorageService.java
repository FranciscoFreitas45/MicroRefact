package com.hmm.finance.logisticst.service;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.activiti.domain.WorkflowDTO;
import com.hmm.activiti.service.IWorkflowService;
import com.hmm.employee.dao.EmployeeDao;
import com.hmm.employee.entity.Employee;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.finance.logisticst.domain.InStorageDTO;
import com.hmm.finance.logisticst.domain.InStorageDetailedDTO;
import com.hmm.finance.logisticst.repository.InStorageRepository;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.logistics.stock.entity.Stock;
import com.hmm.logistics.stock.repository.InDetailedRepository;
import com.hmm.logistics.stock.util.YesOrNoSend;
import com.hmm.Interface.IWorkflowService;
import com.hmm.Interface.EmployeeDao;
import com.hmm.Interface.InDetailedRepository;
@Service
@Transactional
public class InStorageService implements IInStorageService{

@Autowired
 private  InStorageRepository inStorageRepository;

@Autowired
 private  IWorkflowService workflowService;

@Autowired
 private  EmployeeDao employdao;

@Autowired
 private  InDetailedRepository inDetailedRepository;


@Override
public void saveInStorageDetailedPrice(String listString){
    JSONArray list = new JSONArray(listString);
    for (int i = 0; i < list.length(); i++) {
        JSONObject jsonObject = (JSONObject) list.get(i);
        InDetailed inDetailed = inDetailedRepository.findById(jsonObject.getLong("inStorageDetailedId")).get();
        inDetailed.setPrice(jsonObject.getLong("price"));
        inDetailedRepository.save(inDetailed);
    }
}


public Set<InStorageDTO> workflowToDTO(List<WorkflowDTO> workflowLists,Set<InStorageDTO> inStorageDTOs){
    if (workflowLists != null) {
        if (inStorageDTOs == null) {
            inStorageDTOs = new HashSet<InStorageDTO>();
        }
        for (WorkflowDTO workflow : workflowLists) {
            String businessKey = workflow.getBusinessKey();
            if (businessKey == null) {
                continue;
            }
            InStorage inStorage = inStorageRepository.findById(businessKey).get();
            if (inStorage != null) {
                InStorageDTO inStorageDTO = new InStorageDTO();
                BeanUtils.copyProperties(inStorage, inStorageDTO);
                inStorageDTO.setEmployeeId(inStorage.getEmployee().getUserName());
                BeanUtils.copyProperties(workflow, inStorageDTO);
                inStorageDTOs.add(inStorageDTO);
            }
        }
    }
    return inStorageDTOs;
}


@Override
public Page<InStorageDetailedDTO> findInStorageDetailedByInStorageId(String inStorageId,Pageable pageable){
    InStorage instorage = inStorageRepository.findById(inStorageId).get();
    Page<InStorageDetailedDTO> lists = inDetailedRepository.findInStorageDetailedByInAll(instorage, pageable);
    return lists;
}


@Override
public Page<InStorageDTO> findCompleteInStorage(Pageable pageable){
    return inStorageRepository.findCompleteInStorage(pageable);
}


@Override
public void save(InStorage inStorage){
    inStorageRepository.save(inStorage);
}


public void claim(String taskId,String employeeId){
    workflowService.claim(taskId, employeeId);
    ProcessInstance processInstance = workflowService.getProcessInstanceByTaskId(taskId);
    InStorage inStorage = inStorageRepository.findById(processInstance.getBusinessKey()).get();
    // 处于[审批中]状态
    inStorage.setProcessStatus(ProcessStatus.APPROVAL);
}


@Override
public List<InStorageDTO> findTodoTasks(String employeeId,Pageable pageable){
    Set<InStorageDTO> inStorageDTOs = null;
    // 1.根据员工id查询所属任务
    List<WorkflowDTO> workflowLists = workflowService.findTodoTasks(employeeId);
    inStorageDTOs = workflowToDTO(workflowLists, inStorageDTOs);
    // 2.查询申请人自己发起的任务
    workflowLists = workflowService.findOwnerTasks(employeeId);
    inStorageDTOs = workflowToDTO(workflowLists, inStorageDTOs);
    // 3.查询待申请的入库单(根据employeeId查找员工自己的入库单[别的员工看不到])
    Employee emp = employdao.findByUserName(employeeId);
    List<InStorage> inStorageLists = inStorageRepository.findByEmployee(emp);
    if (inStorageLists != null) {
        if (inStorageDTOs == null) {
            inStorageDTOs = new HashSet<InStorageDTO>();
        }
        for (InStorage instorage2 : inStorageLists) {
            InStorageDTO inStorageDTO = new InStorageDTO();
            BeanUtils.copyProperties(instorage2, inStorageDTO);
            inStorageDTO.setEmployeeId(instorage2.getEmployee().getUserName());
            inStorageDTOs.add(inStorageDTO);
        }
    }
    // 3.返回数据
    List<InStorageDTO> inStorageDTOs2 = new ArrayList<>(inStorageDTOs);
    return inStorageDTOs2;
}


public void complete(String taskId,Map<String,Object> variables){
    // 1.先设置状态
    ProcessInstance processInstance = workflowService.getProcessInstanceByTaskId(taskId);
    InStorage inStorage = inStorageRepository.findById(processInstance.getBusinessKey()).get();
    // 审批完成，重新回到[未签收]状态
    inStorage.setProcessStatus(ProcessStatus.UNRECEIPTED);
    // 1.1若有供应商和总金额，则写入入库单实体类
    if (variables.containsKey("amountMoney") && variables.containsKey("supplier")) {
        inStorage.setVender((variables.get("supplier")).toString());
        inStorage.setAmount(Float.parseFloat(variables.get("amountMoney").toString()));
    }
    // 2.再完成，否则完成后查找不到任务id会报错
    workflowService.complete(taskId, variables);
    // 完成时，若流程结束 则将流程状态设为COMPLETE
    ProcessInstance processInstance2 = workflowService.getProcessInstanceById(processInstance.getProcessInstanceId());
    if (processInstance2 == null) {
        inStorage.setProcessStatus(ProcessStatus.COMPLETE);
    }
}


@Override
public void startWorkflow(String inStorageId){
    // 1.声明流程实例
    ProcessInstance processInstance = null;
    // 2.获取入库实例
    InStorage inStorage = inStorageRepository.findById(inStorageId).get();
    String employeeId = inStorage.getEmployee().getUserName();
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("applyUserId", employeeId);
    if (inStorage != null) {
        try {
            processInstance = workflowService.startWorkflow(employeeId, "inStorageApply", inStorageId, variables);
            // 进入(待签收)状态
            inStorage.setProcessStatus(ProcessStatus.UNRECEIPTED);
            inStorage.setProcessInstanceId(processInstance.getId());
            inStorage.setApplyTime(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


}