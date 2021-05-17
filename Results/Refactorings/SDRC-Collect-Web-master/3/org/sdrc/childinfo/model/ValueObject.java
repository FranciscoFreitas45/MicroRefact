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

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMetadata"));

.queryParam("metadata",metadata);
restTemplate.put(builder.toUriString(),null);


public String getMetadata(){
    return metadata;
}


public void setNid(Integer nid){
    this.nid = nid;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNid"));

.queryParam("nid",nid);
restTemplate.put(builder.toUriString(),null);


public void setValue(Object value){
    this.value = value;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setValue"));

.queryParam("value",value);
restTemplate.put(builder.toUriString(),null);


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

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setDescription"));

.queryParam("description",description);
restTemplate.put(builder.toUriString(),null);


public String getDescription(){
    return description;
}


public void setKey(String key){
    this.key = key;
}

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setKey"));

.queryParam("key",key);
restTemplate.put(builder.toUriString(),null);


}