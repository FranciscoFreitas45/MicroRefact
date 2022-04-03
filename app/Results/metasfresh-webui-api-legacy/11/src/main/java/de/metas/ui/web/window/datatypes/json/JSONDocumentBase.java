package de.metas.ui.web.window.datatypes.json;
 import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import de.metas.ui.web.window.datatypes.DocumentId;
import de.metas.ui.web.window.datatypes.DocumentPath;
import de.metas.ui.web.window.datatypes.WindowId;
import de.metas.ui.web.window.exceptions.InvalidDocumentPathException;
public class JSONDocumentBase {

@JsonProperty("windowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  WindowId windowId;

@JsonProperty("id")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DocumentId id;

@JsonProperty("tabId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  String tabId;

@JsonProperty("tabid")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Deprecated
 private  String tabid;

@JsonProperty("rowId")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  DocumentId rowId;

@JsonProperty("deleted")
@JsonInclude(JsonInclude.Include.NON_NULL)
 private  Boolean deleted;

@JsonProperty("fieldsByName")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
 private  Map<String,JSONDocumentField> fieldsByName;

 private  Map<String,Object> otherProperties;

@JsonIgnore
 private  boolean unboxPasswordFields;


@JsonAnyGetter
public Map<String,Object> getOtherProperties(){
    return otherProperties;
}


public JSONDocumentBase putDebugProperty(String name,Object jsonValue){
    otherProperties.put("debug-" + name, jsonValue);
    return this;
}


@JsonIgnore
public int getFieldsCount(){
    return fieldsByName == null ? 0 : fieldsByName.size();
}


@JsonIgnore
public String getTabId(){
    return tabId;
}


@JsonIgnore
public DocumentId getRowId(){
    return rowId;
}


@Override
public String toString(){
    return MoreObjects.toStringHelper(this).omitNullValues().add("windowId", windowId).add("id", id).add("tablId", tabId).add("rowId", rowId).toString();
}


@JsonIgnore
public DocumentId getId(){
    return id;
}


public void setFields(Map<String,JSONDocumentField> fieldsByName){
    this.fieldsByName = fieldsByName;
    if (unboxPasswordFields && fieldsByName != null && !fieldsByName.isEmpty()) {
        fieldsByName.forEach((fieldName, field) -> field.unboxPasswordField());
    }
}


@JsonIgnore
public void setUnboxPasswordFields(){
    this.unboxPasswordFields = true;
}


@JsonIgnore
public WindowId getWindowId(){
    return windowId;
}


public void setDeleted(){
    deleted = Boolean.TRUE;
}


@JsonAnySetter
public void putOtherProperty(String name,Object jsonValue){
    otherProperties.put(name, jsonValue);
}


}