import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hmm.finance.financeReportDaily.Repository.FinanceReportDailyRepository;
import com.hmm.finance.financeReportDaily.domain.FinanceReportDaily;
import com.hmm.finance.logisticst.repository.InStorageRepository;
import com.hmm.finance.roomOrder.repository.RoomOrderRepository;
import com.hmm.finance.salary.repository.SalaryOrderRepository;
@Service
@Transactional
public class FinanceReportDailyService implements com.hmm.finance.financeReportDaily.service.IFinanceReportDailyService,IFinanceReportDailyService{

@Autowired
 private  FinanceReportDailyRepository financeReportDailyRepository;

@Autowired
 private  SalaryOrderRepository salaryOrderRepository;

@Autowired
 private  RoomOrderRepository roomOrderRepository;

@Autowired
 private  InStorageRepository inStorageRepository;


@Override
public void save(FinanceReportDaily financeReportDaily){
    financeReportDailyRepository.save(financeReportDaily);
}


@Override
public void createDailyReport(Date date){
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String dateString = formatter.format(date);
    Float salaryCost = salaryOrderRepository.findSalaryByDay(dateString);
    Float roomIncome = roomOrderRepository.findRoomOrderByDay(dateString);
    Float inStorageCost = inStorageRepository.findInStorageOrderByDay(dateString);
    FinanceReportDaily financeReportDaily = new FinanceReportDaily(roomIncome, salaryCost, inStorageCost);
    financeReportDaily.setDate(new Date());
    financeReportDaily.setTotalIncome(roomIncome);
    financeReportDaily.setTotalCost(salaryCost + inStorageCost);
    financeReportDaily.setProfit(financeReportDaily.getRoomIncome() - financeReportDaily.getLogisticstCost() - financeReportDaily.getSalaryCost());
    financeReportDailyRepository.save(financeReportDaily);
}


@Override
public Page<FinanceReportDaily> findAll(Specification<FinanceReportDaily> spec,Pageable pageable){
    return financeReportDailyRepository.findAll(spec, pageable);
}


@Override
public List<FinanceReportDaily> exportExcelByYearAndMonth(Date date){
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    return financeReportDailyRepository.findFinanceReportDailyByYearAndMonth(year, month);
}


@Override
public List<FinanceReportDaily> exportExcelByAll(){
    return financeReportDailyRepository.findAllFinanceReportDaily();
}


@Override
public List<FinanceReportDaily> exportExcelBySelectIds(Long[] ids){
    List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
    return (List<FinanceReportDaily>) financeReportDailyRepository.findAllById(idLists);
}


@Override
public List<FinanceReportDaily> exportExcelByYear(Integer year){
    return financeReportDailyRepository.findFinanceReportDailyByYear(year);
}


}