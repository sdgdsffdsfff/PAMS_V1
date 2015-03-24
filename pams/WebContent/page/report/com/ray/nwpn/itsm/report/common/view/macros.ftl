<#macro showheader repname>

<script>
jQuery(function($)
{
	$('#site-nav ul li').click(function()
	{
		if($(this).hasClass('changetype'))
		{
			$(this).parent().find('li').removeClass('current');
			$(this).addClass('current');
		}
	})
});	
</script>

<h2 class="noBorder">${repname}</h2>

</#macro>