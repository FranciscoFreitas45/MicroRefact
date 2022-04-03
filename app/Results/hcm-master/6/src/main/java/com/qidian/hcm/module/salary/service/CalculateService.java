package com.qidian.hcm.module.salary.service;
 import com.alibaba.fastjson.JSON;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.DateUtil;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.employee.entity.Employee;
import com.qidian.hcm.module.employee.repository.EmployeeRepository;
import com.qidian.hcm.module.salary.dto;
import com.qidian.hcm.module.salary.entity;
import com.qidian.hcm.module.salary.enums.PointRule;
import com.qidian.hcm.module.salary.enums.SalarySettingEnum;
import com.qidian.hcm.module.salary.repository;
import com.qidian.hcm.module.salary.response.SalaryItemMonthlyResponse;
import com.qidian.hcm.module.salary.utils.SalaryItemsConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.jexl2;
import org.apache.commons.jexl2.parser.TokenMgrError;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.google.common.collect.Lists.newArrayList;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
import com.google.common.collect.Maps.newHashMapWithExpectedSize;
import com.qidian.hcm.Interface.EmployeeRepository;
import com.qidian.hcm.Interface.SalaryThresholdRepository;
import com.qidian.hcm.Interface.HousingFundPlanRepository;
import com.qidian.hcm.Interface.SocialSecurityPlanRepository;
@Slf4j
@Service
public class CalculateService {

@Autowired
 private  SalaryItemRepository salaryItemRepository;

@Autowired
 private  EmployeeRepository employeeRepository;

@Autowired
 private  EmployeeSalaryMonthlyRepository employeeSalaryMonthlyRepository;

@Autowired
 private  ExternalFormulaService externalFormulaService;

@Autowired
 private  SalarySettingService salarySettingService;

@Autowired
 private  SalaryThresholdRepository salaryThresholdRepository;

@Autowired
 private  HousingFundPlanRepository housingFundPlanRepository;

@Autowired
 private  SocialSecurityPlanRepository socialSecurityPlanRepository;

@Autowired
 private  EmployeeSalaryMonthlyService employeeSalaryMonthlyService;

 private  SecureRandom secureRandom;

 private  Pattern pattern;

