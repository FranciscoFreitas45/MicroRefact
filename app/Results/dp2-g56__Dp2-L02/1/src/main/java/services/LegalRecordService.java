package services;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.LegalRecordRepository;
import domain.Brotherhood;
import domain.History;
import domain.LegalRecord;
@Service
@Transactional
public class LegalRecordService {

@Autowired
 private  LegalRecordRepository legalRecordRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  Validator validator;

@Autowired
 private  HistoryService historyService;


public void removeLaw(LegalRecord legalRecord,String law){
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    History history = loggedBrotherhood.getHistory();
    Assert.notNull(loggedBrotherhood.getHistory());
    Assert.isTrue(loggedBrotherhood.getHistory().getLegalRecords().contains(legalRecord));
    List<String> laws = legalRecord.getLaws();
    laws.remove(law);
    legalRecord.setLaws(laws);
    LegalRecord saved = this.save(legalRecord);
    List<LegalRecord> legalRecords = loggedBrotherhood.getHistory().getLegalRecords();
    legalRecords.remove(legalRecord);
    legalRecords.add(saved);
    history.setLegalRecords(legalRecords);
    History savedHistory = this.historyService.save(history);
    loggedBrotherhood.setHistory(savedHistory);
    this.brotherhoodService.save(loggedBrotherhood);
}


public History addLaw(LegalRecord legalRecord,String law){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    Assert.isTrue(loggedBrotherhood.getHistory().getLegalRecords().contains(legalRecord));
    History history = loggedBrotherhood.getHistory();
    legalRecord.getLaws().add(law);
    History saved = this.historyService.save(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public void flush(){
    this.legalRecordRepository.flush();
}


public LegalRecord saveLegalRecord(LegalRecord legalRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    History history = loggedBrotherhood.getHistory();
    List<LegalRecord> legalRecords = history.getLegalRecords();
    Assert.isTrue(legalRecords.contains(legalRecord) || (legalRecord.getId() == 0));
    LegalRecord saved = this.save(legalRecord);
    if (!legalRecords.contains(saved))
        legalRecords.add(saved);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public LegalRecord save(LegalRecord legalRecord){
    return this.legalRecordRepository.save(legalRecord);
}


public LegalRecord findOne(int legalRecordId){
    return this.legalRecordRepository.findOne(legalRecordId);
}


public LegalRecord create(){
    this.brotherhoodService.loggedAsBrotherhood();
    LegalRecord legalRecord = new LegalRecord();
    List<String> laws = new ArrayList<>();
    legalRecord.setDescription("");
    legalRecord.setLaws(laws);
    legalRecord.setLegalName("");
    legalRecord.setTitle("");
    legalRecord.setVatNumber("");
    return legalRecord;
}


public LegalRecord reconstruct(LegalRecord legalRecord,BindingResult binding){
    LegalRecord result;
    if (legalRecord.getId() == 0)
        result = legalRecord;
    else {
        result = legalRecord;
        List<String> laws = this.legalRecordRepository.findOne(legalRecord.getId()).getLaws();
        result.setLaws(laws);
    }
    this.validator.validate(result, binding);
    return result;
}


public void deleteLegalRecord(LegalRecord legalRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    List<LegalRecord> legalRecords = loggedBrotherhood.getHistory().getLegalRecords();
    Assert.isTrue(legalRecords.contains(legalRecord));
    History history = loggedBrotherhood.getHistory();
    legalRecords.remove(legalRecord);
    history.setLegalRecords(legalRecords);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    this.delete(legalRecord);
}


public List<LegalRecord> findAll(){
    return this.legalRecordRepository.findAll();
}


public void delete(LegalRecord legalRecord){
    this.legalRecordRepository.delete(legalRecord);
}


}