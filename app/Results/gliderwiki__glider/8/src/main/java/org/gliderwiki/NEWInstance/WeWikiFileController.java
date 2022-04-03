package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeWikiFileController {

 private WeWikiFile wewikifile;

 private WeWikiFile wewikifile;


@PutMapping
("/setWe_wiki_revision")
public void setWe_wiki_revision(@RequestParam(name = "we_wiki_revision") Integer we_wiki_revision){
wewikifile.setWe_wiki_revision(we_wiki_revision);
}


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wewikifile.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_file_idx")
public void setWe_file_idx(@RequestParam(name = "we_file_idx") Integer we_file_idx){
wewikifile.setWe_file_idx(we_file_idx);
}


}