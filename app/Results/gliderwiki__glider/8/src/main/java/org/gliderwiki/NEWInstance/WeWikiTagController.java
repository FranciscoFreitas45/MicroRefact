package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeWikiTagController {

 private WeWikiTag wewikitag;

 private WeWikiTag wewikitag;


@PutMapping
("/setWe_wiki_revision")
public void setWe_wiki_revision(@RequestParam(name = "we_wiki_revision") Integer we_wiki_revision){
wewikitag.setWe_wiki_revision(we_wiki_revision);
}


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wewikitag.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_wiki_tag_idx")
public void setWe_wiki_tag_idx(@RequestParam(name = "we_wiki_tag_idx") Integer we_wiki_tag_idx){
wewikitag.setWe_wiki_tag_idx(we_wiki_tag_idx);
}


@PutMapping
("/setWe_wiki_idx")
public void setWe_wiki_idx(@RequestParam(name = "we_wiki_idx") Integer we_wiki_idx){
wewikitag.setWe_wiki_idx(we_wiki_idx);
}


@PutMapping
("/setWe_ins_date")
public void setWe_ins_date(@RequestParam(name = "we_ins_date") String we_ins_date){
wewikitag.setWe_ins_date(we_ins_date);
}


@PutMapping
("/setWe_tag")
public void setWe_tag(@RequestParam(name = "we_tag") String we_tag){
wewikitag.setWe_tag(we_tag);
}


}