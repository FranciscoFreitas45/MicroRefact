package com.qidian.hcm.module.employee.service;
 import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qidian.hcm.common.config.HCMConfig;
import com.qidian.hcm.common.enums.YesNo;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.interceptor.TenantThreadHelper;
import com.qidian.hcm.common.utils.HttpClientUtil;
import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.authorization.dto.EmployeeRoleDTO;
import com.qidian.hcm.module.authorization.dto.RoleDTO;
import com.qidian.hcm.module.authorization.entity.EmployeeRole;
import com.qidian.hcm.module.authorization.entity.Role;
import com.qidian.hcm.module.authorization.enums.ActionType;
import com.qidian.hcm.module.authorization.enums.MenuCode;
import com.qidian.hcm.module.authorization.enums.PlatformType;
import com.qidian.hcm.module.authorization.repository.EmployeeRoleRepository;
import com.qidian.hcm.module.authorization.repository.RoleRepository;
import com.qidian.hcm.module.center.service.FileService;
import com.qidian.hcm.module.custom.entity.CustomizedField;
import com.qidian.hcm.module.custom.enums.FieldType;
import com.qidian.hcm.module.custom.service.CustomizedFieldService;
import com.qidian.hcm.module.employee.dto;
import com.qidian.hcm.module.employee.entity.Attachment;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.employee.entity.EmployeePosition;
import com.qidian.hcm.module.employee.enums.EmployeeHistoryTitle;
import com.qidian.hcm.module.employee.enums.EmployeeStatus;
import com.qidian.hcm.module.employee.repository.AttachmentRepository;
import com.qidian.hcm.module.employee.repository.CustomizedEmployeeFormRepository;
import com.qidian.hcm.module.employee.repository.EmployeeRepository;
import com.qidian.hcm.module.employee.request;
import com.qidian.hcm.module.employee.response.ResignedEmployeeResponse;
import com.qidian.hcm.module.employee.response.SelectedLeaderResponse;
import com.qidian.hcm.module.organization.entity.OrganizationEntity;
import com.qidian.hcm.module.organization.entity.PositionEntity;
import com.qidian.hcm.module.organization.enums.OrganizationEnums;
import com.qidian.hcm.module.organization.repository.OrganizationRepository;
import com.qidian.hcm.module.organization.service.GradeService;
import com.qidian.hcm.module.organization.service.OrganizationService;
import com.qidian.hcm.module.organization.service.PositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util;
import java.util.stream.Collectors;
import com.qidian.hcm.Interface.CustomizedEmployeeFormRepository;
import com.qidian.hcm.Interface.AttachmentRepository;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.EmployeeContactService;
import com.qidian.hcm.Interface.EmployeeEmergencyContactService;
import com.qidian.hcm.Interface.EmployeeWorkExperienceService;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.OrganizationRepository;
import com.qidian.hcm.Interface.EmployeePositionService;
import com.qidian.hcm.Interface.GradeService;
import com.qidian.hcm.Interface.EmployeeRoleRepository;
import com.qidian.hcm.Interface.EmployeeRoleRepository;
import com.qidian.hcm.Interface.HCMConfig;
import com.qidian.hcm.Interface.OrganizationService;
import com.qidian.hcm.Interface.CustomizedFieldService;
import com.qidian.hcm.Interface.EmployeePermissionService;
import com.qidian.hcm.DTO.Result;
@Service
@Slf4j
public class EmployeeService {

@Autowired
 private  CustomizedEmployeeFormRepository customizedEmployeeFormRepository;

@Autowired
 private  EmployeeRepository employeeRepository;

@Autowired
 private  AttachmentRepository attachmentRepository;

@Autowired
 private  EmployeeHistoryService employeeHistoryService;

@Autowired
 private  EmployeePositionService employeePositionService;

@Autowired
 private  EmployeeContractService employeeContractService;

@Autowired
 private  EmployeeIdentityService employeeIdentityService;

@Autowired
 private  EmployeeContactService employeeContactService;

@Autowired
 private  EmployeeEmergencyContactService employeeEmergencyContactService;

@Autowired
 private  EmployeeEducationService employeeEducationService;

@Autowired
 private  EmployeeWorkExperienceService employeeWorkExperienceService;

@Autowired
 private  FileService fileService;

@Autowired
 private  OrganizationRepository organizationRepository;

@Autowired
 private  PositionService positionService;

@Autowired
 private  GradeService gradeService;

@Autowired
 private  EmployeeRoleRepository employeeRoleRepository;

@Autowired
 private  RoleRepository roleRepository;

@Autowired
 private  HCMConfig hcmConfig;

@Autowired
 private  OrganizationService organizationService;

@Autowired
 private  CustomizedFieldService customizedFieldService;

@Autowired
 private  EmployeePermissionService employeePermissionService;


@Transactional
public void saveEmployeePosition(Long employeeId,List<EmployeePositionDTO> employeePositionDTOList){
    validateLeaderIsCycle(employeeId, employeePositionDTOList);
    employeePositionService.saveEmployeePositions(employeeId, employeePositionDTOList);
}


@Transactional
public void saveEmployeeBasicInfo(Long employeeId,EmployeeBasicRequest addEmployeeBasicRequest){
    findByEmployeeNoAndIdNot(addEmployeeBasicRequest.getEmployeeNo(), employeeId);
    addEmployeeBasicRequest.setId(employeeId);
    Employee employee = employeeRepository.getOne(employeeId);
    if (!StringUtils.isEmpty(addEmployeeBasicRequest.getAvatar())) {
        // 用户禁用头像
        if ("0".equals(addEmployeeBasicRequest.getAvatar()) && StringUtils.isEmpty(addEmployeeBasicRequest.getAvatarName())) {
            Optional<Attachment> attachmentOptional = attachmentRepository.findByFileId(employee.getAvatarFileId());
            if (attachmentOptional.isPresent()) {
                Attachment attachment = attachmentOptional.get();
                attachment.setFileNameOnOss("");
                attachmentRepository.save(attachment);
            }
        } else {
            // 上传头像
            Attachment attachment = fileService.uploadFileToOSS(addEmployeeBasicRequest.getAvatar(), addEmployeeBasicRequest.getAvatarName());
            attachment.setOriginName(addEmployeeBasicRequest.getAvatarName());
            employee.setAvatarFileId(attachment.getFileId());
            attachmentRepository.save(attachment);
        }
    }
    BeanUtils.copyProperties(addEmployeeBasicRequest, employee);
    employeeRepository.save(employee);
}


@Transactional
public void saveEmployeeJob(Long employeeId,EmployeeJobDTO employeeJobDTO){
    Employee employee = employeeRepository.getOne(employeeId);
    BeanUtils.copyProperties(employeeJobDTO, employee);
    employeeRepository.save(employee);
}


@Transactional
public void initCompanyAdmin(InitCompanyAdminRequest request){
    OrganizationEntity organizationEntity = new OrganizationEntity();
    organizationEntity.setAlias(request.getGroupName());
    organizationEntity.setName(request.getGroupName());
    organizationEntity.setCode("00000000");
    organizationEntity.setType(OrganizationEnums.company);
    // 根节点公司为-1
    organizationEntity.setParentId(-1L);
    organizationEntity.setDeleted(YesNo.NO);
    organizationEntity.setEnable(YesNo.YES);
    organizationEntity.setEnableTime(new Date());
    organizationRepository.save(organizationEntity);
    organizationService.createOrganizationPath(organizationEntity);
    Employee employee = new Employee();
    employee.setName(request.getUsername());
    employee.setMobile(request.getPhone());
    employee.setUserId(request.getUserId());
    employee.setSuperAdmin(true);
    employeeRepository.save(employee);
}


public EmployeeRequest getEmployeeDetail(Long id){
    Employee employee = getEmployeeById(id);
    EmployeeRequest addEmployeeRequest = new EmployeeRequest();
    EmployeeBasicInfoDTO employeeBasicInfoDTO = new EmployeeBasicInfoDTO();
    EmployeePositionInfoDTO employeePositionInfoDTO = new EmployeePositionInfoDTO();
    EmployeeOtherInfoDTO employeeOtherInfoDTO = new EmployeeOtherInfoDTO();
    addEmployeeRequest.setCustomizedForms(JSONArray.parseArray(employee.getCustomizedForms(), EmployeeCustomizedFormsDTO.class));
    // 添加3个子项目
    addEmployeeRequest.setPositionInfo(employeePositionInfoDTO);
    addEmployeeRequest.setOtherInfo(employeeOtherInfoDTO);
    addEmployeeRequest.setBasicInfo(employeeBasicInfoDTO);
    EmployeeJobDTO employeeJobDTO = new EmployeeJobDTO();
    BeanUtils.copyProperties(employee, addEmployeeRequest);
    BeanUtils.copyProperties(employee, employeeJobDTO);
    employeeBasicInfoDTO.setJob(employeeJobDTO);
    // 添加头像地址
    if (Objects.nonNull(employee.getAvatarFileId())) {
        addEmployeeRequest.setAvatar(fileService.getAvatarImgUrl(employee.getAvatarFileId()));
    }
    // position表操作
    employeePositionInfoDTO.setPosition(employeePositionService.getEmployeePositionDTOList(id));
    // contract表操作
    employeeBasicInfoDTO.setContract(employeeContractService.getEmployeeContractDTOList(id));
    // identity表操作
    employeeBasicInfoDTO.setIdentity(employeeIdentityService.getEmployeeIdentityDTOList(id));
    // contacts表操作
    employeeOtherInfoDTO.setContact(employeeContactService.getEmployeeContactDTOList(id));
    // emergencyContact表操作
    employeeOtherInfoDTO.setEmergencyContact(employeeEmergencyContactService.getEmergencyContactsDTOList(id));
    // education表操作
    employeeOtherInfoDTO.setEducation(employeeEducationService.getEducationDTOList(id));
    // workExperience表操作
    employeeOtherInfoDTO.setWorkExperience(employeeWorkExperienceService.getEmployeeWorkExperienceDTOList(id));
    return addEmployeeRequest;
}


public Long addNotActiveUser(String userName,String phone,String tenantName){
    Map<String, String> params = new HashMap<>();
    params.put("userName", userName);
    params.put("phone", phone);
    params.put("tenantName", tenantName);
    String resultStr = HttpClientUtil.doPostObject(hcmConfig.getTenantUrl() + "api/user/add_not_active_user", params, null);
    if (StringUtils.isEmpty(resultStr)) {
        log.error("请求失败！同步用户信息到center.user出错!tenantName={},phone={}", tenantName, phone);
        throw new BizException(ResultCode.CREATE_USER_ERROR);
    } else {
        Result result;
        try {
            result = JSONObject.parseObject(resultStr, Result.class);
        } catch (JSONException e) {
            log.error("center.user返回创建用户信息解析出错!response={}", resultStr);
            throw new BizException(ResultCode.CREATE_USER_ERROR);
        }
        if (!"0".equals(result.getCode())) {
            log.error("同步用户信息到center.user出错!tenantName={},phone={}", tenantName, phone);
            throw new BizException(ResultCode.CREATE_USER_ERROR);
        }
        return Long.valueOf(result.getData().toString());
    }
}


public Set<Long> getPermissionEmployeeIds(ActionType actionType){
    Specification<EmployeePosition> condition = (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        List<Long> orgIds = TenantThreadHelper.getPermissionOrgIds(PlatformType.backend, MenuCode.employeeManage, actionType);
        List<String> pathList = organizationService.listOrganizationPathByIds(orgIds);
        Join<OrganizationEntity, EmployeePosition> join = root.join("department", JoinType.LEFT);
        Predicate[] pathPredicates = new Predicate[pathList.size()];
        for (int i = 0; i < pathList.size(); i++) {
            String orgPath = pathList.get(i) + "%";
            pathPredicates[i] = criteriaBuilder.like(join.get("path"), orgPath);
        }
        predicates.add(criteriaBuilder.or(pathPredicates));
        Predicate[] p = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(p));
    };
    return employeePositionService.findAllByCondition(condition).stream().map(EmployeePosition::getEmployeeId).collect(Collectors.toSet());
}


