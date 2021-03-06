package com.tech.configurations.tools.customvalidators.elements.floatvalidator;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IFloatValidator;
import com.tech.configurations.tools.customvalidators.superclass.FloatValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class LatitudeValidator extends FloatValidatorimplements IFloatValidator{

public LatitudeValidator() {
    super(new ResponseEntity<>(Responses.BAD_COORDINATES.getData(), HttpStatus.UNPROCESSABLE_ENTITY), "LatitudeValidator");
}
@Override
public Pair<Boolean,ResponseEntity> validate(float n){
    if (-90 > n || n > 90) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(n);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}