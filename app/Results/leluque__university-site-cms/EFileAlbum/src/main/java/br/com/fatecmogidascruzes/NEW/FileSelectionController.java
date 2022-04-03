package br.com.fatecmogidascruzes.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.file.File;
@RestController
@CrossOrigin
public class FileSelectionController {

@Autowired
 private FileSelectionService fileselectionservice;


@PutMapping
("/Selection/{id}/File/addDocument")
public void addDocument(@PathVariable(name="id") Long id,@RequestBody File file){
fileselectionservice.addDocument(id,file);
}


}