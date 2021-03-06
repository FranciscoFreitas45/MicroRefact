package com.fosun.fc.projects.creepers.entity;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "T_CREEPERS_MER_UNDO")
@NamedQuery(name = "TCreepersMerUndo.findAll", query = "SELECT t FROM TCreepersMerUndo t")
public class TCreepersMerUndo {

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_MER_UNDO_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_MER_UNDO")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_MER_UNDO_ID_GENERATOR")
@Column(unique = true, nullable = false)
 private  long id;

@Column(name = "MER_NO", length = 100)
 private  String merNo;

@Temporal(TemporalType.DATE)
@Column(name = "UNDO_DT")
 private  Date undoDt;

@Column(name = "UNDO_ITEM", length = 200)
 private  String undoItem;

@Column(name = "UNDO_NEW", length = 100)
 private  String undoNew;

@Column(name = "UNDO_OLD", length = 50)
 private  String undoOld;

public TCreepersMerUndo() {
}
public void setUndoOld(String undoOld){
    this.undoOld = undoOld;
}


public Date getUndoDt(){
    return this.undoDt;
}


public void setMerNo(String merNo){
    this.merNo = merNo;
}


public void setUndoItem(String undoItem){
    this.undoItem = undoItem;
}


public void setUndoDt(Date undoDt){
    this.undoDt = undoDt;
}


public String getUndoNew(){
    return this.undoNew;
}


public String getMerNo(){
    return this.merNo;
}


public void setId(long id){
    this.id = id;
}


public String getUndoItem(){
    return this.undoItem;
}


public long getId(){
    return this.id;
}


public void setUndoNew(String undoNew){
    this.undoNew = undoNew;
}


public String getUndoOld(){
    return this.undoOld;
}


}