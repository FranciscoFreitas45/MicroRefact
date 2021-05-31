import java.util.List;
import org.sdrc.childinfo.domain.ImageGallery;
import org.springframework.transaction.annotation.Transactional;
public interface ImageGalleryRepository {


@Transactional
public ImageGallery save(ImageGallery imageGallery)


public List<ImageGallery> findAll()


}