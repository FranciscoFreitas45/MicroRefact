package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
public class History extends DomainEntity{

 private  InceptionRecord inceptionRecord;

 private  List<PeriodRecord> periodRecords;

 private  List<LegalRecord> legalRecords;

 private  List<LinkRecord> linkRecords;

 private  List<MiscellaneousRecord> miscellaneousRecords;

public History() {
    super();
}
@OneToMany(cascade = CascadeType.ALL)
@Valid
public List<MiscellaneousRecord> getMiscellaneousRecords(){
    return this.miscellaneousRecords;
}


@OneToMany(cascade = CascadeType.ALL)
@Valid
public List<LinkRecord> getLinkRecords(){
    return this.linkRecords;
}


@OneToMany(cascade = CascadeType.ALL)
@Valid
public List<LegalRecord> getLegalRecords(){
    return this.legalRecords;
}


@OneToOne(optional = true, cascade = CascadeType.ALL)
@Valid
public InceptionRecord getInceptionRecord(){
    return this.inceptionRecord;
}


@OneToMany(cascade = CascadeType.ALL)
@Valid
public List<PeriodRecord> getPeriodRecords(){
    return this.periodRecords;
}


}