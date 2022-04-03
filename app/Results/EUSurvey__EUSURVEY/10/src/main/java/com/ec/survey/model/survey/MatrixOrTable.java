package com.ec.survey.model.survey;
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
@Entity
@DiscriminatorValue("MATORTAB")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
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


public void setColumns(int columns)


@Transient
public List<Element> getMissingAnswers(){
    return missingAnswers;
}


@Transient
@Override
public boolean basicDiffersFrom(Element element){
    if (getShortname() != null && !getShortname().equals(element.getShortname()))
        return true;
    if (getTitle() != null && !getTitle().equals(element.getTitle()))
        return true;
    if (element instanceof Question) {
        Question question = (Question) element;
        if (getHelp() != null && !getHelp().equals(question.getHelp()))
            return true;
        if (!(Objects.equals(getOptional(), question.getOptional())))
            return true;
        if (element instanceof MatrixOrTable) {
            MatrixOrTable matrix = (MatrixOrTable) element;
            if (getRows() != matrix.getRows())
                return true;
            if (getColumns() != matrix.getColumns())
                return true;
            if (!(Objects.equals(getTableType(), matrix.getTableType())))
                return true;
            // only check column widths when size is set to "manual columns width"
            if (getTableType() == 2 && !Tools.isEqual(getWidths(), matrix.getWidths())) {
                return true;
            }
            if (getFirstCellText() != null && !getFirstCellText().equals(matrix.getFirstCellText()))
                return true;
            if (!(Objects.equals(getIsDelphiQuestion(), question.getIsDelphiQuestion())))
                return true;
            if (!(Objects.equals(getDelphiChartType(), question.getDelphiChartType())))
                return true;
        } else {
            return true;
        }
    } else {
        return true;
    }
    return false;
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


public void setRows(int rows)


@Column(name = "TABLETYPE")
public Integer getTableType(){
    return tableType;
}


public void setMissingQuestions(List<Element> missingQuestions){
    this.missingQuestions = missingQuestions;
}


public void setTableType(Integer type){
    this.tableType = type != null ? type : 0;
}


@Transient
public List<Element> getAllChildElements(){
    List<Element> result = new ArrayList<>();
    result.addAll(childElements);
    result.addAll(missingQuestions);
    result.addAll(missingAnswers);
    return result;
}


public void setChildElements(List<Element> childElements){
    this.childElements = childElements;
}


public void initCopy(MatrixOrTable matrixOrTable,String fileDir){
    baseCopy(matrixOrTable);
    matrixOrTable.setColumns(columns);
    matrixOrTable.setRows(rows);
    matrixOrTable.setFirstCellText(firstCellText);
    for (Element element : childElements) {
        Element c = element.copy(fileDir);
        matrixOrTable.childElements.add(c);
    }
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


public void setMissingAnswers(List<Element> missingAnswers){
    this.missingAnswers = missingAnswers;
}


public boolean containsChild(int id){
    for (Element element : getAllChildElements()) {
        if (element.getId().equals(id)) {
            return true;
        }
    }
    return false;
}


@Transient
public int getAllColumns(){
    return getColumns() + missingAnswers.size();
}


public void setFirstCellText(String firstCellText){
    this.firstCellText = firstCellText;
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


public void setWidths(String widths){
    this.widths = widths;
}


@Column(name = "TABLEWIDTHS")
public String getWidths(){
    return widths;
}


}