package services;
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
import Interface.AdminService;
import DTO.AdminService;
import DTO.Message;
import DTO.ActorService;
@Service
@Transactional
public class ConfigurationService {

@Autowired
 private  ConfigurationRepository configurationRepository;

@Autowired
 private  ActorService actorService;

@Autowired
 private  AdminService adminService;

@Autowired
 private  Validator validator;


public String addGoodWords(String word){
    this.adminService.loggedAsAdmin();
    final Configuration configuration = this.configurationRepository.configuration();
    String goodWords = configuration.getGoodWords();
    configuration.setGoodWords(goodWords = goodWords + "," + word);
    this.configurationRepository.save(configuration);
    return configuration.getGoodWords();
}


public List<String> getSpamWords(){
    return this.configurationRepository.spamWords();
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
}


public String addBadWords(String word){
    this.adminService.loggedAsAdmin();
    final Configuration configuration = this.configurationRepository.configuration();
    String badWords = configuration.getBadWords();
    configuration.setBadWords(badWords = badWords + "," + word);
    this.configurationRepository.save(configuration);
    return configuration.getBadWords();
}


public List<String> showGoodWordsList(){
    this.adminService.loggedAsAdmin();
    final String goodWordString = this.configurationRepository.goodWords();
    final List<String> goodWordsList = Arrays.asList(goodWordString.split(",[ ]*"));
    return goodWordsList;
}


public Configuration save(Configuration configuration){
    return this.configurationRepository.save(configuration);
}


public String showGoodWords(){
    return this.configurationRepository.goodWords();
}


public Map<Actor,Double> computeAllScores(List<Actor> actors){
    Map<Actor, Double> result = new HashMap<Actor, Double>();
    for (Actor a : actors) result.put(a, this.computeScore(a));
    return result;
}


public Configuration reconstruct(Configuration configuration,BindingResult binding){
    this.loggedAsAdmin();
    Configuration result = new Configuration();
    Configuration configurationBefore = this.configurationRepository.findOne(configuration.getId());
    result.setId(configurationBefore.getId());
    result.setVersion(configurationBefore.getVersion());
    result.setBadWords(configurationBefore.getBadWords());
    result.setGoodWords(configurationBefore.getGoodWords());
    result.setFinderResult(configuration.getFinderResult());
    result.setMinFinderResults(configuration.getMinFinderResults());
    result.setmaxFinderResults(configuration.getmaxFinderResults());
    result.setTimeFinder(configuration.getTimeFinder());
    result.setSpainTelephoneCode(configuration.getSpainTelephoneCode());
    result.setWelcomeMessageEnglish(configuration.getWelcomeMessageEnglish());
    result.setWelcomeMessageSpanish(configuration.getWelcomeMessageSpanish());
    result.setSystemName(configuration.getSystemName());
    result.setImageURL(configuration.getImageURL());
    result.setMaxTimeFinder(configuration.getMaxTimeFinder());
    result.setMinTimeFinder(configuration.getMinTimeFinder());
    result.setVAT(configuration.getVAT());
    result.setFare(configuration.getFare());
    result.setCardType(configuration.getCardType());
    this.validator.validate(result, binding);
    return result;
}


public String editWord(String word,String originalWord){
    this.adminService.loggedAsAdmin();
    String result = "";
    final String goodWords = this.showGoodWords();
    final String badWords = this.showBadWords();
    final Configuration configuration = this.configurationRepository.configuration();
    final List<String> goodWordsList = Arrays.asList(goodWords.split(",[ ]*"));
    final List<String> badWordsList = Arrays.asList(badWords.split(",[ ]*"));
    Integer cont = 0;
    if (goodWordsList.contains(originalWord)) {
        for (final String s : goodWordsList) {
            if (s.equals(originalWord))
                goodWordsList.set(cont, word);
            cont++;
        }
        for (int i = 0; i < goodWordsList.size(); i++) if (i < goodWordsList.size() - 1)
            result = result + goodWordsList.get(i) + ",";
        else
            result = result + goodWordsList.get(i);
        configuration.setGoodWords(result);
    } else {
        for (final String s : badWordsList) {
            if (s.equals(originalWord))
                badWordsList.set(cont, word);
            cont++;
        }
        for (int i = 0; i < badWordsList.size(); i++) if (i < badWordsList.size() - 1)
            result = result + badWordsList.get(i) + ",";
        else
            result = result + badWordsList.get(i);
        configuration.setBadWords(result);
    }
    this.configurationRepository.save(configuration);
    return configuration.getGoodWords();
}


public List<Double> computeAllScoresDouble(List<Actor> actors){
    List<Double> result = new ArrayList<Double>();
    for (Actor a : actors) {
        Double res = 0.0;
        res = this.computeScore(a);
        result.add(res);
    }
    return result;
}


public String showBadWords(){
    return this.configurationRepository.badWords();
}


public void deleteGoodWord(String word){
    this.adminService.loggedAsAdmin();
    final String goodWords = this.showGoodWords();
    final Configuration configuration = this.configurationRepository.configuration();
    final List<String> goodWordsList = new ArrayList<String>();
    goodWordsList.addAll(Arrays.asList(goodWords.split(",[ ]*")));
    if (goodWordsList.contains(word))
        goodWordsList.remove(word);
    String result = "";
    for (int i = 0; i < goodWordsList.size(); i++) if (i < goodWordsList.size() - 1)
        result = result + goodWordsList.get(i) + ",";
    else
        result = result + goodWordsList.get(i);
    configuration.setGoodWords(result);
    this.configurationRepository.save(configuration);
}


public Configuration getConfiguration(){
    return this.configurationRepository.findAll().get(0);
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
}


public void loggedAsAdmin(){
    UserAccount userAccount;
    userAccount = LoginService.getPrincipal();
    List<Authority> authorities = (List<Authority>) userAccount.getAuthorities();
    Assert.isTrue(authorities.get(0).toString().equals("ADMIN"));
}


public List<String> showBadWordsList(){
    this.adminService.loggedAsAdmin();
    final String badWordString = this.configurationRepository.badWords();
    final List<String> badWordsList = Arrays.asList(badWordString.split(",[ ]*"));
    return badWordsList;
}


public void deleteBadWord(String word){
    this.adminService.loggedAsAdmin();
    final String badWords = this.showBadWords();
    final Configuration configuration = this.configurationRepository.configuration();
    final List<String> badWordsList = new ArrayList<String>();
    badWordsList.addAll(Arrays.asList(badWords.split(",[ ]*")));
    if (badWordsList.contains(word))
        badWordsList.remove(word);
    String result = "";
    for (int i = 0; i < badWordsList.size(); i++) if (i < badWordsList.size() - 1)
        result = result + badWordsList.get(i) + ",";
    else
        result = result + badWordsList.get(i);
    configuration.setBadWords(result);
    this.configurationRepository.save(configuration);
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
}


}