 private  ThreadLocal<JexlEngine> jexlEngine;


public List<SalaryItem> findParentFormulaLike(Map<String,FormulaDTO> formulaRelation,String likeStr){
    List<SalaryItem> salaryItemList = salaryItemRepository.findByFormulaLike("%" + likeStr + "%");
    List<String> salaryItemCodes = salaryItemList.stream().map(SalaryItem::getCode).collect(Collectors.toList());
    return findParentSalaryItem(formulaRelation, salaryItemCodes, Boolean.TRUE);
}


public List<String> validateFormulaCircle(Map<String,FormulaDTO> formulaRelation,String code){
    List<String> children = new ArrayList<>();
    lispChildrenPathCode(formulaRelation, children, code, code);
    return children;
}


public void setParentsList(List<FormulaDTO> formulaStream,FormulaDTO formulaDTO){
    String queryStr = SalaryItemsConst.PREFIX + formulaDTO.getCode() + SalaryItemsConst.SUFFIX;
    for (FormulaDTO childrenFormulaDTO : formulaStream) {
        // 排除自生(查找父亲节点可能是自身)
        if (formulaDTO.equals(childrenFormulaDTO)) {
            continue;
        }
        String formula = childrenFormulaDTO.getFormula();
        if (StringUtils.isNotBlank(formula) && formula.indexOf(queryStr) > -1) {
            List<String> parentsFormulaL = formulaDTO.getParents();
            if (CollectionUtils.isEmpty(parentsFormulaL)) {
                parentsFormulaL = new ArrayList<>();
                formulaDTO.setParents(parentsFormulaL);
            }
            if (!parentsFormulaL.contains(childrenFormulaDTO.getCode())) {
                parentsFormulaL.add(childrenFormulaDTO.getCode());
            }
        }
    }
}


public void buildFormulaRelation(Map<String,FormulaDTO> formulaMap){
    // 建立父子关系，方便遍历关联关系
    List<FormulaDTO> formulaDTOList = formulaMap.values().stream().sorted((first, second) -> {
        String firstFormula = first.getFormula();
        String secondFormula = second.getFormula();
        if (StringUtils.isNotBlank(firstFormula) && firstFormula.indexOf(SalaryItemsConst.METHOD_PREFIX) > -1) {
            return -1;
        } else if (StringUtils.isNotBlank(secondFormula) && secondFormula.indexOf(SalaryItemsConst.METHOD_PREFIX) > -1) {
            return 1;
        }
        return 0;
    }).collect(Collectors.toList());
    for (FormulaDTO formulaDTO : formulaDTOList) {
        String formula = formulaDTO.getFormula();
        if (StringUtils.isNotBlank(formula)) {
            List<String> codeList = parseMethodParam(formulaDTO, formula);
            formulaDTO.setChildren(codeList);
        }
        setParentsList(formulaDTOList, formulaDTO);
    }
}


public Map<String,FormulaDTO> getFormulaDTOMap(){
    return salaryItemRepository.findAll().stream().collect(Collectors.toMap(SalaryItem::getCode, salaryItem -> {
        FormulaDTO formulaDTO = new FormulaDTO();
        BeanUtils.copyProperties(salaryItem, formulaDTO);
        return formulaDTO;
    }));
}


public Map<Long,List<SalaryItemMonthlyResponse>> handleResult(Map<Long,Map<String,Object>> employeeCodeValueMap){
    if (CollectionUtils.isEmpty(employeeCodeValueMap)) {
        return Collections.EMPTY_MAP;
    }
    List<SalaryItem> salaryItemList = null;
    Map<Long, List<SalaryItemMonthlyResponse>> salaryItemMonthlyMap = newHashMapWithExpectedSize(employeeCodeValueMap.size());
    Set<Map.Entry<Long, Map<String, Object>>> entrySet = employeeCodeValueMap.entrySet();
    for (Map.Entry<Long, Map<String, Object>> entry : entrySet) {
        if (salaryItemList == null) {
            salaryItemList = salaryItemRepository.findAllByCodeIn(entry.getValue().keySet());
        }
        List<SalaryItemMonthlyResponse> salaryItemMonthlyResponseList = newArrayListWithExpectedSize(salaryItemList.size());
        salaryItemList.forEach(salaryItem -> {
            SalaryItemMonthlyResponse salaryItemMonthlyResponse = new SalaryItemMonthlyResponse();
            BeanUtils.copyProperties(salaryItem, salaryItemMonthlyResponse);
            Object value = entry.getValue().get(salaryItem.getCode());
            Double formatValue = formatDouble(value.toString(), salaryItem.getPointScale(), salaryItem.getPointRule());
            salaryItemMonthlyResponse.setValue(formatValue);
            salaryItemMonthlyResponse.setModified(Boolean.FALSE);
            salaryItemMonthlyResponseList.add(salaryItemMonthlyResponse);
        });
        salaryItemMonthlyMap.put(entry.getKey(), salaryItemMonthlyResponseList);
    }
    return salaryItemMonthlyMap;
}


public List<String> findParameter(String formula){
    Matcher matcher = pattern.matcher(formula);
    List<String> list = new ArrayList<>();
    while (matcher.find()) {
        String groupCode = matcher.group();
        if (!list.contains(groupCode)) {
            list.add(groupCode);
        }
    }
    return list;
}


public void lispParentPathCode(Map<String,FormulaDTO> formulaRelation,List<String> parentResult,String code){
    FormulaDTO formulaDTO = formulaRelation.get(code);
    List<String> parents = formulaDTO.getParents();
    if (!CollectionUtils.isEmpty(parents)) {
        parents.forEach(item -> {
            if (!parentResult.contains(item)) {
                parentResult.add(item);
            }
        });
        parents.forEach(item -> lispParentPathCode(formulaRelation, parentResult, item));
    }
}


public void createThreadJexlEngine(){
    if (Objects.isNull(jexlEngine.get())) {
        jexlEngine.set(new JexlEngine());
    }
}


public JexlContext getMockMapContext(){
    ExternalEmployeeDTO employee = new ExternalEmployeeDTO();
    Date finalDate = new Date();
    employee.setCurrentCycleMonth(DateUtil.convertDateToStr(finalDate, DateUtil.DATE_FORMAT_Y_M_D));
    employee.setHireDate(finalDate);
    employee.setWorkDate(finalDate);
    employee.setResignationDate(finalDate);
    employee.getHousingFund().setEffectDate(finalDate);
    employee.getSocialSecurity().setEffectDate(finalDate);
    // 设置上下文信息
    JexlContext jexlContext = new MapContext();
    jexlContext.set(SalaryItemsConst.EXTERNAL_EMPLOYEE, employee);
    jexlContext.set(SalaryItemsConst.EXTERNAL_SERVICE, externalFormulaService);
    return jexlContext;
}


public List<String> parseMethodParam(FormulaDTO parentFormulaDTO,String formula){
    List<String> codeList;
    // 如果有 #{ 说明有变量
    if (formula.indexOf(SalaryItemsConst.METHOD_PREFIX) > -1) {
        codeList = findParameter(formula);
        formula = parsedFormula(formula, codeList.stream().collect(Collectors.toMap(String::toString, str -> SalaryItemsConst.PREFIX + str + SalaryItemsConst.SUFFIX)));
        parentFormulaDTO.setFormula(formula);
    }
    // 查找成功第二次解析
    codeList = findParameter(formula);
    return codeList;
}


public void lispCalculateOneItems(Map<String,FormulaDTO> formulaRelation,FormulaDTO formulaDTO,ExternalEmployeeDTO employeeInfoDTO,Map<String,Object> employeeCache){
    List<String> children = formulaDTO.getChildren();
    if (!CollectionUtils.isEmpty(children)) {
        children.forEach(childrenCode -> {
            FormulaDTO childrenFormula = formulaRelation.get(childrenCode);
            if (Objects.isNull(childrenFormula)) {
                log.error("公式配置错误,[{}]", childrenCode);
                throw new BizException(ResultCode.SALARY_ITEM_CODE_NOT_EXISTS);
            }
            lispCalculateOneItems(formulaRelation, childrenFormula, employeeInfoDTO, employeeCache);
        });
    }
    String formulaCode = formulaDTO.getCode();
    if (!employeeCache.containsKey(formulaCode)) {
        JexlContext jexlContext = new MapContext();
        jexlContext.set(SalaryItemsConst.EXTERNAL_EMPLOYEE, employeeInfoDTO);
        jexlContext.set(SalaryItemsConst.EXTERNAL_SERVICE, externalFormulaService);
        String formulaStr = parsedFormulaValue(formulaDTO.getFormula(), employeeCache);
        Number number = calculate(formulaStr, employeeCache, jexlContext, Boolean.TRUE);
        log.info("【{}】的值为{}", formulaCode, number);
        employeeCache.put(formulaDTO.getCode(), number);
    }
}


public String parsedFormula(String formula,Map<String,Object> map){
    if (StringUtils.isBlank(formula) || formula.indexOf(SalaryItemsConst.METHOD_PREFIX) == -1) {
        return formula;
    }
    JexlContext context = new MapContext(map);
    context.set(SalaryItemsConst.EXTERNAL_SERVICE, externalFormulaService);
    UnifiedJEXL unifiedJEXL = new UnifiedJEXL(jexlEngine.get());
    UnifiedJEXL.Expression expression = unifiedJEXL.parse(formula);
    String result = expression.evaluate(context).toString();
    log.info("parsedFormula 解析之前: {}", formula);
    log.info("parsedFormula 解析之后: {}", result);
    return result;
}


public Map<Long,List<SalaryItemMonthlyDTO>> getEmployeeMonthlyMap(List<Long> employeeIds,String currentCycleMonth){
    if (CollectionUtils.isEmpty(employeeIds) || StringUtils.isBlank(currentCycleMonth)) {
        return Collections.EMPTY_MAP;
    }
    List<EmployeeSalaryMonthly> employeeSalaryMonthlyList = employeeSalaryMonthlyRepository.findByEmployeeIdInAndDateAndIncludeIsTrue(employeeIds, currentCycleMonth);
    if (!CollectionUtils.isEmpty(employeeSalaryMonthlyList)) {
        return employeeSalaryMonthlyList.stream().filter(salaryMonthly -> StringUtils.isNotBlank(salaryMonthly.getItemsResult())).collect(Collectors.toMap(EmployeeSalaryMonthly::getEmployeeId, employeeSalaryMonthly -> JSON.parseArray(employeeSalaryMonthly.getItemsResult(), SalaryItemMonthlyDTO.class)));
    }
    return Collections.EMPTY_MAP;
}


public void saveMonthlyResultValue(Map<Long,List<SalaryItemMonthlyResponse>> salaryItemMonthlyMap){
    // 当前月份（薪资项不计算）
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    Map<Long, EmployeeSalaryMonthly> employeeSalaryMonthlyMap = employeeSalaryMonthlyRepository.findByEmployeeIdInAndDate(salaryItemMonthlyMap.keySet(), currentCycleMonth).stream().collect(Collectors.toMap(EmployeeSalaryMonthly::getEmployeeId, employeeSalaryMonthly -> employeeSalaryMonthly));
    List<EmployeeSalaryMonthly> employeeSalaryMonthlyList = new ArrayList<>();
    salaryItemMonthlyMap.forEach((employeeId, salaryItemMonthlyList) -> {
        EmployeeSalaryMonthly employeeSalaryMonthly = employeeSalaryMonthlyMap.get(employeeId);
        if (employeeSalaryMonthly == null) {
            employeeSalaryMonthly = new EmployeeSalaryMonthly();
            employeeSalaryMonthly.setEmployeeId(employeeId);
            employeeSalaryMonthly.setDate(currentCycleMonth);
            employeeSalaryMonthly.setInclude(employeeSalaryMonthlyService.isEmployeeSalaryInclude(employeeId));
        }
        List<SalaryItemMonthlyDTO> salaryItemMonthlyDTOList = JSON.parseArray(employeeSalaryMonthly.getItemsResult(), SalaryItemMonthlyDTO.class);
        if (salaryItemMonthlyDTOList == null) {
            salaryItemMonthlyDTOList = new ArrayList<>();
        }
        for (SalaryItemMonthlyResponse salaryItemMonthlyResponse : salaryItemMonthlyList) {
            SalaryItemMonthlyDTO salaryItemMonthlyDTO = employeeSalaryMonthlyService.getSalaryItemMonthlyDTO(salaryItemMonthlyDTOList, salaryItemMonthlyResponse.getId());
            BeanUtils.copyProperties(salaryItemMonthlyResponse, salaryItemMonthlyDTO);
        }
        employeeSalaryMonthly.setItemsResult(JSON.toJSONString(salaryItemMonthlyDTOList));
        employeeSalaryMonthlyList.add(employeeSalaryMonthly);
    });
    employeeSalaryMonthlyRepository.saveAll(employeeSalaryMonthlyList);
}


@Transactional
public void formulaCalculateByEmployee(List<Long> employeeIds){
    Map<String, FormulaDTO> formulaRelation = getRelationFormulaDTOMap();
    // 当前月份（薪资项不计算）
    Map<Long, List<SalaryItemMonthlyResponse>> salaryItemMap = doFormulaCalculate(formulaRelation, employeeIds, null, null);
    saveMonthlyResultValue(salaryItemMap);
}


public ExternalEmployeeDTO initEmployeeInfo(Employee employee){
    ExternalEmployeeDTO employeeInfoDTO = new ExternalEmployeeDTO();
    BeanUtils.copyProperties(employee, employeeInfoDTO);
    EmployeeFinancial employeeFinancial = employee.getEmployeeFinancial();
    // 设置默认财务信息
    if (Objects.isNull(employeeFinancial)) {
        employeeFinancial = new EmployeeFinancial();
        BeanUtils.copyProperties(employeeFinancial, employeeInfoDTO);
        return employeeInfoDTO;
    }
    // 枚举拷贝
    employeeInfoDTO.setGender(employee.getGender() == null ? null : employee.getGender().name());
    employeeInfoDTO.setType(employee.getType() == null ? null : employee.getType().name());
    employeeInfoDTO.setStatus(employee.getStatus() == null ? null : employee.getStatus().name());
    BeanUtils.copyProperties(employeeFinancial, employeeInfoDTO);
    Long thresholdId = employeeFinancial.getThresholdId();
    if (Objects.nonNull(thresholdId)) {
        // 设置免税额
        SalaryThreshold salaryThreshold = salaryThresholdRepository.findById(thresholdId).orElseThrow(() -> new BizException(ResultCode.SALARY_THRESHOLD_NOT_EXISTS));
        ExternalThresholdDTO externalThresholdDTO = employeeInfoDTO.getThreshold();
        BeanUtils.copyProperties(salaryThreshold, externalThresholdDTO);
        // 枚举拷贝
        externalThresholdDTO.setType(salaryThreshold.getType().name());
    }
    Long housingFundPlanId = employeeFinancial.getHousingFundPlanId();
    if (Objects.nonNull(housingFundPlanId)) {
        // 设置公积金
        HousingFundPlan housingFundPlan = housingFundPlanRepository.findById(housingFundPlanId).orElseThrow(() -> new BizException(ResultCode.EMPLOYEE_HOUSING_FUND_NOT_EXISTS));
        ExternalHousingFundPlanDTO externalHousingFundPlanDTO = employeeInfoDTO.getHousingFund();
        BeanUtils.copyProperties(housingFundPlan, externalHousingFundPlanDTO);
        // 枚举拷贝
        externalHousingFundPlanDTO.setPointRule(housingFundPlan.getPointRule().name());
    }
    Long socialSecurityPlanId = employeeFinancial.getSocialSecurityPlanId();
    if (Objects.nonNull(socialSecurityPlanId)) {
        // 设置社保
        SocialSecurityPlan socialSecurityPlan = socialSecurityPlanRepository.findById(socialSecurityPlanId).orElseThrow(() -> new BizException(ResultCode.EMPLOYEE_HOUSING_FUND_NOT_EXISTS));
        ExternalSocialSecurityPlanDTO externalSocialSecurityPlanDTO = employeeInfoDTO.getSocialSecurity();
        BeanUtils.copyProperties(socialSecurityPlan, externalSocialSecurityPlanDTO);
        // 枚举拷贝
        externalSocialSecurityPlanDTO.setPointRule(socialSecurityPlan.getPointRule().name());
    }
    return employeeInfoDTO;
}


@Transactional
public void formulaCalculateBySalaryItem(List<SalaryItem> salaryItem){
    // 当前月份（薪资项不计算）
    Map<String, FormulaDTO> formulaRelation = getRelationFormulaDTOMap();
    List<SalaryItem> salaryItemList = findParentSalaryItem(formulaRelation, salaryItem.stream().map(SalaryItem::getCode).collect(Collectors.toList()), Boolean.TRUE);
    if (!CollectionUtils.isEmpty(salaryItemList)) {
        Map<Long, List<SalaryItemMonthlyResponse>> salaryItemMap = doFormulaCalculate(formulaRelation, null, salaryItemList, null);
        saveMonthlyResultValue(salaryItemMap);
    }
}


public void validateFormula(SalaryItem salaryItem){
    createThreadJexlEngine();
    // 构建公式关系
    String itemCode = salaryItem.getCode();
    Map<String, FormulaDTO> formulaRelation = getFormulaDTOMap();
    FormulaDTO updatedFormulaDTO = new FormulaDTO();
    BeanUtils.copyProperties(salaryItem, updatedFormulaDTO);
    formulaRelation.put(itemCode, updatedFormulaDTO);
    buildFormulaRelation(formulaRelation);
    // 验证公式是否循环引用
    validateFormulaCircle(formulaRelation, itemCode);
    FormulaDTO resultFormulaDTO = formulaRelation.get(salaryItem.getCode());
    List<String> codeList = resultFormulaDTO.getChildren();
    Map<String, Object> formulaValueMap = null;
    if (!CollectionUtils.isEmpty(codeList)) {
        codeList.forEach(code -> {
            if (Objects.isNull(formulaRelation.get(code))) {
                throw new BizException(ResultCode.SALARY_ITEM_CODE_NOT_EXISTS, ResultCode.SALARY_ITEM_CODE_NOT_EXISTS.getMsg() + ":" + code);
            }
        });
        formulaValueMap = codeList.stream().collect(Collectors.toMap(String::toString, str -> secureRandom.nextDouble() * 1000 + 1000D));
    }
    try {
        String resultFormula = parsedFormulaValue(resultFormulaDTO.getFormula(), formulaValueMap);
        Number number = calculate(resultFormula, null, getMockMapContext(), Boolean.FALSE);
        log.info(number.toString());
    } catch (TokenMgrError | JexlException e) {
        log.info("公式验证出错！");
        BizException exception = new BizException(ResultCode.SALARY_ITEM_PARSE_ERROR);
        exception.initCause(e);
        throw exception;
    }
}


public Number calculate(String expression,Map<String,Object> parameter,JexlContext jexlContext,Boolean lenient){
    JexlEngine jexlEngine = this.jexlEngine.get();
    jexlEngine.setLenient(lenient);
    Expression expr = jexlEngine.createExpression(expression);
    if (!CollectionUtils.isEmpty(parameter)) {
        Iterator iterator = parameter.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> elem = (Map.Entry<String, Object>) iterator.next();
            jexlContext.set(elem.getKey(), elem.getValue());
        }
    }
    Object object = expr.evaluate(jexlContext);
    return Objects.nonNull(object) ? (Number) object : 0;
}


