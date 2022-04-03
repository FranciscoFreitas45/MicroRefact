package edu.xr.campusweibo.service.util;
 import org.apache.commons.lang3.RandomStringUtils;
public class RandomUtil {

 private  int DEF_COUNT;


public String generateActivationKey(){
    return RandomStringUtils.randomNumeric(DEF_COUNT);
}


public String generateResetKey(){
    return RandomStringUtils.randomNumeric(DEF_COUNT);
}


public String generateTokenData(){
    return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
}


public String generatePassword(){
    return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
}


public String generateSeriesData(){
    return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
}


}