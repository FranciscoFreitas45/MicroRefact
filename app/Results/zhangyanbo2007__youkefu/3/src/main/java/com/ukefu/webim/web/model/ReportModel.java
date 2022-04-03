package com.ukefu.webim.web.model;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
import com.ukefu.util.bi.ReportData;
@Entity
@Table(name = "uk_reportmodel")
@org.hibernate.annotations.Proxy(lazy = false)
public class ReportModel {

 private  long serialVersionUID;

 private  String id;

 private  String posx;

 private  String posy;

 private  String poswidth;

 private  String posheight;

 private  String name;

 private  String code;

 private  String reportid;

 private  String modeltype;

 private  int sortindex;

 private  String stylestr;

 private  String labeltext;

 private  String cssclassname;

 private  String mposleft;

 private  String mpostop;

 private  String title;

 private  boolean exchangerw;

 private  String publishedcubeid;

 private  String rowdimension;

 private  String coldimension;

 private  String measure;

 private  String dstype;

 private  String dbtype;

 private  String orgi;

 private  String objectid;

 private  Date createtime;

 private  String filterstr;

 private  String sortstr;

 private  String viewtype;

 private  String chartemplet;

 private  String chartype;

 private  String chartdatatype;

 private  String chart3d;

 private  String xtitle;

 private  String ytitle;

 private  String charttitle;

 private  String displayborder;

 private  String bordercolor;

 private  String displaydesc;

 private  String formdisplay;

 private  String labelstyle;

 private  String formname;

 private  String defaultvalue;

 private  String querytext;

 private  String tempquey;

 private  boolean displaytitle;

 private  boolean clearzero;

 private  String titlestr;

 private  String width;

 private  String height;

 private  String widthunit;

 private  String heightunit;

 private  String defheight;

 private  String defwidth;

 private  String neckwidth;

 private  String neckheight;

 private  String extparam;

 private  String marginright;

 private  String colorstr;

 private  String start;

 private  String end;

 private  String rowformatstr;

 private  String colformatstr;

 private  String publishtype;

 private  String editview;

 private  boolean expandbtm;

 private  boolean expandrgt;

 private  String curtab;

 private  String hiddencolstr;

 private  String eventstr;

 private  String dsmodel;

 private  String html;

 private  String mid;

 private  String parentid;

 private  String templetid;

 private  int colspan;

 private  int colindex;

 private  String sqldialect;

 private  int pagesize;

 private  String isloadfulldata;

 private  boolean isexport;

 private  boolean selectdata;

 private  String exporttitle;

 private  int colsize;

 private  String sorttype;

 private  String sortname;

 private  List<ColumnProperties> properties;

 private  List<ColumnProperties> colproperties;

 private  List<ColumnProperties> measures;

 private  List<ReportFilter> filters;

 private  List<DrillDown> drilldown;

 private  ReportData reportData;

 private  ChartProperties chartProperties;

