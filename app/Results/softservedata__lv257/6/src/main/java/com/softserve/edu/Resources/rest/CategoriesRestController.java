package com.softserve.edu.Resources.rest;
 import com.fasterxml.jackson.annotation.JsonView;
import com.softserve.edu.Resources.dto.ExceptionJSONInfo;
import com.softserve.edu.Resources.dto.ResourceCategoryDTO;
import com.softserve.edu.Resources.dto.Views;
import com.softserve.edu.Resources.entity.ResourceCategory;
import com.softserve.edu.Resources.exception.BadCategoryNameException;
import com.softserve.edu.Resources.exception.CycleDependencyException;
import com.softserve.edu.Resources.exception.RemovingCategoriesWithTypesException;
import com.softserve.edu.Resources.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import com.softserve.edu.Resources.DTO.ExceptionJSONInfo;
@Transactional
@RestController
@RequestMapping(value = "/api/resources")
public class CategoriesRestController {

@Autowired
 private ResourceCategoryService categoryService;


@ExceptionHandler(BadCategoryNameException.class)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public ExceptionJSONInfo handleBadCategoryNameException(HttpServletRequest request,Exception ex){
    ExceptionJSONInfo response = new ExceptionJSONInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(ex.getMessage() + ". Check that all categories have correct and unique names and try again");
    return response;
}


@ExceptionHandler(CycleDependencyException.class)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public ExceptionJSONInfo handleCycleDependenciesException(HttpServletRequest request,Exception ex){
    ExceptionJSONInfo response = new ExceptionJSONInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(ex.getMessage() + ". Check all categories and try again");
    return response;
}


@ExceptionHandler(RemovingCategoriesWithTypesException.class)
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public ExceptionJSONInfo handleRemovingCategoriesWithTypesException(HttpServletRequest request,Exception ex){
    ExceptionJSONInfo response = new ExceptionJSONInfo();
    response.setUrl(request.getRequestURL().toString());
    response.setMessage(ex.getMessage() + ". Check that all categories, which you want remove, haven't resource types");
    return response;
}


@JsonView(Views.CategoriesWithTypes.class)
@RequestMapping(value = "/categorizedTypes", method = RequestMethod.GET)
public List<ResourceCategoryDTO> categorizedTypes(){
    List<ResourceCategory> rootCategories = categoryService.findRootCategories();
    List<ResourceCategoryDTO> categoryDTOS = rootCategories.stream().map(categoryService::createCategoryDTO).collect(Collectors.toList());
    return categoryDTOS;
}


@JsonView(Views.Categories.class)
@RequestMapping(value = "/categories", method = RequestMethod.GET)
public List<ResourceCategoryDTO> categories(){
    return categorizedTypes();
}


@RequestMapping(value = "/categories", method = RequestMethod.POST)
public void updateCategoriesHierarchy(List<ResourceCategoryDTO> categoryDTOList){
    List<ResourceCategory> rootCategoriesFromWeb = categoryDTOList.stream().map(categoryService::mapFromDtoToResourceCategory).collect(Collectors.toList());
    List<ResourceCategory> allCategoriesFromWeb = categoryService.deployAllCategoriesFromRoots(rootCategoriesFromWeb);
    if (!categoryService.isValidCategoryName(allCategoriesFromWeb, 3, 50)) {
        throw new BadCategoryNameException("Some categories have identical names or their names are incorrect");
    }
    categoryService.deleteMissingCategoriesInDB(allCategoriesFromWeb);
    rootCategoriesFromWeb.forEach(categoryService::saveResourceCategory);
}


}