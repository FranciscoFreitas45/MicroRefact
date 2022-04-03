package com.tech.configurations.tools.customvalidators.interfaces;
 import com.tech.configurations.tools.Pair;
import org.springframework.http.ResponseEntity;
public interface IFloatValidator extends ICustomValidator{


public Pair<Boolean,ResponseEntity> validate(float n)
;

}