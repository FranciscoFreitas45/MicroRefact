package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.PeriodRecord;
@Component
@Transactional
public class PeriodRecordToStringConverter implements Converter<PeriodRecord, String>{


@Override
public String convert(PeriodRecord periodRecord){
    String result;
    if (periodRecord == null)
        result = null;
    else
        result = String.valueOf(periodRecord.getId());
    return result;
}


}