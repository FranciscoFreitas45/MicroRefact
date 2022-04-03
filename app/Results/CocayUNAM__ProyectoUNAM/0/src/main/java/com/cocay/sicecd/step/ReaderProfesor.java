package com.cocay.sicecd.step;
 import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import com.cocay.sicecd.model.Profesor;
public class ReaderProfesor {


public FlatFileItemReader<Profesor> reader(String pathToFile){
    FlatFileItemReader<Profesor> reader = new FlatFileItemReader<>();
    reader.setResource(new ClassPathResource(pathToFile));
    reader.setLinesToSkip(1);
    reader.setLineMapper(new DefaultLineMapper<Profesor>() {

        {
            setLineTokenizer(new DelimitedLineTokenizer() {

                {
                    setNames(new String[] { "nombre", "apellido_paterno", "apellido_materno", "rfc", "curp", "correo", "telefono", "ciudad_localidad", "id_genero", "plantel", "clave_plantel", "ocupacion", "curriculum", "fecha_nac" });
                }
            });
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Profesor>() {

                {
                    setTargetType(Profesor.class);
                }
            });
        }
    });
    return reader;
}


}