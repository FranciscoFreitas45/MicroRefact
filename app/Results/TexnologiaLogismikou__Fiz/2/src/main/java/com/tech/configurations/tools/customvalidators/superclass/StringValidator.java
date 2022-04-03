package com.tech.configurations.tools.customvalidators.superclass;
 import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IStringValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import org.springframework.http.ResponseEntity;
public class StringValidator extends BaseValidatorimplements IStringValidator{

 protected  IStringValidator next;

public StringValidator(ResponseEntity RESPONSE_ERROR, String name) {
    super(RESPONSE_ERROR, name);
}
@Override
public void replaceNext(ICustomValidator next){
    if (!(next instanceof IStringValidator)) {
        throw new InappropriateValidatorException();
    }
    this.next = (IStringValidator) next;
}


@Override
public void setNext(ICustomValidator next){
    if (!(next instanceof IStringValidator)) {
        throw new InappropriateValidatorException();
    }
    if (this.next != null) {
        this.next.setNext(next);
    } else {
        this.next = (IStringValidator) next;
    }
}


@Override
public ICustomValidator getNext(){
    return next;
}


}