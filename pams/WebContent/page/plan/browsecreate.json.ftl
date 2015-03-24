[
<#list obj.plans as plan>
	{
		"id":"${plan.id}",
		"cname":"${plan.cname}",
		"planstartdate":"<#if plan.planstartdate==""><#else>${plan.planstartdate?date}</#if>",
		"planenddate":"<#if plan.planenddate==""><#else>${plan.planenddate?date}</#if>",
		"baseplanworkload":"${plan.baseplanworkload}",
		"actualstartdate":"<#if plan.actualstartdate==""><#else>${plan.actualstartdate?date}</#if>",
		"actualenddate":"<#if plan.actualenddate==""><#else>${plan.actualenddate?date}</#if>"
	}
	<#if plan_index &lt; obj.plans?size -1>,</#if>	
</#list>
]