@Transactional
public void formulaCalculate(){
    Map<String, FormulaDTO> formulaRelation = getRelationFormulaDTOMap();
    Map<Long, List<SalaryItemMonthlyResponse>> salaryMap = doFormulaCalculate(formulaRelation, null, null, null);
    saveMonthlyResultValue(salaryMap);
}


public Map<String,Object> getSalaryItemsInflow(List<SalaryItem> salaryItemsInflowList,List<SalaryItemMonthlyDTO> salaryItemMonthlyDTOList){
    if (CollectionUtils.isEmpty(salaryItemMonthlyDTOList)) {
        return new HashMap<>();
    }
    Map<String, Object> employeeCache = new HashMap<>();
    for (SalaryItem salaryItemsInflow : salaryItemsInflowList) {
        for (SalaryItemMonthlyDTO salaryItemMonthlyDTO : salaryItemMonthlyDTOList) {
            if (salaryItemsInflow.getCode().equals(salaryItemMonthlyDTO.getCode())) {
                employeeCache.put(salaryItemsInflow.getCode(), salaryItemMonthlyDTO.getValue());
            }
        }
    }
    return employeeCache;
}


public void lispChildrenPathCode(Map<String,FormulaDTO> formulaRelation,List<String> childrenResult,String code,String validateCode){
    FormulaDTO formulaDTO = formulaRelation.get(code);
    if (Objects.isNull(formulaDTO)) {
        throw new BizException(ResultCode.SALARY_ITEM_CODE_NOT_EXISTS, ResultCode.SALARY_ITEM_CODE_NOT_EXISTS.getMsg() + ":" + code);
    }
    List<String> children = formulaDTO.getChildren();
    if (!CollectionUtils.isEmpty(children)) {
        children.forEach(item -> {
            if (!childrenResult.contains(item)) {
                childrenResult.add(item);
            }
            if (childrenResult.contains(validateCode)) {
                log.info("{}:{}", ResultCode.SALARY_ITEM_IS_CYCLE.getMsg(), code);
                throw new BizException(ResultCode.SALARY_ITEM_IS_CYCLE);
            }
        });
        children.forEach(item -> lispChildrenPathCode(formulaRelation, childrenResult, item, validateCode));
    }
}


