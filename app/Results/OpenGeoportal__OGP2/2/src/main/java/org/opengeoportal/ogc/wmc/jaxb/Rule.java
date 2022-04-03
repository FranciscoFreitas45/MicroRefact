package org.opengeoportal.ogc.wmc.jaxb;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.opengeoportal.Interface.LegendGraphic;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "title", "_abstract", "legendGraphic", "filter", "elseFilter", "minScaleDenominator", "maxScaleDenominator", "symbolizer" })
@XmlRootElement(name = "Rule")
public class Rule {

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "Title")
 protected  String title;

@XmlElement(name = "Abstract")
 protected  String _abstract;

@XmlElement(name = "LegendGraphic")
 protected  LegendGraphic legendGraphic;

@XmlElement(name = "Filter", namespace = "http://www.opengis.net/ogc")
 protected  FilterType filter;

@XmlElement(name = "ElseFilter")
 protected  ElseFilter elseFilter;

@XmlElement(name = "MinScaleDenominator")
 protected  Double minScaleDenominator;

@XmlElement(name = "MaxScaleDenominator")
 protected  Double maxScaleDenominator;

@XmlElementRef(name = "Symbolizer", namespace = "http://www.opengis.net/sld", type = JAXBElement.class)
 protected  List<JAXBElement<? extends SymbolizerType>> symbolizer;


public void setName(String value){
    this.name = value;
}


public LegendGraphic getLegendGraphic(){
    return legendGraphic;
}


public String getName(){
    return name;
}


public ElseFilter getElseFilter(){
    return elseFilter;
}


public void setLegendGraphic(LegendGraphic value){
    this.legendGraphic = value;
}


public void setTitle(String value){
    this.title = value;
}


public Double getMaxScaleDenominator(){
    return maxScaleDenominator;
}


public List<JAXBElement<? extends SymbolizerType>> getSymbolizer(){
    if (symbolizer == null) {
        symbolizer = new ArrayList<JAXBElement<? extends SymbolizerType>>();
    }
    return this.symbolizer;
}


public void setFilter(FilterType value){
    this.filter = value;
}


public FilterType getFilter(){
    return filter;
}


public String getTitle(){
    return title;
}


public void setElseFilter(ElseFilter value){
    this.elseFilter = value;
}


public Double getMinScaleDenominator(){
    return minScaleDenominator;
}


public void setAbstract(String value){
    this._abstract = value;
}


public void setMaxScaleDenominator(Double value){
    this.maxScaleDenominator = value;
}


public String getAbstract(){
    return _abstract;
}


public void setMinScaleDenominator(Double value){
    this.minScaleDenominator = value;
}


}