package com.tech.configurations.tools.customvalidators.interfaces;
 import com.tech.exceptions.customexceptions.InappropriateValidatorException;
public interface ICustomValidator {


public String getName()
;

public void replaceNext(ICustomValidator next)
;

public void setNext(ICustomValidator next)
;

public ICustomValidator getNext()
;

}