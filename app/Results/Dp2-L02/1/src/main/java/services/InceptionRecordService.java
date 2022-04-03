package services;
 import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.InceptionRecordRepository;
import domain.Brotherhood;
import domain.History;
import domain.InceptionRecord;
@Service
@Transactional
public class InceptionRecordService {

@Autowired
 private  InceptionRecordRepository inceptionRecordRepository;

@Autowired
 private  Validator validator;

@Autowired
 private  HistoryService historyService;

@Autowired
 private  BrotherhoodService brotherhoodService;


public History addPicture(String picture){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    History history = loggedBrotherhood.getHistory();
    history.getInceptionRecord().getPhotos().add(picture);
    History saved = this.historyService.save(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public void flush(){
    this.inceptionRecordRepository.flush();
}


public InceptionRecord prepareEditInceptionRecord(){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    InceptionRecord inceptionRecord = loggedBrotherhood.getHistory().getInceptionRecord();
    Assert.notNull(inceptionRecord);
    return inceptionRecord;
}


public void removePhoto(String picture){
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    History history = loggedBrotherhood.getHistory();
    InceptionRecord inceptionRecord = history.getInceptionRecord();
    List<String> photos = inceptionRecord.getPhotos();
    photos.remove(picture);
    inceptionRecord.setPhotos(photos);
    InceptionRecord saved = this.save(inceptionRecord);
    history.setInceptionRecord(saved);
    History savedHistory = this.historyService.save(history);
    loggedBrotherhood.setHistory(savedHistory);
    this.brotherhoodService.save(loggedBrotherhood);
}


public InceptionRecord save(InceptionRecord inceptionRecord){
    return this.inceptionRecordRepository.save(inceptionRecord);
}


public InceptionRecord findOne(int inceptionRecordId){
    return this.inceptionRecordRepository.findOne(inceptionRecordId);
}


public InceptionRecord create(){
    InceptionRecord inceptionRecord = new InceptionRecord();
    List<String> photos = new ArrayList<>();
    inceptionRecord.setDescription("");
    inceptionRecord.setPhotos(photos);
    inceptionRecord.setTitle("");
    return inceptionRecord;
}


public InceptionRecord reconstruct(InceptionRecord inceptionRecord,BindingResult binding){
    InceptionRecord result;
    if (inceptionRecord.getId() == 0)
        result = inceptionRecord;
    else {
        result = inceptionRecord;
        List<String> photos = this.inceptionRecordRepository.findOne(inceptionRecord.getId()).getPhotos();
        result.setPhotos(photos);
    }
    this.validator.validate(result, binding);
    return result;
}


public InceptionRecord saveInceptionRecord(InceptionRecord inceptionRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    History history = new History();
    InceptionRecord saved = this.save(inceptionRecord);
    if (inceptionRecord.getId() == 0)
        history = this.historyService.createAndSaveHistory(loggedBrotherhood);
    else
        history = loggedBrotherhood.getHistory();
    history.setInceptionRecord(saved);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public List<InceptionRecord> findAll(){
    return this.inceptionRecordRepository.findAll();
}


public void delete(InceptionRecord inceptionRecord){
    this.inceptionRecordRepository.delete(inceptionRecord);
}


}