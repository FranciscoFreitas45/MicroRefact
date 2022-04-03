package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import security.UserAccount;
import security.UserAccountRepository;
@Component
@Transactional
public class StringToUserAccountConverter implements Converter<String, UserAccount>{

@Autowired
 private UserAccountRepository userAccountRepository;


@Override
public UserAccount convert(String text){
    UserAccount result = new UserAccount();
    int id;
    try {
        if (StringUtils.isEmpty(text)) {
            result = null;
        } else {
            id = Integer.valueOf(text);
            result = this.userAccountRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}