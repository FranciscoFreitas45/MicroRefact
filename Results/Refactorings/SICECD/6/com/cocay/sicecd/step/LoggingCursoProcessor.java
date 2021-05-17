import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.cocay.sicecd.dto.CursoDto;
public class LoggingCursoProcessor implements ItemProcessor<CursoDto, CursoDto>{

 private  Logger LOGGER;


@Override
public CursoDto process(CursoDto item){
    LOGGER.info("Processing student information: {}", item);
    return item;
}


}