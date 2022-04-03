package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.annotation.DisableOnCondition;
import run.halo.app.model.dto.OptionDTO;
import run.halo.app.model.dto.OptionSimpleDTO;
import run.halo.app.model.entity.Option;
import run.halo.app.model.params.OptionParam;
import run.halo.app.model.params.OptionQuery;
import run.halo.app.service.OptionService;
@RestController
@RequestMapping("/api/admin/options")
public class OptionController {

 private  OptionService optionService;

public OptionController(OptionService optionService) {
    this.optionService = optionService;
}
@PostMapping("saving")
@ApiOperation("Saves options")
@DisableOnCondition
public void saveOptions(List<OptionParam> optionParams){
    optionService.save(optionParams);
}


@GetMapping("list_view")
@ApiOperation("Lists all options with list view")
public Page<OptionSimpleDTO> listAllWithListView(Pageable pageable,OptionQuery optionQuery){
    return optionService.pageDtosBy(pageable, optionQuery);
}


@PostMapping
@ApiOperation("Creates option")
@DisableOnCondition
public void createBy(OptionParam optionParam){
    optionService.save(optionParam);
}


@PostMapping("map_view/saving")
@ApiOperation("Saves options by option map")
@DisableOnCondition
public void saveOptionsWithMapView(Map<String,Object> optionMap){
    optionService.save(optionMap);
}


@PutMapping("{optionId:\\d+}")
@ApiOperation("Updates option")
@DisableOnCondition
public void updateBy(Integer optionId,OptionParam optionParam){
    optionService.update(optionId, optionParam);
}


@GetMapping("{id:\\d+}")
@ApiOperation("Gets option detail by id")
public OptionSimpleDTO getBy(Integer id){
    Option option = optionService.getById(id);
    return optionService.convertToDto(option);
}


@PostMapping("map_view/keys")
@ApiOperation("Lists options with map view by keys")
public Map<String,Object> listAllWithMapView(List<String> keys){
    return optionService.listOptions(keys);
}


@GetMapping
@ApiOperation("Lists options")
public List<OptionDTO> listAll(){
    return optionService.listDtos();
}


@DeleteMapping("{optionId:\\d+}")
@ApiOperation("Deletes option")
@DisableOnCondition
public void deletePermanently(Integer optionId){
    optionService.removePermanently(optionId);
}


}