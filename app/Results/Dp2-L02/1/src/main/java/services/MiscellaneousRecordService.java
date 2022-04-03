package services;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.MiscellaneousRecordRepository;
import domain.Brotherhood;
import domain.History;
import domain.MiscellaneousRecord;
@Service
@Transactional
public class MiscellaneousRecordService {

@Autowired
 private  MiscellaneousRecordRepository miscellaneousRecordRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  Validator validator;


public void deleteMiscellaneousRecord(MiscellaneousRecord miscellaneousRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<MiscellaneousRecord> miscellaneousRecords = loggedBrotherhood.getHistory().getMiscellaneousRecords();
    History history = loggedBrotherhood.getHistory();
    Assert.isTrue(miscellaneousRecords.contains(miscellaneousRecord));
    miscellaneousRecords.remove(miscellaneousRecord);
    history.setMiscellaneousRecords(miscellaneousRecords);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    this.delete(miscellaneousRecord);
}


public void flush(){
    this.miscellaneousRecordRepository.flush();
}


public MiscellaneousRecord findOne(int miscellaneousRecordId){
    return this.miscellaneousRecordRepository.findOne(miscellaneousRecordId);
}


public MiscellaneousRecord create(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    MiscellaneousRecord miscellaneousRecord = new MiscellaneousRecord();
    miscellaneousRecord.setTitle("");
    miscellaneousRecord.setDescription("");
    return miscellaneousRecord;
}


public MiscellaneousRecord reconstruct(MiscellaneousRecord miscellaneousRecord,BindingResult binding){
    this.brotherhoodService.loggedAsBrotherhood();
    this.validator.validate(miscellaneousRecord, binding);
    return miscellaneousRecord;
}


public MiscellaneousRecord saveMiscellaneousRecord(MiscellaneousRecord miscellaneousRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    History history = loggedBrotherhood.getHistory();
    List<MiscellaneousRecord> miscellaneousRecords = loggedBrotherhood.getHistory().getMiscellaneousRecords();
    Assert.isTrue(miscellaneousRecords.contains(miscellaneousRecord) || (miscellaneousRecord.getId() == 0));
    MiscellaneousRecord saved = this.miscellaneousRecordRepository.save(miscellaneousRecord);
    if (!miscellaneousRecords.contains(miscellaneousRecord))
        miscellaneousRecords.add(saved);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public List<MiscellaneousRecord> findAll(){
    return this.miscellaneousRecordRepository.findAll();
}


public void delete(MiscellaneousRecord miscellaneousRecord){
    this.miscellaneousRecordRepository.delete(miscellaneousRecord);
}


}