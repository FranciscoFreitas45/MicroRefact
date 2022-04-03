package services;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Finder;
import domain.Member;
import domain.Parade;
import repositories.FinderRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
@Service
@Transactional
public class FinderService {

@Autowired
 private  FinderRepository finderRepository;

@Autowired
 private  MemberService memberRepository;

@Autowired
 private  Validator validator;

@Autowired
 private  ConfigurationService configurationService;


public List<Parade> getAllPublishedParades(){
    return this.finderRepository.getPublushedParades();
}


public void filterParadesByFinder(Finder finder){
    UserAccount userAccount = LoginService.getPrincipal();
    List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("MEMBER"));
    Member loggedMember = this.memberRepository.getMemberByUsername(userAccount.getUsername());
    Assert.isTrue(loggedMember.getFinder().getId() == finder.getId());
    List<Parade> filter = new ArrayList<>();
    List<Parade> result = this.getAllPublishedParades();
    // KeyWord
    if (!finder.getKeyWord().equals(null) && !finder.getKeyWord().equals("")) {
        filter = this.finderRepository.getParadesByKeyWord("%" + finder.getKeyWord() + "%");
        result.retainAll(filter);
    }
    // Area
    if (!finder.getArea().equals(null) && !finder.getArea().equals("")) {
        filter = this.finderRepository.getParadesByArea("%" + finder.getArea() + "%");
        result.retainAll(filter);
    }
    // Dates
    if (finder.getMinDate() != null) {
        filter = this.finderRepository.getParadesByMinDate(finder.getMinDate());
        result.retainAll(filter);
    }
    if (finder.getMaxDate() != null) {
        filter = this.finderRepository.getParadesByMaxDate(finder.getMaxDate());
        result.retainAll(filter);
    }
    finder.setParades(result);
    Finder finderRes = this.finderRepository.save(finder);
    loggedMember.setFinder(finderRes);
    this.memberRepository.save(loggedMember);
}


public Finder getCurrentFinder(){
    UserAccount userAccount = LoginService.getPrincipal();
    Member loggedMember = this.memberRepository.getMemberByUsername(userAccount.getUsername());
    return loggedMember.getFinder();
}


public Finder findOne(int id){
    return this.finderRepository.findOne(id);
}


public Finder save(Finder finder){
    return this.finderRepository.save(finder);
}


public Finder reconstruct(Finder finderForm,BindingResult binding){
    Finder result = new Finder();
    Finder finder = this.findOne(finderForm.getId());
    result.setId(finder.getId());
    result.setVersion(finder.getVersion());
    result.setParades(finder.getParades());
    Date date = new Date();
    result.setLastEdit(date);
    result.setArea(finderForm.getArea());
    result.setKeyWord(finderForm.getKeyWord());
    result.setMaxDate(finderForm.getMaxDate());
    result.setMinDate(finderForm.getMinDate());
    this.validator.validate(result, binding);
    return result;
}


public void updateAllFinders(){
    // LastEdit Finder
    List<Finder> finders = this.findAll();
    // Current Date
    Date currentDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currentDate);
    Integer currentDay = calendar.get(Calendar.DATE);
    Integer currentMonth = calendar.get(Calendar.MONTH);
    Integer currentYear = calendar.get(Calendar.YEAR);
    Integer currentHour = calendar.get(Calendar.HOUR);
    // Max Time Finder
    Integer time = this.configurationService.getConfiguration().getTimeFinder();
    // Empty List parades
    List<Parade> parades = new ArrayList<>();
    for (Finder f : finders) {
        // Last Edit Date
        Date lasEdit = f.getLastEdit();
        calendar.setTime(lasEdit);
        Integer lastEditDay = calendar.get(Calendar.DATE);
        Integer lastEditMonth = calendar.get(Calendar.MONTH);
        Integer lastEditYear = calendar.get(Calendar.YEAR);
        Integer lastEditHour = calendar.get(Calendar.HOUR);
        if (!(currentDay.equals(lastEditDay) && currentMonth.equals(lastEditMonth) && currentYear.equals(lastEditYear) && lastEditHour < (currentHour + time))) {
            f.setParades(parades);
            this.save(f);
        }
    }
}


public Finder createFinder(){
    Finder finder = new Finder();
    List<Parade> parades = new ArrayList<>();
    Date lastEdit = new Date();
    lastEdit.setTime(lastEdit.getTime() - 1);
    finder.setKeyWord("");
    finder.setArea("");
    finder.setMaxDate(null);
    finder.setMinDate(null);
    finder.setLastEdit(lastEdit);
    finder.setParades(parades);
    return finder;
}


public List<Finder> findAll(){
    return this.finderRepository.findAll();
}


public void delete(Finder finder){
    this.finderRepository.delete(finder);
}


}