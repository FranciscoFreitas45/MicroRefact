package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.SocialProfile;
@Component
@Transactional
public class SocialProfileToStringConverter implements Converter<SocialProfile, String>{


@Override
public String convert(SocialProfile socialProfile){
    String result;
    if (socialProfile == null) {
        result = null;
    } else {
        result = String.valueOf(socialProfile.getId());
    }
    return result;
}


}