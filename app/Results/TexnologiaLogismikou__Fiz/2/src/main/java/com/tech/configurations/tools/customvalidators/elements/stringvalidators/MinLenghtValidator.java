package com.tech.configurations.tools.customvalidators.elements.stringvalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.configurations.tools.customvalidators.superclass.StringValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class MinLenghtValidator extends StringValidatorimplements IStringValidator{

 private  int LENGTH;

public MinLenghtValidator(int length) {
    super(new ResponseEntity<>(Responses.STRING_INAPPROPRIATE_FORMAT.getData(), HttpStatus.NOT_ACCEPTABLE), "MinLenghtValidator");
    this.LENGTH = length;
}
@Override
public Pair<Boolean,ResponseEntity> validate(String str){
    if (str.length() < LENGTH) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(str);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}