public List<String> getParentPathCode(Map<String,FormulaDTO> formulaRelation,String code){
    List<String> parent = new ArrayList<>();
    lispParentPathCode(formulaRelation, parent, code);
    return parent;
}


public void calculateOneEmployeeItems(Map<String,FormulaDTO> formulaRelation,SalaryItem salaryItem,ExternalEmployeeDTO employeeInfoDTO,Map<String,Object> employeeCache){
    FormulaDTO formulaDTO = formulaRelation.get(salaryItem.getCode());
    if (Objects.isNull(formulaDTO)) {
        log.error("编码{},公式配置错误:[{}]", salaryItem.getCode(), salaryItem.getFormula());
        throw new BizException(ResultCode.SALARY_ITEM_CODE_NOT_EXISTS);
    }
    lispCalculateOneItems(formulaRelation, formulaDTO, employeeInfoDTO, employeeCache);
}


public Double formatDouble(String value,Integer pointScale,PointRule pointRule){
    BigDecimal bg = new BigDecimal(value).setScale(pointScale, pointRule.getRoundingMode());
    return bg.doubleValue();
}


public List<SalaryItemMonthlyResponse> accountingCalculate(Long employeeId,SalaryItem salaryItem,Double value){
    // 当前月份（薪资项不计算）
    Map<String, FormulaDTO> formulaRelation = getRelationFormulaDTOMap();
    List<SalaryItem> parentSalaryItem = findParentSalaryItem(formulaRelation, newArrayList(salaryItem.getCode()), Boolean.FALSE);
    // 如果没有被引用不需要计算
    if (CollectionUtils.isEmpty(parentSalaryItem)) {
        return Collections.EMPTY_LIST;
    }
    Map<String, Object> defaultValueMap = new HashMap<>();
    defaultValueMap.put(salaryItem.getCode(), value);
    return doFormulaCalculate(formulaRelation, newArrayList(employeeId), parentSalaryItem, defaultValueMap).get(employeeId);
}


