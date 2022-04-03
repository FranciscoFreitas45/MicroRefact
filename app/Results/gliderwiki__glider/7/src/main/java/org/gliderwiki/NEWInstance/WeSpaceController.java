package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WeSpaceController {

 private WeSpace wespace;

 private WeSpace wespace;


@PutMapping
("/setWe_use_yn")
public void setWe_use_yn(@RequestParam(name = "we_use_yn") String we_use_yn){
wespace.setWe_use_yn(we_use_yn);
}


@PutMapping
("/setWe_space_name")
public void setWe_space_name(@RequestParam(name = "we_space_name") String we_space_name){
wespace.setWe_space_name(we_space_name);
}


@GetMapping
("/isAllGroupViewPrivacy")
public boolean isAllGroupViewPrivacy(){
  return wespace.isAllGroupViewPrivacy();
}


@GetMapping
("/isAllGroupEditPrivacy")
public boolean isAllGroupEditPrivacy(){
  return wespace.isAllGroupEditPrivacy();
}


@PutMapping
("/setWe_upload_imgName")
public void setWe_upload_imgName(@RequestParam(name = "we_upload_imgName") String we_upload_imgName){
wespace.setWe_upload_imgName(we_upload_imgName);
}


@GetMapping
("/isUploadImgName")
public boolean isUploadImgName(){
  return wespace.isUploadImgName();
}


@GetMapping
("/doUpdate")
public boolean doUpdate(){
  return wespace.doUpdate();
}


@PutMapping
("/setWe_view_data")
public void setWe_view_data(@RequestParam(name = "we_view_data") String we_view_data){
wespace.setWe_view_data(we_view_data);
}


@PutMapping
("/setWe_edit_data")
public void setWe_edit_data(@RequestParam(name = "we_edit_data") String we_edit_data){
wespace.setWe_edit_data(we_edit_data);
}


@PutMapping
("/setWe_view_name")
public void setWe_view_name(@RequestParam(name = "we_view_name") String we_view_name){
wespace.setWe_view_name(we_view_name);
}


@PutMapping
("/setWe_edit_name")
public void setWe_edit_name(@RequestParam(name = "we_edit_name") String we_edit_name){
wespace.setWe_edit_name(we_edit_name);
}


@PutMapping
("/setWe_space_idx")
public void setWe_space_idx(@RequestParam(name = "we_space_idx") Integer we_space_idx){
wespace.setWe_space_idx(we_space_idx);
}


}