public Employee findByUserId(Long userId){
    Optional<Employee> employeeOptional = employeeRepository.findByUserId(userId);
    return employeeOptional.orElse(null);
}


public Object handlerFile(Object obj){
    LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap) obj;
    JSONObject targetObj = new JSONObject();
    for (Map.Entry<String, Object> objectEntry : linkedHashMap.entrySet()) {
        CustomizedField cf = customizedFieldService.getById(Long.valueOf(objectEntry.getKey()));
        if (cf != null && FieldType.file == cf.getFieldType()) {
            Object str = objectEntry.getValue();
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(str));
            String data = jsonObject.getString("data");
            String fileName = jsonObject.getString("fileName");
            Long fileId;
            if (StringUtils.isEmpty(data)) {
                fileId = Long.valueOf(jsonObject.getString("fileId"));
            } else {
                Attachment attachment = fileService.uploadFileToOSS(data, fileName);
                fileId = attachment.getFileId();
            }
            JSONObject tempJSON = new JSONObject();
            tempJSON.put("fileId", fileId);
            tempJSON.put("fileName", fileName);
            targetObj.put(objectEntry.getKey(), tempJSON);
        }
    }
    if (!targetObj.isEmpty()) {
        return targetObj;
    }
    return obj;
}


@Transactional
public void qualifyEmployeeById(QualifyEmployeeRequest qualifyEmployeeRequest,Long id){
    Employee employee = getEmployeeById(id);
    employee.setStatus(EmployeeStatus.formal);
    employee.setQualifyDate(qualifyEmployeeRequest.getDate());
    employee.setQualifyRemark(qualifyEmployeeRequest.getRemark());
    Attachment attachment = new Attachment();
    if (!StringUtils.isEmpty(qualifyEmployeeRequest.getFileName())) {
        // 上传附件 && 将fileId添加到employee中
        attachment = fileService.uploadFileToOSS(qualifyEmployeeRequest.getAttachment(), qualifyEmployeeRequest.getFileName());
    }
    employee.setQualifyLetterOriginName(attachment.getOriginName());
    employee.setQualifyLetterFileId(attachment.getFileId());
    employeeRepository.save(employee);
    employeeHistoryService.saveEmployeeHistory(id, EmployeeHistoryTitle.QUALIFY.getName(), qualifyEmployeeRequest.getDate(), qualifyEmployeeRequest.getRemark());
    employeeContractService.updateContractProbationEndDate(id, qualifyEmployeeRequest.getDate());
}