public Map<Long,List<SalaryItemMonthlyResponse>> doFormulaCalculate(Map<String,FormulaDTO> formulaRelation,List<Long> employeeIds,List<SalaryItem> salaryItems,Map<String,Object> defaultValueMap){
    createThreadJexlEngine();
    // 获取需要计算的薪资项
    if (CollectionUtils.isEmpty(salaryItems)) {
        salaryItems = salaryItemRepository.findAllByInflow(Boolean.FALSE);
    }
    // 获取需要的员工
    List<Employee> employeeList;
    if (CollectionUtils.isEmpty(employeeIds)) {
        employeeList = employeeRepository.findAll();
    } else {
        employeeList = employeeRepository.findAllById(employeeIds);
    }
    // 获取输入字段考勤字段
    List<SalaryItem> salaryItemsInflow = salaryItemRepository.findAllByInflow(Boolean.TRUE);
    String currentCycleMonth = salarySettingService.getSalarySettingValue(SalarySettingEnum.CURRENT_CYCLE_MONTH);
    if (StringUtils.isBlank(currentCycleMonth)) {
        throw new BizException(ResultCode.SALARY_CURRENT_CYCLE_NOT_EXISTS);
    }
    // 员工薪资项计算结果信息
    Map<Long, Map<String, Object>> employeeCodeValueMap = newHashMapWithExpectedSize(employeeList.size());
    // 获取员工薪资项对应信息
    Map<Long, List<SalaryItemMonthlyDTO>> salaryMonthlyMap = getEmployeeMonthlyMap(employeeIds, currentCycleMonth);
    for (Employee employee : employeeList) {
        ExternalEmployeeDTO employeeInfoDTO = initEmployeeInfo(employee);
        employeeInfoDTO.setCurrentCycleMonth(currentCycleMonth);
        // 中间结果缓存值，避免重复计算
        Map<String, Object> employeeCache = getSalaryItemsInflow(salaryItemsInflow, salaryMonthlyMap.get(employee.getId()));
        if (!CollectionUtils.isEmpty(defaultValueMap)) {
            employeeCache.putAll(defaultValueMap);
        }
        for (SalaryItem salaryItem : salaryItems) {
            calculateOneEmployeeItems(formulaRelation, salaryItem, employeeInfoDTO, employeeCache);
        }
        employeeCodeValueMap.put(employee.getId(), employeeCache);
    }
    // 排除考勤字段，考勤不用计算
    salaryItemsInflow.forEach(salaryItem -> employeeCodeValueMap.remove(salaryItem.getCode()));
    Map<Long, List<SalaryItemMonthlyResponse>> salaryItemMonthlyMap = handleResult(employeeCodeValueMap);
    log.info(JSON.toJSONString(salaryItemMonthlyMap));
    return salaryItemMonthlyMap;
}


