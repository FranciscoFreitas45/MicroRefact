import javax.persistence;
@Entity
@Table(name = "tbl_sal_terms")
public class SalaryTerm {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "sal_term_id")
 private  int salTermId;

@Column(name = "sal_type_id")
 private  int salTypeId;

@Column(name = "is_additive")
 private  int isAdditive;

@Column(name = "name")
 private  String name;

@Column(name = "description")
 private  String description;

@Column(name = "formula_type")
 private  String formulaType;

@Column(name = "formula")
 private  String formula;

@Column(name = "percentage")
 private  float percentage;

@Column(name = "table_name")
 private  String tableName;

@Column(name = "field_name")
 private  String fieldName;

@Column(name = "show_grid")
 private  int showGrid;

@Column(name = "from_table")
 private  String fromTable;

@Column(name = "from_column")
 private  String fromColumn;

@Column(name = "is_applied")
 private  int isApplied;

@Column(name = "sort_order")
 private  int sortOrder;

@Column(name = "remarks")
 private  String remarks;

@Transient
 private double value;


public void setName(String name){
    this.name = name;
}


public void setTableName(String tableName){
    this.tableName = tableName;
}


public String getName(){
    return name;
}


public void setFieldName(String fieldName){
    this.fieldName = fieldName;
}


public void setDescription(String description){
    this.description = description;
}


public void setFromColumn(String fromColumn){
    this.fromColumn = fromColumn;
}


public String getDescription(){
    return description;
}


public int getSortOrder(){
    return sortOrder;
}


public int getIsAdditive(){
    return isAdditive;
}


public int getSalTermId(){
    return salTermId;
}


public String getFromTable(){
    return fromTable;
}


public void setSalTypeId(int salTypeId){
    this.salTypeId = salTypeId;
}


public void setIsAdditive(int isAdditive){
    this.isAdditive = isAdditive;
}


public void setIsApplied(int isApplied){
    this.isApplied = isApplied;
}


public String getFormula(){
    return formula;
}


public String getFromColumn(){
    return fromColumn;
}


public float getPercentage(){
    return percentage;
}


public String getFormulaType(){
    return formulaType;
}


public void setSortOrder(int sortOrder){
    this.sortOrder = sortOrder;
}


public String getTableName(){
    return tableName;
}


public String getRemarks(){
    return remarks;
}


public int getIsApplied(){
    return isApplied;
}


public void setSalTermId(int salTermId){
    this.salTermId = salTermId;
}


public void setPercentage(float percentage){
    this.percentage = percentage;
}


public void setFromTable(String fromTable){
    this.fromTable = fromTable;
}


public int getSalTypeId(){
    return salTypeId;
}


public void setFormulaType(String formulaType){
    this.formulaType = formulaType;
}


public double getValue(){
    return value;
}


public void setValue(double value){
    this.value = value;
}


public void setFormula(String formula){
    this.formula = formula;
}


@Override
public String toString(){
    return "SalaryTerm [salTermId=" + salTermId + ", salTypeId=" + salTypeId + ", isAdditive=" + isAdditive + ", name=" + name + ", description=" + description + ", formulaType=" + formulaType + ", formula=" + formula + ", percentage=" + percentage + ", tableName=" + tableName + ", fieldName=" + fieldName + ", showGrid=" + showGrid + ", fromTable=" + fromTable + ", fromColumn=" + fromColumn + ", isApplied=" + isApplied + ", sortOrder=" + sortOrder + ", remarks=" + remarks + ", value=" + value + "]";
}


public int getShowGrid(){
    return showGrid;
}


public String getFieldName(){
    return fieldName;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


public void setShowGrid(int showGrid){
    this.showGrid = showGrid;
}


}