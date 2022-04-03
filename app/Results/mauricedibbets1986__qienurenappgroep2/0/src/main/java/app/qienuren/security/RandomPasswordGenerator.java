package app.qienuren.security;
 import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;
@Component
public class RandomPasswordGenerator {


public String getErrorCode(){
    return null;
}


public String generatePassayPassword(){
    PasswordGenerator gen = new PasswordGenerator();
    CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
    lowerCaseRule.setNumberOfCharacters(2);
    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
    upperCaseRule.setNumberOfCharacters(2);
    CharacterData digitChars = EnglishCharacterData.Digit;
    CharacterRule digitRule = new CharacterRule(digitChars);
    digitRule.setNumberOfCharacters(2);
    CharacterData specialChars = new CharacterData() {

        public String getErrorCode() {
            return null;
        }

        public String getCharacters() {
            return "!@#$%^&*()_+";
        }
    };
    CharacterRule splCharRule = new CharacterRule(specialChars);
    splCharRule.setNumberOfCharacters(2);
    String password = gen.generatePassword(20, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
    return password;
}


public String getCharacters(){
    return "!@#$%^&*()_+";
}


}