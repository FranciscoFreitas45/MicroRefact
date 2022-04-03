package com.qidian.hcm.module.organization.controller;
 import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.common.utils.ResultGenerator;
import com.qidian.hcm.module.organization.request.CreateGradeRequest;
import com.qidian.hcm.module.organization.request.EnableGradeRequest;
import com.qidian.hcm.module.organization.request.UpdateGradeRequest;
import com.qidian.hcm.module.organization.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
@RestController
@Slf4j
@RequestMapping("/api/grade")
@Api(tags = "组织机构--职级相关")
public class GradeController {

@Autowired
 private  GradeService gradeService;


@ApiOperation("删除职级")
@DeleteMapping("/{id}")
public Result deleteGradeById(Long id){
    gradeService.deleteGradeById(id);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation("修改职级信息")
@PutMapping("/{id}")
public Result updateGrade(Long id,UpdateGradeRequest updateGradeRequest){
    updateGradeRequest.setId(id);
    gradeService.updateGrade(updateGradeRequest);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation("新建职级")
@PostMapping()
public Result createGrade(CreateGradeRequest createGradeRequest){
    gradeService.createGrade(createGradeRequest);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation("生效/失效职级")
@PutMapping("/enable")
public Result enableGradeById(EnableGradeRequest request){
    gradeService.enableGradeById(request);
    return ResultGenerator.genSuccessResult();
}


@ApiOperation("获取职级信息单条记录")
@GetMapping("/{id}")
public Result getGradeById(Long id){
    return ResultGenerator.genSuccessResult(gradeService.getGradeById(id));
}


@ApiOperation("获取职级信息分页列表")
@GetMapping()
public Result getGradeList(Integer active,String keyword,Integer pageNo,Integer pageSize){
    return ResultGenerator.genSuccessResult(gradeService.getGradeList(active, keyword, pageNo, pageSize));
}


}