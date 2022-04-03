package com.lingxiang2014.DTO;
 import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.StringUtils;
import org.dom4j.io.SAXReader;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Similarity;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.core.io.ClassPathResource;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKSimilarity;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.util.FreemarkerUtils;
import freemarker.template.TemplateException;
public class Article extends BaseEntity{

 private  long serialVersionUID;

 public  String HITS_CACHE_NAME;

 public  int HITS_CACHE_INTERVAL;

 private  int PAGE_CONTENT_LENGTH;

 private  String PAGE_BREAK_SEPARATOR;

 private  Pattern PARAGRAPH_SEPARATOR_PATTERN;

 private  String staticPath;

 private  String title;

 private  String author;

 private  String content;

 private  String seoTitle;

 private  String seoKeywords;

 private  String seoDescription;

 private  Boolean isPublication;

 private  Boolean isTop;

 private  Long hits;

 private  Integer pageNumber;

 private  ArticleCategory articleCategory;

 private  Set<Tag> tags;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public void setSeoKeywords(String seoKeywords){
    if (seoKeywords != null) {
        seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
    }
    this.seoKeywords = seoKeywords;
}


@Field(store = Store.YES, index = Index.NO)
@Length(max = 200)
public String getAuthor(){
    return author;
}


@Length(max = 200)
public String getSeoDescription(){
    return seoDescription;
}


@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
@Lob
public String getContent(){
    if (pageNumber != null) {
        String[] pageContents = getPageContents();
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageNumber > pageContents.length) {
            pageNumber = pageContents.length;
        }
        return pageContents[pageNumber - 1];
    } else {
        return content;
    }
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "lx_article_tag")
@OrderBy("order asc")
public Set<Tag> getTags(){
    return tags;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NotNull
@Column(nullable = false)
public Boolean getIsTop(){
    return isTop;
}


@Transient
public int getTotalPages(){
    return getPageContents().length;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false)
public ArticleCategory getArticleCategory(){
    return articleCategory;
}


@Transient
public Integer getPageNumber(){
    return pageNumber;
}


@Field(store = Store.YES, index = Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getTitle(){
    return title;
}


@Transient
public String[] getPageContents(){
    if (StringUtils.isEmpty(content)) {
        return new String[] { "" };
    }
    if (content.contains(PAGE_BREAK_SEPARATOR)) {
        return content.split(PAGE_BREAK_SEPARATOR);
    } else {
        List<String> pageContents = new ArrayList<String>();
        Document document = Jsoup.parse(content);
        List<Node> children = document.body().childNodes();
        if (children != null) {
            int textLength = 0;
            StringBuffer html = new StringBuffer();
            for (Node node : children) {
                if (node instanceof Element) {
                    Element element = (Element) node;
                    html.append(element.outerHtml());
                    textLength += element.text().length();
                    if (textLength >= PAGE_CONTENT_LENGTH) {
                        pageContents.add(html.toString());
                        textLength = 0;
                        html.setLength(0);
                    }
                } else if (node instanceof TextNode) {
                    TextNode textNode = (TextNode) node;
                    String text = textNode.text();
                    String[] contents = PARAGRAPH_SEPARATOR_PATTERN.split(text);
                    Matcher matcher = PARAGRAPH_SEPARATOR_PATTERN.matcher(text);
                    for (String content : contents) {
                        if (matcher.find()) {
                            content += matcher.group();
                        }
                        html.append(content);
                        textLength += content.length();
                        if (textLength >= PAGE_CONTENT_LENGTH) {
                            pageContents.add(html.toString());
                            textLength = 0;
                            html.setLength(0);
                        }
                    }
                }
            }
            String pageContent = html.toString();
            if (StringUtils.isNotEmpty(pageContent)) {
                pageContents.add(pageContent);
            }
        }
        return pageContents.toArray(new String[pageContents.size()]);
    }
}


@Transient
public String getPath(){
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("id", getId());
    model.put("createDate", getCreateDate());
    model.put("modifyDate", getModifyDate());
    model.put("title", getTitle());
    model.put("seoTitle", getSeoTitle());
    model.put("seoKeywords", getSeoKeywords());
    model.put("seoDescription", getSeoDescription());
    model.put("pageNumber", getPageNumber());
    model.put("articleCategory", getArticleCategory());
    try {
        return FreemarkerUtils.process(staticPath, model);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    }
    return null;
}


@Length(max = 200)
public String getSeoTitle(){
    return seoTitle;
}


@Field(store = Store.YES, index = Index.UN_TOKENIZED)
@NotNull
@Column(nullable = false)
public Boolean getIsPublication(){
    return isPublication;
}


@Transient
public String getText(){
    if (getContent() != null) {
        return Jsoup.parse(getContent()).text();
    }
    return null;
}


@Length(max = 200)
public String getSeoKeywords(){
    return seoKeywords;
}


@Column(nullable = false)
public Long getHits(){
    return hits;
}


public void setArticleCategory(ArticleCategory articleCategory){
    this.articleCategory = articleCategory;
}


@Transient
public void setPageNumber(Integer pageNumber){
    this.pageNumber = pageNumber;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPageNumber"))

.queryParam("pageNumber",pageNumber)
;
restTemplate.put(builder.toUriString(),null);
}


}