package org.jeecgframework.tag.core.easyui;
 import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
public class WebUploaderTag extends TagSupport{

 private  long serialVersionUID;

 private  String name;

 private  boolean auto;

 private  String buttonStyle;

 private  String url;

 private  int fileNumLimit;

 private  int fileSingleSizeLimit;

 private  int size;

 private  String fileVal;

 private  boolean duplicate;

 private  String showImgDiv;

 private  String showAndDownUrl;

 private  String pathValues;

 private  String type;

 private  String buttonText;

 private  String extensions;

 private  String extendParams;

 private  String datatype;

 private  String nullMsg;

 private  String readOnly;

 private  String bizType;

 private  boolean displayTxt;

 private  boolean outJs;

 private  boolean swfTransform;


public String getName(){
    return name;
}


public void setFileVal(String fileVal){
    this.fileVal = fileVal;
}


public void setOutJs(boolean outJs){
    this.outJs = outJs;
}


public boolean isOutJs(){
    return outJs;
}


public String getFileVal(){
    return fileVal;
}


public void setExtensions(String extensions){
    this.extensions = extensions;
}


public void setDatatype(String datatype){
    this.datatype = datatype;
}


public String getButtonStyle(){
    if (oConvertUtils.isEmpty(buttonStyle)) {
        buttonStyle = "btn-green btn-S";
    }
    return buttonStyle;
}


public void setDuplicate(boolean duplicate){
    this.duplicate = duplicate;
}


public void setUrl(String url){
    this.url = url;
}


public int doStartTag(){
    return EVAL_PAGE;
}


public String getUrl(){
    return url + "&sessionId=" + pageContext.getSession().getId();
}


public void setDisplayTxt(boolean displayTxt){
    this.displayTxt = displayTxt;
}


public void setShowImgDiv(String showImgDiv){
    this.showImgDiv = showImgDiv;
}


public String getType(){
    return type;
}


public String getPathValues(){
    return pathValues;
}


public void setReadOnly(String readOnly){
    this.readOnly = readOnly;
}


public void setName(String name){
    this.name = name;
}


public String getNullMsg(){
    if (oConvertUtils.isEmpty(nullMsg)) {
        this.nullMsg = "?????????" + ("file".equals(type) ? "??????!" : "??????!");
    }
    return nullMsg;
}


public void setButtonText(String buttonText){
    this.buttonText = buttonText;
}


public void setFileSingleSizeLimit(int fileSingleSizeLimit){
    if (fileSingleSizeLimit > 0) {
        this.fileSingleSizeLimit = fileSingleSizeLimit * 1024;
    }
}


public void setBizType(String bizType){
    this.bizType = bizType;
}


public void setSwfTransform(boolean swfTransform){
    this.swfTransform = swfTransform;
}


public String getBizType(){
    return bizType;
}


public void setExtendParams(String extendParams){
    // update--begin--author:zhangjiaqiang date:20170705 for:TASK #2195 ???ui?????????????????????????????????????????????????????????????????????
    if (StringUtil.isNotEmpty(extendParams) && !extendParams.endsWith(",")) {
        extendParams = extendParams + ",";
    }
    // update--end--author:zhangjiaqiang date:20170705 for:TASK #2195 ???ui?????????????????????????????????????????????????????????????????????
    this.extendParams = extendParams;
}


public void setNullMsg(String nullMsg){
    this.nullMsg = nullMsg;
}


public String getDatatype(){
    return datatype;
}


public void setSize(int size){
    this.size = size;
}


public String getShowImgDiv(){
    return showImgDiv;
}


public boolean isAuto(){
    return auto;
}


public void end(StringBuffer sb){
    String nameWithspchar = getSpecialCharName();
    // ??????????????????
    String btnCss = getButtonStyle();
    // typeResetByext(this.extensions);//?????????????????????????????????type
    if (!outJs) {
        sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"plug-in/webuploader/custom.css\"></link>");
        sb.append("<script type=\"text/javascript\" src=\"plug-in/webuploader/webuploader.min.js\"></script>");
    }
    sb.append("<div id='" + name + "uploader' class='wu-example'>");
    String showthelist = name + "thelist";
    if ("file".equals(type) && oConvertUtils.isNotEmpty(showImgDiv)) {
        showthelist = showImgDiv;
    } else {
        sb.append("<div id='" + showthelist + "' class='uploader-list'></div>");
    }
    // ?????????html??????
    sb.append("<div id='" + name + "_progress_bar' class='progress-bar-ty '");
    if ("image".equals(type)) {
        sb.append(" style='display:none'");
    }
    sb.append("><div class='progress-ty'>");
    sb.append("<span class='upload-label-ty' style='display:none;'>????????????...<b class='value'>79%</b></span></div></div>");
    sb.append("<div class='btns'><div id='" + name + "picker'>" + getButtonText() + "</div></div></div>");
    if ("image".equals(type) && oConvertUtils.isEmpty(showImgDiv)) {
        showImgDiv = "tempdiv_" + name;
        sb.append("<div id='" + showImgDiv + "'></div>");
    }
    // ?????????sb?????????<%=basePath%>,????????????????????????
    sb.append("<script type=\"text/javascript\">Array.prototype.removeItem = function(val){var index = this.indexOf(val);if (index > -1) {this.splice(index, 1);}};" + "var exsitPathArr_" + name + " =new Array();" + "$(function() { var state = 'pending';var $list=$('#" + showthelist + "');" + "var uploader = WebUploader.create({" + "swf: 'plug-in/webuploader/Uploader.swf'," + "server :\'" + url + // getUrl()
    "\'," + " pick: '#" + name + "picker',duplicate: " + duplicate + ",resize: false," + "auto:" + auto + "," + "fileVal:'" + fileVal + "'," + "fileNumLimit:" + fileNumLimit + "," + "fileSingleSizeLimit:" + fileSingleSizeLimit);
    if (oConvertUtils.isNotEmpty(extensions)) {
        sb.append(",accept:{extensions:'" + extensions + "'}");
    }
    if (oConvertUtils.isEmpty(extendParams)) {
        sb.append(",formData:{isup:'1',swfTransform:'" + swfTransform + "',bizType:'" + bizType + "'}});");
    } else {
        sb.append(",formData:{isup:'1',swfTransform:'" + swfTransform + "',bizType:'" + bizType + "'," + extendParams + "}});");
    }
    if (!auto) {
        sb.append("\r\nvar upbtnrdo4=\"<div id='" + name + "ctlBtn' class='upbtn btn-blue " + btnCss + "'>????????????</div>\";$('#" + name + "picker').find('div:eq(0)').after(upbtnrdo4);upbtnrdo4='';\r\n");
        if ("true".equals(readOnly) || "readOnly".equals(readOnly)) {
            sb.append("$('#" + name + "ctlBtn').css('display','none');");
        }
    }
    if ("image".equals(type)) {
        sb.append("var imageAdd_" + name + " = true;");
    } else {
        sb.append("var imageAdd_" + name + " = false;");
    }
    sb.append("$('#" + name + "picker').find('div:eq(0)').addClass('webuploader-pick " + btnCss + "');");
    // sb.append("$('#"+name+"picker').find('div:eq(0)').unbind(\"mouseenter\").unbind(\"mouseleave\");");
    sb.append("$('#" + showImgDiv + "').addClass('tempIMGdiv').append('<ul></ul>');\r\n");
    sb.append("$list.append('<table class=\"temptable\"></table>');\r\n");
    // ?????????????????????
    // ?????????????????????duration???????????????????????????????????????????????????????????????????????????????????????,????????????
    sb.append("var showUploadProgress = function(progress,mycallback,obj){if(!obj){obj = $('#" + name + "_progress_bar').find('.progress-ty');}if(!$('#" + name + "_progress_bar').hasClass('active')){$('#" + name + "_progress_bar').addClass('active');}obj.animate({width:progress+'%'},{duration:100,easing:'swing',complete:function(scope,i,elem){if(!!mycallback){mycallback();}}})};");
    // ??????????????????base64
    sb.append(" var isSupportBase64 = function() {var data = new Image();var support = true;data.onload = data.onerror = function() {if( this.width != 1 || this.height != 1 ){support = false;}}//data['src'] = 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==';\r\nreturn support;};");
    // ???????????????????????????,???????????????css
    sb.append("var ratio = window.devicePixelRatio || 1;var thumbnailWidth = 100 * ratio;var thumbnailHeight = 100 * ratio;");
    // ????????????????????????????????????
    sb.append("var " + name + "addImgli=function(src,name,xpath,flag){if(imageAdd_" + name + "){var titleclass='hidetitle';//if(flag==1){titleclass='hidetitle';}\r\nvar img = '<li><img name=\"' + name + 'img\" class=\"tempimg\" src=\"' + src + '\"><div class=\"' + titleclass + '\"><span';");
    if ("true".equals(readOnly) || "readOnly".equals(readOnly)) {
        sb.append("img+=' style=\"display:none;\"';");
    }
    sb.append("img+=' class=\"titledel\">'+xpath+'</span><span';");
    sb.append("img+=xpath==0?' style=\"display:none;\"' :' ';");
    sb.append("img+=' class=\"titledown\">'+xpath+'</span></div></li>';$('#" + showImgDiv + "').find('ul').append(img);}}\r\n");
    // ??????table????????????????????????
    String tipTableStyle = "";
    if (!displayTxt) {
        tipTableStyle = " style=\"display:none\" ";
    }
    sb.append("var addtrFile=function(id,name,text,downsrc,delflag){var namet = name;if(name.length>15){name = name.substring(0,15)+'...';}var trhtml='<tr " + tipTableStyle + " class=\"item\" id=\"'+id+'\"><td title = '+namet+'>'+name+'</td><td class=\"state\">'+text+'</td><td class=\"icontd\"><span';");
    sb.append("trhtml+=downsrc==0?' style=\"display:none;\"' :' ';");
    sb.append("trhtml+=' class=\"down icon-down\">'+downsrc+'</span></td><td class=\"icontd\"><span';");
    if ("true".equals(readOnly) || "readOnly".equals(readOnly)) {
        sb.append("trhtml+=' style=\"display:none;\"';");
    }
    sb.append("trhtml+=' class=\"del icon-cha\" style=\"overflow:hidden;\">'+delflag+'</span></td>';");
    // ?????????????????????swf?????????????????????????????????
    if ("file".equals(type) && swfTransform) {
        sb.append("if(downsrc==0){trhtml+='<td class=\"viewtd\"><span class=\"view\" style=\"overflow:hidden;\"></span></td>';}");
        sb.append("else{ var aaaaa =\"systemController.do?openViewFile&path=\"+downsrc;var viewclick =\"openwindow(\'??????\',\'\"+aaaaa+\"\',\'tempty\',700,500)\";trhtml+='<td class=\\'icontd\\'><span class=\\'view icon-view\\' onclick=\"'+viewclick+'\"></span></td>';}\r\n");
    }
    sb.append("trhtml+='<td></td></tr>';$list.children('table').append(trhtml);}");
    // ??????dataType??????
    if (oConvertUtils.isNotEmpty(datatype)) {
        sb.append("\r\n$('#" + name + "uploader').find('div.btns').append('<input nullMsg=\"" + getNullMsg() + "\" datatype=\"*\" type=\"hidden\" id= \"" + name + "dataTypeInp\" />');");
    }
    sb.append("\r\nvar reset_" + name + "_dataTypeInpVal=function(addOrdel){var obj = $(\"#" + name + "dataTypeInp\");if(obj.length>0){var objval=obj.val()||'';\r\nif (addOrdel == 1) {if(objval==''){obj.val('1');}else{obj.val(objval.toString()+(parseInt(objval.length)+1));}}else{if(objval.length <=1){obj.val('');}else{obj.val(objval.substr(0,objval.length-1));\r\n}\r\n}obj.blur();}}");
    // ???????????????
    if (oConvertUtils.isNotEmpty(pathValues)) {
        sb.append("\r\n$list.append( '<div class=\"fordel\"><input type=\"hidden\" name=\"" + nameWithspchar + "\" value=\"" + pathValues.replace("\\", "\\\\") + "\" /></div>' );\r\n");
        if ("image".equals(type)) {
            sb.append("var pvs='" + pathValues.replace("\\", "\\\\") + "';exsitPathArr_" + name + "=pvs.split(',');for(var a = 0; a< exsitPathArr_" + name + ".length; a++){\r\nvar singlePath=exsitPathArr_" + name + "[a];\r\nif(''!=singlePath){reset_" + name + "_dataTypeInpVal(1);var singleSrc=\"" + showAndDownUrl + "\"+singlePath;" + name + "addImgli(singleSrc,'name'+a,singlePath,1);}}");
        } else {
            sb.append("var pvs='" + pathValues.replace("\\", "\\\\") + "';exsitPathArr_" + name + "=pvs.split(',');for(var a = 0; a< exsitPathArr_" + name + ".length; a++){\r\nvar singlePath=exsitPathArr_" + name + "[a];\r\nif(''!=singlePath){reset_" + name + "_dataTypeInpVal(1);var rf6=randomFor(6);addtrFile('id'+a+rf6,mygetFileName(singlePath),'--??????????????????--',singlePath,singlePath);}}");
        }
    }
    // ????????????
    sb.append("\r\nvar imgDelReq=function(delpath,spanobj){$.post('" + url + "',{path:delpath,swfTransform:'" + swfTransform + "',isdel:\"1\"},function(aj){var data=JSON.parse(aj);if(data.success){reset_" + name + "_dataTypeInpVal(0);exsitPathArr_" + name + ".removeItem(delpath);$list.children('.fordel').children('input').val(exsitPathArr_" + name + ".join(','));var myimgli=$(spanobj).closest('li');myimgli.off().find('.hidetitle').off().end().remove();}});}\r\n");
    sb.append("var " + name + "addFile=function(file,filepath){\r\nuploader.makeThumb(file, function(error,src) {\r\nif(error){return false;}\r\nif(isSupportBase64()){if(filepath==''){" + name + "addImgli(src,file.id,0,0);}\r\n}else if(filepath!=''){\r\nvar actSrc=\"" + showAndDownUrl + "\"+filepath;\r\n" + name + "addImgli(actSrc,file.id,0,0);}}, thumbnailWidth, thumbnailHeight );}");
    sb.append("\r\nvar updatetdState=function(id,content){$list.children('table').find('#" + name + "'+id).find('.state').text('--'+content+'--');}\r\n");
    // update-begin-author:taoyan date:20180411 for:TASK #2589 ???UI??????????????????????????????,????????????????????????1??????????????????????????????
    if (fileNumLimit == 1) {
        if (auto) {
            sb.append("var " + name + "_oneLimit = 0;");
        }
        if ("image".equals(type)) {
            sb.append("uploader.on('beforeFileQueued',function(file){");
            if (auto) {
                // ???????????????????????????
                sb.append("if(" + name + "_oneLimit>=1){return false;}else{" + name + "_oneLimit++;};");
            }
            // ????????????????????????????????????????????????
            sb.append("var currLi=$('#" + showImgDiv + ">ul').find('li:last');if(currLi.length>0){currLi.addClass('wait-remove');var abcfile=currLi.find('img').attr('name');if(abcfile.indexOf('name')==0){}else{abcfile=abcfile.substring(0,abcfile.length-3);uploader.removeFile(abcfile)}}});");
        } else {
            sb.append("uploader.on('beforeFileQueued',function(file){");
            if (auto) {
                // ???????????????????????????
                sb.append("if(" + name + "_oneLimit>=1){return false;}else{" + name + "_oneLimit++;};");
            }
            // ????????????????????????????????????????????????
            sb.append("var currLi=$('#" + showthelist + ">table').find('tr.item:last');if(currLi.length>0){currLi.addClass('wait-remove');var abcfile=currLi[0].id;if(abcfile.indexOf('id')==0){}else{abcfile=abcfile.substring(" + name.length() + ");uploader.removeFile(abcfile)}}});");
        }
    }
    // update-end-author:taoyan date:20180411 for:TASK #2589 ???UI??????????????????????????????,????????????????????????1??????????????????????????????
    // ???????????????????????????????????????
    sb.append(// '+file.name+'---????????????---</span>
    "uploader.on( 'fileQueued', function( file ) {" + "var id='" + name + "'+file.id;var name=file.name;var text='--????????????--';addtrFile(id,name,text,0,0);" + name + "addFile(file,'');\r\n" + "});");
    // ?????????????????????????????????????????????
    sb.append("	uploader.on( 'uploadProgress', function( file, percentage ) {var $li = $('#" + name + "'+file.id+' td:last'),$percent = $li.find('.progress .progress-bar');if ( !$percent.length ) {$percent = $('<div class=\"progress progress-striped active\"><div class=\"progress-bar\" role=\"progressbar\" style=\"width: 0%\"></div></div>').appendTo($li).find('.progress-bar');}updatetdState(file.id,'?????????');$percent.css( 'width', percentage * 100 + '%' );});");
    // ?????????????????? ???????????????
    sb.append("uploader.on('uploadStart',function(file){$('#" + name + "_progress_bar').find('.progress-ty').css('width','1%');var temprd=Math.floor(Math.random()*7+1);if(temprd<4){temprd=Number(temprd)+3}temprd=Number(temprd)*10;showUploadProgress(temprd,function(){showUploadProgress(Number(temprd)+15);})});");
    // update-begin-author:taoyan date:20180411 for:TASK #2589 ???UI??????????????????????????????,????????????????????????1??????????????????????????????
    // ?????????????????????????????????????????????????????????input?????? filePath
    sb.append("uploader.on( 'uploadSuccess', function(file,response) {showUploadProgress(100,function(){if(response.success){$('#" + name + "_progress_bar').removeClass('active');updatetdState(file.id,'????????????');reset_" + name + "_dataTypeInpVal(1);" + "var filepath=response['" + name + "']||response.obj;$('#" + name + "'+file.id+' td:first').append('<input type=\"hidden\" name=\"" + nameWithspchar + "\" value=\"'+filepath+'\" />');" + name + "addFile(file, filepath);");
    // ?????????????????????swf?????????????????????????????????
    if ("file".equals(type) && swfTransform) {
        sb.append("$('#" + name + "'+file.id+' td.viewtd').removeClass('viewtd').addClass('icontd').find('span').addClass('icon-view').attr('onclick',\"openwindow('??????','systemController.do?openViewFile&path=\"+filepath+\"','tempty',700,500)\");");
    }
    if (fileNumLimit == 1) {
        if (auto) {
            // ???????????? ????????????
            sb.append(name + "_oneLimit = 0;");
        }
        // ?????????????????????????????????
        if ("image".equals(type)) {
            sb.append("$('#" + showImgDiv + ">ul').find('li.wait-remove').find('.titledel').click()");
        } else {
            sb.append("$('#" + showthelist + ">table').find('tr.wait-remove').find('.del').click()");
        }
    }
    // TODO ??????????????????????????????
    sb.append("}else{$('#" + name + "_progress_bar').removeClass('active');updatetdState(file.id,'????????????'+response.msg);}});});\r\n");
    // update-end-author:taoyan date:20180411 for:TASK #2589 ???UI??????????????????????????????,????????????????????????1??????????????????????????????
    // ????????????
    sb.append("uploader.on( 'uploadError', function( file,reason ) {updatetdState(file.id,'????????????-code:'+reason);});");
    // ???validate??????????????????????????????????????????????????????????????????
    sb.append("uploader.on( 'error', function(type) {if(type=='Q_TYPE_DENIED'){tip('?????????????????????');}if(type=='Q_EXCEED_NUM_LIMIT'){tip('??????????????????');}if(type=='F_DUPLICATE'){tip('?????????????????????????????????');}if(type=='F_EXCEED_SIZE'){tip('????????????????????????');}if(type=='Q_EXCEED_SIZE_LIMIT'){tip('??????????????????');}});");
    sb.append("uploader.on( 'uploadComplete', function( file ) {$( '#" + name + "'+file.id ).find('.progress').fadeOut('slow');});");
    if (!auto) {
        sb.append("var $btn=$('#" + name + "ctlBtn');");
        sb.append("uploader.on('all', function (type) {if (type === 'startUpload') {state = 'uploading';} else if (type === 'stopUpload'){state = 'paused';} else if (type === 'uploadFinished'){state = 'done';}if (state === 'uploading') {$btn.text('????????????');} else {$btn.text('????????????');}});");
        sb.append("\r\n$btn.on('click', function () {if (state === 'uploading') {uploader.stop();} else {uploader.upload();}});");
    }
    if ("true".equals(readOnly) || "readOnly".equals(readOnly)) {
        sb.append("\r\n$('#" + name + "picker').find('div:eq(0)').css('display','none');");
    }
    sb.append("$('#" + showImgDiv + "').on('mouseenter','li',function(){$(this).find('.hidetitle').slideDown(500);});$('#" + showImgDiv + "').on('mouseleave','li',function(){$(this).find('.hidetitle').slideUp(500);});");
    // ?????????????????????
    sb.append("$('#" + showImgDiv + "').on('click', 'span',function() {var spanopt=$(this).attr('class');var optpath=$(this).text();\r\n");
    // 
    sb.append("if(spanopt.indexOf('titledel')>=0){if(0==optpath){var optimgname=$(this).parent('.hidetitle').prev('img').attr('name');var img_file_div='" + name + "'+optimgname.substring(0,optimgname.indexOf('img'));$('#'+img_file_div).find('.del').trigger('click');}else{imgDelReq(optpath,this);}}\r\n");
    sb.append("if(spanopt.indexOf('titledown')>=0){var downsrc=\"" + showAndDownUrl + "\"+optpath+'?down=true';location.href=downsrc;//$(this).find('a').click(function(event){event.stopPropagation()});\r\n}});");
    // ??????
    sb.append("$list.on(\"click\", \".down\",function(){var optpath=$(this).text();if(0!=optpath){var downsrc=\"" + showAndDownUrl + "\"+optpath+'?down=true';location.href=downsrc;}});");
    // ??????
    sb.append("$list.on(\"click\", \".del\", function () {var delspantext=$(this).text();var itemObj=$(this).closest(\".item\");var id=itemObj.attr(\"id\").substring(" + name.length() + ");var delpath=itemObj.find(\"input[name='" + nameWithspchar + "']\").val();if(undefined==delpath||null==delpath){delpath=delspantext;if(delspantext==0){itemObj.remove();uploader.removeFile(id);var myimgli=$('#" + showImgDiv + "').find(\"img[name='\"+id+\"img']\").closest('li');myimgli.off().find('.hidetitle').off().end().remove();\r\nreturn false;}}");
    // sb.append("$list.on(\"click\", \".del\", function () {var delspantext=$(this).text();var itemObj=$(this).closest(\".item\");var id=itemObj.attr(\"id\").substring("+name.length()+");var delpath=itemObj.find(\"input[name='"+name+"']\").val();if((undefined==delpath||null==delpath) && delspantext==1){itemObj.remove();var fordelInput=$list.children('.fordel').children('input');if($(this).text()==0){uploader.removeFile(id);var myimgli=$('#"+showImgDiv+"').find(\"img[name='\"+id+\"img']\").closest('li');myimgli.off().find('.hidetitle').off().end().remove();}\r\nif(fordelInput.length>0){fordelInput.val(exsitPathArr_"+name+".join(','));}return false;}");
    sb.append("$.post('" + url + "',{path:delpath,swfTransform:'" + swfTransform + "',isdel:\"1\"},function(aj){var data=JSON.parse(aj);if(data.success){reset_" + name + "_dataTypeInpVal(0);var fordelInput = $list.children('.fordel').children('input');itemObj.remove();if(delspantext==0){uploader.removeFile(id);var myimgli=$('#" + showImgDiv + "').find(\"img[name='\"+id+\"img']\").closest('li');\r\nmyimgli.off().find('.hidetitle').off().end().remove();}else if(fordelInput.length > 0) {exsitPathArr_" + name + ".removeItem(delpath);fordelInput.val(exsitPathArr_" + name + ".join(','));\r\n}\r\n}\r\n});\r\n});");
    sb.append("if(location.href.indexOf('load=detail')!=-1){$('#" + name + "uploader').find('.btns').addClass('virtual-hidden').css('visibility','hidden');$list.find('span.del').css('display','none');");
    if ("image".equals(type)) {
        sb.append("$('#" + showImgDiv + "').find('.titledel').css('display','none');");
    } else {
        sb.append("$('#" + name + "uploader').find('.del').closest('td').css('display','none');");
    }
    sb.append("}\r\n});");
    // ???????????????
    sb.append("\r\nfunction mygetFileName(filepath){var fileNameEndindex = filepath.lastIndexOf('_');var filenameSuffix = filepath.lastIndexOf('.');if(fileNameEndindex<0){fileNameEndindex = filepath.length;}if(filepath.lastIndexOf('\\\\')>0){return filepath.substring(filepath.lastIndexOf('\\\\')+1,fileNameEndindex)+filepath.substring(filenameSuffix);\r\n}else if(filepath.lastIndexOf('/')>0){return filepath.substring(filepath.lastIndexOf('/')+1,fileNameEndindex)+filepath.substring(filenameSuffix);}else{return filepath;}}");
    // ?????????
    sb.append("function randomFor(n){var rnd='';for(var i=0;i<n;i++){rnd+=Math.floor(Math.random()*10);\r\n}\r\nreturn rnd;}\r\n");
    sb.append("</script>");
}


