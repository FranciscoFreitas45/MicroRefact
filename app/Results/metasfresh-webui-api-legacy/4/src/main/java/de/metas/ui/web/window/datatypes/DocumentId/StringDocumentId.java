package de.metas.ui.web.window.datatypes.DocumentId;
 import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.adempiere.exceptions.AdempiereException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import de.metas.printing.esb.base.util.Check;
import de.metas.ui.web.window.datatypes.json.JSONNullValue;
import de.metas.util.lang.RepoIdAware;
import lombok.NonNull;
public class StringDocumentId extends DocumentId{

 private  String idStr;


@Override
public boolean isComposedKey(){
    return idStr.indexOf(COMPOSED_KEY_SEPARATOR) >= 0;
}


@Override
public String toJson(){
    return idStr;
}


@Override
public int toInt(){
    if (isComposedKey()) {
        throw new AdempiereException("Composed keys cannot be converted to int: " + this);
    } else {
        throw new AdempiereException("String document IDs cannot be converted to int: " + this);
    }
}


@Override
public int hashCode(){
    return Objects.hash(idStr);
}


@Override
public List<Object> toComposedKeyParts(){
    final ImmutableList.Builder<Object> composedKeyParts = ImmutableList.builder();
    COMPOSED_KEY_SPLITTER.split(idStr).forEach(composedKeyParts::add);
    return composedKeyParts.build();
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (!(obj instanceof StringDocumentId)) {
        return false;
    }
    final StringDocumentId other = (StringDocumentId) obj;
    return Objects.equals(idStr, other.idStr);
}


@Override
public boolean isInt(){
    return false;
}


@Override
public boolean isNew(){
    return false;
}


}