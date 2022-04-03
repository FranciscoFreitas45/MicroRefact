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
public class IntDocumentId extends DocumentId{

 private  int idInt;


@Override
public boolean isComposedKey(){
    return false;
}


@Override
public String toJson(){
    if (idInt == NEW_ID) {
        return NEW_ID_STRING;
    }
    return String.valueOf(idInt);
}


@Override
public int toInt(){
    return idInt;
}


@Override
public int hashCode(){
    return Objects.hash(idInt);
}


@Override
public List<Object> toComposedKeyParts(){
    return ImmutableList.of(idInt);
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (!(obj instanceof IntDocumentId)) {
        return false;
    }
    final IntDocumentId other = (IntDocumentId) obj;
    return idInt == other.idInt;
}


@Override
public boolean isInt(){
    return true;
}


@Override
public boolean isNew(){
    return idInt == NEW_ID;
}


}