public boolean isDuplicate(){
    return duplicate;
}


public int getFileNumLimit(){
    return fileNumLimit;
}


public void setShowAndDownUrl(String showAndDownUrl){
    this.showAndDownUrl = showAndDownUrl;
}


public String getSpecialCharName(){
    String temp = getName();
    if (temp.contains(".")) {
        this.name = name.replace(".", "");
    }
    if (temp.contains("[")) {
        this.name = name.replace("[", "");
    }
    if (temp.contains("]")) {
        this.name = name.replace("]", "");
    }
    return temp;
}


public void setPathValues(String pathValues){
    this.pathValues = pathValues;
}


public void setButtonStyle(String buttonStyle){
    this.buttonStyle = buttonStyle;
}


public String getButtonText(){
    if (oConvertUtils.isEmpty(buttonText)) {
        buttonText = "????????????";
    }
    return buttonText;
}


public void setType(String type){
    this.type = type;
}


public String getExtensions(){
    return extensions;
}


public int doEndTag(){
    JspWriter out = null;
    StringBuffer sb = new StringBuffer();
    try {
        out = this.pageContext.getOut();
        end(sb);
        out.print(sb.toString());
        out.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            try {
                out.clearBuffer();
                sb.setLength(0);
                sb = null;
                this.showImgDiv = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    return EVAL_PAGE;
}


public boolean isDisplayTxt(){
    return displayTxt;
}


public int getFileSingleSizeLimit(){
    return fileSingleSizeLimit;
}


public void setFileNumLimit(int fileNumLimit){
    this.fileNumLimit = fileNumLimit;
}


public int getSize(){
    return size;
}


public String getExtendParams(){
    return extendParams;
}


public String getShowAndDownUrl(){
    return showAndDownUrl;
}


public void setAuto(boolean auto){
    this.auto = auto;
}


public String getReadOnly(){
    return readOnly;
}


public boolean isSwfTransform(){
    return swfTransform;
}


}