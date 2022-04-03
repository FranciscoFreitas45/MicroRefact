package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.SocialProfileRepository;
import domain.SocialProfile;
@Component
@Transactional
public class StringToSocialProfileConverter implements Converter<String, SocialProfile>{

@Autowired
 private SocialProfileRepository socialProfileRepository;


@Override
public SocialProfile convert(String text){
    SocialProfile result = new SocialProfile();
    int id;
    try {
        if (StringUtils.isEmpty(text)) {
            result = null;
        } else {
            id = Integer.valueOf(text);
            result = this.socialProfileRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}