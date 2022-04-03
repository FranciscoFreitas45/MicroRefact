package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComboBoxController {

 private ComboBox combobox;

 private ComboBox combobox;


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") String id){
combobox.setId(id);
}


@PutMapping
("/setText")
public void setText(@RequestParam(name = "text") String text){
combobox.setText(text);
}


@PutMapping
("/setSelected")
public void setSelected(@RequestParam(name = "selected") boolean selected){
combobox.setSelected(selected);
}


}