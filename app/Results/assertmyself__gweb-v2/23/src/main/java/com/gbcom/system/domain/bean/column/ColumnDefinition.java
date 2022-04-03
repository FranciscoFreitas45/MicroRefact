package com.gbcom.system.domain.bean.column;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "columnParam" })
@XmlRootElement(name = "columnDefinition")
public class ColumnDefinition {

 protected  List<ColumnParam> columnParam;


public List<ColumnParam> getColumnParam(){
    if (columnParam == null) {
        columnParam = new ArrayList<ColumnParam>();
    }
    return this.columnParam;
}


public void setColumnParam(List<ColumnParam> columnParam){
    this.columnParam = columnParam;
}


}