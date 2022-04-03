package io.delivery.entity;
 import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "document")
public class Document {

@Id
@Column(name = "document_id")
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

@Column(name = "document_name")
 private  String name;

@Transient
 private  String specificInnerInfo;

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
 private  List<DocumentItem> documentItems;

public Document() {
}
public void setName(String name){
    this.name = name;
}


public void setDocumentItems(List<DocumentItem> documentItems){
    this.documentItems = documentItems;
}


public String getName(){
    return name;
}


public List<DocumentItem> getDocumentItems(){
    return documentItems;
}


public String getSpecificInnerInfo(){
    return specificInnerInfo;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setSpecificInnerInfo(String specificInnerInfo){
    this.specificInnerInfo = specificInnerInfo;
}


}