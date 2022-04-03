package com.ec.survey.DTO;
 import com.ec.survey.model.survey.base.File;
import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class GalleryQuestion extends Question{

 private  long serialVersionUID;

 private  Integer columns;

 private  Integer limit;

 private  boolean selection;

 private  boolean numbering;

 private  List<File> files;

public GalleryQuestion() {
}public GalleryQuestion(String title, String shortname, String uid) {
    super(title, shortname, uid);
}
@Column(name = "SELLIMIT")
public Integer getLimit(){
    return limit;
}


@Transient
@Override
public String getCss(){
    String css = super.getCss();
    css += " gallery";
    if (limit != null && limit > 0) {
        css += " limit" + limit.toString();
    }
    if (columns != null) {
        css += " columns" + columns.toString();
    }
    if (selection) {
        css += " selection";
    }
    return css;
}


@Column(name = "NUMBERING")
public Boolean getNumbering(){
    return numbering;
}


@SuppressWarnings("deprecation")
@ManyToMany(targetEntity = File.class, cascade = CascadeType.ALL)
@JoinTable(foreignKey = @ForeignKey(javax.persistence.ConstraintMode.NO_CONSTRAINT))
@org.hibernate.annotations.ForeignKey(name = "none")
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<File> getFiles(){
    return files;
}


@Column(name = "COLS")
public Integer getColumns(){
    return columns;
}


@Column(name = "SELECTION")
public boolean getSelection(){
    return selection;
}


}