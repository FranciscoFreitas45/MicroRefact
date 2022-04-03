package DTO;
 import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Actor;
import domain.Box;
import domain.Configuration;
import domain.Message;
import repositories.ConfigurationRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.ActorService;
import Interface.AdminService;
import DTO.AdminService;
import DTO.Actor;
import DTO.Box;
import DTO.Message;
import DTO.ActorService;
import DTO.UserAccount;
import DTO.Authority;
public class ConfigurationService {

 private  ConfigurationRepository configurationRepository;

 private  ActorService actorService;

 private  AdminService adminService;

 private  Validator validator;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public List<String> getSpamWords(){
    return this.configurationRepository.spamWords();
}


public Configuration getConfiguration(){
    return this.configurationRepository.findAll().get(0);
}


public Boolean isStringSpam(String s,List<String> spamWords){
    Boolean result = false;
    List<String> trimmedString = new ArrayList<String>();
    trimmedString = Arrays.asList(s.split("\\+|(?=[,.�?;!�])"));
    // ("\\s*(=>|,|\\s)\\s*"));
    for (final String g : spamWords) for (final String c : trimmedString) if (g.equals(c) || g.equalsIgnoreCase(c)) {
        result = true;
        break;
    }
    return result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isStringSpam"))

.queryParam("s",s)
.queryParam("spamWords",spamWords)
;
Boolean aux = restTemplate.getForObject(builder.toUriString(),Boolean.class);
return aux;
}


public Double computeScore(Actor a){
    String goodWords = this.configurationRepository.goodWords();
    String badWords = this.configurationRepository.badWords();
    Double countGood = 0.0;
    Double countBad = 0.0;
    Double total = 0.0;
    List<Double> parcialresult = new ArrayList<Double>();
    List<String> goodWordsList = Arrays.asList(goodWords.split(",[ ]*"));
    List<String> badWordsList = Arrays.asList(badWords.split(",[ ]*"));
    List<Box> boxes = a.getBoxes();
    for (Box b : boxes) if (!b.getMessages().isEmpty())
        for (Message m : b.getMessages()) if (m.getSender().equals(a)) {
            List<String> messageSplit = Arrays.asList(m.getBody().split("\\W+"));
            for (String word : messageSplit) {
                if (goodWordsList.contains(word))
                    countGood = countGood + 1.0;
                if (badWordsList.contains(word))
                    countBad = countBad - 1.0;
                total = countGood - countBad;
            }
        }
    if (total == 0.)
        total = 1.;
    parcialresult.add((countGood / total) + (countBad / total));
    Double res = 0.0;
    Double cont = 0.0;
    for (Double d : parcialresult) cont = cont + d;
    if (parcialresult.size() == 0)
        res = 0.;
    else
        res = cont / parcialresult.size();
    DecimalFormat df2 = new DecimalFormat(".##");
    a.setPolarity(Double.valueOf(df2.format(res)));
    this.actorService.save(a);
    return res;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/computeScore"))

.queryParam("a",a)
;
Double aux = restTemplate.getForObject(builder.toUriString(),Double.class);
return aux;
}


public Boolean isActorSuspicious(Actor a){
    boolean result = false;
    List<String> spamWords = new ArrayList<String>();
    spamWords = this.getSpamWords();
    Integer spamCount = 0;
    Integer messagesCount = 0;
    Double spamPorcent = 0.;
    // COMPROBANDO LAS CAJAS DEL ACTOR
    for (Box b : a.getBoxes()) {
        messagesCount += b.getMessages().size();
        for (Message g : b.getMessages()) if (g.getSender().equals(a) && (this.isStringSpam(g.getBody(), spamWords) || this.isStringSpam(g.getSubject(), spamWords)))
            spamCount++;
    }
    spamPorcent = spamCount / messagesCount * 100.;
    if (spamPorcent >= 10)
        result = true;
    return result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isActorSuspicious"))

.queryParam("a",a)
;
Boolean aux = restTemplate.getForObject(builder.toUriString(),Boolean.class);
return aux;
}


}