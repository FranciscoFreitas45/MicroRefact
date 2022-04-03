package org.danyuan.application.common.base;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.danyuan.application.DTO.Pagination;
@NoRepositoryBean
public class BaseControllerImpl implements BaseController<T>{

@Autowired
 private BaseService<T> baseService;


@Override
public BaseResult<T> saveAll(Pagination<T> vo){
    try {
        baseService.saveAll(vo.getList());
        return ResultUtil.success();
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<T> trunc(){
    try {
        baseService.trunc();
        return ResultUtil.success();
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<List<T>> findAllById(Pagination<T> vo){
    try {
        List<T> list = baseService.findAllById(vo.getUuidList());
        return ResultUtil.success(list);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<T> findOne(T info){
    try {
        T page = baseService.findOne(info);
        return ResultUtil.success(page);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<T> save(T info){
    try {
        T page = baseService.save(info);
        return ResultUtil.success(page);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<T> deleteAll(Pagination<T> vo){
    try {
        baseService.deleteAll(vo.getList());
        return ResultUtil.success();
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<Long> count(T info){
    try {
        Long lengthLong = baseService.count(info);
        return ResultUtil.success(lengthLong);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<T> deleteById(Pagination<T> vo){
    try {
        baseService.deleteById(vo.getUuid());
        return ResultUtil.success();
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<Page<T>> page(Pagination<T> vo){
    try {
        Page<T> page = baseService.page(vo);
        return ResultUtil.success(page);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<List<T>> findAll(T info){
    try {
        List<T> list = baseService.findAll(info);
        return ResultUtil.success(list);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


@Override
public BaseResult<T> delete(T info){
    try {
        baseService.delete(info);
        return ResultUtil.success(info);
    } catch (Exception e) {
        return ResultUtil.error(-1, e.getMessage());
    }
}


}