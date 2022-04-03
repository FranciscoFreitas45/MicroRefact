package DTO;
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
import Interface.ConfigurationService;
import DTO.UserAccount;
import DTO.Authority;
import DTO.ConfigurationService;
public class FinderService {

 private  FinderRepository finderRepository;

 private  MemberService memberRepository;

 private  Validator validator;

 private  ConfigurationService configurationService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public List<Parade> getAllPublishedParades(){
    return this.finderRepository.getPublushedParades();
}


public Finder getCurrentFinder(){
    UserAccount userAccount = LoginService.getPrincipal();
    Member loggedMember = this.memberRepository.getMemberByUsername(userAccount.getUsername());
    return loggedMember.getFinder();
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
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAllFinders"))

;
restTemplate.put(builder.toUriString(),null);
}


}