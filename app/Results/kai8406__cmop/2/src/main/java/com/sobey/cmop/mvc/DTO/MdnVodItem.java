package com.sobey.cmop.mvc.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class MdnVodItem {

 private  Integer id;

 private  MdnItem mdnItem;

 private  String vodDomain;

 private  String vodProtocol;

 private  String sourceStreamerUrl;

 private  String sourceOutBandwidth;

// Constructors
/**
 * default constructor
 */
public MdnVodItem() {
}/**
 * full constructor
 */
public MdnVodItem(MdnItem mdnItem, String vodDomain, String vodProtocol, String sourceStreamerUrl, String sourceOutBandwidth) {
    this.mdnItem = mdnItem;
    this.vodDomain = vodDomain;
    this.vodProtocol = vodProtocol;
    this.sourceStreamerUrl = sourceStreamerUrl;
    this.sourceOutBandwidth = sourceOutBandwidth;
}
@Column(name = "vod_protocol", nullable = false, length = 45)
public String getVodProtocol(){
    return this.vodProtocol;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@Column(name = "vod_domain", nullable = false, length = 100)
public String getVodDomain(){
    return this.vodDomain;
}


@Column(name = "source_streamer_url", nullable = false, length = 100)
public String getSourceStreamerUrl(){
    return this.sourceStreamerUrl;
}


@Column(name = "source_out_bandwidth", nullable = false, length = 45)
public String getSourceOutBandwidth(){
    return this.sourceOutBandwidth;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "mdn_item_id", nullable = false)
public MdnItem getMdnItem(){
    return this.mdnItem;
}


}