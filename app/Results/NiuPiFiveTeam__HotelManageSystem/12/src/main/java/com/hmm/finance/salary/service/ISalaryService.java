package com.hmm.finance.salary.service;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeQueryDTO;
import com.hmm.finance.salary.domain.SalaryOrder;
import com.hmm.finance.salary.domain.SalaryOrderDTO;
import com.hmm.finance.salary.domain.SalaryOrderQueryDTO;
public interface ISalaryService {


public List<SalaryOrderDTO> findByMonth(SalaryOrderQueryDTO salaryOrderQueryDTO)
;

}