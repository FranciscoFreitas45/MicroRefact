package com.evolvingreality.onleave.service.util;
 import org.apache.commons.lang.RandomStringUtils;
public class RandomUtil {

 private  int DEF_COUNT;

private RandomUtil() {
}
public String generateActivationKey(){
    return RandomStringUtils.randomNumeric(DEF_COUNT);
}


public String generateResetKey(){
    return RandomStringUtils.randomNumeric(DEF_COUNT);
}


public String generatePassword(){
    return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
}


}