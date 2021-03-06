package com.tech.configurations.tools.customvalidators.elements.numbervalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
import com.tech.configurations.tools.customvalidators.superclass.NumberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class NotNegativeValidator extends NumberValidatorimplements INumberValidator{

public NotNegativeValidator() {
    super(new ResponseEntity<>(Responses.ID_INAPPROPRIATE_FORMAT.getData(), HttpStatus.UNPROCESSABLE_ENTITY), "NotNegativeValidator");
}
@Override
public Pair<Boolean,ResponseEntity> validate(Long n){
    if (n <= 0) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(n);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}