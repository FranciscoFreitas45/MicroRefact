package converters;
 import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Member;
@Component
@Transactional
public class MemberToStringConverter implements Converter<Member, String>{


@Override
public String convert(Member member){
    String result;
    if (member == null)
        result = null;
    else
        result = String.valueOf(member.getId());
    return result;
}


}