package run.halo.app.utils;
 import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
public class ValidationUtils {

 private  Validator VALIDATOR;

private ValidationUtils() {
}
public Map<String,String> mapWithFieldError(List<FieldError> fieldErrors){
    if (CollectionUtils.isEmpty(fieldErrors)) {
        return Collections.emptyMap();
    }
    Map<String, String> errMap = new HashMap<>(4);
    fieldErrors.forEach(filedError -> errMap.put(filedError.getField(), filedError.getDefaultMessage()));
    return errMap;
}


@NonNull
public Map<String,String> mapWithValidError(Set<ConstraintViolation<?>> constraintViolations){
    if (CollectionUtils.isEmpty(constraintViolations)) {
        return Collections.emptyMap();
    }
    Map<String, String> errMap = new HashMap<>(4);
    // Format the error message
    constraintViolations.forEach(constraintViolation -> errMap.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()));
    return errMap;
}


@NonNull
public Validator getValidator(){
    if (VALIDATOR == null) {
        synchronized (ValidationUtils.class) {
            if (VALIDATOR == null) {
                // Init the validation
                VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
            }
        }
    }
    return VALIDATOR;
}


public void validate(Iterable<?> objs,Class<?> groups){
    if (objs == null) {
        return;
    }
    // get validator
    Validator validator = getValidator();
    // wrap index
    AtomicInteger i = new AtomicInteger(0);
    final Set<ConstraintViolation<?>> allViolations = new LinkedHashSet<>();
    objs.forEach(obj -> {
        int index = i.getAndIncrement();
        Set<? extends ConstraintViolation<?>> violations = validator.validate(obj, groups);
        violations.forEach(violation -> {
            Path path = violation.getPropertyPath();
            if (path instanceof PathImpl) {
                PathImpl pathImpl = (PathImpl) path;
                pathImpl.makeLeafNodeIterableAndSetIndex(index);
            }
            allViolations.add(violation);
        });
    });
    if (!CollectionUtils.isEmpty(allViolations)) {
        throw new ConstraintViolationException(allViolations);
    }
}


}