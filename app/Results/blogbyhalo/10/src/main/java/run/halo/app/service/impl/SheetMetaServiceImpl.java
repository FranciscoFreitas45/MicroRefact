package run.halo.app.service.impl;
 import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.entity.SheetMeta;
import run.halo.app.repository.SheetMetaRepository;
import run.halo.app.repository.SheetRepository;
import run.halo.app.service.SheetMetaService;
@Slf4j
@Service
public class SheetMetaServiceImpl extends BaseMetaServiceImpl<SheetMeta>implements SheetMetaService{

 private  SheetMetaRepository sheetMetaRepository;

 private  SheetRepository sheetRepository;

public SheetMetaServiceImpl(SheetMetaRepository sheetMetaRepository, SheetRepository sheetRepository) {
    super(sheetMetaRepository);
    this.sheetMetaRepository = sheetMetaRepository;
    this.sheetRepository = sheetRepository;
}
@Override
public void validateTarget(Integer sheetId){
    sheetRepository.findById(sheetId).orElseThrow(() -> new NotFoundException("查询不到该页面的信息").setErrorData(sheetId));
}


}