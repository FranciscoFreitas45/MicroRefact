package de.metas.ui.web.window.datatypes.LookupValue;
 import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.annotation.Nullable;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.KeyNamePair;
import org.compiere.util.NamePair;
import org.compiere.util.ValueNamePair;
import org.compiere.util.ValueNamePairValidationInformation;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import de.metas.i18n.ITranslatableString;
import de.metas.i18n.TranslatableStrings;
import de.metas.process.IProcessDefaultParametersProvider;
import de.metas.process.JavaProcess;
import de.metas.ui.web.process.descriptor.ProcessParamLookupValuesProvider;
import de.metas.util.lang.ReferenceListAwareEnum;
import de.metas.util.lang.RepoIdAware;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
public class StringLookupValue extends LookupValue{

 private  Integer idInt;


@Override
public int getIdAsInt(){
    Integer idInt = this.idInt;
    if (idInt == null) {
        idInt = this.idInt = Integer.parseInt((String) id);
    }
    return idInt;
}


public StringLookupValue of(String id,ITranslatableString displayName,ITranslatableString helpText,ValueNamePairValidationInformation validationInformation){
    return new StringLookupValue(id, displayName, helpText, null, /* attributes */
    null, /* active */
    validationInformation);
}


public StringLookupValue unknown(String value){
    return new StringLookupValue(value, TranslatableStrings.constant("<" + value + ">"), null, /* description */
    null, /* attributes */
    false, /* not active */
    null);
}


}