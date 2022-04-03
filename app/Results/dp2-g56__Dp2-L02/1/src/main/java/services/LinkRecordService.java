package services;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import repositories.LinkRecordRepository;
import domain.Brotherhood;
import domain.History;
import domain.LinkRecord;
import forms.FormObjectLinkRecord;
@Service
@Transactional
public class LinkRecordService {

@Autowired
 private  LinkRecordRepository linkRecordRepository;

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  Validator validator;


public LinkRecord saveLinkRecord(LinkRecord linkRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    History history = loggedBrotherhood.getHistory();
    List<LinkRecord> linkRecords = loggedBrotherhood.getHistory().getLinkRecords();
    Assert.isTrue(linkRecords.contains(linkRecord) || linkRecord.getId() == 0);
    LinkRecord saved = this.save(linkRecord);
    if (!linkRecords.contains(linkRecord))
        linkRecords.add(saved);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    return saved;
}


public void flush(){
    this.linkRecordRepository.flush();
}


public LinkRecord save(LinkRecord linkRecord){
    return this.linkRecordRepository.save(linkRecord);
}


public LinkRecord findOne(int linkRecordId){
    return this.linkRecordRepository.findOne(linkRecordId);
}


public void deleteLinkRecord(LinkRecord linkRecord){
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    List<LinkRecord> linkRecords = loggedBrotherhood.getHistory().getLinkRecords();
    Assert.isTrue(linkRecords.contains(linkRecord));
    History history = loggedBrotherhood.getHistory();
    linkRecords.remove(linkRecord);
    history.setLinkRecords(linkRecords);
    loggedBrotherhood.setHistory(history);
    this.brotherhoodService.save(loggedBrotherhood);
    this.delete(linkRecord);
}


public LinkRecord create(){
    LinkRecord linkRecord = new LinkRecord();
    linkRecord.setTitle("");
    linkRecord.setDescription("");
    linkRecord.setLink("");
    return linkRecord;
}


public LinkRecord reconstruct(LinkRecord linkRecord,BindingResult binding){
    LinkRecord result;
    if (linkRecord.getId() == 0)
        result = linkRecord;
    else
        result = linkRecord;
    this.validator.validate(result, binding);
    return result;
}


public FormObjectLinkRecord createFormObjectLinkRecord(){
    FormObjectLinkRecord formObjectLinkRecord = new FormObjectLinkRecord();
    formObjectLinkRecord.setId(0);
    formObjectLinkRecord.setBrotherhood(null);
    formObjectLinkRecord.setDescription("");
    formObjectLinkRecord.setTitle("");
    return formObjectLinkRecord;
}


public List<LinkRecord> findAll(){
    return this.linkRecordRepository.findAll();
}


public void delete(LinkRecord linkRecord){
    this.linkRecordRepository.delete(linkRecord);
}


public FormObjectLinkRecord prepareFormObjectLinkRecord(int linkRecordId){
    LinkRecord linkRecord = this.findOne(linkRecordId);
    Assert.notNull(linkRecord);
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(loggedBrotherhood.getHistory());
    Assert.isTrue(loggedBrotherhood.getHistory().getLinkRecords().contains(linkRecord));
    FormObjectLinkRecord result = new FormObjectLinkRecord();
    result.setDescription(linkRecord.getDescription());
    result.setId(linkRecord.getId());
    result.setTitle(linkRecord.getTitle());
    return result;
}


public LinkRecord reconstructFormObject(FormObjectLinkRecord formObjectLinkRecord,BindingResult binding){
    LinkRecord result = new LinkRecord();
    this.brotherhoodService.loggedAsBrotherhood();
    Brotherhood loggedBrotherhood = this.brotherhoodService.loggedBrotherhood();
    Assert.notNull(formObjectLinkRecord.getBrotherhood());
    Assert.notNull(loggedBrotherhood);
    Assert.notNull(loggedBrotherhood.getHistory());
    if (!(formObjectLinkRecord.getId() == 0)) {
        result = this.linkRecordRepository.findOne(formObjectLinkRecord.getId());
        Assert.notNull(result);
    }
    Integer id = formObjectLinkRecord.getBrotherhood().getId();
    Assert.isTrue(id != loggedBrotherhood.getId());
    result.setDescription(formObjectLinkRecord.getDescription());
    result.setLink("showAll/annonymous/brotherhood/list.do?brotherhoodId=" + id.toString());
    result.setTitle(formObjectLinkRecord.getTitle());
    return result;
}


}