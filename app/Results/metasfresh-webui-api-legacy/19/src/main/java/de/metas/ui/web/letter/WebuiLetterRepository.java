package de.metas.ui.web.letter;
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
@Component
public class WebuiLetterRepository {

 private  AtomicInteger nextLetterId;

 private  Cache<String,WebuiLetterEntry> lettersById;

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


public WebuiLetterEntry getLetterEntry(String letterId){
    final WebuiLetterEntry letterEntry = lettersById.getIfPresent(letterId);
    if (letterEntry == null) {
        throw new EntityNotFoundException("Letter not found").setParameter("letterId", letterId);
    }
    return letterEntry;
}


public void removeLetterById(String letterId){
    lettersById.invalidate(letterId);
}


public void createC_Letter(WebuiLetter letter){
    final I_C_Letter persistentLetter = InterfaceWrapperHelper.newInstance(I_C_Letter.class);
    persistentLetter.setLetterSubject(letter.getSubject());
    persistentLetter.setLetterBody(Strings.nullToEmpty(letter.getContent()));
    persistentLetter.setLetterBodyParsed(letter.getContent());
    persistentLetter.setAD_BoilerPlate_ID(letter.getTextTemplateId());
    persistentLetter.setC_BPartner_ID(letter.getBpartnerId());
    persistentLetter.setC_BPartner_Location_ID(letter.getBpartnerLocationId());
    persistentLetter.setC_BP_Contact_ID(letter.getBpartnerContactId());
    persistentLetter.setBPartnerAddress(Strings.nullToEmpty(letter.getBpartnerAddress()));
    InterfaceWrapperHelper.save(persistentLetter);
}


public WebuiLetterChangeResult changeLetter(String letterId,UnaryOperator<WebuiLetter> letterModifier){
    return getLetterEntry(letterId).compute(letterModifier);
}


public WebuiLetter createNewLetter(WebuiLetterBuilder createRequest){
    final WebuiLetter letter = createRequest.letterId(String.valueOf(nextLetterId.getAndIncrement())).build();
    Check.assumeNotNull(letter.getOwnerUserId(), "ownerUserId is not null");
    lettersById.put(letter.getLetterId(), new WebuiLetterEntry(letter));
    return letter;
}


}