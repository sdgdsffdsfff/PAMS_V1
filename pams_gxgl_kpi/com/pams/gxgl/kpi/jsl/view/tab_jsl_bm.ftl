<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="300">公司</th>
<th width="100">考核总分</th>
</tr>
<#assign total = 0>
<#list obj.jsls as aobj>
<#assign total = total + aobj.zxsccskh?number>
<tr>
<td>${aobj_index+1}</td>

<td><a href="${base}/module/pams/gxgl/kpi/jsl/rep_main_jsl_ry.action?internal=${aobj.internal}&reptype=${arg.reptype}&begindate=${arg.begindate}&enddate=${arg.enddate}">${aobj.orgcname}</a></td>
<td <#if aobj.zxsccskh ?number &gt; 0 >style="font-weight:bold;color:red"</#if>>
${aobj.zxsccskh}
</td>
</tr>
</#list>

<tr>
<td></td>
<td></td>
<td <#if total?number &gt; 0 >style="font-weight:bold;color:red"</#if>>
${total}</td>
</tr>
</table>