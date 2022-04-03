package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.SponsorshipRepository;
import domain.Sponsorship;
@Component
@Transactional
public class StringToSponsorshipConverter implements Converter<String, Sponsorship>{

@Autowired
 private SponsorshipRepository sponsorshipRepository;


@Override
public Sponsorship convert(String text){
    Sponsorship result = new Sponsorship();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.sponsorshipRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}