public void validateLeaderIsCycle(Long employeeId,List<EmployeePositionDTO> employeePositionDTOList){
    List<Long> allowSelectLeaderIds = listAllowSelectLeaders(employeeId).stream().map(SelectedLeaderResponse::getId).collect(Collectors.toList());
    List<Long> invalidLeaders = Lists.newArrayList();
    for (EmployeePositionDTO dto : employeePositionDTOList) {
        if (dto.getLeaderId() != null && !allowSelectLeaderIds.contains(dto.getLeaderId())) {
            invalidLeaders.add(dto.getLeaderId());
        }
    }
    if (!invalidLeaders.isEmpty()) {
        throw new BizException(ResultCode.LEADER_IS_CYCLE, invalidLeaders);
    }
}


public void setAvatar(List<FilterEmployeeDTO> dtoList){
    dtoList.forEach(e -> e.setAvatar(fileService.getAvatarImgUrl(e.getAvatarFileId())));
}


public List<SelectedLeaderResponse> listAllowSelectLeaders(Long employeeId){
    List<EmployeePosition> employeePositions = employeePositionService.findAllByExcludeEmployeeStatus(EmployeeStatus.former);
    Set<Long> excludeLeaders = Sets.newHashSet(employeeId);
    Set<Long> upLeaders = Sets.newHashSet(employeeId);
    Set<Long> subordinates = Sets.newHashSet();
    do {
        subordinates.clear();
        for (EmployeePosition employeePosition : employeePositions) {
            if (upLeaders.contains(employeePosition.getLeaderId())) {
                subordinates.add(employeePosition.getEmployeeId());
            }
        }
        upLeaders.clear();
        excludeLeaders.addAll(subordinates);
        upLeaders.addAll(subordinates);
    } while (!subordinates.isEmpty());
    List<Employee> employees = employeeRepository.findByIdNotIn(excludeLeaders);
    return employees.stream().map(e -> new SelectedLeaderResponse(e.getId(), e.getName())).collect(Collectors.toList());
}


