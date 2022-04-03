package de.metas.ui.web.window.datatypes.json;
 import java.util.List;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Singular;
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class JSONDocumentReferencesGroup {

@JsonProperty("caption")
 private  String caption;

@JsonProperty("references")
 private  List<JSONDocumentReference> references;

@JsonIgnore
 private  boolean miscGroup;


public List<JSONDocumentReference> getReferences(){
    return references;
}


public boolean isEmpty(){
    return references.isEmpty();
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).add("caption", caption).add("references", references).toString();
}


public String getCaption(){
    return caption;
}


public boolean isMiscGroup(){
    return miscGroup;
}


}