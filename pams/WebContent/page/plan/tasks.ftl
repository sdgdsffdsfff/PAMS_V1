{
	"head":["名称", "计划开始", "计划结束", "资源", "完工比", "状态"],
	"body":[
	<#list obj.plan.subplans as subplan>
	{"tds":["<a href=''>${subplan.cname}</a>", "<#if subplan.planstartdate==""><#else>${subplan.planstartdate?date}</#if>", "<#if subplan.planenddate==""><#else>${subplan.planenddate?date}</#if>", "${subplan.planheadercname}", "10%", "<span class='text-danger'>延迟</span>"]}

	<#if subplan_index &lt; obj.plan.subplans?size-1>,</#if>
	</#list> 
	]
}