package DTO;
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
public class DocumentId implements Serializable{

 private  int NEW_ID;

 public  String NEW_ID_STRING;

 public  DocumentId NEW;

 private  String DOCUMENT_ID_PREFIX;

 private  String COMPOSED_KEY_SEPARATOR;

 private  Splitter COMPOSED_KEY_SPLITTER;

 private  int idInt;

 private  String idStr;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public T toId(IntFunction<T> mapper){
    return mapper.apply(toInt());
}


public int toIntOr(int fallbackValue){
    return isInt() ? toInt() : fallbackValue;
}


@Override
public List<Object> toComposedKeyParts(){
    final ImmutableList.Builder<Object> composedKeyParts = ImmutableList.builder();
    COMPOSED_KEY_SPLITTER.split(idStr).forEach(composedKeyParts::add);
    return composedKeyParts.build();
}


@Override
public boolean isNew(){
    return false;
}


@Override
public boolean isComposedKey(){
    return idStr.indexOf(COMPOSED_KEY_SEPARATOR) >= 0;
}


@Override
public String toJson(){
    return idStr;
}


@Override
public int hashCode(){
    return Objects.hash(idStr);
}


public Supplier<DocumentId> supplier(String prefix,int firstId){
    final AtomicInteger nextId = new AtomicInteger(firstId);
    return () -> ofString(prefix + nextId.getAndIncrement());
}


@Override
public boolean isInt(){
    return false;
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


public Object convertToDocumentIdPart(Object idPartObj){
    if (idPartObj == null) {
        return null;
    } else if (idPartObj instanceof LookupValue) {
        return ((LookupValue) idPartObj).getId();
    } else {
        return idPartObj;
    }
}


public DocumentId of(RepoIdAware id){
    return of(id.getRepoId());
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/of"))

.queryParam("id",id);
DocumentId aux = restTemplate.getForObject(builder.toUriString(),DocumentId.class);
return aux;
}


@Override
public int toInt(){
    if (isComposedKey()) {
        throw new AdempiereException("Composed keys cannot be converted to int: " + this);
    } else {
        throw new AdempiereException("String document IDs cannot be converted to int: " + this);
    }
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toInt"))

int aux = restTemplate.getForObject(builder.toUriString(),int.class);
return aux;
}


}