package org.opengeoportal.export.geocommons;
 import com.fasterxml.jackson.annotation.JsonProperty;
public class CreateFileDataSetRequestJson {

@JsonProperty("title")
 private String title;

@JsonProperty("description")
 private String description;

@JsonProperty("author")
 private String author;

@JsonProperty("tags")
 private String tags;

@JsonProperty("metadata_url")
 private String metadata_url;

@JsonProperty("contact_name")
 private String contact_name;

@JsonProperty("contact_address")
 private String contact_address;

@JsonProperty("contact_phone")
 private String contact_phone;


public String getAuthor(){
    return author;
}


public void setContact_name(String contact_name){
    this.contact_name = contact_name;
}


public void setTitle(String title){
    this.title = title;
}


public String getTags(){
    return tags;
}


public void setDescription(String description){
    this.description = description;
}


public void setTags(String tags){
    this.tags = tags;
}


public void setContact_phone(String contact_phone){
    this.contact_phone = contact_phone;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


public void setContact_address(String contact_address){
    this.contact_address = contact_address;
}


public String getContact_phone(){
    return contact_phone;
}


public void setAuthor(String author){
    this.author = author;
}


public String getContact_address(){
    return contact_address;
}


public void setMetadata_url(String metadata_url){
    this.metadata_url = metadata_url;
}


public String getMetadata_url(){
    return metadata_url;
}


public String getContact_name(){
    return contact_name;
}


}