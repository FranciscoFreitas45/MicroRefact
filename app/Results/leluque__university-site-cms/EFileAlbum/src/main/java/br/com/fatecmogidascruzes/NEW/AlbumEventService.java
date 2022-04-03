package br.com.fatecmogidascruzes.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.fatecmogidascruzes.gallery.data.AlbumDAO;
import br.com.fatecmogidascruzes.gallery.Album;
@Service
public class AlbumEventService {

@Autowired
 private AlbumDAO albumdao;


}