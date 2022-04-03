package services;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.PeriodRecordRepository;
import domain.Brotherhood;
import domain.History;
import domain.PeriodRecord;
@Service
@Transactional
public class PeriodRecordService {

@Autowired
 private  PeriodRecordRepository periodRecordRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  Validator validator;

@Autowired
 private  HistoryService historyService;


public void deletePeriodRecord(PeriodRecord periodRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<PeriodRecord> periodRecords = loggedBrotherhood.getHistory().getPeriodRecords();
    Assert.isTrue(periodRecords.contains(periodRecord));
    History history = loggedBrotherhood.getHistory();
    periodRecords.remove(periodRecord);
    history.setPeriodRecords(periodRecords);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    this.delete(periodRecord);
}


public History addPicture(PeriodRecord periodRecord,String picture){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    Assert.isTrue(loggedBrotherhood.getHistory().getPeriodRecords().contains(periodRecord));
    History history = loggedBrotherhood.getHistory();
    periodRecord.getPhotos().add(picture);
    History saved = this.historyService.save(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public void flush(){
    this.periodRecordRepository.flush();
}


public void removePhoto(PeriodRecord periodRecord,String picture){
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    History history = loggedBrotherhood.getHistory();
    Assert.notNull(loggedBrotherhood.getHistory());
    Assert.isTrue(loggedBrotherhood.getHistory().getPeriodRecords().contains(periodRecord));
    List<String> photos = periodRecord.getPhotos();
    photos.remove(picture);
    periodRecord.setPhotos(photos);
    PeriodRecord saved = this.save(periodRecord);
    History savedHistory = this.historyService.save(history);
    loggedBrotherhood.setHistory(savedHistory);
    this.brotherhoodService.save(loggedBrotherhood);
}


public PeriodRecord save(PeriodRecord periodRecord){
    return this.periodRecordRepository.save(periodRecord);
}


public PeriodRecord findOne(int periodRecordId){
    return this.periodRecordRepository.findOne(periodRecordId);
}


public PeriodRecord savePeriodRecord(PeriodRecord periodRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    if (periodRecord.getStartYear() != null && periodRecord.getEndYear() != null)
        Assert.isTrue(periodRecord.getStartYear() <= periodRecord.getEndYear());
    History history = loggedBrotherhood.getHistory();
    List<PeriodRecord> periodRecords = history.getPeriodRecords();
    Assert.isTrue(periodRecords.contains(periodRecord) || (periodRecord.getId() == 0));
    PeriodRecord saved = this.save(periodRecord);
    if (!periodRecords.contains(saved))
        periodRecords.add(saved);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public PeriodRecord create(){
    PeriodRecord periodRecord = new PeriodRecord();
    List<String> photos = new ArrayList<>();
    periodRecord.setDescription("");
    periodRecord.setEndYear(0);
    periodRecord.setPhotos(photos);
    periodRecord.setStartYear(0);
    periodRecord.setTitle("");
    return periodRecord;
}


public PeriodRecord reconstruct(PeriodRecord periodRecord,BindingResult binding){
    PeriodRecord result;
    if (periodRecord.getId() == 0)
        result = periodRecord;
    else {
        result = periodRecord;
        List<String> photos = this.periodRecordRepository.findOne(periodRecord.getId()).getPhotos();
        result.setPhotos(photos);
    }
    this.validator.validate(result, binding);
    return result;
}


public List<PeriodRecord> findAll(){
    return this.periodRecordRepository.findAll();
}


public void delete(PeriodRecord periodRecord){
    this.periodRecordRepository.delete(periodRecord);
}


}