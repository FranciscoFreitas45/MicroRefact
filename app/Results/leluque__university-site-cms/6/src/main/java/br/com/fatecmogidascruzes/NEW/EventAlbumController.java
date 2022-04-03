package br.com.fatecmogidascruzes.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.event.Event;
@RestController
@CrossOrigin
public class EventAlbumController {

@Autowired
 private EventAlbumService eventalbumservice;


}