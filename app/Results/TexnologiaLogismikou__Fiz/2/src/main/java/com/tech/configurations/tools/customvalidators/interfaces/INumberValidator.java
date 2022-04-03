package com.tech.configurations.tools.customvalidators.interfaces;
 import com.tech.configurations.tools.Pair;
import org.springframework.http.ResponseEntity;
public interface INumberValidator extends ICustomValidator{


public Pair<Boolean,ResponseEntity> validate(Long n)
;

}