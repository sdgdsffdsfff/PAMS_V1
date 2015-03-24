<table class="repgGrid">
<tr>
<th width="40">序号</th>
<th width="100">人员</th>
<th width="60">考核总分</th>
<th width="350">公司</th>
<th>部门</th>
</tr>
<#assign total = 0>
<#assign deptcname_old="">
<#assign orgcname_old="">

<#list obj.jsls as aobj>
<#assign total = total + aobj.zxsccskh?number>
<tr>
<td>${aobj_index+1}</td>
<td><a href="${base}/module/pams/gxgl/rep/zxqk/gxd/rep/main_zxqk_yfbzs_csfbzs.action?ownerctx=${aobj.loginname}&reptype=${obj.reptype}&begindate=${obj.begindate}&enddate=${obj.enddate}">${aobj.username}</a></td>
<td <#if aobj.zxsccskh?number &gt; 0 >style="font-weight:bold;color:red"</#if>>
${aobj.zxsccskh}
</td>
<td><#if orgcname_old!=aobj.orgcname>${aobj.orgcname}</#if></td>
<td><#if deptcname_old!=aobj.deptcname>${aobj.deptcname}</#if></td>
</tr>
<#assign orgcname_old = aobj.orgcname>
<#assign deptcname_old = aobj.deptcname>

</#list>

<tr>
<td></td>
<td></td>
<td <#if total?number &gt; 0 >style="font-weight:bold;color:red"</#if>>
${total}</td>
<td></td>
<td></td>
</tr>
</table>