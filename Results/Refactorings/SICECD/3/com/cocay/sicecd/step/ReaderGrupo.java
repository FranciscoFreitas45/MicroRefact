import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import com.cocay.sicecd.model.Curso;
import com.cocay.sicecd.model.Grupo;
public class ReaderGrupo {


public FlatFileItemReader<Grupo> reader(String path){
    FlatFileItemReader<Grupo> reader = new FlatFileItemReader<Grupo>();
    reader.setResource(new ClassPathResource(path));
    reader.setLinesToSkip(1);
    reader.setLineMapper(new DefaultLineMapper<Grupo>() {

        {
            setLineTokenizer(new DelimitedLineTokenizer() {

                {
                    setNames(new String[] { "clave" });
                }
            });
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Grupo>() {

                {
                    setTargetType(Grupo.class);
                }
            });
        }
    });
    return reader;
}


}