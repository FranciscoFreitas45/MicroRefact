package services;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.HistoryRepository;
import domain.Brotherhood;
import domain.History;
import domain.LegalRecord;
import domain.LinkRecord;
import domain.MiscellaneousRecord;
import domain.PeriodRecord;
@Service
@Transactional
public class HistoryService {

@Autowired
 private  HistoryRepository historyRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;


public void deleteHistory(History history){
    this.historyRepository.delete(history);
}


public History createAndSaveHistory(Brotherhood brotherhood){
    History history = new History();
    history.setInceptionRecord(null);
    history.setLegalRecords(new ArrayList<LegalRecord>());
    history.setLinkRecords(new ArrayList<LinkRecord>());
    history.setMiscellaneousRecords(new ArrayList<MiscellaneousRecord>());
    history.setPeriodRecords(new ArrayList<PeriodRecord>());
    History saved = this.save(history);
    brotherhood.setHistory(saved);
    return saved;
}


public History save(History history){
    return this.historyRepository.save(history);
}


public List<History> findAll(){
    return this.historyRepository.findAll();
}


public void deleteAllHistory(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood brotherhood = this.brotherhoodService.loggedBrotherhood();
    History history = brotherhood.getHistory();
    brotherhood.setHistory(null);
    this.deleteHistory(history);
    this.brotherhoodService.save(brotherhood);
}


}