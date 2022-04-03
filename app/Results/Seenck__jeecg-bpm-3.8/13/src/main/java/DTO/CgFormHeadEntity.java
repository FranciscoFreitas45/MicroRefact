package DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


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


@Column(name = "is_checkbox", nullable = false, length = 5)
public java.lang.String getIsCheckbox(){
    return this.isCheckbox;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "table_type", length = 50)
public String getTableType(){
    return tableType;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "is_tree", nullable = false, length = 5)
public java.lang.String getIsTree(){
    return this.isTree;
}


@Column(name = "physice_id", length = 32)
public String getPhysiceId(){
    return physiceId;
}


@Column(name = "tree_fieldname", nullable = true, length = 50)
public java.lang.String getTreeFieldname(){
    return treeFieldname;
}


@Column(name = "table_name", nullable = false, length = 50)
public java.lang.String getTableName(){
    return this.tableName;
}


@Column(name = "jform_type", nullable = false, length = 1)
public Integer getJformType(){
    return jformType;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "form_template_mobile", length = 50)
public String getFormTemplateMobile(){
    return formTemplateMobile;
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


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "is_dbsynch", nullable = false, length = 20)
public java.lang.String getIsDbSynch(){
    return this.isDbSynch;
}


@Column(name = "tree_parentid_fieldname", nullable = true, length = 50)
public java.lang.String getTreeParentIdFieldName(){
    return treeParentIdFieldName;
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


@Column(name = "querymode", nullable = false, length = 10)
public java.lang.String getQuerymode(){
    return this.querymode;
}


@Column(name = "sub_table_str", nullable = true, length = 1000)
public String getSubTableStr(){
    return subTableStr;
}


public void setIsDbSynch(java.lang.String isDbSynch){
    this.isDbSynch = isDbSynch;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsDbSynch"))

.queryParam("isDbSynch",isDbSynch)
;
restTemplate.put(builder.toUriString(),null);
}


}