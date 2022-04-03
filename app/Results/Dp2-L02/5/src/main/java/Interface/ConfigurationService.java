package Interface;
public interface ConfigurationService {

   public List<String> getSpamWords();
   public Boolean isStringSpam(String s,List<String> spamWords);
   public Double computeScore(Actor a);
   public Boolean isActorSuspicious(Actor a);
   public Configuration getConfiguration();
}