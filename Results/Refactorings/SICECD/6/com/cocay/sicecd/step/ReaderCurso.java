import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import com.cocay.sicecd.model.Curso;
public class ReaderCurso {


public FlatFileItemReader<Curso> reader(String path){
    FlatFileItemReader<Curso> reader = new FlatFileItemReader<Curso>();
    reader.setResource(new ClassPathResource(path));
    reader.setLinesToSkip(1);
    reader.setLineMapper(new DefaultLineMapper<Curso>() {

        {
            setLineTokenizer(new DelimitedLineTokenizer() {

                {
                    setNames(new String[] { "clave", "nombre", "horas" });
                }
            });
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Curso>() {

                {
                    setTargetType(Curso.class);
                }
            });
        }
    });
    return reader;
}


}