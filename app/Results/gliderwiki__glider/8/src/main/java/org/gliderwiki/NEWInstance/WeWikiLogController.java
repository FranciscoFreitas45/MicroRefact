package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeWikiLogController {

 private WeWikiLog wewikilog;

 private WeWikiLog wewikilog;


@PutMapping
("/setWe_wiki_revision")
public void setWe_wiki_revision(@RequestParam(name = "we_wiki_revision") Integer we_wiki_revision){
wewikilog.setWe_wiki_revision(we_wiki_revision);
}


@PutMapping
("/setWe_wiki_status")
public void setWe_wiki_status(@RequestParam(name = "we_wiki_status") String we_wiki_status){
wewikilog.setWe_wiki_status(we_wiki_status);
}


@PutMapping
("/setWe_user_idx")
public void setWe_user_idx(@RequestParam(name = "we_user_idx") Integer we_user_idx){
wewikilog.setWe_user_idx(we_user_idx);
}


@PutMapping
("/setWe_wiki_action_type")
public void setWe_wiki_action_type(@RequestParam(name = "we_wiki_action_type") String we_wiki_action_type){
wewikilog.setWe_wiki_action_type(we_wiki_action_type);
}


}