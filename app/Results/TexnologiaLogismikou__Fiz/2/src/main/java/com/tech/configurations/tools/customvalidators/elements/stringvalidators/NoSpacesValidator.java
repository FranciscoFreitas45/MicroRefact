package com.tech.configurations.tools.customvalidators.elements.stringvalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.configurations.tools.customvalidators.superclass.StringValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class NoSpacesValidator extends StringValidatorimplements IStringValidator{

public NoSpacesValidator() {
    super(new ResponseEntity<>(Responses.STRING_INAPPROPRIATE_FORMAT.getData(), HttpStatus.NOT_ACCEPTABLE), "NoSpacesValidator");
}
@Override
public Pair<Boolean,ResponseEntity> validate(String str){
    if (str.replaceAll("\\s+", "").length() != str.length()) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(str);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}