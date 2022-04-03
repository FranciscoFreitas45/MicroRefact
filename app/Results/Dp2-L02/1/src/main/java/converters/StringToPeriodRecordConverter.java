package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.PeriodRecordRepository;
import domain.PeriodRecord;
@Component
@Transactional
public class StringToPeriodRecordConverter implements Converter<String, PeriodRecord>{

@Autowired
 private PeriodRecordRepository periodRecordRepository;


@Override
public PeriodRecord convert(String text){
    PeriodRecord result = new PeriodRecord();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.periodRecordRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}