package es.us.isa.ideas.app.mail;
 import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
public class CustomizationsExtractor {

@SuppressWarnings("rawtypes")
 private Collection<SpecificCustomizationExtractor> extractors;

@SuppressWarnings("rawtypes")
public CustomizationsExtractor(Collection<SpecificCustomizationExtractor> extractors) {
    this.extractors = extractors;
}
public Map<String,String> extract(Collection<Object> objects){
    Map<String, String> result = new HashMap<String, String>();
    for (Object object : objects) result.putAll(extract(object));
    return result;
}


}