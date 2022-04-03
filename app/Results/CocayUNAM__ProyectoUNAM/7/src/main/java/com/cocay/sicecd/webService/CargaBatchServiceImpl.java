package com.cocay.sicecd.webService;
 import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.dao.CargaBatchServiceDAO;
import com.cocay.sicecd.dto.ResponseGeneric;
import com.cocay.sicecd.dto.ResponseGenericPagination;
public class CargaBatchServiceImpl implements CargaBatchService{

@Autowired
 private  CargaBatchServiceDAO cargaBatchServiceDAO;


@Override
public ResponseGeneric lstLimpiar(){
    ResponseGeneric response = new ResponseGeneric();
    try {
        cargaBatchServiceDAO.lstLimpiar();
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric lstInscripciones(Integer limit,Integer offset,String search,String name,String order){
    ResponseGeneric response = new ResponseGeneric();
    ResponseGenericPagination pagination = new ResponseGenericPagination();
    try {
        pagination.setRows(cargaBatchServiceDAO.lstInscripciones(limit, offset, search, name, order));
        pagination.setTotal(cargaBatchServiceDAO.totalLstInscripciones(search));
        response.setResponse(pagination);
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric limpiarTabla(String tabla){
    ResponseGeneric response = new ResponseGeneric();
    try {
        cargaBatchServiceDAO.limpiarTabla(tabla);
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric lstProfesores(Integer limit,Integer offset,String search,String name,String order){
    ResponseGeneric response = new ResponseGeneric();
    ResponseGenericPagination pagination = new ResponseGenericPagination();
    try {
        pagination.setRows(cargaBatchServiceDAO.lstProfesores(limit, offset, search, name, order));
        pagination.setTotal(cargaBatchServiceDAO.totalLstProfesores(search));
        response.setResponse(pagination);
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric lstCursos(Integer limit,Integer offset,String search,String name,String order){
    ResponseGeneric response = new ResponseGeneric();
    ResponseGenericPagination pagination = new ResponseGenericPagination();
    try {
        pagination.setRows(cargaBatchServiceDAO.lstCursos(limit, offset, search, name, order));
        pagination.setTotal(cargaBatchServiceDAO.totalLstCursos(search));
        response.setResponse(pagination);
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric lstGrupo(Integer limit,Integer offset,String search,String name,String order){
    ResponseGeneric response = new ResponseGeneric();
    ResponseGenericPagination pagination = new ResponseGenericPagination();
    try {
        pagination.setRows(cargaBatchServiceDAO.lstGrupo(limit, offset, search, name, order));
        pagination.setTotal(cargaBatchServiceDAO.totalLstGrupo(search));
        response.setResponse(pagination);
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric lstErrores(Integer limit,Integer offset,String search,String name,String order){
    ResponseGeneric response = new ResponseGeneric();
    ResponseGenericPagination pagination = new ResponseGenericPagination();
    try {
        pagination.setRows(cargaBatchServiceDAO.lstErrores(limit, offset, search, name, order));
        pagination.setTotal(cargaBatchServiceDAO.totalLstErrores(search));
        response.setResponse(pagination);
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


@Override
public ResponseGeneric ultimoValor(){
    ResponseGeneric response = new ResponseGeneric();
    try {
        response.setResponse(cargaBatchServiceDAO.ultimoValor());
        response.setCdMensaje("1");
        response.setNbMensaje("OK");
    } catch (Exception e) {
        response.setCdMensaje("0");
        response.setNbMensaje("ERROR");
    }
    return response;
}


}