package com.tech.DTO;
 import com.tech.configurations.tools.Pair;
import org.springframework.http.ResponseEntity;
public interface IStringValidator extends ICustomValidator{


public Pair<Boolean,ResponseEntity> validate(String str)
;

}