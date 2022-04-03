package com.ukefu.util.bi.model;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.ukefu.util.UKTools;
public class Level {

 private  long serialVersionUID;

 private  String name;

 private  String formatName;

 private  String nameValue;

 private  Level parent;

 private  String rename;

 private  String code;

 private  int depth;

 private  Object value;

 private  String leveltype;

 private  int colspan;

 private  int rowspan;

 private  String dimname;

 private  List<Level> childeren;

 private  List<List<Level>> title;

 private  boolean total;

 private  boolean first;

 private  int index;

 private  String level;

 private  String url;

 private  String target;

 private  String description;

 private  String model;

 private  List<ValueData> valueData;

 private  List<FirstTitle> firstTitle;

public Level(String name, String nameValue, String leveltype, int rowspan, int colspan, List<ValueData> valueData, boolean total, boolean first) {
    this.name = name;
    this.leveltype = leveltype;
    this.rowspan = rowspan;
    this.colspan = colspan;
    this.valueData = valueData;
    this.total = total;
    this.first = first;
    this.nameValue = nameValue;
}public Level(String name, String nameValue, String leveltype, int rowspan, int colspan, List<ValueData> valueData, boolean total, boolean first, int depth) {
    this.name = name;
    this.leveltype = leveltype;
    this.rowspan = rowspan;
    this.colspan = colspan;
    this.valueData = valueData;
    this.total = total;
    this.first = first;
    this.nameValue = nameValue;
    this.depth = depth;
}public Level(String name, String leveltype, Level parent, int depth) {
    this(name, leveltype, parent, depth, null);
}public Level(String name, String leveltype, Level parent, int depth, int index) {
    this(name, leveltype, parent, depth, null);
    this.index = index;
}public Level(String name, String leveltype, Level parent, int depth, String code) {
    this.name = name;
    this.nameValue = name;
    this.leveltype = leveltype;
    this.parent = parent;
    this.depth = depth;
    this.code = code;
}
public List<?> getChartTitle(){
    return this.getTitle() != null && this.getTitle().size() > 1 ? this.getTitle().get(this.getTitle().size() - 2) : this.getTitle().size() > 0 ? this.getTitle().get(0) : null;
}


public String getNameValue(){
    return nameValue;
}


public String getName(){
    return name;
}


public int getIndex(){
    return index;
}


public void setFormatName(String formatName){
    this.formatName = formatName;
}


public void setLevel(String level){
    this.level = level;
}


public boolean isFirst(){
    return first;
}


public String getFormatName(){
    return formatName != null ? formatName : this.name;
}


public String getTarget(){
    return target;
}


public void setRowspan(int rowspan){
    this.rowspan = rowspan;
}


public List<List<Level>> getTitle(){
    return title;
}


public void setFirst(boolean first){
    this.first = first;
}


public String getCode(){
    return code;
}


public void init(){
    title = new ArrayList<List<Level>>();
    title.add(new ArrayList<Level>());
    for (Level level : childeren) {
        title.get(0).add(level);
    }
    for (int i = 0; i < depth; i++) {
        List<Level> levelList = title.get(i);
        List<Level> next = new ArrayList<Level>();
        for (Level lv : levelList) {
            if (lv.getChilderen() != null) {
                for (Level tp : lv.getChilderen()) {
                    next.add(tp);
                }
            }
        }
        title.add(next);
    }
    if (leveltype != null && leveltype.equals("row")) {
        for (List<Level> tl : title) {
            for (int inx = 0; inx < tl.size(); inx++) {
                Level lv = tl.get(inx);
                if (lv != null) {
                    for (int i = 1; lv != null && i < lv.getRowspan(); i++) {
                        tl.add(inx + 1, null);
                    }
                }
            }
        }
    }
}


public void setNameValue(String nameValue){
    this.nameValue = nameValue;
}


public String getModel(){
    return model;
}


public void setFirstTitle(List<FirstTitle> firstTitle){
    this.firstTitle = firstTitle;
}


public void setCode(String code){
    this.code = code;
}


public void setTitle(List<List<Level>> title){
    this.title = title;
}


public void setUrl(String url){
    this.url = url;
}


public String getCubeLevel(String dimname){
    Level temp = this.parent;
    StringBuffer strb = new StringBuffer();
    while (temp != null && !temp.getName().equals("root")) {
        if (strb.length() > 0) {
            strb.insert(0, ".");
        }
        strb.insert(0, "]").insert(0, temp.getName()).insert(0, "[");
        temp = temp.parent;
    }
    if (!StringUtils.isBlank(dimname) && strb.length() > 0) {
        strb.insert(0, "].").insert(0, dimname).insert(0, "[");
    }
    return strb.toString();
}


public String getUrl(){
    return url;
}


public List<Level> getChilderen(){
    return childeren;
}


public void setIndex(int index){
    this.index = index;
}


public void setModel(String model){
    Level parent = this.parent;
    if (parent != null) {
        parent.setModel(model);
        while ((parent = parent.getParent()) != null) {
            parent.setModel(model);
        }
    }
    this.model = model;
}


public void setName(String name){
    this.name = name;
}


public void setTotal(boolean total){
    this.total = total;
}


public String getDimname(){
    return dimname;
}


public int getDepth(){
    return depth;
}


public boolean isTotal(){
    return total;
}


public List<ValueData> getValueData(){
    return valueData;
}


public void setDescription(String description){
    this.description = description;
}


public String getLeveltype(){
    return leveltype;
}


public String getDescription(){
    return description;
}


public void setColspan(int colspan){
    this.colspan = colspan;
}


public String getRename(){
    return rename;
}


public void setRename(String rename){
    this.rename = rename;
}


public void setTarget(String target){
    this.target = target;
}


public void setParent(Level parent){
    this.parent = parent;
}


public void setDepth(int depth){
    this.depth = depth;
}


public void setDimname(String dimname){
    this.dimname = dimname;
}


public Level getParent(){
    return parent;
}


public void setLeveltype(String leveltype){
    this.leveltype = leveltype;
}


public void setChilderen(List<Level> childeren){
    this.childeren = childeren;
}


public int getRowspan(){
    return rowspan;
}


public void setValueData(List<ValueData> valueData){
    this.valueData = valueData;
}


public Object getValue(){
    return value;
}


public String getLevel(){
    return level;
}


public int getColspan(){
    return colspan;
}


public String getDataid(){
    return UKTools.md5(this.name != null ? this.name : "");
}


public List<FirstTitle> getFirstTitle(){
    return firstTitle;
}


public void setValue(Object value){
    this.value = value;
}


public String toString(){
    return name;
}


}