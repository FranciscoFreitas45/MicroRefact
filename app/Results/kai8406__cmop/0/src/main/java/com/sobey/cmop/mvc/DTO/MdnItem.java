package com.sobey.cmop.mvc.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class MdnItem {

 private  Integer id;

 private  Apply apply;

 private  String identifier;

 private  String coverArea;

 private  String coverIsp;

 private  String bandwidth;

 private  Set<MdnVodItem> mdnVodItems;

 private  Set<MdnLiveItem> mdnLiveItems;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://9";

// Constructors
/**
 * default constructor
 */
public MdnItem() {
}/**
 * minimal constructor
 */
public MdnItem(String identifier, String coverArea, String coverIsp, String bandwidth) {
    this.identifier = identifier;
    this.coverArea = coverArea;
    this.coverIsp = coverIsp;
    this.bandwidth = bandwidth;
}/**
 * full constructor
 */
public MdnItem(Apply apply, String identifier, String coverArea, String coverIsp, String bandwidth, Set<MdnVodItem> mdnVodItems, Set<MdnLiveItem> mdnLiveItems) {
    this.apply = apply;
    this.identifier = identifier;
    this.coverArea = coverArea;
    this.coverIsp = coverIsp;
    this.bandwidth = bandwidth;
    this.mdnVodItems = mdnVodItems;
    this.mdnLiveItems = mdnLiveItems;
}
@Column(name = "cover_isp", nullable = false, length = 45)
public String getCoverIsp(){
    return this.coverIsp;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mdnItem")
public Set<MdnLiveItem> getMdnLiveItems(){
    return this.mdnLiveItems;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "cover_area", nullable = false, length = 45)
public String getCoverArea(){
    return this.coverArea;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mdnItem")
public Set<MdnVodItem> getMdnVodItems(){
    return this.mdnVodItems;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "apply_id")
public Apply getApply(){
    return this.apply;
}


@Column(name = "identifier", nullable = false, length = 45)
public String getIdentifier(){
    return this.identifier;
}


@Column(name = "bandwidth", nullable = false, length = 45)
public String getBandwidth(){
    return bandwidth;
}


public void setCoverArea(String coverArea){
    this.coverArea = coverArea;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCoverArea"))

.queryParam("coverArea",coverArea)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCoverIsp(String coverIsp){
    this.coverIsp = coverIsp;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCoverIsp"))

.queryParam("coverIsp",coverIsp)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBandwidth(String bandwidth){
    this.bandwidth = bandwidth;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBandwidth"))

.queryParam("bandwidth",bandwidth)
;
restTemplate.put(builder.toUriString(),null);
}


}