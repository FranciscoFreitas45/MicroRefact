import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.repo.GrupoBatchRep;
import com.cocay.sicecd.model.Grupo;
public class WriterGrupo implements ItemWriter<Grupo>{

@Autowired
 private  GrupoBatchRep grupoRep;


@Override
public void write(List<? extends Grupo> grupo){
    System.out.println("Data Saved for Users: " + grupo);
    grupoRep.saveAll(grupo);
}


}