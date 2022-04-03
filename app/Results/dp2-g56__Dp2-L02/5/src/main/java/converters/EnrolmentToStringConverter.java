package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Enrolment;
@Component
@Transactional
public class EnrolmentToStringConverter implements Converter<Enrolment, String>{


@Override
public String convert(Enrolment enrolment){
    String result;
    if (enrolment == null)
        result = null;
    else
        result = String.valueOf(enrolment.getId());
    return result;
}


}