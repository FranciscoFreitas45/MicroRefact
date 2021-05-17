import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hmm.finance.financeReport.domain.FinanceReport;
import com.hmm.finance.financeReport.service.IFinanceReportService;
@RestController
@RequestMapping(value = "/financeReport")
public class FinanceReportController {

@Autowired
 private  IFinanceReportService financeReportService;


@GetMapping
public List<FinanceReport> findFinanceReportDailyByYearGroupByMonth(Integer year){
    if (year == null) {
        Calendar date = Calendar.getInstance();
        year = date.get(Calendar.YEAR);
    }
    List<FinanceReport> list = financeReportService.findFinanceReportDailyByYearGroupByMonth(year);
    return list;
}


@GetMapping("/findAllYear")
public List<Map<String,Integer>> findAllYearInFinanceReport(){
    List<Map<String, Integer>> list = financeReportService.findAllYearInFinanceReport();
    return list;
}


}