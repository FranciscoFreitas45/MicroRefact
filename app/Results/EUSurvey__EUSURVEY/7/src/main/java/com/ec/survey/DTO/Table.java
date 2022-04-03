package com.ec.survey.DTO;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;
public class Table extends MatrixOrTable{

 private  long serialVersionUID;

public Table(String ptitle, String shortname, String uid) {
    setTitle(ptitle);
    setShortname(shortname);
    setUniqueId(uid);
}public Table() {
}
@Column(name = "TABLEROWS")
public int getRows(){
    return rows;
}


@Column(name = "TABLECOLUMNS")
public int getColumns(){
    return columns;
}


}