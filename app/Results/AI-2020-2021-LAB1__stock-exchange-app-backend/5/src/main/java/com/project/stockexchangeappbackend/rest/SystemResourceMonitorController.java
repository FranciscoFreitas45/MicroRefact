package com.project.stockexchangeappbackend.rest;
 import com.project.stockexchangeappbackend.dto.SystemResourcesMonitorDTO;
import com.project.stockexchangeappbackend.repository.specification.SystemResourceMonitorSpecification;
import com.project.stockexchangeappbackend.service.SystemResourcesMonitorService;
import io.swagger.annotations;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/system")
@Validated
@CrossOrigin("*")
@AllArgsConstructor
@Api(value = "System", description = "REST API for system resources' management", tags = "System")
public class SystemResourceMonitorController {

 private  SystemResourcesMonitorService systemResourcesMonitorService;

 private  ModelMapper modelMapper;


@GetMapping("/resources")
@PreAuthorize("hasAnyRole('ADMIN')")
@ApiOperation(value = "Page and filter system resource info.", notes = "Required role: ADMIN")
@ApiResponses({ @ApiResponse(code = 200, message = "System resources' info was successfully retrieved."), @ApiResponse(code = 401, message = "Unauthorized."), @ApiResponse(code = 403, message = "Access Denied") })
@ApiImplicitParams({ @ApiImplicitParam(name = "datetime>", dataType = "integer", paramType = "query", value = "Filtering criteria for field `timestamp` (omitted if null)."), @ApiImplicitParam(name = "datetime<", dataType = "integer", paramType = "query", value = "Filtering criteria for field `timestamp` (omitted if null).") })
public List<SystemResourcesMonitorDTO> getInfo(SystemResourceMonitorSpecification specification){
    return systemResourcesMonitorService.getInfo(specification).stream().map(info -> modelMapper.map(info, SystemResourcesMonitorDTO.class)).collect(Collectors.toList());
}


}