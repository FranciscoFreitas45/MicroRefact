package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.LinkRecordRepository;
import domain.LinkRecord;
@Component
@Transactional
public class StringToLinkRecordConverter implements Converter<String, LinkRecord>{

@Autowired
 private LinkRecordRepository linkRecordRepository;


@Override
public LinkRecord convert(String text){
    LinkRecord result = new LinkRecord();
    int id;
    try {
        if (StringUtils.isEmpty(text))
            result = null;
        else {
            id = Integer.valueOf(text);
            result = this.linkRecordRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}