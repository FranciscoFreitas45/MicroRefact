package com.jeecg.demo.entity;
 import javax.persistence;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSType;
@Entity
@Table(name = "t_s_document")
@PrimaryKeyJoinColumn(name = "id")
public class TSDocument extends TSAttachment{

 private  String documentTitle;

 private  byte[] pictureIndex;

 private  Short documentState;

 private  Short showHome;

 private  TSType TSType;


@Column(name = "showhome")
public Short getShowHome(){
    return showHome;
}


public void setTSType(TSType tSType){
    TSType = tSType;
}


public void setShowHome(Short showHome){
    this.showHome = showHome;
}


@Column(name = "documenttitle", length = 100)
public String getDocumentTitle(){
    return documentTitle;
}


@Column(name = "pictureindex", length = 3000)
public byte[] getPictureIndex(){
    return pictureIndex;
}


@Column(name = "documentstate")
public Short getDocumentState(){
    return documentState;
}


public void setDocumentState(Short documentState){
    this.documentState = documentState;
}


public void setDocumentTitle(String documentTitle){
    this.documentTitle = documentTitle;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "typeid")
public TSType getTSType(){
    return TSType;
}


public void setPictureIndex(byte[] pictureIndex){
    this.pictureIndex = pictureIndex;
}


}