@Transactional
public void addEmployees(EmployeeRequest addEmployeeRequest){
    findByEmployeeNoAndIdNot(addEmployeeRequest.getEmployeeNo(), 0L);
    Long userId = addNotActiveUser(addEmployeeRequest.getName(), addEmployeeRequest.getMobile(), TenantThreadHelper.getToken().getTenantName());
    addEmployeeRequest.setUserId(userId);
    saveOrUpdateEmployee(addEmployeeRequest, "id");
}


public Attachment saveResignAttachment(ResignEmployeeRequest resignEmployeeRequest){
    Attachment attachment = new Attachment();
    if (!StringUtils.isEmpty(resignEmployeeRequest.getFileName())) {
        attachment = fileService.uploadFileToOSS(resignEmployeeRequest.getAttachment(), resignEmployeeRequest.getFileName());
        attachment.setOriginName(resignEmployeeRequest.getFileName());
        attachmentRepository.save(attachment);
    }
    return attachment;
}


public EmployeeRoleDTO convertEmpToEmpRole(Employee employee,Map<Long,String> orgMap){
    EmployeeRoleDTO dto = new EmployeeRoleDTO();
    EmployeePosition currentEp = employeePositionService.getCurrentEmployeePosition(employee.getId());
    if (currentEp != null) {
        dto.setCompanyName(orgMap.get(currentEp.getCompanyId()));
        dto.setDepartmentName(orgMap.get(currentEp.getDepartmentId()));
    }
    dto.setId(employee.getId());
    dto.setMobile(employee.getMobile());
    dto.setEmployeeName(employee.getName());
    // 设置角色信息
    List<EmployeeRole> allByEmployeeId = employeeRoleRepository.findAllByEmployeeId(employee.getId());
    Map<Long, Role> roleMap = roleRepository.findAll().stream().collect(Collectors.toMap(Role::getId, r -> r));
    List<RoleDTO> roleList = new ArrayList<>();
    allByEmployeeId.forEach(e -> {
        Role role = roleMap.get(e.getRoleId());
        RoleDTO roleDTO = new RoleDTO();
        if (role != null) {
            BeanUtils.copyProperties(role, roleDTO);
        }
        roleList.add(roleDTO);
    });
    dto.setRoles(roleList);
    return dto;
}


