package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.SponsorRepository;
import domain.Sponsor;
@Component
@Transactional
public class StringToSponsorConverter implements Converter<String, Sponsor>{

@Autowired
 private SponsorRepository sponsorRepository;


@Override
public Sponsor convert(String text){
    Sponsor result = new Sponsor();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.sponsorRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}