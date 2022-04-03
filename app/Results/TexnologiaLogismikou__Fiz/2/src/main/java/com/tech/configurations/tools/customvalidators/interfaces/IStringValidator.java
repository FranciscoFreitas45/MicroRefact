package com.tech.configurations.tools.customvalidators.interfaces;
 import com.tech.configurations.tools.Pair;
import org.springframework.http.ResponseEntity;
public interface IStringValidator extends ICustomValidator{


public Pair<Boolean,ResponseEntity> validate(String str)
;

}