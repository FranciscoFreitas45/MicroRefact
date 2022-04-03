package gov.cdc.nccdphp.formatters;
 import gov.cdc.nccdphp.domain.Division;
import gov.cdc.nccdphp.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.util.Locale;
@Component
public class DivisionFormatter implements Formatter<Division>{

@Autowired
 private  DivisionRepository divisionRepository;


@Override
public String print(Division object,Locale locale){
    return String.valueOf(object.getId());
}


@Override
public Division parse(String text,Locale locale){
    // When adding a "Seleect Division" Option on the drop down with a value of -1, the findOne was retrieving
    // Null value, which threw an error saying this method cannot return Null.
    // Therefore, a dummy Division was created!
    Division division = divisionRepository.findOne(Integer.valueOf(text));
    return division == null ? new Division() : division;
}


}