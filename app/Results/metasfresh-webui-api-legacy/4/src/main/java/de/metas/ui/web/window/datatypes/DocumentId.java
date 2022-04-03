package de.metas.ui.web.window.datatypes;
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
@Immutable
@SuppressWarnings("serial")
public class DocumentId implements Serializable{

 private  int NEW_ID;

 public  String NEW_ID_STRING;

 public  DocumentId NEW;

 private  String DOCUMENT_ID_PREFIX;

 private  String COMPOSED_KEY_SEPARATOR;

 private  Splitter COMPOSED_KEY_SPLITTER;

 private  int idInt;

 private  String idStr;


public int toIntOrThrow(Supplier<? extends X> exceptionSupplier){
    if (isInt()) {
        return toInt();
    } else {
        throw exceptionSupplier.get();
    }
}


public T toId(IntFunction<T> mapper){
    return mapper.apply(toInt());
}


@Override
public int toInt(){
    if (isComposedKey()) {
        throw new AdempiereException("Composed keys cannot be converted to int: " + this);
    } else {
        throw new AdempiereException("String document IDs cannot be converted to int: " + this);
    }
}


public int toIntOr(int fallbackValue){
    return isInt() ? toInt() : fallbackValue;
}


public DocumentId ofStringOrEmpty(String idStr){
    if (Check.isEmpty(idStr, true)) {
        return null;
    }
    return of(idStr.trim());
}


@Override
public List<Object> toComposedKeyParts(){
    final ImmutableList.Builder<Object> composedKeyParts = ImmutableList.builder();
    COMPOSED_KEY_SPLITTER.split(idStr).forEach(composedKeyParts::add);
    return composedKeyParts.build();
}


public DocumentId ofObjectOrNull(Object idObj){
    if (idObj == null) {
        return null;
    }
    if (idObj instanceof String) {
        return ofStringOrEmpty((String) idObj);
    }
    return ofObject(idObj);
}


@Override
public boolean isNew(){
    return false;
}


public DocumentId ofString(String idStr){
    return new StringDocumentId(idStr);
}


@Override
public boolean isComposedKey(){
    return idStr.indexOf(COMPOSED_KEY_SEPARATOR) >= 0;
}


public int removeDocumentPrefixAndConvertToInt(){
    if (isInt()) {
        return toInt();
    }
    String idStr = toJson();
    if (idStr.startsWith(DOCUMENT_ID_PREFIX)) {
        idStr = idStr.substring(DOCUMENT_ID_PREFIX.length());
    }
    return Integer.parseInt(idStr);
}


@Override
public String toJson(){
    return idStr;
}


public DocumentId ofObject(Object idObj){
    if (JSONNullValue.isNull(idObj)) {
        throw new NullPointerException("Null id");
    } else if (idObj instanceof Integer) {
        final int idInt = (int) idObj;
        return of(idInt);
    } else if (idObj instanceof String) {
        final String idStr = (String) idObj;
        return of(idStr);
    } else if (idObj instanceof LookupValue) {
        final LookupValue lookupValue = (LookupValue) idObj;
        return ofObject(lookupValue.getId());
    } else {
        throw new AdempiereException("Cannot convert '" + idObj + "' (" + idObj.getClass() + ") to " + DocumentId.class);
    }
}


@Override
public int hashCode(){
    return Objects.hash(idStr);
}


public DocumentId of(RepoIdAware id){
    return of(id.getRepoId());
}


public Supplier<DocumentId> supplier(String prefix,int firstId){
    final AtomicInteger nextId = new AtomicInteger(firstId);
    return () -> ofString(prefix + nextId.getAndIncrement());
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
public String toString(){
    return toJson();
}


public DocumentId ofComposedKeyParts(List<Object> composedKeyParts){
    Check.assumeNotEmpty(composedKeyParts, "composedKeyParts is not empty");
    if (composedKeyParts.size() == 1) {
        final Object idObj = composedKeyParts.get(0);
        return ofObject(idObj);
    } else {
        final String idStr = composedKeyParts.stream().map(idPart -> convertToDocumentIdPart(idPart)).map(String::valueOf).collect(Collectors.joining(COMPOSED_KEY_SEPARATOR));
        return ofString(idStr);
    }
}


public DocumentId toIncludedRowId(){
    return ofString(DocumentId.DOCUMENT_ID_PREFIX + toJson());
}


public Object convertToDocumentIdPart(Object idPartObj){
    if (idPartObj == null) {
        return null;
    } else if (idPartObj instanceof LookupValue) {
        return ((LookupValue) idPartObj).getId();
    } else {
        return idPartObj;
    }
}


}