package com.ec.survey.model.survey;
 import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.owasp.esapi.errors.ValidationException;
import javax.persistence;
@Entity
@DiscriminatorValue("IMAGE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Image extends Question{

 private  long serialVersionUID;

 private  Integer scale;

 private  String align;

 private  String url;

 private  Integer width;

 private  String longdesc;

 private  String filename;

public Image(String text, String uid) {
    setTitle(text);
    setUniqueId(uid);
}public Image() {
}
@Transient
public String titleOrFilename(){
    if (getTitle() != null && getTitle().length() > 0)
        return getTitle();
    return url.substring(url.lastIndexOf('/') + 1);
}


public void setScale(Integer scale){
    this.scale = scale;
}


@Column(name = "ALIGN")
public String getAlign(){
    return align;
}


@Column(name = "IM_WIDTH")
public Integer getWidth(){
    return width;
}


public void setWidth(Integer width){
    this.width = width;
}


@Override
public boolean differsFrom(Element element){
    if (basicDiffersFrom(element))
        return true;
    if (!(element instanceof Image))
        return true;
    Image image = (Image) element;
    if (align != null && !align.equals(image.align))
        return true;
    if (scale != null && !scale.equals(image.scale))
        return true;
    if (url != null && !url.equals(image.url))
        return true;
    return (width != null && !width.equals(image.width));
}


public void setUrl(String url){
    this.url = url;
}


public void setFilename(String filename){
    this.filename = filename;
}


@Column(name = "URL")
public String getUrl(){
    return url;
}


@Transient
public String getFilename(){
    return filename;
}


@Column(name = "SCALE")
public Integer getScale(){
    return scale;
}


public void setAlign(String align){
    this.align = align;
}


public void setLongdesc(String longdesc){
    this.longdesc = longdesc;
}


public Image copy(String fileDir){
    Image copy = new Image();
    baseCopy(copy);
    copy.align = align;
    copy.scale = scale;
    copy.longdesc = longdesc;
    copy.url = url;
    copy.width = width;
    return copy;
}


@Column(name = "LONGDESC")
public String getLongdesc(){
    return longdesc;
}


}