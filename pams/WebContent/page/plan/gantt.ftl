{
	"t": "${obj.plan.cname}",
	"start": "${obj.plan.planstartdate?string('yyyy-MM-dd')}",
	"end": "${obj.plan.planenddate?string('yyyy-MM-dd')}",
	"plan": "0.33",
	"actual": "0.5",
	"phases": [
	<#list obj.plan.subplans as subplan>
	{
		"t": "${subplan.cname}",
		"start": "<#if subplan.planstartdate==""><#else>${subplan.planstartdate?date}</#if>",
		"end": "<#if subplan.planenddate==""><#else>${subplan.planenddate?date}</#if>",
		"plan": "0.8",
		"actual": "0.5"
	}
	<#if subplan_index &lt; obj.plan.subplans?size-1>,</#if>
	</#list>
	]
}