 private  String chartcontent;


public boolean isIsexport(){
    return isexport;
}


public String getCssclassname(){
    return cssclassname;
}


public void setTempletid(String templetid){
    this.templetid = templetid;
}


public String getHeightunit(){
    return heightunit;
}


public void setSortname(String sortname){
    this.sortname = sortname;
}


public void setProperties(List<ColumnProperties> properties){
    this.properties = properties;
}


public void setExpandrgt(boolean expandrgt){
    this.expandrgt = expandrgt;
}


public String getHiddencolstr(){
    return hiddencolstr;
}


public void setViewtype(String viewtype){
    this.viewtype = viewtype;
}


public void setFormname(String formname){
    this.formname = formname;
}


public void setMeasures(List<ColumnProperties> measures){
    this.measures = measures;
}


public void setSelectdata(boolean selectdata){
    this.selectdata = selectdata;
}


public void setRowdimension(String rowdimension){
    this.rowdimension = rowdimension;
}


public String getDisplaydesc(){
    return displaydesc;
}


public void setExtparam(String extparam){
    this.extparam = extparam;
}


public void setLabelstyle(String labelstyle){
    this.labelstyle = labelstyle;
}


public String getColformatstr(){
    return colformatstr;
}


public void setColindex(int colindex){
    this.colindex = colindex;
}


public String getNeckwidth(){
    return neckwidth;
}


@Transient
public List<ReportFilter> getFilters(){
    return filters;
}


public void setEnd(String end){
    this.end = end;
}


public String getCode(){
    return code;
}


public void setChartype(String chartype){
    this.chartype = chartype;
}


public void setClearzero(boolean clearzero){
    this.clearzero = clearzero;
}


public void setPublishedcubeid(String publishedcubeid){
    this.publishedcubeid = publishedcubeid;
}


public String getDefwidth(){
    return defwidth;
}


public void setCode(String code){
    this.code = code;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public Template templet(){
    return UKTools.getTemplate(this.templetid);
}


@Transient
public List<ColumnProperties> getProperties(){
    return properties;
}


public String getLabeltext(){
    return labeltext;
}


public boolean isDisplaytitle(){
    return displaytitle;
}


public String getDefaultvalue(){
    return defaultvalue;
}


public String getTitlestr(){
    return titlestr;
}


public int getColsize(){
    return colsize;
}


@Transient
public ChartProperties getChartProperties(){
    Base64 base64 = new Base64();
    try {
        return chartProperties != null ? chartProperties : (chartProperties = (this.chartcontent == null ? null : (ChartProperties) UKTools.toObject(base64.decode(this.chartcontent))));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return chartProperties;
}


@Column(name = "send")
public String getEnd(){
    return end;
}


public void setEditview(String editview){
    this.editview = editview;
}


public String getObjectid(){
    return objectid;
}


public String getBordercolor(){
    return bordercolor;
}


public boolean isExpandbtm(){
    return expandbtm;
}


public void setNeckheight(String neckheight){
    this.neckheight = neckheight;
}


public String getTempquey(){
    return tempquey;
}


public void setDbtype(String dbtype){
    this.dbtype = dbtype;
}


public void setDstype(String dstype){
    this.dstype = dstype;
}


public String getLabelstyle(){
    return labelstyle;
}


public String getColorstr(){
    return colorstr;
}


public void setName(String name){
    this.name = name;
}


public void setColproperties(List<ColumnProperties> colproperties){
    this.colproperties = colproperties;
}


public String getEditview(){
    return editview;
}


public void setXtitle(String xtitle){
    this.xtitle = xtitle;
}


public String getPoswidth(){
    return poswidth;
}


public String getDefheight(){
    return defheight;
}


public void setTitlestr(String titlestr){
    this.titlestr = titlestr;
}


public void setLabeltext(String labeltext){
    this.labeltext = labeltext;
}


public String getChartemplet(){
    return chartemplet;
}


public void setDefwidth(String defwidth){
    this.defwidth = defwidth;
}


public void setPagesize(int pagesize){
    this.pagesize = pagesize;
}


public String getChartdatatype(){
    return chartdatatype;
}


public String getWidth(){
    return width;
}


public void setFilterstr(String filterstr){
    this.filterstr = filterstr;
}


public String getMarginright(){
    return marginright;
}


public String getYtitle(){
    return ytitle;
}


public String getHeight(){
    return height;
}


public String getRowdimension(){
    return rowdimension;
}


public Date getCreatetime(){
    return createtime;
}


public void setModeltype(String modeltype){
    this.modeltype = modeltype;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setRowformatstr(String rowformatstr){
    this.rowformatstr = rowformatstr;
}


public String getParentid(){
    return parentid;
}


public String getPosheight(){
    return posheight;
}


public String getDisplayborder(){
    return displayborder;
}


public String getChart3d(){
    return chart3d;
}


public void setIsexport(boolean isexport){
    this.isexport = isexport;
}


public void setHeightunit(String heightunit){
    this.heightunit = heightunit;
}


public String getExtparam(){
    return extparam;
}


public void setChart3d(String chart3d){
    this.chart3d = chart3d;
}


public void setHtml(String html){
    this.html = html;
}


public void setColsize(int colsize){
    this.colsize = colsize;
}


public void setColorstr(String colorstr){
    this.colorstr = colorstr;
}


public void setPosx(String posx){
    this.posx = posx;
}


public void setPosy(String posy){
    this.posy = posy;
}


public String getNeckheight(){
    return neckheight;
}


public String getSortname(){
    return sortname;
}


public void setWidth(String width){
    this.width = width;
}


public void setDefheight(String defheight){
    this.defheight = defheight;
}


public void setMarginright(String marginright){
    this.marginright = marginright;
}


public void setFilters(List<ReportFilter> filters){
    this.filters = filters;
}


public void setChartemplet(String chartemplet){
    this.chartemplet = chartemplet;
}


public void setExchangerw(boolean exchangerw){
    this.exchangerw = exchangerw;
}


public String getDstype(){
    return dstype;
}


public void setReportData(ReportData reportData){
    this.reportData = reportData;
}


public int getSortindex(){
    return sortindex;
}


public String getChartype(){
    return chartype;
}


public void setDisplayborder(String displayborder){
    this.displayborder = displayborder;
}


public String getName(){
    return name;
}


public void setSortstr(String sortstr){
    this.sortstr = sortstr;
}


public String getFormdisplay(){
    return formdisplay;
}


@Column(name = "sstart")
public String getStart(){
    return start;
}


public void setMid(String mid){
    this.mid = mid;
}


public String getModeltype(){
    return modeltype;
}


public String getPublishedcubeid(){
    return publishedcubeid;
}


public void setFormdisplay(String formdisplay){
    this.formdisplay = formdisplay;
}


@Transient
public List<DrillDown> getDrilldown(){
    return drilldown;
}


@Transient
public String getChartPropertiesJson(){
    return UKTools.toJson(getChartProperties());
}


public void setPoswidth(String poswidth){
    this.poswidth = poswidth;
}


public String getMeasure(){
    return measure;
}


public String getTitle(){
    return title;
}


public void setStart(String start){
    this.start = start;
}


public void setId(String id){
    this.id = id;
}


public String getSqldialect(){
    return sqldialect;
}


public String getMid(){
    return mid;
}


public void setChartdatatype(String chartdatatype){
    this.chartdatatype = chartdatatype;
}


public String getPosx(){
    return posx;
}


public String getPosy(){
    return posy;
}


public void setPublishtype(String publishtype){
    this.publishtype = publishtype;
}


public String getMposleft(){
    return mposleft;
}


public String getHtml(){
    return html;
}


public void setIsloadfulldata(String isloadfulldata){
    this.isloadfulldata = isloadfulldata;
}


public String getReportid(){
    return reportid;
}


public String getEventstr(){
    return eventstr;
}


@Transient
public ReportData getReportData(){
    return reportData;
}


public int getPagesize(){
    return pagesize;
}


public String getStylestr(){
    return stylestr;
}


public String getXtitle(){
    return xtitle;
}


public void setDsmodel(String dsmodel){
    this.dsmodel = dsmodel;
}


public void setSqldialect(String sqldialect){
    this.sqldialect = sqldialect;
}


public void setDrilldown(List<DrillDown> drilldown){
    this.drilldown = drilldown;
}


public void setColdimension(String coldimension){
    this.coldimension = coldimension;
}


@Transient
public List<ColumnProperties> getColproperties(){
    return colproperties;
}


public void setTitle(String title){
    this.title = title;
}


public boolean isExchangerw(){
    return exchangerw;
}


public void setHeight(String height){
    this.height = height;
}


public void setQuerytext(String querytext){
    this.querytext = querytext;
}


public void setMeasure(String measure){
    this.measure = measure;
}


public void setWidthunit(String widthunit){
    this.widthunit = widthunit;
}


public void setCurtab(String curtab){
    this.curtab = curtab;
}


public String getSorttype(){
    return sorttype;
}


public boolean isExpandrgt(){
    return expandrgt;
}


public String getMpostop(){
    return mpostop;
}


public String getSortstr(){
    return sortstr;
}


public void setChartcontent(String chartcontent){
    this.chartcontent = chartcontent;
}


public void setPosheight(String posheight){
    this.posheight = posheight;
}


public int getColindex(){
    return colindex;
}


public void setCssclassname(String cssclassname){
    this.cssclassname = cssclassname;
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


public void setStylestr(String stylestr){
    this.stylestr = stylestr;
}


public void setCharttitle(String charttitle){
    this.charttitle = charttitle;
}


public String getChartcontent(){
    return chartcontent;
}


public String getIsloadfulldata(){
    return isloadfulldata;
}


public void setMposleft(String mposleft){
    this.mposleft = mposleft;
}


public boolean isSelectdata(){
    return selectdata;
}


public void setReportid(String reportid){
    this.reportid = reportid;
}


public String getPublishtype(){
    return publishtype;
}


public void setColspan(int colspan){
    this.colspan = colspan;
}


public void setColformatstr(String colformatstr){
    this.colformatstr = colformatstr;
}


public void setNeckwidth(String neckwidth){
    this.neckwidth = neckwidth;
}


public void setMpostop(String mpostop){
    this.mpostop = mpostop;
}


public String getColdimension(){
    return coldimension;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setYtitle(String ytitle){
    this.ytitle = ytitle;
}


public void setDisplaydesc(String displaydesc){
    this.displaydesc = displaydesc;
}


public void setExporttitle(String exporttitle){
    this.exporttitle = exporttitle;
}


public void setBordercolor(String bordercolor){
    this.bordercolor = bordercolor;
}


public void setTempquey(String tempquey){
    this.tempquey = tempquey;
}


public String getTempletid(){
    return templetid;
}


public String getCharttitle(){
    return charttitle;
}


public String getRowformatstr(){
    return rowformatstr;
}


public String getFormname(){
    return formname;
}


public String getViewtype(){
    return viewtype;
}


public boolean isClearzero(){
    return clearzero;
}


public String getFilterstr(){
    return filterstr;
}


public String getCurtab(){
    return curtab;
}


public void setDefaultvalue(String defaultvalue){
    this.defaultvalue = defaultvalue;
}


public String getDsmodel(){
    return dsmodel;
}


@Transient
public List<ColumnProperties> getMeasures(){
    return measures;
}


public void setObjectid(String objectid){
    this.objectid = objectid;
}


public String getExporttitle(){
    return exporttitle;
}


public int getColspan(){
    return colspan;
}


public void setExpandbtm(boolean expandbtm){
    this.expandbtm = expandbtm;
}


public void setSorttype(String sorttype){
    this.sorttype = sorttype;
}


public String getQuerytext(){
    return querytext;
}


public String getOrgi(){
    return orgi;
}


public String getDbtype(){
    return dbtype;
}


public void setDisplaytitle(boolean displaytitle){
    this.displaytitle = displaytitle;
}


public String getWidthunit(){
    return widthunit;
}


public void setHiddencolstr(String hiddencolstr){
    this.hiddencolstr = hiddencolstr;
}


public void setEventstr(String eventstr){
    this.eventstr = eventstr;
}


}