<#assign actionErrors = stack.findValue("actionErrors")?if_exists/>
<#if actionErrors?has_content || fieldErrors?has_content>
	<#list fieldErrors?keys as key>
	<#list fieldErrors[key] as error>
	<script>
		alert("${action.getText(error)?if_exists?replace("\n", "<br/>")}");
	</script>
	</#list>
	</#list>		
	<#list actionErrors as error>
	<script>
		alert("${action.getText(error)?if_exists?replace("\n", "<br/>")}");
	</script>
	</#list>
</#if>