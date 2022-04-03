package com.tech.configurations.tools.customvalidators.elements.stringvalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.configurations.tools.customvalidators.superclass.StringValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class NotMatchValidator extends StringValidatorimplements IStringValidator{

 private  Pattern P;

 private  Matcher m;

public NotMatchValidator(String p) {
    super(new ResponseEntity<>(Responses.STRING_INAPPROPRIATE_FORMAT.getData(), HttpStatus.NOT_ACCEPTABLE), "NotMatchValidator");
    this.P = Pattern.compile(p);
}
@Override
public Pair<Boolean,ResponseEntity> validate(String str){
    m = P.matcher(str);
    // String contains special characters
    if (!m.find()) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(str);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}