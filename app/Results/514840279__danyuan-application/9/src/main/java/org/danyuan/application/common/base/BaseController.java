package org.danyuan.application.common.base;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
public interface BaseController {


@RequestMapping(value = "/saveAll", method = RequestMethod.POST)
public BaseResult<T> saveAll(Pagination<T> vo)
;

@RequestMapping(value = "/trunc", method = RequestMethod.POST)
public BaseResult<T> trunc()
;

@RequestMapping(value = "/findAllById", method = RequestMethod.POST)
public BaseResult<List<T>> findAllById(Pagination<T> vo)
;

@RequestMapping(value = "/findOne", method = RequestMethod.POST)
public BaseResult<T> findOne(T info)
;

@RequestMapping(value = "/save", method = RequestMethod.POST)
public BaseResult<T> save(T info)
;

@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
public BaseResult<T> deleteAll(Pagination<T> vo)
;

@RequestMapping(value = "/count", method = RequestMethod.POST)
public BaseResult<Long> count(T info)
;

@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
public BaseResult<T> deleteById(Pagination<T> vo)
;

@RequestMapping(value = "/page", method = RequestMethod.POST)
public BaseResult<Page<T>> page(Pagination<T> vo)
;

@RequestMapping(value = "/findAll", method = RequestMethod.POST)
public BaseResult<List<T>> findAll(T info)
;

@RequestMapping(value = "/delete", method = RequestMethod.POST)
public BaseResult<T> delete(T info)
;

}