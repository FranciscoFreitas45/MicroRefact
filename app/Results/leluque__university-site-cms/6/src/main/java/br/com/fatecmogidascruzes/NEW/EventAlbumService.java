package br.com.fatecmogidascruzes.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.event.data.EventDAO;
import br.com.fatecmogidascruzes.event.Event;
@Service
public class EventAlbumService {

@Autowired
 private EventDAO eventdao;


}