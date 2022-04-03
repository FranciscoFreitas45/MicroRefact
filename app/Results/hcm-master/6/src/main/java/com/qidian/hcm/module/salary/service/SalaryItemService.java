package com.qidian.hcm.module.salary.service;
 import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.salary.entity.SalaryItem;
import com.qidian.hcm.module.salary.repository.SalaryItemRepository;
import com.qidian.hcm.module.salary.request.SalaryItemRequest;
import com.qidian.hcm.module.salary.response.SalaryItemResponse;
import com.qidian.hcm.module.salary.response.SalaryItemsOptionResponse;
import com.qidian.hcm.module.salary.utils.SalaryItemsConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Optional;
import com.google.common.collect.Lists.newArrayList;
import com.google.common.collect.Lists.newArrayListWithExpectedSize;
@Slf4j
@Service
public class SalaryItemService {

 private  String CODE_PREFIX;

@Autowired
 private  SalaryItemRepository salaryItemRepository;

@Autowired
 private  CalculateService calculateService;


@Transactional
public Long addSalaryItem(SalaryItemRequest salaryItemRequest){
    validateNameExist(salaryItemRequest.getName(), 0L);
    SalaryItem salaryItem = new SalaryItem();
    BeanUtils.copyProperties(salaryItemRequest, salaryItem, "id");
    salaryItem.setDeletable(Boolean.TRUE);
    salaryItem.setInList(Boolean.TRUE);
    salaryItem.setInOption(Boolean.TRUE);
    salaryItem.setDeletable(Boolean.TRUE);
    salaryItem.setEditable(Boolean.TRUE);
    salaryItem.setInflow(Boolean.FALSE);
    salaryItem = salaryItemRepository.save(salaryItem);
    salaryItem.setCode(CODE_PREFIX + salaryItem.getId());
    calculateService.validateFormula(salaryItem);
    salaryItemRepository.save(salaryItem);
    calculateService.formulaCalculateBySalaryItem(newArrayList(salaryItem));
    return salaryItem.getId();
}


public List<SalaryItemResponse> getSalaryItems(){
    List<SalaryItem> salaryItems = salaryItemRepository.findByInListOrderByIdAsc(Boolean.TRUE);
    List<SalaryItemResponse> salaryItemDTOS = newArrayListWithExpectedSize(salaryItems.size());
    salaryItems.forEach(item -> {
        SalaryItemResponse salaryItemResponse = new SalaryItemResponse();
        BeanUtils.copyProperties(item, salaryItemResponse);
        salaryItemDTOS.add(salaryItemResponse);
    });
    return salaryItemDTOS;
}


public void validateNameExist(String name,Long id){
    Optional<SalaryItem> salaryItemOptional = salaryItemRepository.findByNameAndIdNot(name, id);
    if (salaryItemOptional.isPresent()) {
        throw new BizException(ResultCode.SALARY_ITEM_NAME_EXISTS);
    }
}


public List<SalaryItemsOptionResponse> getSalaryItemOption(){
    List<SalaryItem> salaryItemList = salaryItemRepository.findByInOption(Boolean.TRUE);
    List<SalaryItemsOptionResponse> salaryItemsOptionList = newArrayListWithExpectedSize(salaryItemList.size());
    salaryItemList.forEach(salaryItem -> {
        SalaryItemsOptionResponse salaryItemResponse = new SalaryItemsOptionResponse();
        BeanUtils.copyProperties(salaryItem, salaryItemResponse);
        salaryItemsOptionList.add(salaryItemResponse);
    });
    return salaryItemsOptionList;
}


public SalaryItem getSalaryItemAndValidate(Long id){
    return salaryItemRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.SALARY_ITEM_NOT_EXISTS));
}


public void deleteSalaryItem(Long id){
    SalaryItem salaryItem = getSalaryItemAndValidate(id);
    if (!salaryItem.getEditable()) {
        throw new BizException(ResultCode.SALARY_ITEM_NOT_EDIT);
    }
    // 判断是否被其它公式引用
    String queryStr = "%" + SalaryItemsConst.PREFIX + salaryItem.getCode() + SalaryItemsConst.SUFFIX + "%";
    List<SalaryItem> employeeFinancialList = salaryItemRepository.findByFormulaLike(queryStr);
    if (!CollectionUtils.isEmpty(employeeFinancialList)) {
        throw new BizException(ResultCode.SALARY_ITEM_USED);
    }
    salaryItemRepository.deleteById(id);
}


@Transactional
public void updateSalaryItem(Long id,SalaryItemRequest salaryItemRequest){
    SalaryItem salaryItem = getSalaryItemAndValidate(id);
    if (!salaryItem.getEditable()) {
        throw new BizException(ResultCode.SALARY_ITEM_NOT_EDIT);
    }
    validateNameExist(salaryItemRequest.getName(), id);
    BeanUtils.copyProperties(salaryItemRequest, salaryItem);
    calculateService.validateFormula(salaryItem);
    salaryItem.setId(id);
    calculateService.formulaCalculateBySalaryItem(newArrayList(salaryItem));
    salaryItemRepository.save(salaryItem);
}


}