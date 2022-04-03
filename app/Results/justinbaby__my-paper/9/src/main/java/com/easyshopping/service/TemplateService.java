package com.easyshopping.service;
 import java.util.List;
import com.easyshopping.Template;
import com.easyshopping.Template.Type;
public interface TemplateService {


public List<Template> getAll()
;

public String read(Template template)
;

public List<Template> getList(Type type)
;

public Template get(String id)
;

public void write(Template template,String content)
;

}