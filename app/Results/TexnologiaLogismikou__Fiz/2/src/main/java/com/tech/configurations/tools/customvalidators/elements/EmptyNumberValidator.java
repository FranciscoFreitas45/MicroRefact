package com.tech.configurations.tools.customvalidators.elements;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
import com.tech.configurations.tools.customvalidators.superclass.NumberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class EmptyNumberValidator extends NumberValidatorimplements INumberValidator{

public EmptyNumberValidator() {
    super(new ResponseEntity<>(Responses.BAD_COORDINATES.getData(), HttpStatus.UNPROCESSABLE_ENTITY), "Empty");
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