public EmployeeCustomizedFormsDTO getEmployeeFormsDTO(Long formID,List<EmployeeCustomizedFormsDTO> employeeFormsDTOList){
    for (EmployeeCustomizedFormsDTO form : employeeFormsDTOList) {
        if (formID.equals(form.getFormId())) {
            // 移除之后在增加
            return form;
        }
    }
    return null;
}


public EmployeeRoleDTO getEmployeeWithRoleInfo(Long employeeId){
    Employee employee = getEmployeeById(employeeId);
    List<OrganizationEntity> organizations = organizationRepository.findAll();
    Map<Long, String> orgMap = organizations.stream().collect(Collectors.toMap(OrganizationEntity::getId, OrganizationEntity::getName));
    return convertEmpToEmpRole(employee, orgMap);
}


public List<CommonListDTO> findAllEmployees(){
    return employeeRepository.findEmployees();
}


@Transactional
public void transferEmployeeById(TransferEmployeeRequest transferEmployeeRequest,Long employeeId){
    employeePositionService.addEmployeePositionForTransferredEmployee(employeeId, transferEmployeeRequest);
    employeeHistoryService.saveEmployeeHistory(employeeId, EmployeeHistoryTitle.TRANSFER.getName(), transferEmployeeRequest.getDate(), transferEmployeeRequest.getReason());
}


public void setEmployeeEditStatus(List<FilterEmployeeDTO> dtoList){
    if (TenantThreadHelper.getToken().isSuperAdmin()) {
        dtoList.forEach(e -> e.setEnableEdit(true));
    } else {
        Set<Long> employeeIds = getPermissionEmployeeIds(ActionType.EDIT);
        dtoList.forEach(e -> e.setEnableEdit(employeeIds.contains(e.getId())));
    }
}


