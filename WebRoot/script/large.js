/**
  * 功能：复选框全选
  * 参数：form对象   */
 function SelectAll(){
var ids=$("input[name='downloadFile']");    
for(var i=0;i<ids.length;i++)
{   
ids[i].checked="checked";
}
 }
  /**
  * 功能：复选框反选
  * 参数：form对象   */
 function TurnOver(oForm){
var ids=$("input[name='downloadFile']");
 for(var i=0;i<ids.length;i++)
{if(ids[i].checked==true)
{    
ids[i].checked="";   
}else{   
ids[i].checked="checked";}
}
} /**
  * 功能：下载文件列表实现
  * 参数：form对象*/
 function DownLodSelected(){
 var params = '' ;
var files=$("input[name='downloadFile']");
		var flag = false;
		var count = 0;
		for ( var i = 0, len = files.length; i < len; i++) {
			if (files[i].checked) {
				params += 'downloadFile=' + getFileName(files[i].value) + '&' ;
				files[i].value = getFileName(files[i].value);
				count++;
				flag = true;
			}
		}
		if (flag) {
			if (count > 1)
				alert("您选择了" + count + "个文件进行批量下载，可能要花费较长的时间，请耐心等待!");
				window.location = 'report_multiFileDownload.action?' + params ;
			return true;
		} else {
			alert("请至少选择一个文件进行下载!");
			return false;
		}
  }
  	function getFileName(name) {
		return name.substring(name.lastIndexOf("/") + 1, name.length);
	}