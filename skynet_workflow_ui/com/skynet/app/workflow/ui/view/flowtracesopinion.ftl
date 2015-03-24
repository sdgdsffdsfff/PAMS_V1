<#list obj.opinions as aobj>
<tr><td>${aobj.createtime.substring(0,16)}: ${aobj.name}  填写意见：${aobj.opinion}</td></tr>	
</#list>