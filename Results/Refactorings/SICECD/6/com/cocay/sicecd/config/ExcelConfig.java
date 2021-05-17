import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.cocay.sicecd.dto.CursoDto;
import com.cocay.sicecd.step.CursoExcelRowMapper;
import com.cocay.sicecd.step.LoggingCursoProcessor;
import com.cocay.sicecd.step.LoggingCursoWriter;
@Configuration
public class ExcelConfig {


@Bean
public ItemReader<CursoDto> excelCursoReader(){
    PoiItemReader<CursoDto> reader = new PoiItemReader<>();
    reader.setLinesToSkip(1);
    reader.setResource(new ClassPathResource("PruebaExcel.xls"));
    reader.setRowMapper(excelRowMapper());
    return reader;
}


@Bean
public ItemWriter<CursoDto> excelCursoWriter(){
    return new LoggingCursoWriter();
}


@Bean
public Job excelFileToDatabaseJob(JobBuilderFactory jobBuilderFactory,Step excelCursoStep){
    return jobBuilderFactory.get("excelFileToDatabaseJob").incrementer(new RunIdIncrementer()).flow(excelCursoStep).end().build();
}


@Bean
public Step excelFileToDatabaseStep(ItemReader<CursoDto> excelCursoReader,ItemProcessor<CursoDto,CursoDto> excelCursoProcessor,ItemWriter<CursoDto> excelCursoWriter,StepBuilderFactory stepBuilderFactory){
    return stepBuilderFactory.get("excelFileToDatabaseStep").<CursoDto, CursoDto>chunk(1).reader(excelCursoReader).processor(excelCursoProcessor).writer(excelCursoWriter).build();
}


@Bean
public ItemProcessor<CursoDto,CursoDto> excelCursoProcessor(){
    return new LoggingCursoProcessor();
}


public RowMapper<CursoDto> excelRowMapper(){
    return new CursoExcelRowMapper();
}


}