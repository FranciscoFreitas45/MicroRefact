package de.metas.ui.web.letter.WebuiLetterRepository;
 import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import org.adempiere.model.InterfaceWrapperHelper;
import org.springframework.stereotype.Component;
import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import de.metas.letters.model.I_C_Letter;
import de.metas.ui.web.exceptions.EntityNotFoundException;
import de.metas.ui.web.letter.WebuiLetter.WebuiLetterBuilder;
import de.metas.util.Check;
import lombok.NonNull;
import lombok.ToString;
@ToString
public class WebuiLetterEntry {

 private  WebuiLetter letter;


public WebuiLetterChangeResult compute(UnaryOperator<WebuiLetter> modifier){
    final WebuiLetter letterOld = letter;
    final WebuiLetter letterNew = modifier.apply(letterOld);
    if (letterNew == null) {
        throw new NullPointerException("letter");
    }
    letter = letterNew;
    return WebuiLetterChangeResult.builder().letter(letterNew).originalLetter(letterOld).build();
}


public WebuiLetter getLetter(){
    return letter;
}


}