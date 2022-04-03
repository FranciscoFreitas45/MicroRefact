package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Request;
@Component
@Transactional
public class RequestToStringConverter implements Converter<Request, String>{


@Override
public String convert(Request request){
    String result;
    if (request == null)
        result = null;
    else
        result = String.valueOf(request.getId());
    return result;
}


}