public Employee getEmployeeById(Long id){
    return employeeRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.EMPLOYEE_NOT_EXISTS));
}


public void setEmployeeCurrentPosition(List<FilterEmployeeDTO> dtoList){
    Map<Long, String> orgMap = organizationService.getIdNameMap();
    Map<Long, PositionEntity> positionMap = positionService.getIdEntityMap();
    Map<Long, String> gradeMap = gradeService.getIdNameMap();
    List<Long> employeeIds = dtoList.stream().map(FilterEmployeeDTO::getId).collect(Collectors.toList());
    Map<Long, EmployeePosition> currentPositionMap = employeePositionService.getEmployeeCurrentPositionMap(employeeIds);
    for (FilterEmployeeDTO dto : dtoList) {
        EmployeePosition currentEp = currentPositionMap.get(dto.getId());
        if (currentEp != null) {
            dto.setCompanyName(orgMap.get(currentEp.getCompanyId()));
            dto.setDepartmentName(orgMap.get(currentEp.getDepartmentId()));
            dto.setPositionName(positionMap.get(currentEp.getPositionId()).getName());
            dto.setGradeName(gradeMap.get(positionMap.get(currentEp.getPositionId()).getGradeId()));
        }
    }
}


public void saveOrUpdateEmployee(EmployeeRequest addEmployeeRequest,String ignoreProperties){
    EmployeePositionInfoDTO employeePositionInfoDTO = addEmployeeRequest.getPositionInfo();
    EmployeeBasicInfoDTO employeeBasicInfoDTO = addEmployeeRequest.getBasicInfo();
    EmployeeOtherInfoDTO employeeOtherInfoDTO = addEmployeeRequest.getOtherInfo();
    // employee表操作
    Employee employee = new Employee();
    BeanUtils.copyProperties(addEmployeeRequest, employee, ignoreProperties);
    BeanUtils.copyProperties(employeeBasicInfoDTO.getJob(), employee, ignoreProperties);
    if (!StringUtils.isEmpty(addEmployeeRequest.getUserId())) {
        employee.setUserId(addEmployeeRequest.getUserId());
    }
    if (!StringUtils.isEmpty(addEmployeeRequest.getAvatar()) && !StringUtils.isEmpty(addEmployeeRequest.getAvatarName())) {
        // 上传头像
        Attachment attachment = fileService.uploadFileToOSS(addEmployeeRequest.getAvatar(), addEmployeeRequest.getAvatarName());
        attachment.setOriginName(addEmployeeRequest.getAvatarName());
        employee.setAvatarFileId(attachment.getFileId());
        attachmentRepository.save(attachment);
    }
    // 更新自定义字段
    employee.setCustomizedForms(JSONArray.toJSONString(addEmployeeRequest.getCustomizedForms()));
    employee = employeeRepository.save(employee);
    // position表操作
    List<EmployeePositionDTO> positions = employeePositionInfoDTO.getPosition();
    long employeeId = employee.getId();
    saveEmployeePosition(employeeId, positions);
    employeeContractService.saveEmployeeContract(employeeId, employeeBasicInfoDTO.getContract());
    employeeIdentityService.saveEmployeeIdentity(employeeId, employeeBasicInfoDTO.getIdentity());
    employeeContactService.saveEmployeeContact(employeeId, employeeOtherInfoDTO.getContact());
    employeeEmergencyContactService.saveEmployeeEmergencyContact(employeeId, employeeOtherInfoDTO.getEmergencyContact());
    // education表操作
    employeeEducationService.saveEmployeeEducation(employeeId, employeeOtherInfoDTO.getEducation());
    // workExperience表操作
    employeeWorkExperienceService.saveEmployeeWorkExperience(employeeId, employeeOtherInfoDTO.getWorkExperience());
}


