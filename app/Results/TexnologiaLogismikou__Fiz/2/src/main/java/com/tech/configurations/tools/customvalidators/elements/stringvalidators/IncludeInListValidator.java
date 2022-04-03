package com.tech.configurations.tools.customvalidators.elements.stringvalidators;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.configurations.tools.customvalidators.superclass.StringValidator;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class IncludeInListValidator extends StringValidatorimplements IStringValidator{

 private  List<String> LIST;

public IncludeInListValidator(List<String> list) {
    super(new ResponseEntity<>(Responses.STRING_INAPPROPRIATE_FORMAT.getData(), HttpStatus.NOT_ACCEPTABLE), "IncludeInListValidator");
    this.LIST = list;
}
@Override
public Pair<Boolean,ResponseEntity> validate(String str){
    if (!LIST.contains(str)) {
        return Pair.of(Boolean.FALSE, getErrorResponse());
    }
    if (next != null) {
        return next.validate(str);
    } else {
        return Pair.of(Boolean.TRUE, getSuccessResponse());
    }
}


}