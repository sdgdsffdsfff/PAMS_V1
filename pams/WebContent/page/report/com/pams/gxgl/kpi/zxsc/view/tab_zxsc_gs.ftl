<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="300">公司</th>
<th width="100">考核总分</th>
</tr>
<#assign total = 0>
<#list obj.zxscs as aobj>
<#assign total = total + aobj.zxsccskh?number>
<tr>
<td>${aobj_index+1}</td>
<td><a href="${base}/module/pams/gxgl/kpi/zxsc/rep/main_zxsc_bm.action?internal=${aobj.internal}&reptype=${obj.reptype}&begindate=${obj.begindate}&enddate=${obj.enddate}">${aobj.orgcname}</a></td>
<td <#if aobj.zxsccskh?number &gt; 0>style="font-weight:bold;color:red"<#else></#if>>${aobj.zxsccskh}</td>
</tr>
</#list>

<tr>
<td></td>
<td></td>
<td>${total}</td>
</tr>
</table>