package com.tech.configurations.tools.customvalidators.superclass;
 import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import org.springframework.http.ResponseEntity;
public class NumberValidator extends BaseValidator{

 protected  INumberValidator next;

public NumberValidator(ResponseEntity RESPONSE_ERROR, String name) {
    super(RESPONSE_ERROR, name);
}
@Override
public void replaceNext(ICustomValidator next){
    if (!(next instanceof INumberValidator)) {
        throw new InappropriateValidatorException();
    }
    this.next = (INumberValidator) next;
}


@Override
public void setNext(ICustomValidator next){
    if (!(next instanceof INumberValidator)) {
        throw new InappropriateValidatorException();
    }
    if (this.next != null) {
        this.next.setNext(next);
    } else {
        this.next = (INumberValidator) next;
    }
}


@Override
public ICustomValidator getNext(){
    return next;
}


}