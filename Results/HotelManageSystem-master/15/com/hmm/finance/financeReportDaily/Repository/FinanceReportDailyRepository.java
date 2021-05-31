import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDaily;
@Repository
public interface FinanceReportDailyRepository extends PagingAndSortingRepository<FinanceReportDaily, Long> {


@Query("select month(date) as month,sum(t.salaryCost) as salaryCost,sum(t.logisticstCost) as logisticstCost,sum(t.roomIncome) as roomIncome,sum(t.profit) as profit " + "from FinanceReportDaily t " + "where year(date)=?1 " + "group by month(date)")
public List<Map<Object,Object>> findFinanceReportDailyByYearGroupByMonth(Integer year)


@Query("from FinanceReportDaily where year(date)=?1")
public List<FinanceReportDaily> findFinanceReportDailyByYear(Integer year)


@Query("from FinanceReportDaily")
public List<FinanceReportDaily> findAllFinanceReportDaily()


@Query("from FinanceReportDaily where year(date)=?1 and month(date)=?2")
public List<FinanceReportDaily> findFinanceReportDailyByYearAndMonth(Integer year,Integer month)


@Query("select year(date) as year from FinanceReportDaily group by year(date)")
public List<Map<String,Integer>> findAllYearInFinanceReport()


}