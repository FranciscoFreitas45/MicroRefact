package com.tech.configurations.tools.customvalidators.elements.numbervalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.superclass.NumberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
public class NotEmptyValidatorN extends NumberValidatorimplements INumberValidator{

public NotEmptyValidatorN() {
    super(new ResponseEntity<>(Responses.ID_INAPPROPRIATE_FORMAT.getData(), HttpStatus.UNPROCESSABLE_ENTITY), "NotEmptyValidatorN");
}
@Override
public Pair<Boolean,ResponseEntity> validate(Long n){
    if (n == null) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(n);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}