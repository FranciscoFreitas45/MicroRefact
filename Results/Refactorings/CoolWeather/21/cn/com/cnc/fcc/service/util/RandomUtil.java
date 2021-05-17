import org.apache.commons.lang3.RandomStringUtils;
public class RandomUtil {

 private  int DEF_COUNT;


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