package es.us.isa.ideas.app.mail;
 import java.util.Map;
public interface SpecificCustomizationExtractor {


public boolean canExtractCustomizations(Object o)
;

public Map<String,String> extractCustomizations(X element)
;

}