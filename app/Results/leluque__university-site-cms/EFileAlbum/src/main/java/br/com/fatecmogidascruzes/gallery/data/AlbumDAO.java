package br.com.fatecmogidascruzes.gallery.data;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.fatecmogidascruzes.agenda.AgendaEntry;
import br.com.fatecmogidascruzes.data.DAOImpl;
import br.com.fatecmogidascruzes.gallery.Album;
public interface AlbumDAO extends DAOImpl<Album, Long>, JpaRepository<Album, Long>{


@Query("SELECT al FROM Album al WHERE TRUE = al.enabled AND (UPPER(al.name) LIKE CONCAT('%',:filter,'%') OR UPPER(al.description) LIKE CONCAT('%',:filter,'%'))")
public List<Album> getEnabledByFilter(String filter)
;

@Query("SELECT al FROM Album al WHERE TRUE = al.enabled AND :event = al.event AND TRUE = al.event.enabled")
public List<Album> getEnabledByEnabledEvent(AgendaEntry event)
;

public void addImage(Long id,File image);

public void removeImage(Long id,UUID imageHash);

}