@Transactional
public void saveEmployeeCustomize(Long employeeId,EmployeeCustomizedFormsDTO employeeCustomizedFormsDTO){
    // 加上自定义表单验证，不存在抛出异常
    Long formID = employeeCustomizedFormsDTO.getFormId();
    customizedEmployeeFormRepository.findById(formID).orElseThrow(() -> new BizException(ResultCode.CUSTOMIZED_FORM_IS_NULL));
    Employee employee = getEmployeeById(employeeId);
    List<EmployeeCustomizedFormsDTO> employeeCustomizedFormsDTOList = JSONObject.parseArray(employee.getCustomizedForms(), EmployeeCustomizedFormsDTO.class);
    // 自定义表单中的文件处理
    JSONArray formData = employeeCustomizedFormsDTO.getFormData();
    JSONArray totalJsonArray = new JSONArray();
    if (!CollectionUtils.isEmpty(formData)) {
        for (Object obj : formData) {
            totalJsonArray.add(handlerFile(obj));
        }
    }
    employeeCustomizedFormsDTO.setFormData(totalJsonArray);
    if (employeeCustomizedFormsDTOList == null) {
        employeeCustomizedFormsDTOList = new ArrayList<>();
    }
    EmployeeCustomizedFormsDTO employeeCustomized = getEmployeeFormsDTO(formID, employeeCustomizedFormsDTOList);
    if (employeeCustomized == null) {
        employeeCustomizedFormsDTOList.add(employeeCustomizedFormsDTO);
    } else {
        BeanUtils.copyProperties(employeeCustomizedFormsDTO, employeeCustomized);
    }
    employee.setCustomizedForms(JSONObject.toJSONString(employeeCustomizedFormsDTOList));
    // 更新自定义字段
    employeeRepository.save(employee);
}


@Transactional
public void resignEmployeeById(ResignEmployeeRequest resignEmployeeRequest,Long id){
    if (resignEmployeeRequest.getHandoverManId().equals(id)) {
        throw new BizException(ResultCode.HANDOVER_MAN_CAN_NOT_BE_YOURSELF);
    }
    Employee employee = getEmployeeById(id);
    employeeRepository.findByIdAndStatusIsNot(resignEmployeeRequest.getHandoverManId(), EmployeeStatus.former).orElseThrow(() -> new BizException(ResultCode.HANDOVER_MAN_NOT_EXISTS));
    // 上传附件 && 将 fileId 添加到 employee 中
    Attachment attachment = saveResignAttachment(resignEmployeeRequest);
    resignEmployee(resignEmployeeRequest, employee, attachment);
    employeeContractService.saveEmployeeContractForResignation(resignEmployeeRequest, id);
    // 保存到历史
    employeeHistoryService.saveEmployeeHistory(id, EmployeeHistoryTitle.RESIGN.getName(), resignEmployeeRequest.getDate(), resignEmployeeRequest.getReason());
}


public Page<FilterEmployeeDTO> pageEmployees(FilterEmployeeRequest request){
    Pageable pageable = PageRequest.of(request.getPageNo() - 1, request.getPageSize(), Sort.by(Sort.Order.desc("id")));
    Specification<Employee> condition = (root, query, criteriaBuilder) -> {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(root.get("status"), request.getStatus()));
        if (!StringUtils.isEmpty(request.getKeyword())) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + request.getKeyword() + "%"));
        }
        Predicate[] p = new Predicate[predicates.size()];
        return criteriaBuilder.and(predicates.toArray(p));
    };
    // 查询权限范围内数据过滤
    if (!TenantThreadHelper.getToken().isSuperAdmin()) {
        Set<Long> queryEmployeeIds = getPermissionEmployeeIds(ActionType.GET);
        if (CollectionUtils.isEmpty(queryEmployeeIds)) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
        Specification<Employee> idInCondition = (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Long> idIn = criteriaBuilder.in(root.get("id"));
            queryEmployeeIds.forEach(idIn::value);
            return criteriaBuilder.and(idIn);
        };
        condition = condition.and(idInCondition);
    }
    Page<Employee> pageBean = employeeRepository.findAll(condition, pageable);
    List<FilterEmployeeDTO> dtoList = Lists.newArrayList();
    if (!pageBean.getContent().isEmpty()) {
        for (Employee employee : pageBean.getContent()) {
            FilterEmployeeDTO dto = new FilterEmployeeDTO();
            BeanUtils.copyProperties(employee, dto);
            dtoList.add(dto);
        }
        setEmployeeCurrentPosition(dtoList);
        setEmployeeEditStatus(dtoList);
        setAvatar(dtoList);
    }
    return new PageImpl<>(dtoList, pageBean.getPageable(), pageBean.getTotalElements());
}


