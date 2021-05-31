public class ValueObject {

 private  String key;

 private  Object value;

 private  String description;

 private  String metadata;

 private  Integer nid;


public String getKey(){
    return key;
}


public Object getValue(){
    return value;
}


public void setMetadata(String metadata){
    this.metadata = metadata;
}


public String getMetadata(){
    return metadata;
}


public void setNid(Integer nid){
    this.nid = nid;
}


public void setValue(Object value){
    this.value = value;
}


@Override
public String toString(){
    return "ValueObject [key=" + key + ", value=" + value + ", description=" + description + ", metadata=" + metadata + "]";
}


public Integer getNid(){
    return nid;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public void setKey(String key){
    this.key = key;
}


}