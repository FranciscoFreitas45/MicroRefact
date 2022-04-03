package org.jeecgframework.core.beanvalidator;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
public class BeanValidators {


public void validateWithException(Validator validator,Object object,Class<?> groups){
    Set constraintViolations = validator.validate(object, groups);
    if (!constraintViolations.isEmpty()) {
        throw new ConstraintViolationException(constraintViolations);
    }
}


public Map<String,String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations){
    Map<String, String> errorMessages = new HashMap<String, String>();
    for (ConstraintViolation violation : constraintViolations) {
        errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
    return errorMessages;
}


public List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,String separator){
    List<String> errorMessages = new ArrayList<String>();
    for (ConstraintViolation violation : constraintViolations) {
        errorMessages.add(violation.getPropertyPath() + separator + violation.getMessage());
    }
    return errorMessages;
}


public List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations){
    List<String> errorMessages = new ArrayList<String>();
    for (ConstraintViolation violation : constraintViolations) {
        errorMessages.add(violation.getMessage());
    }
    return errorMessages;
}


}