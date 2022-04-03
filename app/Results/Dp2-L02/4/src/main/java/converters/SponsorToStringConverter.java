package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Sponsor;
@Component
@Transactional
public class SponsorToStringConverter implements Converter<Sponsor, String>{


@Override
public String convert(Sponsor sponsor){
    String result;
    if (sponsor == null)
        result = null;
    else
        result = String.valueOf(sponsor.getId());
    return result;
}


}