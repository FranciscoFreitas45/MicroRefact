package com.tech.configurations.tools.customvalidators.superclass;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.configurations.tools.customvalidators.interfaces.IFloatValidator;
import com.tech.configurations.tools.customvalidators.interfaces.INumberValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import org.springframework.http.ResponseEntity;
public class FloatValidator extends BaseValidator{

 protected  IFloatValidator next;

public FloatValidator(ResponseEntity RESPONSE_ERROR, String name) {
    super(RESPONSE_ERROR, name);
}
@Override
public void replaceNext(ICustomValidator next){
    if (!(next instanceof IFloatValidator)) {
        throw new InappropriateValidatorException();
    }
    this.next = (IFloatValidator) next;
}


@Override
public void setNext(ICustomValidator next){
    if (!(next instanceof IFloatValidator)) {
        throw new InappropriateValidatorException();
    }
    if (this.next != null) {
        this.next.setNext(next);
    } else {
        this.next = (IFloatValidator) next;
    }
}


@Override
public ICustomValidator getNext(){
    return next;
}


}