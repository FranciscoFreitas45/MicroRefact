package com.qidian.hcm.module.salary.service;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.salary.dto.SocialSecurityPlanDTO;
import com.qidian.hcm.module.salary.entity.EmployeeFinancial;
import com.qidian.hcm.module.salary.entity.SocialSecurityPlan;
import com.qidian.hcm.module.salary.repository.EmployeeFinancialRepository;
import com.qidian.hcm.module.salary.repository.SocialSecurityPlanRepository;
import com.qidian.hcm.module.salary.utils.SalaryFormulaIKeyword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
import com.qidian.hcm.Interface.EmployeeFinancialRepository;
import com.qidian.hcm.Interface.CalculateService;
@Slf4j
@Service
public class SocialSecurityPlanService {

@Autowired
 private  SocialSecurityPlanRepository socialSecurityPlanRepository;

@Autowired
 private  EmployeeFinancialRepository employeeFinancialRepository;

@Autowired
 private  CalculateService calculateService;


public void addSecurityPlan(SocialSecurityPlanDTO socialSecurityPlanDTO){
    validateNameExist(socialSecurityPlanDTO.getName(), 0L);
    SocialSecurityPlan socialSecurityPlan = new SocialSecurityPlan();
    BeanUtils.copyProperties(socialSecurityPlanDTO, socialSecurityPlan, "id");
    socialSecurityPlanRepository.save(socialSecurityPlan);
}


@Transactional
public void updateSecurityPlan(Long id,SocialSecurityPlanDTO socialSecurityPlanDTO){
    SocialSecurityPlan socialSecurityPlan = getSocialSecurityPlanAndValidate(id);
    validateNameExist(socialSecurityPlanDTO.getName(), id);
    BeanUtils.copyProperties(socialSecurityPlanDTO, socialSecurityPlan);
    socialSecurityPlan.setId(id);
    socialSecurityPlanRepository.save(socialSecurityPlan);
    // 计算社保信息变化的员工薪资项
    List<EmployeeFinancial> employeeFinancialList = employeeFinancialRepository.findBySocialSecurityPlanId(id);
    if (!CollectionUtils.isEmpty(employeeFinancialList)) {
        calculateService.formulaCalculate(employeeFinancialList.stream().map(EmployeeFinancial::getEmployeeId).collect(Collectors.toList()), SalaryFormulaIKeyword.SOCIAL_SECURITY);
    }
}


public void validateNameExist(String name,Long id){
    Optional<SocialSecurityPlan> socialSecurityPlanOptional = socialSecurityPlanRepository.findByNameAndIdNot(name, id);
    if (socialSecurityPlanOptional.isPresent()) {
        throw new BizException(ResultCode.SOCIAL_SECURITY_NAME_EXISTS);
    }
}


public void deleteSecurityPlan(Long id){
    getSocialSecurityPlanAndValidate(id);
    // 社保被引用无法删除
    List<EmployeeFinancial> employeeFinancialList = employeeFinancialRepository.findBySocialSecurityPlanId(id);
    if (!CollectionUtils.isEmpty(employeeFinancialList)) {
        throw new BizException(ResultCode.SOCIAL_SECURITY_USED);
    }
    socialSecurityPlanRepository.deleteById(id);
}


public SocialSecurityPlan getSocialSecurityPlanAndValidate(Long id){
    return socialSecurityPlanRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.SOCIAL_SECURITY_NOT_EXISTS));
}


public List<SocialSecurityPlanDTO> getSecurityPlans(){
    List<SocialSecurityPlan> socialSecurityPlans = socialSecurityPlanRepository.findAll();
    List<SocialSecurityPlanDTO> socialSecurityPlanDTOS = newArrayListWithExpectedSize(socialSecurityPlans.size());
    socialSecurityPlans.forEach(item -> {
        SocialSecurityPlanDTO socialSecurityPlanDTO = new SocialSecurityPlanDTO();
        BeanUtils.copyProperties(item, socialSecurityPlanDTO);
        socialSecurityPlanDTOS.add(socialSecurityPlanDTO);
    });
    return socialSecurityPlanDTOS;
}


}