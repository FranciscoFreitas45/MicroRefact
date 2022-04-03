package com.cocay.sicecd.step;
 import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.ProfesorBatchRep;
public class WriterProfesor implements ItemWriter<Profesor>{

@Autowired
 private  ProfesorBatchRep profesorRep;

public WriterProfesor(ProfesorBatchRep profesorRep) {
    this.profesorRep = profesorRep;
}
@Override
public void write(List<? extends Profesor> profesor){
    profesorRep.saveAll(profesor);
}


}