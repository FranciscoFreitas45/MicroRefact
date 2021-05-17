import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;
import com.cocay.sicecd.dto.CursoDto;
public class CursoExcelRowMapper implements RowMapper<CursoDto>{


@Override
public CursoDto mapRow(RowSet rowSet){
    CursoDto curso = new CursoDto();
    curso.setClave(rowSet.getColumnValue(0));
    curso.setNombre(rowSet.getColumnValue(1));
    curso.setTipo(rowSet.getColumnValue(2));
    curso.setHoras(rowSet.getColumnValue(3));
    // TODO Auto-generated method stub
    return null;
}


}