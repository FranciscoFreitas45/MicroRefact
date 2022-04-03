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
public class IntegerLookupValue extends LookupValue{


@Override
public int getIdAsInt(){
    return (Integer) id;
}


public IntegerLookupValue of(StringLookupValue stringLookupValue){
    if (stringLookupValue == null) {
        return null;
    }
    return new IntegerLookupValue(stringLookupValue.getIdAsInt(), stringLookupValue.displayName, stringLookupValue.description, null, /* attributes */
    stringLookupValue.getActive());
}


public IntegerLookupValue unknown(int id){
    return new IntegerLookupValue(id, TranslatableStrings.constant("<" + id + ">"), null, /* description */
    null, /* attributes */
    false);
}


}