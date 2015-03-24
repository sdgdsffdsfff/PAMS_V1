[
	<#list obj.bean_owners as p>
	{"ownerctx" : "${p.ownerctx}", "cname" : "${p.cname}", "ctype" : "${p.ctype}"}
	<#if p_index &lt; obj.bean_owners?size - 1>,</#if>
	</#list>	
]
