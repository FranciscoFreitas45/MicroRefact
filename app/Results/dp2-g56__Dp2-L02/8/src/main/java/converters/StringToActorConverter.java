package converters;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import repositories.ActorRepository;
import domain.Actor;
@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor>{

@Autowired
 private ActorRepository actorRepository;


@Override
public Actor convert(String text){
    Actor result = new Actor();
    int id;
    try {
        if (StringUtils.isEmpty(text)) {
            result = null;
        } else {
            id = Integer.valueOf(text);
            result = this.actorRepository.findOne(id);
        }
    } catch (Throwable oops) {
        throw new IllegalArgumentException(oops);
    }
    return result;
}


}