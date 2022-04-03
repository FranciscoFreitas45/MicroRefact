package com.tech.configurations.tools.customvalidators.elements.numbervalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
import com.tech.configurations.tools.customvalidators.superclass.NumberValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class MaxNumberAllowedValidator extends NumberValidatorimplements INumberValidator{

 private  int L;

public MaxNumberAllowedValidator(int L) {
    super(new ResponseEntity<>(Responses.ID_INAPPROPRIATE_FORMAT.getData(), HttpStatus.UNPROCESSABLE_ENTITY), "MaxNumberAllowed");
    this.L = L;
}
@Override
public Pair<Boolean,ResponseEntity> validate(Long n){
    if (n > L) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(n);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}