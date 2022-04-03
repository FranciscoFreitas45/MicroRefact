package com.cocay.sicecd.step;
 import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Inscripcion;
import com.cocay.sicecd.repo.InscripcionBatchRep;
public class WriterInscripcion implements ItemWriter<Inscripcion>{

@Autowired
 private  InscripcionBatchRep inscripcionRep;

public WriterInscripcion(InscripcionBatchRep inscripcionRep) {
    this.inscripcionRep = inscripcionRep;
}
@Override
public void write(List<? extends Inscripcion> inscripcion){
    inscripcionRep.saveAll(inscripcion);
}


}