public String parsedFormulaValue(String formula,Map<String,Object> map){
    if (StringUtils.isBlank(formula) || formula.indexOf(SalaryItemsConst.PREFIX) == -1) {
        return formula;
    }
    JexlContext context = new MapContext(map);
    UnifiedJEXL unifiedJEXL = new UnifiedJEXL(jexlEngine.get());
    UnifiedJEXL.Expression expression = unifiedJEXL.parse(formula);
    return expression.evaluate(context).toString();
}


public List<SalaryItem> findParentSalaryItem(Map<String,FormulaDTO> formulaRelation,List<String> codeList,Boolean include){
    List<String> resultCode = new ArrayList<>();
    codeList.forEach(code -> {
        List<String> parentPathCode = getParentPathCode(formulaRelation, code);
        resultCode.addAll(parentPathCode);
    });
    if (include) {
        resultCode.addAll(codeList);
    } else if (CollectionUtils.isEmpty(resultCode)) {
        return Collections.EMPTY_LIST;
    }
    return salaryItemRepository.findAllByCodeIn(resultCode);
}


public Map<String,FormulaDTO> getRelationFormulaDTOMap(){
    createThreadJexlEngine();
    // 构建公式编码对应表
    Map<String, FormulaDTO> formulaRelation = getFormulaDTOMap();
    buildFormulaRelation(formulaRelation);
    return formulaRelation;
}


}