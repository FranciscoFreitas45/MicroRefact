import java.util.List;
public class Template {

 private  String toUser;

 private  String templateId;

 private  String formId;

 private  String url;

 private  String topColor;

 private  String page;

 private  String emphasis_keyword;

 private  List<TemplateParam> templateParamList;


public void setTopColor(String topColor){
    this.topColor = topColor;
}


public String getFormId(){
    return formId;
}


public String getPage(){
    return page;
}


public void setTemplateId(String templateId){
    this.templateId = templateId;
}


public void setToUser(String toUser){
    this.toUser = toUser;
}


public void setFormId(String formId){
    this.formId = formId;
}


public void setUrl(String url){
    this.url = url;
}


public String getToUser(){
    return toUser;
}


public String getTemplateId(){
    return templateId;
}


public String getUrl(){
    return url;
}


public String toJSON(){
    StringBuffer buffer = new StringBuffer();
    buffer.append("{");
    buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
    buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
    buffer.append(String.format("\"form_id\":\"%s\"", this.formId)).append(",");
    buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
    buffer.append(String.format("\"topcolor\":\"%s\"", this.topColor)).append(",");
    buffer.append(String.format("\"page\":\"%s\"", this.page)).append(",");
    buffer.append(String.format("\"emphasis_keyword\":\"%s\"", this.emphasis_keyword)).append(",");
    buffer.append("\"data\":{");
    TemplateParam param = null;
    if (this.templateParamList.size() > 0) {
        for (int i = 0; i < this.templateParamList.size(); i++) {
            param = templateParamList.get(i);
            // 判断是否追加逗号
            if (i < this.templateParamList.size() - 1) {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(), param.getColor()));
            } else {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(), param.getColor()));
            }
        }
    }
    buffer.append("}");
    buffer.append("}");
    return buffer.toString();
}


public void setTemplateParamList(List<TemplateParam> templateParamList){
    this.templateParamList = templateParamList;
}


public void setEmphasis_keyword(String emphasis_keyword){
    this.emphasis_keyword = emphasis_keyword;
}


public String getTopColor(){
    return topColor;
}


public String getEmphasis_keyword(){
    return emphasis_keyword;
}


public void setPage(String page){
    this.page = page;
}


public List<TemplateParam> getTemplateParamList(){
    return templateParamList;
}


}