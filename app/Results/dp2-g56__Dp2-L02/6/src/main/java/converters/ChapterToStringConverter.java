package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Chapter;
@Component
@Transactional
public class ChapterToStringConverter implements Converter<Chapter, String>{


@Override
public String convert(Chapter chapter){
    String result;
    if (chapter == null)
        result = null;
    else
        result = String.valueOf(chapter.getId());
    return result;
}


}