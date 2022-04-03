package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QualityConfigController {

 private QualityConfig qualityconfig;

 private QualityConfig qualityconfig;


@PutMapping
("/setCreatetime")
public void setCreatetime(@RequestParam(name = "createtime") Date createtime){
qualityconfig.setCreatetime(createtime);
}


@PutMapping
("/setArchivetime")
public void setArchivetime(@RequestParam(name = "archivetime") int archivetime){
qualityconfig.setArchivetime(archivetime);
}


@PutMapping
("/setAplarchivetime")
public void setAplarchivetime(@RequestParam(name = "aplarchivetime") int aplarchivetime){
qualityconfig.setAplarchivetime(aplarchivetime);
}


}