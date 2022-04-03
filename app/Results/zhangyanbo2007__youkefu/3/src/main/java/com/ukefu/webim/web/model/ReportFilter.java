package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import com.ukefu.util.UKTools;
import com.ukefu.util.bi.ReportData;
@Entity
@Table(name = "uk_reportfilter")
@org.hibernate.annotations.Proxy(lazy = false)
public class ReportFilter {

 private  long serialVersionUID;

 private  String id;

 private  String dataid;

 private  String dataname;

 private  String title;

 private  String modelid;

 private  String reportid;

 private  String contype;

 private  Date createtime;

 private  String funtype;

 private  String filtertype;

 private  String measureid;

 private  String dimid;

 private  boolean request;

 private  String cubeid;

 private  String modeltype;

 private  String formatstr;

 private  String convalue;

 private  String userdefvalue;

 private  String valuefiltertype;

 private  String name;

 private  String code;

 private  String orgi;

 private  String content;

 private  String valuestr;

 private  String filterprefix;

 private  String filtersuffix;

 private  String valuecompare;

 private  String defaultvalue;

 private  String noformatvalue;

 private  String startvalue;

 private  String endvalue;

 private  String comparetype;

 private  String requestvalue;

 private  boolean child;

 private  String requeststartvalue;

 private  String requestendvalue;

 private  String defaultvaluerule;

 private  CubeLevel level;

 private  ReportData reportData;

 private  String queryText;

 private  String mustvalue;

 private  String cascadeid;

 private  TableProperties tableproperty;

 private  ReportFilter childFilter;

@SuppressWarnings("unused")
 private  ReportFilter parentFilter;

 private  String parentValue;

 private  String groupids;

 private  String filtertemplet;

 private  int sortindex;

 private  String tableid;

 private  String fieldid;

 private  String fktableid;

 private  String fkfieldid;

 private  String filterfieldid;

 private  boolean isdic;

 private  String diccode;

 private  String keyfield;

