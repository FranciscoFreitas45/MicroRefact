package com.hmm.finance.salary.service;
 import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.finance.financeReport.domain.FinanceReport;
import com.hmm.finance.salary.domain.SalaryOrder;
import com.hmm.finance.salary.domain.SalaryOrderDTO;
import com.hmm.finance.salary.domain.SalaryOrderQueryDTO;
import com.hmm.finance.salary.repository.SalaryOrderRepository;
import com.hmm.userRole.entity.GroupRole;
import com.hmm.DTO.Department;
@Service
@Transactional
public class SalaryService implements ISalaryService{

@Autowired
 private  SalaryOrderRepository salaryOrderRepository;


@Override
public List<SalaryOrderDTO> findByMonth(SalaryOrderQueryDTO salaryOrderQueryDTO){
    List<SalaryOrder> salaryOrders = salaryOrderRepository.findAll(salaryOrderQueryDTO.getWhereClause(salaryOrderQueryDTO));
    List<SalaryOrderDTO> salaryOrderDTOs = null;
    if (salaryOrders != null) {
        salaryOrderDTOs = new ArrayList<>();
        for (SalaryOrder so : salaryOrders) {
            SalaryOrderDTO dto = new SalaryOrderDTO();
            SalaryOrderDTO.entityToDto(so, dto);
            dto.setEmpNo(so.getEmployee().getEmpNo());
            dto.setEmpName(so.getEmployee().getEmpName());
            Department a = so.getEmployee().getDepartmentes();
            if (a != null)
                dto.setDeptName(a.getDeptName());
            salaryOrderDTOs.add(dto);
        }
    }
    return salaryOrderDTOs;
}


}