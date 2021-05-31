import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.common.web.ExtjsPageRequest;
import com.hmm.finance.salary.domain.SalaryOrderDTO;
import com.hmm.finance.salary.domain.SalaryOrderQueryDTO;
import com.hmm.finance.salary.service.ISalaryService;
@RestController
@RequestMapping(value = "/salaryOrder")
public class SalaryController {

@Autowired
 private  ISalaryService salaryService;


@GetMapping
public Page<SalaryOrderDTO> findByMonth(SalaryOrderQueryDTO salaryOrderQueryDTO,ExtjsPageRequest pageable){
    List<SalaryOrderDTO> list = salaryService.findByMonth(salaryOrderQueryDTO);
    return new PageImpl<SalaryOrderDTO>(list, pageable.getPageable(), list != null ? list.size() : 0);
}


}