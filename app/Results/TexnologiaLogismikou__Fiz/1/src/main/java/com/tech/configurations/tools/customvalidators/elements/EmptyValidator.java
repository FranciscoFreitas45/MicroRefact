package com.tech.configurations.tools.customvalidators.elements;
 import com.tech.configurations.tools.customvalidators.interfaces.ICustomValidator;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
public class EmptyValidator implements ICustomValidator{


@Override
public String getName(){
    return null;
}


@Override
public void replaceNext(ICustomValidator next){
}


@Override
public void setNext(ICustomValidator next){
}


@Override
public ICustomValidator getNext(){
    return null;
}


}