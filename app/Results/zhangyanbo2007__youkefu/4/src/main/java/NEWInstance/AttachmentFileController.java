package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentFileController {

 private AttachmentFile attachmentfile;

 private AttachmentFile attachmentfile;


@PutMapping
("/setOrgi")
public void setOrgi(@RequestParam(name = "orgi") String orgi){
attachmentfile.setOrgi(orgi);
}


@PutMapping
("/setOrgan")
public void setOrgan(@RequestParam(name = "organ") String organ){
attachmentfile.setOrgan(organ);
}


@PutMapping
("/setModel")
public void setModel(@RequestParam(name = "model") String model){
attachmentfile.setModel(model);
}


@PutMapping
("/setFilelength")
public void setFilelength(@RequestParam(name = "filelength") int filelength){
attachmentfile.setFilelength(filelength);
}


@PutMapping
("/setFiletype")
public void setFiletype(@RequestParam(name = "filetype") String filetype){
attachmentfile.setFiletype(filetype);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
attachmentfile.setTitle(title);
}


@PutMapping
("/setImage")
public void setImage(@RequestParam(name = "image") boolean image){
attachmentfile.setImage(image);
}


@PutMapping
("/setFileid")
public void setFileid(@RequestParam(name = "fileid") String fileid){
attachmentfile.setFileid(fileid);
}


@PutMapping
("/setDataid")
public void setDataid(@RequestParam(name = "dataid") String dataid){
attachmentfile.setDataid(dataid);
}


@PutMapping
("/setModelid")
public void setModelid(@RequestParam(name = "modelid") String modelid){
attachmentfile.setModelid(modelid);
}


}