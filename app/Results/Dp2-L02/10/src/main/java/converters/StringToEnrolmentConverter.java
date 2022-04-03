package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.EnrolmentRepository;
import domain.Enrolment;
@Component
@Transactional
public class StringToEnrolmentConverter implements Converter<String, Enrolment>{

@Autowired
 private EnrolmentRepository enrolmentRepository;


@Override
public Enrolment convert(String text){
    Enrolment result = new Enrolment();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.enrolmentRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}