public void findByEmployeeNoAndIdNot(String employeeNo,Long employeeId){
    List<Employee> employee = employeeRepository.findByEmployeeNoAndIdNot(employeeNo, employeeId);
    if (!CollectionUtils.isEmpty(employee)) {
        throw new BizException(ResultCode.EMPLOYEE_NO_EXISTS);
    }
}


public ResignedEmployeeResponse findResignedEmployeeById(Long id){
    Employee employee = employeeRepository.findByIdAndStatus(id, EmployeeStatus.former).orElseThrow(() -> new BizException(ResultCode.EMPLOYEE_NOT_EXISTS));
    ResignedEmployeeResponse resignedEmployeeResponse = new ResignedEmployeeResponse();
    // 交接人姓名
    String handoverManName = "";
    if (null != employee.getResignationHandoverManId()) {
        Optional<Employee> handoverManOptional = employeeRepository.findById(employee.getResignationHandoverManId());
        if (handoverManOptional.isPresent()) {
            handoverManName = handoverManOptional.get().getName();
        }
    }
    // 交接人dto
    ResignHandoverManDTO resignHandoverManDTO = new ResignHandoverManDTO();
    resignHandoverManDTO.setId(employee.getResignationHandoverManId());
    resignHandoverManDTO.setName(handoverManName);
    resignedEmployeeResponse.setDate(employee.getResignationDate());
    resignedEmployeeResponse.setHandoverMan(resignHandoverManDTO);
    resignedEmployeeResponse.setReason(employee.getResignationReason());
    resignedEmployeeResponse.setFileName(employee.getResignationLetterOriginName());
    if (null != employee.getResignationLetterFileId()) {
        resignedEmployeeResponse.setFileId(employee.getResignationLetterFileId());
    }
    return resignedEmployeeResponse;
}


public void resignEmployee(ResignEmployeeRequest resignEmployeeRequest,Employee employee,Attachment attachment){
    // 修改在职状态
    employee.setStatus(EmployeeStatus.former);
    employee.setResignationDate(resignEmployeeRequest.getDate());
    employee.setResignationReason(resignEmployeeRequest.getReason());
    employee.setResignationLetterOriginName(attachment.getOriginName());
    employee.setResignationLetterFileId(attachment.getFileId());
    employee.setResignationHandoverManId(resignEmployeeRequest.getHandoverManId());
    employeeRepository.save(employee);
    employeePositionService.updateLeaderId(employee.getId(), resignEmployeeRequest.getHandoverManId());
    // 清空员工权限信息
    employeePermissionService.deleteEmployeeRole(employee);
}


public Page<EmployeeRoleDTO> pageEmployeesWithRole(FilterEmployeeRequest request){
    Page<Employee> pageBean;
    PageRequest pageRequest = PageRequest.of(request.getPageNo() - 1, request.getPageSize(), Sort.by(Sort.Order.desc("id")));
    if (StringUtils.isEmpty(request.getKeyword())) {
        pageBean = employeeRepository.findByStatusNot(EmployeeStatus.former, pageRequest);
    } else {
        String likeKeyWord = "%" + request.getKeyword() + "%";
        pageBean = employeeRepository.findByNameLikeOrMobileLikeAndStatusNot(likeKeyWord, likeKeyWord, EmployeeStatus.former, pageRequest);
    }
    List<EmployeeRoleDTO> dtoList = new ArrayList<>();
    if (!pageBean.getContent().isEmpty()) {
        Map<Long, String> orgMap = organizationService.getIdNameMap();
        for (Employee employee : pageBean.getContent()) {
            EmployeeRoleDTO dto = convertEmpToEmpRole(employee, orgMap);
            dtoList.add(dto);
        }
    }
    return new PageImpl<>(dtoList, pageBean.getPageable(), pageBean.getTotalElements());
}


}