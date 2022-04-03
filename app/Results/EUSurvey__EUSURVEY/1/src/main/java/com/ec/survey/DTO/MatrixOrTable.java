package com.ec.survey.DTO;
 import com.ec.survey.tools.Tools;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class MatrixOrTable extends Question{

 private  long serialVersionUID;

 protected  List<Element> childElements;

 protected  List<Element> missingQuestions;

 protected  List<Element> missingAnswers;

 protected  int rows;

 protected  int columns;

 protected  String widths;

 protected  Integer tableType;

 protected  String firstCellText;


@Transient
public List<Element> getMissingAnswers(){
    return missingAnswers;
}


@Transient
public int getAllRows(){
    return getRows() + missingQuestions.size();
}


@Transient
public int getRows()


@Transient
public String getWidth(int col){
    try {
        if (widths != null) {
            String[] allwidths = widths.split(";");
            return allwidths[col] + "px";
        }
    } catch (Exception e) {
    // ignore
    }
    return "50px";
}


@Transient
public int getColumns()


@Column(name = "TABLETYPE")
public Integer getTableType(){
    return tableType;
}


@Transient
public List<Element> getAllChildElements(){
    List<Element> result = new ArrayList<>();
    result.addAll(childElements);
    result.addAll(missingQuestions);
    result.addAll(missingAnswers);
    return result;
}


@Transient
public List<Element> getQuestions(){
    List<Element> result = new ArrayList<>();
    List<Element> childElements = this.childElements;
    for (int i = getColumns(); i < childElements.size(); i++) {
        result.add(childElements.get(i));
    }
    result.addAll(missingQuestions);
    return result;
}


@Transient
public String getCompleteWidth(){
    try {
        if (widths != null) {
            Integer w = 0;
            String[] allwidths = widths.split(";");
            if (allwidths.length == getColumns()) {
                for (String s : allwidths) {
                    if (s.length() > 0) {
                        w += Integer.parseInt(s);
                    }
                }
                return w.toString() + "px";
            }
        }
    } catch (Exception e) {
    // ignore
    }
    return "auto";
}


@OneToMany(targetEntity = Element.class, cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SELECT)
@OrderBy(value = "position asc")
@JoinColumn(nullable = true)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Element> getChildElements(){
    return childElements;
}


@Transient
public List<Element> getMissingQuestions(){
    return missingQuestions;
}


@Transient
public int getAllColumns(){
    return getColumns() + missingAnswers.size();
}


@Transient
public List<Element> getAnswers(){
    List<Element> result = new ArrayList<>();
    List<Element> childElements = this.childElements;
    for (int i = 1; i < getColumns(); i++) {
        result.add(childElements.get(i));
    }
    result.addAll(missingAnswers);
    return result;
}


@Lob
@Column(name = "FIRSTCELLTEXT", length = 40000)
public String getFirstCellText(){
    return firstCellText != null && firstCellText.length() > 0 ? firstCellText : " ";
}


@Column(name = "TABLEWIDTHS")
public String getWidths(){
    return widths;
}


}