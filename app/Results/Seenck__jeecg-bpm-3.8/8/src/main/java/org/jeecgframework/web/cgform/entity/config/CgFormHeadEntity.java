package org.jeecgframework.web.cgform.entity.config;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;
import org.jeecgframework.codegenerate.database.JeecgReadTable;
@Entity
@Table(name = "cgform_head", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class CgFormHeadEntity {

 private  java.lang.String id;

 private  java.lang.String tableName;

 private  java.lang.String isTree;

 private  java.lang.String isPagination;

 private  java.lang.String isDbSynch;

 private  java.lang.String isCheckbox;

 private  java.lang.String querymode;

 private  java.lang.String content;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;

 private  java.lang.String jformVersion;

 private  Integer jformType;

 private  java.lang.String jformPkType;

 private  java.lang.String jformPkSequence;

 private  Integer relationType;

 private  String subTableStr;

 private  Integer tabOrder;

 private  List<CgFormFieldEntity> columns;

 private  List<CgFormIndexEntity> indexes;

 private  java.lang.String treeParentIdFieldName;

 private  java.lang.String treeIdFieldname;

 private  java.lang.String treeFieldname;

 private  java.lang.String jformCategory;

 private  String formTemplate;

 private  String formTemplateMobile;

 private  String tableType;

 private  Integer tableVersion;

 private  String physiceId;


public void setTableName(java.lang.String tableName){
    this.tableName = tableName;
}


public void setTreeParentIdFieldName(java.lang.String treeParentIdFieldName){
    this.treeParentIdFieldName = treeParentIdFieldName;
}


public void setJformVersion(java.lang.String jformVersion){
    this.jformVersion = jformVersion;
}


@Column(name = "form_template", length = 50)
public String getFormTemplate(){
    return formTemplate;
}


@Column(name = "relation_type", nullable = true, length = 1)
public Integer getRelationType(){
    return relationType;
}


@Transient
public java.lang.String getTreeFieldnamePage(){
    if (StringUtils.isNotEmpty(treeFieldname)) {
        return JeecgReadTable.formatField(treeFieldname);
    }
    return "";
}


public void setTreeFieldname(java.lang.String treeFieldname){
    this.treeFieldname = treeFieldname;
}


@Column(name = "is_checkbox", nullable = false, length = 5)
public java.lang.String getIsCheckbox(){
    return this.isCheckbox;
}


public void setTreeIdFieldname(java.lang.String treeIdFieldname){
    this.treeIdFieldname = treeIdFieldname;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setJformType(Integer jformType){
    this.jformType = jformType;
}


@Column(name = "table_type", length = 50)
public String getTableType(){
    return tableType;
}


public void setJformCategory(java.lang.String jformCategory){
    this.jformCategory = jformCategory;
}


public void setSubTableStr(String subTableStr){
    this.subTableStr = subTableStr;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


public void setIsPagination(java.lang.String isPagination){
    this.isPagination = isPagination;
}


public void setTabOrder(Integer tabOrder){
    this.tabOrder = tabOrder;
}


@Column(name = "is_tree", nullable = false, length = 5)
public java.lang.String getIsTree(){
    return this.isTree;
}


public void setIsTree(java.lang.String isTree){
    this.isTree = isTree;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "physice_id", length = 32)
public String getPhysiceId(){
    return physiceId;
}


@Column(name = "tree_fieldname", nullable = true, length = 50)
public java.lang.String getTreeFieldname(){
    return treeFieldname;
}


public void setIsDbSynch(java.lang.String isDbSynch){
    this.isDbSynch = isDbSynch;
}


@Column(name = "table_name", nullable = false, length = 50)
public java.lang.String getTableName(){
    return this.tableName;
}


public void setIndexes(List<CgFormIndexEntity> indexes){
    this.indexes = indexes;
}


@Column(name = "jform_type", nullable = false, length = 1)
public Integer getJformType(){
    return jformType;
}


public void setQuerymode(java.lang.String querymode){
    this.querymode = querymode;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "form_template_mobile", length = 50)
public String getFormTemplateMobile(){
    return formTemplateMobile;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setIsCheckbox(java.lang.String isCheckbox){
    this.isCheckbox = isCheckbox;
}


@Column(name = "jform_pk_type", nullable = true, length = 100)
public java.lang.String getJformPkType(){
    return jformPkType;
}


@Column(name = "table_version", length = 11)
public Integer getTableVersion(){
    return tableVersion;
}


@Column(name = "jform_version", nullable = false, length = 10)
public java.lang.String getJformVersion(){
    return jformVersion;
}


public void setColumns(List<CgFormFieldEntity> columns){
    this.columns = columns;
}


public void setContent(java.lang.String content){
    this.content = content;
}


public void setPhysiceId(String physiceId){
    this.physiceId = physiceId;
}


@Column(name = "content", nullable = false, length = 200)
public java.lang.String getContent(){
    return this.content;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "tab_order", nullable = true, length = 1)
public Integer getTabOrder(){
    return tabOrder;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "id", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "jform_pk_sequence", nullable = true, length = 200)
public java.lang.String getJformPkSequence(){
    return jformPkSequence;
}


public void setRelationType(Integer relationType){
    this.relationType = relationType;
}


public void setTableVersion(Integer tableVersion){
    this.tableVersion = tableVersion;
}


@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "table")
@OrderBy(clause = "orderNum asc")
public List<CgFormFieldEntity> getColumns(){
    return columns;
}


@Transient
public java.lang.String getTreeParentIdFieldNamePage(){
    if (StringUtils.isNotEmpty(treeParentIdFieldName)) {
        return JeecgReadTable.formatField(treeParentIdFieldName);
    }
    return "";
}


@Column(name = "jform_category", nullable = false, length = 50)
public java.lang.String getJformCategory(){
    return jformCategory;
}


@Column(name = "tree_id_fieldname", nullable = true, length = 50)
public java.lang.String getTreeIdFieldname(){
    return treeIdFieldname;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public void setJformPkType(java.lang.String jformPkType){
    this.jformPkType = jformPkType;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "is_dbsynch", nullable = false, length = 20)
public java.lang.String getIsDbSynch(){
    return this.isDbSynch;
}


public void setTableType(String tableType){
    this.tableType = tableType;
}


@Column(name = "tree_parentid_fieldname", nullable = true, length = 50)
public java.lang.String getTreeParentIdFieldName(){
    return treeParentIdFieldName;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "table")
public List<CgFormIndexEntity> getIndexes(){
    return indexes;
}


@Column(name = "is_pagination", nullable = false, length = 5)
public java.lang.String getIsPagination(){
    return this.isPagination;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setFormTemplateMobile(String formTemplateMobile){
    this.formTemplateMobile = formTemplateMobile;
}


@Column(name = "querymode", nullable = false, length = 10)
public java.lang.String getQuerymode(){
    return this.querymode;
}


public void setFormTemplate(String formTemplate){
    this.formTemplate = formTemplate;
}


public void setJformPkSequence(java.lang.String jformPkSequence){
    this.jformPkSequence = jformPkSequence;
}


@Column(name = "sub_table_str", nullable = true, length = 1000)
public String getSubTableStr(){
    return subTableStr;
}


}