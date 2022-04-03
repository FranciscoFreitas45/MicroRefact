package com.cg.oms.converter;
 import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.cg.oms.model.Medicine;
import com.cg.oms.vo.MedicineVo;
@Component
public class MedicineConverter {


public List<Medicine> voToModel(List<MedicineVo> medicineVo){
    return medicineVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
}


public List<MedicineVo> modelToVo(List<Medicine> medicine){
    return medicine.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
}


}