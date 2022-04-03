package com.tech.models.dtos.superclass;
 import com.tech.configurations.tools.Pair;
import com.tech.exceptions.customexceptions.InappropriateValidatorException;
import org.springframework.http.ResponseEntity;
public class BaseDTO {


public String getDTOName()


public Pair<Boolean,ResponseEntity> validate()


}