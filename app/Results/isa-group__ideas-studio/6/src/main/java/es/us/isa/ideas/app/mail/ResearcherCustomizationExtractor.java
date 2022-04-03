package es.us.isa.ideas.app.mail;
 import java.util.HashMap;
import java.util.Map;
import es.us.isa.ideas.app.entities.Researcher;
public class ResearcherCustomizationExtractor implements SpecificCustomizationExtractor<Researcher>{

 public  String NAME_KEY;

 public  String EMAIL_KEY;

 public  String PHONE_KEY;

 public  String ADRESS_KEY;


@Override
public boolean canExtractCustomizations(Object o){
    return o instanceof Researcher;
}


@Override
public Map<String,String> extractCustomizations(Researcher element){
    Map<String, String> result = new HashMap<String, String>();
    result.put(NAME_KEY, element.getName());
    result.put(ADRESS_KEY, element.getAddress());
    result.put(PHONE_KEY, element.getPhone());
    result.put(EMAIL_KEY, element.getEmail());
    return result;
}


}