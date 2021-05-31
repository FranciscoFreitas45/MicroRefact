import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDaily;
public interface IFinanceReportDailyService {


public void save(FinanceReportDaily financeReportDaily)


public List<FinanceReportDaily> exportExcelByYearAndMonth(Date date)


public Page<FinanceReportDaily> findAll(Specification<FinanceReportDaily> spec,Pageable pageable)


public void createDailyReport(Date date)


public List<FinanceReportDaily> exportExcelBySelectIds(Long[] ids)


public List<FinanceReportDaily> exportExcelByAll()


public List<FinanceReportDaily> exportExcelByYear(Integer year)


}