 private  String valuefield;


public String getCascadeid(){
    return cascadeid;
}


public String getContype(){
    return contype;
}


public void setChildFilter(ReportFilter childFilter){
    this.childFilter = childFilter;
}


public String getEndvalue(){
    return endvalue;
}


public String getFkfieldid(){
    return fkfieldid;
}


public void setTableid(String tableid){
    this.tableid = tableid;
}


public void setContype(String contype){
    this.contype = contype;
}


public String getTableid(){
    return tableid;
}


public void setLevel(CubeLevel level){
    this.level = level;
}


public String getValuefiltertype(){
    return valuefiltertype;
}


public void setParentValue(String parentValue){
    this.parentValue = parentValue;
}


public String getFiltertype(){
    return filtertype;
}


public String getUserdefvalue(){
    return userdefvalue;
}


public void setChild(boolean child){
    this.child = child;
}


public void setRequeststartvalue(String requeststartvalue){
    this.requeststartvalue = requeststartvalue;
}


public String getNoformatvalue(){
    return noformatvalue;
}


public String getCode(){
    return code != null ? code : id;
}


public void setMustvalue(String mustvalue){
    this.mustvalue = mustvalue;
}


public String getModelid(){
    return modelid;
}


@Transient
public String getRequestendvalue(){
    return requestendvalue != null && requestendvalue.length() > 0 ? requestendvalue : null;
}


public void setFilterfieldid(String filterfieldid){
    this.filterfieldid = filterfieldid;
}


public void setModelid(String modelid){
    this.modelid = modelid;
}


public void setFormatstr(String formatstr){
    this.formatstr = formatstr;
}


public String getDataname(){
    return dataname;
}


public void setFkfieldid(String fkfieldid){
    this.fkfieldid = fkfieldid;
}


public void setCode(String code){
    this.code = code;
}


public void setValuestr(String valuestr){
    this.valuestr = valuestr;
}


@Transient
public Template templet(){
    return UKTools.getTemplate(this.filtertemplet);
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public String getFieldid(){
    return fieldid;
}


public String getDefaultvalue(){
    return defaultvalue != null ? defaultvalue : "";
}


public String getComparetype(){
    return comparetype != null ? comparetype : "";
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public void setFieldid(String fieldid){
    this.fieldid = fieldid;
}


public void setFiltertemplet(String filtertemplet){
    this.filtertemplet = filtertemplet;
}


public void setName(String name){
    this.name = name;
}


public void setRequest(boolean request){
    this.request = request;
}


public void setContent(String content){
    this.content = content;
}


public void setFiltersuffix(String filtersuffix){
    this.filtersuffix = filtersuffix;
}


public String getFilterfieldid(){
    return filterfieldid;
}


public String getGroupids(){
    return groupids;
}


public String getFormatstr(){
    return this.formatstr;
}


public void setFilterprefix(String filterprefix){
    this.filterprefix = filterprefix;
}


public void setFktableid(String fktableid){
    this.fktableid = fktableid;
}


public void setValuecompare(String valuecompare){
    this.valuecompare = valuecompare;
}


public Date getCreatetime(){
    return createtime;
}


public void setFiltertype(String filtertype){
    this.filtertype = filtertype;
}


public void setDiccode(String diccode){
    this.diccode = diccode;
}


public void setModeltype(String modeltype){
    this.modeltype = modeltype;
}


public String getValuestr(){
    return valuestr;
}


public void setTableproperty(TableProperties tableproperty){
    this.tableproperty = tableproperty;
}


@Transient
public String getRequeststartvalue(){
    return requeststartvalue != null && requeststartvalue.length() > 0 ? requeststartvalue : null;
}


public void setEndvalue(String endvalue){
    this.endvalue = endvalue;
}


@Transient
public String getParentValue(){
    return parentValue;
}


@Transient
public String getRequestvalue(){
    return requestvalue;
}


public String getKeyfield(){
    return keyfield;
}


public void setComparetype(String comparetype){
    this.comparetype = comparetype;
}


public void setMeasureid(String measureid){
    this.measureid = measureid;
}


public void setConvalue(String convalue){
    this.convalue = convalue;
}


@Transient
public CubeLevel getLevel(){
    return level;
}


public String getFktableid(){
    return fktableid;
}


public String getDataid(){
    return dataid;
}


public String getValuefield(){
    return valuefield;
}


public String getFuntype(){
    return funtype;
}


public void setReportData(ReportData reportData){
    this.reportData = reportData;
}


public int getSortindex(){
    return sortindex;
}


public void setNoformatvalue(String noformatvalue){
    this.noformatvalue = noformatvalue;
}


public String getName(){
    return name;
}


public String getConvalue(){
    return convalue;
}


public void setRequestendvalue(String requestendvalue){
    this.requestendvalue = requestendvalue;
}


public String getDefaultvaluerule(){
    return defaultvaluerule;
}


public String getModeltype(){
    return modeltype;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "tableproperty")
@NotFound(action = NotFoundAction.IGNORE)
public TableProperties getTableproperty(){
    return tableproperty;
}


public void setStartvalue(String startvalue){
    this.startvalue = startvalue;
}


public void setDefaultvaluerule(String defaultvaluerule){
    this.defaultvaluerule = defaultvaluerule;
}


public void setFuntype(String funtype){
    this.funtype = funtype;
}


public String getTitle(){
    return title;
}


public void setParentFilter(ReportFilter parentFilter){
    this.parentFilter = parentFilter;
}


public void setId(String id){
    this.id = id;
}


public void setGroupids(String groupids){
    this.groupids = groupids;
}


@Transient
public boolean isChild(){
    return child;
}


public String getReportid(){
    return reportid;
}


@Transient
public ReportData getReportData(){
    return reportData;
}


public String getCubeid(){
    return cubeid;
}


public void setValuefield(String valuefield){
    this.valuefield = valuefield;
}


public void setTitle(String title){
    this.title = title;
}


public void setValuefiltertype(String valuefiltertype){
    this.valuefiltertype = valuefiltertype;
}


public void setKeyfield(String keyfield){
    this.keyfield = keyfield;
}


public String getDimid(){
    return dimid;
}


public void setDimid(String dimid){
    this.dimid = dimid;
}


public String getDiccode(){
    return diccode;
}


public String getFiltertemplet(){
    return filtertemplet;
}


public String getMeasureid(){
    return measureid;
}


@Transient
public boolean isRequest(){
    return request;
}


public String getContent(){
    return content;
}


public void setCubeid(String cubeid){
    this.cubeid = cubeid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setReportid(String reportid){
    this.reportid = reportid;
}


public String getMustvalue(){
    return mustvalue;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setQueryText(String queryText){
    this.queryText = queryText;
}


public void setRequestvalue(String requestvalue){
    this.requestvalue = requestvalue;
}


public void setCascadeid(String cascadeid){
    this.cascadeid = cascadeid;
}


@Transient
public String getQueryText(){
    return queryText;
}


public void setDefaultvalue(String defaultvalue){
    this.defaultvalue = defaultvalue;
}


public boolean isIsdic(){
    return isdic;
}


public String getStartvalue(){
    return startvalue;
}


@Transient
public ReportFilter getChildFilter(){
    return childFilter;
}


public void setDataname(String dataname){
    this.dataname = dataname;
}


public String getValuecompare(){
    return valuecompare;
}


public String getFilterprefix(){
    return filterprefix;
}


public void setUserdefvalue(String userdefvalue){
    this.userdefvalue = userdefvalue;
}


public String getOrgi(){
    return orgi;
}


public void setIsdic(boolean isdic){
    this.isdic = isdic;
}


public String getFiltersuffix(){
    return filtersuffix;
}


}