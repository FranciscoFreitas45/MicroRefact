import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hmm.finance.financeReport.domain.FinanceReport;
import com.hmm.finance.financeReportDaily.Repository.FinanceReportDailyRepository;
@Service
public class FinanceReportService implements com.hmm.finance.financeReport.service.IFinanceReportService,IFinanceReportService{

@Autowired
 private  FinanceReportDailyRepository financeReportDailyRepository;


@Override
public int compare(FinanceReport stu1,FinanceReport stu2){
    return stu1.getMonth().compareTo(stu2.getMonth());
}


@Override
public List<FinanceReport> findFinanceReportDailyByYearGroupByMonth(Integer year){
    List<Map<Object, Object>> listMap = financeReportDailyRepository.findFinanceReportDailyByYearGroupByMonth(year);
    List<FinanceReport> list = new ArrayList<>();
    List<Integer> existMonth = new ArrayList<>();
    try {
        for (Map map : listMap) {
            FinanceReport financeReport = new FinanceReport();
            BeanUtils.populate(financeReport, map);
            existMonth.add(financeReport.getMonth());
            list.add(financeReport);
        }
        for (int i = 1; i <= 12; i++) {
            if (!existMonth.contains(i)) {
                FinanceReport financeReport = new FinanceReport(i, 0, 0, 0, 0);
                list.add(financeReport);
            }
        }
        list.sort(new Comparator<FinanceReport>() {

            @Override
            public int compare(FinanceReport stu1, FinanceReport stu2) {
                return stu1.getMonth().compareTo(stu2.getMonth());
            }
        });
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


@Override
public List<Map<String,Integer>> findAllYearInFinanceReport(){
    return financeReportDailyRepository.findAllYearInFinanceReport();
}


}